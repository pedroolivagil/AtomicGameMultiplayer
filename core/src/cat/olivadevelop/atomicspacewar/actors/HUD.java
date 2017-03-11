package cat.olivadevelop.atomicspacewar.actors;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import cat.olivadevelop.atomicspacewar.tools.CustomImage;
import cat.olivadevelop.atomicspacewar.tools.CustomLabel;
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

    private int borderX = 10;
    private int borderY = 10;
    private int width = 350;
    private int height = 50;
    private int limitBarsHealthShield = 6;

    public HUD(GenericScreen screen) {
        this.screen = screen;
        setWidth(Tools.getScreenWidth());
        setHeight(Tools.getScreenHeight());

        touchpad = new CustomTouchPad(screen);
        // Health group
        healthGroup = new Group();
        CustomImage bgHealth = new CustomImage(new NinePatch(screen.getGame().getUI("glassPanel_cornerTR"), 14, 14, 14, 14));
        bgHealth.setBounds(0, 0, width, height);
        CustomLabel bgLabelHealth = new CustomLabel(screen, Tools.getString("hudLabelHealth"));
        bgLabelHealth.setTextSize(.5f);
        bgLabelHealth.setBounds(15, -13f, bgLabelHealth.getWidth(), bgLabelHealth.getHeight());

        healthGroup.setBounds(borderX, borderY, width, height);
        healthGroup.addActor(bgHealth);
        healthGroup.addActor(bgLabelHealth);
        for (int x = 0; x < limitBarsHealthShield; x++) {
            CustomImage bgShadow = new CustomImage(screen, "square_shadow");
            bgShadow.setPosition(
                    (width - 185) + (x * (bgShadow.getWidth() + 10)),
                    12
            );
            healthGroup.addActor(bgShadow);
        }

        // Shield Group
        shieldGroup = new Group();
        CustomImage bgShield = new CustomImage(new NinePatch(screen.getGame().getUI("glassPanel_cornerTL"), 14, 14, 14, 14));
        bgShield.setBounds(0, 0, width, height);
        CustomLabel bgLabelShield = new CustomLabel(screen, Tools.getString("hudLabelShield"));
        bgLabelShield.setTextSize(.5f);
        bgLabelShield.setBounds(width - (bgLabelShield.getWidth() * .5f) - 15, -13f, bgLabelShield.getWidth(), bgLabelShield.getHeight());

        shieldGroup.setBounds(Tools.getScreenWidth() - width - borderX, borderY, width, height);
        shieldGroup.addActor(bgShield);
        shieldGroup.addActor(bgLabelShield);
        for (int x = 0; x < limitBarsHealthShield; x++) {
            CustomImage bgShadow = new CustomImage(screen, "square_shadow");
            bgShadow.setPosition(
                    20 + (x * (bgShadow.getWidth() + 10)),
                    12
            );
            shieldGroup.addActor(bgShadow);
        }

        addActors();
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
