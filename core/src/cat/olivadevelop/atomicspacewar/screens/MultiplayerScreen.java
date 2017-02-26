package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.controllers.Controller;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 23/02/2017.
 */

public class MultiplayerScreen extends GenericScreen {

    private PlayerBasic player;

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
    }

    @Override
    public void show() {
        super.show();
        player = new PlayerBasic(this, getWorld(), 50, 50);
        getStage().addActor(player);
    }

    @Override
    protected void actionsRender(float delta) {
        super.actionsRender(delta);

        getGame().getMapBackground().setView(getStage().getCamera().combined, 0, 0, Tools.tiledMapW, Tools.tiledMapH);
        getGame().getMapBackground().render();

        /*getStage().getCamera().translate(
                (player.getDirection().x * (Tools.getScreen_width() / 2f + player.getX() + player.getWidth() / 2)) * Tools.METERS_IN_PIXELS,
                (player.getDirection().y * (Tools.getScreen_height() / 2f + player.getY() + player.getHeight() / 2)) * Tools.METERS_IN_PIXELS,
                0
        );*/
        /*getStage().getCamera().translate(
                (player.getBody().getTransform().getPosition().x * player.dirX),
                (player.getBody().getTransform().getPosition().y * player.dirY),
                0
        );*/
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
}
