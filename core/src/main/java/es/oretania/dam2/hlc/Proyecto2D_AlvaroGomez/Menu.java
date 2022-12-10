package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
    private String dificultad;
    private Texture fondoPantallaInicio;
    private Sound musicaJuego;

    public Menu(ImpossibleGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        musicaJuego = Gdx.audio.newSound(Gdx.files.internal("music.mp3"));
        musicaJuego.loop(1);

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
                Gdx.graphics.getWidth() / 2 - nick.getWidth() / 2,
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

        //Label para el error del nick
        Label error = new Label("Debes introducir un nick de jugador.", game.gameSkin, "default");
        error.setAlignment(Align.center);
        error.setY(35);
        error.setWidth(Gdx.graphics.getWidth());
        error.setFontScale(1, 1);
        error.setColor(Color.RED);
        error.setVisible(false);
        stage.addActor(error);

        //SelectBox para la dificultad
        SelectBox elegirNivel = new SelectBox(game.gameSkin, "default");
        elegirNivel.setItems("Facil", "Medio", "Dificil");
        elegirNivel.setWidth(Gdx.graphics.getWidth() / 5);
        elegirNivel.setPosition(400, 225);
        stage.addActor(elegirNivel);

        //Botón jugar
        TextButton btnJugar = new TextButton("Jugar", game.gameSkin, "default");
        btnJugar.setWidth(Gdx.graphics.getWidth() / 2);
        btnJugar.setPosition(70, 220);
        btnJugar.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(nick.getText().equals("") || nick.getText().equals("Introduce tu nick")){
                    error.setVisible(true);
                } else{
                    if(elegirNivel.getSelectedIndex() == 0)
                        game.setScreen((Screen) new PantallaDeJuego(game, 0, nick.getText()));
                    else if(elegirNivel.getSelectedIndex() == 1)
                        game.setScreen((Screen) new PantallaDeJuego(game, 1, nick.getText()));
                    else if(elegirNivel.getSelectedIndex() == 2)
                        game.setScreen((Screen) new PantallaDeJuego(game, 2, nick.getText()));
                }
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
        btnInst.setPosition(70, 160);
        btnInst.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                //hacer instrucciones
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
        btnSalir.setPosition(70, 100);
        btnSalir.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(btnSalir);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        fondoPantallaInicio = new Texture(Gdx.files.internal("fondoPantallaInicio.jpg"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(fondoPantallaInicio, 0, 0, fondoPantallaInicio.getWidth(), fondoPantallaInicio.getHeight());
        game.batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
