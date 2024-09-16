package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
    private Texture intro;
    private Texture goldCoin;
    private Texture fallingCoin;
    private Rectangle goldCoinBounds;
   // private Rectangle fallingCoinBounds;
    private Rectangle playButtonBounds;
    private Rectangle objectBounds;
    private Rectangle obst1Bounds;
    private Rectangle obst2pBounds;
    private Rectangle obst2Bounds;
    private Rectangle bonusBounds;
    private Rectangle exitButtonBounds;
    private Rectangle gameworldbounds;
    private Rectangle mainImagebounds;
    private Rectangle introBounds;

    private SpriteBatch batch;
    private Sound jumpSound;
    private BitmapFont customFont;
    private BitmapFont textFont;

    private float elapsedTime = 0f;  // controlling the delay in letter display
    private float elapsedTime1 = 0f;
    private String fullText = "Explore The Nature";
    private String text1="Break The Limits";
    private StringBuilder displayedText = new StringBuilder();
    private StringBuilder displayedText1=new StringBuilder();
    private float letterDelay = 0.2f;

    private float fallingSpeed = 100;
    private Rectangle[] fallingCoinBounds = new Rectangle[4];
    private boolean[] hasCollided = new boolean[4];
    private Sound click;

    public MainMenuScreen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        playButton = new Texture("playButton.png");
        exitButton = new Texture("exit1.png");
        gameworld = new Texture("gameworld.png");
        mainImage = new Texture("mainImage.png");
        background = new Texture("mainScreenBackground.jpg");
        object = new Texture("object1.png");
        obst1 = new Texture("obstacle1.png");
        obst2 = new Texture("obstacle2.png");
        obst2p = new Texture("obstacle2.png");
        bonus = new Texture("bonus.png");
        intro = new Texture("introImg.png");
        goldCoin=new Texture("goldCoins.png");
        fallingCoin = new Texture("floatingCoin.png");

        float buttonWidth = 300;
        float buttonHeight = 300;
        playButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 -100, buttonWidth, buttonHeight-230);
        exitButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 200, buttonWidth , buttonHeight - 230);
        gameworldbounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 + 200, buttonWidth, buttonHeight);
        mainImagebounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth - 400) / 2, Gdx.graphics.getHeight() / 2 - 50, buttonWidth + 500, buttonHeight + 150);
        objectBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 - 450, Gdx.graphics.getHeight() / 2 - 200, buttonWidth, buttonHeight - 170);
        obst1Bounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 600, Gdx.graphics.getHeight() / 2 + 220, buttonWidth - 200, buttonHeight - 200);
        obst2Bounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 550, Gdx.graphics.getHeight() / 2 + 170, buttonWidth - 240, buttonHeight - 240);
        obst2pBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 650, Gdx.graphics.getHeight() / 2 + 130, buttonWidth - 240, buttonHeight - 240);
        bonusBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 600, Gdx.graphics.getHeight() / 2 + 40, buttonWidth - 220, buttonHeight - 220);
        goldCoinBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 700, Gdx.graphics.getHeight() / 2 -40, buttonWidth - 200, buttonHeight - 200);
        //goldCoinBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 700,
        introBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2, Gdx.graphics.getHeight() / 2 - 300, buttonWidth , buttonHeight - 230);
        for (int i = 0; i < fallingCoinBounds.length; i++) {
            fallingCoinBounds[i] = new Rectangle(
                    goldCoinBounds.x + 20 * i,
                    Gdx.graphics.getHeight() + i * 100,
                    goldCoinBounds.width - 50,
                    goldCoinBounds.height - 50
            );
            hasCollided[i] = false;  // No collisions at the start
        }
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Myttf.ttf"));
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("ttf22.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 48;
        parameter1.size=48;
        parameter.color= Color.BLACK;
        parameter1.color=Color.BLACK;
        customFont = generator.generateFont(parameter);
        textFont= generator1.generateFont(parameter1);
        generator.dispose();
        generator1.dispose();
        click=Gdx.audio.newSound(Gdx.files.internal("click.wav"));
    }

    @Override
    public void show() {

       jumpSound = Gdx.audio.newSound(Gdx.files.internal("mixkit-percussions-01-733.mp3"));
        jumpSound.play();
    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        if (playButtonBounds.contains(touchPos)) {

            batch.draw(playButton, playButtonBounds.x - 10, playButtonBounds.y - 10, playButtonBounds.width + 20, playButtonBounds.height + 20);
        } else {

            batch.draw(playButton, playButtonBounds.x, playButtonBounds.y, playButtonBounds.width, playButtonBounds.height);
        }
        if (exitButtonBounds.contains(touchPos)) {

            batch.draw(exitButton, exitButtonBounds.x-10, exitButtonBounds.y-10, exitButtonBounds.width+20, exitButtonBounds.height+20);
        }
        else {

             batch.draw(exitButton, exitButtonBounds.x, exitButtonBounds.y, exitButtonBounds.width, exitButtonBounds.height);

        }
        if (introBounds.contains(touchPos)) {

            batch.draw(intro, introBounds.x-10, introBounds.y-10, introBounds.width+20, introBounds.height+20);
        } else {

            batch.draw(intro, introBounds.x, introBounds.y, introBounds.width, introBounds.height);
        }



       // batch.draw(exitButton, exitButtonBounds.x, exitButtonBounds.y, exitButtonBounds.width, exitButtonBounds.height);
        batch.draw(gameworld, gameworldbounds.x, gameworldbounds.y, gameworldbounds.width, gameworldbounds.height);
        batch.draw(mainImage, mainImagebounds.x, mainImagebounds.y, mainImagebounds.width, mainImagebounds.height);
        batch.draw(object, objectBounds.x, objectBounds.y, objectBounds.width, objectBounds.height);
        batch.draw(obst1, obst1Bounds.x, obst1Bounds.y, obst1Bounds.width, obst1Bounds.height);
        batch.draw(obst2, obst2Bounds.x, obst2Bounds.y, obst2Bounds.width, obst2Bounds.height);
        batch.draw(obst2p, obst2pBounds.x, obst2pBounds.y, obst2pBounds.width, obst2pBounds.height);
        batch.draw(bonus, bonusBounds.x, bonusBounds.y, bonusBounds.width, bonusBounds.height);
       // batch.draw(intro, introBounds.x, introBounds.y, introBounds.width, introBounds.height);
        batch.draw(goldCoin, goldCoinBounds.x, goldCoinBounds.y, goldCoinBounds.width, goldCoinBounds.height);

        for (int i = 0; i < fallingCoinBounds.length; i++) {
            if (!hasCollided[i]) {
                fallingCoinBounds[i].y -= fallingSpeed * delta;
                // Check if falling coin collides with the gold coin
                if (fallingCoinBounds[i].overlaps(goldCoinBounds)) {
                    hasCollided[i] = true;
                   if(i==0) fallingCoinBounds[i].y = goldCoinBounds.y +60;
                   else fallingCoinBounds[i].y=fallingCoinBounds[i-1].y+10;
                }
            }
            // Draw the falling coin
            batch.draw(fallingCoin, fallingCoinBounds[i].x, fallingCoinBounds[i].y, fallingCoinBounds[i].width, fallingCoinBounds[i].height);
        }

        elapsedTime += delta;
        int numLettersToShow = (int) (elapsedTime / letterDelay);
        elapsedTime1 += delta;
        int numLettersToShow1 = (int) (elapsedTime1 / letterDelay);

        // Ensure we don't show more letters than are in the text
        if (numLettersToShow > fullText.length()) {
            numLettersToShow = fullText.length();
        }
        if (numLettersToShow1 > text1.length()) {
            numLettersToShow1 = text1.length();
        }

        // Update the displayed text based on the elapsed time
        displayedText.setLength(0);
        displayedText.append(fullText, 0, numLettersToShow);

        displayedText1.setLength(0);
        displayedText1.append(text1, 0, numLettersToShow1);


        customFont.draw(batch, displayedText.toString(), Gdx.graphics.getWidth() / 2-240, Gdx.graphics.getHeight()/2+140);
        textFont.draw(batch, displayedText1.toString(), Gdx.graphics.getWidth() / 2+150, Gdx.graphics.getHeight()/2+100);

        batch.end();


        if (Gdx.input.isTouched()) {
           // Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (playButtonBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new GameScreen(game));
            }
            if (exitButtonBounds.contains(touchPos)) {
                click.play();
                Gdx.app.exit();
            }
            if (introBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new Introduction(game));
            }
        }
    }

    @Override
    public void hide() {
        jumpSound.stop();
    }

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
        intro.dispose();
        jumpSound.dispose();

        customFont.dispose();
        textFont.dispose();
        goldCoin.dispose();
        click.dispose();

    }
}
