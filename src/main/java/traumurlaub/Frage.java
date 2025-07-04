package traumurlaub;

public class Frage {

    private final String frage1;
    private final String frage2;
    private final String frage3;

    public Frage() {
        this.frage1 = "🌍 Welches Bild weckt deine Reiselust am meisten?";
        this.frage2 = "🌿 Möchtest du lieber Ruhe oder Action im Urlaub?";
        this.frage3 = "💰 Soll der Urlaub günstig oder luxuriös sein?";
    }

    public String getFrage1() {
        return frage1;
    }

    public String getFrage2() {
        return frage2;
    }

    public String getFrage3() {
        return frage3;
    }
}
