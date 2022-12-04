package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PantallaDeJuego extends ScreenAdapter {

    private final ImpossibleGame game;
    private int dificultad;
    private Screen juego;
    Stage stage;
    TiledMap mapa;
    Actor jugador;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    Viewport viewport;
    MapProperties propiedades;
    private float offsetX, offsetY;
    private int mapWidthInPixels;
    private int mapHeightInPixels;

    public PantallaDeJuego(ImpossibleGame game, int dificultad){
        this.dificultad = dificultad;
        this.game = game;

        switch (dificultad){
            case 0:
                mapa = new TmxMapLoader().load("MapaNivelFacil.tmx");
                break;
            case 1:
                mapa = new TmxMapLoader().load("MapaNivelMedio.tmx");
                break;
            case 2:
                //mapa = new TmxMapLoader().load("MapaNivelDificil.tmx");
                break;
        }
        propiedades = mapa.getProperties();
        int tileWidth = propiedades.get("tilewidth", Integer.class);
        int tileHeight = propiedades.get("tileheight", Integer.class);
        int mapWidthInTiles = propiedades.get("width", Integer.class);
        int mapHeightInTiles = propiedades.get("height", Integer.class);
        mapWidthInPixels = mapWidthInTiles * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;
        mapRenderer = new OrthogonalTiledMapRenderer(mapa);

        camera = new OrthographicCamera();
        offsetX = 0;
        offsetY = 0;

        stage = new Stage();
        jugador = new Jugador(mapa);
        stage.addActor(jugador);
        Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(jugador);

        camera.setToOrtho(false, 640, 480);
        viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);

        if (jugador.getX() > (offsetX + camera.viewportWidth) * 0.9) {
            offsetX += 150 * delta;
        }

        if (jugador.getX() < (offsetX + 0.1 * camera.viewportWidth)) {
            offsetX -= 150 * delta;
        }

        if (jugador.getY() - mapHeightInPixels + camera.viewportHeight > (offsetY + camera.viewportHeight - jugador.getHeight()) * 0.9) {
            offsetY += 150 * delta;
        }

        if (jugador.getY() - mapHeightInPixels + camera.viewportHeight < (offsetY + 0.1 * camera.viewportHeight)) {
            offsetY -= 150 * delta;
        }

        if (offsetX < 0) offsetX = 0;
        if (offsetY > 0) offsetY = 0;
        if (offsetX > mapWidthInPixels - camera.viewportWidth) offsetX = mapWidthInPixels - camera.viewportWidth;
        if (offsetY < -mapHeightInPixels + camera.viewportHeight) offsetY = -mapHeightInPixels + camera.viewportHeight;


        if (jugador.getX() < 0) jugador.setX(0);
        if (jugador.getY() < 0) jugador.setY(0);
        if (jugador.getX() > mapWidthInPixels - jugador.getWidth()) jugador.setX(mapWidthInPixels - jugador.getWidth());
        if (jugador.getY() > mapHeightInPixels - jugador.getHeight()) jugador.setY(mapHeightInPixels - jugador.getHeight());

        camera.position.x = camera.viewportWidth / 2 + offsetX;
        camera.position.y = mapHeightInPixels - camera.viewportHeight / 2 + offsetY;
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.setToOrtho(false, width, height);
        camera.position.x = camera.viewportWidth / 2 + offsetX;
        camera.position.y = mapHeightInPixels - camera.viewportHeight / 2 + offsetY;
        camera.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
        mapa.dispose();
    }
}
