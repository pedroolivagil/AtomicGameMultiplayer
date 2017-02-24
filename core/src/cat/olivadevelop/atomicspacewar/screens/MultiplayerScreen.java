package cat.olivadevelop.atomicspacewar.screens;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.actors.players.PlayerBasic;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

/**
 * Created by Oliva on 23/02/2017.
 */

public class MultiplayerScreen extends GenericScreen {

    public MultiplayerScreen(AtomicSpaceWarGame game) {
        super(game, true);
    }

    @Override
    public void show() {
        super.show();
        PlayerBasic player = new PlayerBasic(this, getWorld(), 0, 0);
        player.debug();
        getStage().addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
