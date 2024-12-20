package fr.istic.vv.Exercise6;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoGetterAnalyzer {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Should provide the path to the source code");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isDirectory() || !file.canRead()) {
            System.err.println("Provide a path to an existing readable directory");
            System.exit(2);
        }

        File projectDir = new File(args[0]);
        List<PrivateFieldInfo> privateFieldsWithoutGetters = new ArrayList<>();
        analyzeProject(projectDir, privateFieldsWithoutGetters);
        ReportGenerator.generateReport(privateFieldsWithoutGetters, "code/javaparser-starter/src/main/java/fr/istic/vv/Exercise6/reports/report.md");
    }

    private static void analyzeProject(File projectDir, List<PrivateFieldInfo> privateFieldsWithoutGetters) throws IOException {
        SourceRoot sourceRoot = new SourceRoot(projectDir.toPath());
        sourceRoot.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> unit.accept(new ClassVisitor(), privateFieldsWithoutGetters));
            return SourceRoot.Callback.Result.DONT_SAVE;
        });
    }
}