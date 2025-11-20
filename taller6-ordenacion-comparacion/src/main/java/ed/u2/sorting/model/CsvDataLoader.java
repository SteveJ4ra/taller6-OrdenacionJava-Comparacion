package ed.u2.sorting.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLoader {

    public int[] loadCsvAndExtractKey(String file, int colIndex, boolean isDate) {
        List<Integer> keys = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        File f = new File(file);

        if (!f.exists()) return new int[0];

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) { // UTF-8

            String line = br.readLine(); // Saltar header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > colIndex) {
                    String raw = parts[colIndex];
                    if (isDate) {
                        LocalDateTime ldt = LocalDateTime.parse(raw, fmt);
                        keys.add((int) (ldt.toEpochSecond(ZoneOffset.UTC) / 60));
                    } else {
                        keys.add(Integer.parseInt(raw));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error leyendo " + file + ": " + e.getMessage());
            return new int[0];
        }
        return keys.stream().mapToInt(i -> i).toArray();
    }
}