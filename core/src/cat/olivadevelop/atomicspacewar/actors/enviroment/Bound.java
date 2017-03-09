package cat.olivadevelop.atomicspacewar.actors.enviroment;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cat.olivadevelop.atomicspacewar.tools.ColorGame;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

import static cat.olivadevelop.atomicspacewar.tools.Tools.FIXTURE_BOUND;
import static cat.olivadevelop.atomicspacewar.tools.Tools.convertMetersInPixels;

/**
 * Created by Oliva on 01/03/2017.
 */

public class Bound extends Actor {

    private ShapeRenderer shape;
    private GenericScreen screen;
    private World world;
    private Body body;
    private Fixture fixture;
    private TextureRegion texture;

    public Bound(GenericScreen screen, World world, float x, float y, float w, float h) {
        texture = screen.getGame().getUI("square_shadow");
        this.screen = screen;
        this.world = world;

        BodyDef def = new BodyDef();
        def.position.set(new Vector2((x), (y)));
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(
                convertMetersInPixels(w / 2),
                convertMetersInPixels(h / 2)
        );
        fixture = body.createFixture(polygon, 1f);
        polygon.dispose();

        setName(FIXTURE_BOUND);
        fixture.setUserData(FIXTURE_BOUND);

        shape = new ShapeRenderer();
        drawDebug(shape);

        setSize(convertMetersInPixels(w), convertMetersInPixels(h));
        setPosition(convertMetersInPixels(x), convertMetersInPixels(y));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), (getWidth() / 2f), (getHeight() / 2f), (getWidth()), (getHeight()), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public Actor debug() {
        shape.setProjectionMatrix(screen.getStage().getCamera().combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(ColorGame.RED);
        shape.rect(getX(), getY(), getWidth(), getHeight());
        shape.end();
        return super.debug();
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
}
