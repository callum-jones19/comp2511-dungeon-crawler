package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private HashMap<Entity, ImageView> entities;

    //Images
    private Image groundImage;
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image doorImage;
    private Image doorOpenImage;
    private Image keyImage;
    private Image enemyImage;
    private Image swordImage;
    private Image boulderImage;
    private Image switchImage;
    private Image portalImage;
    private Image potionImage;
    private Image treasureImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new HashMap<Entity, ImageView>();
        
        loadTextures();
    }

    public void loadTextures() {
        groundImage = new Image((new File("images/ground.png")).toURI().toString());
        playerImage = new Image((new File("images/human.png")).toURI().toString());
        wallImage = new Image((new File("images/wall.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        doorOpenImage = new Image((new File("images/open_door.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        enemyImage = new Image((new File("images/enemy.png")).toURI().toString());
        swordImage = new Image((new File("images/sword.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        potionImage = new Image((new File("images/potion.png")).toURI().toString());
        treasureImage = new Image((new File("images/treasure.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);

    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);

    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);

    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);

    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);

    }

    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);

    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(floorSwitch, view);

    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);

    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);

    }


    /**
     * Link the entityImage and the entity's location together
     * Add the image to a list of images.
     * @param entity
     * @param view
     */
    private void addEntity(Entity entity, ImageView view) {
        // trackPosition(entity, view);
        entities.put(entity, view);
    }



    public HashMap<Entity, ImageView> loadDungeonImages() {
        return this.entities;
    }


    /**
     * Gets the size of each each tile in the dungeon. Bases this off the background tile.
     * @return
     */
    public int getTileSize() {
        return (int) groundImage.getWidth();
    }


    public Image getGroundTexture() {
        return this.groundImage;
    }

}
