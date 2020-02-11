import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class CommandLineListener {

    public static Data data = new Data();
    public static String dataFile = "";

    public static void main(String[] args) {

        readPropertys();

        if(data.readFromFile(dataFile)){

            Scanner scanner = new Scanner(System.in);

            showAllData(scanner);

            while (true){
                System.out.println("stelle deine Frage");
                String input = scanner.nextLine();
                if(data.data.containsKey(input)){
                    System.out.println("Antwort bitte: ");

                    String answer = scanner.nextLine();
                    if(answer.equals(data.data.get(input))){
                        System.out.println("Richtig");
                    }else{
                        System.out.println("leider falsch");
                        richtigeAntwort(scanner, input);
                    }
                }
            }

        }




    }

    private static void readPropertys() {
        try (InputStream input = new FileInputStream("data.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            dataFile = prop.getProperty("data");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void richtigeAntwort(Scanner scanner, String input){
        System.out.println("willst du die richtige Antwort sehen? (Y/N)");
        String right = scanner.nextLine();
        if(right.equals("Y")||right.equals("y")){
            System.out.println("richtige Antwort: " + data.data.get(input));
        }else if(right.equals("N")||right.equals("n")){
            System.out.println("okay, dann nicht");
        }else{
            richtigeAntwort(scanner, input);
        }
    }

    public static void showAllData(Scanner scanner){
        System.out.println("willst du alle importieren Daten sehen? (Y/N)");
        String right = scanner.nextLine();
        if(right.equals("Y")||right.equals("y")){
            for(Map.Entry<String, String> entry : data.data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ":" + value);
            }
        }else if(right.equals("N")||right.equals("n")){
            System.out.println("okay, dann nicht");
        }else{
            showAllData(scanner);
        }
    }



}
