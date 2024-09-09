package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Introduction extends AbstractScreen{
    private Texture helpButton;
    private Texture background;
    private Texture sentence;
    private Rectangle sentenceBounds;
    private Rectangle helpButtonBounds;
    private Texture backButton;
    private Rectangle backButtonBounds;

    private SpriteBatch batch;
    public Introduction(SoaringAdventure game) {
        super(game);
        float buttonWidth = 300;
        float buttonHeight = 300;
        batch = new SpriteBatch();
        //helpButton=new Texture("IntroBackgroundImg.png");
        background=new Texture("bb.png");
        helpButton=new Texture("helpIcon.png");
        backButton=new Texture("backIcon.png");
        sentence=new Texture("ssIntro.png");

        helpButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2+300, Gdx.graphics.getHeight() / 2-300 , buttonWidth+50, buttonHeight-200);
        backButtonBounds = new Rectangle((Gdx.graphics.getWidth() - buttonWidth) / 2-350, Gdx.graphics.getHeight() / 2-300, buttonWidth-80 , buttonHeight-200 );
        sentenceBounds=new Rectangle((Gdx.graphics.getWidth() - buttonWidth-100) / 2-200, Gdx.graphics.getHeight() / 2-230,800 , 500 );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(helpButton, helpButtonBounds.x, helpButtonBounds.y, helpButtonBounds.width, helpButtonBounds.height);
        batch.draw(backButton, backButtonBounds.x, backButtonBounds.y, backButtonBounds.width, backButtonBounds.height);
        batch.draw(sentence, sentenceBounds.x, sentenceBounds.y, sentenceBounds.width, sentenceBounds.height);


        batch.end();
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            if (helpButtonBounds.contains(touchPos)) {
                game.setScreen(new HelpScreen(game));
            }
            if(backButtonBounds.contains(touchPos)){
                game.setScreen(new MainMenuScreen(game));
            }
        }

    }
    public void dispose(){
        background.dispose();
        helpButton.dispose();
        backButton.dispose();
        sentence.dispose();
    }
}
