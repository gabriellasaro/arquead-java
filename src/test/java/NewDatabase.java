import dev.lasaro.arquead.Arquead;

import java.io.IOException;

public class NewDatabase {
    public static void main(String[] args) {
        try {
            Arquead.create("/home/user/arquead-test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
