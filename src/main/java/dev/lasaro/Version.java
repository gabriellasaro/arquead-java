package dev.lasaro;

public class Version {
    private String version = "0.3.0";
    private String releaseDate = "2020-09-01";
    private String[] compatible = {"0.3.0"};

    public String toString() {
        return version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public String[] getCompatible() {
        return compatible;
    }
}
