package cat.olivadevelop.atomicspacewar.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import cat.olivadevelop.atomicspacewar.tools.CustomTouchPad;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 10/03/2017.
 */

public class HUD extends Group {

    private CustomTouchPad touchpad;
    GenericScreen screen;

    public HUD(GenericScreen screen) {
        this.screen = screen;
        touchpad = new CustomTouchPad(screen);

        setWidth(Tools.getScreenWidth());
        setHeight(Tools.getScreenHeight());
    }

    public void addTouchpad() {
        // usamos false en el contains para comparar con equals()
        if (!getChildren().contains(touchpad, false)) {
            addActor(touchpad);
        }
    }

    public void removeTouchpad() {
        removeActor(touchpad);
    }

    public Touchpad getTouchpad() {
        return touchpad.getTouchpad();
    }
}
