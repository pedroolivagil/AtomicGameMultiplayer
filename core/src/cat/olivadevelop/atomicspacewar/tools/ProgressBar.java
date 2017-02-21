package cat.olivadevelop.atomicspacewar.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Oliva on 20/02/2017.
 */

public class ProgressBar extends Group {

    private GenericScreen screen;
    private CustomImage bg_shadow;
    private CustomImage fg_bar;
    private CustomLabel text;
    private float progress;

    /**
     * @param screen  Pantalla en la que se muestra
     * @param initial Indica si el Actor tiene las texturas cargadas o no.
     */
    public ProgressBar(GenericScreen screen, boolean initial) {
        this.screen = screen;
        if (initial) {
            init(new TextureAtlas("textures/ui.atlas"), new Skin(Gdx.files.internal("skin/L/uiskin.json")));
        } else {
            init(screen.getGame().getUI(), screen.getGame().getSkinL());
        }
    }

    private void init(TextureAtlas textureAtlas, Skin skin) {
        setWidth(400);
        setHeight(40);
        setPosition((Tools.getScreen_width() / 2) - (getWidth() / 2), 20);
        bg_shadow = new CustomImage(new NinePatchDrawable(new NinePatch(textureAtlas.findRegion("square_shadow"), 8, 8, 8, 8)));
        bg_shadow.setWidth(getWidth());
        bg_shadow.setHeight(getHeight());

        fg_bar = new CustomImage(new NinePatchDrawable(new NinePatch(textureAtlas.findRegion("squareWhite"), 8, 8, 8, 8)));
        fg_bar.setWidth(0);
        fg_bar.setHeight(getHeight());

        text = new CustomLabel("", skin);
        text.setTextSize(.5f);
        text.setPosition(bg_shadow.getX() + bg_shadow.getWidth() + 15, getY() - (text.getHeight() / 2) + 5);

        addActor(bg_shadow);
        addActor(fg_bar);
        addActor(text);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getProgress() >= 0) {
            fg_bar.setWidth(getProgress() * getWidth() / 100);
            text.setText(getProgress() + "%");
        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return (int) (progress * 100);
    }
}
