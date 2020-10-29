package dev.lasaro.arquead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Stream;

public final class Collection {
    private final Path path;

    public Collection(Path path) {
        this.path = path;
    }

    public Path getName() {
        return this.path.getFileName();
    }

    public Stream<Path> getDocuments() {
        try {
            return Files.list(this.path);
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    public ObjectId insert(HashMap<String, Object> data) throws IOException {
        ObjectId objectId;
        if (!data.containsKey("id")) {
            objectId = new ObjectId(UUID.randomUUID().toString());
        } else {
            objectId = new ObjectId(data.get("id").toString().replaceAll(" ", "_"));
        }
        data.put("id", objectId.getId());

        Path documentDirectory = this.path.resolve(objectId.getId());

        if (Files.exists(documentDirectory)) {
            throw new ArqueadException("Já existe um documento com este ID.");
        }

       Files.createDirectory(documentDirectory);

        try {
            Util.saveFileToJson(documentDirectory.resolve("1.json").toString(), data);
        } catch (IOException e) {
            Util.deleteDirectory(documentDirectory);
            throw new ArqueadException("Erro ao criar arquivo!");
        }

        return objectId;
    }
}
