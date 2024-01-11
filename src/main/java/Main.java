import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("ultimatetest.cfg");
        try {
            Texttexture texttexture = new Texttexture(file);
            System.out.println(texttexture.getTexttextureIndexesList());
            System.out.println(texttexture.getUsetexttextureIndexes());
            System.out.println(texttexture.replaceTexttexture());
            System.out.println(texttexture.replaceUsetexttexture());
            texttexture.makeCfg();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
