package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.badlogic.gdx.audio.Sound;


public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Texture background;
    private Texture background2;
    private Texture p,me,ps,r;
    private Rectangle pBounds,meBounds,psBounds,rBounds;

    private float backgroundX;
    private float backgroundX2;
    private float backgroundSpeed;

    private MovingObject movingObject;
    // private Texture bag;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<BonusItem> bonusItems;

    private Random random;

    private int score;
    float coinCount;
    private BitmapFont font;
    private GlyphLayout layout;

    private Texture[] obstacleTextures;
    private Texture bonusTexture;

    private float timeSinceLastSpawnObstacle1;
    private float timeSinceLastSpawnObstacle2;
    private float minSpawnInterval;
    private float maxSpawnInterval;
    private float timeSinceLastSpawnBonus;
    private float bonusSpawnInterval;

    private int obstacle2Count;
    private int maxObstacle2Count;

    private ShapeRenderer shapeRenderer;

    private boolean isGameOver;
    private ArrayList<TemporaryMessage> messages;
    private float timeSinceGameOver = 0f;
    private Bag bag;
    private boolean isBagSpawned;
    private boolean bagCollected = false;
    private float bagCollectedTimer = 0;
    private boolean gamePaused = false;
    private Sound bonusSoundEffect;
    private Sound backSound;
    private  Sound hitSound;
    private Sound levelWin;
    private Sound end;
    private Sound click;

    public GameScreen(SoaringAdventure game) {
        super(game);
        messages = new ArrayList<>();
        batch = new SpriteBatch();
        background = new Texture("background.png");
        background2 = new Texture("background.png");
        p=new Texture("pppp.png");
        ps=new Texture("pause.png");
        me=new Texture("menu.png");
        r=new Texture("restart.png");
        backgroundX = 0;
        backgroundX2 = background.getWidth();
        backgroundSpeed = 600;

        Texture img = new Texture("object1.png");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float scale = 0.2f;
        movingObject = new MovingObject(img, screenWidth, screenHeight, 200, scale);

        obstacles = new ArrayList<>();
        bonusItems = new ArrayList<>();
        random = new Random();

        score = 0;
        font = new BitmapFont();
        font.getData().setScale(2.0f);
        font.setColor(Color.BLACK);
        layout = new GlyphLayout();

        obstacleTextures = new Texture[]{
                new Texture("obstacle1.png"),
                new Texture("obstacle2.png")
        };

        bonusTexture = new Texture("bonus.png");
        // bag=new Texture("bag.png");
        float width=200;
        float height=60;
        psBounds = new Rectangle((Gdx.graphics.getWidth() - width) / 2-400, Gdx.graphics.getHeight() / 2 -390, width, height);
        pBounds = new Rectangle((Gdx.graphics.getWidth() - width) / 2-150, Gdx.graphics.getHeight() / 2 -390, width, height);
        meBounds = new Rectangle((Gdx.graphics.getWidth() - width) / 2+100, Gdx.graphics.getHeight() / 2 -390, width, height);
        rBounds = new Rectangle((Gdx.graphics.getWidth() - width) / 2+350, Gdx.graphics.getHeight() / 2 -390, width, height);

        timeSinceLastSpawnObstacle1 = 0;
        timeSinceLastSpawnObstacle2 = 0;
        minSpawnInterval = 0.5f;
        maxSpawnInterval = 1.5f;
        timeSinceLastSpawnBonus = 0;
        bonusSpawnInterval = 7.0f;

        obstacle2Count = 0;
        maxObstacle2Count = 3;

        shapeRenderer = new ShapeRenderer();

        isGameOver = false;
        bag = null;
        isBagSpawned = false;
        bonusSoundEffect = Gdx.audio.newSound(Gdx.files.internal("bonus (2).mp3"));
        hitSound= Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
        levelWin=Gdx.audio.newSound(Gdx.files.internal("level-win.mp3"));
        end=Gdx.audio.newSound(Gdx.files.internal("End.mp3"));
        click=Gdx.audio.newSound(Gdx.files.internal("click.wav"));

    }

    @Override
    public void show() {

        backSound = Gdx.audio.newSound(Gdx.files.internal("nature.mp3"));
        backSound.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);

        if (gamePaused) {
            renderPaused();
            return;
        }
        backgroundX -= backgroundSpeed * Gdx.graphics.getDeltaTime();
        backgroundX2 -= backgroundSpeed * Gdx.graphics.getDeltaTime();

        if (backgroundX + background.getWidth() <= 0) {
            backgroundX = backgroundX2 + background2.getWidth();
        }
        if (backgroundX2 + background2.getWidth() <= 0) {
            backgroundX2 = backgroundX + background.getWidth();
        }

        if (!isGameOver) {
            handleInput(delta);
            movingObject.update(Gdx.graphics.getDeltaTime());
            score += delta * 57.5f;

            spawnObstacles(delta);
            spawnBonusItem(delta);
            updateObstacles(delta);
            updateBonusItems(delta);
            if (score >= 500 && !isBagSpawned) {
                spawnBag();
            }

            if (bag != null) {
                bag.update(delta,backgroundSpeed);


                if (bag.overlaps(movingObject)) {
                    levelWin.play();
                    gamePaused=false;
                    addTemporaryMessage("Woh! Bag Collected", movingObject.getPosition().x + movingObject.getWidth()+200 / 2, movingObject.getPosition().y + movingObject.getHeight() / 2, 4.0f);
                    bagCollected = true;
                    bagCollectedTimer = 0;
                    // bag = null;
                    isBagSpawned =true;
                    backgroundSpeed=0;
                    gamePaused=false;



                }


                if (bag.getX() + bag.getWidth() < 0) {
                    isBagSpawned = false;
                    bag = null;
                }
            }
        }
        if (bagCollected) {


            bagCollectedTimer += delta;
            gamePaused = false;

            if (bagCollectedTimer >= 4.0) {
                game.setScreen(new Level3Screen(game));
            }
        }


        batch.begin();


        batch.draw(background, backgroundX, 0, background.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background2,backgroundX2, 0, background2.getWidth(), Gdx.graphics.getHeight());
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        if (pBounds.contains(touchPos)) {
            batch.draw(p, pBounds.x - 10, pBounds.y - 10, pBounds.width + 10, pBounds.height + 10);
        } else {
            batch.draw(p, pBounds.x, pBounds.y, pBounds.width, pBounds.height);
        }
        if (meBounds.contains(touchPos)) {
            batch.draw(me, meBounds.x - 10, meBounds.y - 10, meBounds.width + 10, meBounds.height + 10);
        } else {
            batch.draw(me, meBounds.x, meBounds.y, meBounds.width, meBounds.height);
        }
        if (psBounds.contains(touchPos)) {
            batch.draw(ps, psBounds.x - 10, psBounds.y - 10, psBounds.width + 10, psBounds.height + 10);
        } else {
            batch.draw(ps, psBounds.x, psBounds.y, psBounds.width, psBounds.height);
        }
        if (rBounds.contains(touchPos)) {
            batch.draw(r, rBounds.x - 10, rBounds.y - 10, rBounds.width + 10, rBounds.height + 10);
        } else {
            batch.draw(r, rBounds.x, rBounds.y, rBounds.width, rBounds.height);        }


        movingObject.render(batch);

        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }

        for (BonusItem bonusItem : bonusItems) {
            bonusItem.render(batch);
        }
        if (bag != null) {
            bag.render(batch);
        }

        String levelText = "Level 1 " ;
        layout.setText(font, levelText);
        font.draw(batch, levelText, Gdx.graphics.getWidth() - layout.width - 600, Gdx.graphics.getHeight() - 10);


        String scoreText = "Score: " + (int) score;
        layout.setText(font, scoreText);
        font.draw(batch, scoreText, Gdx.graphics.getWidth() - layout.width - 10, Gdx.graphics.getHeight() - 10);

        if (isGameOver) {
            String gameOverText = "Game Over";
            layout.setText(font, gameOverText);
            font.draw(batch, gameOverText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);
            backgroundSpeed = 0;
            scoreText = "Score: " + (int) score;
            layout.setText(font, scoreText);
            font.draw(batch, scoreText, Gdx.graphics.getWidth() - layout.width - 10, Gdx.graphics.getHeight() - 10);

            timeSinceGameOver += Gdx.graphics.getDeltaTime();
            if (timeSinceGameOver >= 2f) {
                game.setScreen(new GameOverScreen(game, (int) score,(int) coinCount));
            }

        }
        if (score >=10) {
            game.setScreen(new Level3Screen(game));
        }

        for (TemporaryMessage message : messages) {
            message.render(batch);
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        float borderY = Gdx.graphics.getHeight() * 0.25f;
        shapeRenderer.rect(0, borderY - 2, Gdx.graphics.getWidth() - 4, 8);
        float borderUpper = Gdx.graphics.getHeight() - 5;
        shapeRenderer.rect(0, borderUpper, Gdx.graphics.getWidth(), 8);
        shapeRenderer.end();

        messages.removeIf(TemporaryMessage::isExpired);
        for (TemporaryMessage message : messages) {
            message.update(delta);
        }
        if (Gdx.input.isTouched()) {
            // Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (meBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new MainMenuScreen(game));
            }
            if (rBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new GameScreen(game));
            }
            if (Gdx.input.isTouched() && psBounds.contains(touchPos)) {
                click.play();
                backSound.pause();
                gamePaused = !gamePaused;
            }

        }

    }
    private void renderPaused() {
        batch.begin();
        batch.draw(background, backgroundX, 0, background.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background2, backgroundX2, 0, background2.getWidth(), Gdx.graphics.getHeight());

        String pausedText = "Game Paused";
        layout.setText(font, pausedText);
        font.draw(batch, pausedText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);

        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        //Vector2 touchPos2 = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        if (pBounds.contains(touchPos)) {
            batch.draw(p, pBounds.x - 10, pBounds.y - 10, pBounds.width + 10, pBounds.height + 10);
        } else {
            batch.draw(p, pBounds.x, pBounds.y, pBounds.width, pBounds.height);
        }
        if (rBounds.contains(touchPos)) {
            batch.draw(r, rBounds.x - 10, rBounds.y - 10, rBounds.width + 10, rBounds.height + 10);
        } else {
            batch.draw(r, rBounds.x, rBounds.y, rBounds.width, rBounds.height);
        }
        if (meBounds.contains(touchPos)) {
            batch.draw(me, meBounds.x - 10, meBounds.y - 10, meBounds.width + 10, meBounds.height + 10);
        } else {
            batch.draw(me, meBounds.x, meBounds.y, meBounds.width, meBounds.height);
        }


        batch.end();
        if (Gdx.input.isTouched()) {

            if (Gdx.input.isTouched() && pBounds.contains(touchPos)) {
                click.play();
                backSound.resume();
                gamePaused = false;
            }

            if (meBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new MainMenuScreen(game));
            }
            if (rBounds.contains(touchPos)) {
                click.play();
                game.setScreen(new GameScreen(game));
            }
        }
    }




    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movingObject.moveLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movingObject.moveRight(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            movingObject.moveUp(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            movingObject.moveDown(delta);
        }
    }
    private void spawnBag(){
        float bagX = Gdx.graphics.getWidth();
        float bagY = random.nextFloat() * (Gdx.graphics.getHeight() * 0.5f) + Gdx.graphics.getHeight() * 0.25f;
        bag = new Bag(bagX, bagY, 80, 80, new Texture("bag.png"));
        isBagSpawned = true;
    }

    private void spawnObstacles(float delta) {
        timeSinceLastSpawnObstacle1 += delta;
        timeSinceLastSpawnObstacle2 += delta;

        if (timeSinceLastSpawnObstacle1 >= 8.0f && (obstacles.isEmpty() || timeSinceLastSpawnObstacle1 >= maxSpawnInterval)) {
            spawnobject(obstacleTextures[0]);
            timeSinceLastSpawnObstacle1 = 0;
        }

        if (obstacle2Count < maxObstacle2Count && timeSinceLastSpawnObstacle2 >= minSpawnInterval && (obstacles.isEmpty() || timeSinceLastSpawnObstacle2 >= maxSpawnInterval)) {
            spawnobject(obstacleTextures[1]);
            obstacle2Count++;
            timeSinceLastSpawnObstacle2 = 0;
        }
    }


    private void spawnobject(Texture obstacleTexture){
        boolean validPosition =false;
        float obstacleX=0;
        float obstacleY=0;
        float obstacleWidth = 0;
        float obstacleHeight = 0;
        if(obstacleTexture==obstacleTextures[0]){
            obstacleHeight=80;
            obstacleWidth=60;
        }
        else if(obstacleTexture==obstacleTextures[1]){
            obstacleHeight=60;
            obstacleWidth=60;
        }
        else if(obstacleTexture==bonusTexture){
            obstacleHeight=80;
            obstacleWidth=80;
        }
        int attempts=0;
        int maxAttempts=10;
        while(!validPosition &&  attempts<maxAttempts){
            attempts++;
            obstacleX=Gdx.graphics.getWidth();
            obstacleY=random.nextFloat()*(Gdx.graphics.getHeight()*0.75f)+Gdx.graphics.getHeight()*0.25f;
            if(obstacleY +obstacleHeight >Gdx.graphics.getHeight()-5){
                continue;
            }
            validPosition=true;
            for(Obstacle obstacle: obstacles){
                if(checkOverlap(obstacleX,obstacleY,obstacleWidth,obstacleHeight,obstacle.getX(),obstacle.getY(),obstacle.getWidth(),obstacle.getHeight())){
                    validPosition=false;
                    break;
                }

            }

        }
        if(validPosition){
            obstacles.add(new Obstacle(obstacleX,obstacleY,obstacleWidth,obstacleHeight,obstacleTexture));
        }

    }
    private boolean checkOverlap(float x1, float y1, float  width1, float height1,float x2, float y2, float  width2, float height2 ){
        return x1<x2+width2&& x1+width1>x2 && y1<y2+height2 && y1+height1>y2;
    }
//    private void spawnObstacle(Texture texture, float width, float height) {
//        float x = Gdx.graphics.getWidth();
//        float y = random.nextFloat() * (Gdx.graphics.getHeight() * 0.75f) + Gdx.graphics.getHeight() * 0.25f;
//        obstacles.add(new Obstacle(x, y, width, height, texture));
//    }

    private void spawnBonusItem(float delta) {
        timeSinceLastSpawnBonus += delta;

        if (timeSinceLastSpawnBonus >= bonusSpawnInterval) {
            boolean validPosition =true;
            float bonusX = Gdx.graphics.getWidth();
            float bonusY = random.nextFloat() * (Gdx.graphics.getHeight() * 0.75f) + Gdx.graphics.getHeight() * 0.25f;
            float bonusWidth = 70;
            float bonusHeight = 70;
            for(Obstacle obstacle: obstacles){
                if(checkOverlap( bonusX,bonusY,bonusWidth,bonusHeight,obstacle.getX(),obstacle.getY(),obstacle.getWidth(),obstacle.getHeight())){
                    validPosition=false;
                    break;
                }

            }
            if(validPosition) {
                bonusItems.add(new BonusItem(bonusX, bonusY, bonusWidth, bonusHeight, bonusTexture));
            }
            timeSinceLastSpawnBonus = 0;
        }
    }

    private void updateObstacles(float delta) {
        Iterator<Obstacle> obstacleIterator = obstacles.iterator();

        while (((Iterator<?>) obstacleIterator).hasNext()) {
            Obstacle obstacle = obstacleIterator.next();
            obstacle.update(delta,backgroundSpeed);

            // Check collision with obstacle1
            if (obstacle.getTexture() == obstacleTextures[0] && checkCollision(movingObject, obstacle)) {
                end.play();
                isGameOver = true;
                font.getData().setScale(2.0f);
                break;
            }

            // Check collision with obstacle2
            if (obstacle.getTexture() == obstacleTextures[1] && checkCollision(movingObject, obstacle)) {
                hitSound.play();
                score = Math.max(0, score - 50);
                addTemporaryMessage("-50", movingObject.getPosition().x + movingObject.getWidth() / 2, movingObject.getPosition().y + movingObject.getHeight() / 2, 1.0f);
                isGameOver = false;


                obstacleIterator.remove();
                obstacle2Count--;
                continue;
            }


            if (obstacle.getX() + obstacle.getWidth() < 0) {
                if (obstacle.getTexture() == obstacleTextures[1]) {
                    obstacle2Count--;
                }
                obstacleIterator.remove();
            }
        }
    }


    private void updateBonusItems(float delta) {
                    for (BonusItem bonusItem : bonusItems) {
                        bonusItem.update(delta,backgroundSpeed);

                        if (checkCollision(movingObject, bonusItem)) {
                            bonusSoundEffect.play();
                            score += 500;
                            addTemporaryMessage("+500", movingObject.getPosition().x + movingObject.getWidth() / 2, movingObject.getPosition().y + movingObject.getHeight() / 2, 1.0f);
                            bonusItems.remove(bonusItem);
                break;
            }
        }

        bonusItems.removeIf(bonusItem -> bonusItem.getX() + bonusItem.getWidth() < 0);
    }

    private void addTemporaryMessage(String message, float x, float y, float duration) {
        messages.add(new TemporaryMessage(message, x, y, font, duration));
    }

//    private void spawnObject(Texture texture) {
//        float x = Gdx.graphics.getWidth();
//        float y = random.nextFloat() * (Gdx.graphics.getHeight() * 0.75f) + Gdx.graphics.getHeight() * 0.25f;
//        float width = 100;
//        float height = 100;
//
//        obstacles.add(new Obstacle(x, y, width, height, texture));
//    }

    private boolean checkCollision(GameObject a, GameObject b) {
//

        return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() && a.getY() + a.getHeight() > b.getY();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {}

    @Override
    public void hide() {

        backSound.stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        background2.dispose();
        movingObject.getTexture().dispose();
        for (Texture texture : obstacleTextures) {
            texture.dispose();
        }
        bonusTexture.dispose();
        font.dispose();
        bonusSoundEffect.dispose();
        backSound.dispose();
        hitSound.dispose();
        levelWin.dispose();
        end.dispose();
        p.dispose();
        ps.dispose();
        me.dispose();
        r.dispose();

    }
}