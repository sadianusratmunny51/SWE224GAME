package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingObject extends GameObject {
    private float screenWidth, screenHeight;
    private float speed;
    private float scale;

    public MovingObject(Texture texture, float screenWidth, float screenHeight, float speed, float scale) {
        super((screenWidth / 4 -150) - texture.getWidth() * scale / 4, screenHeight / 2 - texture.getHeight() * scale / 2,
                texture.getWidth() * scale, texture.getHeight() * scale, texture);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.speed = speed;
        this.scale = scale;
    }

    public void update(float delta) {
        // Update object position or state here if necessary
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void moveLeft(float delta) {
        x = Math.max(0, x - speed * delta);
    }

    public void moveRight(float delta) {
        x = Math.min(screenWidth - width, x + speed * delta);
    }

    public void moveUp(float delta) {
        y = Math.min(screenHeight - height, y + speed * delta);
    }

    public void moveDown(float delta) {
        y = Math.max(0, y - speed * delta);
    }
}
