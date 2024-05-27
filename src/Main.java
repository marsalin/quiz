import java.util.*;

public class Main {
    // statische Variablen für "Punkte", "Name" und "Weiter"
    static int punkte;
    static String name;
    static String weiter;
    static Scanner scan = new Scanner(System.in);
    // statische Array-List für den Highscore
    static ArrayList<String> highscoreliste = new ArrayList<>();

    public static void main(String[] args) {
        hauptmenue();
    }

    /* Methode für das Hauptmenü
        - Hauptmenü in einer Do-While Schleife
        - mit verschachteltem Switch-Case zur Auswahl
     */
    public static void hauptmenue() {

        boolean check = false;

        do{
            System.out.println("----------------------------------");
            System.out.println("Hauptmenü - Quiz \tAlina Marschke");
            System.out.println("----------------------------------");
            System.out.println("[S] Start des Quiz");
            System.out.println("[H] Highscore anzeigen");
            System.out.println("[Q] Quiz beenden");
            System.out.println("----------------------------------");
            System.out.println("Bitte wählen Sie aus:");

            String eingabe = scan.next();

            switch (eingabe) {
                case "S", "s" -> {
                    System.out.println("---------------------------------");
                    System.out.println("Das Quiz beginnt!");
                    System.out.println("---------------------------------");
                    quizstarten();
                    check = true;
                }
                case "H", "h" -> {
                    System.out.println("---------------------------------");
                    System.out.println("Highscoreliste");
                    System.out.println("---------------------------------");
                    highscoreAnzeigen();
                    System.out.println("Wollen Sie zurück ins Hauptmenü?");
                    weiter = scan.next();
                    if (weiter.equalsIgnoreCase("ja")){
                        hauptmenue();
                    }
                    check = true;
                }
                case "Q", "q" -> {
                    System.out.println("Tschüssi!" + "\n" + "Das Quiz wird beendet.");
                    check = true;
                }
                default -> System.err.println("Denk nochmal drüber nach.");
            }
        } while (!check);
        scan.close();
    }
    /*
        - Fragen, Antworten und richtige Antwort in ein Array
        - diese werden in einer Do-While Schleife in eine Liste umgewandelt und geshuffelt
        - for Schleifen zur Ausgabe
        - ifelse um richtige Auswahl rauszufinden
        - Namenseingabe
     */
    public static void quizstarten() {
        String[][] fragen = {
                {"Welche der folgenden Auswahlmöglichkeiten ist keine Schleife?", "A) FOR", "B) DO WHILE", "C) IF", "D) WHILE", "c"},
                {"Was kann man nicht dem Compiler zuordnen?", "A) Übersetzt den gesamten Code vorab", "B) Stoppt beim ersten Fehler", "C) Listet alle Fehler vor Ausführung aus", "D) Erzeugt schnellen, optimierten Maschinencode", "b"},
                {"Die FOR-Schleife ist...", "A) eine Zählschleife", "B) eine kopfgesteuerte Schleife", "C) eine bedingte Anweisung", "D) ein Datentyp", "a"},
                {"Was versteht man unter Deklarierung?", "A) weist Variablen oder Objekten Anfangswerte zu", "B) definiert Typ und Namen für Variablen oder Methoden", "C) ist die Ausführung des Codes", "D) ist die Prüfung auf Fehler", "b"},
                {"Welche Java-Klasse wird verwendet, um Benutzereingaben von der Konsole zu lesen?", "A) Scanner", "B) ArrayList", "C) Collections", "D) Switch-Case", "a"}
        };

        String antwort;
        Scanner scan = new Scanner(System.in);
        do{
            punkte = 0;
            List<String[]> fragenalsliste = Arrays.asList(fragen);
            Collections.shuffle(fragenalsliste);

            for(String[] frage : fragenalsliste) {
                System.out.println(frage[0]);

                for (int i = 1; i < 5; i++) {
                    System.out.println(i + ". " + frage[i]);
                }

                System.out.println("Deine Antwort:");
                antwort = scan.next();

                if (antwort.equalsIgnoreCase(frage[5])){
                    System.out.println("Richtige Antwort! \n Du erhälst 1 Punkt!");
                    punkte += 1;
                    if (punkte==5){
                        System.out.println("Das Quiz ist beendet! \n Du hast insgesamt " + punkte + " Punkte erspielt!");
                    }
                }
                else {
                    System.out.println("Falsche Antwort! \n Du erhälst 0 Punkte! \n Deine aktuelle Punktzahl beträgt: " + punkte);
                    break;
                }
            }
            System.out.println("Bitte gib deinen Namen ein.");
            name = scan.next();
            System.out.println("Quiz wiederholen? (ja/nein)");
            weiter = scan.next();
            highscore();
        }while(weiter.equalsIgnoreCase("ja"));


        if (!weiter.equalsIgnoreCase("ja")) {
            hauptmenue();
        }
    }
    /*
        - Anlegen des highscores
        - Eintragung in eine ArrayList
        - absteigend sortiert nach Punkten
    */
    public static void highscore() {
        highscoreliste.add(punkte + "\t" + name);
        highscoreliste.sort(Collections.reverseOrder());
    }
    /*
        - Ausgabe Highscore durch for-Schleife mit Platzierung
     */
    public static void highscoreAnzeigen(){
        for (int i = 0; i < highscoreliste.size(); i++) {
            System.out.println((i + 1) + ".  Platz: " + highscoreliste.get(i));
        }
    }
}
