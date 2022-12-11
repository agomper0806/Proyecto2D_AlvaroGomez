package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class Instrucciones extends ScreenAdapter {

    private Stage stage;
    ImpossibleGame game;
    private SpriteBatch batch;
    private Texture texture;
    Texture fondoPantalla;
    String reglas = "Viajar por el mapa\nesquivando los enemigos azules,\ny llegando a la meta\ncon todas las coins amarillas.\n" +
            "Usar las flechas de\ndireccion para mover al \npersonaje.";

    public Instrucciones(ImpossibleGame game){
        this.game = game;
        stage = new Stage();

        Label explicacion = new Label(reglas, game.gameSkin, "default");
        explicacion.setAlignment(Align.center);
        explicacion.setY(Gdx.graphics.getHeight() * 6 / 10);
        explicacion.setWidth(Gdx.graphics.getWidth());
        explicacion.setFontScale(1, 1);
        stage.addActor(explicacion);

        TextButton tbVolver = new TextButton("Volver",this.game.gameSkin,"default");
        tbVolver.setSize(200,50);
        tbVolver.setPosition(350,120);
        tbVolver.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Menu(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(tbVolver);

    }

    @Override
    public void show() {
        fondoPantalla = new Texture(Gdx.files.internal("fondoPantallaInicio.jpg"));
        Gdx.input.setInputProcessor(stage);
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
