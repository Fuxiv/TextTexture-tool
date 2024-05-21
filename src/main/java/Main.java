import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Program Files (x86)\\Steam\\steamapps\\common\\OMSI 2\\Vehicles\\Urbino IV Pack_Iwonew\\Model\\FL_Urbino_12_Cummins_Continental_PL_3D.cfg");
        System.out.println(file.getPath().substring(0, file.getPath().length() - 4));
        try {
            Texttexture texttexture = new Texttexture(file);
            System.out.println(texttexture.getUsetexttextureIndexes());
//            System.out.println(texttexture.getTexttextureIndexesList());
//            System.out.println(texttexture.getUsetexttextureIndexes());
//            System.out.println(texttexture.replaceTexttexture());
//            System.out.println(texttexture.replaceUsetexttexture());
            texttexture.makeCfg();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
