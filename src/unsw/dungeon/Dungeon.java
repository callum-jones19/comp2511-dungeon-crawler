/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements DestroyObserver, GoalObserver{

    private int width, height;

    private List<Entity> entities;
    private Player player;
    
    private List<Goal> goals;
    private boolean isComplete;

    BoulderSwitchMediator boulderSwitchMediator;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.player = null;
        this.goals = new ArrayList<Goal>();
        this.boulderSwitchMediator = new BoulderSwitchMediator();
    }

    public void update(DestroySubject sub) {
        if (sub instanceof Player) {
            removePlayer((Player) sub);
        } else if (sub instanceof Entity) {
            this.entities.remove(sub);
        }
    }

    public void update(Goal g) {
        boolean allGoalsDone = true;
        for (Goal goal : goals) {
            if (!goal.isCompleted()) {
                allGoalsDone = false;
            }
        }

        if (allGoalsDone) {
            this.isComplete = true;
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean entityExists(Entity e) {
        return entities.contains(e);
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.registerObserver(this);
    }

    public void removePlayer(Player p) {
        this.player = null;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Boulder) boulderSwitchMediator.addBoulder((Boulder) entity);
        if (entity instanceof FloorSwitch) boulderSwitchMediator.addSwitch((FloorSwitch) entity);
        entity.registerObserver(this);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void addGoal(Goal g) {
        this.goals.add(g);
        g.registerObserver(this);
    }

    public void removeGoal(Goal g) {
        this.goals.remove(g);
        // Watch out how you use this - will cause an error if you removeGoal
        // in the goal update function.
        g.removeObserver(this);
    }

    public List<Entity> getEntities(int x, int y) {
        List<Entity> result = new ArrayList<Entity>();
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y) {
                result.add(e);
            }
        }
        if (player != null && player.getX() == x && player.getY() == y) {
            result.add(player);
        }
        return result;
    }

    public boolean tileIsEmpty(int x, int y) {
        List<Entity> e = getEntities(x, y);
        if (player == null) {
            return e.isEmpty();
        }
        if (e.isEmpty() && (player.getX() != x || player.getY() != y)) {
            return true;
        } else {
            return false;
        }
    }

    public Entity getTopmostEntity(int x, int y) {
        List<Entity> entities = getEntities(x, y);
        if (entities.isEmpty()) {
            return null;
        } else {
            for (Entity e: entities) {
                if (e instanceof IMoveable) {
                    return e;
                }
            }
            return entities.get(0);
        }
    }

    public boolean checkEnterableTile(int x, int y) {
        List<Entity> entities = getEntities(x, y);
        for (Entity e: entities) {
            if (!(e.isEnterable())) {
                return false;
            }
        }
        return true;
    }

    public void printDungeon() {
        System.out.println("<========= Dungeon =========>");
        for (int i = 1; i  <= getHeight(); i++) {
            for (int j = 1; j <= getWidth(); j++) {
                Entity e = getTopmostEntity(j, i);
                if (e == null) {
                    System.out.print("-");
                } else if (e instanceof Player) {
                    System.out.print("P");
                } else if (e instanceof Boulder) {
                    System.out.print("B");
                } else if (e instanceof Wall) {
                    System.out.print("W");
                } else if (e instanceof Door) {
                    System.out.print("D");
                } else if (e instanceof Item) {
                    System.out.print("I");
                } else if (e instanceof FloorSwitch) {
                    FloorSwitch f = (FloorSwitch) e;
                    if (f.isActive()) {
                        System.out.print("F*");
                    } else {
                        System.out.print("F");
                    }
                } else {
                    System.out.print("*");
                }
            }
            System.out.print("\n");
        }
    }
    
    public List<Enemy> getEnemies() {
        List<Enemy> result = new ArrayList<Enemy>();
        for (Entity e : entities) {
            if (e instanceof Enemy) {
                result.add((Enemy) e);
            }
        }
        return result;
    }

    public void scareEnemies() {
        List<Enemy> enemies = getEnemies();

        for (Enemy e : enemies) {
            e.makeVulnerable();
        }
    }

    public void unScareEnemies() {
        List<Enemy> enemies = getEnemies();

        for (Enemy e : enemies) {
            e.makeHarmful();
        }
    }

}
