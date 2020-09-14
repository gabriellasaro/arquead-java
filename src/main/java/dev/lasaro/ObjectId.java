package dev.lasaro;

public final class ObjectId {
    private final String id;
    private final int version;

    public ObjectId(String id) {
        this.id = id;
        this.version = 1;
    }

    public ObjectId(String id, int version) {
        this.id = id;
        this.version = version;
    }

    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
