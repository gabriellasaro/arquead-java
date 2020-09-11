package dev.lasaro;

public class Info {
    private Version version = new Version();
    private String name = "ArqueaD";
    private String repository = "https://github.com/gabriellasaro/arquead-java";
    private String website = "https://arquead.lasaro.dev";

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
        return String.format("%s v%s %s", name, version.getVersion(), version.getReleaseDate());
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
        System.out.println("Lista de banco de dados compatíveis com a versão corrente (" + this.version.getVersion() + "):");
        for (String version : this.version.getCompatible()) {
            System.out.println("\t" + version);
        }
    }
}
