package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu extends ScreenAdapter {

    private final ImpossibleGame game;
    private Stage stage;
    private SpriteBatch batch;

    public Menu(ImpossibleGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        //Título
        Label titulo = new Label("Impossible Game", game.gameSkin, "default");
        titulo.setAlignment(Align.center);
        titulo.setY(Gdx.graphics.getHeight() * 8 / 10);
        titulo.setWidth(Gdx.graphics.getWidth());
        titulo.setFontScale(2, 2);
        stage.addActor(titulo);

        //Nick
        TextField nick = new TextField("Introduce tu nick", game.gameSkin, "default");
        nick.setWidth(Gdx.graphics.getWidth() / 2);
        nick.setPosition(
                Gdx.graphics.getWidth() / 3 - nick.getWidth() / 2,
                Gdx.graphics.getHeight() * 2 / 3 - nick.getHeight() / 2
        );
        nick.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                nick.setText("");
                return true;
            }
        });
        stage.addActor(nick);


        //Botón jugar
        TextButton btnJugar = new TextButton("Jugar", game.gameSkin, "default");
        btnJugar.setWidth(Gdx.graphics.getWidth() / 2);
        btnJugar.setPosition(
                Gdx.graphics.getWidth() / 3 - btnJugar.getWidth() / 2,
                Gdx.graphics.getHeight() * 2 / 4 - btnJugar.getHeight() / 2
        );
        btnJugar.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen((Screen) new PantallaDeJuego());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnJugar);


        //Botón instrucciones
        TextButton btnInst = new TextButton("Instrucciones", game.gameSkin, "default");
        btnInst.setWidth(Gdx.graphics.getWidth() / 2);
        btnInst.setPosition(
                Gdx.graphics.getWidth() / 3 - btnInst.getWidth() / 2,
                Gdx.graphics.getHeight() * 2 / 5 - btnInst.getHeight() / 2
        );
        btnInst.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen((Screen) new PantallaDeJuego());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnInst);


        //Botón salir
        TextButton btnSalir = new TextButton("Salir", game.gameSkin, "default");
        btnSalir.setWidth(Gdx.graphics.getWidth() / 2);
        btnSalir.setPosition(
                Gdx.graphics.getWidth() / 3 - btnSalir.getWidth() / 2,
                Gdx.graphics.getHeight() * 2 / 6 - btnSalir.getHeight() / 2
        );
        btnSalir.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen((Screen) new PantallaDeJuego());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnSalir);


        SelectBox elegirNivel = new SelectBox(game.gameSkin, "default");
        elegirNivel.setItems("Facil", "Medio", "Dificil");
        stage.addActor(elegirNivel);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
