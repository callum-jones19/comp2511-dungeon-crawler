package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Key;
import unsw.dungeon.Door;
import unsw.dungeon.Player;
import unsw.dungeon.Boulder;
import unsw.dungeon.Sword; 

public class DoorTest {
    
    @Test
    public void testLocked() {

        // Test Setup
        Dungeon d = new Dungeon (5, 5);
        Key key1 = new Key(4, 3, 1);
        d.addEntity(key1);
        Door door1 = new Door(2, 3, 1);
        d.addEntity(door1);
        key1.linkDoor(door1);
        door1.linkKey(key1);
        Player p1 = new Player(d, 1, 1);
        d.setPlayer(p1);
        Enemy enemy1 = new Enemy(2, 2, d);
        d.addEntity(enemy1);
        Boulder boulder1 = new Boulder(d, 3, 3);
        d.addEntity(boulder1);
        Sword sword = new Sword(4, 4);
        sword.setUser(p1);
        d.addEntity(sword);


        // Player collides with the door without having the appropriate key
        p1.moveDown();
        p1.moveDown();
        
        assertEquals(3, p1.getY());
        assertEquals(1, p1.getX());
        
        p1.moveRight();

        assertEquals(p1, d.getTopmostEntity(1, 3));
        assertEquals(door1, d.getTopmostEntity(2, 3));


        // Enemy collides with the door while it is locked
        enemy1.move(2, 3);
        assertEquals(enemy1, d.getTopmostEntity(2, 2));
        assertEquals(door1, d.getTopmostEntity(2, 3));

        // Boulder is pushed into locked door
        p1.move(4, 3);
        p1.moveLeft();

        assertEquals(boulder1, d.getTopmostEntity(3, 3));
        assertEquals(p1, d.getTopmostEntity(4, 3));
        assertEquals(door1, d.getTopmostEntity(2, 3));

        // Player strikes locked door with sword
        sword.pickup(p1);

        assertEquals(true, p1.isHoldingInstance(sword));
        boulder1.destroy();
        p1.moveLeft();
        p1.attack();
        
        assertEquals(p1, d.getTopmostEntity(3, 3));
        assertEquals(door1, d.getTopmostEntity(2, 3));

    }
    @Test
    public void testUnlocked() {
        //TODO remove prints
        // Test Setup 
        Dungeon d = new Dungeon (5, 5);
        Key key1 = new Key(4, 3, 1);
        d.addEntity(key1);
        Door door1 = new Door(2, 3, 1);
        d.addEntity(door1);
        key1.linkDoor(door1);
        door1.linkKey(key1);
        Player p1 = new Player(d, 1, 1);
        d.setPlayer(p1);
        Enemy enemy1 = new Enemy(2, 2, d);
        d.addEntity(enemy1);
        Boulder boulder1 = new Boulder(d, 2, 4);
        d.addEntity(boulder1);


        d.printDungeon();
        // Player collides with door having picked up the appropriate key
        p1.move(4, 3);

        d.printDungeon();
        p1.moveLeft();
        d.printDungeon();
        p1.moveLeft();
        d.printDungeon();
        p1.moveLeft();
        d.printDungeon();

        assertEquals(p1, d.getTopmostEntity(2, 3));
        assertEquals(2, door1.getX());
        assertEquals(3, door1.getY());

        // Player pushes boulder through unlocked door
        d.printDungeon();
        p1.moveRight();
        p1.moveRight();
        boulder1.move(3, 3);
        
        d.printDungeon();

        p1.moveLeft();
        d.printDungeon();

        assertEquals(boulder1, d.getTopmostEntity(2, 3));
        d.printDungeon();

        p1.moveLeft();
        d.printDungeon();

        assertEquals(p1, d.getTopmostEntity(3, 3));

        p1.moveDown();
        enemy1.chasePlayer();
        d.printDungeon();

        assertEquals(enemy1, d.getTopmostEntity(2, 2));

        p1.move(3, 2);
        enemy1.chasePlayer();
        d.printDungeon();
        assertEquals(null, d.getPlayer());
        assertEquals(enemy1, d.getTopmostEntity(3, 2));
    }

    @Test
    public void testEquals() {
        Door d = new Door(1, 1, 1);
        assertEquals(false, d.equals(null));
        Door d2 = new Door(1,1, 1);
        assertEquals(true, d.equals(d2));
    }


}