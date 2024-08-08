package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected float x, y;
    protected float width, height;
    protected Texture texture;

    public GameObject(float x, float y, float width, float height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
}