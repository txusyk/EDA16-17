package lab1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Josu on 25/09/2016.
 */
public class ActorCatalog {

    private static ActorCatalog myActorCatalog;
    private HashMap<String, Actor> actorL;

    private ActorCatalog() {
        this.actorL = new HashMap<>();
    }

    public static ActorCatalog getmyActorCatalog() {
        if (myActorCatalog == null) {
            myActorCatalog = new ActorCatalog();
        }
        return myActorCatalog;
    }

    public HashMap<String, Actor> getActorL() {
        return this.actorL;
    }

    private boolean exist(String pActorName, String pActorSurname) {
        if (actorL.get(pActorName + " " + pActorSurname) != null) {
            return true;
        }
        return false;
    }

    public void addActor(Actor pActor) {
        if (!this.exist(pActor.getName(), pActor.getSurname())) {
            this.actorL.put(pActor.getName() + " " + pActor.getSurname(), pActor);
        }
    }

    public Actor searchActor(Actor pActor) {
        if (this.exist(pActor.getName(), pActor.getSurname())) {
            return this.actorL.get(pActor.getName() + " " + pActor.getSurname());
        }
        return null;
    }

    public void removeActor(Actor pActor) {
        if (this.exist(pActor.getName(), pActor.getSurname())) {
            this.actorL.remove(pActor.getName() + " " + pActor.getSurname());
            System.out.println("Actor erased");
        } else {
            System.out.println("Actor not found");
        }
    }

    public String[] quickSortList() {
        String[] auxS = new String[actorL.size()];
        int i = 0;
        for (String key : actorL.keySet()) {
            auxS[i] = key;
            i++;
        }
        long timeStart = System.currentTimeMillis();
        StringQuickSort.sort(auxS);
        long timeTotal = (System.currentTimeMillis() - timeStart);
        System.out.println("\t\t --- Elapsed time to order the actor list --- : " + (int) timeTotal / 1000 + "sec, " + timeTotal * 1000 + "ms\n");
        return auxS;
    }

    public void printList(String[] auxS) {
        for (String key : auxS) {
            System.out.println(key);
        }
    }


}
