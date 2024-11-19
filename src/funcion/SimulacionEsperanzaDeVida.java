/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacionEsperanzaDeVida {

    private double esperanzaDeVidaInicial = 77.0; // Esperanza de vida en 2023
    private double tasaDañoAmbientalAnual = 5.0;  // Incremento anual del daño ambiental
    private double variabilidadAleatoria = 0.5;   // Variabilidad en la simulación

    public void agregarGrafica(JPanel jPanel7, int añosFuturos) {
        // Generar los datos de la simulación
        DefaultCategoryDataset dataset = generarDatosSimulacion(añosFuturos);

        // Crear la gráfica
        JFreeChart chart = ChartFactory.createLineChart(
                "Proyección de Esperanza de Vida",
                "Año",
                "Esperanza de Vida",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Crear el panel de la gráfica
        ChartPanel chartPanel = new ChartPanel(chart);

        // Configurar el panel para ajustarse al JPanel existente
        chartPanel.setPreferredSize(new java.awt.Dimension(jPanel7.getWidth(), jPanel7.getHeight()));
        chartPanel.setMouseWheelEnabled(true);

        // Limpiar el JPanel antes de añadir la gráfica
        jPanel7.removeAll();
        jPanel7.setLayout(new java.awt.BorderLayout());
        jPanel7.add(chartPanel, java.awt.BorderLayout.CENTER);
        jPanel7.validate(); // Actualizar el diseño
    }

    private DefaultCategoryDataset generarDatosSimulacion(int añosFuturos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Double> esperanzaDeVida = new ArrayList<>();
        esperanzaDeVida.add(esperanzaDeVidaInicial);

        int añoActual = 2023;

        for (int i = 0; i <= añosFuturos; i++) {
            double impactoDañoAmbiental = tasaDañoAmbientalAnual * -0.05;
            double variabilidad = new Random().nextDouble() * 2 * variabilidadAleatoria - variabilidadAleatoria;
            double nuevaEsperanza = esperanzaDeVida.get(i) + impactoDañoAmbiental + variabilidad;
            esperanzaDeVida.add(Math.max(nuevaEsperanza, 0)); // Aseguramos que no sea negativo

            dataset.addValue(esperanzaDeVida.get(i), "Esperanza de Vida", Integer.toString(añoActual + i));
        }

        return dataset;
    }
}
