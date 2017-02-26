package cat.olivadevelop.atomicspacewar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;

import cat.olivadevelop.atomicspacewar.screens.MainMenuScreen;
import cat.olivadevelop.atomicspacewar.screens.MultiplayerScreen;
import cat.olivadevelop.atomicspacewar.screens.SplashScreen;
import cat.olivadevelop.atomicspacewar.tools.ColorGame;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.PlayServices;
import cat.olivadevelop.atomicspacewar.tools.ToastAction;
import cat.olivadevelop.atomicspacewar.tools.Xbox;

import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MAIN_MENU;
import static cat.olivadevelop.atomicspacewar.tools.Tools.SCREEN_MULTIPLAYER;

public class AtomicSpaceWarGame extends Game {

    private final PlayServices playServices;
    private final ToastAction toast;
    private final Xbox btnsPad;
    private AssetManager assets;

    private HashMap<String, GenericScreen> listScreens;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public AtomicSpaceWarGame(PlayServices playServices, ToastAction toast, Xbox btnsPad) {
        this.playServices = playServices;
        this.toast = toast;
        this.btnsPad = btnsPad;
    }

    @Override
    public void create() {
        assets = new AssetManager();

        tiledMap = new TmxMapLoader().load("map/space_map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

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
        Gdx.app.log("Navigate to", "" + nameScreen);
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

    public OrthogonalTiledMapRenderer getMapBackground() {
        //return new OrthogonalTiledMapRenderer(getAssets().get("map/space_map.tmx", TiledMap.class));
        return tiledMapRenderer;
    }

    public ToastAction getToast() {
        return toast;
    }

    public Xbox getBtnsPad() {
        return btnsPad;
    }

    public Skin getSkinL() {
        return getAssets().get("skin/L/uiskin.json");
    }

    public void exitGame() {
        super.dispose();
        Gdx.app.exit();
    }
}
