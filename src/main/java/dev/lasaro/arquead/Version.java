package dev.lasaro.arquead;

public final class Version {
    private static final String version = "0.1.0";
    private static final String releaseDate = "2020-10-29";
    private static final String[] compatible = {"0.1.0"};

    @Override
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
