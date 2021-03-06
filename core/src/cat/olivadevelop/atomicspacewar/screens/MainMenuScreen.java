package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.scenes.scene2d.Group;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.CustomButton;
import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Listener;
import cat.olivadevelop.atomicspacewar.tools.MenuGroup;
import cat.olivadevelop.atomicspacewar.tools.Tools;

import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_CONFIG;
import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MULTIPLAYER;
import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_SINGLEPLAYER;

/**
 * Created by Oliva on 19/02/2017.
 */

public class MainMenuScreen extends GenericScreen {

    private CustomButton btnMultiplayer;
    private CustomButton btnSingleplayer;
    private CustomButton btnConfig;
    private CustomButton btnExit;
    private Group groupButtons;
    private CustomImage iTitle;
    private CustomImage bg;

    public MainMenuScreen(final AtomicSpaceWarGame game) {
        super(game);
        bg = new CustomImage(getGame().getApp("background"));
        bg.setPosition(0, 0);
        bg.setWidth(Tools.getScreenWidth());
        bg.setHeight(Tools.getScreenHeight());

        iTitle = new CustomImage(getGame().getApp("title"));
        iTitle.setPosition(70, -100);
        iTitle.setScale(.8f);
        iTitle.setRotation(20);

        // Botón pantalla multijugador
        btnMultiplayer = new CustomButton(Tools.getString("btnMultiplayer"), game);
        btnMultiplayer.addListener(new Listener() {
            @Override
            public void action() {
                game.goToScreen(SCREEN_MULTIPLAYER);
            }
        });

        // Botón pantalla un jugador, desactivado por el momento
        btnSingleplayer = new CustomButton(Tools.getString("btnSingleplayer"), game);
        btnSingleplayer.addListener(new Listener() {
            @Override
            public void action() {
                game.goToScreen(SCREEN_SINGLEPLAYER);
            }
        });
        btnSingleplayer.setDisabled(true);

        // Botón de acceso a las preferéncias, desactivado de momento
        btnConfig = new CustomButton(Tools.getString("btnConfig"), game);
        btnConfig.addListener(new Listener() {
            @Override
            public void action() {
                game.goToScreen(SCREEN_CONFIG);
            }
        });
        btnConfig.setDisabled(true);

        btnExit = new CustomButton(Tools.getString("btnExit"), game);
        btnExit.addListener(new Listener() {
            @Override
            public void action() {
                game.exitGame();
            }
        });

        groupButtons = new MenuGroup();
        groupButtons.setPosition(Tools.getScreenWidth() / 2 + 200, Tools.getScreenHeight() / 4);
        groupButtons.addActor(btnExit);
        groupButtons.addActor(btnConfig);
        groupButtons.addActor(btnSingleplayer);
        groupButtons.addActor(btnMultiplayer);
    }

    @Override
    public void show() {
        super.show();
        getStage().addActor(bg);
        getStage().addActor(iTitle);
        getStage().addActor(groupButtons);
        // para el desarrollo
        getGame().goToScreen(SCREEN_MULTIPLAYER);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
