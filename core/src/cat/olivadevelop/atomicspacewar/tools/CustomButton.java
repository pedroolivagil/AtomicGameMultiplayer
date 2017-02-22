package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Align;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;

import static cat.olivadevelop.atomicspacewar.tools.Tools.ERROR_MARGIN_FONT;
import static cat.olivadevelop.atomicspacewar.tools.Tools.HEIGHT_BUTTON;
import static cat.olivadevelop.atomicspacewar.tools.Tools.WIDTH_BUTTON;

/**
 * Created by Oliva on 22/02/2017.
 */

public class CustomButton extends Group implements Disableable {

    private CustomLabel label;
    private CustomImage bg;
    private AtomicSpaceWarGame game;
    private boolean disabled;

    public CustomButton(String text, AtomicSpaceWarGame game) {
        this.game = game;
        label = new CustomLabel(text, game.getSkinL());
        bg = new CustomImage(new NinePatch(game.getUI().findRegion("glassPanel"), 14, 14, 14, 14)); //glassPanel_corners
        label.setTextSize(1);
        setScale(.7f);
        setWidth(WIDTH_BUTTON * (1 + getScale()));
        setHeight(HEIGHT_BUTTON);
        addActor(label);
        addActor(bg);
        center();
        label.setY((getHeight() / 2f - label.getHeight() / 2f) + ERROR_MARGIN_FONT);
        disabled = false;
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

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isDisabled()) {
            bg.setColor(ColorGame.GRAY);
            setColor(ColorGame.GRAY);
        }
    }

}
