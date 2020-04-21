package ooga.engine.generic;

import java.util.ResourceBundle;

public class GameRules {

    private ResourceBundle props;
    public double FLOOR_HEIGHT;
    public double INITIAL_X_POS;
    public int SCORE_X;
    public int SCORE_Y;
    public int SCORE_TEXT_SIZE;
    public String VERSION_NAME;
    public String PLAYER_IMAGE;
    public String BACKGROUND_IMAGE;
    public String LEVEL_CSV;
    public String ENEMY_PNG;
    public double ENEMY_SPEED;
    public double PLAYER_X_OFFSET;
    public double PLAYER_Y_OFFSET;
    public double ENEMY_X_OFFSET;
    public double ENEMY_Y_OFFSET;
    public double ENEMY_STANDARD_Y;
    public double GRAVITY;
    public double DEFAULT_JUMP_STRENGTH;
    public boolean START_FALLING;
    public boolean PROPULSION;
    public boolean ALLOW_COINS;

    public GameRules(){
        String path = "ooga.engine.generic.GameRules";
        props = ResourceBundle.getBundle(path);

        FLOOR_HEIGHT = Double.parseDouble(props.getString("FLOOR-HEIGHT"));
        INITIAL_X_POS = Double.parseDouble(props.getString("INITIAL-X-POS"));
        SCORE_X = Integer.parseInt(props.getString("SCORE-X-COORD"));
        SCORE_Y = Integer.parseInt(props.getString("SCORE-Y-COORD"));
        SCORE_TEXT_SIZE = Integer.parseInt(props.getString("SCORE-TEXT-SIZE"));
        VERSION_NAME = props.getString("VERSION-NAME");
        PLAYER_IMAGE  = props.getString("PLAYER-PNG");
        BACKGROUND_IMAGE = props.getString("BACKGROUND-PNG");
        LEVEL_CSV = props.getString("CSV-PATH");
        ENEMY_PNG = props.getString("ENEMY-PNG");
        ENEMY_SPEED = Double.parseDouble(props.getString("ENEMY-SPEED"));
        PLAYER_X_OFFSET = Double.parseDouble(props.getString("PLAYER-X-OFFSET"));
        PLAYER_Y_OFFSET = Double.parseDouble(props.getString("PLAYER-Y-OFFSET"));
        ENEMY_X_OFFSET = Double.parseDouble(props.getString("ENEMY-X-OFFSET"));
        ENEMY_Y_OFFSET = Double.parseDouble(props.getString("ENEMY-Y-OFFSET"));
        ENEMY_STANDARD_Y = Double.parseDouble(props.getString("ENEMY-STANDARD-Y"));
        GRAVITY = Double.parseDouble(props.getString("GRAVITY"));
        DEFAULT_JUMP_STRENGTH = Double.parseDouble(props.getString("DEFAULT-JUMP-STRENGTH"));
        START_FALLING = Boolean.parseBoolean(props.getString("START-FALLING"));
        PROPULSION = Boolean.parseBoolean(props.getString("PROPULSION"));
        ALLOW_COINS = Boolean.parseBoolean(props.getString("ALLOW-COINS"));
    }

}
