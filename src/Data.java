import java.io.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Data {

    public HashMap<String, String> data;

    public Data() {
        data = new HashMap<>();
    }

    public boolean readFromFile(String fileName) {
        AtomicBoolean aborded = new AtomicBoolean(false);
        System.out.print("importing data");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));

            bufferedReader.lines().forEach(line -> {
                if(line.length()>1){
                    String[] parts = line.split(";");
                    if(data.containsKey(parts[0])){
                        data = new HashMap<>();
                        System.out.println("");
                        System.err.println("der key \"" + parts[0] + "\" kommt mehrfach vor");
                        System.err.println("das impertieren wurde abgebrochen, checke bitte deinen Datensatz");
                        aborded.set(true);
                        return;
                    }
                    data.put(parts[0], parts[1]);
                    System.out.print(".");
                }
            });
            if(aborded.get()){
                return false;
            }
            System.out.println("");
            System.out.println("inported " + data.size() + " data");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Datei \"" + fileName + "\" konnte nicht gefunden werden");
            return false;
        }
    }

}
