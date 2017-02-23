package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.ActorBox2D;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

/**
 * Created by Oliva on 23/02/2017.
 */

public class Player extends ActorBox2D {

    public Player(String texture, GenericScreen screen, World world, Vector2 position) {
        super(screen.getGame().getUIShips().findRegion(texture), screen, world, position);
    }
}
