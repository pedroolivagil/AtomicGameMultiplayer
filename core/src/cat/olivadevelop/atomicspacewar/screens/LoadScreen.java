package cat.olivadevelop.atomicspacewar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.ProgressBar;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 19/02/2017.
 */

public class LoadScreen extends GenericScreen {

    private ProgressBar progressBar;
    private CustomImage logo;
    private TextureAtlas appAtlas;
    private boolean cargaTerminada;
    private Skin skin;

    public LoadScreen(AtomicSpaceWarGame game) {
        super(game);
        cargaTerminada = false;

        skin = new Skin(Gdx.files.internal("skin/L/uiskin.json"));
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
        progressBar = new ProgressBar(this, true);
        progressBar.setProgress(0f);
        getStage().addActor(progressBar);
    }

    @Override
    public void dispose() {
        skin.dispose();
        appAtlas.dispose();
        super.dispose();
    }

    @Override
    protected void actionsRender(float delta) {
        super.actionsRender(delta);
        if (!cargaTerminada) {
            if (getGame().getAssets().update()) {
                loadMainMenu();
            } else {
                progressBar.setProgress(getGame().getAssets().getProgress());
            }
        }
    }

    private void loadMainMenu() {
        cargaTerminada = true;
        Gdx.app.log("Load assets", "success");
        progressBar.setProgress(1);
        getGame().finishLoad();
    }
}
