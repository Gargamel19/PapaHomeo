package REST;

import REST.Pages.RestCards;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import trendelenburg.data.Data;
import trendelenburg.data.Karte;
import trendelenburg.utils.Utils;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Application {

    public static List<Karte> karten;

    static int port = 8080;

    public static void main(String[] args) {
        startServer(args);
        karten = Data.readFromFileAsList(Utils.dataFile);
        RestCards restServer = new RestCards();


    }

    private static void startServer(String[] args){
        try{
            HashMap<String, Object> props = new HashMap<>();
            props.put("server.port", port);

            new SpringApplicationBuilder()
                    .sources(Application.class)
                    .properties(props)
                    .run(args);
        }catch (Exception e){
            port++;
            startServer(args);
        }
    }
}