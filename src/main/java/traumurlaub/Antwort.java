package traumurlaub;

public class Antwort {
    private String kategorie; // "strand" oder "schnee" oder "wandern" oder "stadt" oder "abenteuerlich"
    private String stimmung; // "ruhig" oder "lebendig"
    private String budget;   // "günstig" oder "luxuriös"

    public Antwort() {
    }

    public Antwort(String kategorie, String stimmung, String budget) {
        this.kategorie = kategorie;
        this.stimmung = stimmung;
        this.budget = budget;
    }

    // Getter & Setter
    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getStimmung() {
        return stimmung;
    }

    public void setStimmung(String stimmung) {
        this.stimmung = stimmung;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

}
