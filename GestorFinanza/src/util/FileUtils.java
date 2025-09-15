package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtils {
    public static void ensureDataFolder() {
        try {
            Files.createDirectories(Paths.get("data"));
            // create files if not exist
            Path[] ps = {Paths.get("data/usuarios.txt"), Paths.get("data/transacciones.csv"), Paths.get("data/metas.txt"), Paths.get("data/presupuestos.csv")};
            for (Path p : ps) if (!Files.exists(p)) Files.createFile(p);
        } catch (IOException e) { System.out.println("Error creando carpeta data: " + e.getMessage()); }
    }

    public static List<String> readLines(String path) {
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) return new ArrayList<>();
            return Files.readAllLines(p);
        } catch (IOException e) { return new ArrayList<>(); }
    }

    public static void writeLines(String path, List<String> lines) {
        try {
            Path p = Paths.get(path);
            Files.createDirectories(p.getParent());
            Files.write(p, lines, java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) { System.out.println("Error escribiendo archivo " + path + ": " + e.getMessage()); }
    }
}
