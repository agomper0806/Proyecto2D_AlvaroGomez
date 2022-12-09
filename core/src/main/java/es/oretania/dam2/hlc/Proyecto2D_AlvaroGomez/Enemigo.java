package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Enemigo extends Actor {

    private Texture imageEnemigo;

    public Enemigo(float x, float y, int codigoEnemigo){
        imageEnemigo = new Texture(Gdx.files.internal("Enemigo.png"));
        //Size importante, si no no colisionan
        setSize(imageEnemigo.getWidth(), imageEnemigo.getHeight());
        setPosition(x, y);

        switch (codigoEnemigo){
            case 1:
                movimientoEnemigo1(x, y);
                break;
            case 2:
                movimientoEnemigo2(x, y);
                break;
            case 3:
                movimientoEnemigo3y4(x, y);
                break;
            case 5:
                movimientoEnemigo5(x, y);
                break;
            case 6:
                movimientoEnemigo6(x, y);
                break;
            case 7:
                movimientoEnemigo7(x, y);
                break;
            case 8:
                movimientoEnemigo8(x, y);
                break;
            case 9:
                movimientoEnemigo9(x, y);
                break;
            case 10:
                movimientoEnemigo10(x, y);
                break;
        }
    }

    public void movimientoEnemigo1(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x - 150, y);
        move1.setDuration(1f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x + 150, y);
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
        move1.setDuration(0.6f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y + 100);
        move2.setDuration(0.6f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo3y4(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x + 165, y);
        move1.setDuration(0.8f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x + 165, y - 165);
        move2.setDuration(0.8f);
        MoveToAction move3 = new MoveToAction();
        move3.setPosition(x, y - 165);
        move3.setDuration(0.8f);
        MoveToAction move4 = new MoveToAction();
        move4.setPosition(x, y);
        move4.setDuration(0.8f);
        SequenceAction secuencia = new SequenceAction(move1, move2, move3, move4);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }
    public void movimientoEnemigo5(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y - 50);
        move1.setDuration(0.3f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x + 192, y - 50);
        move2.setDuration(1.152f);
        MoveToAction move3 = new MoveToAction();
        move3.setPosition(x + 192, y );
        move3.setDuration(0.3f);
        MoveToAction move4 = new MoveToAction();
        move4.setPosition(x + 192, y - 50);
        move4.setDuration(0.3f);
        MoveToAction move5 = new MoveToAction();
        move5.setPosition(x, y - 50);
        move5.setDuration(1.152f);
        MoveToAction move6 = new MoveToAction();
        move6.setPosition(x, y);
        move6.setDuration(0.3f);
        SequenceAction secuencia1 = new SequenceAction(move1, move2, move3);
        SequenceAction secuencia2 = new SequenceAction(move4, move5, move6);
        SequenceAction secuencia = new SequenceAction(secuencia1, secuencia2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo6(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y + 50);
        move1.setDuration(0.3f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x - 192, y + 50);
        move2.setDuration(1.152f);
        MoveToAction move3 = new MoveToAction();
        move3.setPosition(x - 192, y );
        move3.setDuration(0.3f);
        MoveToAction move4 = new MoveToAction();
        move4.setPosition(x - 192, y + 50);
        move4.setDuration(0.3f);
        MoveToAction move5 = new MoveToAction();
        move5.setPosition(x, y + 50);
        move5.setDuration(1.152f);
        MoveToAction move6 = new MoveToAction();
        move6.setPosition(x, y);
        move6.setDuration(0.3f);
        SequenceAction secuencia1 = new SequenceAction(move1, move2, move3);
        SequenceAction secuencia2 = new SequenceAction(move4, move5, move6);
        SequenceAction secuencia = new SequenceAction(secuencia1, secuencia2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo7(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y + 170);
        move1.setDuration(0.6f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y);
        move2.setDuration(0.6f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo8(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x - 100, y);
        move1.setDuration(0.7f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y);
        move2.setDuration(0.7f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo9(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y - 156);
        move1.setDuration(0.6f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y);
        move2.setDuration(0.6f);
        SequenceAction secuencia = new SequenceAction(move1, move2);
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(secuencia);
        addAction(forever);
    }

    public void movimientoEnemigo10(float x, float y){
        MoveToAction move1 = new MoveToAction();
        move1.setPosition(x, y + 156);
        move1.setDuration(0.6f);
        MoveToAction move2 = new MoveToAction();
        move2.setPosition(x, y);
        move2.setDuration(0.6f);
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


    public Circle getShape() {
        return new Circle(getX(), getY(), 30);
    }
}
