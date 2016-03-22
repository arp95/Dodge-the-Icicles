package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Constants;

/**
 * Created by arpitdec5 on 22-03-2016.
 */
public class MainScreen implements Screen {

    public static final String TAG = MainScreen.class.getName();
    ShapeRenderer renderer;
    ExtendViewport viewport;
    SpriteBatch batch;
    BitmapFont font;
    ScreenViewport hudViewport;
    com.mygdx.game.Icicles icicles;
    com.mygdx.game.Balloon balloon;
    int topscore =0;

    @Override
    public void show() {

        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        hudViewport = new ScreenViewport();
        font = new BitmapFont();
        batch = new SpriteBatch();
        icicles = new com.mygdx.game.Icicles(viewport);
        balloon = new com.mygdx.game.Balloon(viewport);
        topscore = 0;
    }

    @Override
    public void render(float delta) {

        balloon.update(delta);
        icicles.update(delta);
        if(balloon.isHit(icicles))
            icicles.init();

        viewport.apply(true);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        icicles.render(renderer);
        balloon.render(renderer);
        renderer.end();

        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        topscore = Math.max(topscore, icicles.dodged);

        font.draw(batch, "Deaths: " + balloon.deaths,
                Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);
        font.draw(batch, "Score: " + icicles.dodged + "\nTop Score: " + topscore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width,height,true);
        hudViewport.update(width,height,true);
        font.getData().setScale(Math.min(width,height)/Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);
        balloon.init();
        icicles.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        renderer.dispose();
        font.dispose();
        batch.dispose();
    }


}
