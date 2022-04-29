package centrivaccinali;

/**
 * <p>La classe Path e' la classe che si occupa della gestione dei percorsi di salvataggio dei File "CentriVaccinali.dati" e "NomeCentroVaccinale.dati",
 *  in questa classe vengono stabiliti i metodi per determinare il percorso più appropriato, determinando il sistema operativo in uso e le differenze nei separatori. </p>
 *
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 */

public class Path {

    /**
     * Metodo per determinare il percorso del file "CentriVaccinali.dati"
     * @return il percorso del file "CentriVaccinali.dati"
     */
    protected static String getPathCentriVaccinali() {

        String path;
        String nomeFile = "CentriVaccinali.dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            //Sistema Operativo Windows
            path = cartella + "\\" + "data" + "\\" + "Dati Centri Vaccinali" + "\\" + nomeFile;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Sistema Operativo Mac / Unix
            path = cartella + "/" + "data" + "/" + "Dati Centri Vaccinali" +"/" + nomeFile ;
        } else {
            //Sistema Operativo Sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Centri Vaccinali" +"/" + nomeFile;
        }
        return path;
    }

    /**
     * Metodo per determinare il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     * riguardante uno specifico centro vaccinale, dove NomeCentroVaccinale viene sostituito dinamicamente
     * @param FN Vaccinato del centro vaccinale in esame
     * @return il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     */
    protected static String getPathNomeCentroVaccinale(Vaccinato FN) {
        String path;
        String pathNome = FN.nomecentrovaccinale;
        String nomeFile = "Vaccinati_" + pathNome + ".dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            //Sistema Operativo Windows
            path = cartella + "\\" + "data" + "\\" + "Dati Vaccinati per ogni Centro Vaccinale" + "\\" +  nomeFile;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Sistema Operativo Mac / Unix
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        } else {
            //Sistema Operativo Sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        }
        return path;
    }
    /**
     * Metodo per determinare il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     * riguardante uno specifico centro vaccinale, dove NomeCentroVaccinale viene sostituito dinamicamente
     * @param cc centro vaccinale preso in considerazione
     * @return il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     */
    protected static String getPathNomeCentroVaccinale(CentriVaccinali cc) {
        String path;
        String pathNome = cc.getNomeCentroVaccinale();
        String nomeFile = "Vaccinati_" + pathNome + ".dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            //Sistema Operativo Windows
            path = cartella + "\\" + "data" + "\\" + "Dati Vaccinati per ogni Centro Vaccinale" + "\\" +  nomeFile;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Sistema Operativo Mac / Unix
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        } else {
            //Sistema Operativo Sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        }
        return path;
    }
    /**
     * Metodo per determinare il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     * riguardante uno specifico centro vaccinale, dove NomeCentroVaccinale viene sostituito dinamicamente
     * @param pathNome stringa che identifica il nome del centro vaccinale
     * @return il percorso del file "Vaccinati_NomeCentroVaccinale.dati"
     */
    protected static String getPathNomeCentroVaccinale(String pathNome) {
        String path;
        String nomeFile = "Vaccinati_" + pathNome + ".dati.txt";
        String cartella = System.getProperty("user.dir");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            //Sistema Operativo Windows
            path = cartella + "\\" + "data" + "\\" + "Dati Vaccinati per ogni Centro Vaccinale" + "\\" +  nomeFile;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Sistema Operativo Mac/Unix
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        } else {
            //Sistema Operativo Sconosciuto
            path = cartella + "/" + "data" + "/" + "Dati Vaccinati per ogni Centro Vaccinale" + "/" +  nomeFile;
        }
        return path;
    }
}