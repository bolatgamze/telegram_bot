package traumurlaub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vorschlag {
    private Map<String, List<String>> vorschlaege = new HashMap<>();

    @Override
    public String toString() {
        return "Vorschlag{" +
                "vorschlaege=" + vorschlaege +
                '}';
    }

    public Vorschlag() {
        initVorschlaege();
    }

    private void initVorschlaege() {
        //Strand
        vorschlaege.put("strand|ruhig|günstig", List.of(
                "Pula (HR)",
                "Cadaqués (ES)",
                "Ksamil (AL)"
        ));
        vorschlaege.put("strand|ruhig|luxuriös", List.of(
                "Cefalù (IT)",
                "Formentera (ES)",
                "Cassis (FR)"
        ));
        vorschlaege.put("strand|lebendig|günstig", List.of(
                "Makarska (HR)",
                "Gili Trawangan (ID)",
                "Ölüdeniz (TR)"
        ));
        vorschlaege.put("strand|lebendig|luxuriös", List.of(
                "Dubrovnik (HR)",
                "Ibiza (ES)",
                "Mykonos (GR)"
        ));

        //Schnee
        vorschlaege.put("schnee|ruhig|günstig", List.of(
                "Hochzeiger (Ötztal) (AT)",
                "Feldberg (DE)",
                "La Plagne (FR)"
        ));
        vorschlaege.put("schnee|ruhig|luxuriös", List.of(
                "St. Moritz (CH)",
                "Lech Zürs (AT)",
                "Verbier (CH)"
        ));
        vorschlaege.put("schnee|lebendig|günstig", List.of(
                "Zell am See (AT)",
                "Courchevel (FR)",
                "Saalbach (AT)"
        ));
        vorschlaege.put("schnee|lebendig|luxuriös", List.of(
                "Aspen (US)",
                "Whistler (CA)",
                "Kitzbühel (AT)"
        ));

        //Wandern
        vorschlaege.put("wandern|ruhig|günstig", List.of(
                "Rheinburgenweg (DE)",
                "Kloof Corner (ZA)",
                "Stoss (CH)"
        ));
        vorschlaege.put("wandern|ruhig|luxuriös", List.of(
                "Augstmatthorn (CH)",
                "Lago di Sorapis (IT)",
                "Obersee, Berchtesgaden (DE)"
        ));
        vorschlaege.put("wandern|lebendig|günstig", List.of(
                "Pico Ruivo, Madeira (PT)",
                "Bleder See (SI)",
                "Olpererhütte (AT)"
        ));
        vorschlaege.put("wandern|lebendig|luxuriös", List.of(
                "Mont Blanc – Chamonix (FR)",
                "Moraine Lake (CA)",
                "Zermatt – Fünf-Seen-Wanderung (CH)"
        ));

        //Stadt
        vorschlaege.put("stadt|ruhig|günstig", List.of(
                "Rovinj (HR)",
                "Belcastel (FR)",
                "Leiden (NL)"
        ));
        vorschlaege.put("stadt|ruhig|luxuriös", List.of(
                "Montreux (CH)",
                "Sintra (PT)",
                "Saignon (FR)"
        ));
        vorschlaege.put("stadt|lebendig|günstig", List.of(
                "Brügge (BE)",
                "Lissabon (PT)",
                "Heidelberg (DE)"
        ));
        vorschlaege.put("stadt|lebendig|luxuriös", List.of(
                "Bergen (NO)",
                "Capri (IT)",
                "Valencia (ES)"
        ));

        //Abenteuerlich
        vorschlaege.put("abenteuerlich|ruhig|günstig", List.of(
                "Chur (Bernina Express) (CH)",
                "Lieser (Übernachtung im Schloss) (DE)",
                "Leuchtturm Lindesnes (NO)"
        ));
        vorschlaege.put("abenteuerlich|ruhig|luxuriös", List.of(
                "Tromsø (Aurora Borealis Observatory) (NO)",
                "Bali (Helikopterflug) (ID)",
                "Glengorm Castle (Übernachtung im Schloss), Isle of Mull (GB)"
        ));
        vorschlaege.put("abenteuerlich|lebendig|günstig", List.of(
                "Johannesburg (Löwenpark) (ZA)",
                "Port Lincoln (Haikäfigtauchen) (AU)",
                "Boulders Beach (Kapstadt) (ZA)"
        ));
        vorschlaege.put("abenteuerlich|lebendig|luxuriös", List.of(
                "Nairobi (Luxus-Giraffen-Lodge) (KE)",
                "Manhattan (Helikopterflug) (US)",
                "Queenstown (Jetbootfahren) (NZ)"
        ));

    }
    public List<String> getVorschlaege(String key) {
        return vorschlaege.get(key);
    }
}
