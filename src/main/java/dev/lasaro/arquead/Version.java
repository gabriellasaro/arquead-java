package dev.lasaro.arquead;

public final class Version {
    private static final String version = "0.3.0";
    private static final String releaseDate = "2020-09-01";
    private static final String[] compatible = {"0.3.0"};

    public String toString() {
        return version;
    }

    public static String getReleaseDate() {
        return releaseDate;
    }

    public static String getVersion() {
        return version;
    }

    public static String[] getCompatible() {
        return compatible;
    }
}
