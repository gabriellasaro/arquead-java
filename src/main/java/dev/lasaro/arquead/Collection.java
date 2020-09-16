package dev.lasaro.arquead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Stream;

public class Collection {
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

    public Result<ObjectId> insert(HashMap<String, Object> data) {
        ObjectId objectId;
        if (!data.containsKey("id")) {
            objectId = new ObjectId(UUID.randomUUID().toString());
        } else {
            objectId = new ObjectId(data.get("id").toString().replaceAll(" ", "_"));
        }
        data.put("id", objectId.getId());

        Path documentDirectory = this.path.resolve(objectId.getId());

        if (Files.exists(documentDirectory)) {
            return new Result<>("Já existe um documento com este ID.");
        }

        try {
            Files.createDirectory(documentDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>("Não foi possível criar este diretório.");
        }

        try {
            Util.saveFileToJson(documentDirectory.resolve("1.json").toString(), data);
        } catch (IOException e) {
            Util.deleteDirectory(documentDirectory);
            return new Result<>("Erro ao criar arquivo!");
        }

        return new Result<>(objectId);
    }
}
