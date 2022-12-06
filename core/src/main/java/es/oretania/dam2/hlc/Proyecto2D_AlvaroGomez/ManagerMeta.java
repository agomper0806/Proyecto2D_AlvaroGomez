package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ManagerMeta extends Actor {

    private Jugador jugador;
    private Meta meta;

    public ManagerMeta(Jugador jugador, Meta meta){
        this.jugador = jugador;
        this.meta = meta;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(jugador.getShape(), meta.getShape())) {
            if(jugador.contadorMonedas == jugador.numMonedas){
                PantallaDeJuego.dentroMeta = 1;
            }
        }
    }
}
