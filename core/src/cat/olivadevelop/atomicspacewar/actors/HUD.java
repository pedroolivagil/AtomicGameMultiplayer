package cat.olivadevelop.atomicspacewar.actors;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.CustomTouchPad;
import cat.olivadevelop.atomicspacewar.tools.GenericScreen;
import cat.olivadevelop.atomicspacewar.tools.Tools;

/**
 * Created by Oliva on 10/03/2017.
 */

public class HUD extends Group {

    private CustomTouchPad touchpad;
    private GenericScreen screen;
    private Group healthGroup;
    private Group shieldGroup;

    public HUD(GenericScreen screen) {
        this.screen = screen;
        setWidth(Tools.getScreenWidth());
        setHeight(Tools.getScreenHeight());

        touchpad = new CustomTouchPad(screen);
        healthGroup = new Group();
        CustomImage bgHealth = new CustomImage(new NinePatch(screen.getGame().getUI("glassPanel_cornerTR"), 14, 14, 14, 14));
        bgHealth.setBounds(10, 10, 300, 60);
        healthGroup.addActor(bgHealth);

        shieldGroup = new Group();
        CustomImage bgShield = new CustomImage(new NinePatch(screen.getGame().getUI("glassPanel_cornerTL"), 14, 14, 14, 14));
        bgShield.setBounds(Tools.getScreenWidth() - 310, 10, 300, 60);
        shieldGroup.addActor(bgShield);

        addActors();
        //debugAll();
    }

    private void addActors() {
        addActor(healthGroup);
        addActor(shieldGroup);
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
