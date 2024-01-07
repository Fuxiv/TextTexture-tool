import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File fileTT = new File("ultimatetest.cfg");
        File fileUT = new File("ultimatetest.cfg");
        List<Integer> list = new ArrayList<>();
        try {
            Texttexture texttexture = new Texttexture(fileTT, fileUT);
            System.out.println(texttexture.getTexttextureIndexesList());
            list = texttexture.getUsetexttextureIndexes();
//            Collections.sort(list);
            System.out.println(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
