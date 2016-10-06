package lab1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by david on 25/09/2016.
 */
public class Film {

    private String name;
    private int earned = 0;
    private ActorList actorList;

    public Film(String pName) {
        this.name = pName;
        this.actorList = new ActorList();
    }

    public String getName() {
        return this.name;
    }

    public int getEarned() {
        return this.earned;
    }

    public void incrementEarned(int auxEarned) {
        this.earned += auxEarned;
    }

    public ActorList getActorList() {
        return this.actorList;
    }


}
