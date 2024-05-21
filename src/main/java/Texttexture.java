import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;


public class Texttexture {
    private File file;
    private ArrayList<Integer> texttextureIndexes = new ArrayList<>();
    private ArrayList<Integer> usetexttextureIndexes = new ArrayList<>();
    private ArrayList<Integer> texttextureIndexesUpdated = new ArrayList<>();
    private ArrayList<Integer> usetexttextureIndexesUpdated = new ArrayList<>();
    private StringBuilder finalCfg = new StringBuilder();

    public Texttexture(File file) throws IOException {
        this.file = file;
        getTexttextureIndexes();
        getUsetexttexutreIndexes();
    }

    public Texttexture() {
    }

    public ArrayList<Integer> getUsetexttextureIndexes() {
        return usetexttextureIndexes;
    }

    public ArrayList<Integer> getTexttextureIndexesList() {
        return texttextureIndexes;
    }

    private void getTexttextureIndexes() throws IOException {
        String content = Files.readString(this.file.toPath(), StandardCharsets.UTF_8);
        String[] indexes = content.split("\\[texttexture");
        for (int i = 0; i < indexes.length - 1; i++) {
            int length = indexes[i].length();
            try {
                //TO JEST ROZPIERDOLONE
                //KAZDY CFG KURWA INACZEJ SE ROBI
                this.texttextureIndexes.add(Integer.parseInt(indexes[i].substring(length - 3, length - 1)));
            } catch (Exception e) {
                this.texttextureIndexes.add(Integer.parseInt(String.valueOf(indexes[i].charAt(indexes[i].length() - 3))));
            }
        }
        System.out.println("tt: " + texttextureIndexes);
    }

    private void getUsetexttexutreIndexes() throws IOException {
        String content = Files.readString(this.file.toPath(), StandardCharsets.UTF_8);
        String[] indexes = content.split("\\[useTextTexture]");
        for (int i = 1; i < indexes.length; i++) {
            try {
                try {
                    this.usetexttextureIndexes.add(Integer.parseInt(indexes[i].substring(1, 3)));
                    this.usetexttextureIndexesUpdated.add(Integer.parseInt(indexes[i].substring(1, 3)));
                } catch (Exception eee){
                        this.usetexttextureIndexes.add(Integer.parseInt(indexes[i].substring(2, 4)));
                        this.usetexttextureIndexesUpdated.add(Integer.parseInt(indexes[i].substring(2, 4)));
                }
            } catch (Exception e) {
                try{
                    this.usetexttextureIndexes.add(Integer.parseInt(String.valueOf(indexes[i].charAt(1))));
                    this.usetexttextureIndexesUpdated.add(Integer.parseInt(String.valueOf(indexes[i].charAt(1))));
                }catch (Exception ee){
                    this.usetexttextureIndexes.add(Integer.parseInt(String.valueOf(indexes[i].charAt(2))));
                    this.usetexttextureIndexesUpdated.add(Integer.parseInt(String.valueOf(indexes[i].charAt(2))));
                }
            }
        }

    }

    public ArrayList<Integer> replaceTexttexture() {
        for (int i = 0; i < this.texttextureIndexes.size(); i++) {
            texttextureIndexesUpdated.add(i);
        }
        System.out.println(texttextureIndexesUpdated);
        return texttextureIndexesUpdated;
    }

    public ArrayList<Integer> replaceUsetexttexture() {
        ArrayList<Integer> texttextureIndexesUpdated = replaceTexttexture();
        for (int i = 0; i < texttextureIndexes.size(); i++) {
            if (!Objects.equals(texttextureIndexes.get(i), texttextureIndexesUpdated.get(i))) {
                for (int j = 0; j < usetexttextureIndexesUpdated.size(); j++) {
                    if (Objects.equals(texttextureIndexes.get(i), usetexttextureIndexesUpdated.get(j))){
                        usetexttextureIndexesUpdated.set(j, texttextureIndexesUpdated.get(i));
                    }
                }
            }
        }
        return usetexttextureIndexesUpdated;
    }

    public void makeCfg() throws IOException {
        replaceTexttexture();
        replaceUsetexttexture();
        String content = Files.readString(this.file.toPath(), StandardCharsets.UTF_8);
        String[] texttextures = content.split("\\[texttexture");
        for (int i = 0; i < texttextures.length - 1; i++) {
            int length = texttextures[i].length();
            try {
                Integer.parseInt(texttextures[i].substring(length - 4, length - 2));
                finalCfg.append(texttextures[i].substring(0, length - 4) + "\n" + texttextureIndexesUpdated.get(i) + "\n[texttexture");
            } catch (Exception e) {
                finalCfg.append(texttextures[i].substring(0, length - 3) + "\n" + texttextureIndexesUpdated.get(i) + "\n[texttexture");
            }
        }
        String[] usetexttextures = texttextures[texttextures.length - 1].split("\\[useTextTexture]");
        finalCfg.append(usetexttextures[0]);
        for (int i = 0; i < usetexttextures.length - 1; i++) {
            System.out.print(usetexttextureIndexesUpdated.get(i) + ", ");
            try {
                Integer.parseInt(usetexttextures[i + 1].substring(2, 4));
                finalCfg.append("[useTextTexture]\n" + usetexttextureIndexesUpdated.get(i) + "\n" + usetexttextures[i + 1].substring(4));
            } catch (Exception e) {
                finalCfg.append("[useTextTexture]\n" + usetexttextureIndexesUpdated.get(i) + "\n" + usetexttextures[i + 1].substring(3));
            }
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath().substring(0, file.getPath().length() - 4) + "_.cfg"));
        bufferedWriter.write(String.valueOf(finalCfg));
        bufferedWriter.close();
//        System.out.println(finalCfg);
    }

    /*TODO:
     *  IMPORTANT: remove the things you want to change (for example: old ibis along with its texttexture entries)
     *    gradually change components inside the cfg file in order:
     *      - update existing texttextures, so they are in order
     *      - set new texttexture to new unit
     *      - append new mesh entries and texttexture entries to the cfg
     */
}
