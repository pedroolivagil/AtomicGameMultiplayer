package cat.olivadevelop.atomicspacewar.screens;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 23/02/2017.
 */

public class MultiplayerScreen extends GenericScreen {

    PlayerBasic player;

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
    }

    @Override
    public void show() {
        super.show();
        player = new PlayerBasic(this, getWorld(), 50, 50);
        player.debug();
        getStage().addActor(player);
        showCenter();
    }

    @Override
    protected void actionsRender(float delta) {
        super.actionsRender(delta);

        getStage().getCamera().translate(
                (player.getDirection().x * (Tools.getScreen_width() / 2f + player.getX() + player.getWidth() / 2)) * Tools.METERS_IN_PIXELS,
                (player.getDirection().y * (Tools.getScreen_height() / 2f + player.getY() + player.getHeight() / 2)) * Tools.METERS_IN_PIXELS,
                0
        );
        //Gdx.app.log("PlayerDirection", "X: " + getStage().getCamera().position.x + "; Y:" + getStage().getCamera().position.y);
        getGame().getMapBackground().setView(getStage().getCamera().combined, 0, 0, Tools.tiledMapW, Tools.tiledMapH);
        getGame().getMapBackground().render();

        /*Gdx.app.log("XY", "" + player.getX() + "; " + player.getY());
        Gdx.app.log("Stage XY", "" + getStage().getCamera().position.x + "; " + getStage().getCamera().position.y);*/
        moveCenter(getStage().getCamera().position.x, getStage().getCamera().position.y);
    }

    @Override
    public void dispose() {
        getGame().getMapBackground().dispose();
        super.dispose();
    }
}
