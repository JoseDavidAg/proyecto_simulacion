/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
/**
 *
 * @author ambro
 */
public class NewClass {
    

    public static void main(String[] args) {
        // Crear JFrame
        JFrame frame = new JFrame("ScrollPane Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear un panel con contenido
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(50, 1)); // Muchos elementos para forzar el scroll
        for (int i = 1; i <= 50; i++) {
            panel.add(new JLabel("Elemento " + i));
        }

        // Envolver el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        
        // Personalizar las barras de desplazamiento
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI());

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new CustomScrollBarUI());

        // Agregar el JScrollPane al JFrame
        frame.getContentPane().add(scrollPane);

        // Mostrar el frame
        frame.setVisible(true);
    }

    // Clase para personalizar las barras de desplazamiento
    static class CustomScrollBarUI extends BasicScrollBarUI {
        private static final int THUMB_SIZE = 10;

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(100, 100, 200); // Color del "pulgar"
            this.trackColor = new Color(230, 230, 230); // Color de la pista
        }

        protected Dimension getThumbSize() {
            return new Dimension(THUMB_SIZE, THUMB_SIZE);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton(); // Sin botones
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton(); // Sin botones
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
    }
}

    



