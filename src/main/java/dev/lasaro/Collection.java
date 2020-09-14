package dev.lasaro;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Path path = Paths.get(documentDirectory.resolve("1.json").toString());
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            Gson gson = new Gson();
            writer.write(gson.toJson(data));
        } catch(IOException ex){
            ex.printStackTrace();
            Util.deleteDirectory(documentDirectory);
            return new Result<>("Erro ao criar arquivo!");
        }

        return new Result<>(objectId);
    }
}
