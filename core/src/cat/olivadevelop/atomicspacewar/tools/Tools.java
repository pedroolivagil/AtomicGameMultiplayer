package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by Oliva on 17/02/2017.
 */

public abstract class Tools {
    public static final float PIXELS_IN_METERS = 100f;
    public static final float METERS_IN_PIXELS = .01f;

    public static final int WIDTH_BUTTON = 280;
    public static final int HEIGHT_BUTTON = 100;
    public static final int ERROR_MARGIN_FONT = 10;
    public static final int MARGIN_BETWEEN_BTNS = 20;

    public static final String SCREEN_MAIN_MENU = "main-menu";
    public static final String SCREEN_MULTIPLAYER = "multiplayer";
    public static final String SCREEN_SINGLEPLAYER = "singleplayer";
    public static final String SCREEN_GAMEOVER_MULTIPLAYER = "game-over-multiplayer";
    public static final String SCREEN_GAMEOVER_SINGLEPLAYER = "game-over-singleplayer";

    private static final float screen_width = 1280;
    private static final float screen_height = 720;

    private static I18NBundle bundle;

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

    public static float getScreen_height() {
        return screen_height;
    }

    public static float getScreen_width() {
        return screen_width;
    }

    public static float resize(float anchoOriginal, float altoOriginal, float anchoDeseado) {
        return (anchoDeseado * altoOriginal) / anchoOriginal;
    }
}
