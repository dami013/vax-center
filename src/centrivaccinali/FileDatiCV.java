package centrivaccinali;
import java.io.*;

/**
 * <p> La classe FileDatiCV permette di verificare l'esistenza del file "Vaccinati_NomeCentroVaccinale.dati.txt" e  nel caso in cui il file non fosse presente lo crea.
 *
 * inoltre permette di stampare sul file "Vaccinati_NomeCentroVaccinale.dati" le informazioni contenute nell'oggetto Vaccinato. NomeCentroVaccinale viene sostituito dinamicamente dal nome
 * del centro vaccinale dove e' avvenuta la vaccinazione. </p>
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 * @author Simone Torno
 */


public class FileDatiCV {

    /**
     * il metodo verifica la presenza del file dove si andra' a stampare le informazioni e nel caso non ci fosse lo crea, successivamente tramite la funzione FaseScritturaDatiCV stampa
     * le informazioni del vaccinato nel file scelto
     * @param FN oggetto di tipo vaccinato
     */
    public FileDatiCV(Vaccinato FN){
        String pathDatiCV = Path.getPathNomeCentroVaccinale(FN);
        try {
            File FileCV = new File(pathDatiCV);
            if (FileCV.exists()) {
                FaseScritturaDatiCV(FN);

            } else {
                FileCV.createNewFile();
                FileWriter fw = new FileWriter(pathDatiCV, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                out.write("Numero segnalazioni= 0 somma severità= 0 \n");
                out.flush();
                FaseScritturaDatiCV(FN);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di stampare su file nominato "Vaccinati_NomeCentroVaccinale.dati" le informazioni contenute nell'oggetto vaccinato. NomeCentrovaccinale viene sostituito dinamicamente dal nome
     * del centro vaccinale dove e' avvenuta la vaccinazione.
     * @param FN oggetto di tipo vaccinato
     */
    public static void FaseScritturaDatiCV(Vaccinato FN){
        String pathDatiCV = Path.getPathNomeCentroVaccinale(FN);
        //append = true permette di non sovrascrivere ogni volta il file
        try (FileWriter fw = new FileWriter(pathDatiCV, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
        {
            out.write(FN.toString());
            // vengono stampate 6 righe bianche perchè quando il cittadino post-vaccinazione andrà a registrarsi potrà inserire gli eventi avversi da lui notati in questi 7 spazi bianchi
            out.write("\n ");
            out.write("\n ");
            out.write("\n ");
            out.write("\n ");
            out.write("\n ");
            out.write("\n");
            //linea di separazione tra un vaccinato e l'altro
            out.write("------------------------------------------------------------------------------------------- "+ "\n");
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}



