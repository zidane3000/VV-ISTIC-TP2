package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This class visits a compilation unit and
// prints all public enum, classes or interfaces along with their public methods
public class PublicElementsPrinter extends VoidVisitorWithDefaults<Void> {

    private final List<MethodMetrics> methodMetricsList = new ArrayList<>();

    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for(TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {
        if(!declaration.isPublic()) return;
        System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
        for(MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        // Printing nested types in the top level
        for(BodyDeclaration<?> member : declaration.getMembers()) {
            if (member instanceof TypeDeclaration)
                member.accept(this, arg);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if(!declaration.isPublic()) return;
        //System.out.println("  " + declaration.getDeclarationAsString(true, true));

        String packageName = declaration.findCompilationUnit()
                .flatMap(CompilationUnit::getPackageDeclaration)
                .map(pd -> pd.getName().toString())
                .orElse("[default]");

        String className = declaration.findAncestor(ClassOrInterfaceDeclaration.class)
                .map(ClassOrInterfaceDeclaration::getNameAsString)
                .orElse("[Anonymous]");

        String methodName = declaration.getNameAsString();

        List<String> parameterTypes = declaration.getParameters().stream()
                .map(param -> param.getType().asString())
                .collect(Collectors.toList());


        // Calcul de la complexité cyclomatique
        int complexity = CyclomaticComplexityCalculator.calculate(declaration);
        //System.out.println("    Cyclomatic Complexity: " + complexity);

        methodMetricsList.add(new MethodMetrics(packageName, className, methodName, parameterTypes, complexity));

    }
    public void writeReportMarkdown(String outputPath) {
        try (PrintWriter writer = new PrintWriter(outputPath)) {
            writer.println("# Cyclomatic Complexity Report");
            writer.println();
            writer.println("| Package | Class | Method | Parameters | Cyclomatic Complexity |");
            writer.println("|---------|-------|--------|------------|------------------------|");

            for (MethodMetrics metric : methodMetricsList) {
                String parameters = String.join(", ", metric.getParameterTypes());
                writer.printf("| %s | %s | %s | %s | %d |%n",
                        metric.getPackageName(),
                        metric.getClassName(),
                        metric.getMethodName(),
                        parameters,
                        metric.getCyclomaticComplexity());
            }

            System.out.println("Markdown report written to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReport(String outputPath) {
        try (PrintWriter writer = new PrintWriter(outputPath)) {
            writer.println("Package,Class,Method,Parameters,Cyclomatic Complexity");

            for (MethodMetrics metric : methodMetricsList) {
                String parameters = String.join(", ", metric.getParameterTypes());
                writer.printf("%s,%s,%s,\"%s\",%d%n",
                        metric.getPackageName(),
                        metric.getClassName(),
                        metric.getMethodName(),
                        parameters,
                        metric.getCyclomaticComplexity());
            }

            System.out.println("Report written to: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<MethodMetrics> getMethodMetricsList() {
        return methodMetricsList;
    }



}
