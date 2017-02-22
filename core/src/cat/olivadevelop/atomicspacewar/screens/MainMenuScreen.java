package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.scenes.scene2d.Group;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.CustomButton;
import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Listener;
import cat.olivadevelop.atomicspacewar.tools.MenuGroup;
import cat.olivadevelop.atomicspacewar.tools.Tools;

import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MULTIPLAYER;

/**
 * Created by Oliva on 19/02/2017.
 */

public class MainMenuScreen extends GenericScreen {

    private CustomButton btnMultiplayer;
    private CustomButton btnExit;
    private Group groupButtons;
    private CustomImage iTitle;
    private CustomImage bg;

    public MainMenuScreen(final AtomicSpaceWarGame game) {
        super(game);
        bg = new CustomImage(getGame().getApp().findRegion("background"));
        bg.setPosition(0, 0);
        bg.setWidth(Tools.getScreen_width());
        bg.setHeight(Tools.getScreen_height());

        iTitle = new CustomImage(getGame().getApp().findRegion("title"));
        iTitle.setPosition(100, 160);
        iTitle.setRotation(30);

        btnMultiplayer = new CustomButton(Tools.getString("btnMultiplayer"), game);
        btnMultiplayer.addListener(new Listener() {
            @Override
            public void action() {
                game.goToScreen(SCREEN_MULTIPLAYER);
            }
        });
        btnMultiplayer.setDisabled(true);

        btnExit = new CustomButton(Tools.getString("btnExit"), game);
        btnExit.addListener(new Listener() {
            @Override
            public void action() {
                game.exitGame();
            }
        });

        /*groupButtons = new Group();*/
        groupButtons = new MenuGroup();
        groupButtons.setPosition(Tools.getScreen_width() / 2 + 200, Tools.getScreen_height() / 4);
        groupButtons.addActor(btnExit);
        groupButtons.addActor(btnMultiplayer);

    }

    @Override
    public void show() {
        super.show();
        getStage().addActor(bg);
        getStage().addActor(iTitle);
        getStage().addActor(groupButtons);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
