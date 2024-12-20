package fr.istic.vv.Exercise6;

public class PrivateFieldInfo {
    private final String className;
    private final String fieldName;
    private final String packageName;

    public PrivateFieldInfo(String className, String fieldName, String packageName) {
        this.className = className;
        this.fieldName = fieldName;
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return "Class: " + className + ", Field: " + fieldName + ", Package: " + packageName;
    }
}