package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
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

    private Menu menu;
    private ImpossibleGame game;
    private int dificultad;
    public static Sound golpeMuerte;
    public static int dentroMeta = 0;
    Stage stage;
    TiledMap mapa;
    Jugador jugador;
    Enemigo enemigo1, enemigo2, enemigo3, enemigo4;
    Monedas moneda1, moneda2;
    Meta meta;
    Actor manager1, manager2, manager3, manager4;
    Actor manMoneda1, manMoneda2;
    Actor manMeta;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    Viewport viewport;
    MapProperties propiedades;
    public float offsetX, offsetY;
    private int mapWidthInPixels;
    private int mapHeightInPixels;


    public PantallaDeJuego(ImpossibleGame game, int dificultad){
        this.dificultad = dificultad;
        this.game = game;
        stage = new Stage();
        golpeMuerte = Gdx.audio.newSound(Gdx.files.internal("golpeMuerte.mp3"));

        switch (dificultad){
            case 0:
                mapa = new TmxMapLoader().load("MapaNivelFacil.tmx");
                int numMonedas = 2;
                //Añadir jugador
                jugador = new Jugador(mapa, numMonedas);
                stage.addActor(jugador);
                Gdx.input.setInputProcessor(stage);
                stage.setKeyboardFocus(jugador);
                //Añadir enemigos
                enemigo1 = new Enemigo(180, 210, 1);
                enemigo2 = new Enemigo(546, 510, 2);
                enemigo3 = new Enemigo(50, 560, 3);
                enemigo4 = new Enemigo(560, 370, 3);
                stage.addActor(enemigo1);
                stage.addActor(enemigo2);
                stage.addActor(enemigo3);
                stage.addActor(enemigo4);
                //Añadir manager jugador-enemigo
                manager1 = new ManagerEnemigo(jugador, enemigo1);
                manager2 = new ManagerEnemigo(jugador, enemigo2);
                manager3 = new ManagerEnemigo(jugador, enemigo3);
                manager4 = new ManagerEnemigo(jugador, enemigo4);
                stage.addActor(manager1);
                stage.addActor(manager2);
                stage.addActor(manager3);
                stage.addActor(manager4);
                //Añadir monedas
                moneda1 = new Monedas(547, 644);
                moneda2 = new Monedas(227, 132);
                stage.addActor(moneda1);
                stage.addActor(moneda2);
                //Añadir manager jugador-moneda
                manMoneda1 = new ManagerMoneda(jugador, moneda1);
                stage.addActor(manMoneda1);
                manMoneda2 = new ManagerMoneda(jugador, moneda2);
                stage.addActor(manMoneda2);
                //Añadir meta
                meta = new Meta(720, 40);
                stage.addActor(meta);
                //Añadir manager jugador-meta
                manMeta = new ManagerMeta(jugador, meta);
                jugador.toFront();
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

        camera.setToOrtho(false, 640, 480);
        viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);

        if (jugador.getX() > (offsetX + camera.viewportWidth) * 0.8) {
            offsetX += 150 * delta;
        }
        if (jugador.getX() < (offsetX + 0.2 * camera.viewportWidth)) {
            offsetX -= 150 * delta;
        }
        if (jugador.getY() - mapHeightInPixels + camera.viewportHeight + jugador.getHeight() > (offsetY + camera.viewportHeight) *0.2) {
            offsetY += 150 * delta;
        }
        if (jugador.getY()- mapHeightInPixels + camera.viewportHeight < (offsetY + 0.2 * camera.viewportHeight)) {
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

        if(dentroMeta == 1){
            game.setScreen((Screen) new Menu(game));
        }

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
