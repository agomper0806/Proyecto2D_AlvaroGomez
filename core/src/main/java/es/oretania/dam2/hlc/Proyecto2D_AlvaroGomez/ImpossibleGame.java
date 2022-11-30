package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ImpossibleGame extends Game {

    BitmapFont font;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    static public Skin gameSkin;

    @Override
    public void create() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        gameSkin = new Skin(Gdx.files.internal("quantum-horizon/skin/quantum-horizon-ui.json"));

        setScreen(new Menu(this));
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
