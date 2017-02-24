package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

/**
 * Created by Oliva on 23/02/2017.
 */

public class PlayerBasic extends GenericPlayer {

    Vector2 direction;
    // + (player.getDirection().x * Tools.PLAYER_SPEED * delta * Tools.PIXELS_IN_METERS)

    public PlayerBasic(GenericScreen screen, World world, float x, float y) {
        super("playerShip3_orange", screen, world, x, y);
        this.direction = new Vector2(0, 0);
        getFixture().setUserData("player");
        getFixture().setFriction(100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Vector2 getDirection() {
        return direction;
    }

}
