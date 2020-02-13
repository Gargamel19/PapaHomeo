package trendelenburg.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String dataFile = "";
    public static String trennmittel = "";
    public static boolean guimode = true;

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
            guimode = Boolean.parseBoolean(prop.getProperty("guimode"));
            System.out.println("guimode: " + guimode);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
