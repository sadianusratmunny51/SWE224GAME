package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bag extends GameObject{
    public Bag(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void update(float delta,float backgroundSpeed) {
        if(backgroundSpeed==0) x=0;
        x -= 200 * delta;
    }

    public Texture getTexture() {
        return texture;
    }


    public boolean overlaps(GameObject other) {
        Rectangle obstacleRect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.getX(), other.getY(), other.getWidth(), other.getHeight());
        return obstacleRect.overlaps(otherRect);
    }
}
