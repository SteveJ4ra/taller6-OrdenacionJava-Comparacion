package ed.u2.sorting.controller;

import ed.u2.sorting.model.*;
import ed.u2.sorting.view.ConsoleView;
import java.util.Arrays;

public class SortingController {

    private final ConsoleView view;
    private final CsvDataLoader dataLoader;

    public SortingController() {
        this.view = new ConsoleView();
        this.dataLoader = new CsvDataLoader();
    }

    public void start() {
        // Generar los datos
        DatasetGenerator.generateAll();

        // Mostrar mensaje de bienvenida
        view.showWelcomeMessage();

        // Ejecutar los experimentos para las pruebas de ordenamiento de cada dataset con cada algoritmo
        runExperiment("Dataset 1: Citas Aleatorias", "citas_100.csv", 2, true);
        runExperiment("Dataset 2: Citas Casi Ordenadas", "citas_100_casi_ordenadas.csv", 2, true);
        runExperiment("Dataset 3: Pacientes", "pacientes_500.csv", 2, false);
        runExperiment("Dataset 4: Inventario Inverso", "inventario_500_inverso.csv", 2, false);
    }

    private void runExperiment(String title, String file, int colIndex, boolean isDate) {
        int[] data = dataLoader.loadCsvAndExtractKey(file, colIndex, isDate);

        if (data.length == 0) return;

        view.printHeader(title, data.length);

        testAlgorithm("Insertion", data);
        testAlgorithm("Selection", data);
        testAlgorithm("Bubble", data);

        view.printFooter();
    }

    private void testAlgorithm(String name, int[] originalData) {
        long[] times = new long[10]; // R=10
        SortStats stats = new SortStats(name);

        for (int i = 0; i < 10; i++) {
            int[] arr = Arrays.copyOf(originalData, originalData.length);
            SortStats runStats = new SortStats(name);
            System.gc(); // Limpieza de memoria para evitar que se acumule-

            switch (name) {
                case "Insertion" -> InsertionSort.sort(arr, runStats);
                case "Selection" -> SelectionSort.sort(arr, runStats);
                case "Bubble" -> BubbleSort.sort(arr, runStats);
            }

            times[i] = runStats.timeNano;
            if (i == 9) { // Guardamos stats de la última ejecución válida
                stats.comparisons = runStats.comparisons;
                stats.swaps = runStats.swaps;
            }
        }

        // Cálculo de Mediana (Descartando las 3 primeras)
        long[] validRuns = Arrays.copyOfRange(times, 3, 10);
        Arrays.sort(validRuns); // Ordena los arreglos válidos
        long medianTime = validRuns[3]; // En este caso tomar el 4 dato de los 7 (mediana)
                                        // (i = 3 o cuarto dato)

        view.printResultRow(stats, medianTime);
    }
}