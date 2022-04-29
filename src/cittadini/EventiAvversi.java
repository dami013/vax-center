package cittadini;
import java.util.*;
/**
 * La classe "EventiAvversi" permette di creare un elenco di domande che vengono poste al cittadino registrato, con scala 1 a 5 di severita', in cui vengono
 * poste domande inerenti ai sintomi post vaccinazione.
 *
 * @author Alessandro Cassani
 */

public class EventiAvversi {

    /**
     * <code> severita </code> numero da 1 a 5 che rappresenta la severita' con cui si sono presentati sintomi avversi
     */
    public String severita = "";
    /**
     * <code> note </code> note opzionali che possono essere inserite dall'utente
     */
    public String note = "";
    /**
     * <code> nome </code> nome del'evento avverso
     */
    public String nome;

    /**
     * Il costruttore permette la creazione di un oggetto di tipo EventiAvversi
     * @param nome Stringa rappresentante il nome del sintomo
     */
    protected EventiAvversi(String nome)
    {
        this.nome = nome;
    }

    /**
     * Il metodo permette al cittadino registrato di creare un elenco personalizzato di eventi avversi comparsi post-vaccinazione. E' possibile inserire la severita' dei sintomi e anche delle note opzionali (max 256 caratteri).
     * @return ArrayList di tipo EventiAvversi contenente tutti gli eventi avversi segnalati dal cittadino. quando l'evento non si e' verificato, viene comunque inserito l'oggetto contenente la stringa "non noto"
     */
    protected static ArrayList<EventiAvversi> creazioneElenco() {
        ArrayList<EventiAvversi> sintomo = new ArrayList<>();


        sintomo.add(new EventiAvversi("mal di testa"));
        sintomo.add(new EventiAvversi("febbre"));
        sintomo.add(new EventiAvversi("Dolori articolari e muscolari"));
        sintomo.add(new EventiAvversi("Linfoadenopatia"));
        sintomo.add(new EventiAvversi("Tachicardia"));
        sintomo.add(new EventiAvversi("Crisi ipertensiva"));
        String risposta, risposta2;
        Scanner sc = new Scanner(System.in);
        sc.reset();

        for (EventiAvversi ea : sintomo) {
            System.out.println("ha avuto " + ea.nome + "?");
            do {
                System.out.println("rispondere sì/no");
                risposta = sc.next().toLowerCase().strip();
            } while (!(risposta.equals("si") || risposta.equals("sì") || risposta.equals("no")));
            sc.nextLine();

            if (risposta.equals("si") || risposta.equals("sì")) {
                do {
                    System.out.println("Inserire severità da 1 a 5");
                    ea.severita = sc.nextLine().strip();
                }while (!(ea.severita.equals("1")||ea.severita.equals("2")||ea.severita.equals("3")||ea.severita.equals("4")||ea.severita.equals("5")));

                System.out.println("Desidera inserire anche delle note opzionali? (max 256 caratteri)");
                System.out.println("Inserire sì/no");
                do {
                    risposta2 = sc.next().toLowerCase().strip();
                    if(!((risposta2.equals("si".strip()) || risposta2.equals("sì".strip()) || risposta2.equals("no".strip()))))
                        System.out.println("Inserire sì/no");
                } while (!((risposta2.equals("si") || risposta2.equals("sì") || risposta2.equals("no"))));

                if (risposta2.equals("si") || risposta2.equals("sì")) {
                    do {
                        System.out.println("prego inserire note opzionali");
                        //permette di "pulire" il buffer
                        sc.nextLine();
                        ea.note = sc.nextLine();
                        if(ea.note.length()>256)
                            System.out.println("limite massimo di caratteri superato");
                    } while (ea.note.length() > 256); //Controllo che le note inserite siano composte al massimo da 256 caratteri
                }
            }
        }
        return sintomo;
    }

    /**
     * Il metodo permette di contare il numero di segnalazioni effettuate nell'elenco personalizzato di eventi avversi comparsi post-vaccinazione
     * @param sintomi lista di sintomi segnalati dal cittadino post-vaccinazione
     * @return il numero di segnalazioni, e quindi di sintomi, presentatisi al cittadino
     */
    protected static int contaSegnalazioni(ArrayList<EventiAvversi> sintomi){
        int numeroSegnalazioni = 0;
        for (EventiAvversi ev:sintomi) {
            // Se stringa vuota vuol dire che il cittadino non ha riscontrato il sintomo in questione, perchè il campo severita è rimasto al valore iniziale
            if(!ev.severita.equals(""))
                numeroSegnalazioni++;
        }
        return numeroSegnalazioni;
    }

    /**
     * Il metodo permette di sommare tutte le severita' registrate nell'elenco personalizzato di sintomi post-vaccinazione
     * @param sintomi lista di sintomi segnalati dal cittadino post-vaccinazione
     * @return la somma delle severita' con cui i sintomi post-vaccinazione si sono presentati
     */
    protected static int sommaSeverita(ArrayList<EventiAvversi> sintomi){
        int sommaSeverita = 0;
        for (EventiAvversi ev : sintomi) {
            // Se stringa vuota vuol dire che il cittadino non ha riscontrato il sintomo in questione, perchè il campo severita è rimasto al valore iniziale
            if (!ev.severita.equals(""))
                sommaSeverita += Integer.parseInt(ev.severita);
        }
        return sommaSeverita;
    }

    /**
     * Genera una stringa contenente le informazioni di un oggetto EventiAvversi
     * @return stringa contenente le informazioni dell'oggetto EventiAvversi
     */
    public String toString() {
        String s;
        s =  this.nome + ":";
        //i campi "note" e "severità" sono inizializzati con "" di conseguenza se dopo la creazione dell'elenco personalizzato di eventi avversi quest'ultimi sono ancora uguali al valore di partenza
        //significa che quello specifico sintomo non è stato segnalato
        if(!this.severita.equals("")){
            s += " evento verificatosi con severità pari a " + this.severita +". ";
            if(!this.note.equals(""))
                return s + " note opzionali: " + this.note;
            else
                return s;
        }
        return s + " non noto ";
    }
}


