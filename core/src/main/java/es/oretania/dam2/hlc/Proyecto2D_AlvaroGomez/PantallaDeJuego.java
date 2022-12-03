package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class PantallaDeJuego extends ScreenAdapter {

    private final ImpossibleGame game;
    private int dificultad;
    private Screen juego;
    Stage stage;
    TiledMap mapa;
    OrthogonalTiledMapRenderer mapRenderer;
    MapProperties propiedades;

    public PantallaDeJuego(ImpossibleGame game, int dificultad){
        this.dificultad = dificultad;
        this.game = game;
    }

    @Override
    public void show() {
        switch (dificultad){
            case 0:
                mapa = new TmxMapLoader().load("MapaNivelFacil.tmx");
                break;
            case 1:
                //mapa = new TmxMapLoader().load("MapaNivelMedio.tmx");
                break;
            case 2:
                //mapa = new TmxMapLoader().load("MapaNivelDificil.tmx");
                break;
        }
        stage = new Stage();
        Actor jugador = new Jugador(mapa);
        stage.addActor(jugador);
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(jugador);

        propiedades = mapa.getProperties();
        int tileWidth = propiedades.get("tilewidth", Integer.class);
        int tileHeight = propiedades.get("tileheight", Integer.class);
        int mapWidthInTiles = propiedades.get("width", Integer.class);
        int mapHeightInTiles = propiedades.get("height", Integer.class);

        mapRenderer = new OrthogonalTiledMapRenderer(mapa);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        mapa.dispose();
    }
}
