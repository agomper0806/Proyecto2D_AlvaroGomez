package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PantallaFinal extends ScreenAdapter {

    ImpossibleGame game;
    Stage stage;
    Texture fondoPantalla;

    public PantallaFinal(ImpossibleGame game){
        this.game = game;
        stage = new Stage(new ScreenViewport());

        //Título
        Label titulo = new Label("Felicidades " + PantallaDeJuego.nick + "\n, has ganado!!!\nPulsa INTRO para ir al menu.", game.gameSkin, "default");
        titulo.setAlignment(Align.center);
        titulo.setPosition(Gdx.graphics.getWidth() / 2 - titulo.getWidth() / 2,
                Gdx.graphics.getHeight() * 2 / 3 - titulo.getHeight() / 2);
        titulo.setFontScale(1, 2);
        stage.addActor(titulo);
    }

    @Override
    public void show() {
        fondoPantalla = new Texture(Gdx.files.internal("fondoPantallaInicio.jpg"));
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
        game.batch.draw(fondoPantalla, 0, 0, fondoPantalla.getWidth(), fondoPantalla.getHeight());
        game.batch.end();
        stage.draw();
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
