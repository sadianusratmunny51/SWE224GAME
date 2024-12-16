package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.InputAdapter;

public class HelpScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Texture background;
    private Texture ok;
    private Rectangle okBounds;
    private boolean transitioning;
    private BitmapFont textFont;
    private String text="This game is consisting of Three levels.\nThere is no way to jump the next level without completing " +
            "the\n previous level.\nThe player needs to collect a bag from the first level for storing \nthe coins what will get from the" +
            "Second level . \nIn the second level the player needs to collect certain amount of coins\n to unlock  the last level that is Night Mode";
    private BitmapFont obstacles, usefulObjects;
    private String obstacleText="OBSTACLES";
    private String usefulObjectText="USEFUL OBJECTS";





    public HelpScreen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        background = new Texture("helpBackground.png");
        ok = new Texture("ok.png");

        okBounds = new Rectangle((Gdx.graphics.getWidth() - 300) / 2, Gdx.graphics.getHeight() / 2 - 300, 150, 70);
        transitioning = false;


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 touchPos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if (okBounds.contains(touchPos) && !transitioning) {
                    transitioning = true;
                    game.setScreen(new MainMenuScreen(game));
                    return true;
                }
                return false;
            }
        });

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();



        parameter.size=40;
        parameter.color= Color.BLACK;


        parameter1.size=48;
        parameter1.color= Color.MAROON;

        parameter2.size=48;
        parameter2.color= Color.PURPLE;

        textFont = generator.generateFont(parameter);
        obstacles = generator1.generateFont(parameter1);
        usefulObjects = generator2.generateFont(parameter2);


        generator.dispose();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (okBounds.contains(touchPos)) {
            batch.draw(ok, okBounds.x - 10, okBounds.y - 10, okBounds.width + 10, okBounds.height + 10);
        } else {
            batch.draw(ok, okBounds.x, okBounds.y, okBounds.width, okBounds.height);
        }
       // batch.draw(ok, okBounds.x, okBounds.y, okBounds.width, okBounds.height);


        textFont.draw(batch, text, Gdx.graphics.getWidth() / 2-550, Gdx.graphics.getHeight() / 2 +300);
        usefulObjects.draw(batch, usefulObjectText, Gdx.graphics.getWidth() / 2+100, Gdx.graphics.getHeight() / 2);
        obstacles.draw(batch, obstacleText, Gdx.graphics.getWidth() / 2-500, Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ok.dispose();
        batch.dispose();
    }
}
