package ed.u2.sorting.view;

import ed.u2.sorting.model.SortStats;

public class ConsoleView {


    // Formato para cada fila de la tabla
    private final String ROW_FORMAT = "| %-14s | %-12s | %-10s | %-10s | %-12s |%n";

    public void showWelcomeMessage() {
        System.out.println("\n====== BENCHMARKING DE ORDENACIÓN ======");
        System.out.println("Config: R=10, Descartar=3, Mediana de 7 runs.\n");
    }

    // Método que imprime el encabezado de la tabla
    public void printHeader(String datasetTitle, int nSize) {
        System.out.println("\n>>> " + datasetTitle + " (N=" + nSize + ")");
        System.out.println("+----------------+--------------+------------+------------+--------------+");
        System.out.printf(ROW_FORMAT, "Algoritmo", "Tiempo(ns)", "Comparac.", "Swaps", "Estado");
        System.out.println("+----------------+--------------+------------+------------+--------------+");
    }

    public void printResultRow(SortStats stats, long medianTime) {
        System.out.printf(ROW_FORMAT,
                stats.algorithmName,
                medianTime,
                stats.comparisons,
                stats.swaps,
                "OK");
    }

    public void printFooter() {
        System.out.println("+----------------+--------------+------------+------------+--------------+");
    }
}