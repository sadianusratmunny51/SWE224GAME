package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarItem extends GameObject {

    public StarItem(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }


    public void update(float delta, float backgroundSpeed) {
        // Update position to make the star fall
        y -= (200 * delta); // Adjust the falling speed as needed

        // Remove the star if it goes off-screen
        if (y + height < 0) {
            // This will remove the star in the NightMood class updateStarItems method
        }
    }
}
