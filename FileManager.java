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

    private FileManager() {

    }

    public static FileManager getMyFileManager() {
        if (myFileManager == null) {
            myFileManager = new FileManager();
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

    public static int countLines2(File pFile) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(pFile));
        lnr.skip(Long.MAX_VALUE);
        lnr.close();
        return lnr.getLineNumber() + 1;//Add 1 because line index starts at 0
    }


    public void readFile() throws IOException {

        String[] auxLine2;
        String line;

        FileInputStream inputStream = null;
        Scanner scanner = null;

        int j=0;

        try {

            inputStream = new FileInputStream("D:/ProjectosJava/EDA16-17/src/lab1/test80000lines.txt");
            int contLines = countLines2(new File("D:/ProjectosJava/EDA16-17/src/lab1/test80000lines.txt"));
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNext()) {
                line = scanner.nextLine(); //scanner of lines
                auxLine2 = line.split("\\s+\\--->+\\s"); //we split to get the name of the movie
                NormalizeStrings.getMyNormalizeString().run(auxLine2);

                String filmName = auxLine2[0]; //here we save the name of the film

                String[] auxLine1 = Arrays.copyOfRange(auxLine2, 1, auxLine2.length); //array containing the name of the actors
                String auxLine1Joined = String.join("", auxLine1); //we convert the array to an string

                String[] auxLine3 = auxLine1Joined.split("\\s+\\&&&+\\s"); //we split the array of actors in

                //this.normalizeString(filmName); //we use the method that we have written
                Film auxFilm = new Film(filmName); //create a new film
                FilmCatalog.getMyFilmCatalog().addFilm(auxFilm); //we add the film if its not been added before

                int i = 0;

                NormalizeStrings.getMyNormalizeString().run(auxLine3);

                while (auxLine3.length > i) { // mientras el indice no sea mayor que el tamaño de la lista(indexOutOfBoundException)

                    //String actorName = this.normalizeString(auxLine3[i]);//Normalizamos el nombre del actor

                    String actorSurname = new String("");
                    String actorName = auxLine3[i];

                    if (actorName.indexOf("(") != -1) {
                        auxLine2 = actorName.split("\\s\\(");
                        actorName = auxLine2[0];
                    }
                    if (actorName.indexOf(",") != -1) {//convertimos -> Apellido, Nombre --> Nombre, Apellido (como es habitual)
                        auxLine2 = actorName.split(",\\s*");
                        if (auxLine2.length > 1) {
                            if (auxLine2[1].compareToIgnoreCase("null") != 0) {
                                actorSurname = auxLine2[0];
                                actorName = auxLine2[1];
                            } else {
                                actorName = auxLine2[0];
                            }
                        }
                    }
                    Actor auxActor = new Actor(actorName, actorSurname);//Creamos la pelicula enviandole el nombre una vez normalizado

                    auxActor.getFilmList().addFilm(auxFilm);
                    ActorCatalog.getmyActorCatalog().addActor(auxActor);
                    FilmCatalog.getMyFilmCatalog().getFilm(auxFilm.getName()).getActorList().addActor(auxActor);

                    i++;

                }
                j++;
                if (j % 2500 == 0) {
                    if (((j*100)/contLines) % 10 == 0){
                        System.out.println((j*100)/contLines);
                    }else if (((j*100)/contLines) % 5 == 0){
                        JMenu.getMyJMenu().updateBar((j*100)/contLines);
                    }
                }
                if (scanner.ioException() != null) {
                    throw scanner.ioException();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
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
            for (Object key : keys) {
                pw.print("Actor " + i + " -> ");
                pw.println(ActorCatalog.getmyActorCatalog().getActorL().get(key).getName() + " " + ActorCatalog.getmyActorCatalog().getActorL().get(key).getSurname());
                Object[] keys2 = ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().keySet().toArray();
                for (Object auxKey : keys2) {
                    pw.println("\t" + ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().get(auxKey).getName());
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
                System.out.println("\nFile exported to: " + System.getProperty("user.dir"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    public void readFile2() throws IOException{

        FileReader in = new FileReader("D:/ProjectosJava/EDA16-17/src/lab1/test80000lines.txt");
        BufferedReader br = new BufferedReader(in);
        String line = null;
        String[] auxLine2 = null;
        int j = 0;
        Scanner scanner = new Scanner(System.in);
        int contLines = countLines2(new File("D:/ProjectosJava/EDA16-17/src/lab1/test80000lines.txt"));

        while ((line = br.readLine()) != null) {
            auxLine2 = line.split("\\s+\\--->+\\s"); //we split to get the name of the movie
            NormalizeStrings.getMyNormalizeString().run(auxLine2);

            String filmName = auxLine2[0]; //here we save the name of the film

            String[] auxLine1 = Arrays.copyOfRange(auxLine2, 1, auxLine2.length); //array containing the name of the actors
            String auxLine1Joined = String.join("", auxLine1); //we convert the array to an string

            String[] auxLine3 = auxLine1Joined.split("\\s+\\&&&+\\s"); //we split the array of actors in

            //this.normalizeString(filmName); //we use the method that we have written
            Film auxFilm = new Film(filmName); //create a new film
            FilmCatalog.getMyFilmCatalog().addFilm(auxFilm); //we add the film if its not been added before

            int i = 0;

            NormalizeStrings.getMyNormalizeString().run(auxLine3);

            while (auxLine3.length > i) { // mientras el indice no sea mayor que el tamaño de la lista(indexOutOfBoundException)

                //String actorName = this.normalizeString(auxLine3[i]);//Normalizamos el nombre del actor

                String actorSurname = new String("");
                String actorName = auxLine3[i];
                if (actorName.indexOf("(") != -1) {
                    auxLine2 = actorName.split("\\s\\(");
                    actorName = auxLine2[0];
                }
                if (actorName.indexOf(",") != -1) {//convertimos -> Apellido, Nombre --> Nombre, Apellido (como es habitual)
                    auxLine2 = actorName.split(",\\s*");
                    if (auxLine2.length > 1) {
                        if (auxLine2[1].compareToIgnoreCase("null") != 0) {
                            actorSurname = auxLine2[0];
                            actorName = auxLine2[1];
                        } else {
                            actorName = auxLine2[0];
                        }
                    }
                }
                Actor auxActor = new Actor(actorName, actorSurname);//Creamos la pelicula enviandole el nombre una vez normalizado

                auxActor.getFilmList().addFilm(auxFilm);
                ActorCatalog.getmyActorCatalog().addActor(auxActor);
                FilmCatalog.getMyFilmCatalog().getFilm(auxFilm.getName()).getActorList().addActor(auxActor);

                i++;
            }

                j++;
                if (j % 2500 == 0) {
                    if (((j*100)/contLines) % 10 == 0){
                        System.out.println((j*100)/contLines);
                    }else if (((j*100)/contLines) % 5 == 0){
                        JMenu.getMyJMenu().updateBar((j*100)/contLines);
                    }
                }
                if (scanner.ioException() != null) {
                    throw scanner.ioException();
                }
            }

        }

}
