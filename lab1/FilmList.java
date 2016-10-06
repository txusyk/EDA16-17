package lab1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Josu on 25/09/2016.
 */
public class FilmList {

    private HashMap<String, Film> filmL;

    public FilmList() {
        this.filmL = new HashMap<>();
    }


    private boolean exist(String pFilmName) {
        if (this.filmL.get(pFilmName) != null) {
            return true;
        }
        return false;
    }

    public void addFilm(Film pFilm) {
        if (!this.exist(pFilm.getName())) {
            this.filmL.put(pFilm.getName(), pFilm);
        }
    }

    public Film getFilm(String pFilmName) {
        if (this.filmL.get(pFilmName) != null) {
            return this.filmL.get(pFilmName);
        }
        return null;
    }

    public HashMap<String, Film> getFilmL() {
        return this.filmL;
    }

    public void printFilms() {
        System.out.println("These are all the films: ");
        for (HashMap.Entry<String, Film> entry : filmL.entrySet()) {
            String key = entry.getKey();
            System.out.println(key.toString());
        }
    }


}
