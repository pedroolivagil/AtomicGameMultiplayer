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
    private CustomImage image;
    private CustomImage image2;

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
        Gdx.input.setInputProcessor(stage);
        if (useWorld) {
            world = new World(new Vector2(Tools.GRAVITY_X, Tools.GRAVITY_Y), true);
        }
    }

    public void showCenter() {
        image = new CustomImage(getGame().getUI().findRegion("barHorizontal_blue_right"));
        image.setHeight(Tools.getScreen_height());
        image.setWidth(2);
        image.setPosition(Tools.getScreen_width() / 2f, 0);
        getStage().addActor(image);

        image2 = new CustomImage(getGame().getUI().findRegion("barHorizontal_blue_right"));
        image2.setHeight(2);
        image2.setWidth(Tools.getScreen_width());
        image2.setPosition(0, Tools.getScreen_height() / 2f);
        getStage().addActor(image2);
    }

    public void moveCenter(float x, float y) {
        image.setPosition(x + Tools.getScreen_width() / 2f, 0);
        image2.setPosition(0, y + Tools.getScreen_height() / 2f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        actionsRender(delta);

        stage.act();
        if (useWorld) {
            world.step(1 / 60f, 6, 2);
        }
        stage.draw();
    }

    protected void actionsRender(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    public Stage getStage() {
        return stage;
    }

    public World getWorld() {
        return world;
    }
}
