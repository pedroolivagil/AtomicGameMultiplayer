package cat.olivadevelop.atomicspacewar.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cat.olivadevelop.atomicspacewar.AtomicSpaceWarGame;
import cat.olivadevelop.atomicspacewar.tools.Tools;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = (int) Tools.resize(1280, 720, 800);
        config.width = 800;
        config.y = 250;
        config.x = -1350;
        config.addIcon("logo.png", Files.FileType.Internal);
        config.title = "OlivaDevelop Box Games - Atomic SpaceWar";
        config.useGL30 = false;
        new LwjglApplication(new AtomicSpaceWarGame(new Play(), new ToastDesktop(), new XboxDesktop()), config);
    }
}
