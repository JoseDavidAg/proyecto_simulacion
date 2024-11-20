/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcion;
import java.util.ArrayList;
import java.util.List;

//permite visualizar el archivo csv dentro de JTable. Considero que apartir del arrego obtenido del CSV se puede trabajar para la graficacion
public class ContenidoCSV {
    private List<String[]> filas;

    public ContenidoCSV() {
        this.filas = new ArrayList<>();
    }

    public void limpiar() {
        filas.clear();
    }

    // Agrega una fila asegurando que tenga 5 columnas
    public void agregarFila(String[] fila) {
        String[] filaAjustada = ajustarColumnas(fila, 6);
        filas.add(filaAjustada);
    }

    public List<String[]> obtenerFilas() {
        return filas;
    }

    public boolean estaVacio() {
        return filas.isEmpty();
    }

    public String[] obtenerFila(int indice) {
        if (indice >= 0 && indice < filas.size()) {
            return filas.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public int obtenerNumeroFilas() {
        return filas.size();
    }

    // Método privado para ajustar el número de columnas de cada fila
    private String[] ajustarColumnas(String[] fila, int numColumnas) {
        String[] filaAjustada = new String[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            // Llenar con el valor original si existe, si no llenar con vacío
            filaAjustada[i] = (i < fila.length) ? fila[i] : "";
        }
        return filaAjustada;
    }
}
