package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MovingObject {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private float scale;

    public MovingObject(Texture texture, float screenWidth, float screenHeight, float speed, float scale) {
        this.texture = texture;
        this.speed = speed;
        this.scale = scale;
        this.position = new Vector2(screenWidth / 4 - (texture.getWidth() * scale) / 2, screenHeight / 2 - (texture.getHeight() * scale) / 2);
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    public void dispose() {
        texture.dispose();
    }

    public void moveLeft(float delta) {
        position.x -= speed * delta;
    }

    public void moveRight(float delta) {
        position.x += speed * delta;
    }

    public void moveUp(float delta) {
        position.y += speed * delta;
    }

    public void moveDown(float delta) {
        position.y -= speed * delta;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return texture.getWidth() * scale;
    }

    public float getHeight() {
        return texture.getHeight() * scale;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Texture getTexture() {
        return texture;
    }
}
