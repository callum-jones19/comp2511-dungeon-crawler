package unsw.dungeon;

public class UpwardsOrientation implements PlayerOrientation {
    
    private Entity parent;
    private Dungeon dungeon;

    public UpwardsOrientation(Entity p, Dungeon dungeon) {
        this.parent = p;
        this.dungeon = dungeon;
    }
    
    public void attack(Weapon weapon) {
        int attackY = parent.getY() - 1;
        int attackX = parent.getX();
        weapon.use(dungeon.getTopmostEntity(attackX, attackY));
    }
}