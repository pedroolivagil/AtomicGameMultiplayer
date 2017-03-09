package cat.olivadevelop.atomicspacewar.tools;

/**
 * Created by Oliva on 09/03/2017.
 */

public enum BotonesXbox {

    BUTTON_X("BUTTON_X"),
    BUTTON_Y("BUTTON_Y"),
    BUTTON_A("BUTTON_A"),
    BUTTON_B("BUTTON_B"),
    BUTTON_DPAD_UP("BUTTON_DPAD_UP"),
    BUTTON_DPAD_DOWN("BUTTON_DPAD_DOWN"),
    BUTTON_DPAD_RIGHT("BUTTON_DPAD_RIGHT"),
    BUTTON_DPAD_LEFT("BUTTON_DPAD_LEFT"),
    BUTTON_LB("BUTTON_LB"),
    BUTTON_L3("BUTTON_L3"),
    BUTTON_RB("BUTTON_RB"),
    BUTTON_R3("BUTTON_R3"),
    AXIS_LEFT_X("AXIS_LEFT_X"),
    AXIS_LEFT_Y("AXIS_LEFT_Y"),
    AXIS_LEFT_TRIGGER("AXIS_LEFT_TRIGGER"),
    AXIS_RIGHT_X("AXIS_RIGHT_X"),
    AXIS_RIGHT_Y("AXIS_RIGHT_Y"),
    AXIS_RIGHT_TRIGGER("AXIS_RIGHT_TRIGGER"),
    BUTTON_START("BUTTON_START"),
    BUTTON_SELECT("BUTTON_SELECT");

    private final String code;

    BotonesXbox(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
