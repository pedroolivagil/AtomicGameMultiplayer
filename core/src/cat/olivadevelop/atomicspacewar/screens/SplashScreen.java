package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 19/02/2017.
 */

public class SplashScreen extends GenericScreen {

    private CustomImage logo;
    private TextureAtlas appAtlas;

    public SplashScreen(AtomicSpaceWarGame game) {
        super(game);
        game.getAssets().load("skin/L/uiskin.atlas", TextureAtlas.class);
        game.getAssets().load("skin/L/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/L/uiskin.atlas"));
        game.getAssets().load("skin/XL/uiskin.atlas", TextureAtlas.class);
        game.getAssets().load("skin/XL/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/XL/uiskin.atlas"));
        game.getAssets().load("skin/S/uiskin.atlas", TextureAtlas.class);
        game.getAssets().load("skin/S/uiskin.json", Skin.class, new SkinLoader.SkinParameter("skin/S/uiskin.atlas"));
        game.getAssets().load("textures/app.atlas", TextureAtlas.class);
        game.getAssets().load("textures/ships.atlas", TextureAtlas.class);
        game.getAssets().load("textures/ui.atlas", TextureAtlas.class);
        game.getAssets().load("sounds/explosion_spaceship.mp3", Sound.class);
        game.getAssets().load("sounds/level_up.mp3", Sound.class);
        game.getAssets().load("sounds/power_up.mp3", Sound.class);
        game.getAssets().load("sounds/shoot_laser.mp3", Sound.class);
        game.getAssets().load("sounds/shoot_normal.mp3", Sound.class);
        game.getAssets().load("sounds/shoot_plasma.mp3", Sound.class);
        game.getAssets().load("enviroment/enviroment_edgy.mp3", Music.class);
        game.getAssets().load("enviroment/enviroment_quiet.mp3", Music.class);

        appAtlas = new TextureAtlas("textures/app.atlas");

        logo = new CustomImage(appAtlas.findRegion("logo"));
        logo.setPosition(
                Tools.getScreen_width() / 2 - logo.getWidth() / 2,
                Tools.getScreen_height() / 2 - logo.getHeight() / 2
        );
    }

    @Override
    public void show() {
        super.show();
        getStage().addActor(logo);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                getGame().setScreen(new LoadScreen(getGame()));
            }
        }, 2f);
    }

    @Override
    public void dispose() {
        appAtlas.dispose();
        super.dispose();
    }
}
