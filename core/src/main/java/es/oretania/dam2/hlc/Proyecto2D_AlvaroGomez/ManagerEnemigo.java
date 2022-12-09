package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ManagerEnemigo extends Actor {

    private Jugador jugador;
    private Enemigo enemigo;

    public ManagerEnemigo(Jugador jugador, Enemigo enemigo){
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(enemigo.getShape(), jugador.getShape())){
            PantallaDeJuego.golpeMuerte.play();
            jugador.intentosPuntuacion++;
            jugador.setX(jugador.inicioX);
            jugador.setY(jugador.inicioY);
        }
    }
}
