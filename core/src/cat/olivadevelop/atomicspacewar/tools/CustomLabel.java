package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Oliva on 19/02/2017.
 */

public class CustomLabel extends Label {

    public CustomLabel(CharSequence text, Skin skin) {
        super(text, skin);
        setTextSize(.7f);
    }

    public void setTextSize(float scaleXY) {
        this.setScale(scaleXY);
        this.setFontScale(scaleXY);
    }
}
