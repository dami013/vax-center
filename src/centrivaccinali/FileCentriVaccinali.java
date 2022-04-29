package centrivaccinali;

import java.io.*;

/**
 * <p> Dopo aver controllato l'esistenza del file "CentriVaccinali.dati.txt", la classe "FileCentriVaccinali" permette di stampare su file  il centro vaccinale inserito
 * inoltre permette di stampare sul file "CentriVaccinali.dati.txt" le informazioni contenute nell'oggetto Centrivaccinali.</p>
 *
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 * @author Simone Torno
 */

public class FileCentriVaccinali{

    /**
     * Il metodo verifica la presenza del file dove si andra' a stampare le informazioni e nel caso non ci fosse lo crea, successivamente tramite la funzione FaseScritturaCV stampa
     * le informazioni sul file CentriVaccinali.dati.txt
     * @param cv oggetto di tipo Centrivaccinali
     */
    public FileCentriVaccinali(CentriVaccinali cv){
        String path = Path.getPathCentriVaccinali();
        try {
            File FileCV = new File(path);
            if (FileCV.exists()){
                FaseScritturaCV(cv);
            }
            else {
                FileCV.createNewFile();
                FaseScritturaCV(cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo permette di stampare su file nominato "CentriVaccinali.dati.txt" le informazioni contenute nell'oggetto Centrivaccinali.
     * @param cv oggetto di tipo Centrivaccinali
     */
    public void FaseScritturaCV(CentriVaccinali cv){

        String path = Path.getPathCentriVaccinali();
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
        {
            out.write(cv.toString());
            out.write("-------------------------------------------------------------------------------------------"+ " \n");
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}


