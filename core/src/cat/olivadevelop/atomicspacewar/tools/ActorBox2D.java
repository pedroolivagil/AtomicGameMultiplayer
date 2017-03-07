package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_ENVIROMENT;
import static cat.olivadevelop.atomicspacewar.tools.Tools.convertMetersInPixels;
import static cat.olivadevelop.atomicspacewar.tools.Tools.convertPixelsInMeters;

/**
 * Created by Oliva on 19/02/2017.
 */

public class ActorBox2D extends Actor {

    private ShapeRenderer shape;
    private PolygonShape polygon;
    private TextureRegion texture;
    private GenericScreen screen;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive;
    private int health;
    private int shield;

    /**
     * @param texture
     * @param screen
     * @param world
     * @param x       in meters
     * @param y       in meters
     */
    public ActorBox2D(TextureRegion texture, GenericScreen screen, World world, float x, float y) {
        this.texture = texture;
        this.screen = screen;
        this.world = world;
        setAlive(true);
        setHealth(0);
        setShield(0);

        BodyDef def = new BodyDef();
        //def.position.set(new Vector2(convertMetersInPixels(x), convertMetersInPixels(y)));
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        polygon = new PolygonShape();
        polygon.setAsBox(
                convertPixelsInMeters(texture.getRegionWidth() / 2),
                convertPixelsInMeters(texture.getRegionHeight() / 2)
        );
        fixture = body.createFixture(polygon, 1);
        polygon.dispose();

        setSize(texture.getRegionWidth(), texture.getRegionHeight());
        setName(FIXTURE_ENVIROMENT);
        shape = new ShapeRenderer();
        drawDebug(shape);

        setPosition(convertMetersInPixels(x), convertMetersInPixels(y));
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        fixture.setUserData(name);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public Actor debug() {
        shape.setProjectionMatrix(getScreen().getStage().getCamera().combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(ColorGame.YELLOW);
        shape.rect(getX(), getY(), texture.getRegionWidth(), texture.getRegionHeight());
        shape.end();
        return super.debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(convertPixelsInMeters(body.getPosition().x), convertPixelsInMeters(body.getPosition().y));
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void born() {
        setAlive(true);
    }

    public void death() {
        setAlive(false);
    }

    @Override
    public boolean remove() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        return super.remove();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        body.setTransform(x + (getWidth() / 2), y + (getHeight() / 2), 0);
    }

    public GenericScreen getScreen() {
        return screen;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
