package dev.lasaro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class Database {
    final private Path path;

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

    public Result<Path> createCollection(String name) {
        Path directoryCollection = this.path.resolve(name);
        if (Files.exists(directoryCollection)) {
            return new Result<>("Já existe uma coleção com este nome.");
        }

        try {
            Files.createDirectory(directoryCollection);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>("Erro ao criar diretório para coleção.");
        }
        return new Result<>(directoryCollection);
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

        Result<Path> newCollection = this.createCollection("_arquea");
        if (newCollection.isSuccess()) {
            Collection collection = new Collection(newCollection.getData());

            HashMap<String, Object> conf = new HashMap<>();
            conf.put("id", "conf");
            conf.put("version", Version.getVersion());

            Result<ObjectId> doc = collection.insert(conf);
            if (doc.isError()) {
                return new Error(doc.getMessage());
            }
            return new Error();
        }
        return new Error(newCollection.getMessage());
    }
}
