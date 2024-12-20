package fr.istic.vv.Exercise6;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ClassVisitor extends VoidVisitorAdapter<List<PrivateFieldInfo>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<PrivateFieldInfo> collector) {
        if (n.isPublic()) {
            n.getFields().forEach(field -> {
                if (field.isPrivate() && !hasPublicGetter(n, field)) {
                    collector.add(new PrivateFieldInfo(n.getNameAsString(), field.getVariable(0).getNameAsString(), n.getFullyQualifiedName().orElse("")));
                }
            });
        }
        super.visit(n, collector);
    }

    private boolean hasPublicGetter(ClassOrInterfaceDeclaration classDecl, FieldDeclaration field) {
        String fieldName = field.getVariable(0).getNameAsString();
        String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        for (MethodDeclaration method : classDecl.getMethods()) {
            if (method.isPublic() && method.getNameAsString().equals(getterName) && method.getParameters().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}