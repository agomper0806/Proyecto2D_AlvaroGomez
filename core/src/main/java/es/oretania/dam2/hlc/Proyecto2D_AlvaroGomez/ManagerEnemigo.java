package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ManagerEnemigo extends Actor {

    private Jugador jugador;
    private Enemigo enemigo;
    private Monedas moneda;
    public int intentosPuntuacion;

    public ManagerEnemigo(Jugador jugador, Enemigo enemigo){
        this.jugador = jugador;
        this.enemigo = enemigo;

        intentosPuntuacion = 0;
    }

    public ManagerEnemigo(Jugador jugador, Monedas moneda){
        this.jugador = jugador;
        this.moneda = moneda;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(jugador.getShape(), enemigo.getShape())){
            jugador.intentosPuntuacion++;
            jugador.setX(jugador.inicioX);
            jugador.setY(jugador.inicioY);
        } /*else if(Intersector.overlaps(jugador.getShape(), moneda.getShape())) {
            jugador.intentosPuntuacion++;
            jugador.setX(jugador.inicioX);
            jugador.setY(jugador.inicioY);
        }*/
    }
}
