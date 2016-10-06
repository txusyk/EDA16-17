package lab1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by david on 25/09/2016.
 */
public class ActorList {

    private HashMap<String, Actor> actorList;


    public ActorList() {
        this.actorList = new HashMap<>();
    }


    public boolean exist(String pActorName, String pActorSurname) {
        if (this.actorList.get(pActorName + " " + pActorSurname) != null) {
            return true;
        }
        return false;
    }

    public void addActor(Actor auxActor) {
        if (!this.exist(auxActor.getName(), auxActor.getSurname())) {
            this.actorList.put(auxActor.getName() + " " + auxActor.getSurname(), auxActor);
        }
    }

    public Actor getActor(String pActorName, String pActorSurname) {
        if (this.actorList.get(pActorName + " " + pActorSurname) != null) {
            return actorList.get(pActorName);
        }
        return null;
    }

    public void removeActor(Actor pActor) {
        if (this.actorList.get(pActor.getName() + " " + pActor.getSurname()) != null) {
            this.actorList.remove(pActor.getName() + " " + pActor.getSurname());
        }
    }

    public void printActors() {
        System.out.println("These are all the actors: ");
        for (HashMap.Entry<String, Actor> entry : actorList.entrySet()) {
            String key = entry.getKey();
            System.out.println(key.toString());
        }
    }


}
