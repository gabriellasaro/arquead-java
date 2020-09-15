package dev.lasaro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Document {
    private final Path path;

    public Document(Path path) {
        this.path = path;
    }

    private Path getPathByVersion(String version) {
        return this.path.resolve(version + ".json");
    }

    public Stream<Path> getVersions() {
        try {
            return Files.list(this.path).sorted();
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    public Path getPathToCurrentVersion() {
        long count = this.getVersions().count();

        return this.getVersions().skip(count - 1).findFirst().get();
    }

    public ObjectId getObjectId() {
        String basename = this.getPathToCurrentVersion().getFileName().toString();
        int version = Integer.parseInt(basename.substring(0, basename.length()-5));

        return new ObjectId(this.path.getFileName().toString(), version);
    }
}
