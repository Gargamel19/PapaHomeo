package trendelenburg.data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Data {

    public static HashMap<String, String> readFromFileAsHash(String fileName) {
        AtomicReference<HashMap<String, String>> hash = new AtomicReference<>(new HashMap<>());
        AtomicBoolean aborded = new AtomicBoolean(false);
        System.out.print("importing data");
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            if(!file.exists()) throw new FileNotFoundException();
            bufferedReader = new BufferedReader(new FileReader(fileName));

            bufferedReader.lines().forEach(line -> {
                if(line.length()>1){
                    String[] parts = line.split(",");
                    if(hash.get().containsKey(parts[0])){
                        hash.set(new HashMap<>());
                        System.out.println("");
                        System.err.println("der key \"" + parts[0] + "\" kommt mehrfach vor");
                        System.err.println("das impertieren wurde abgebrochen, checke bitte deinen Datensatz");
                        aborded.set(true);
                        return;
                    }
                    hash.get().put(parts[0], parts[1]);
                    System.out.print(".");

                }
            });
            if(aborded.get()){
                return hash.get();
            }
            System.out.println("");
            System.out.println("inported " + hash.get().size() + " data");
            return hash.get();
        } catch (FileNotFoundException e) {
            System.out.println("Datei \"" + fileName + "\" konnte nicht gefunden werden");
            return null;
        }
    }

    public static ArrayList<Karte> readFromFileAsList(String fileName) {
        AtomicReference<ArrayList<Karte>> karten = new AtomicReference<>(new ArrayList<>());
        AtomicBoolean aborded = new AtomicBoolean(false);
        System.out.print("importing data");
        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileName);
            if(!file.exists()) throw new FileNotFoundException();
            bufferedReader = new BufferedReader(new FileReader(fileName));

            bufferedReader.lines().forEach(line -> {
                if(line.length()>1){
                    String[] parts = line.split(",");
                    if(karten.get().contains(new Karte(parts[0], ""))){
                        karten.set(new ArrayList<>());
                        System.out.println("");
                        System.err.println("der key \"" + parts[0] + "\" kommt mehrfach vor");
                        System.err.println("das impertieren wurde abgebrochen, checke bitte deinen Datensatz");
                        aborded.set(true);
                        return;
                    }
                    karten.get().add(new Karte(parts[0], parts[1]));
                    System.out.print(".");

                }
            });
            if(aborded.get()){
                return karten.get();
            }
            System.out.println("");
            System.out.println("inported " + " data");
            return karten.get();
        } catch (FileNotFoundException e) {
            System.out.println("Datei \"" + fileName + "\" konnte nicht gefunden werden");
            return karten.get();
        }
    }

}
