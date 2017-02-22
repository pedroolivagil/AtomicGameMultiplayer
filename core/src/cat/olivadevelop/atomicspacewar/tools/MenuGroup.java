package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import static cat.olivadevelop.atomicspacewar.tools.Tools.MARGIN_BETWEEN_BTNS;

/**
 * Created by Oliva on 22/02/2017.
 */
public class MenuGroup extends Group {

    @Override
    public void addActor(Actor actor) {
        if (hasChildren()) {
            Actor a = getChildren().get(getChildren().size - 1);
            actor.setY(a.getY() + a.getHeight() - MARGIN_BETWEEN_BTNS);
            Gdx.app.log("MENU BTNS ACTOR Y", "" + (a.getY() + a.getHeight() - MARGIN_BETWEEN_BTNS));
        }
        super.addActor(actor);
    }
}
