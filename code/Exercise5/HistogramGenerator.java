package fr.istic.vv;

import org.knowm.xchart.Histogram;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HistogramGenerator {

    public void generateHistogram(Map<String, Integer> ccValues, String projectName) {
        // Convertir les valeurs de CC en une liste
        List<Integer> values = ccValues.values().stream().collect(Collectors.toList());

        // Créer un histogramme avec XChart
        Histogram histogram = new Histogram(values, 10); // 10 bins pour les intervalles

        XYChart chart = new XYChartBuilder()
                .title("Histograme - " + projectName)
                .xAxisTitle("Valeur CC")
                .yAxisTitle("Frequence")
                .build();

        // Ajouter les données à la chart
        chart.addSeries("Valuers CC ", histogram.getxAxisData(), histogram.getyAxisData());

        // Sauvegarder l'histogramme en PNG
        try {
            BitmapEncoder.saveBitmap(chart, "histogram_" + projectName, BitmapFormat.PNG);
            System.out.println("histograme.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afficher le graphique (optionnel)
        new SwingWrapper<>(chart).displayChart();
    }
}
