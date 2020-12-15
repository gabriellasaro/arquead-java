package dev.lasaro.arquead;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public final class Util {
    public static void deleteDirectory(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFileToJson(Path path, Object data) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        writer.write(gson.toJson(data));
        writer.close();
    }

    public static Object readFileToJson(Path docDirectory) throws IOException {
        String rawData = Files.readString(docDirectory);
        Gson gson = new Gson();
        return gson.fromJson(rawData, JsonObject.class);
    }
}
