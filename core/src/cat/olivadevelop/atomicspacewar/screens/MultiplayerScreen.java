package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Contact;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.enviroment.Bound;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

import static cat.olivadevelop.atomicspacewar.tools.Tools.BOUND_LARGE;
import static cat.olivadevelop.atomicspacewar.tools.Tools.BOUND_SMALL;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_H;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_W;

/**
 * Created by Oliva on 23/02/2017.
 */

public class MultiplayerScreen extends GenericScreen {

    private PlayerBasic player;
    private Bound[] bounds;
    private ShapeRenderer shape;

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
    }

    @Override
    public void show() {
        super.show();
        getWorld().setContactListener(this);
        bounds = new Bound[]{
                new Bound(this, getWorld(), BOUND_LARGE, BOUND_LARGE, BOUND_SMALL, TILED_MAP_H - (BOUND_LARGE * 2)),                    // LEFT
                new Bound(this, getWorld(), TILED_MAP_W - BOUND_LARGE, BOUND_LARGE, BOUND_SMALL, TILED_MAP_H - (BOUND_LARGE * 2)),      // RIGHT
                new Bound(this, getWorld(), BOUND_LARGE, TILED_MAP_H - BOUND_LARGE, TILED_MAP_W - (BOUND_LARGE * 2), BOUND_SMALL),      // TOP
                new Bound(this, getWorld(), BOUND_LARGE, BOUND_LARGE, TILED_MAP_W - (BOUND_LARGE * 2), BOUND_SMALL)                     // BOTTOM
        };
        shape = new ShapeRenderer();

        player = new PlayerBasic(this, getWorld(), 1000, 1000);
        getStage().addActor(player);
        for (Bound b : bounds) {
            getStage().addActor(b);
        }
    }

    @Override
    protected void actionsRender(float delta) {
        super.actionsRender(delta);
        getGame().getMapBackground().setView(getStage().getCamera().combined, 0, 0, TILED_MAP_W, TILED_MAP_H);
        getGame().getMapBackground().render();
        checkGamePad();
    }

    @Override
    public void connected(Controller controller) {
        super.connected(controller);
        checkGamePad();
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (buttonCode == getGame().getBtnsPad().getKeys("BUTTON_RB")) {
            player.setSpeedExtra(Tools.PLAYER_SUPER_SPEED);
        }
        return super.buttonDown(controller, buttonCode);
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if (buttonCode == getGame().getBtnsPad().getKeys("BUTTON_RB")) {
            player.setSpeedExtra(0);
        }
        return super.buttonUp(controller, buttonCode);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        player.setMoving(true);
        if (axisCode == getGame().getBtnsPad().getKeys("AXIS_LEFT_X")) {
            player.dirX = value;
        } else if (axisCode == getGame().getBtnsPad().getKeys("AXIS_LEFT_Y")) {
            player.dirY = -value;
        }
        return super.axisMoved(controller, axisCode, value);
    }

    @Override
    public void dispose() {
        getGame().getMapBackground().dispose();
        super.dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        super.beginContact(contact);
        Gdx.app.log("FIX A USR DATA BGN", "" + contact.getFixtureA().getUserData().toString());
        Gdx.app.log("FIX B USR DATA BGN", "" + contact.getFixtureB().getUserData().toString());
    }

    @Override
    public void endContact(Contact contact) {
        super.endContact(contact);
        Gdx.app.log("FIX A USR DATA END", "" + contact.getFixtureA().getUserData().toString());
        Gdx.app.log("FIX B USR DATA END", "" + contact.getFixtureB().getUserData().toString());
    }
}
