package centrivaccinali;

import cittadini.Cittadini;
import java.io.*;
import java.util.*;

/**
 * La classe Main e' considerata la Home del programma
 * In questa classe vengo richiamati tutti i metodi per il funzionamento completo del programma che verra' eseguito
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 */
public class Main{

    public static void main(String[] args) throws IOException, InterruptedException {

        //Predisposizione strumenti
        Scanner sc = new Scanner(System.in);
        //La capacità iniziale di ht è 2834 perchè il numero di centri vaccinali presenti il 5/09/2021 è pari a 2841, il fattore di carico è a 0,9 perchè visto che non sono possibili collisioni ht viene riempita il più possibile
        Hashtable<String, CentriVaccinali> ht = new Hashtable<>(2841, 0.9f);
        //caricamento di tutti i dati presenti all'interno del Data
        Main.caricamentoDati(ht);
        //Inizio Applicazione e avvio della grafica iniziale
        System.out.println();

        //create crea la cornice iniziale del programma
        CreazioneCornice.create("Progetto Lab - A    CENTRI VACCINALI");
        //sleep delay utilizzato per ritardare la stampa su schermo di determinate informazioni
        Thread.sleep(200);
        //Sposto verso il centro del programma la scritta iniziale
        System.out.println("\n\t\tBenvenuti nel Portale dei CENTRI VACCINALI!");
        Thread.sleep(200);
        System.out.println("________________________________________________________________________");
        Thread.sleep(200);
        System.out.println("Buongiorno...\n");
        Thread.sleep(200);
        //Viene chiesto di selezionare se si è un Cittadino oppure un Operatore vaccinale

        String tipologia;
        boolean termina = false;
        do {
            //verifico che il numero inserito dall'operatore vaccinale rientri nei range mostrati nelle scelte del programma
            do {
                System.out.println("É un OPERATORE VACCINALE o un CITTADINO? : \n");
                System.out.println("1: OPERATORE VACCINALE");
                System.out.println("2: CITTADINO");
                System.out.println("3: ESCI \n");
                System.out.println("Inserire 1/2/3");
                tipologia = sc.next().strip();

                if(!(tipologia.equals("1") || tipologia.equals("2") || (tipologia.equals("3"))))
                    System.out.println("Valore inserito non valido, prego riprovare \n");

            } while (!(tipologia.equals("1") || tipologia.equals("2") || (tipologia.equals("3"))));

            System.out.println("________________________________________________________________________");

            boolean exit = false;
            String seleUno;
            switch (tipologia.toUpperCase()) {
                case "1":
                    do {
                        //verifico che il numero inserito dall'operatore vaccinale rientri nei range mostrati nelle scelte del programma
                        do {
                            System.out.println("Inserire il valore associato alla funzionalità che si desidera utilizzare:\n");
                            System.out.println("1: Registrare un nuovo Centro Vaccinale ");
                            System.out.println("2: Registrare un nuovo Vaccinato ");
                            System.out.println("3: Torna alla Home");
                            seleUno = sc.next().strip();

                            if(!(seleUno.equals("1") || seleUno.equals("2") || (seleUno.equals("3"))))
                                System.out.println("Valore inserito non valido, prego riprovare \n");

                        }while (!(seleUno.equals("1") || seleUno.equals("2") || (seleUno.equals("3"))));

                        switch (seleUno) {
                            case "1":
                                //Permette all'operatore vaccinale di registrare un nuovo centro vaccinale
                                CentriVaccinali.registraCentroVaccinale(ht);
                                break;
                            case "2":
                                //Permette all'operatore vaccinale di registrare un nuovo vaccinato
                                Vaccinato.registraVaccinato(ht);
                                break;
                            case "3":
                                // se il numero è uguale a 3, stampa a schermo e termina il programma
                                Thread.sleep(200);
                                exit = true;
                                break;
                            default:
                                //Se il numero non è compreso nelle scelte mostrate, stampa a schermo e permette di reinserire il numero all'utente
                                System.out.println("Numero non valido, riprovare!\n");
                        }
                    } while (!exit);

                    System.out.println("________________________________________________________________________");
                    System.out.println("Caricamento Dati... ");
                    Thread.sleep(200);
                    break;

                case "2":
                    boolean uscita = false;
                    String seleDue;
                    do {
                        do {
                            System.out.println("Inserire il valore associato alla funzionalità che si desidera utilizzare: \n");
                            System.out.println("1: Visualizzare le Informazioni di un Centro Vaccinale ");
                            System.out.println("2: Registrarsi presso un Centro Vaccinale ");
                            System.out.println("3: Inserire segnalazioni di eventi avversi alla vaccinazione ");
                            System.out.println("4: Torna alla Home");

                            //verifico che il numero inserito dal cittadino rientri nei range mostrati nelle scelte del programma
                            seleDue = sc.next().strip();

                        }while (!(seleDue.equals("1") || seleDue.equals("2") || (seleDue.equals("3") || seleDue.equals("4"))));

                        switch (seleDue) {
                            case "1":
                                //permette di visualizzare le informazioni del centro vaccinale selezionato
                                Cittadini.visalizzaInfoCentroVaccinale(ht);
                                break;

                            case "2"://permette al cittadino di registrarsi presso un centro vaccinale
                                Cittadini.registraCittadino(ht);
                                break;

                            case "3"://Permette al cittadino di inserire gli eventi avversi avvenuti dopo la somministrazione del vaccino
                                Cittadini.inserisciEventiAvversi(ht);
                                break;

                            case "4":// se il numero è uguale a 4, stampa a schermo e termina il programma
                                Thread.sleep(200);
                                uscita = true;
                                break;

                            default://Se il numero non è compreso nelle scelte mostrate, stampa a schermo e permette di reinserire il numero all'utente
                                    System.out.println("Numero non valido, riprovare! \n");
                        }
                    } while (!uscita);

                    System.out.println("________________________________________________________________________");
                    System.out.println("Caricamento Dati ... ");
                    Thread.sleep(200);
                    break;

                case "3":
                    Thread.sleep(200);
                    System.out.println("Grazie e Arrivederci!");
                    termina = true;
                    break;
            }
        } while (!termina);
    }

    /**
     * Il metodo permette di caricare nelle strutture dati designate le informazioni precedentemente stampate sui file .txt
     * @param ht Hashtable dove sono registrati i centri vaccinali
     * @throws IOException nel caso il tentativo di accedere ad un file avesse esito negativo
     */
    private static void caricamentoDati( Hashtable<String, CentriVaccinali> ht ) throws IOException{
        //Carica tutti i dati presenti nelle strutture dati designate
        final String pathcv = Path.getPathCentriVaccinali();

        if(CentriVaccinali.controllocaricamento(pathcv)){
            CentriVaccinali.CaricaDaticv(ht);
            CentriVaccinali.prospettoRiassuntivo(ht);}

        Vaccinato.caricaDativaccinati(ht);

        if(Cittadini.controllocaricamento())
            Cittadini.caricadaticittadini(ht);
    }
}


