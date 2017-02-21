package cat.olivadevelop.atomicspacewar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;

import cat.olivadevelop.atomicspacewar.screens.MainMenuScreen;
import cat.olivadevelop.atomicspacewar.screens.SplashScreen;
import cat.olivadevelop.atomicspacewar.tools.ColorGame;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

public class AtomicSpaceWarGame extends Game {

    private AssetManager assets;

    private HashMap<String, GenericScreen> listScreens;
    private GenericScreen mainMenu;

    public static final String MAIN_MENU_SCREEN = "main-menu";
    public static final String MULTIPLAYER_SCREEN = "multiplayer";
    public static final String SINGLEPLAYER_SCREEN = "singleplayer";
    public static final String GAMEOVER_MULTIPLAYER_SCREEN = "game-over-multiplayer";
    public static final String GAMEOVER_SINGLEPLAYER_SCREEN = "game-over-singleplayer";

    @Override
    public void create() {
        assets = new AssetManager();
        ColorGame.initColorGame();
        assets.load("skin/L/uiskin.atlas", TextureAtlas.class);
        assets.load("skin/L/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/L/uiskin.atlas"));
        assets.load("skin/XL/uiskin.atlas", TextureAtlas.class);
        assets.load("skin/XL/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/XL/uiskin.atlas"));
        assets.load("skin/S/uiskin.atlas", TextureAtlas.class);
        assets.load("skin/S/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/S/uiskin.atlas"));
        assets.load("textures/app.atlas", TextureAtlas.class);
        assets.load("textures/enemy_ship.atlas", TextureAtlas.class);
        assets.load("textures/ui.atlas", TextureAtlas.class);
        assets.load("sounds/explosion_spaceship.mp3", Sound.class);
        assets.load("sounds/level_up.mp3", Sound.class);
        assets.load("sounds/power_up.mp3", Sound.class);
        assets.load("sounds/shoot_laser.mp3", Sound.class);
        assets.load("sounds/shoot_normal.mp3", Sound.class);
        assets.load("sounds/shoot_plasma.mp3", Sound.class);
        assets.load("enviroment/enviroment_edgy.mp3", Music.class);
        assets.load("enviroment/enviroment_quiet.mp3", Music.class);
        setScreen(new SplashScreen(this));
    }

    public void finishLoad() {
        loadScreens();
        //goToScreen(MAIN_MENU_SCREEN);
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
            mainMenu = new MainMenuScreen(this);
            listScreens.put(MAIN_MENU_SCREEN, mainMenu);
        }
    }

    public GenericScreen goToScreen(String nameScreen) {
        return listScreens.get(nameScreen);
    }

    public TextureAtlas getUI(){
        return getAssets().get("textures/ui.atlas");
    }

    public Skin getSkinL(){
        return getAssets().get("skin/L/uiskin.json");
    }
}
