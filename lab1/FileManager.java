package lab1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 * Created by david on 25/09/2016.
 */
public class FileManager {

    private static FileManager myFileManager;

    private FileManager(){

    }

    public static FileManager getMyFileManager(){
        if(myFileManager==null){
            myFileManager= new FileManager();
        }
        return myFileManager;
    }
    
    public static int countLines(File pFile) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(pFile));
    try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}
    
    public static int countLines2(File pFile) throws IOException{
        LineNumberReader  lnr = new LineNumberReader(new FileReader(pFile));
        lnr.skip(Long.MAX_VALUE);
        lnr.close();
        return lnr.getLineNumber()+1;//Add 1 because line index starts at 0
    }


    public void readFile(FileReader pFile) {
        Scanner scanner = new Scanner(pFile);
        String[] auxLine2;
        String line;

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            auxLine2 = line.split("\\s+\\--->+\\s");

            String filmName = auxLine2[0];
            String[] auxLine1 = Arrays.copyOfRange(auxLine2, 1, auxLine2.length);
            String auxLine1Joined = String.join("", auxLine1);
            String[] auxLine3 = auxLine1Joined.split("\\s+\\&&&+\\s");

            this.normalizeString(filmName);
            Film auxFilm = new Film(filmName);

            FilmCatalog.getMyFilmCatalog().addFilm(auxFilm);

            int i = 0;//i equivale a 1. Entrara en la lista de peliculas. Donde al menos habra 1.
            int percent = 0;

            percent++;

            while (auxLine3.length > i) { // mientras el indice no sea mayor que el tamaño de la lista(indexOutOfBoundException)

                String actorName = this.normalizeString(auxLine3[i]);//Normalizamos el nombre del actor
                String actorSurname = null;

                if (actorName.indexOf("(") != -1) {
                    auxLine2 = actorName.split("\\s\\(");
                    actorName = auxLine2[0];
                }
                if (actorName.indexOf(",") != -1) {//convertimos -> Apellido, Nombre --> Nombre, Apellido (como es habitual)
                    auxLine2 = actorName.split(",\\s*");
                    actorName = auxLine2[1];
                    actorSurname = auxLine2[0];
                }

                Actor auxActor = new Actor(actorName, actorSurname);//Creamos la pelicula enviandole el nombre una vez normalizado

                auxActor.getFilmList().addFilm(auxFilm);

                ActorCatalog.getmyActorCatalog().addActor(auxActor);
                FilmCatalog.getMyFilmCatalog().getFilm(auxFilm.getName()).getActorList().addActor(auxActor);

                i++;
                percent+=i;
            }
            final int percent1 = percent;
            JMenu.getMyJMenu().updateBar(percent1);


        }
        System.out.println("-------- File read finished --------");
        scanner.close();
    }

    @SuppressWarnings("rawtypes")
    public void exportToFile() {
        Object[] keys = ActorCatalog.getmyActorCatalog().getActorL().keySet().toArray();
        Arrays.sort(keys);

        FileWriter fichero = null;
        PrintWriter pw = null;


        try {
            String directorio = System.getProperty("user.dir");//cogemos variable entorno
            fichero = new FileWriter(directorio + "/ActorList_ordered.txt");
            pw = new PrintWriter(fichero);

            for(Object key : keys){
                pw.print("Actor -> ");
                pw.println(ActorCatalog.getmyActorCatalog().getActorL().get(key).getName()+ " " + ActorCatalog.getmyActorCatalog().getActorL().get(key).getSurname());
                Object[] keys2 = ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().keySet().toArray();
                for(Object auxKey : keys2) {
                    //pw.print("Film ->");
                    pw.println("\t"+ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().get(auxKey).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
                System.out.println("File exported to: "+System.getProperty("user.dir"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    private String normalizeString(String pWord){

        pWord = pWord.replaceAll("Ã¡","a");
        pWord = pWord.replaceAll("Ã©","e");
        pWord = pWord.replaceAll("Ã­","i");
        pWord = pWord.replaceAll("Ã³","o");
        pWord = pWord.replaceAll("Ãº","u");
        pWord = pWord.replaceAll("Ã�","A");
        pWord = pWord.replaceAll("Ã‰","E");
        pWord = pWord.replaceAll("Ã�","I");
        pWord = pWord.replaceAll("Ã“","O");
        pWord = pWord.replaceAll("Ãš","U");
        pWord = pWord.replaceAll("Ã¤","a");
        pWord = pWord.replaceAll("Ã«","e");
        pWord = pWord.replaceAll("Ã¯","i");
        pWord = pWord.replaceAll("Ã¶","o");
        pWord = pWord.replaceAll("Ã¼","u");
        pWord = pWord.replaceAll("Ã„","A");
        pWord = pWord.replaceAll("Ã‹","E");
        pWord = pWord.replaceAll("Ã�","I");
        pWord = pWord.replaceAll("Ã–","O");
        pWord = pWord.replaceAll("Ãœ","U");
        pWord = pWord.replaceAll("Ã§","c");
        pWord = pWord.replaceAll("Ã ","a");
        pWord = pWord.replaceAll("Ã¨","e");
        pWord = pWord.replaceAll("Ã¬","i");
        pWord = pWord.replaceAll("Ã²","o");
        pWord = pWord.replaceAll("Ã¹","u");
        pWord = pWord.replaceAll("Ã€","A");
        pWord = pWord.replaceAll("Ãˆ","E");
        pWord = pWord.replaceAll("ÃŒ","I");
        pWord = pWord.replaceAll("Ã’","O");
        pWord = pWord.replaceAll("Ã™","U");
        pWord = pWord.replaceAll("Ã§","c");
        pWord = pWord.replaceAll("Ã‡","C");
        pWord = pWord.replaceAll("�","A");
        return pWord;
    }



}
