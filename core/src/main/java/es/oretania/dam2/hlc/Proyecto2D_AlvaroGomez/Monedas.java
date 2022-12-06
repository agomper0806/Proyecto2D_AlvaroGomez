package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Monedas extends Actor {

    private Texture imageMoneda;

    public Monedas(float x, float y){
        imageMoneda = new Texture(Gdx.files.internal("Moneda.png"));
        //Size importante, si no no colisionan
        setSize(imageMoneda.getWidth(), imageMoneda.getHeight());
        setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imageMoneda, getX(), getY());
    }

    public Rectangle getShape() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
