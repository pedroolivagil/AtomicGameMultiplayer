package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;

/**
 * Created by Oliva on 17/02/2017.
 */

public class GenericScreen implements Screen, ControllerListener, InputProcessor, ContactListener {

    private AtomicSpaceWarGame game;
    private World world;
    private Stage stage;
    private boolean useWorld;
    private Controller pad = null;
    private boolean initGamePad;
    private boolean debugAllStage;
    private Box2DDebugRenderer renderer;

    public GenericScreen(AtomicSpaceWarGame game) {
        this.game = game;
        this.useWorld = false;
        this.debugAllStage = false;
    }

    public GenericScreen(AtomicSpaceWarGame game, boolean useWorld) {
        this.game = game;
        this.useWorld = useWorld;
        debugAllStage = false;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Tools.getScreenWidth(), Tools.getScreenHeight()));
        if (useWorld) {
            world = new World(new Vector2(Tools.GRAVITY_X, Tools.GRAVITY_Y), true);
            world.setContactListener(this);
            renderer = new Box2DDebugRenderer();
        }
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addListener(new Listener() {
            @Override
            public void action(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    actionBackButton();
                }
                actionOtherButton(event, keycode);
            }
        });
        Controllers.addListener(this);
        checkGamPad();
        initGamePad = false;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (useWorld) {
            world.step(1 / 60f, 6, 2);
            stage.act();
            renderer.render(world, getStage().getCamera().combined);
        }
        actionsRender(delta);

        if (debugAllStage) {
            for (Actor a : getStage().getActors()) {
                a.debug();
            }
        }

        if (useWorld) {
            stage.draw();
        } else {
            stage.act();
            stage.draw();
        }
    }

    protected void actionsRender(float delta) {
        checkGamPad();
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
            renderer.dispose();
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

    public void setDebugAllStage(boolean debugAllStage) {
        this.debugAllStage = debugAllStage;
    }

    public void actionBackButton() {

    }

    public void actionOtherButton(InputEvent event, int keycode) {

    }

    @Override
    public void connected(Controller controller) {
        for (Controller c : Controllers.getControllers()) {
            System.out.println(c.getName());
            Gdx.app.log("Controller", c.getName());
        }
    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void setPad(Controller c) {
        pad = c;
    }

    protected void checkGamPad() {
        if (pad != null) return;
        if (!initGamePad) {
            initGamePad = true;
            for (Controller c : Controllers.getControllers()) {
                System.out.println(c.getName());
                Gdx.app.log("Controller", c.getName());
            }
            Array<Controller> controllers = Controllers.getControllers();
            if (controllers.size == 0) {
                getGame().getToast().show("You not have a controller");
            } else {
                getGame().getToast().show("You have a controller");
                for (Controller c : controllers) {
                    if (c.getName().toLowerCase().contains("xbox") && c.getName().contains("360") ||
                            (c.getName().toLowerCase().contains("gamesir"))) {
                        setPad(c);
                    }
                }
            }
        }
    }

    protected boolean isGamePadActive() {
        return Controllers.getControllers().size != 0;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void setInitGamePad(boolean initGamePad) {
        this.initGamePad = initGamePad;
    }
}
