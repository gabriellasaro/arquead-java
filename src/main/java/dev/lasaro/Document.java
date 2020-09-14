package dev.lasaro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Document {
    private final Path path;

    public Document(Path path) {
        this.path = path;
    }

    private Path getPathByVersion(String version) {
        return this.path.resolve(version + ".json");
    }

    public String readFile(String version) throws IOException {
        return Files.readString(this.getPathByVersion(version));
    }
}
