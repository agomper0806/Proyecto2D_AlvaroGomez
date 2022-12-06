package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Meta extends Actor {

    private Texture imageMeta;

    public Meta(float x, float y){
        imageMeta = new Texture(Gdx.files.internal("Meta.png"));
        //Size importante, si no no colisionan
        setSize(imageMeta.getWidth(), imageMeta.getHeight());
        setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imageMeta, getX(), getY());
    }

    public Rectangle getShape() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
