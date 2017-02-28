package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

import static cat.olivadevelop.atomicspacewar.tools.Tools.METERS_IN_PIXELS;
import static cat.olivadevelop.atomicspacewar.tools.Tools.PLAYER_SPEED;

/**
 * Created by Oliva on 23/02/2017.
 */

public class PlayerBasic extends GenericPlayer {

    public float dirX;
    public float dirY;
    public float lastDirX;
    public float lastDirY;
    private Vector2 direction;
    private float speedExtra;
    private boolean moving;
    private boolean assigned;

    public PlayerBasic(GenericScreen screen, World world, float x, float y) {
        super("playerShip3_orange", screen, world, x, y);
        this.direction = new Vector2(0, 0);
        getFixture().setUserData("player");
        getFixture().setFriction(100);
        speedExtra = 0;
        moving = false;
        assigned = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
    }

    public Vector2 getDirection() {
        return direction;
    }

    private void move(float delta) {
        if ((dirX == (float) 0.000015258789 && dirY == (float) -0.007827878)) {
            dirX = 0.0f;
            dirY = 0.0f;
        }
        if (!(dirX == 0.0 && dirY == 0.0)) {
            moveAction(delta);
            lastDirX = dirX;
            lastDirY = dirY;
            assigned = false;
        } else {
            if (!assigned) {
                dirX = lastDirX;
                dirY = lastDirY;
                assigned = true;
            }
        }
        //setPosition(getX() + dirX * (Tools.PLAYER_SPEED + speedExtra) * delta, getY() + dirY * (Tools.PLAYER_SPEED + speedExtra) * delta);
        getBody().setTransform(
                (getX() * METERS_IN_PIXELS) + (METERS_IN_PIXELS * dirX * (PLAYER_SPEED + speedExtra)),
                (getY() * METERS_IN_PIXELS) + (METERS_IN_PIXELS * dirY * (PLAYER_SPEED + speedExtra)),
                getRotation()
        );

        /*getScreen().getStage().getCamera().translate(
                (getX() * METERS_IN_PIXELS) + (METERS_IN_PIXELS * dirX * (PLAYER_SPEED + speedExtra)),
                (getY() * METERS_IN_PIXELS) + (METERS_IN_PIXELS * dirY * (PLAYER_SPEED + speedExtra)),
                0
        );*/
        getScreen().getStage().getCamera().position.x = (getX() + (getWidth() / 2))
                + (METERS_IN_PIXELS * dirX * (PLAYER_SPEED + speedExtra));
        getScreen().getStage().getCamera().position.y = (getY() - (getHeight() * METERS_IN_PIXELS) / 2)
                + (METERS_IN_PIXELS * dirY * (PLAYER_SPEED + speedExtra));
    }

    private void moveAction(float delta) {
        setRotation(calcDegree(getX() + dirX * (PLAYER_SPEED + speedExtra) * delta, getY() + dirY * (PLAYER_SPEED + speedExtra) * delta));
    }

    private float calcDegree(float newX, float newY) {
        double finalDeg = Math.atan2(newY - getY(), newX - getX());
        finalDeg = (finalDeg * MathUtils.radiansToDegrees);
        double degrees = finalDeg - 90;
        return (float) degrees;
    }

    public void setSpeedExtra(float speedExtra) {
        this.speedExtra = speedExtra;
    }

    public float getSpeedExtra() {
        return speedExtra;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }
}
