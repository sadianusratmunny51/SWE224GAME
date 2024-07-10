package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class TemporaryMessage {
    private String message;
    private float x, y;
    private BitmapFont font;
    private GlyphLayout layout;
    private float duration;
    private float timeElapsed;

    public TemporaryMessage(String message, float x, float y, BitmapFont font, float duration) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.font = font;
        this.layout = new GlyphLayout();
        this.duration = duration;
        this.timeElapsed = 0;
        this.font.setColor(Color.BLACK);
    }

    public boolean isExpired() {
        return timeElapsed > duration;
    }

    public void update(float delta) {
        timeElapsed += delta;
    }

    public void render(SpriteBatch batch) {
        layout.setText(font, message);
        font.draw(batch, message, x - layout.width / 2, y + layout.height / 2);
    }
}
