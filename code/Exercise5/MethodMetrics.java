package fr.istic.vv;

import java.util.List;

public class MethodMetrics {
    private final String packageName;
    private final String className;
    private final String methodName;
    private final List<String> parameterTypes;
    private final int cyclomaticComplexity;

    public MethodMetrics(String packageName, String className, String methodName, List<String> parameterTypes, int cyclomaticComplexity) {
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public int getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }
}
