package dev.lasaro.arquead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public final class Arquead {
    final private Path path;

    public Arquead(String path) {
        this.path = Path.of(path);
    }

    public Arquead(Path path) {
        this.path = path;
    }

    /**
     * Returns true if the path refers to an Arquead directory.
     *
     * @return the resulting {@code boolean}
     */
    public static boolean valid(String path) {
        return valid(Path.of(path));
    }

    /**
     * Returns true if the path refers to an Arquead directory.
     *
     * @return the resulting {@code boolean}
     */
    public static boolean valid(Path path) {
        return Files.isDirectory(path);
    }

    /**
     * Prepare a new directory.
     *
     * @param path
     * @throws IOException
     */
    public static void create(Path path) throws IOException {
        if (Files.exists(path)) {
            throw new ArqueadException("Já existe um diretório com este nome.");
        }

        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ArqueadException("Não foi possível criar este diretório.");
        }

        Arquead arquead = new Arquead(path);
        Path newCollection = arquead.createCollection("_arquea");
        Collection collection = new Collection(newCollection);

        HashMap<String, Object> conf = new HashMap<>();
        conf.put("id", "conf");
        conf.put("version", Version.getVersion());
        collection.insert(conf);
    }

    /**
     * Prepare a new directory.
     *
     * @param path
     * @throws IOException
     */
    public static void create(String path) throws IOException {
        create(Path.of(path));
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
}
