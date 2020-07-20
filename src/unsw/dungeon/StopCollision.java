package unsw.dungeon;

public class StopCollision implements CollisionBehaviour {
    
    Boolean isEnterable;

    public StopCollision() {
        super();
        this.isEnterable = false;
    }

    public boolean isEnterable() {
        return isEnterable;
    }

    public void onCollide(Entity e) {
        // As of now, do nothing.
    }


}