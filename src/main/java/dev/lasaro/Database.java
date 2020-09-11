package dev.lasaro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Database {
    private Path path;

    public Database(String path) {
        this.path = Path.of(path);
    }

    public boolean isArquead() {
        return Files.isDirectory(this.path);
    }

    public Stream<Path> getCollections() {
        try {
            return Files.list(this.path);
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    public Error createCollection(String name) {
        Path directoryCollection = this.path.resolve(name);
        if (Files.exists(directoryCollection)) {
            return new Error("Já existe uma coleção com este nome.");
        }

        try {
            Files.createDirectory(directoryCollection);
        } catch (IOException e) {
            e.printStackTrace();
            return new Error("Erro ao criar diretório para coleção.");
        }
        return new Error();
    }

    public Error createDatabase() {
        if (Files.exists(this.path)) {
            return new Error("Já existe um diretório com este nome.");
        }

        try {
            Files.createDirectory(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            return new Error("Não foi possível criar este diretório.");
        }

        Error err = this.createCollection("_arquea");
        if (err.isSuccess()) {
            // criar documento.
            return new Error();
        }
        return err;
    }
}
