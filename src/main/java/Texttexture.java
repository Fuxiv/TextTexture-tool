import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;


public class Texttexture {
    private File fileTT;
    private File fileUT;
    private ArrayList<Integer> texttextureIndexes = new ArrayList<>();
    private ArrayList<Integer> usetexttextureIndexes = new ArrayList<>();

    public ArrayList<Integer> getUsetexttextureIndexes() {
        return usetexttextureIndexes;
    }

    public Texttexture(File fileTT, File fileUT) throws IOException {
        this.fileTT = fileTT;
        this.fileUT = fileUT;
        getTexttextureIndexes();
        getUsetexttexutreIndexes();
    }

    public Texttexture() {
    }

    public ArrayList<Integer> getTexttextureIndexesList() {
        return texttextureIndexes;
    }

    private void getTexttextureIndexes() throws IOException {
        String content = Files.readString(this.fileTT.toPath(), StandardCharsets.UTF_8);
        String[] indexes = content.split("\\[texttexture");
        for (int i = 0; i < indexes.length - 1; i++) {
            int length = indexes[i].length();
            try {
                this.texttextureIndexes.add(Integer.parseInt(indexes[i].substring(length - 4, length - 2)));
            } catch (Exception e) {
                this.texttextureIndexes.add(Integer.parseInt(indexes[i].substring(length - 3, length - 2)));
            }
        }
    }

    private void getUsetexttexutreIndexes() throws IOException {
        String content = Files.readString(this.fileUT.toPath(), StandardCharsets.UTF_8);
        String[] indexes = content.split("useTextTexture]");
        for (int i = 1; i < indexes.length; i++) {
            try {
                this.usetexttextureIndexes.add(Integer.parseInt(indexes[i].substring(2, 4)));
            } catch (Exception e) {
                this.usetexttextureIndexes.add(Integer.parseInt(indexes[i].substring(2, 3)));
            }
        }
    }


}
