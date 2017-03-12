package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Oliva on 12/03/2017.
 */

public class CustomPad extends Group {

    GenericScreen screen;
    CustomImage btnA;
    CustomImage btnB;
    CustomImage btnX;
    CustomImage btnY;

    public CustomPad(final GenericScreen screen) {
        this.screen = screen;

        btnA = new CustomImage(screen.getGame().getControlImg("transparentDark34"));
        btnB = new CustomImage(screen.getGame().getControlImg("transparentDark35"));
        btnX = new CustomImage(screen.getGame().getControlImg("transparentDark36"));
        btnY = new CustomImage(screen.getGame().getControlImg("transparentDark37"));

        btnA.addListener(new Listener() {
            @Override
            public void action() {
                Tools.logger(screen, "Button A Pressed");
            }
        });
        btnB.addListener(new Listener() {
            @Override
            public void action() {
                Tools.logger(screen, "Button B Pressed");
            }
        });
        btnX.addListener(new Listener() {
            @Override
            public void action() {
                Tools.logger(screen, "Button X Pressed");
            }
        });
        btnY.addListener(new Listener() {
            @Override
            public void action() {
                Tools.logger(screen, "Button Y Pressed");
            }
        });

        btnB.setBounds(Tools.getScreenWidth() - 100, 140, 80, 80);
        btnA.setBounds(Tools.getScreenWidth() - 170, 70, 80, 80);
        btnX.setBounds(Tools.getScreenWidth() - 240, 140, 80, 80);
        btnY.setBounds(Tools.getScreenWidth() - 170, 210, 80, 80);

        addActor(btnA);
        addActor(btnB);
        addActor(btnX);
        addActor(btnY);
    }

}
