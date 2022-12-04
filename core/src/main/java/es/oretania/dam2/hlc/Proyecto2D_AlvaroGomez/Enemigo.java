package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Enemigo extends Actor {

    public Texture imageEnemigo;
    private int codigoEnemigo;

    public Enemigo(float x, float y, int codigoEnemigo){
        this.codigoEnemigo = codigoEnemigo;
        imageEnemigo = new Texture(Gdx.files.internal("Enemigo.png"));
        setPosition(x - getWidth() / 2, y - getHeight() / 2);

        switch (codigoEnemigo){
            case 1:
                movimientoEnemigo1(x, y);
                break;
            case 2:
                movimientoEnemigo2(x, y);
        }

    }

    public void movimientoEnemigo1(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x - 100, y);
        move1.setDuration(1f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x + 100, y);
        move2.setDuration(1f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo2(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y - 100);
        move1.setDuration(1f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y + 100);
        move2.setDuration(1f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imageEnemigo, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta); // MUY IMPORTANTE
        if (getX() < 0) setX(0);
        if (getY() < 0) setY(0);
        if (getX() >= 639 - getWidth()) setX(639 - getWidth());
        if (getY() >= 479 - getHeight()) setY(479 - getHeight());
    }

}
