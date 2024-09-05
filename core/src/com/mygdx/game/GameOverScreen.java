package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GameOverScreen(SoaringAdventure game,int finalScore,int totalCoin) {
        super(game);
        this.finalScore = finalScore;
        this.totalCoin=totalCoin;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3.0f);
        font.setColor(Color.BLACK);
        layout = new GlyphLayout();
        yesButton = new Texture("yes.png");
        noButton = new Texture("no.png");
        iconButton=new Texture("adv.png");
        background = new Texture("gameOver.png");
      //  sentenceButton=new Texture("sentence.png");

        float buttonWidth = 150;
        float buttonHeight = 70;
        yesButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+170, Gdx.graphics.getHeight() / 2-330 , buttonWidth, buttonHeight);
        noButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-150, Gdx.graphics.getHeight() / 2-330, buttonWidth, buttonHeight);
        iconButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-300, Gdx.graphics.getHeight() / 2 +250, buttonWidth+700, buttonHeight+100);
     //   sentenceButtonBounds=new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-150, Gdx.graphics.getHeight() / 2-50 , buttonWidth+300, buttonHeight);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(yesButton, yesButtonBounds.x, yesButtonBounds.y, yesButtonBounds.width, yesButtonBounds.height);
        batch.draw(noButton, noButtonBounds.x, noButtonBounds .y, noButtonBounds.width, noButtonBounds.height);
        batch.draw(iconButton, iconButtonBounds.x, iconButtonBounds.y, iconButtonBounds.width, iconButtonBounds.height);
        //batch.draw(sentenceButton, sentenceButtonBounds.x, sentenceButtonBounds.y, sentenceButtonBounds.width, sentenceButtonBounds.height);

        String scoreText = "Final Score: " + finalScore;
        layout.setText(font, scoreText);
        font.draw(batch, scoreText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2+30 );

        String coinText = "Total Coins: " + totalCoin;
        layout.setText(font, coinText);
        font.draw(batch, coinText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 50);

        batch.end();

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (yesButtonBounds.contains(touchPos)) {
                game.setScreen(new MainMenuScreen(game));  // Assuming GameScreen handles both levels
            }
            if (noButtonBounds.contains(touchPos)) {
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