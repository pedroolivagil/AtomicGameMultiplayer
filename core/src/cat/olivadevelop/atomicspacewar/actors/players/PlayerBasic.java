package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

/**
 * Created by Oliva on 23/02/2017.
 */

public class PlayerBasic extends GenericPlayer {

    public PlayerBasic(GenericScreen screen, World world, float x, float y) {
        super("playerShip3_orange", screen, world, x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
