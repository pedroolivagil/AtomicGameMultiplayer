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
        super(screen.getGame().getUIShips().findRegion(texture), screen, world, x, y);
        damage = 1;
    }


    public void movement(){

    }

    public void shoot(){

    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
