package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.HUD;
import cat.olivadevelop.atomicspacewar.actors.enviroment.Bound;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

import static cat.olivadevelop.atomicspacewar.tools.BotonesXbox.AXIS_LEFT_X;
import static cat.olivadevelop.atomicspacewar.tools.BotonesXbox.AXIS_LEFT_Y;
import static cat.olivadevelop.atomicspacewar.tools.BotonesXbox.BUTTON_RB;
import static cat.olivadevelop.atomicspacewar.tools.Tools.BOUND_LARGE;
import static cat.olivadevelop.atomicspacewar.tools.Tools.BOUND_SMALL;
import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_BOUND;
import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_PLAYER;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_H;
import static cat.olivadevelop.atomicspacewar.tools.Tools.TILED_MAP_W;
import static cat.olivadevelop.atomicspacewar.tools.Tools.getScreenHeight;
import static cat.olivadevelop.atomicspacewar.tools.Tools.getScreenWidth;
import static cat.olivadevelop.atomicspacewar.tools.Tools.pixToMet;

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
    private int contadorMuertes;
    private boolean playerMuerto;
    private HUD hud;

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
        //setDebugAllStage(true);
    }

    @Override
    public void show() {
        super.show();
        hud = new HUD(this);
        player = new PlayerBasic(this, getWorld());
        bounds = new Bound[]{
                new Bound(this, getWorld(), pixToMet(BOUND_LARGE), pixToMet(BOUND_LARGE) + pixToMet(BOUND_SMALL), pixToMet(BOUND_SMALL), pixToMet(TILED_MAP_H - (BOUND_LARGE * 2))),                    // LEFT
                new Bound(this, getWorld(), pixToMet(TILED_MAP_W - BOUND_LARGE), pixToMet(BOUND_LARGE), pixToMet(BOUND_SMALL), pixToMet(TILED_MAP_H - (BOUND_LARGE * 2))),      // RIGHT
                new Bound(this, getWorld(), pixToMet(BOUND_LARGE) + pixToMet(BOUND_SMALL), pixToMet(TILED_MAP_H - BOUND_LARGE), pixToMet(TILED_MAP_W - (BOUND_LARGE * 2)), pixToMet(BOUND_SMALL)),      // TOP
                new Bound(this, getWorld(), pixToMet(BOUND_LARGE), pixToMet(BOUND_LARGE), pixToMet(TILED_MAP_W - (BOUND_LARGE * 2)), pixToMet(BOUND_SMALL))                     // BOTTOM
        };
        getStage().addActor(player);
        getStage().addActor(hud);
        for (Bound b : bounds) {
            getStage().addActor(b);
        }
        player.born();
    }

    @Override
    protected void actionsRender(float delta) {
        super.actionsRender(delta);
        getGame().getMapBackground().setView(getStage().getCamera().combined, 0, 0, TILED_MAP_W, TILED_MAP_H);
        getGame().getMapBackground().render();
        checkGamPad();

        getStage().getCamera().position.x = player.getX() + (player.getWidth() / 2);
        getStage().getCamera().position.y = player.getY();
        if (playerMuerto) {
            if (player.isAlive()) {
                player.death();
                contadorMuertes++;
                Tools.logger(this, "Actor muerto", contadorMuertes);
                playerMuerto = false;
            }
        }
        hud.setPosition(
                getStage().getCamera().position.x - (getScreenWidth() / 2),
                getStage().getCamera().position.y - (getScreenHeight() / 2)
        );

        // quitar la negación
        if (isGamePadActive()) {
            hud.addTouchpad();
            if (hud.getTouchpad().isTouched()) {
                player.dirX = hud.getTouchpad().getKnobPercentX();
                player.dirY = hud.getTouchpad().getKnobPercentY();
            }
        } else {
            hud.removeTouchpad();
        }
    }

    @Override
    public void connected(Controller controller) {
        super.connected(controller);
        setInitGamePad(false);
        checkGamPad();
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        if (player.isAlive()) {
            if (buttonCode == getGame().getBtnsPad().getKeys(BUTTON_RB.code())) {
                player.setSpeedExtra(Tools.PLAYER_SUPER_SPEED);
                player.getFire().addAction(
                        Actions.sequence(
                                Actions.scaleTo(1.2f, 1.2f, .2f),
                                Actions.scaleTo(1, 1, .1f)
                        )
                );
            }
        }
        return super.buttonDown(controller, buttonCode);
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if (player.isAlive()) {
            if (buttonCode == getGame().getBtnsPad().getKeys(BUTTON_RB.code())) {
                player.setSpeedExtra(0);
            }
        }
        return super.buttonUp(controller, buttonCode);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (player.isAlive()) {
            if (axisCode == getGame().getBtnsPad().getKeys(AXIS_LEFT_X.code())) {
                player.dirX = value;
            } else if (axisCode == getGame().getBtnsPad().getKeys(AXIS_LEFT_Y.code())) {
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
            playerMuerto = true;
        }
    }
}
