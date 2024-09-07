package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BonusItem extends GameObject {
    public BonusItem(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void update(float delta,float b) {
        if(b==0)x=0;
        x -= 200 * delta; // Example movement: Move to the left
    }
}