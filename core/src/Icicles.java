package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by arpitdec5 on 22-03-2016.
 */
public class Icicles {

    public static final String TAG = Icicles.class.getName();

    int dodged =0;
    DelayedRemovalArray<com.mygdx.game.Icicle>arr;
    Viewport viewport;

    public Icicles(Viewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void init()
    {
        arr = new DelayedRemovalArray<com.mygdx.game.Icicle>(false,100);
        dodged=0;
    }

    public void update(float delta)
    {
        if (MathUtils.random() < delta* com.mygdx.game.Constants.ICICLE_SPAWNS_PER_SECOND) {
            Vector2 newIciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            com.mygdx.game.Icicle newIcicle = new com.mygdx.game.Icicle(newIciclePosition);
            arr.add(newIcicle);
        }

        for (com.mygdx.game.Icicle icicle : arr) {
            icicle.update(delta);
        }

        arr.begin();
        for (int i = 0; i < arr.size; i++) {
            if (arr.get(i).position.y < -com.mygdx.game.Constants.ICICLE_HEIGHT) {
                dodged += 1;
                arr.removeIndex(i);
            }
        }
        arr.end();
    }


    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.WHITE);
        for(com.mygdx.game.Icicle icicle: arr)
            icicle.render(renderer);
    }

}