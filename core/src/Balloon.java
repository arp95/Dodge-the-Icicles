package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Constants;
import com.mygdx.game.Icicle;

/**
 * Created by arpitdec5 on 22-03-2016.
 */
public class Balloon {

    int deaths = 0;
    public static final String TAG = Balloon.class.getName();

    Vector2 position;
    Viewport viewport;

    public Balloon(Viewport viewport)
    {
        deaths =0;
        this.viewport = viewport;
        position = new Vector2();
        init();
    }

    public void init()
    {
        this.position = new Vector2(viewport.getWorldWidth()/2 , Constants.PLAYER_HEAD_HEIGHT);
    }

    public void update(float delta)
    {
        float accy = -Gdx.input.getAccelerometerY()/(Constants.ACCELEROMETER_SENSITIVITY*Constants.GRAVITATIONAL_ACCELERATION);
        position.x += -delta*accy*Constants.PLAYER_MOVEMENT_SPEED;
        ensure();
    }

    public void ensure()
    {
        if(position.x-Constants.PLAYER_HEAD_RADIUS<0)
            position.x = Constants.PLAYER_HEAD_RADIUS;
        if(position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth())
            position.x = viewport.getWorldWidth()-Constants.PLAYER_HEAD_RADIUS;
    }


    public boolean isHit(com.mygdx.game.Icicles icicles)
    {
        boolean hit = false;
        for(Icicle icicle: icicles.arr)
        {
            if(icicle.position.dst(position)<Constants.PLAYER_HEAD_RADIUS)
                hit = true;
        }
        if(hit)
            deaths+=1;
        return hit;
    }
    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.BLACK);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x,position.y,Constants.PLAYER_HEAD_RADIUS,Constants.PLAYER_HEAD_SEGMENTS);

        Vector2 v1 = new Vector2(position.x , position.y-Constants.PLAYER_HEAD_RADIUS);
        Vector2 v2 = new Vector2(v1.x , v1.y-2*Constants.PLAYER_HEAD_RADIUS);
        renderer.rectLine(v1,v2,Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(v1.x,v1.y,v1.x-Constants.PLAYER_HEAD_RADIUS,v1.y-Constants.PLAYER_HEAD_RADIUS,Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(v1.x,v1.y,v1.x+Constants.PLAYER_HEAD_RADIUS,v1.y-Constants.PLAYER_HEAD_RADIUS,Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(v2.x,v2.y,v2.x-Constants.PLAYER_HEAD_RADIUS,v2.y-Constants.PLAYER_HEAD_RADIUS,Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(v2.x,v2.y,v2.x+Constants.PLAYER_HEAD_RADIUS,v2.y-Constants.PLAYER_HEAD_RADIUS,Constants.PLAYER_LIMB_WIDTH);

    }

}
