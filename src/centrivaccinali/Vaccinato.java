package centrivaccinali;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * <p>La classe "Vaccinato" permette all'operatore vaccinale di registrare il cittadino che ha eseguito la vaccinazione.
 *
 * Consente la creazione di un oggetto di tipo Vaccinato, contente tutte le informazioni del cittadino vaccinato.
 *
 * permette di creare un oggetto Vaccinato e inserirlo nella specifica struttura dati leggendo le informazioni dal file "Vaccinati_NomeCentroVaccinale.dati.txt", dove "NomeCentrovaccinale" viene sostituito dinamicamente dal nome del centro vaccinale e di registrare a sistema le informazioni di un cittadino vaccinato.
 *
 * @see Vaccinato#registraVaccinato(Hashtable) Il metodo consente di registrare a sistema le informazioni di un cittadino vaccinato </p>
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 * @author Simone Torno
 */

public class Vaccinato{
    /**
     * <code> nomecentrovaccinale </code> nome del centro vaccinale dove e' stata eseguita la vaccinazione
     */
    public String nomecentrovaccinale;
    /**
     * <code> nome </code> nome del vaccinato
     */
    private final String nome;
    /**
     * <code> cognome </code> cognome del vaccinato
     */
    private final String cognome;
    /**
     * <code> codfisc </code> codice fiscale del vaccinato
     */
    protected String codFisc;
    /**
     * <code> datasomministrazione </code> data di somministrazione del vaccino
     */
    private final String dataSomministrazione;
    /**
     *  tipologia di vaccino inoculata
     */
    private final String tipoVax;
    /**
     * id numerico univoco su 16 bit della vaccinazione
     */
    private final String id;
    /**
     * <code> vaccinati </code> contatore dei vaccinati totali
     */
    protected static int vaccinati=0;
    /**
     * <code> linea </code> linea dove si trovano le informazioni di un vaccinato nel file Vaccinati_NomeCentroVaccinale.dati
     */
    public int linea;


    /**
     * costruttore che permette la creazione di un oggetto di tipo Vaccinato, contenente tutte le informazioni del cittadino vaccinato. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     */
    public Vaccinato(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire il nome del centro vaccinale dove è stato vaccinato il cittadino: ");
        String controlloCV = sc.nextLine().toUpperCase().strip();
        //controllo che l'utente non inserisca una stringa vuota
        while(controlloCV.equals("")){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il nome del centro vaccinale dove è stato vaccinato il cittadino: ");
            controlloCV = sc.next().toUpperCase().strip();
        }
        this.nomecentrovaccinale=controlloCV;

        System.out.println("Inserire il nome del cittadino: ");
        String controlloNome = sc.nextLine().toUpperCase().strip();
        //controllo che l'utente non inserisca una stringa vuota
        while(controlloNome.equals("")){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il nome del cittadino: ");
            controlloNome = sc.nextLine().toUpperCase().strip();
        }
        this.nome=controlloNome;

        System.out.println("Inserire il cognome del cittadino: ");
        String controlloCognome = sc.nextLine().toUpperCase().strip();
        //controllo che l'utente non inserisca una stringa vuota
        while(controlloCognome.equals("".strip())){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il cognome del cittadino: ");
            controlloCognome = sc.nextLine().toUpperCase().strip();
        }
        this.cognome=controlloCognome;

        System.out.println("Inserire il codice fiscale del cittadino: ");
        String controlloCF = sc.next().toUpperCase().strip();
        //controllo che la stringa inserita sia di 16 caratteri, requisito del CF
        while (controlloCF.length() != 16){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il codice fiscale del cittadino: ");
            controlloCF = sc.next().replace(" ", "").toUpperCase();
        }
        this.codFisc=controlloCF;

        System.out.println("Inserire data somministrazione vaccino (formato gg/mm/aaaa): ");
        String gg = "";
        String  mm = "";
        String aaaa = "";
        String eccGM;
        boolean check;
        do {
            try {
                check = true;
                System.out.println("Giorno (numero): ");
                gg = sc.next();
                while (Integer.parseInt(gg) < 1 || Integer.parseInt(gg) > 31) {
                    System.out.println("Errato, Riprova! \n");
                    System.out.println("Giorno: ");
                    gg = sc.next();
                }
                System.out.println("Mese (numero): ");
                mm = sc.next();
                while (Integer.parseInt(mm) < 1 || Integer.parseInt(mm) > 12) {
                    System.out.println("Errato, Riprova! \n");
                    System.out.println("Mese (numero): ");
                    mm = sc.next();
                }
                eccGM = gg + "/" + mm;
                while (eccGM.equals("30/2") || eccGM.equals("31/2") || eccGM.equals("31/4") || eccGM.equals("31/6") || eccGM.equals("31/9") || eccGM.equals("31/11")) {
                    System.out.println("Giorni inesistenti! Riprova! \n");
                    System.out.println("Giorno: ");
                    gg = sc.next();
                    while (Integer.parseInt(gg) < 1 || Integer.parseInt(gg) > 31) {
                        System.out.println("Errato, Riprova! \n");
                        System.out.println("Giorno: ");
                        gg = sc.next();
                    }
                    System.out.println("Mese (numero): ");
                    mm = sc.next();
                    while (Integer.parseInt(mm) < 1 || Integer.parseInt(mm) > 12) {
                        System.out.println("Errato, Riprova! \n");
                        System.out.println("Mese (numero): ");
                        mm = sc.next();
                    }
                    eccGM = gg + "/" + mm;
                }
                System.out.println("Anno (dal 2021 in poi): ");
                aaaa = sc.next();
                while (Integer.parseInt(aaaa) < 2021) {
                    System.out.println("Errato, Riprova! \n");
                    System.out.println("Anno (numero): ");
                    aaaa = sc.next();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Controllare di aver inserito un valore numerico \n");
                check = false;
            }
        }while (!check);

        this.dataSomministrazione = (gg+"/"+mm+"/"+aaaa);

        System.out.println("Inserire tipologia vaccino somministrato (Pfizer, J&J, Moderna, AstraZeneca): ");
        String controllTipoVax = sc.next().strip().toUpperCase();
        while((!controllTipoVax.equals("PFIZER")) && (!controllTipoVax.equals("ASTRAZENECA")) && (!controllTipoVax.equals("MODERNA")) && (!controllTipoVax.equals("J&J"))) {
            System.out.println("Errato, Riprova!");
            System.out.println("Inserire tipologia vaccino somministrato (Pfizer, J&J, Moderna, AstraZeneca):");
            controllTipoVax = sc.next().strip().toUpperCase();
        }
        this.tipoVax=controllTipoVax;
        this.id = idnumerico();
        vaccinati++;
        //aggiunge linea di spazio nel caso il costruttore venga utilizzato più volte consecutivamente
        System.out.println();
    }

    /**
     * Fornisce una stringa contenente il nome del centro vaccinale in cui si e' vaccinato l'oggetto Vaccinato che esegue il metodo
     * @return nome del centro vaccinale dove e' stato vaccinato il cittadino
     */
    public String getNomecentrovaccinale(){
        return  this.nomecentrovaccinale;
    }

    /**
     * Fornisce una stringa contenente il codice fiscale dell'oggetto Vaccinato che esegue il metodo
     * @return codice fiscale del vaccinato
     */
    public String getCodfisc(){
        return  this.codFisc;
    }

    /**
     * Questo costruttore permette di creare un oggetto Vaccinato leggendo le informazioni dal file "Vaccinati_NomeCentroVaccinale.dati.txt", dove NomeCentrovaccinale
     * viene sostituito dinamicamente dal nome del centro vaccinale. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     * @param line numero linea nel file dalla quale immagazziniamo informazioni di un singolo oggetto Vaccinato
     * @param path indirizzo in memoria del file contenete i vaccinati di uno specifico centro vaccinale
     * @throws FileNotFoundException se il file inerente ai vaccinati di un certo centro vaccinale non esiste o non viene trovato nel path
     */
    public Vaccinato(int line, String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));

        //se line !=0 arrivo alla posizione del file Vaccinati_NomeCentroVaccinale.dati definita da line
        if(line!=0){
            for(int i=0;i<line;i++)
                sc.nextLine();}

        this.nome = sc.next().strip();
        this.cognome = sc.next().strip();
        //Supero le parole "codice fiscale="
        sc.next();sc.next();
        this.codFisc = sc.next().strip();
        //Supero le parole "Luogo di vaccinazione"
        sc.next();sc.next();sc.next();
        this.nomecentrovaccinale = sc.nextLine().strip();
        //Supero le parole "Vaccino somministrato"
        sc.next();sc.next();
        this.tipoVax = sc.next().strip();
        //Supero le parole "in data"
        sc.next();sc.next();
        this.dataSomministrazione = sc.next().strip();
        //Supero le parole "ID univoco vaccinazione"
        sc.next();sc.next();sc.next();
        this.id = sc.next();
        //Associo al campo linea, la linea dove si trova l'inizio delle informazioni del vaccinato
        this.linea = line;
        sc.close();
    }

    /**
     * Il metodo permette di generare un id numerico univoco a 16 bit per ogni vaccinato.
     * In base al numero di cifre del campo statico <code> vaccinati </code> viene concatenata all'inizio una stringa di 0 tale che la somma del numero di 0 e il numero delle cifre del campo vaccinati sia pari a 16
     * @return Stringa da 16 bit rappresentante il numero di vaccinazione del vaccinato
     */
    protected static String idnumerico(){
        String nVaccinati = String.valueOf(vaccinati);
        String str = null;
        //se il numero di vaccinati totale è un numero a 2 cifre, allora concatenerò 14 zeri all'inizio
        //se il numero di vaccinati totali è formato da 10 cifre, allora concatenerò 6 zeri all'inizio
        switch (nVaccinati.length()){
            case 0: str = "0000000000000000";
                break;
            case 1: str = "000000000000000" + nVaccinati;
                break;
            case 2: str = "00000000000000" + nVaccinati;
                break;
            case 3: str = "0000000000000" + nVaccinati;
                break;
            case 4: str = "000000000000" + nVaccinati;
                break;
            case 5: str = "00000000000" + nVaccinati;
                break;
            case 6: str = "0000000000" + nVaccinati;
                break;
            case 7: str = "000000000" + nVaccinati;
                break;
            case 8: str = "00000000" + nVaccinati;
                break;
            case 9: str = "0000000" + nVaccinati;
                break;
            case 10: str = "000000" + nVaccinati;
                break;
            case 11: str = "00000" + nVaccinati;
                break;
            case 12: str = "0000" + nVaccinati;
                break;
            case 13: str = "000" + nVaccinati;
                break;
            case 14: str = "00" + nVaccinati;
                break;
            case 15: str = "0" + nVaccinati;
                break;
            case 16: str = nVaccinati;
                break;
        }
        return str;
    }

    /**
     * Genera una stringa contenente le informazioni principali del cittadino vaccinato
     * @return stringa rappresentante le informazioni del cittadino vaccinato
     */
    public String toString(){
        String str;
        str = this.nome + " " + this.cognome + " codice fiscale: " + this.codFisc + " \n";
        str +=  "Luogo di vaccinazione: " + this.nomecentrovaccinale + " \n";
        str += "Vaccino somministrato: " + this.tipoVax + " in data " + this.dataSomministrazione + " \n";
        str += "ID univoco vaccinazione " + this.id + " \n";
        return str;
    }

    /**
     * Il metodo permette di registrare nella struttura dati contenente i cittadini vaccinati all'interno dell'oggetto CentriVaccinali (rappresentante il centro vaccinale in cui
     * e' avvenuta la vaccinazione del cittadino) le informazioni di un cittadino vaccinato e stampare le sue informazioni nel file"Vaccinati_NomeCentroVaccinale.dati.txt", dove NomeCentroVaccinale viene sostituito dinamicamente
     * dal nome del centro in questione
     * @param ht Hashtable dove sono registrati i centri vaccinali, dentro ad ogni oggetto centro vaccinale e' presente un campo "vaccinati", anch'esso Hashtable, nel quale vengono registrati i cittadini vaccinati
     */
    public static void registraVaccinato(Hashtable<String,CentriVaccinali> ht) {
        Vaccinato vac = new Vaccinato();
        try {
            //il nome del centro vaccinale contenuto nell'oggetto vaccinato viene usato come key per estrarre dalla struttura dati ht il centro vaccinale dove è avvenuta la vaccinazione
            CentriVaccinali cv = ht.get(vac.getNomecentrovaccinale());
            //l'oggetto Vaccinato viene inserito in struttura dati con key il codice fiscale
            cv.vaccinati.put(vac.getCodfisc(), vac);
            //stampo l'oggetto Vaccinato su file
            new FileDatiCV(vac);
            System.out.println("Operazione eseguita con successo! \n");
            System.out.println();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Il nome del centro vaccinale inserito è inesistente! \n");
        }
    }

    /**
     * Il metodo permette, ogni qual volta che viene utilizzato il prgramma, di caricare i dati dei vaccinati salvati sui diversi file nella struttura dati designata
     * @param ht Hashtable dove sono registrati i centri vaccinali, dentro ad ogni oggetto centro vaccinale e' presente un campo "vaccinati" nel quale vengono immagazinati i cittadini vaccinati
     * @throws FileNotFoundException se il file Vaccinati.NomeCentroVaccinale.dati non esiste o non viene trovato nel path NomeCentroVaccinale viene sostituito dinamicamente in base
     * al nome del centro vaccinale dove e' stata eseguita la vaccinazione
     */
    public static void caricaDativaccinati(Hashtable<String,CentriVaccinali> ht) throws FileNotFoundException {
        ArrayList<String> VaxNames = new ArrayList<>();
        int numerovaccinati = 0;

        for (CentriVaccinali cv : CentriVaccinali.hub) {
            String path = Path.getPathNomeCentroVaccinale(cv);
            File filevac = new File(path);
            // controllo che il centro vaccinale registrato abbia almeno un vaccinato registrato, se il file specificato dal path esiste allora è stato salvato almeno un vaccinato e si prosegue con il caricamento
            if(filevac.exists())
                VaxNames.add(cv.getNomeCentroVaccinale());
        }
        for (CentriVaccinali cv : CentriVaccinali.ospedaliero) {
            String path = Path.getPathNomeCentroVaccinale(cv);
            File filevac = new File(path);
            // controllo che il centro vaccinale registrato abbia almeno un vaccinato registrato, se il file specificato dal path esiste allora è stato salvato almeno un vaccinato e si prosegue con il caricamento
            if(filevac.exists())
                VaxNames.add(cv.getNomeCentroVaccinale());
        }
        for (CentriVaccinali cv : CentriVaccinali.aziendale) {
            String path = Path.getPathNomeCentroVaccinale(cv);
            File filevac = new File(path);
            // controllo che il centro vaccinale registrato abbia almeno un vaccinato registrato, se il file specificato dal path esiste allora è stato salvato almeno un vaccinato e si prosegue con il caricamento
            if(filevac.exists())
                VaxNames.add(cv.getNomeCentroVaccinale());
        }
        for (String name : VaxNames) {

            String path = Path.getPathNomeCentroVaccinale(name);
            File filecv = new File(path);

            // scanner del metodo, != da scanner del costruttore
            Scanner sc = new Scanner(filecv);
            //la variabile line serve per indicare la posizione nel file Vaccinati_NomeCentroVaccinale.dati delle informazioni del vaccinato da cui si andra' a costruire l'oggetto Vaccinato
            //line inizializzato ad 1 perchè nella linea 0 nel file sono presenti il numero di segnalazioni e la somma delle severità riscontrate
            int line = 1;
            do {
                //avanzo di 11 linee
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();

                Vaccinato vac = new Vaccinato(line, path);
                //ottengo il centro vaccinale dove è avvenuta la vaccinazione
                CentriVaccinali VaxCenter = ht.get(vac.getNomecentrovaccinale());
                //inserisco nella struttura dati del centro vaccinale appena trovato il vaccinato
                VaxCenter.vaccinati.put(vac.getCodfisc(),vac);
                numerovaccinati++;
                //lo scanner del file supera la linea separatrice (--------)
                sc.next();
                //tra le informazioni di 2 utenti diversi nel file Vaccinati_NomeCentroVaccinale ci sono 11 linee
                line+=11;
            } while(sc.hasNext());
        }
        //aggiorno anche campo statico vaccinati
        vaccinati = numerovaccinati;
    }
}




