package trendelenburg.utils;

import java.io.*;
import java.util.Properties;



public class Utils {

    public static String dataFile = "";
    public static String trennmittel = "";
    public static String passwort = "";
    public static Mode mode = null;
    public static int id = -1;

    public static void readPropertys() {
        try (InputStream input = new FileInputStream("data.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            trennmittel = prop.getProperty("trennmittel");
            System.out.println("trennmittel: " + trennmittel);
            dataFile = prop.getProperty("data");
            System.out.println("dataFile: " + dataFile);
            mode = Mode.valueOf(prop.getProperty("mode"));
            System.out.println("guimode: " + mode.name());
            passwort = prop.getProperty("passwort");
            System.out.println("passwort: " + passwort);
            if(prop.getProperty("id")!=null){
                id = Integer.valueOf(prop.getProperty("id"));
                System.out.println("id: " + id);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void saveID(){




        try (OutputStream output = new FileOutputStream("data.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.setProperty("trennmittel", trennmittel);
            prop.setProperty("data", dataFile);
            prop.setProperty("mode", mode.name());
            prop.setProperty("passwort", passwort);
            prop.setProperty("id", id + "");
            prop.store(output, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
