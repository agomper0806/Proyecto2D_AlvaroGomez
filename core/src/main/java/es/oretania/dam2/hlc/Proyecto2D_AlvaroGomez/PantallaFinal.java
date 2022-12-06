package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PantallaFinal extends ScreenAdapter {

    ImpossibleGame game;
    Texture fondoPantalla;

    public PantallaFinal(ImpossibleGame game){
        this.game = game;
    }

    @Override
    public void show() {
        fondoPantalla = new Texture(Gdx.files.internal("fondoFinalPantalla.jpg"));
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new Menu(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        float x = Gdx.graphics.getWidth() * .25f;
        int height = Gdx.graphics.getHeight();
        game.batch.draw(fondoPantalla, 0, 0, fondoPantalla.getWidth(), fondoPantalla.getHeight());
        game.font.draw(game.batch, "¡Has ganado " + PantallaDeJuego.nick + "!", x, height * .75f);
        game.font.draw(game.batch, "Presiona Intro para volver a empezar.", x, height * .25f);
        game.batch.end();
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
