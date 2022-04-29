package cittadini;
import java.io.*;

/**
 *<p> La classe "FileDatiCittadini" verifica se il file "Cittadini_Registrati.dati.txt" esiste, altrimenti ne crea uno nuovo.
 * Successivamente stampa sul file "Cittadini_Registrati.dati.txt" i cittadini registrati in uno specifico centro vaccinale </p>
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 */

public class FileDatiCittadini{

    /**
     *il metodo verifica la presenza del file dove si andra' a stampare le informazioni e nel caso non ci fosse lo crea, successivamente tramite la funzione FaseScritturaCittadini() stampa le inforazioni del cittadino
     * @param person oggetto di tipo cittadino contenente i dati che si desiderano stampare su file
     */
    protected FileDatiCittadini(Cittadini person){
        String pathCittadini = PathCittadini.getPathCittadini();
        try {
            File fileCittadino = new File(pathCittadini);
            if (fileCittadino.exists()) {
                FaseScritturaCittadini(person);
            } else {
                fileCittadino.createNewFile();
                FaseScritturaCittadini(person);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * il metodo permette di stampare sul file Cittadini_Registrati.dati.txt i cittadini registrati in un centro vaccinale
     * @param person oggetto di tipo Cittadini
     */
    protected void FaseScritturaCittadini(Cittadini person){
        String pathCittadini = PathCittadini.getPathCittadini();
        //append = true permette di non sovrascrivere ogni volta il file
        try (FileWriter fw = new FileWriter(pathCittadini, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
        {
            out.write(person.toString());
            //linea di separazione tra un cittadino e l'altro
            out.write("-------------------------------------------------------------------------------------------"+ " \n");
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

