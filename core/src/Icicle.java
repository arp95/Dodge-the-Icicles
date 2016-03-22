package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants;

/**
 * Created by arpitdec5 on 22-03-2016.
 */
public class Icicle {

    public static final String TAG = Icicle.class.getName();
    ShapeRenderer renderer;
    Vector2 position;
    Vector2 velocity;

    public Icicle(Vector2 position)
    {
        this.position = position;
        this.velocity = new Vector2();
    }


    public void update(float delta)
    {
        velocity.mulAdd(Constants.ICICLES_ACCELERATION,delta);
        position.mulAdd(velocity,delta);
    }


    public void render(ShapeRenderer renderer)
    {
        renderer.triangle(position.x,position.y,position.x- Constants.ICICLE_WIDTH/2,position.y+Constants.ICICLE_HEIGHT, position.x+Constants.ICICLE_WIDTH/2 , position.y+Constants.ICICLE_HEIGHT);
    }

}
