package cat.olivadevelop.atomicspacewar.actors.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cat.olivadevelop.atomicspacewar.tools.ColorGame;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 09/03/2017.
 */

public class FirePlayer extends Actor {

    private final Animation animation;
    private float elapsedTime;

    public FirePlayer(GenericScreen screen) {
        TextureRegion[][] tmp = new TextureRegion[][]{
                {
                        screen.getGame().getUI("fire14"),
                        screen.getGame().getUI("fire15")
                }
        };
        animation = new Animation(1 / 10f, Tools.getSprites(2, 1, tmp));
        setHeight(31);
        setWidth(14);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setScale(1);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(ColorGame.WHITE);
        super.draw(batch, parentAlpha);
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime, true), getX() - getWidth() / 2, getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
