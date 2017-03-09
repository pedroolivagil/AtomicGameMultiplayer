package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.ActorBox2D;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

/**
 * Created by Oliva on 23/02/2017.
 */

public class GenericPlayer extends ActorBox2D {

    private int damage;

    public GenericPlayer(String texture, GenericScreen screen, World world, float x, float y) {
        super(screen.getGame().getUIShips(texture), screen, world, x, y);
        damage = 1;
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void shoot() {

    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
