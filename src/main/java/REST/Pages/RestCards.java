package REST.Pages;

import REST.Application;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trendelenburg.data.Data;
import trendelenburg.data.Karte;
import trendelenburg.utils.Utils;

@RestController
@RequestMapping("/cards")
public class RestCards {



    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> findAll() {
        JsonArray ja = new JsonArray();
        for (int i = 0; i < Application.karten.size(); i++) {
            ja.add(Application.karten.get(i).toJson());
        }
        return new ResponseEntity<>(ja.toString(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(value = "/pre", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getCardByTerm(@RequestParam String search) {
        JsonArray ja = new JsonArray();
        for (int i = 0; i < Application.karten.size(); i++) {
            if(Application.karten.get(i).getFrage().toLowerCase().contains(search.toLowerCase())){
                ja.add(Application.karten.get(i).toJson());
            }
        }
        return new ResponseEntity<>(ja.toString(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getCardByID(@PathVariable int id) {
        for (int i = 0; i < Application.karten.size(); i++) {
            if(Application.karten.get(i).getId()==id){
                return new ResponseEntity<>(Application.karten.get(i).toJson().toString(), HttpStatus.resolve(200));
            }
        }
        return new ResponseEntity<>(HttpStatus.resolve(400));
    }

    @CrossOrigin
    @RequestMapping(value = "check/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getCheckAnswer(@PathVariable int id, @RequestParam String antwort) {
        JsonObject jo = checkIfRight(id, antwort);

        return new ResponseEntity<>(jo.toString(), HttpStatus.resolve(200));
    }

    @CrossOrigin
    @RequestMapping(value = "add", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getCheckAnswer(@RequestParam String frage, @RequestParam String antwort, @RequestParam String passwort) {
        if(passwort.equals(Utils.passwort)){
            Utils.id++;
            Data.addKarte(Application.karten, new Karte(Utils.id, frage, antwort));
            JsonObject jo = new JsonObject();
            jo.addProperty("status", "okay");
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getRemoveCard(@RequestParam int id, @RequestParam String passwort) {
        if(passwort.equals(Utils.passwort)){
            Data.removeKarte(Application.karten, id);
            JsonObject jo = new JsonObject();
            jo.addProperty("status", "okay");
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> editCard(@RequestParam int id, @RequestParam String frage, @RequestParam String antwort, @RequestParam String passwort) {
        if(passwort.equals(Utils.passwort)){
            Data.editKarte(Application.karten, id, frage, antwort);
            JsonObject jo = new JsonObject();
            jo.addProperty("status", "okay");
            return new ResponseEntity<>(jo.toString(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


    private JsonObject checkIfRight(int id, String antwort){
        JsonObject jo = new JsonObject();
        String[] input = antwort.replace(" ", "").split(",");
        for (int i = 0; i < Application.karten.size(); i++) {
            if(Application.karten.get(i).getId()==id) {
                for (int j = 0; j < input.length; j++) {
                    if(Application.karten.get(i).getAntwort().toLowerCase().equals(input[j].toLowerCase())) {
                        if(input.length>1) {

                            jo.addProperty("antwort", "part");
                        }else {
                            jo.addProperty("antwort", "ok");
                        }
                        break;
                    }else{
                        jo.addProperty("antwort", "no");
                    }
                }
            }
        }
        return jo;
    }

}
