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
    private Texture object;
    private Texture obst1;
    private Texture obst2p;
    private Texture obst2;
    private Texture bonus;

    private Rectangle playButtonBounds;
    private Rectangle objectBounds;
    private Rectangle obst1Bounds;
    private Rectangle obst2pBounds;
    private Rectangle obst2Bounds;

    private Rectangle bonusBounds;
    private Rectangle exitButtonBounds;
    private Rectangle gameworldbounds;
    private Rectangle mainImagebounds;
    private SpriteBatch batch;

    public MainMenuScreen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        playButton = new Texture("play.png");
        exitButton = new Texture("exit1.png");
        gameworld = new Texture("gameworld.png");
        mainImage = new Texture("mainImage.png");
        background = new Texture("mainScreenBackground.jpg");
        object=new Texture("object1.png");
        obst1=new Texture("obstacle1.png");
        obst2=new Texture("obstacle2.png");
        obst2p=new Texture("obstacle2.png");
        bonus=new Texture("bonus.png");

        float buttonWidth = 300;
        float buttonHeight = 300;
        playButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 200, buttonWidth, buttonHeight);
        exitButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 450, buttonWidth-30 , buttonHeight-30 );
        gameworldbounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 + 200, buttonWidth, buttonHeight);
        mainImagebounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth - 400) / 2, Gdx.graphics.getHeight() / 2 - 50, buttonWidth + 500, buttonHeight + 150);
        objectBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-450, Gdx.graphics.getHeight() / 2-200, buttonWidth, buttonHeight-170);
        obst1Bounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+600, Gdx.graphics.getHeight() / 2+220 , buttonWidth-200, buttonHeight-200);
        obst2Bounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+550, Gdx.graphics.getHeight() / 2+170 , buttonWidth-240, buttonHeight-240);
        obst2pBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+650, Gdx.graphics.getHeight() / 2+130 , buttonWidth-240, buttonHeight-240);
        bonusBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+600, Gdx.graphics.getHeight() / 2+40 , buttonWidth-220, buttonHeight-220);
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
        batch.draw(object, objectBounds.x, objectBounds.y, objectBounds.width, objectBounds.height);
        batch.draw(obst1, obst1Bounds.x, obst1Bounds.y, obst1Bounds.width, obst1Bounds.height);
        batch.draw(obst2, obst2Bounds.x, obst2Bounds.y, obst2Bounds.width, obst2Bounds.height);
        batch.draw(obst2p, obst2pBounds.x, obst2pBounds.y, obst2pBounds.width, obst2pBounds.height);
        batch.draw(bonus, bonusBounds.x, bonusBounds.y, bonusBounds.width, bonusBounds.height);
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
        object.dispose();
        obst1.dispose();
        obst2.dispose();
        obst2p.dispose();
        background.dispose();
    }
}
