package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Manager extends Actor {

    private final Jugador jugador;
    private final Enemigo enemigo;
    public int intentosPuntuacion;

    public Manager(Jugador jugador, Enemigo enemigo){
        this.jugador = jugador;
        this.enemigo = enemigo;

        intentosPuntuacion = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(jugador.getShape(), enemigo.getShape())){
            jugador.intentosPuntuacion++;
            jugador.setX(jugador.inicioX);
            jugador.setY(jugador.inicioY);
        }
    }
}
