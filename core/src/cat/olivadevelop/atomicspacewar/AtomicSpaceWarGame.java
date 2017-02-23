package cat.olivadevelop.atomicspacewar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;

import cat.olivadevelop.atomicspacewar.screens.MainMenuScreen;
import cat.olivadevelop.atomicspacewar.screens.MultiplayerScreen;
import cat.olivadevelop.atomicspacewar.screens.SplashScreen;
import cat.olivadevelop.atomicspacewar.tools.ColorGame;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MAIN_MENU;
import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MULTIPLAYER;

public class AtomicSpaceWarGame extends Game {

    private AssetManager assets;

    private HashMap<String, GenericScreen> listScreens;

    @Override
    public void create() {
        assets = new AssetManager();
        ColorGame.initColorGame();
        setScreen(new SplashScreen(this));
    }

    public void finishLoad() {
        loadScreens();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                goToScreen(SCREEN_MAIN_MENU);
            }
        }, .5f);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        assets.dispose();
    }

    public AssetManager getAssets() {
        return assets;
    }

    private void loadScreens() {
        if (listScreens == null) {
            listScreens = new HashMap<String, GenericScreen>();
            GenericScreen mainMenuScreen = new MainMenuScreen(this);
            GenericScreen multiplayerScreen = new MultiplayerScreen(this);
            listScreens.put(SCREEN_MAIN_MENU, mainMenuScreen);
            listScreens.put(SCREEN_MULTIPLAYER, multiplayerScreen);
        }
    }

    public void goToScreen(String nameScreen) {
        setScreen(listScreens.get(nameScreen));
    }

    public TextureAtlas getUI() {
        return getAssets().get("textures/ui.atlas");
    }

    public TextureAtlas getUIShips() {
        return getAssets().get("textures/ships.atlas");
    }

    public TextureAtlas getApp() {
        return getAssets().get("textures/app.atlas");
    }

    public Skin getSkinL() {
        return getAssets().get("skin/L/uiskin.json");
    }

    public void exitGame() {
        super.dispose();
        Gdx.app.exit();
    }
}
