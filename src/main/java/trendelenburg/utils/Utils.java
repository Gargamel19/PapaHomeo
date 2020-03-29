package trendelenburg.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Utils {

    public static String dataFile = "";
    public static String trennmittel = "";
    public static String passwort = "";
    public static Mode mode = null;

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

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



}
