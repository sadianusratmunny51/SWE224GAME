package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameOverScreen extends AbstractScreen {
    private Texture yesButton;
    private Texture noButton;
    private Texture background;
    private Texture iconButton;
    private Texture sentenceButton;

    private Rectangle yesButtonBounds;
    private Rectangle noButtonBounds;
    private Rectangle iconButtonBounds;
    private Rectangle sentenceButtonBounds;
    private SpriteBatch batch;


    private BitmapFont font;
    private GlyphLayout layout;
    private int finalScore;
    private int totalCoin;
    private  BitmapFont scoreFont;
    private  BitmapFont coinFont;
    private Sound click;


    public GameOverScreen(SoaringAdventure game,int finalScore,int totalCoin) {
        super(game);
        this.finalScore = finalScore;
        this.totalCoin=totalCoin;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3.0f);
        font.setColor(Color.BLACK);
        layout = new GlyphLayout();
        yesButton = new Texture("lastPlay.png");
        noButton = new Texture("lastExit.png");
        iconButton=new Texture("Game_Over_Logo.png");
        background = new Texture("screen.png");
        sentenceButton=new Texture("playAgain.png");
        click=Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        float buttonWidth = 100;
        float buttonHeight = 50;
        yesButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+60, Gdx.graphics.getHeight() / 2-280 , buttonWidth+100, buttonHeight+20);
        noButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-150, Gdx.graphics.getHeight() / 2-280, buttonWidth+100, buttonHeight+20);
        iconButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-300, Gdx.graphics.getHeight() / 2 +200, buttonWidth+700, buttonHeight+100);
        sentenceButtonBounds=new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-180, Gdx.graphics.getHeight() / 2-200 , buttonWidth+400, buttonHeight);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ShortBaby.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        // General font settings
        parameter.size = 55; // Change font size as needed
        parameter.color = Color.PURPLE;

        scoreFont = generator.generateFont(parameter);

        coinFont=generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(yesButton, yesButtonBounds.x, yesButtonBounds.y, yesButtonBounds.width, yesButtonBounds.height);
        batch.draw(noButton, noButtonBounds.x, noButtonBounds .y, noButtonBounds.width, noButtonBounds.height);
        batch.draw(iconButton, iconButtonBounds.x, iconButtonBounds.y, iconButtonBounds.width, iconButtonBounds.height);
        batch.draw(sentenceButton, sentenceButtonBounds.x, sentenceButtonBounds.y, sentenceButtonBounds.width, sentenceButtonBounds.height);

//        String scoreText = "Final Score: " + finalScore;
//        layout.setText(font, scoreText);
//        font.draw(batch, scoreText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2+30 );
//
//        String coinText = "Total Coins: " + totalCoin;
//        layout.setText(font, coinText);
//        font.draw(batch, coinText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 50);

        scoreFont.draw(batch, "Final Score : " + (int) finalScore, (Gdx.graphics.getWidth() - layout.width) / 2-150,  (Gdx.graphics.getHeight() + layout.height) / 2+150);
        coinFont.draw(batch, "Total Coins : " + (int) totalCoin, (Gdx.graphics.getWidth() - layout.width) / 2-150, (Gdx.graphics.getHeight() + layout.height) / 2 +70);

        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        if (yesButtonBounds.contains(touchPos)) {

            batch.draw(yesButton, yesButtonBounds.x - 10, yesButtonBounds.y - 10, yesButtonBounds.width + 20, yesButtonBounds.height + 20);
        } else {

            batch.draw(yesButton, yesButtonBounds.x, yesButtonBounds.y, yesButtonBounds.width, yesButtonBounds.height);
        }
        if (noButtonBounds.contains(touchPos)) {

            batch.draw(noButton, noButtonBounds.x-10, noButtonBounds.y-10, noButtonBounds.width+20, noButtonBounds.height+20);
        }
        else {

            batch.draw(noButton, noButtonBounds.x, noButtonBounds.y, noButtonBounds.width, noButtonBounds.height);

        }

        batch.end();

        if (Gdx.input.isTouched()) {
           // Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (yesButtonBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new MainMenuScreen(game));  // Assuming GameScreen handles both levels
            }
            if (noButtonBounds.contains(touchPos)) {
                click.play();
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        yesButton.dispose();
        noButton.dispose();
        iconButton.dispose();
       // sentenceButton.dispose();
        background.dispose();
    }
}