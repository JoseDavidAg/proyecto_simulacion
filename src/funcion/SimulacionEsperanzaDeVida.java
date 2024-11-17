/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacionEsperanzaDeVida extends JFrame {
    private double esperanzaDeVidaInicial = 77.0; // Esperanza de vida en 2023
    private double tasaDañoAmbientalAnual = 5.0;  // Incremento anual del daño ambiental
    private double variabilidadAleatoria = 0.5;   // Variabilidad en la simulación

    public SimulacionEsperanzaDeVida(int añosFuturos) {
        setTitle("Simulación de Esperanza de Vida");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultCategoryDataset dataset = generarDatosSimulacion(añosFuturos);
        JFreeChart chart = ChartFactory.createLineChart(
                "Proyección de Esperanza de Vida",
                "Año",
                "Esperanza de Vida",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
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
            esperanzaDeVida.add(Math.max(nuevaEsperanza, 0));  // Aseguramos que no sea negativo

            dataset.addValue(esperanzaDeVida.get(i), "Esperanza de Vida", Integer.toString(añoActual + i));
        }

        return dataset;
    }

    public static void main(String[] args) {
        int añosFuturos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de años para simular:"));
        SimulacionEsperanzaDeVida frame = new SimulacionEsperanzaDeVida(añosFuturos);
        frame.setVisible(true);
    }
}
