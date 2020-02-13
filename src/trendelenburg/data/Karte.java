package trendelenburg.data;

import java.util.Objects;

public class Karte {

    String frage = "";
    String antwort = "";

    public Karte(String frage, String antwort) {
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
}
