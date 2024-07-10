package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private Texture background;
    private Texture background2;
    private float backgroundX;
    private float backgroundX2;
    private float backgroundSpeed;

    private MovingObject movingObject;
    private ArrayList<Obstacle> obstacles;

    private Random random;

    private int score;
    private BitmapFont font;
    private GlyphLayout layout;

    private Texture[] obstacleTextures;

    private float timeSinceLastSpawnObstacle1;
    private float timeSinceLastSpawnObstacle2;
    private float minSpawnInterval;
    private float maxSpawnInterval;

    private int obstacle2Count;
    private int maxObstacle2Count;

    private ShapeRenderer shapeRenderer;

    private boolean isGameOver;
    private ArrayList<TemporaryMessage> messages;
    public GameScreen(SoaringAdventure game) {
        super(game);
        messages = new ArrayList<>();
        batch = new SpriteBatch();
        background = new Texture("background.png");
        background2 = new Texture("background.png");
        backgroundX = 0;
        backgroundX2 = background.getWidth();
        backgroundSpeed = 600;

        Texture img = new Texture("object1.png");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float scale = 0.2f;
        movingObject = new MovingObject(img, screenWidth, screenHeight, 200, scale);

        obstacles = new ArrayList<>();
        random = new Random();

        score = 0;
        font = new BitmapFont();
        font.getData().setScale(2.0f);
        font.setColor(Color.BLACK);
        layout = new GlyphLayout();

        //  obstacle textures
        obstacleTextures = new Texture[]{
                new Texture("obstacle1.png"),
                new Texture("obstacle2.png"),
                // new Texture("obstacle3.png"),
                // new Texture("obstacle4.png"),

        };

        // Initial timers and intervals
        timeSinceLastSpawnObstacle1 = 0;
        timeSinceLastSpawnObstacle2 = 0;
        minSpawnInterval = 0.5f;
        maxSpawnInterval = 1.5f;

        obstacle2Count = 0;
        maxObstacle2Count = 3;

        shapeRenderer = new ShapeRenderer();

        isGameOver = false;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);

        //  background updating
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

            // Update score
            score += delta * 57.5f; // score increase over time


            spawnObstacles(delta);
            updateObstacles(delta);
        }


        batch.begin();

        // Drawing background
        batch.draw(background, backgroundX, 0, background.getWidth(), Gdx.graphics.getHeight());
        batch.draw(background2, backgroundX2, 0, background2.getWidth(), Gdx.graphics.getHeight());

        // Drawing moving object
        movingObject.render(batch);

        // Drawing obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }
        String scoreText = "Score: " + (int) score;
        layout.setText(font, scoreText);
        font.draw(batch, scoreText, Gdx.graphics.getWidth() - layout.width - 10, Gdx.graphics.getHeight() - 10);


        if (isGameOver) {
            String gameOverText = "Game Over";
            layout.setText(font, gameOverText);
            font.draw(batch, gameOverText, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);
            backgroundSpeed = 0;
        }

        for (TemporaryMessage message : messages) {
            message.render(batch);
        }
        batch.end();

        // Draw the border
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        float borderY = Gdx.graphics.getHeight() * 0.25f;
        shapeRenderer.rect(0, borderY - 2, Gdx.graphics.getWidth() - 4, 8);
        float borderUpper = Gdx.graphics.getHeight() - 5;
        shapeRenderer.rect(0, borderUpper, Gdx.graphics.getWidth(), 6);
        shapeRenderer.end();

        messages.removeIf(TemporaryMessage::isExpired);
        for (TemporaryMessage message : messages) {
            message.update(delta);
        }
    }

    private void handleInput(float delta) {
        //  player movement
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

    private void spawnObstacles(float delta) {
        timeSinceLastSpawnObstacle1 += delta;
        timeSinceLastSpawnObstacle2 += delta;

        if (timeSinceLastSpawnObstacle1 >= 5.0f && (obstacles.isEmpty() || timeSinceLastSpawnObstacle1 >= maxSpawnInterval)) {
            spawnObject(obstacleTextures[0]);
            timeSinceLastSpawnObstacle1 = 0;
        }


        if (obstacle2Count < maxObstacle2Count && timeSinceLastSpawnObstacle2 >= minSpawnInterval && (obstacles.isEmpty() || timeSinceLastSpawnObstacle2 >= maxSpawnInterval)) {
            spawnObject(obstacleTextures[1]);
            obstacle2Count++;
            timeSinceLastSpawnObstacle2 = 0;
        }
    }

    private void updateObstacles(float delta) {

        for (Obstacle obstacle : obstacles) {
            obstacle.update(delta);

            // Checking collision with obstacle1
            if (obstacle.getTexture() == obstacleTextures[0] && checkCollision(movingObject, obstacle)) {
                isGameOver = true;
                font.getData().setScale(3.0f);
            }

            // Checking collision with obstacle2
            if (obstacle.getTexture() == obstacleTextures[1] && checkCollision(movingObject, obstacle)) {
                score = Math.max(0, score - 50);
                addTemporaryMessage("-50", movingObject.getPosition().x + movingObject.getWidth() / 2, movingObject.getPosition().y + movingObject.getHeight() / 2, 0.2f);
            }
        }


        obstacles.removeIf(obstacle -> {
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                if (obstacle.getTexture() == obstacleTextures[1]) {
                    obstacle2Count--;
                }
                return true;
            }
            return false;
        });
    }
    private void addTemporaryMessage(String message, float x, float y, float duration) {
        messages.add(new TemporaryMessage(message, x, y, font, duration));
    }

    //checking overlap pf obstacle. if overlap then ignored
    private boolean checkCollision(MovingObject movingObject, Obstacle obstacle) {

        return movingObject.getPosition().x < obstacle.getX() + obstacle.getWidth() &&
                movingObject.getPosition().x + movingObject.getWidth() > obstacle.getX() + 30 &&
                movingObject.getPosition().y < obstacle.getY() + obstacle.getHeight() &&
                movingObject.getPosition().y + movingObject.getHeight() > obstacle.getY() + 50;
    }

    private void spawnObject(Texture obstacleTexture) {
        boolean validPosition = false;
        float obstacleX = 0, obstacleY = 0;
        float obstacleWidth, obstacleHeight;
      //obstacles size
        if (obstacleTexture == obstacleTextures[0]) {
            obstacleWidth = 80;
            obstacleHeight = 100;
        } else if (obstacleTexture == obstacleTextures[1]) {
            obstacleWidth = 70 ;
            obstacleHeight = 70;
        } else {
            obstacleWidth = 70;
            obstacleHeight = 80;
        }

        int attempts = 0;
        int maxAttempts = 10;

        while (!validPosition && attempts < maxAttempts) {
            attempts++;
            obstacleX = Gdx.graphics.getWidth();
            obstacleY = random.nextFloat() * (Gdx.graphics.getHeight() * 0.75f) + Gdx.graphics.getHeight() * 0.25f;

            if (obstacleY + obstacleHeight > Gdx.graphics.getHeight() - 5) {
                continue;
            }

            validPosition = true;
            for (Obstacle obstacle : obstacles) {
                if (checkOverlap(obstacleX, obstacleY, obstacleWidth, obstacleHeight, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight())) {
                    validPosition = false;
                    break;
                }
            }
        }

        if (validPosition) {
            obstacles.add(new Obstacle(obstacleX, obstacleY, obstacleWidth, obstacleHeight, obstacleTexture));
        }
    }

    private boolean checkOverlap(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // Disposing all resources
        batch.dispose();
        background.dispose();
        background2.dispose();
        movingObject.dispose();
        font.dispose();
        for (Obstacle obstacle : obstacles) {
            obstacle.dispose();
        }

        for (Texture texture : obstacleTextures) {
            texture.dispose();
        }

        shapeRenderer.dispose();
    }
}
