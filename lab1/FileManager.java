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
        LineNumberReader lnr = new LineNumberReader(new FileReader(pFile));
        lnr.skip(Long.MAX_VALUE);
        lnr.close();
        return lnr.getLineNumber() + 1;//Add 1 because line index starts at 0
    }


    public void readFile(int pOption) throws IOException {

        String[] auxLine2;
        String line;

        FileReader in = null;
        BufferedReader br = null;

        long startTime = System.currentTimeMillis();

        int j = 0; //j= counts the actual line of file
        int total = 0; //total = saves total running time of reading
        int auxCont = 0;  //auxCount = saves the percentage of reading of file

        try {
            in = new FileReader("/Users/Josu/IdeaProjects/EDA16-17/src/lab1/testAllActors.txt");
            br = new BufferedReader(in);
            int contLines = countLines(new File("/Users/Josu/IdeaProjects/EDA16-17/src/lab1/testAllActors.txt"));

            String filmName, auxLine1Joined, actorName, actorSurname;

            String[] auxLine1, auxLine3;

            Film auxFilm;

            while ((line = br.readLine()) != null) {

                auxLine2 = line.split("\\s+\\--->+\\s"); //we split to get the name of the movie

                if (pOption == 1) {
                    NormalizeStrings.getMyNormalizeString().run(auxLine2);
                }

                filmName = auxLine2[0]; //here we save the name of the film

                auxLine1 = Arrays.copyOfRange(auxLine2, 1, auxLine2.length); //array containing the name of the actors
                auxLine1Joined = String.join("", (CharSequence[]) auxLine1); //we convert the array to an string

                auxLine3 = auxLine1Joined.split("\\s+\\&&&+\\s"); //we split the array of actors in

                auxFilm = new Film(filmName); //create a new film

                if (pOption == 1) {
                    if (!auxFilm.getName().contains("�")) {
                        FilmCatalog.getMyFilmCatalog().addFilm(auxFilm); //we add the film if its not been added before
                    }
                } else {
                    FilmCatalog.getMyFilmCatalog().addFilm(auxFilm); //we add the film if its not been added before
                }


                int i = 0;

                while (auxLine3.length > i) { // mientras el indice no sea mayor que el tamaño de la lista(indexOutOfBoundException)

                    actorSurname = "";
                    actorName = auxLine3[i];

                    if (actorName.contains("(")) {
                        auxLine2 = actorName.split("\\s\\(");
                        actorName = auxLine2[0];
                    }
                    if (actorName.contains(",")) {//convertimos -> Apellido, Nombre --> Nombre, Apellido (como es habitual)
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

                    if (pOption == 1) {
                        if (!auxActor.getName().contains("�")) {
                            if (auxActor.getName().charAt(0) > 'A' && auxActor.getName().charAt(0) < 'Z') {
                                auxActor.getFilmList().addFilm(auxFilm);
                                ActorCatalog.getmyActorCatalog().addActor(auxActor);
                                if (!auxFilm.getName().contains("�")) {
                                    FilmCatalog.getMyFilmCatalog().getFilm(auxFilm.getName()).getActorList().addActor(auxActor);
                                }
                            }
                        }
                    } else {
                        auxActor.getFilmList().addFilm(auxFilm);
                        ActorCatalog.getmyActorCatalog().addActor(auxActor);
                        FilmCatalog.getMyFilmCatalog().getFilm(auxFilm.getName()).getActorList().addActor(auxActor);
                    }

                    i++;

                }
                if (j == 0) {
                    System.out.println("\t[*] 0% file readed");
                }
                j++;

                if (((j * 100) / contLines) % 5 == 0) {
                    if (((j * 100) / contLines) != auxCont) {
                        auxCont = ((j * 100) / contLines);
                        long stopTime = System.currentTimeMillis();
                        total = (int) (stopTime - startTime) / 1000;

                        System.out.println("\t[*] " + auxCont + "% file readed. Time elapsed: " + total + "s");
                    }
                } else if (((j * 100) / contLines) % 5 == 0) {
                    JMenu.getMyJMenu().updateBar((j * 100) / contLines);
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (in != null) {
                in.close();
            }
        }
        System.out.println("\t-------- File read finished --------\n");
        System.out.println("\t--- Elapsed time to read the file ---> " + total + "s---");
        System.out.println("\t--- Total actor/actresses found :"+ActorCatalog.getmyActorCatalog().getActorL().size());
        System.out.println("\t--- Total films found : "+FilmCatalog.getMyFilmCatalog().getSize());
    }

    @SuppressWarnings("rawtypes")
    public void exportToFile() {

        String[] keys = ActorCatalog.getmyActorCatalog().quickSortList();

        FileWriter fichero = null;
        PrintWriter pw;

        long timeStart = System.currentTimeMillis();

        try {
            String directorio = System.getProperty("user.dir");//cogemos variable entorno
            fichero = new FileWriter(directorio + "/ActorList_ordered.txt");
            pw = new PrintWriter(fichero);

            int i = 1;
            for (int i1 = 0, keysLength = keys.length; i1 < keysLength; i1++) {
                Object key = keys[i1];
                pw.print("Actor " + i + " -> ");
                pw.println(ActorCatalog.getmyActorCatalog().getActorL().get(key).getName() + " " + ActorCatalog.getmyActorCatalog().getActorL().get(key).getSurname());
                Object[] keys2;
                keys2 = ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().keySet().toArray();
                for (Object auxKey : keys2) {
                    pw.println("\t" + ActorCatalog.getmyActorCatalog().getActorL().get(key).getFilmList().getFilmL().get(auxKey).getName());
                }
                i++;
                int percentage = (i * 100) / ActorCatalog.getmyActorCatalog().getActorL().size();
                if (((i * 100) / ActorCatalog.getmyActorCatalog().getActorL().size()) % 5 == 0) {
                    if (((i * 100) / ActorCatalog.getmyActorCatalog().getActorL().size()) != percentage) {
                        System.out.println("\t\t[*] " + percentage + "%");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
                long timeTotal = (System.currentTimeMillis() - timeStart);
                System.out.println("\t\t --- Elapsed time to export the file --- : " + (int) timeTotal / 1000 + "sec, " + timeTotal * 1000 + "ms\n");
                System.out.println("\n\tFile exported to: " + System.getProperty("user.dir"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
