package lab1;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Josu on 26/09/2016.
 */
public class TerminalGUI {

    private static TerminalGUI myTerminalGUI;
    private Scanner optMenu;

    private TerminalGUI() {
        optMenu = new Scanner(System.in);
    }

    public static TerminalGUI getMyTerminalGUI() {
        if (myTerminalGUI == null) {
            myTerminalGUI = new TerminalGUI();
        }
        return myTerminalGUI;
    }

    public static void main() throws FileNotFoundException {

        String[] auxActorArray;
        String auxActorName;
        String auxActorSurname;

        String auxS;

        System.out.println("\t\t\t***-----Welcome to our IMDb EDA16/17 project menu-----***");

        do {
            System.out.println("\n\t\t\t----------MENU----------");
            System.out.println("\t\t1) Read data from file");
            System.out.println("\t\t2) Search for an actor/actress");
            System.out.println("\t\t3) Add a new actor/actress");
            System.out.println("\t\t4) Search for films of a particular actor");
            System.out.println("\t\t5) Search for actor of a particular film");
            System.out.println("\t\t6) Increase the money raised by a film");
            System.out.println("\t\t7) Erase an actor/actress");
            System.out.println("\t\t8) Obtain an ordered list of actor (name,surname)");
            System.out.println("\t\t9) Save/Export the new list to a file");

            int optMenu = Teclado.getMiTeclado().recogerInt();

            switch (optMenu) {
                case 1:
                    try {
                        long startTime = System.currentTimeMillis();
                        FileManager.getMyFileManager().readFile();
                        long stopTime = System.currentTimeMillis();
                        long total = stopTime - startTime;
                        System.out.println("Elapsed time ---> "+total/1000+"s");
                    } catch (FileNotFoundException e1) {
                        System.out.println("File not found. ¿Are you sure that you're opening the correct file?");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the actor you want to look for: ");
                    auxS = Teclado.getMiTeclado().recogerString();
                    auxActorArray = auxS.split("\\s");
                    auxActorName = auxActorArray[0];
                    auxActorSurname = auxActorArray[1];
                    Actor auxA = ActorCatalog.getmyActorCatalog().searchActor(new Actor(auxActorName, auxActorSurname));
                    if (auxA == null) {
                        System.out.println("Actor not finded: " + auxS);
                    } else {
                        System.out.println("Actor finded: " + auxA.getName());
                    }
                    break;
                case 3:
                    System.out.println("Enter the name of the actor that you want to add: ");
                    auxS = Teclado.getMiTeclado().recogerString();
                    auxActorArray = auxS.split("\\s");
                    auxActorName = auxActorArray[0];
                    auxActorSurname = auxActorArray[1];
                    if (ActorCatalog.getmyActorCatalog().searchActor(new Actor(auxActorName, auxActorSurname)) != null) {
                        System.out.println("Actor: " + auxS + " already exist");
                    } else {
                        ActorCatalog.getmyActorCatalog().addActor(new Actor(auxActorName, auxActorSurname));
                        System.out.println("Actor: " + auxS + " added to the ActorCatalog");
                    }
                    break;
                case 4:
                    System.out.println("Enter the name of the actor whose films you want to know");
                    auxS = Teclado.getMiTeclado().recogerString();
                    auxActorArray = auxS.split("\\s");
                    auxActorName = auxActorArray[0];
                    auxActorSurname = auxActorArray[1];
                    if (ActorCatalog.getmyActorCatalog().searchActor(new Actor(auxActorName, auxActorSurname)) != null) {
                        ActorCatalog.getmyActorCatalog().searchActor(new Actor(auxActorName, auxActorSurname)).getFilmList().printFilms();
                    } else {
                        System.out.println("Actor: " + auxS + " do not exist in the ActorCatalog");
                    }
                    break;
                case 5:
                    System.out.println("Enter the film whose actors that do you want to know: ");
                    auxS = Teclado.getMiTeclado().recogerString();
                    if (FilmCatalog.getMyFilmCatalog().getFilm(auxS) != null) {
                        FilmCatalog.getMyFilmCatalog().getFilm(auxS).getActorList().printActors();
                    }
                    break;
                case 6:
                    System.out.println("Enter the film whose amount of earning you want to raise: ");
                    auxS = Teclado.getMiTeclado().recogerString();
                    System.out.println("Enter the amount of money that you want to increase: ");
                    int auxI = Teclado.getMiTeclado().recogerInt();
                    if (FilmCatalog.getMyFilmCatalog().exist(auxS)) {
                        FilmCatalog.getMyFilmCatalog().getFilm(auxS).incrementEarned(auxI);
                        System.out.println("Total earned by the film: " + FilmCatalog.getMyFilmCatalog().getFilm(auxS).getEarned());
                    } else {
                        System.out.println("File not found. Are you sure that you have written title correctly?");
                    }
                    break;
                case 7:
                    System.out.println("Enter the name of the actor that you want to remove");
                    auxS = Teclado.getMiTeclado().recogerString();
                    auxActorArray = auxS.split("\\s");
                    auxActorName = auxActorArray[0];
                    auxActorSurname = auxActorArray[1];
                    if (ActorCatalog.getmyActorCatalog().searchActor(new Actor(auxActorName, auxActorSurname)) != null) {
                        ActorCatalog.getmyActorCatalog().removeActor(new Actor(auxActorName, auxActorSurname));
                        System.out.println("Actor: " + auxS + " succesfully removed");
                    } else {
                        System.out.println("Actor: " + auxS + " do not exist in the ActorCatalog");
                    }
                    break;
                case 8:
                    ActorCatalog.getmyActorCatalog().quickSortList();
                    break;
                case 9:
                    FileManager.getMyFileManager().exportToFile();
                    break;
            }
        }while(1 != 0);
    }
}
