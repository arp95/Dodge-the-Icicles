package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by arpitdec5 on 22-03-2016.
 */
public class Constants {

    public static final String TAG = Constants.class.getName();
    public static final float WORLD_SIZE = 10.0f;

    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    public static final Color ICICLE_COLOR = Color.WHITE;
    public static final float ICICLE_HEIGHT = 1.0f;
    public static final float ICICLE_WIDTH = 0.5f;
    public static final Vector2 ICICLES_ACCELERATION = new Vector2(0, -5.0f);
    public static final float ICICLE_SPAWNS_PER_SECOND = 10.0f;

    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final Color PLAYER_COLOR = Color.BLACK;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;


}
