package lab1;

/**
 * Created by Josu on 25/09/2016.
 */
public class Actor {

    private String name;
    private String surname;
    private FilmList filmL;

    public Actor(String pName, String pSurname) {
        this.name = pName;
        this.surname = pSurname;
        this.filmL = new FilmList();
    }

    public FilmList getFilmList() {
        return this.filmL;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

}