package centrivaccinali;

/**
 * <p>La classe "CreazioneCornice" permette di creare una semplice grafica formata da stringhe di asterischi e spazi bianchi all'avvio dell'applicazione </p>
 *
 * @author Paolo Bruscagin
 */

public class CreazioneCornice {

    //Creazione Stringe per bordi superiori, inferiori e laterali

    /**
     * <code> rigaAsterischi</code> riga di asterischi generata per creare la cornice all'avvio del programma
     */
    public static String rigaAsteristichi = "********************"+ "********************"+ "********************"+ "********************";

    /**
     * <code> rigaBianca </code> riga di spazi utilizzata per la creazione della cornice all'avvio del programma
     */

    public static String rigaBianca =       "                    "+ "                    "+ "                    "+ "                    ";

    /**
     *
     * @param text oggetto di tipo Stringa che permette di inserire all'interno della cornice il titolo del progetto
     */

    public static void create(String text) {
        int lunghezza = text.length();

        //substring restituisce una parte della stringa formata da asterischi per la creazione della cornice
        String rigaCornice = rigaAsteristichi.substring(0,lunghezza + 36);
        String spazi = rigaBianca.substring(0, lunghezza + 34);
        String rigaInternmedia = "*" + spazi + "*";
        String rigaTesto = "*                " + text + "                  *";
        System.out.println(rigaCornice);
        System.out.println(rigaInternmedia);
        System.out.println(rigaTesto);
        System.out.println(rigaInternmedia);
        System.out.println(rigaCornice);
    }
}


