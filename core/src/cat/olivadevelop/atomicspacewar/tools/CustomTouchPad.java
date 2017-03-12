package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Oliva on 10/03/2017.
 */

public class CustomTouchPad extends Group {

    private Touchpad touchpad;

    public CustomTouchPad(GenericScreen screen) {
        TouchpadStyle tStyle = new TouchpadStyle();
        Drawable bg = new TextureRegionDrawable(new TextureRegion(screen.getGame().getControlImg("transparentDark09")));
        Drawable knob = new TextureRegionDrawable(new TextureRegion(screen.getGame().getControlImg("transparentDark49")));
        bg.setMinHeight(140);
        bg.setMinWidth(140);
        knob.setMinHeight(80);
        knob.setMinWidth(80);
        tStyle.background = bg;
        tStyle.knob = knob;
        touchpad = new Touchpad(10, tStyle);
        touchpad.setBounds(20, 80, 150, 150);
        addActor(touchpad);
    }

    public Touchpad getTouchpad() {
        return touchpad;
    }
}
