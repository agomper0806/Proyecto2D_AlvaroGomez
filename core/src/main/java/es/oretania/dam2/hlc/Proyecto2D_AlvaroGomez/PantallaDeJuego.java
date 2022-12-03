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
        Jugador.mapWidthInPixels = mapWidthInTiles * tileWidth;
        Jugador.mapHeightInPixels = mapHeightInTiles * tileHeight;

        mapRenderer = new OrthogonalTiledMapRenderer(mapa);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Jugador.offsetX < 0) Jugador.offsetX = 0;
        if (Jugador.offsetY > 0) Jugador.offsetY = 0;
        if (Jugador.offsetX > Jugador.mapWidthInPixels - Jugador.camera.viewportWidth)
            Jugador.offsetX = Jugador.mapWidthInPixels - Jugador.camera.viewportWidth;
        if (Jugador.offsetY < -Jugador.mapHeightInPixels + Jugador.camera.viewportHeight)
            Jugador.offsetY = -Jugador.mapHeightInPixels + Jugador.camera.viewportHeight;
        Jugador.camera.position.x = Jugador.camera.viewportWidth / 2 + Jugador.offsetX;
        Jugador.camera.position.y = Jugador.mapHeightInPixels - Jugador.camera.viewportHeight / 2 + Jugador.offsetY;
        Jugador.camera.update();
        mapRenderer.setView(Jugador.camera);
        mapRenderer.render();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Jugador.camera.setToOrtho(false, width, height);
        Jugador.camera.position.x = Jugador.camera.viewportWidth / 2 + Jugador.offsetX;
        Jugador.camera.position.y = Jugador.mapHeightInPixels - Jugador.camera.viewportHeight / 2 + Jugador.offsetY;
        Jugador.camera.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        mapa.dispose();
    }
}
