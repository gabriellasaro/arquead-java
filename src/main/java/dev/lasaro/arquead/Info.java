package dev.lasaro.arquead;

public final class Info {
    private final String name = "ArqueaD";
    private final String repository = "https://github.com/gabriellasaro/arquead-java";
    private final String website = "https://arquead.lasaro.dev";

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getRepository() {
        return repository;
    }

    public String getRelease() {
        return String.format("%s v%s %s", name, Version.getVersion(), Version.getReleaseDate());
    }

    public void help() {
        System.out.println("Você pode obter ajuda no repositório oficial: " + repository);
        System.out.println("Ou acessando o nosso website: " + website);
        System.out.println("Lista de comandos:");
        System.out.println("\tcreate");
        System.out.println("\tcompatible");
        System.out.println("\tversion");
        System.out.println("\thelp");
    }

    public void showCompatible() {
        System.out.println("Lista de banco de dados compatíveis com a versão corrente (" + Version.getVersion() + "):");
        for (String version : Version.getCompatible()) {
            System.out.println("\t" + version);
        }
    }
}
