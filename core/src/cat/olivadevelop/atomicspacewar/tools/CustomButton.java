package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;

import static cat.olivadevelop.atomicspacewar.tools.Tools.HEIGHT_BUTTON;
import static cat.olivadevelop.atomicspacewar.tools.Tools.WIDTH_BUTTON;

/**
 * Created by Oliva on 22/02/2017.
 */

public class CustomButton extends Group {

    private CustomLabel label;
    private CustomImage bg;

    public CustomButton(String text, AtomicSpaceWarGame game) {
        label = new CustomLabel(text, game.getSkinL());
        bg = new CustomImage(new NinePatch(game.getUI().findRegion("glassPanel_projection"), 14, 14, 14, 14)); //glassPanel_corners
        //bg.setScale(.7f);
        label.setTextSize(1);
        setScale(.7f);

        setWidth(WIDTH_BUTTON * (1 + getScale()));
        setHeight(HEIGHT_BUTTON);
        addActor(label);
        addActor(bg);
        center();
        label.setY(getHeight() / 2f + label.getHeight() / 7f);
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
