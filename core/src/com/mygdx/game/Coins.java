package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Coins extends GameObject {

    private float speed;

    public Coins(Texture texture, float x, float y, float width, float height, float speed) {
        super(x, y, width, height, texture);
        this.speed = speed;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void update(float delta) {
        x -= speed * delta; // Move to the left based on speed
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public String toString() {
        return "Coins [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed=" + speed + "]";
    }
}
