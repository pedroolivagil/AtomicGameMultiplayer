package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.scenes.scene2d.Group;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.CustomButton;
import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Listener;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 19/02/2017.
 */

public class MainMenuScreen extends GenericScreen {

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

        btnExit = new CustomButton(Tools.getString("btnExit"), game);
        btnExit.addListener(new Listener(){
            @Override
            public void action() {
                game.exitGame();
            }
        });

        groupButtons = new Group();
        groupButtons.addActor(btnExit);
        groupButtons.debugAll();
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
