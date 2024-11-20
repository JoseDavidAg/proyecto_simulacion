/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;

/**
 *
 * @author ambro
 */
//intento de graficar a partir de los datos del CSV, hay error en la libreria de apache y opencsv
//librerias para metodos estadisticos como regresion lineal
//libreria para leer un archivo CSV
import com.opencsv.CSVReader;
import org.apache.commons.math4.legacy.stat.regression.OLSMultipleLinearRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.FileReader;
import java.util.*;

public class SimulacionEsperanzaDeVida1 {

    public void agregarGraficaDesdeCSV(JPanel jPanel7, String rutaArchivo) {
        try {
            // Cargar datos desde el archivo CSV
            Map<String, List<Double>> datos = cargarDatosDesdeCSV(rutaArchivo);
            List<Double> esperanzaDeVida = datos.get("Esperanza de vida");
            List<Double> tasaDeNatalidad = datos.get("Tasa de natalidad");
            List<Double> danoAmbiental = datos.get("Daño ambiental");

            // Crear el modelo de regresión para predecir la esperanza de vida
            double[] coeficientes = predecirEsperanzaDeVida(tasaDeNatalidad, danoAmbiental, esperanzaDeVida);

            // Graficar los datos actuales
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < esperanzaDeVida.size(); i++) {
                dataset.addValue(esperanzaDeVida.get(i), "Esperanza de Vida", String.valueOf(2020 + i));
            }

            // Crear la gráfica
            JFreeChart chart = ChartFactory.createLineChart(
                    "Proyección de Esperanza de Vida",
                    "Año",
                    "Esperanza de Vida (años)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            // Crear el panel de la gráfica
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(jPanel7.getWidth(), jPanel7.getHeight()));
            chartPanel.setMouseWheelEnabled(true);

            // Limpiar el JPanel antes de añadir la gráfica
            jPanel7.removeAll();
            jPanel7.setLayout(new java.awt.BorderLayout());
            jPanel7.add(chartPanel, java.awt.BorderLayout.CENTER);
            jPanel7.validate(); // Actualizar el diseño
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    private Map<String, List<Double>> cargarDatosDesdeCSV(String rutaArchivo) throws Exception {
        Map<String, List<Double>> datos = new HashMap<>();
        List<String[]> filas;

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            filas = reader.readAll();
        }

        // Procesamos cada variable para extraer los datos de cada año
        String[] variables = filas.get(0);
        for (int i = 0; i < variables.length - 1; i++) {
            datos.put(variables[i], new ArrayList<>());
        }

        for (int i = 1; i < filas.size(); i++) {
            String[] fila = filas.get(i);
            for (int j = 1; j < fila.length - 1; j++) {
                String variable = variables[j];
                datos.get(variable).add(Double.parseDouble(fila[j].replace(",", "")));
            }
        }

        return datos;
    }

    private double[] predecirEsperanzaDeVida(List<Double> tasaDeNatalidad, List<Double> danoAmbiental, List<Double> esperanzaDeVida) {
        // Preparar los datos para la regresión
        int n = tasaDeNatalidad.size();
        double[][] X = new double[n][2]; // Dos variables predictoras (tasa de natalidad y daño ambiental)
        double[] Y = new double[n]; // Esperanza de vida

        for (int i = 0; i < n; i++) {
            X[i][0] = tasaDeNatalidad.get(i);
            X[i][1] = danoAmbiental.get(i);
            Y[i] = esperanzaDeVida.get(i);
        }

        // Usar regresión lineal múltiple
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        
        regression.newSampleData(Y, X);
       
        return regression.estimateRegressionParameters();
    }
  

   
}
