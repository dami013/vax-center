package cittadini;

    public class CifrarioDiCesare{
        private final int chiave;

        /**
         * questo costruttore permette di creare un oggetto di tipo CifrarioDiCesare, assegnando di default una chiave pari a 19
         */
        public CifrarioDiCesare(){ // creazione costruttore
            chiave = 19;
        }

        /**
         * @param cleartext valore in input da criptare
         * @return valore in input criptato
         */
        public String enc(String cleartext){
            String text=cleartext.toLowerCase(); //porto la stringa minuscola
            char[] charText=text.toCharArray(); //converto la stringa in un array di char

            for(int i=0;i<charText.length;i++){
                if((charText[i]>='a' && charText[i]<='z')) //il valore è compreso tra a=97 e z = 122
                    charText[i]=(int) charText[i]+chiave%26>'z'?(char)('a'+charText[i]-('z'+1)+chiave%26):(char)(charText[i]+chiave%26); //? true:false
                //t=116 chiave=10  116+10>122   97+116 -123+10

            }
            return new String(charText);
        }
    }
