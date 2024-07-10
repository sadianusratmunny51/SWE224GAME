package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen extends AbstractScreen {
    private Texture playButton;
    private Texture exitButton;
    private Texture gameworld;
    private Texture mainImage;
    private Texture background;
    private Rectangle playButtonBounds;
    private Rectangle exitButtonBounds;
    private Rectangle gameworldbounds;
    private Rectangle mainImagebounds;
    private SpriteBatch batch;

    public MainMenuScreen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        playButton = new Texture("play.png");
        exitButton = new Texture("img.png");
        gameworld = new Texture("gameworld.png");
        mainImage = new Texture("mainImage.png");
        background = new Texture("mainScreenBackground.jpg");
        float buttonWidth = 300;
        float buttonHeight = 300;
        playButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 150, buttonWidth, buttonHeight);
        exitButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 300, buttonWidth - 20, buttonHeight - 200);
        gameworldbounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 + 200, buttonWidth, buttonHeight);
        mainImagebounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth - 400) / 2, Gdx.graphics.getHeight() / 2 - 50, buttonWidth + 500, buttonHeight + 150);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playButton, playButtonBounds.x, playButtonBounds.y, playButtonBounds.width, playButtonBounds.height);
        batch.draw(exitButton, exitButtonBounds.x, exitButtonBounds.y, exitButtonBounds.width, exitButtonBounds.height);
        batch.draw(gameworld, gameworldbounds.x, gameworldbounds.y, gameworldbounds.width, gameworldbounds.height);
        batch.draw(mainImage, mainImagebounds.x, mainImagebounds.y, mainImagebounds.width, mainImagebounds.height);
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (playButtonBounds.contains(touchPos)) {
                game.setScreen(new GameScreen(game));
            }
            if (exitButtonBounds.contains(touchPos)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        playButton.dispose();
        exitButton.dispose();
        gameworld.dispose();
        mainImage.dispose();
        background.dispose();
    }
}
