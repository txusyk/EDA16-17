package lab3;

import lab2.UnorderedCircularLinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphHash {

    private  HashMap<Film,Film> path;
    private static GraphHash myGraphHash;

    private  GraphHash(){
        this.path = new HashMap<>();
    }

    public static GraphHash getMyGraphHash(){
        if (myGraphHash == null){
            myGraphHash = new GraphHash();
        }
        return myGraphHash;
    }

	public void crearGrafo(){
		for (String f : FilmCatalog.getMyFilmCatalog().getFilmL().keySet()){
            ArrayList<String> tested = new ArrayList<>();
		    for (String auxA : FilmCatalog.getMyFilmCatalog().getFilm(f).getActorList().getActorL().keySet()){
		        String name, surname;
		        String[] auxName = auxA.split("\\s");
		        name = auxName[0];
		        surname = "";
		        if (auxName.length > 1) {
                    surname = auxName[1];
                }
		        if (ActorCatalog.getmyActorCatalog().searchActor(new Actor(name,surname)) != null) {
                    for (String auxF : ActorCatalog.getmyActorCatalog().searchActor(new Actor(name,surname)).getFilmList().getFilmL().keySet()) {
                        if (!f.equalsIgnoreCase(auxF) && !tested.contains(auxA)) {
                            if (FilmCatalog.getMyFilmCatalog().getFilm(auxF) != null && FilmCatalog.getMyFilmCatalog().getFilm(f) != null){
                                FilmCatalog.getMyFilmCatalog().getFilm(auxF).getFriends().add(FilmCatalog.getMyFilmCatalog().getFilm(f));
                            }
                            tested.add(auxA);
                        }
                    }
                }
            }
        }
	}
	
	public boolean estanConectadas(String p1, String p2){
	    crearGrafo();
        UnorderedCircularLinkedList<Film> pending = new UnorderedCircularLinkedList<>();
        pending.addToRear(FilmCatalog.getMyFilmCatalog().getFilm(p1));

        HashSet<String> checked =  new HashSet<>();
        boolean find = false;

        Film currentFilm;
        do{
            currentFilm = pending.first();
            pending.remove(currentFilm);
            if (currentFilm.getName().equalsIgnoreCase(p2)){
                find = true;
            }else{
                for (Film friendsFilm : currentFilm.getFriends()){
                    if (!checked.contains(friendsFilm.getName())){
                        path.put(friendsFilm, currentFilm);
                        checked.add(friendsFilm.getName());
                    }
                    pending.addToRear(friendsFilm);
                }
            }
        }while (!find && !pending.isEmpty());

        for (Film auxFilm : path.keySet()){
            System.out.println(auxFilm.getName());
        }
        return find;
    }
}
