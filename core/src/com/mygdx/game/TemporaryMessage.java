package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TemporaryMessage {
    private String message;
    private float x;
    private float y;
    private BitmapFont font;
    private float duration;
    private float elapsedTime;

    public TemporaryMessage(String message, float x, float y, BitmapFont font, float duration) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.font = font;
        this.duration = duration;
        this.elapsedTime = 0;
    }

    public void update(float delta) {
        elapsedTime += delta;
    }

    public void render(SpriteBatch batch) {
        if (elapsedTime < duration) {
            font.draw(batch, message, x, y);
        }
    }

    public boolean isExpired() {
        return elapsedTime >= duration;
    }
}
