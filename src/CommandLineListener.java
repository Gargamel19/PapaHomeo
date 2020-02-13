import trendelenburg.UI.Window;
import trendelenburg.data.Data;
import trendelenburg.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineListener {

    static HashMap<String, String> data;


    public static void main(String[] args) {

        Utils.readPropertys();

        if (Utils.guimode){

            Window window = new Window();

        }else {

            if (null!=(data = Data.readFromFileAsHash(Utils.dataFile))) {

                Scanner scanner = new Scanner(System.in);

                showAllData(scanner);

                while (true) {
                    System.out.println("stelle deine Frage");
                    String input = scanner.nextLine();
                    if (data.containsKey(input)) {
                        System.out.println("Antwort bitte: ");

                        String answer = scanner.nextLine();
                        if (answer.equals(data.get(input))) {
                            System.out.println("Richtig");
                        } else {
                            System.out.println("leider falsch");
                            richtigeAntwort(scanner, input);
                        }
                    }
                }

            }


        }

    }



    public static void richtigeAntwort(Scanner scanner, String input){
        System.out.println("willst du die richtige Antwort sehen? (Y/N)");
        String right = scanner.nextLine();
        if(right.equals("Y")||right.equals("y")){
            System.out.println("richtige Antwort: " + data.get(input));
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
            for(Map.Entry<String, String> entry : data.entrySet()) {
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
