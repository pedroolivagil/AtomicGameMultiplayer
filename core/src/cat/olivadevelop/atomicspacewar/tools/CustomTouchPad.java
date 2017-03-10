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

        Drawable bg = new TextureRegionDrawable(new TextureRegion(screen.getGame().getUI("dot_shadow")));
        Drawable knob = new TextureRegionDrawable(new TextureRegion(screen.getGame().getUI("dotBlue")));

        bg.setMinHeight(100);
        bg.setMinWidth(100);

        knob.setMinHeight(45);
        knob.setMinWidth(45);

        tStyle.background = bg;
        tStyle.knob = knob;

        touchpad = new Touchpad(20, tStyle);
        touchpad.setBounds(20, 20, 100, 100);
        addActor(touchpad);
    }

    public Touchpad getTouchpad() {
        return touchpad;
    }
}
