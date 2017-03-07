package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.enviroment.Bound;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_BOUND;
import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_PLAYER;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_H;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_W;

/**
 * Created by Oliva on 23/02/2017.
 * <p>
 * plantearse la idea de no borrar el jugador al morir, si no que crear una
 * animación reversible que simule que el jugador ha muerto y
 * lo reposicione a otra ubicacion
 */

public class MultiplayerScreen extends GenericScreen {

    private PlayerBasic player;
    private Bound[] bounds;
    private ShapeRenderer shape;
    private int contadorMuertes;

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
        setDebugAllStage(true);
    }

    @Override
    public void show() {
        super.show();
        player = new PlayerBasic(this, getWorld());
        /*bounds = new Bound[]{
                new Bound(this, getWorld(), BOUND_LARGE, BOUND_LARGE, BOUND_SMALL, TILED_MAP_H - (BOUND_LARGE * 2)),                    // LEFT
                new Bound(this, getWorld(), TILED_MAP_W - BOUND_LARGE, BOUND_LARGE, BOUND_SMALL, TILED_MAP_H - (BOUND_LARGE * 2)),      // RIGHT
                new Bound(this, getWorld(), BOUND_LARGE, TILED_MAP_H - BOUND_LARGE, TILED_MAP_W - (BOUND_LARGE * 2), BOUND_SMALL),      // TOP
                new Bound(this, getWorld(), BOUND_LARGE, BOUND_LARGE, TILED_MAP_W - (BOUND_LARGE * 2), BOUND_SMALL)                     // BOTTOM
        };*/
        bounds = new Bound[]{
                new Bound(this, getWorld(), 1, 1.1f, .1f, 5),         // LEFT
                new Bound(this, getWorld(), 6, 1, .1f, 5),        // RIGHT
                new Bound(this, getWorld(), 1.1f, 6, 5, .1f),       // TOP
                new Bound(this, getWorld(), 1, 1, 5, .1f)         // BOTTOM
        };
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

        getStage().getCamera().position.x = player.getX() + (player.getWidth() / 2);
        getStage().getCamera().position.y = player.getY();
    }

    @Override
    public void connected(Controller controller) {
        super.connected(controller);
        checkGamePad();
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (player.isAlive()) {
            if (buttonCode == getGame().getBtnsPad().getKeys("BUTTON_RB")) {
                player.setSpeedExtra(Tools.PLAYER_SUPER_SPEED);
            }
        }
        return super.buttonDown(controller, buttonCode);
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if (player.isAlive()) {
            if (buttonCode == getGame().getBtnsPad().getKeys("BUTTON_RB")) {
                player.setSpeedExtra(0);
            }
        }
        return super.buttonUp(controller, buttonCode);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (player.isAlive()) {
            if (axisCode == getGame().getBtnsPad().getKeys("AXIS_LEFT_X")) {
                player.dirX = value;
            } else if (axisCode == getGame().getBtnsPad().getKeys("AXIS_LEFT_Y")) {
                player.dirY = -value;
            }
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
        Fixture a = contact.getFixtureA(), b = contact.getFixtureB();
        if ((a.getUserData().equals(FIXTURE_BOUND) && b.getUserData().equals(FIXTURE_PLAYER))
                || (b.getUserData().equals(FIXTURE_BOUND) && a.getUserData().equals(FIXTURE_PLAYER))) {
            if (player.isAlive()) {
                //player.death();
                contadorMuertes++;
                Gdx.app.log("Actor muerto", "" + contadorMuertes);
            }
        }
    }
}
