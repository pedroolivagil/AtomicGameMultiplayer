package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by Oliva on 17/02/2017.
 */

public abstract class Tools {
    // Conversores
    public static final float PIXELS_IN_METERS = 100f;
    public static final float METERS_IN_PIXELS = .01f;

    public static final int WIDTH_BUTTON = 280;
    public static final int HEIGHT_BUTTON = 100;
    public static final int ERROR_MARGIN_FONT = 10;
    public static final int MARGIN_BETWEEN_BTNS = 20;

    // Pantallas
    public static final String SCREEN_MAIN_MENU = "main-menu";
    public static final String SCREEN_MULTIPLAYER = "multiplayer";
    public static final String SCREEN_SINGLEPLAYER = "singleplayer";
    public static final String SCREEN_CONFIG = "config-screen";
    public static final String SCREEN_GAMEOVER_MULTIPLAYER = "game-over-multiplayer";
    public static final String SCREEN_GAMEOVER_SINGLEPLAYER = "game-over-singleplayer";

    public static final float GRAVITY_X = 0f;
    public static final float GRAVITY_Y = 0f;

    public static final float PLAYER_SPEED = 3f;
    public static final float PLAYER_SUPER_SPEED = 3f;
    public static final float PLAYER_HIPER_SPEED = 8f;

    public static final int TILED_MAP_H = 12800;
    public static final int TILED_MAP_W = 12800;
    public static final int BOUND_LARGE = 1000;
    public static final int BOUND_SMALL = 10;

    private static I18NBundle bundle;

    private static final float SCREEN_WIDTH = 1280;
    private static final float SCREEN_HEIGHT = 720;

    private static I18NBundle getBundle() {
        if (bundle == null) {
            bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/MyBundle"), Locale.getDefault());
        }
        return bundle;
    }

    // Bundle
    public static String getString(String key) {
        return getBundle().format(key);
    }

    public static float getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static float getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static float resize(float anchoOriginal, float altoOriginal, float anchoDeseado) {
        return (anchoDeseado * altoOriginal) / anchoOriginal;
    }
}
