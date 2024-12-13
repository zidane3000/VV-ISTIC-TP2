package fr.istic.vv;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

public class CyclomaticComplexityCalculator {

    public static int calculate(MethodDeclaration method) {
        int complexity = 1; // Complexité initiale

        // Ajout des structures conditionnelles
        complexity += method.findAll(IfStmt.class).size();
        complexity += method.findAll(ForStmt.class).size();
        complexity += method.findAll(WhileStmt.class).size();
        complexity += method.findAll(DoStmt.class).size();
        complexity += method.findAll(SwitchEntry.class).size();
        complexity += method.findAll(CatchClause.class).size();

        // Ajouter la complexité pour les opérateurs logiques
        complexity += method.toString().split("\\|\\|").length - 1; // `||`
        complexity += method.toString().split("&&").length - 1; // `&&`

        return complexity;
    }
}
