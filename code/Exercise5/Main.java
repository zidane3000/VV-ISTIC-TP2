package fr.istic.vv;


import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.err.println("Should provide the path to the source code");
            System.exit(1);
        }

        File file = new File(args[0]);
        if(!file.exists() || !file.isDirectory() || !file.canRead()) {
            System.err.println("Provide a path to an existing readable directory");
            System.exit(2);
        }

        SourceRoot root = new SourceRoot(file.toPath());
        PublicElementsPrinter printer = new PublicElementsPrinter();
        root.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> unit.accept(printer, null));
            return SourceRoot.Callback.Result.DONT_SAVE;
        });

        // Écriture du rapport après l'analyse
        printer.writeReport("report.csv");
        printer.writeReportMarkdown("report.md");

        // Génération de l'histogramme des complexités cyclomatiques
        Map<String, Integer> ccValues = new HashMap<>();
        for (MethodMetrics metric : printer.getMethodMetricsList()) {
            ccValues.put(metric.getMethodName(), metric.getCyclomaticComplexity());
        }

        // Appeler la méthode de génération de l'histogramme
        HistogramGenerator generator = new HistogramGenerator();
        generator.generateHistogram(ccValues, "Apache Commons Lang");
    }

}



