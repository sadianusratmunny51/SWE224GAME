package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    private BitmapFont fullFont,textFont;
    private StringBuilder displayedText = new StringBuilder();
    private float elapsedTime = 0f;
    private float letterDelay = 0.1f;

    private String fullText = "     Congratulations \nFirst  level  Completed";
    private String text1 = "In the next level you need to collect\n as much as coin to unlock the night mode. ";
    private  boolean textShowed=false;
    private Texture leaf1;
    private Texture leaf2;
    private float[] leafX;
    private float[] leafY;
    private float[] leafSpeedY;
    private float[] leafSwingSpeedX;
    private float[] swingAmplitude;
    private float[] swingTime;
    private int numLeaves = 10; // Number of leaves
    private Texture box;
    private Rectangle boxBounds;
    private Sound click, backgroundSound;


    public Level2Screen(SoaringAdventure game) {
        super(game);
        batch = new SpriteBatch();
        continueButton = new Texture("Pplay.png");
        dismissButton = new Texture("Quit.png");
        iconButton=new Texture("level.png");
        background = new Texture("level2background.png");
        sentenceButton=new Texture("leaf.png");
        box=new Texture("box.png");
        click=Gdx.audio.newSound(Gdx.files.internal("click.wav"));


        float buttonWidth = 300;
        float buttonHeight = 100;
        continueButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+300, Gdx.graphics.getHeight() / 2-300 ,200, 100);
        dismissButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-250, Gdx.graphics.getHeight() / 2-300, 200, 100);
        iconButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth-250) / 2, Gdx.graphics.getHeight() / 2 +200, buttonWidth+100, buttonHeight);
        sentenceButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+100, Gdx.graphics.getHeight() / 2-350 ,100, 100);
        boxBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-160, Gdx.graphics.getHeight() / 2 -50,660, 250);


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator1= new FreeTypeFontGenerator(Gdx.files.internal("munni.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();


        parameter.size=48;
        parameter.color= Color.CHARTREUSE;
        fullFont = generator1.generateFont(parameter);

        parameter1.size=40;
        parameter1.color=Color.BLACK;
        textFont = generator.generateFont(parameter1);
        generator1.dispose();
        generator.dispose();

        leaf1 = new Texture("leave1.png");
        leaf2 = new Texture("leave2.png");

        leafX = new float[numLeaves];
        leafY = new float[numLeaves];
        leafSpeedY = new float[numLeaves];
        leafSwingSpeedX = new float[numLeaves];
        swingAmplitude = new float[numLeaves];
        swingTime = new float[numLeaves];

        for (int i = 0; i < numLeaves; i++) {
            leafX[i] = (float) (Math.random() * Gdx.graphics.getWidth());
            leafY[i] = Gdx.graphics.getHeight() + (float) (Math.random() * 300); // Random height for leaves
            leafSpeedY[i] = (float) (50 + Math.random() *70); // Random falling speed
            leafSwingSpeedX[i] = (float) (0.5f + Math.random()); // Swinging speed
            swingAmplitude[i] = (float) (50 + Math.random() * 100); // Amplitude of swing
            swingTime[i] = (float) (Math.random() * Math.PI * 2); // Different starting swing time
        }


    }

    @Override
    public void show() {
        backgroundSound=Gdx.audio.newSound(Gdx.files.internal("mixkit-percussions-01-733.mp3"));
        backgroundSound.play();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(box, boxBounds.x, boxBounds.y, boxBounds.width, boxBounds.height);

        for (int i = 0; i < numLeaves; i++) {
            // Swing leaf horizontally using sine function
            leafX[i] += Math.sin(swingTime[i]) * swingAmplitude[i] * delta;
            leafY[i] -= leafSpeedY[i] * delta; // Move leaf downward

            // Choose which texture to draw (alternate between two textures)
            Texture currentLeafTexture = i % 2 == 0 ? leaf1 : leaf2;
            batch.draw(currentLeafTexture, leafX[i], leafY[i], 50, 50); // Draw leaf with a size of 50x50

            swingTime[i] += leafSwingSpeedX[i] * delta; // Increment time for swinging motion

            // Reset leaf position when it goes off-screen
            if (leafY[i] < -50) {
                leafY[i] = Gdx.graphics.getHeight() + (float) (Math.random() * 200); // Reset to random height above screen
                leafX[i] = (float) (Math.random() * Gdx.graphics.getWidth()); // Reset to a random x position
                swingTime[i] = (float) (Math.random() * Math.PI * 2); // Reset swing time
            }
        }


        batch.draw(continueButton, continueButtonBounds.x, continueButtonBounds.y, continueButtonBounds.width, continueButtonBounds.height);
        batch.draw(dismissButton, dismissButtonBounds.x, dismissButtonBounds.y, dismissButtonBounds.width, dismissButtonBounds.height);
        batch.draw(iconButton, iconButtonBounds.x, iconButtonBounds.y, iconButtonBounds.width, iconButtonBounds.height);
       // batch.draw(sentenceButton, sentenceButtonBounds.x, sentenceButtonBounds.y, sentenceButtonBounds.width, sentenceButtonBounds.height);

        elapsedTime += delta;
        int numLettersToShow = (int) (elapsedTime / letterDelay);

        if (numLettersToShow >fullText.length()) {
            numLettersToShow = fullText.length();
            textShowed=true;
        }
        displayedText.setLength(0);
        displayedText.append(fullText, 0, numLettersToShow);


      //  textFont.draw(batch,fullText, Gdx.graphics.getWidth() / 2-300 , Gdx.graphics.getHeight() / 2+200 );
        fullFont.draw(batch, displayedText.toString(), Gdx.graphics.getWidth() / 2-250, Gdx.graphics.getHeight() / 2+120);
        if(textShowed) {
            batch.draw(sentenceButton, sentenceButtonBounds.x, sentenceButtonBounds.y, sentenceButtonBounds.width, sentenceButtonBounds.height);
            textFont.draw(batch, text1, Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 -80);
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
            Vector2 touchPos1 = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (continueButtonBounds.contains(touchPos1)) {
                click.play();
                game.setScreen(new GameScreen2(game));
            }
            if (dismissButtonBounds.contains(touchPos1)) {
                click.play();
                game.setScreen(new GameOverScreen(game, (int) score, (int) coinCount));
            }
        }

    }

    @Override
    public void hide() {

        backgroundSound.stop();
    }

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