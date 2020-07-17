package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private CollisionBehaviour coll;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setY(int y) {
        y().set(y);
    }

    public void setX(int x) {
        x().set(x);
    }

    public void setPos(int x, int y) {
        setY(y);
        setX(x);
    }

    public void onCollide(Entity collider) {
        coll.onCollide(collider);
    }

    public boolean isEnterable() {
        return coll.isEnterable();
    }

    public void destroy() {
        // TODO
    }
}
