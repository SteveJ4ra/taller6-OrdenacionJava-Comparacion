package ed.u2.sorting.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatasetGenerator {
    private static final Random rand = new Random(42); // Semilla Obligatoria
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    // Generar todos los datasets de los .csv
    public static void generateAll() {
        try {
            generatorOf100Appointments();
            generateAlmostOrderedQuotes();
            generatePatients500();
            generateReverseInventory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCSV(String fileName, List<String> lines) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)))) {
            for (String line : lines) {
                pw.println(line);
            }
        }
    }

    private static void generatorOf100Appointments() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;apellido;fechaHora");

        // lista de apellidos para generar aleatoriamente
        String[] apellidos = {"Guerrero", "Naranjo", "Cedeño", "Torres", "Vargas", "Romero", "Silva", "Molina"};

        for (int i = 1; i <= 100; i++) {
            String id = String.format("CITA-%03d", i);
            String ape = apellidos[rand.nextInt(apellidos.length)];
            int dia = 1 + rand.nextInt(31);
            int hora = 8 + rand.nextInt(11);
            int min = rand.nextInt(60);
            LocalDateTime ldt = LocalDateTime.of(2025, 3, dia, hora, min);
            lines.add(id + ";" + ape + ";" + ldt.format(DATE_FMT));
        }
        writeCSV("citas_100.csv", lines);
    }

    private static void generateAlmostOrderedQuotes() throws IOException {
        List<appointmentRow> data = new ArrayList<>();
        String[] apellidos = {"Guerrero", "Naranjo", "Cedeño"};
        for (int i = 1; i <= 100; i++) {
            int dia = 1 + rand.nextInt(31);
            int hora = 8 + rand.nextInt(11);
            int min = rand.nextInt(60);
            LocalDateTime ldt = LocalDateTime.of(2025, 3, dia, hora, min);
            data.add(new appointmentRow(String.format("CITA-%03d", i), apellidos[rand.nextInt(apellidos.length)], ldt));
        }

        // 1. Ordenar
        data.sort(Comparator.comparing(c -> c.fecha));
        // 2. Perturbar con 5 swaps
        for (int k = 0; k < 5; k++) {
            Collections.swap(data, rand.nextInt(100), rand.nextInt(100));
        }

        List<String> lines = new ArrayList<>();
        lines.add("id;apellido;fechaHora");
        for (appointmentRow c : data) lines.add(c.id + ";" + c.apellido + ";" + c.fecha.format(DATE_FMT));
        writeCSV("citas_100_casi_ordenadas.csv", lines);
    }

    static class appointmentRow {
        String id, apellido;
        LocalDateTime fecha;
        public appointmentRow(String i, String a, LocalDateTime f) { id=i; apellido=a; fecha=f; }
    }

    private static void generatePatients500() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;apellido;prioridad");
        String[] grupoA = {"Ramírez", "Zambrano", "Jaramillo"};
        String[] grupoB = {"Ortega", "Villavicencio"};
        String[] grupoC = {"Yánez", "Quito"};

        for (int i = 1; i <= 500; i++) {
            String id = String.format("PAC-%04d", i);
            int r = rand.nextInt(100);
            String ape = (r < 60) ? grupoA[rand.nextInt(grupoA.length)] :
                    (r < 90) ? grupoB[rand.nextInt(grupoB.length)] : grupoC[rand.nextInt(grupoC.length)];
            lines.add(id + ";" + ape + ";" + (1 + rand.nextInt(3)));
        }
        writeCSV("pacientes_500.csv", lines);
    }

    private static void generateReverseInventory() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id;insumo;stock");
        for (int i = 1; i <= 500; i++) {
            lines.add(String.format("ITEM-%04d;Insumo;%d", i, (501 - i)));
        }
        writeCSV("inventario_500_inverso.csv", lines);
    }
}