package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;

/**
 * Created by Oliva on 17/02/2017.
 */

public class GenericScreen extends OverlapListener implements Screen {

    private AtomicSpaceWarGame game;
    private World world;
    private Stage stage;
    private boolean useWorld;

    public GenericScreen(AtomicSpaceWarGame game) {
        this.game = game;
        this.useWorld = false;
    }

    public GenericScreen(AtomicSpaceWarGame game, boolean useWorld) {
        this.game = game;
        this.useWorld = useWorld;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Tools.getScreen_width(), Tools.getScreen_height()));
        if (useWorld) {
            world = new World(new Vector2(0, -10), true);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        actionsRender();

        stage.act();
        if (useWorld) {
            world.step(1 / 60f, 6, 2);
        }
        stage.draw();
    }

    protected void actionsRender() {

    }

    @Override
    public void resize(int width, int height) {
        getStage().getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        if (useWorld) {
            world.dispose();
        }
    }

    public AtomicSpaceWarGame getGame() {
        return game;
    }

    protected Stage getStage() {
        return stage;
    }

    public World getWorld() {
        if (useWorld) {
            return world;
        } else {
            return null;
        }
    }
}
