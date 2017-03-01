package cat.olivadevelop.atomicspacewar.actors.enviroment;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cat.olivadevelop.atomicspacewar.tools.ActorBox2D;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;

import static cat.olivadevelop.atomicspacewar.tools.Tools.METERS_IN_PIXELS;

/**
 * Created by Oliva on 01/03/2017.
 */

public class Bound extends ActorBox2D {

    public Bound(GenericScreen screen, World world, float x, float y, float w, float h) {
        //super(screen.getGame().getUI().findRegion("square_shadow"), screen, world, x, y);
        super(screen.getGame().getUI().findRegion("metalPanel"), screen, world, x, y);

        BodyDef def = new BodyDef();
        def.position.set(new Vector2(x * METERS_IN_PIXELS, y * METERS_IN_PIXELS));
        def.type = BodyDef.BodyType.StaticBody;
        setBody(world.createBody(def));

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(
                (w / 2) * METERS_IN_PIXELS,
                (h / 2) * METERS_IN_PIXELS
        );
        setFixture(getBody().createFixture(polygon, 1));
        polygon.dispose();

        getFixture().setUserData("bound");
        //setPosition(x, y);
        setWidth(w);
        setHeight(h);
    }
}
