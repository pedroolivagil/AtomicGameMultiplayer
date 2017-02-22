package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;

/**
 * Created by Oliva on 22/02/2017.
 */

public class CustomButton extends Group {

    private CustomLabel label;
    private CustomImage bg;

    public CustomButton(String text, AtomicSpaceWarGame game) {
        label = new CustomLabel(text.toUpperCase(), game.getSkinL());
        bg = new CustomImage(game.getUI().findRegion("glassPanel_corners"));

        addActor(bg);
        addActor(label);
    }

    private float getScale() {
        return super.getScaleX();
    }

    @Override
    public void setColor(Color color) {
        label.setColor(color);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width * getScale());
        bg.setWidth(width + (width * .1f));
        label.setX(bg.getWidth() / 2 - label.getWidth() / 2);
    }

    public CustomButton center() {
        label.setAlignment(Align.center);
        bg.setAlign(Align.center);
        return this;
    }
}
