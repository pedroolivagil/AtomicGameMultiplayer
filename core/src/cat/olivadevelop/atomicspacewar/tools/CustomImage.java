package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by Oliva on 19/02/2017.
 */

public class CustomImage extends Image {

    public CustomImage(GenericScreen screen, String texture) {
        super(screen.getGame().getUI(texture));
    }

    public CustomImage(NinePatch patch) {
        super(patch);
    }

    public CustomImage(TextureRegion region) {
        super(region);
    }

    public CustomImage(Texture texture) {
        super(texture);
    }

    public CustomImage(Skin skin, String drawableName) {
        super(skin, drawableName);
    }

    public CustomImage(Drawable drawable) {
        super(drawable);
    }

    public CustomImage(Drawable drawable, Scaling scaling) {
        super(drawable, scaling);
    }

    public CustomImage(Drawable drawable, Scaling scaling, int align) {
        super(drawable, scaling, align);
    }
}
