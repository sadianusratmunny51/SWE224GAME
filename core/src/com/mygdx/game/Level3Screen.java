package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Level3Screen extends AbstractScreen {
    private Texture continueButton;
    private Texture dismissButton;
    private Texture background;
    private Texture iconButton;
    private Texture congratulationsImage;

    private Rectangle continueButtonBounds;
    private Rectangle dismissButtonBounds;
    private Rectangle iconButtonBounds;
    private SpriteBatch batch;
    float score;
    float coinCount;
    private BitmapFont levelFont;
    private BitmapFont customFont;
    private BitmapFont textFont;
    private float elapsedTime = 0f;
    private float elapsedTime1 = 0f;
    private String levelText = "LEVEL 3";
    private String fullText = "Congratulations\n             Night Mood Unlocked";
    private String text1 = "Accept the challenge \n        complete the level";
    private StringBuilder displayedText = new StringBuilder();
    private StringBuilder displayedText1 = new StringBuilder();

    private float letterDelay = 0.1f;
    private boolean showCongratulationsImage = false;
    private Texture starTexture;
    private Vector2[] starPositions;
    private float[] starSpeeds;
    private int numStars = 50;


    public Level3Screen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        continueButton = new Texture("munni3.png");
        dismissButton = new Texture("munni4.png");
        iconButton = new Texture("icons.png");
        congratulationsImage = new Texture("congratulation.png");
        background = new Texture("img_1.png");

        float buttonWidth = 200;
        float buttonHeight = 80;
        continueButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 500, Gdx.graphics.getHeight() / 2 - 350, buttonWidth, buttonHeight);
        dismissButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 100, Gdx.graphics.getHeight() / 2 - 350, buttonWidth, buttonHeight);
        iconButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2 + 170, Gdx.graphics.getHeight() / 2 + 150, buttonWidth + 100, buttonHeight + 50);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Believe it.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("ttf22.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator levelGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter levelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 60;
        parameter1.size = 60;
        levelParameter.size = 80;
        parameter.color = Color.VIOLET;
        parameter1.color = Color.SKY;
        levelParameter.color = Color.GOLDENROD;
        customFont = generator.generateFont(parameter);
        textFont = generator1.generateFont(parameter1);
        levelFont = levelGenerator.generateFont(levelParameter);
        generator.dispose();
        generator1.dispose();
        levelGenerator.dispose();


        starTexture = new Texture("star33.png");

        // Initialize star positions and speeds
        starPositions = new Vector2[numStars];
        starSpeeds = new float[numStars];

        for (int i = 0; i < numStars; i++) {
            float x = MathUtils.random(0, Gdx.graphics.getWidth());
            float y = MathUtils.random(0, Gdx.graphics.getHeight());
            starPositions[i] = new Vector2(x, y);
            starSpeeds[i] = MathUtils.random(50, 50);
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (int i = 0; i < numStars; i++) {

            starPositions[i].x += starSpeeds[i] * delta;


            if (starPositions[i].x > Gdx.graphics.getWidth()) {
                starPositions[i].x = -starTexture.getWidth();
                starPositions[i].y = MathUtils.random(0, Gdx.graphics.getHeight());
            }

            // Draw the star at its updated position
            float starSize = MathUtils.random(5,10 );
            batch.draw(starTexture, starPositions[i].x, starPositions[i].y, starSize, starSize);
        }


        batch.draw(continueButton, continueButtonBounds.x, continueButtonBounds.y, continueButtonBounds.width, continueButtonBounds.height);
        batch.draw(dismissButton, dismissButtonBounds.x, dismissButtonBounds.y, dismissButtonBounds.width, dismissButtonBounds.height);
        batch.draw(iconButton, iconButtonBounds.x, iconButtonBounds.y, iconButtonBounds.width, iconButtonBounds.height);

        elapsedTime += delta;
        int numLettersToShow = (int) (elapsedTime / letterDelay);
        elapsedTime1 += delta;
        int numLettersToShow1 = (int) (elapsedTime1 / letterDelay);

        if (numLettersToShow > fullText.length()) {
            numLettersToShow = fullText.length();
            showCongratulationsImage = true;
        }

        if (numLettersToShow1 > text1.length()) {
            numLettersToShow1 = text1.length();
        }

        displayedText.setLength(0);
        displayedText.append(fullText, 0, numLettersToShow);
        displayedText1.setLength(0);
        displayedText1.append(text1, 0, numLettersToShow1);

        customFont.draw(batch, displayedText.toString(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 140);
        textFont.draw(batch, displayedText1.toString(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2-100);
        levelFont.draw(batch, levelText, Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 + 350);

        // Show the Congratulations image when the text is fully displayed
        if (showCongratulationsImage) {
            batch.draw(congratulationsImage, Gdx.graphics.getWidth() / 2+200 , Gdx.graphics.getHeight() / 2-100, 250, 100);
        }


            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (continueButtonBounds.contains(touchPos)) {

                batch.draw(continueButton, continueButtonBounds.x - 10, continueButtonBounds.y - 10, continueButtonBounds.width + 20, continueButtonBounds.height + 20);
            } else {

                batch.draw(continueButton, continueButtonBounds.x, continueButtonBounds.y, continueButtonBounds.width, continueButtonBounds.height);
            }
            if (dismissButtonBounds.contains(touchPos)) {

                batch.draw(dismissButton, dismissButtonBounds.x-10, dismissButtonBounds.y-10, dismissButtonBounds.width+20, dismissButtonBounds.height+20);
            }
            else {

                batch.draw(dismissButton, dismissButtonBounds.x, dismissButtonBounds.y, dismissButtonBounds.width, dismissButtonBounds.height);

            }


        batch.end();
        if (Gdx.input.isTouched()) {
           // Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (continueButtonBounds.contains(touchPos)) {
                game.setScreen(new NightMood(game));
            }
            if (dismissButtonBounds.contains(touchPos)) {
                game.setScreen(new GameOverScreen(game, (int) score, (int) coinCount));
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
        congratulationsImage.dispose();  // Dispose of the congratulations image
        background.dispose();
    }
}