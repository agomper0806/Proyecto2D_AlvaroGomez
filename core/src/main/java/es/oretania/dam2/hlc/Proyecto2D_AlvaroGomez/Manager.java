package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Manager extends Actor {

    private static BitmapFont font;
    private final Jugador jugador;
    private final Enemigo enemigo;
    public int intentosPuntuacion;

    public Manager(Jugador jugador, Enemigo enemigo){
        this.jugador = jugador;
        this.enemigo = enemigo;
        font = new BitmapFont();

        intentosPuntuacion = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Intentos: " + intentosPuntuacion, 20, 460);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(jugador.getShape(), enemigo.getShape())){
            intentosPuntuacion++;
            jugador.setX(jugador.inicioX);
            jugador.setY(jugador.inicioY);
        }
    }
}
