package lab1;

import java.util.HashMap;

/**
 * Created by Josu on 25/09/2016.
 */
public class FilmCatalog {

    private static FilmCatalog myFilmCatalog;
    private HashMap<String,Film> filmL;

    private FilmCatalog(){
        this.filmL = new HashMap<>();
    }

    public static FilmCatalog getMyFilmCatalog(){
        if (myFilmCatalog == null){
            myFilmCatalog = new FilmCatalog();
        }
        return myFilmCatalog;
    }

    public boolean exist(String pFilmName){
        if (filmL.get(pFilmName) != null){
            return true;
        }
        return false;
    }

    public void addFilm(Film pFilm){
        if (!this.exist(pFilm.getName())){
            this.filmL.put(pFilm.getName(),pFilm);
        }
    }

    public Film getFilm(String pFilmName){
        if (this.exist(pFilmName)){
            return this.filmL.get(pFilmName);
        }
        return null;
    }

}
