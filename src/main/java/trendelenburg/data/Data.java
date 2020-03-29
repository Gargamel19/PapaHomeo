package trendelenburg.data;

import trendelenburg.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
                    String[] parts = line.split(Utils.trennmittel);
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
            AtomicInteger i = new AtomicInteger();
            bufferedReader.lines().forEach(line -> {
                if(line.length()>1){
                    String[] parts = line.split(Utils.trennmittel);
                    if(karten.get().contains(new Karte(0, parts[0], ""))){
                        karten.set(new ArrayList<>());
                        System.out.println("");
                        System.err.println("der key \"" + parts[0] + "\" kommt mehrfach vor");
                        System.err.println("das impertieren wurde abgebrochen, checke bitte deinen Datensatz");
                        aborded.set(true);
                        return;
                    }
                    karten.get().add(new Karte(i.get(), parts[0], parts[1]));
                    i.getAndIncrement();
                    if(i.get()>Utils.id){
                        Utils.id = i.get();
                    }
                    System.out.print(".");

                }
            });
            if(aborded.get()){
                return karten.get();
            }
            System.out.println("");
            System.out.println("inported " + " data");
            Utils.saveID();
            System.out.println("next id: " + (Utils.id+1));
            return karten.get();
        } catch (FileNotFoundException e) {
            System.out.println("Datei \"" + fileName + "\" konnte nicht gefunden werden");
            return karten.get();
        }
    }

    public static void removeKarte(List<Karte> list, int id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId()==id){
                list.remove(i);
                break;
            }
        }
        save(list);

    }

    public static void addKarte(List<Karte> list, Karte karte){
        list.add(karte);
        save(list);

    }

    public static void save(List<Karte> list){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i).getFrage());
            output.append(Utils.trennmittel);
            output.append(list.get(i).getAntwort());
            output.append("\n");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Utils.dataFile)));
            bw.write(output.toString());
            bw.flush();
            bw.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void editKarte(List<Karte> karten, int id, String frage, String antwort) {
        for (int i = 0; i < karten.size(); i++) {
            if(karten.get(i).getId()==id){
                karten.set(i, new Karte(id, frage, antwort));
                break;
            }
        }
        save(karten);
    }
}
