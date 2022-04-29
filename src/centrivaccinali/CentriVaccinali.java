package centrivaccinali;

import cittadini.Cittadini;
import java.io.*;
import java.util.*;

/**
 * <p> La classe CentriVaccinali permette di creare e registrare a sistema un centro vaccinale e inserirlo sul file CentriVaccinali.dati.
 * Ogni qual volta viene utilizzato il programma, permette di caricare e controllare i dati dei centri vaccinali salvati su file nella struttura dati designata. </p>
 *
 * @see CentriVaccinali#registraCentroVaccinale(Hashtable) Il metodo consente di registrare un centro di vaccinazione nel sistema
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 * @author Simone Torno
 */


public class CentriVaccinali {

    /**
     * <code> NomeCentroVaccinale </code> nome del centro vaccinale
     */
    private final String nomeCentroVaccinale;

    /**
     * <code> qualificatore </code> e' il tipo di locazione in cui si trova il centro vaccinale, come una via, una piazza o un viale
     */
    private final String qualificatore;

    /**
     * <code> nomeVia </code> e' il nome della via in cui si trova il centro vaccinale
     */
    private final String nomeVia;

    /**
     * <code> numerocivico </code> e' il numero civico in cui si trova il centro vaccinale
     */
    //String perchè il numero civico può presentare delle lettere es.12/A
    private final String numeroCivico;

    /**
     * <code> comune </code> comune dove e' situato il centro vaccinale
     */
    public String comune;

    /**
     * <code> provincia </code> provincia in cui si trova il centro vaccinale
     */
    private final String provincia;

    /**
     * <code> cap </code> cap del comune del centro vaccinale
     */
    private final String cap;

    /**
     * <code> vaccinati </code> Hashtable dove sono inserite tutte le persone vaccinate nello specifico centro vaccinale
     */
    public Hashtable<String,Vaccinato> vaccinati = new Hashtable<>();

    /**
     * <code> tipologia </code> tipologia di centro vaccinale, aziendale ospedaliero o hub
     */
    public String tipologia;

    /**
     * <code> cittadiniregistrati </code> Hashtable dove sono registrati i cittadini che hanno effettuato la registrazione in uno specifico centro vaccinale
     */
    public Hashtable<String,Cittadini> cittadiniRegistrati = new Hashtable<>();

    /**
     * <code> hub </code> lista dove vengono immagazzinati i centri vaccinali di tipo hub
     */
    public static ArrayList<CentriVaccinali> hub = new ArrayList<>();

    /**
     * <code> aziendale </code> lista dove vengono immagazzinati i centri vaccinali di tipo aziendale
     */
    public static ArrayList<CentriVaccinali> aziendale = new ArrayList<>();

    /**
     * <code> ospedaliero </code> lista dove vengono immagazzinati i centri vaccinali di tipo ospedaliero
     */
    public  static  ArrayList<CentriVaccinali> ospedaliero = new ArrayList<>();

    /**
     * <code> sommaSeverita </code> somma di tutte le severita' di eventi avversi riscontrati post vaccinazione in uno specifico centro vaccinale
     */
    public int sommaSeverita = 0;

    /**
     * <code> numeroSegnalazioni </code> numero totale di tutte le segnalazioni riscontrate in uno specifico centro vaccinale
     */
    public int numeroSegnalazioni = 0;

    /**
     * costruisce un oggetto centro vaccinale con le informazioni inserite dall'operatore vaccinale. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     * @param ht Hashtable dove sono registrati i centri vaccinali
     */
    public CentriVaccinali(Hashtable<String,CentriVaccinali> ht){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire il nome del centro vaccinale: ");

        String controlloNomeCV;
        do {
            //utilizzo sc.nextLine() perchè il nome del centro potrebbe essere formato da più parole
            controlloNomeCV = sc.nextLine().toUpperCase().strip();
            //controllo che l'utente non inserisca una stringa vuota o che il nome del centro non sia stato ancora inserito
            while (controlloNomeCV.equals("")||ht.containsKey(controlloNomeCV)) {
                if(controlloNomeCV.equals(""))
                    System.out.println("Errato, Riprova! \n");
                if(ht.containsKey(controlloNomeCV))
                    System.out.println("Nome del centro vaccinale già presente, prego modificarlo \n");
                System.out.println("Inserire il nome del centro vaccinale: ");
                controlloNomeCV = sc.nextLine().toUpperCase().strip();
            }
        }while (ht.containsKey(controlloNomeCV));
        this.nomeCentroVaccinale=controlloNomeCV;

        System.out.println("Inserire il qualificatore del centro vaccinale (via/viale/piazza): ");
        String controlloVia = sc.next().toUpperCase().strip();
        while(!controlloVia.equals("VIA") && !controlloVia.equals("VIALE") && !controlloVia.equals("PIAZZA")){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il qualificatore del centro vaccinale (via/viale/piazza/vicolo): ");
            controlloVia = sc.next().toUpperCase().strip();
        }
        this.qualificatore = controlloVia;
        sc.nextLine();

        System.out.println("Inserire il nome della via/viale/piazza dove risiede il centro vaccinale: ");
        String controllonomeVIA = sc.nextLine().toUpperCase().strip();
        //controllo che l'utente non inserisca una stringa vuota
        while(controllonomeVIA.equals("")){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il nome della via/viale/piazza dove risiede il centro vaccinale: ");
            controllonomeVIA = sc.nextLine().toUpperCase().strip();
        }
        this.nomeVia=controllonomeVIA;

        System.out.println("Inserire il numero civico dove risiede il centro vaccinale: ");
        String controlloCivico = sc.next().strip();
        //controllo che l'utente non inserisca una stringa vuota e che inserisca almeno un numero
        while(!(controlloCivico.equals("")||controlloCivico.contains("1")||controlloCivico.contains("2")||controlloCivico.contains("3")||controlloCivico.contains("4")||controlloCivico.contains("5")||controlloCivico.contains("6")||controlloCivico.contains("7")||controlloCivico.contains("8")||controlloCivico.contains("9"))){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il numero civico dove risiede il centro vaccinale: ");
            controlloCivico = sc.next().toUpperCase().strip();
        }
        this.numeroCivico=controlloCivico;
        sc.nextLine();

        System.out.println("Inserire il comune dove risiede il centro vaccinale: ");
        String controlloComune = sc.nextLine().toUpperCase().strip();
        //controllo che l'utente non inserisca una stringa vuota
        while(controlloComune.equals("".strip())){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire il comune dove risiede il centro vaccinale: ");
            controlloComune = sc.nextLine().toUpperCase().strip();
        }
        this.comune=controlloComune;

        System.out.println("Inserire la SIGLA della provincia dove risiede il centro vaccinale (es. Varese VA, Milano MI): ");
        String controlloProvincia = sc.next().toUpperCase().strip();
        //controllo che l'utente inserisca una stringa formata da due caratteri, visto che deve essere inserite la sigla della provincia non il nome intero
        while(controlloProvincia.length()!=2){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire la SIGLA della provincia dove risiede il centro vaccinale (es. Varese VA, Milano MI): ");
            controlloProvincia = sc.next().toUpperCase();
        }
        this.provincia = controlloProvincia;
        boolean check;
        int controlloCAP = 0;
        do {
            check = true;
            try {
                System.out.println("Inserire CAP di provenienza del centro vaccinale: ");
                 controlloCAP = sc.nextInt();
                //controllo che il CAP inserito sia valido
                while (controlloCAP < 00010 || controlloCAP > 97100) {
                    System.out.println("Errato, Riprova! \n");
                    System.out.println("Inserire CAP di provenienza del centro vaccinale: ");
                    controlloCAP = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                check = false;
                System.out.println("Inserire valore numerico! \n");
                sc.nextLine();
            }
        }while (!check);
        this.cap = String.valueOf(controlloCAP);

        System.out.println("Inserire la tipologia di centro vaccinale (aziendale/ospedaliero/hub): ");
        String controlloTipologia = sc.next().toUpperCase().strip();
        while(!controlloTipologia.equals("AZIENDALE") && !controlloTipologia.equals("OSPEDALIERO") && !controlloTipologia.equals("HUB")){
            System.out.println("Errato, Riprova! \n");
            System.out.println("Inserire la tipologia di centro vaccinale (aziendale/ospedaliero/hub): ");
            controlloTipologia = sc.next().toUpperCase().strip();
        }
        this.tipologia = controlloTipologia;

        //aggiunge linea di spazio nel caso il costruttore venga utilizzato più volte consecutivamente
        System.out.println();
    }

    /**
     * Il metodo fornisce il nome del centro vaccinale dell'oggetto CentriVaccinali che svolge il metodo
     * @return nome del centro vaccinale
     */
    public String getNomeCentroVaccinale(){
        return this.nomeCentroVaccinale;
    }

    /**
     * Il metodo fornisce il comune in cui si trova l'oggetto CentriVaccinali che svolge il metodo
     * @return comune dove si trova il centro vaccinale
     */
    public String getComune(){
        return this.comune;
    }

    /**
     * Il metodo fornisce la tipologia di centro vaccinale dell'oggetto CentriVaccinali che svolge il metodo
     * @return tipologia del centro vaccinale
     */
    public String getTipologia(){
        return this.tipologia;
    }

    /**
     * costruisce un oggetto Centrivaccinali leggendo le informazioni dal file CentriVaccinali.dati. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     * @param line   numero linea nel file dalla quale immagazziniamo le informazioni per la creazione di un singolo oggetto CentriVaccinali
     * @throws FileNotFoundException se il file Centrivaccinali.dati non esiste o non viene trovato nel path
     */
    public CentriVaccinali (int line) throws FileNotFoundException {
        String pathcv = Path.getPathCentriVaccinali();
        Scanner sc = new Scanner(new File(pathcv));

        //se line !=0 arrivo alla posizione del file CentriVaccinali.dati definita da line
        if(line!=0){
            for(int i=0;i<line;i++)
                sc.nextLine();}

        this.nomeCentroVaccinale = sc.nextLine().strip();
        //Supero la parola "Comune:"
        sc.next();
        this.comune = sc.nextLine().strip();
        //Supero la parola "Indirizzo:"
        sc.next();
        this.qualificatore = sc.next().strip();
        this.nomeVia = sc.next().strip();
        this.numeroCivico = sc.next().strip();
        //Supero la parola "Provincia:"
        sc.next();
        //elimino anche le parentesi, che nella visualizzazione sarebbero ridondanti
        this.provincia = sc.next().replace("(","").replace(")","").strip();
        //Supero la parola "CAP:"
        sc.next();
        this.cap = sc.next();
        //Supero le parole "centro vaccinale di tipo"
        sc.next();sc.next();sc.next();sc.next();
        this.tipologia = sc.next().strip();

        sc.close();
    }

    /**
     * Il metodo permette, ogni qual volta che viene utilizzato il prgramma, di caricare i dati dei centri vaccinali salvati su file nella Hashtable dove sono contenuti i centri vaccinali
     * @param ht struttura dati dove sono registrati i centri vaccinali
     * @throws FileNotFoundException se il file Centrivaccinali.dati non esiste o non viene trovato nel path
     */
    protected static void CaricaDaticv(Hashtable<String,CentriVaccinali> ht) throws FileNotFoundException {
        String pathcv =Path.getPathCentriVaccinali();
        File filecv = new File(pathcv);
        //controllo esistenza del file, se file non presente allora viene creato.
        try {
            if (!filecv.exists())
                filecv.createNewFile();
        } catch (IOException e){
            e.printStackTrace();}

        //Scanner del metodo, != da scanner del costruttore
        Scanner sc = new Scanner(filecv);
        //la variabile line serve per indicare la posizione nel file CentriVaccinali.dati delle informazioni del centro vaccinale da cui si andra' a costruire l'oggetto CentriVaccinali
        int line = 0;
        //visto che nel file Centrivaccinali.dati il primo centro vaccinale stampato non ha sopra di sè la linea separatrice, lo scanner deve avanzare una volta in meno rispetto alle successive volte.
        //primavolta è true solo alla prima iterazione, dopo passa subito a false.
        boolean primavolta = true;
        do{
            if(primavolta){
                //avanzo di 5 linee nel file solo la prima volta
                sc.nextLine();sc.nextLine();sc.nextLine();sc.nextLine();sc.nextLine();
                primavolta = false;
            }
            else{
                //avanzo di 6 linee nel file dalla seconda iterazione compresa in poi
                sc.nextLine();sc.nextLine();sc.nextLine();sc.nextLine();sc.nextLine();sc.nextLine();}

            CentriVaccinali VaxCenter = new CentriVaccinali(line);
            //controllo di sicurezza per evitare di re-inserire in struttura dati 2 centri vaccinali con lo stesso nome
            if(!ht.containsKey(VaxCenter.getNomeCentroVaccinale())){
                //inserisco in struttura dati il centro vaccinale con chiave il nome del centro vaccinale
                ht.put(VaxCenter.getNomeCentroVaccinale(), VaxCenter);

                //inserisco il centro vaccinale nella lista adeguata in base alla sua tipologia
                if(VaxCenter.getTipologia().equals("HUB"))
                    hub.add(VaxCenter);
                else{
                    if(VaxCenter.getTipologia().equals("AZIENDALE"))
                        aziendale.add(VaxCenter);
                    else
                        ospedaliero.add(VaxCenter);
                }
            }
            //linea aumenta di 4 ogni volta perchè l'inizio delle informazioni tra un centro vaccinale e un altro nel file distano 4 linee
            line += 6;
            //permette di eliminare la "greca" altrimenti sbaglia il controllo del do-while
            sc.next();
        }while(sc.hasNext());
    }

    /**
     * Il metodo controlla se il file "CentriVaccinali.dati.txt" esiste oppure no
     * @return true se il file esiste, altrimenti false
     * @param pathcv percorso in memoria del file "CentriVaccinali.dati.txt"
     */
    protected static boolean controllocaricamento(String pathcv) {
        File filecv = new File(pathcv);
        return filecv.exists();

    }

    /**
     * Il metodo genera una stringa delle informazioni principali dell'oggetto CentriVaccinali che esegue il metodo
     * @return stringa contenente le informazioni di un centro vaccinale
     */
    public String toString(){
        return  this.nomeCentroVaccinale +" \n" + "Comune: " +  this.comune + " \n" + "Indirizzo: " + this.qualificatore + " " + this.nomeVia + " " + this.numeroCivico + " \n" + "Provincia: (" + this.provincia + ") CAP: " + this.cap + " \n" + "centro vaccinale di tipo " + this.tipologia + " \n";
    }

    /**
     * Il metodo permette di registrare a sistema un centro vaccinale e stampare le sue informazioni nel file "CentriVaccinali.dati.txt"
     * @param ht Hashtable dove vengono registrati i centri vaccinali
     */
    protected static void registraCentroVaccinale(Hashtable<String,CentriVaccinali> ht){
        CentriVaccinali centro = new CentriVaccinali(ht);
        ht.put(centro.getNomeCentroVaccinale(),centro);

        //blocco switch per inserire il cv appena creato nella corretta lista per tipologia (hub,aziendale ospedaliero)
        switch (centro.getTipologia()){
            case "HUB": hub.add(centro); break;
            case "OSPEDALIERO": ospedaliero.add(centro); break;
            case "AZIENDALE": aziendale.add(centro); break;
        }
        //stampo il centro vaccinale appena registrato nel file CentriVaccinali.dati
        new FileCentriVaccinali(centro);
        System.out.println("Operazione eseguita con successo! \n");
    }

    /**
     * Il metodo confronta un oggetto di tipo CentriVaccinali con un altro oggetto fornito come parametro
     * @param obj oggetto con cui confrontare oggetto CentriVaccinali
     * @return true se i due oggetti hanno lo stesso campo nomeCentroVaccinale, altrimenti false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof CentriVaccinali))
            return false;
        CentriVaccinali cv = (CentriVaccinali) obj;
        return (Objects.equals(getNomeCentroVaccinale(), cv.getNomeCentroVaccinale()));

    }

    /**
     * Il metodo fornisce il valore di hash del nome del centro vaccinale dell'oggetto CentriVaccinali che esegue il metodo
     * @return valore hash del nome del centro vaccinale
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.nomeCentroVaccinale);
    }

    /**
     * Il metodo permette all'utente di selezionare un centro vaccinale da una ArrayList di centri vaccinali fornita come parametro
     * @param VaxList lista formata da oggetti di tipo CentriVaccinali
     * @return centro vaccinale selezionato dall'utente
     */
    public static CentriVaccinali getVaxCenter(ArrayList<CentriVaccinali> VaxList){
        Scanner sc = new Scanner(System.in);
        int count = 1;
        int index = 0;
        boolean check;
        //controllo che VaxList non sia una lista vuota
        if(VaxList.size()!=0){
            for (CentriVaccinali cv : VaxList) {
                System.out.println(count + ": " + cv.toString() + "\n");
                count++;
            }

            System.out.println("Inserire il numero rappresentante il centro vaccinale desiderato");

            //intercetto l'eccezione che viene lanciato quando l'utente inserisce nella variabile index un valore diverso da un numero intero
            do {
                try {
                    check = true;
                    index = sc.nextInt();
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                    System.out.println("Prego inserire un valore numerico \n");
                    check = false;
                }
            }while (!check);
            // i messaggi d'errore delle eccezioni lanciate sono visualizzati nei metodi a diretto contatto con l'utente
            if(index>VaxList.size())
                throw new IndexOutOfBoundsException();

            return VaxList.get(index - 1);
        }
        else
            throw new NullPointerException();
    }

    /**
     ** Il metodo itera tutti i centri vaccinali presenti in struttura dati e aggiorna per ognuno i campi sommaSeverita e numeroSegnalazioni
     ** @param ht struttura dati che contiene i centri vaccinali
     ** @throws FileNotFoundException se la ricerca del file Vacinati_NomeCentroVaccinale.dati non ha esito positivo
     **/
    public static void prospettoRiassuntivo(Hashtable<String,CentriVaccinali> ht) throws FileNotFoundException {
        Enumeration<String> key = ht.keys();
        while (key.hasMoreElements()) {
            String VaxName = key.nextElement();
            CentriVaccinali vaxcenter = ht.get(VaxName);
            vaxcenter.caricaProspettoRiassuntivo(VaxName);
        }
    }


    /**
     * Il metodo permette di aggiornare i campi numeroSegnalazioni e sommaSeverita dell'oggetto CentriVaccinali all'avvio del programma, leggendo le informazioni dagli specifici file "Vaccinati_NomeCentroVaccinale.dati.txt"
     * @param vaxName nome del centro vaccinale
     * @throws FileNotFoundException se la ricerca del file CentriVaccinali.dati non ha esito positivo
     */
    public void caricaProspettoRiassuntivo(String vaxName)throws FileNotFoundException {
        String path = Path.getPathNomeCentroVaccinale(vaxName);
        File filecv = new File(path);
        if(filecv.exists()){
            Scanner sc = new Scanner(new File(path));
            //supero nel file le parole "Numero segnalazioni="
            sc.next();
            sc.next();
            try {
                this.numeroSegnalazioni = Integer.parseInt(sc.next());
                //Supero nel file le parole "somma severità="
                sc.next();
                sc.next();
                this.sommaSeverita = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                e.printStackTrace();}
        }
    }

    /**
     * Il metodo fornisce la media delle severita' di eventi avversi riscontrati nel centro vaccinale che esegue il metodo
     * @return media delle severita' dei sintomi post-vaccinazione segnalati in un centro vaccinale
     */
    public float mediaSeverita(){
        return (float) this.sommaSeverita/this.numeroSegnalazioni;
    }
}









