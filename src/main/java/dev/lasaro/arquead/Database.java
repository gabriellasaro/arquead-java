package dev.lasaro.arquead;

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

    public Path createCollection(String name) {
        Path directoryCollection = this.path.resolve(name);
        if (Files.exists(directoryCollection)) {
            throw new ArqueadException("Já existe uma coleção com este nome.");
        }

        try {
            Files.createDirectory(directoryCollection);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ArqueadException("Erro ao criar diretório para coleção.");
        }
        return directoryCollection;
    }

    public void createDatabase() throws IOException {
        if (Files.exists(this.path)) {
            throw new ArqueadException("Já existe um diretório com este nome.");
        }

        try {
            Files.createDirectory(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ArqueadException("Não foi possível criar este diretório.");
        }

        Path newCollection = this.createCollection("_arquea");
        Collection collection = new Collection(newCollection);

        HashMap<String, Object> conf = new HashMap<>();
        conf.put("id", "conf");
        conf.put("version", Version.getVersion());
        collection.insert(conf);
    }
}
