package cittadini;

import centrivaccinali.Vaccinato;

/**
 * La classe PathCittadini e' la classe che si occupa della gestione dei percorsi di salvataggio del File "Cittadini_Registratii.dati" e "Vaccinati_NomeCentroVaccinale.dati.txt.
 * In questa classe vengono stabiliti i metodi per determinare il percorso più appropriato, determinando il sistema operativo in uso e le differenze nei separatori.
 *
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 */

public class PathCittadini {

    /**
     * Metodo per determinare il percorso del file "Cittadini_Registrati.dati.txt"
     * @return il percorso del file "Cittadini_Registrati.dati.txt"
     */
    protected static String getPathCittadini() {
        //Dichiarazione variabili
        String path;
        String nomeFile = "Cittadini_Registrati.dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();
        //  Sistema operativo windows
        if (os.contains("win")) {
            path = cartella + "\\" + "data" + "\\" + "Dati Cittadini" + "\\" + nomeFile;
        } else if (os.contains("nix") || os.contains("linux") || os.contains("mac")) {
            //Sistema operativo mac/unix
            path = cartella + "/" + "data" + "/" + "Dati Cittadini" +"/" + nomeFile ;
        } else {
            //Sistema operativo sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Cittadini" +"/" + nomeFile;
        }
        return path;


    }

    /**
     * Metodo per determinare il percorso del file "Vaccinati_NomeCentroVaccinale.dati.txt" dove NomeCentroVaccinale viene sostituito dinamicamente dal nome del centro in cui si
     * e' vaccinato il cittadino
     * @param FN Vaccinato
     * @return il percorso del file "Vaccinati_NomeCentroVaccinale.dati.txt"
     */
    protected static String getPathNomeCentroVaccinale(Vaccinato FN) {
        String path;
        String pathNome = FN.nomecentrovaccinale;
        String nomeFile = "Vaccinati_" + pathNome + ".dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            //Sistema operativo windows
            path = cartella + "\\" + "data" + "\\" + "Dati Vaccinati per ogni Centro Vaccinale" + "\\" +  nomeFile;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Sistema operativo unix o mac
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        } else {
            //Sistema operativo sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        }
        return path;
    }
}