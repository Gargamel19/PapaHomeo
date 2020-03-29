package trendelenburg.data;

import com.google.gson.JsonObject;

import java.util.Objects;

public class Karte {
    int id;
    String frage = "";
    String antwort = "";

    public Karte(int i, String frage, String antwort) {
        this.id = i;
        this.frage = frage;
        this.antwort = antwort;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karte karte = (Karte) o;
        return Objects.equals(frage, karte.frage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frage);
    }

    public JsonObject toJson(){
        JsonObject jo = new JsonObject();
        jo.addProperty("id", id);
        jo.addProperty("frage", frage);
        jo.addProperty("antwort", antwort);
        return jo;
    }
}
