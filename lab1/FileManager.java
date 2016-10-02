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
            line = scanner.nextLine(); //scanner of lines
            auxLine2 = line.split("\\s+\\--->+\\s"); //we split to get the name of the movie

            String filmName = auxLine2[0]; //here we save the name of the film
            String[] auxLine1 = Arrays.copyOfRange(auxLine2, 1, auxLine2.length); //array containing the name of the actors
            String auxLine1Joined = String.join("", auxLine1); //we convert the array to an string
            String[] auxLine3 = auxLine1Joined.split("\\s+\\&&&+\\s"); //we split the array of actors in

            this.normalizeString(filmName); //we use the method that we have written
            Film auxFilm = new Film(filmName); //create a new film

            FilmCatalog.getMyFilmCatalog().addFilm(auxFilm); //we add the film if its not been added before

            int i = 0;
            int percent = 0;

            percent++;

            while (auxLine3.length > i) { // mientras el indice no sea mayor que el tamaño de la lista(indexOutOfBoundException)

                String actorName = this.normalizeString(auxLine3[i]);//Normalizamos el nombre del actor
                String actorSurname = new String("");

                if (actorName.indexOf("(") != -1) {
                    auxLine2 = actorName.split("\\s\\(");
                    actorName = auxLine2[0];
                }
                if (actorName.indexOf(",") != -1) {//convertimos -> Apellido, Nombre --> Nombre, Apellido (como es habitual)
                    auxLine2 = actorName.split(",\\s*");
                    try{
                        if (auxLine2[1].compareToIgnoreCase("null") != 0){
                            actorSurname = auxLine2[0];
                            actorName = auxLine2[1];
                        }else{
                            actorName = auxLine2[0];
                        }
                    }catch (ArrayIndexOutOfBoundsException e1){
                        e1.printStackTrace();
                    }
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
        String[] keys = ActorCatalog.getmyActorCatalog().quickSortList();

        FileWriter fichero = null;
        PrintWriter pw;

        try {
            String directorio = System.getProperty("user.dir");//cogemos variable entorno
            fichero = new FileWriter(directorio + "/ActorList_ordered.txt");
            pw = new PrintWriter(fichero);

            int i = 1;
            for(Object key : keys){
                pw.print("Actor "+i+" -> ");
                pw.println(ActorCatalog.getmyActorCatalog().getActorL().get(key).getName()+ " " + ActorCatalog.getmyActorCatalog().getActorL().get(key).getSurname());
                Object[] keys2 = ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().keySet().toArray();
                for(Object auxKey : keys2) {
                    pw.println("\t"+ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().get(auxKey).getName());
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
                System.out.println("\nFile exported to: "+System.getProperty("user.dir"));
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
