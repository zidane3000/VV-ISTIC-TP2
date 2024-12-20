package fr.istic.vv.Exercise6;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public static void generateReport(List<PrivateFieldInfo> privateFieldsWithoutGetters, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("# Report of Private Fields Without Public Getters\n\n");
            writer.write("| Package | Class | Field |\n");
            writer.write("|---------|-------|-------|\n");
            for (PrivateFieldInfo info : privateFieldsWithoutGetters) {
                writer.write(String.format("| %s | %s | %s |\n", info.getPackageName(), info.getClassName(), info.getFieldName()));
            }
        }
    }
}