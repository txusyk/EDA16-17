package lab1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Josu on 25/09/2016.
 */
public class FilmList {

    ArrayList<Film> FilmL;

    public FilmList(){
        this.FilmL = new ArrayList<>();
    }

    private Iterator<Film> getIterator(){
        return this.FilmL.iterator();
    }

    private boolean exist(String pFilmName){
        Iterator<Film> itr = this.getIterator();
        boolean finded = false;
        while (itr.hasNext() || finded){
            Film auxFilm = itr.next();
            if (pFilmName == auxFilm.getName()) {
                finded = true;
            }
        }
        return finded;
    }

    public void addFilm(Film pFilm){
        if (!this.exist(pFilm.getName())){
            this.FilmL.add(pFilm);
        }
    }

    public Film getFilm(String pFilmName){
        Film auxFilm = null;
        if (this.exist(pFilmName)){
            Iterator<Film> itr = this.getIterator();
            boolean finded = false;
            while (itr.hasNext() || finded){
                auxFilm = itr.next();
                if (pFilmName == auxFilm.getName()){
                    finded = true;
                }
            }
        }
        return auxFilm;
    }


}
