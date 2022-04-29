package cittadini;

import centrivaccinali.CentriVaccinali;
import centrivaccinali.Vaccinato;

import java.io.*;
import java.util.*;

/**
 * <p>La classe "Cittadini" permette all' utente che deve effetuare la vaccinazione di registrarsi,
 *
 * i dati inseriti vengono successivamente salvati su file
 *
 * la classe permette di creare un oggetto "cittadini" leggendo le informazioni dal file "Cittadini_Registrati.dati"
 *
 * ogni qual volta viene utilizzato il programma, permette di caricare i dati dei cittadini registrati salvati
 *  su file nella struttura dati "cittadiniregistrati" presente come campo in ogni oggetto di tipo CentriVaccinali contenuto nella struttura dati dei centri vaccinali
 *
 * consente di registrare a sistema un cittadino nel centro vaccinale selezionato dopo la ricerca del centro vaccinale desiderato
 *
 * permette inoltre di ricercare il proprio centro vaccinale dove e' avvenuta la vaccinazione, eseguire il logIn e successivamente inserire l'elenco
 * personalizzato di sintomi comparsi post-vaccinazione
 *
 * infine permette di avere un prospetto riassuntivo delle segnalazioni di eventi avversi riscontrate post-vaccinazione di uno specifico centro vaccinale </p>
 *
 * @see Cittadini#cercaCentroVaccinale(String, String) Il metodo permette di ricercare un centro vaccinale inserendo il comune e la tipologia
 *
 * @see Cittadini#cercaCentroVaccinale(Hashtable, String)  Il metodo permette di ricercare il centro vaccinale desiderato tramite l'inserimento di una stringa di caratteri
 *
 * @see Cittadini#visalizzaInfoCentroVaccinale(Hashtable) Il metodo permette di avere un prospetto riassuntivo delle segnalazioni di eventi avversi riscontrate post-vaccinazione di uno specifico centro vaccinale
 *
 * @see Cittadini#registraCittadino(Hashtable) Il metodo permette di registrare a sistema un cittadino nel centro vaccinale selezionato successivamente alla ricerca del centro vaccinale desiderato
 *
 * @see Cittadini#inserisciEventiAvversi(Hashtable) l metodo permette di ricercare il proprio centro vaccinale dove e' avvenuta la vaccinazione, permette di eseguire il logIn e  inserire l'elenco
 * dei sintomi comparsi post-vaccinazione in modo personalizzato.
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 * @author Simone Torno
 */


public class Cittadini {

    /**
     * <code> nomeCognome </code> nome e cognome del cittadino
     */
    private final String nomeCognome;
    /**
     * <code> codicefiscale </code> codice fiscale del cittadino
     */
    private final String codicefiscale;
    /**
     * <code> mail </code> mail di posta elettronica del cittadino
     */
    private final String email;
    /**
     * <code> userid </code> user id per login utente
     */
    private final String userId;
    /**
     * <code> password </code> password per login utente
     */
    private final String password;
    /**
     * <code> id </code> id numerico univoco di vaccinazione
     */
    private final String id;
    /**
     * <code> centrovaccinale </code> centro vaccinale dove si e' registrato il cittadino
     */
    public String centroVaccinale;

    /**
     * Crea un oggetto contenente tutte le informazioni necessarie per la creazione di un oggetto di tipo Cittadini. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     * @param ht hashtable, contenuta dentro l'oggetto CentriVaccinali, dove sono salvati i cittadini registrati dello specifico centro vaccinale
     * @param NomeCentroVaccinale nome del centro vaccinale
     */
    public Cittadini(String NomeCentroVaccinale, Hashtable<String, Cittadini> ht )  {
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire il proprio Nome e Cognome: ");
        String controlloNC = sc.nextLine().toUpperCase().strip();
        while (controlloNC.equals("".strip())) {
            System.out.println("Errato, Riprova!");
            System.out.println("Inserire il proprio Nome e Cognome: ");
            controlloNC = sc.nextLine().toUpperCase().strip();
        }
        this.nomeCognome = controlloNC;

        System.out.println("Inserire il proprio Codice Fiscale");
        String controlloCF = sc.next().toUpperCase().strip();
        while (controlloCF.length() != 16) {
            System.out.println("Errato, Riprova!");
            System.out.println("Inserire il codice fiscale del cittadino: ");
            controlloCF = sc.next();
        }
        this.codicefiscale = controlloCF;

        System.out.println("Inserire indirizzo di posta elettronica: ");
        String controlloMail = sc.next().strip();
        if(!(controlloMail.contains("@") && controlloMail.contains("."))){
            System.out.println("Reinserire indirizzo di posta elettronica: ");
            controlloMail = sc.next().strip();
        }
        System.out.println("L'indirizzo e-mail è corretto? : " + controlloMail);
        System.out.println("SI/NO");
        String confermaMail = sc.next().toUpperCase().strip();
        while (!confermaMail.equals("SI")) {
            System.out.println("Inserire indirizzo di posta elettronica:");
            controlloMail = sc.next().strip();
            System.out.println("L'indirizzo e-mail è corretto? : " + controlloMail);
            System.out.println("SI/NO");
            confermaMail = sc.next().strip().toUpperCase();
        }
        this.email = controlloMail;

        System.out.println("Inserire userId: ");
        String controlloUserID = sc.next().strip();
        //userId sarà la key della nostra hashtable, quindi quando si procede con l'inserimento dell'user viene sempre controllato che non ne sia già stato inserito uno uguale, evitando collisioni
        while (ht.containsKey(controlloUserID.strip())) {
            System.out.println("User id già esistente, prego riprovare");
            System.out.println("Inserire userId: ");
            controlloUserID = sc.next();
        }
        this.userId = controlloUserID;

        System.out.println("Inserire password di sistema (almeno 8 caratteri): ");
        String controlloPW = sc.next();
        while (controlloPW.length() < 8) {
            System.out.println("Password non valida!");
            System.out.println("Inserire password di sistema (almeno 8 caratteri): ");
            controlloPW = sc.next();
        }
        //cripto la password
        this.password = new CifrarioDiCesare().enc(controlloPW);
        System.out.println("Inserire ID Vaccinazione del Cittadino: ");
        this.id = sc.next().strip();
        this.centroVaccinale = NomeCentroVaccinale;
        //aggiunge linea di spazio nel caso il costruttore venga utilizzato più volte consecutivamente
        System.out.println();
    }

    /**
     * Fornisce la stringa contenente il codice fiscale dell'oggetto Cittadini che esegue il metodo
     * @return codice fiscale del cittadino
     */
    public String getCodicefiscale() {
        return this.codicefiscale;
    }

    /**
     * Fornisce la stringa contenente l'user id dell'oggetto Cittadini che esegue il metodo
     * @return userid del cittadino
     */
    public String getUserid() {
        return this.userId;
    }

    /**
     * Fornisce la stringa contenente la password dell'oggetto Cittadini che esegue il metodo
     * @return password del cittadino
     */
    private String getPassword() {
        return this.password;
    }

    /**
     * Fornisce la stringa contenente il nome del centro vaccinale dell'oggetto Cittadini che esegue il metodo
     * @return nome del centro vaccinale dove si e' registrato il cittadino
     */
    public String getCentrovaccinale() {
        return this.centroVaccinale;
    }

    /**
     * Crea un oggetto cittadini leggendo le informazioni dal file Cittadini_Registrati.dati. Le informazioni vengono salvate in maiuscolo e vengono eliminati gli spazi bianchi all'inizio e alla fine delle stringhe
     * @param path percorso in memoria del file "Cittadini_Registrati.dati.txt"
     * @param line numero linea nel file dalla quale immagazziniamo informazioni di un singolo oggetto di tipo Cittadini
     * @throws FileNotFoundException se il file Cittadini_Registrati.dati non esiste o non viene trovato nel path
     */
    public Cittadini(int line, String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));

        //se line !=0 arrivo alla posizione del file Cittadini_Registrati.dati definita da line
        if (line != 0) {
            for (int i = 0; i < line; i++)
                sc.nextLine();
        }

        this.nomeCognome = sc.nextLine().strip();
        //supero le parole "Codice Fiscale:"
        sc.next();
        sc.next();
        this.codicefiscale = sc.next().strip();
        //supero la parola "E_mail:"
        sc.next();
        this.email = sc.next().strip();
        //supero la parola "User_ID"
        sc.next();
        this.userId = sc.next().strip();
        //supero la parola "Password:"
        sc.next();
        this.password = sc.next().strip();
        //supero le parole "Id univoco"
        sc.next();
        sc.next();
        this.id = sc.next().strip();
        //supero le parole "Luogo di vaccinazione:"
        sc.next();
        sc.nextLine();
        this.centroVaccinale = sc.nextLine().strip();
    }

    /**
     * Il metodo permette, ogni qual volta viene utilizzato il programma, di caricare i dati dei cittadini registrati salvati sul file "Cittadini_Registrati.dati.txt"
     * nella Hashtable cittadiniregistrati presente come campo in ogni oggetto di tipo CentriVaccinali.
     *
     * @param ht ht struttura dati dove sono registrati i centri vaccinali
     * @throws FileNotFoundException se il file Cittadini_Rgistrati.dati non esiste o non viene trovato nel path
     */
    public static void caricadaticittadini(Hashtable<String, CentriVaccinali> ht) throws FileNotFoundException {
        String path = PathCittadini.getPathCittadini();
        File filecv = new File(path);
        CentriVaccinali Vaxcenter;
        //controllo esistenza del file, se il file non esiste allora lo creo
        try {
            if (!filecv.exists())
                filecv.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // scanner del metodo, != da scanner del costruttore
        Scanner sc = new Scanner(filecv);
        //la variabile line serve per indicare la posizione nel file Cittadini_Registrati.dati delle informazioni del cittadino da cui si andra' a costruire l'oggetto Cittadini
        //alla linea 0 è presente la prima informazione necessaria alla costruzione dell'oggetto Cittadini
        int line = 0;
        //nel file Cittadini_Registrati.dati alla prima iterazione lo Scanner sc si muove per 7 linee verso il basso, poi finito il corpo del ciclo esegue il controllo per verificare se sono presenti
        //altre informazioni per creari nuovi oggetti Cittadini. Dopo la prima iterazione lo Scanner si muoverà sempre per 8 linee verso il basso, perchè deve sorpassare anche la linea separatrice che
        //alla prima iterazione non era presente.
        boolean primavolta = true;

        do {
            if (primavolta) {
                //mi muovo di 7 linee verso il basso
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                //primavolta dopo la prima iterazione passa subito a false, quindi per tutte le iterazioni successive lo Scanner sc si sposterà sempre di 8 linee
                primavolta = false;
            } else {
                //mi muovo di 8 linee verso il basso
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
                sc.nextLine();
            }

            Cittadini cit = new Cittadini(line, path);
            //ottengo il nome del centro vaccinale in cui si è vaccinato il cittadino
            Vaxcenter = ht.get(cit.getCentrovaccinale());
            //inserisco l'oggetto cittadino in struttura dati del centro vaccinale dove è avvenuta la vaccinazione
            Vaxcenter.cittadiniRegistrati.put(cit.getUserid(), cit);
            //aumentando line di 8 righe, nel file Cittadini_Registrati.dati accedo alla prima riga dove sono contenute le informazioni del prossimo cittadino
            line += 8;
            //supero la linea separatrice (-------)
            sc.next();
        } while (sc.hasNext());
    }


    /**
     * Controlla se il file "Cittadini_Registrati.dati.txt" esiste
     * @return true se il file Cittadini_Registrati.dati esiste, altrimenti false
     */
    public static boolean controllocaricamento() {
        String path = PathCittadini.getPathCittadini();
        File filecv = new File(path);
        return filecv.exists();
    }

    /**
     * Il metodo, dopo la ricerca del centro vaccinale desiderato, permette di registrare un cittadino nella struttura dati contenuta nell'oggetto CentriVaccinali rappresentante il centro selezionato.
     * Successivamente stampa le informazioni dell'oggetto Cittadini creato nel file "Cittadini_Registrati.dati.txt"
     * @param ht struttura dati dove sono registrati a sistema i centri vaccinali
     */
    public static void registraCittadino(Hashtable<String, CentriVaccinali> ht) {
        Scanner sc = new Scanner(System.in);
        boolean check;
        String scelta;
        ArrayList<CentriVaccinali> Vaxlist;
        String comune, tipologia, NameCenter;
        CentriVaccinali VaxCenter = null;

        System.out.println("Per ricercare il centro vaccinale nel quale ci si desidera registrare, esistono 2 metodi di ricerca \n");
        System.out.println("1: ricerca per comune e tipologia \n");
        System.out.println("2: ricerca per nome \n");
        System.out.println("Inserire 1/2");

        do {
            scelta = sc.next();
            if(!(scelta.equals("1")||scelta.equals("2")))
                System.out.println("Inserire 1/2 \n");
        }while(!(scelta.equals("1")||scelta.equals("2")));

        sc.nextLine();

        if (scelta.equals("1")) {
            //ricerco il centro vaccinale con le caratteristiche inserite
            do {
                System.out.println("Inserire comune");
                comune = sc.nextLine().toUpperCase().strip();

                System.out.println("Inserire tipologia");
                tipologia = sc.next().toUpperCase();
                check = true;
                try {
                    Vaxlist = cercaCentroVaccinale(comune, tipologia);
                    //seleziono centro vaccinale desiderato
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while(!check);
            //creo l'oggetto cittadino inserendo in aggiunta l'informazione appena trovata ovvero il centro vaccinale dove è avvenuta la vaccinazione
            //creo l'oggetto cittadino controllando che l'user id non sia già presente in struttura dati, onde e vitare eventuali collisioni
            Cittadini person = new Cittadini(VaxCenter.getNomeCentroVaccinale(), VaxCenter.cittadiniRegistrati);
            //inserisco in struttura dati con key userId
            VaxCenter.cittadiniRegistrati.put(person.getUserid(), person);
            //stampo l'oggetto appena creato nel file Cittadini_Registrati.dati
            new FileDatiCittadini(person);
            System.out.println("Operazione eseguita con successo \n");
        }else{
            //ricerco il centro vaccinale con le caratteristiche inserite
            do {
                System.out.println("Inserire nome centro vaccinale");
                NameCenter = sc.nextLine().toUpperCase().strip();
                check = true;
                try {
                    Vaxlist = cercaCentroVaccinale(ht, NameCenter);
                    //Controllo che la lista generata contenga almeno 1 elemento, altrimenti sollevo eccezione
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while (!check);
            //procedimento analogo alle righe precedenti
            Cittadini person = new Cittadini(VaxCenter.getNomeCentroVaccinale(), VaxCenter.cittadiniRegistrati);
            VaxCenter.cittadiniRegistrati.put(person.getUserid(), person);
            new FileDatiCittadini(person);
            System.out.println("Operazione eseguita con successo \n");
        }
    }

    /**
     * Fornisce una stringa contenente le informazioni principali dell'oggetto Cittadini che esegue il metodo
     * @return una stringa contenente le informazioni di un cittadino
     */
    public String toString() {
        return this.nomeCognome + " \n" + "Codice Fiscale: " + this.codicefiscale + " \n" + "E_mail: " + this.email + " \n"
                + "UserID: " + this.userId + " Password: " + this.password + " \n" + "ID univoco: " + this.id + " \n" + "Luogo di vaccinazione: \n" + this.centroVaccinale + " \n";
    }

    /**
     * Il metodo permette all'utente di inserire una stringa di caratteri e creare una ArrayList nella quale sono contenuti i centri vaccinali nel cui nome e' contenuta la stringa inserita
     * @param ht struttura dati dove sono registrati a sistema i centri vaccinali
     * @param text stringa in input dalla quale verrano selezionati i centri vaccinali contenenti questa stringa
     * @return lista di centri vaccinali la quale contiene i centri vaccinali della tipologia ricercata nel comune ricercato
     */
    public static ArrayList<CentriVaccinali> cercaCentroVaccinale(Hashtable<String, CentriVaccinali> ht, String text) {
        ArrayList<CentriVaccinali> VaxList = new ArrayList<>();
        //in names sono contenute tutte le chiavi di ht, ovvero tutti i nomi dei centri vaccinali
        Enumeration<String> names = ht.keys();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            if (key.contains(text))
                //se il nome di un centro vaccinale contiene i caratteri inseriti nel parametro text allora viene aggiunto in lista
                VaxList.add(ht.get(key));
        }
        return VaxList;
    }

    /**
     * Il metodo permette di creare una ArrayList di centri vaccinali dopo aver inserito come parametri di ricerca il comune e la tipologia di centro vaccinale
     * @param comune Stringa rappresentante il comune dove risiede il centro vaccinale ricercato
     * @param tipologia Stringa rappresentante la tipologia del centro vaccinale ricercato (hub.aziendale.ospedaliero)
     * @return Lista di centri vaccinali la quale contiene i centri vaccinali della tipologia ricercata nel comune ricercato
     */
    public static ArrayList<CentriVaccinali> cercaCentroVaccinale(String comune, String tipologia) {
        ArrayList<CentriVaccinali> VaxList = new ArrayList<>();
        //in base alla tipologia di centro vaccinale ricercato, scorro le liste di centri vaccinali divisi per tipologia e aggiungo in lista tutti i centri vaccinali che hanno tipologia ricercata nel comune ricercato.
        switch (tipologia.toUpperCase()) {
            case "HUB":
                //controllo che la lista non sia vuota
                if(CentriVaccinali.hub.size()==0)
                    break;
                for (CentriVaccinali cv : CentriVaccinali.hub) {
                    //controllo che il centro vaccinale sia nel comune richiesto
                    if (cv.getComune().equals(comune.toUpperCase())) {
                        VaxList.add(cv);
                    }
                }
                break;
            case "OSPEDALIERO":
                //controllo che la lista non sia vuota
                if(CentriVaccinali.ospedaliero.size()==0)
                    break;
                for (CentriVaccinali cv : CentriVaccinali.ospedaliero) {
                    //controllo che il centro vaccinale sia nel comune richiesto
                    if (cv.getComune().equals(comune.toUpperCase())) {
                        VaxList.add(cv);
                    }
                }
                break;
            case "AZIENDALE":
                //controllo che la lista non sia vuota
                if(CentriVaccinali.aziendale.size()==0)
                    break;
                for (CentriVaccinali cv : CentriVaccinali.aziendale) {
                    //controllo che il centro vaccinale sia nel comune richiesto
                    if (cv.getComune().equals(comune.toUpperCase())) {
                        VaxList.add(cv);
                    }
                }
                break;
        }
        return VaxList;
    }

    /**
     * Il metodo permette di confrontare un oggetto di tipo Cittadini con un altro oggetto e stabilisce se sono uguali verificando l'uguaglianza tra i campi user id e password dei due oggetti
     * @param obj oggetto generico
     * @return true se i due oggetti confrontati hanno lo stesso user id e la stessa password
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Cittadini))
            return false;
        Cittadini persona = (Cittadini) obj;
        return (Objects.equals(userId, persona.getUserid()) && Objects.equals(password, persona.getPassword()));

    }
    /**
     * Il metodo permette di ricercare il proprio centro vaccinale dove e' avvenuta la vaccinazione, eseguire il logIn e successivamente inserire e stampare sul file "Vaccinati_NomeCentroVaccinale.dati.txt" l'elenco
     * personalizzato di sintomi comparsi post-vaccinazione. NomeCentroVaccinale viene sostituito dinamicamente in base al nome del centro dove e' avvenuta la vaccinazione
     * @param ht Hashtable che contiene i centri vaccinali
     * @throws IOException se si tenta di accedere al file Vaccinati_NomeCentroVaccinale.dati senza successo
     */
    public static void inserisciEventiAvversi(Hashtable<String, CentriVaccinali> ht) throws IOException {
        Scanner sc = new Scanner(System.in);
        String scelta;
        boolean check;
        CentriVaccinali VaxCenter = null;
        ArrayList<CentriVaccinali> Vaxlist;
        ArrayList<EventiAvversi> ListaEventi;
        System.out.println("Per ricercare il centro vaccinale nel quale si desidera effettuare il log-in, esistono 2 metodi di ricerca \n");
        System.out.println("1: ricerca per comune e tipologia \n");
        System.out.println("2: ricerca per nome \n");
        System.out.println("Inserire 1/2");
        do {
            scelta = sc.next();
            if(!(scelta.equals("1")||scelta.equals("2")))
                System.out.println("Inserire 1/2");
        }while(!(scelta.equals("1")||scelta.equals("2")));

        if (scelta.equals("1")) {
            do {
                System.out.println("Inserire comune");
                String comune = sc.next().toUpperCase().strip();
                System.out.println("Inserire tipologia");
                String tipologia = sc.next().toUpperCase().strip();
                check = true;
                try {
                    //ricerco il centro vaccinale con le caratteristiche inserite
                    Vaxlist = cercaCentroVaccinale(comune, tipologia);
                    //seleziono centro vaccinale desiderato
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while(!check);

            //se logIn approvato in s contenuto codice fiscale
            String s = logIn(VaxCenter.getNomeCentroVaccinale(), ht);

            if (!s.equals("false")) {
                //viene creato elenco personalizzato di eventi avversi
                ListaEventi = EventiAvversi.creazioneElenco();
                //aggiorno campo numeroSegnalazioni dell'oggetto VaxCenter sommando il numero di segnalazioni effetuate dall'utente
                VaxCenter.numeroSegnalazioni += EventiAvversi.contaSegnalazioni(ListaEventi);
                //aggiorno campo sommaSeverita dell'oggetto VaxCenter sommando la severità dei sintomi registrati dall'utente a quelli già segnalati
                VaxCenter.sommaSeverita += EventiAvversi.sommaSeverita(ListaEventi);
                //ottengo l'oggetto vaccinato (che rappresenta la stessa persona dell'oggetto cittadino) tramite il codice fiscale
                Vaccinato vax = VaxCenter.vaccinati.get(s);
                System.out.println(vax);
                String path = PathCittadini.getPathNomeCentroVaccinale(vax);
                Scanner file = new Scanner(new File(path));
                //in questa lista andremo ad inserire tutte le linee presenti nel file situato in memoria dal percorso definito nella variabile path.
                ArrayList<String> list = new ArrayList<>();
                //Aggiungo come prima linea il numero di segnalazioni e la media delle severità aggiornati
                list.add("Numero segnalazioni= " + VaxCenter.numeroSegnalazioni + " somma severità= " + VaxCenter.sommaSeverita + " \n");
                //Avanzo di una linea lo Scanner del file perchè la prima linea inserita è l'aggiornamento delle severità e delle segnalazioni
                file.nextLine();

                //Inserisco in lista tutte le linee del file fino alla riga dalla quale dovremo iniziare a stampare gli eventi avversi appena segnalati dal Cittadino
                for(int i=0;i<vax.linea+3;i++)
                    list.add(file.nextLine() + " \n");

                int count = 0;
                //Aggiungo in lista gli eventi avversi
                for (EventiAvversi ea:ListaEventi){
                    if(count==ListaEventi.size())
                        list.add(ea.toString());
                    else
                        list.add(ea.toString() + " \n");
                    count++;
                }

                //Avanzo lo scanner del file superando le righe bianche dove andremo poi ad aggiungere gli eventi avversi segnalati
                for(int j=0;j<6;j++)
                    file.nextLine();

                //successivamente continuo ad aggiungere in lista le restanti linee presenti nel file
                while (file.hasNextLine())
                    list.add(file.nextLine() + " \n");

                file.close();

                //come ultimo passaggio le informazioni salvate in lista vengono stampate sullo stesso file sovrascrivendolo e quindi aggiornandolo
                FileWriter wr = new FileWriter(path);
                for(String str: list)
                    wr.write(str);
                wr.close();
            }else
                System.out.println("login errato \n");
        }else{
            do {
                System.out.println("Inserire nome centro vaccinale");
                String NameCenter = sc.next().toUpperCase();
                check = true;
                try {
                    Vaxlist = cercaCentroVaccinale(ht, NameCenter);
                    //Controllo che la lista generata contenga almeno 1 elemento, altrimenti sollevo eccezione
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while (!check);

            String s = logIn(VaxCenter.getNomeCentroVaccinale(), ht);
            if (!s.equals("false")) {
                //viene creato elenco personalizzato di eventi avversi
                ListaEventi = EventiAvversi.creazioneElenco();
                //aggiorno campo numeroSegnalazioni dell'oggetto VaxCenter sommando il numero di segnalazioni effetuate dall'utente
                VaxCenter.numeroSegnalazioni += EventiAvversi.contaSegnalazioni(ListaEventi);
                //aggiorno campo sommaSeverita dell'oggetto VaxCenter sommando la severità dei sintomi registrati dall'utente a quelli già segnalati
                VaxCenter.sommaSeverita += EventiAvversi.sommaSeverita(ListaEventi);
                //ottengo l'oggetto vaccinato (che rappresenta la stessa persona dell'oggetto cittadino) tramite il codice fiscale
                Vaccinato vax = VaxCenter.vaccinati.get(s);
                String path = PathCittadini.getPathNomeCentroVaccinale(vax);
                Scanner file = new Scanner(new File(path));
                //in questa lista andremo ad inserire tutte le linee presenti nel file situato in memoria dal percorso definito nella variabile path.
                ArrayList<String> list = new ArrayList<>();
                //Aggiungo come prima linea il numero di segnalazioni e la media delle severità aggiornati
                list.add("Numero segnalazioni= " + VaxCenter.numeroSegnalazioni + " somma severità= " + VaxCenter.sommaSeverita + " \n");
                //Avanzo di una linea lo Scanner del file perchè la prima linea inserita è l'aggiornamento delle severità e delle segnalazioni
                file.nextLine();
                //Inserisco in lista tutte le linee del file fino alla riga dalla quale dovremo iniziare a stampare gli eventi avversi appena segnalati dal Cittadino
                for(int i=0;i<vax.linea+3;i++)
                    list.add(file.nextLine() + " \n");
                int count = 0;

                //Aggiungo in lista gli eventi avversi
                for (EventiAvversi ea:ListaEventi) {
                    if(count==ListaEventi.size())
                        list.add(ea.toString());
                    else
                        list.add(ea.toString() + " \n");

                    count++;
                }

                //Avanzo lo scanner del file superando le righe bianche dove andremo poi ad aggiungere gli eventi avversi segnalati
                for(int j=0;j<6;j++)
                    file.nextLine();

                //successivamente continuo ad aggiungere in lista le restanti linee presenti nel file
                while (file.hasNextLine())
                    list.add(file.nextLine() + " \n");

                file.close();

                //come ultimo passaggio le informazioni salvate in lista vengono stampate sullo stesso file sovrascrivendolo e quindi aggiornandolo
                FileWriter wr = new FileWriter(path);
                for(String str: list)
                    wr.write(str);
                wr.close();
            }else
                System.out.println("login errato \n");
        }
    }

    /**
     * Il metodo permette di verificare se le credenziali di accesso inserite dall'utente corrispondo a quelle registrate a sistema
     * @param NomeCentroVaccinale nome del centro vaccinale in cui e' stato vaccinato il cittadino
     * @param ht Hashtable che contiene i centri vaccinali
     * @return una stringa contenente il codice fiscale del cittadino se il logIn ha avuto esito positivo, altrimenti la stringa "false"
     */
    public static String logIn (String NomeCentroVaccinale, Hashtable <String,CentriVaccinali> ht)  {
        Scanner sc = new Scanner(System.in);

        System.out.println("inserire user id");
        String user = sc.next().strip();
        //limite massimo di tentativi per poter effettuare il logIn
        int tentativi = 5;
        //ottengo l'oggetto CentriVaccinali dalla struttura dati conoscendo la sua chiave, ovvero il nome
        CentriVaccinali VaxCenter = ht.get(NomeCentroVaccinale);
        //se non è presente la chiave user nella struttura dati dei cittadini registrati vuol dire che si sta inserendo un user errato
        if (!VaxCenter.cittadiniRegistrati.containsKey(user)) {
            --tentativi;
            do {
                System.out.println("user inesistente, prego riprovare");
                System.out.println("ha ancora " + tentativi + " tentativi");
                user = sc.next();
                --tentativi;
            } while (tentativi > 0 && !(VaxCenter.cittadiniRegistrati.containsKey(user)));
            //esaurito il numero massimo di tentativi
            if (tentativi <= 0)
                return "false";
        }
        //si hanno 5 tentativi sia per user che per la password
        tentativi = 5;
        System.out.println("Inserire password");
        //cripto la password che andrà ad essere confrontata con la password già criptata salvata nell'oggetto cittadino
        String password = new CifrarioDiCesare().enc(sc.next());
        Cittadini person = VaxCenter.cittadiniRegistrati.get(user);

        if (person.getPassword().equals(password))
            //se la password inserita coincide, ritorno il codice fiscale del cittadino. quest'ultimo serve per le operazioni successive
            return person.getCodicefiscale();
        else {
            --tentativi;
            do {
                System.out.println("informazioni errate, prego riprovare");
                System.out.println("Ha ancora " + tentativi + " tentativi");
                password = sc.next();
                --tentativi;
            } while (tentativi > 0 && !(person.getPassword().equals(password)));

            if (tentativi < 1)
                return "false";
            else
                //i tentativi sono >=1 vuol dire che la password inserita è corretta
                return person.getCodicefiscale();
        }
    }

    /**
     * Il metodo, dopo la ricerca del centro vaccinale desiderato, permette di avere un prospetto riassuntivo delle segnalazioni di eventi avversi riscontrate post-vaccinazione nel centro selezionato.
     *  Per prospetto riassuntivo si intende il numero di segnalazioni effettuate e la media della severita' di sintomi registrati
     * @param ht Hashtable dove sono contenuti i centri vaccinali
     */
    public static void visalizzaInfoCentroVaccinale(Hashtable<String,CentriVaccinali> ht) {
        Scanner sc = new Scanner(System.in);
        String scelta;
        float mediaSeverita;
        boolean check;
        ArrayList<CentriVaccinali> Vaxlist;
        CentriVaccinali VaxCenter = null;
        System.out.println("Per ricercare il centro vaccinale del quale si desidera avere un prospetto riassuntivo, esistono due metodi di ricerca \n");
        System.out.println("1: ricerca per comune e tipologia \n");
        System.out.println("2: ricerca per nome \n");
        System.out.println("Inserire 1/2");
        do {
            scelta = sc.next();
            if (!(scelta.equals("1") || scelta.equals("2")))
                System.out.println("Inserire 1/2");
        } while (!(scelta.equals("1") || scelta.equals("2")));

        if (scelta.equals("1")) {
            do {
                System.out.println("Inserire comune");
                String comune = sc.next().toUpperCase().strip();
                System.out.println("Inserire tipologia");
                String tipologia = sc.next().toUpperCase().strip();
                check = true;
                try {
                    //ricerco il centro vaccinale con le caratteristiche inserite
                    Vaxlist = cercaCentroVaccinale(comune, tipologia);
                    //seleziono centro vaccinale desiderato
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while(!check);
            //calcolo la media delle severità degli eventi avversi registrati
            mediaSeverita = VaxCenter.mediaSeverita();
            //il metodo String.format("%0.1f",mediaSeverita) permette di stampare in uscita il valore con una sola cifra decimale
            System.out.println("Nel centro vaccinale " + VaxCenter.getNomeCentroVaccinale() + " sono state registrate " + VaxCenter.numeroSegnalazioni + " segnalazioni di eventi avversi, con una severità media pari a " + String.format("%.01f", mediaSeverita) + "/5 \n");
            System.out.println();
        } else {
            sc.nextLine();
            do {
                System.out.println("Inserire nome centro vaccinale");
                String NameCenter = sc.nextLine().toUpperCase();
                check = true;
                try {
                    //genero lista ci di centri vaccinali con le caratteristiche inserite
                    Vaxlist = cercaCentroVaccinale(ht, NameCenter);
                    //seleziono centro vaccinale desiderato
                    VaxCenter = CentriVaccinali.getVaxCenter(Vaxlist);
                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("La ricerca non ha evidenziato centri vaccinali con le caratteristiche richieste, prego riprovare \n");
                    check = false;
                }
                catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    System.out.println("Inserire un valore compreso tra i range dell'elenco, prego riprovare \n");
                    check = false;
                }
            }while (!check);
            //calcolo la media delle severità degli eventi avversi registrati
            mediaSeverita = VaxCenter.mediaSeverita();
            //il metodo String.format("%0.1f",mediaSeverita) permette di stampare in uscita il valore con una sola cifra decimale
            System.out.println("Nel centro vaccinale " + VaxCenter.getNomeCentroVaccinale() + " sono state registrate " + VaxCenter.numeroSegnalazioni + " segnalazioni di eventi avversi, con una severità media pari a " + String.format("%.01f", mediaSeverita) + "/5 \n");
            System.out.println();
        }
    }
}






