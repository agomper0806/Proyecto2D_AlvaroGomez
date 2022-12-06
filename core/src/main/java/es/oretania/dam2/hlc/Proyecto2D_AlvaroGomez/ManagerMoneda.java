package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ManagerMoneda extends Actor {

    private Jugador jugador;
    private Monedas moneda;

    public ManagerMoneda(Jugador jugador, Monedas moneda){
        this.jugador = jugador;
        this.moneda = moneda;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Intersector.overlaps(jugador.getShape(), moneda.getShape())) {
            moneda.addAction(Actions.removeActor());
        }
    }
}
