package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static cat.olivadevelop.atomicspacewar.tools.Tools.METERS_IN_PIXELS;
import static cat.olivadevelop.atomicspacewar.tools.Tools.PIXELS_IN_METERS;

/**
 * Created by Oliva on 19/02/2017.
 */

public class ActorBox2D extends Actor {

    private TextureRegion texture;
    private GenericScreen screen;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive;
    private int health;
    private int shield;

    public ActorBox2D(TextureRegion texture, GenericScreen screen, World world, float x, float y) {
        this.texture = texture;
        this.screen = screen;
        this.world = world;
        setAlive(true);
        setHealth(0);
        setShield(0);

        BodyDef def = new BodyDef();
        def.position.set(new Vector2(x * METERS_IN_PIXELS, y * METERS_IN_PIXELS));
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(
                (texture.getRegionWidth() / 2) * METERS_IN_PIXELS,
                (texture.getRegionHeight() / 2) * METERS_IN_PIXELS
        );
        fixture = body.createFixture(polygon, 1);
        polygon.dispose();

        setSize(texture.getRegionWidth() / 1.25f, texture.getRegionHeight() / 1.25f);
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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(body.getPosition().x * PIXELS_IN_METERS, body.getPosition().y * PIXELS_IN_METERS);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        remove();
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

    public Body getBody() {
        return body;
    }
}
