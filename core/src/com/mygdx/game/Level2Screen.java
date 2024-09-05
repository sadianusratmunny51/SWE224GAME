package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Level2Screen extends AbstractScreen {
    private Texture continueButton;
    private Texture dismissButton;
    private Texture background;
    private Texture iconButton;
    private Texture sentenceButton;

    private Rectangle continueButtonBounds;
    private Rectangle dismissButtonBounds;
    private Rectangle iconButtonBounds;
    private Rectangle sentenceButtonBounds;
    private SpriteBatch batch;
    float score;
    float coinCount;


    public Level2Screen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        continueButton = new Texture("continue.png");
        dismissButton = new Texture("dismiss.png");
        iconButton=new Texture("level.png");
        background = new Texture("level2background.png");
        sentenceButton=new Texture("sentence.png");

        float buttonWidth = 300;
        float buttonHeight = 100;
        continueButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+300, Gdx.graphics.getHeight() / 2-200 , buttonWidth, buttonHeight);
        dismissButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-300, Gdx.graphics.getHeight() / 2-200, buttonWidth, buttonHeight);
        iconButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth-250) / 2, Gdx.graphics.getHeight() / 2 +100, buttonWidth+300, buttonHeight+100);
        sentenceButtonBounds=new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-150, Gdx.graphics.getHeight() / 2-50 , buttonWidth+300, buttonHeight);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(continueButton, continueButtonBounds.x, continueButtonBounds.y, continueButtonBounds.width, continueButtonBounds.height);
        batch.draw(dismissButton, dismissButtonBounds.x, dismissButtonBounds.y, dismissButtonBounds.width, dismissButtonBounds.height);
        batch.draw(iconButton, iconButtonBounds.x, iconButtonBounds.y, iconButtonBounds.width, iconButtonBounds.height);
        batch.draw(sentenceButton, sentenceButtonBounds.x, sentenceButtonBounds.y, sentenceButtonBounds.width, sentenceButtonBounds.height);
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (continueButtonBounds.contains(touchPos)) {
                game.setScreen(new GameScreen2(game));  // Assuming GameScreen handles both levels
            }
            if (dismissButtonBounds.contains(touchPos)) {

                game.setScreen(new GameOverScreen(game, (int) score,(int) coinCount));
               // Gdx.app.exit();
            }
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        continueButton.dispose();
        dismissButton.dispose();
        iconButton.dispose();
        sentenceButton.dispose();
        background.dispose();
    }
}