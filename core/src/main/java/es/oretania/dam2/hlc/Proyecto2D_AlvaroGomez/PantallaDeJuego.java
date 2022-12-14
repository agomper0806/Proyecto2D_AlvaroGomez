package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public static String nick;
    private int dificultad;
    public static Sound golpeMuerte, sonidoMoneda, victoria;
    Stage stage;
    TiledMap mapa;
    Jugador jugador;
    //Enemigos facil
    Enemigo enemigoF1, enemigoF2, enemigoF3, enemigoF4;
    Actor managerF1, managerF2, managerF3, managerF4;
    //Enemigos medio
    Enemigo enemigoM1, enemigoM2, enemigoM3, enemigoM4, enemigoM5;
    Actor managerM1, managerM2, managerM3, managerM4, managerM5;
    //Enemigos dificil
    Enemigo enemigoD1, enemigoD2, enemigoD3, enemigoD4, enemigoD5, enemigoD6, enemigoD7, enemigoD8, enemigoD9, enemigoD10, enemigoD11, enemigoD12, enemigoD13, enemigoD14, enemigoD15;
    Actor managerD1, managerD2, managerD3, managerD4, managerD5, managerD6, managerD7, managerD8, managerD9, managerD10, managerD11, managerD12, managerD13, managerD14, managerD15;
    //Monedas
    Monedas moneda1, moneda2, moneda3, moneda4;
    Actor manMoneda1, manMoneda2, manMoneda3, manMoneda4;
    //Meta
    Meta meta;
    Actor manMeta;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    Viewport viewport;
    MapProperties propiedades;
    SpriteBatch batch;
    BitmapFont font;
    public float offsetX, offsetY;
    private int mapWidthInPixels;
    private int mapHeightInPixels;
    int numMonedas;


    public PantallaDeJuego(ImpossibleGame game, int dificultad, String nick){
        this.dificultad = dificultad;
        this.game = game;
        this.nick = nick;
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage();
        golpeMuerte = Gdx.audio.newSound(Gdx.files.internal("golpeMuerte.mp3"));
        sonidoMoneda = Gdx.audio.newSound(Gdx.files.internal("monedas.mp3"));
        victoria = Gdx.audio.newSound(Gdx.files.internal("victoria.mp3"));

        switch (dificultad){
            case 0:
                mapa = new TmxMapLoader().load("MapaNivelFacil.tmx");
                numMonedas = 2;
                //A??adir jugador
                jugador = new Jugador(mapa, numMonedas, game);
                stage.addActor(jugador);
                Gdx.input.setInputProcessor(stage);
                stage.setKeyboardFocus(jugador);
                //A??adir enemigos
                enemigoF1 = new Enemigo(180, 210, 1);
                enemigoF2 = new Enemigo(546, 510, 2);
                enemigoF3 = new Enemigo(50, 560, 3);
                enemigoF4 = new Enemigo(560, 370, 3);
                stage.addActor(enemigoF1);
                stage.addActor(enemigoF2);
                stage.addActor(enemigoF3);
                stage.addActor(enemigoF4);
                //A??adir manager jugador-enemigo
                managerF1 = new ManagerEnemigo(jugador, enemigoF1);
                managerF2 = new ManagerEnemigo(jugador, enemigoF2);
                managerF3 = new ManagerEnemigo(jugador, enemigoF3);
                managerF4 = new ManagerEnemigo(jugador, enemigoF4);
                stage.addActor(managerF1);
                stage.addActor(managerF2);
                stage.addActor(managerF3);
                stage.addActor(managerF4);
                //A??adir monedas
                moneda1 = new Monedas(547, 644);
                moneda2 = new Monedas(227, 132);
                stage.addActor(moneda1);
                stage.addActor(moneda2);
                //A??adir manager jugador-moneda
                manMoneda1 = new ManagerMoneda(jugador, moneda1);
                stage.addActor(manMoneda1);
                manMoneda2 = new ManagerMoneda(jugador, moneda2);
                stage.addActor(manMoneda2);
                //A??adir meta
                meta = new Meta(720, 40);
                stage.addActor(meta);
                //A??adir manager jugador-meta
                manMeta = new ManagerMeta(jugador, meta);
                stage.addActor(manMeta);
                jugador.toFront();
                break;
            case 1:
                mapa = new TmxMapLoader().load("MapaNivelMedio.tmx");
                int numMonedas = 3;
                //A??adir jugador
                jugador = new Jugador(mapa, numMonedas, game);
                stage.addActor(jugador);
                Gdx.input.setInputProcessor(stage);
                stage.setKeyboardFocus(jugador);
                //A??adir enemigos
                enemigoM1 = new Enemigo(307, 692, 5);
                enemigoM2 = new Enemigo(498, 528, 6);
                enemigoM3 = new Enemigo(274, 340, 7);
                enemigoM4 = new Enemigo(595, 182, 8);
                enemigoM5 = new Enemigo(658, 182, 9);
                stage.addActor(enemigoM1);
                stage.addActor(enemigoM2);
                stage.addActor(enemigoM3);
                stage.addActor(enemigoM4);
                stage.addActor(enemigoM5);
                //A??adir manager jugador-enemigo
                managerM1 = new ManagerEnemigo(jugador, enemigoM1);
                managerM2 = new ManagerEnemigo(jugador, enemigoM2);
                managerM3 = new ManagerEnemigo(jugador, enemigoM3);
                managerM4 = new ManagerEnemigo(jugador, enemigoM4);
                managerM5 = new ManagerEnemigo(jugador, enemigoM5);
                stage.addActor(managerM1);
                stage.addActor(managerM2);
                stage.addActor(managerM3);
                stage.addActor(managerM4);
                stage.addActor(managerM5);
                //A??adir monedas
                moneda1 = new Monedas(307, 692);
                moneda2 = new Monedas(498, 528);
                moneda3 = new Monedas(145, 210);
                stage.addActor(moneda1);
                stage.addActor(moneda2);
                stage.addActor(moneda3);
                //A??adir manager jugador-moneda
                manMoneda1 = new ManagerMoneda(jugador, moneda1);
                manMoneda2 = new ManagerMoneda(jugador, moneda2);
                manMoneda3 = new ManagerMoneda(jugador, moneda3);
                stage.addActor(manMoneda1);
                stage.addActor(manMoneda2);
                stage.addActor(manMoneda3);
                //A??adir meta
                meta = new Meta(725, 120);
                stage.addActor(meta);
                //A??adir manager jugador-meta
                manMeta = new ManagerMeta(jugador, meta);
                stage.addActor(manMeta);
                enemigoM1.toFront();
                enemigoM2.toFront();
                jugador.toFront();
                break;
            case 2:
                mapa = new TmxMapLoader().load("MapaNivelDificil.tmx");
                numMonedas = 4;
                //A??adir jugador
                jugador = new Jugador(mapa, numMonedas, game);
                stage.addActor(jugador);
                Gdx.input.setInputProcessor(stage);
                stage.setKeyboardFocus(jugador);
                //A??adir enemigos
                enemigoD1 = new Enemigo(115, 660, 10);
                enemigoD2 = new Enemigo(243, 188, 11);
                enemigoD3 = new Enemigo(370, 149, 12);
                enemigoD4 = new Enemigo(720, 94, 13);
                enemigoD5 = new Enemigo(370, 39, 12);
                enemigoD6 = new Enemigo(370, 273, 14);
                enemigoD7 = new Enemigo(370, 373, 14);
                enemigoD8 = new Enemigo(720, 323, 15);
                enemigoD9 = new Enemigo(720, 423, 15);
                enemigoD10 = new Enemigo(355, 738, 16);
                enemigoD11 = new Enemigo(355, 638, 16);
                enemigoD12 = new Enemigo(471, 738, 16);
                enemigoD13 = new Enemigo(471, 638, 16);
                enemigoD14 = new Enemigo(587, 738, 16);
                enemigoD15 = new Enemigo(587, 638, 16);
                stage.addActor(enemigoD1);
                stage.addActor(enemigoD2);
                stage.addActor(enemigoD3);
                stage.addActor(enemigoD4);
                stage.addActor(enemigoD5);
                stage.addActor(enemigoD6);
                stage.addActor(enemigoD7);
                stage.addActor(enemigoD8);
                stage.addActor(enemigoD9);
                stage.addActor(enemigoD10);
                stage.addActor(enemigoD11);
                stage.addActor(enemigoD12);
                stage.addActor(enemigoD13);
                stage.addActor(enemigoD14);
                stage.addActor(enemigoD15);
                //A??adir manager jugador-enemigo
                managerD1 = new ManagerEnemigo(jugador, enemigoD1);
                managerD2 = new ManagerEnemigo(jugador, enemigoD2);
                managerD3 = new ManagerEnemigo(jugador, enemigoD3);
                managerD4 = new ManagerEnemigo(jugador, enemigoD4);
                managerD5 = new ManagerEnemigo(jugador, enemigoD5);
                managerD6 = new ManagerEnemigo(jugador, enemigoD6);
                managerD7 = new ManagerEnemigo(jugador, enemigoD7);
                managerD8 = new ManagerEnemigo(jugador, enemigoD8);
                managerD9 = new ManagerEnemigo(jugador, enemigoD9);
                managerD10 = new ManagerEnemigo(jugador, enemigoD10);
                managerD11 = new ManagerEnemigo(jugador, enemigoD11);
                managerD12 = new ManagerEnemigo(jugador, enemigoD12);
                managerD13 = new ManagerEnemigo(jugador, enemigoD13);
                managerD14 = new ManagerEnemigo(jugador, enemigoD14);
                managerD15 = new ManagerEnemigo(jugador, enemigoD15);
                stage.addActor(managerD1);
                stage.addActor(managerD2);
                stage.addActor(managerD3);
                stage.addActor(managerD4);
                stage.addActor(managerD5);
                stage.addActor(managerD6);
                stage.addActor(managerD7);
                stage.addActor(managerD8);
                stage.addActor(managerD9);
                stage.addActor(managerD10);
                stage.addActor(managerD11);
                stage.addActor(managerD12);
                stage.addActor(managerD13);
                stage.addActor(managerD14);
                stage.addActor(managerD15);
                //A??adir monedas
                moneda1 = new Monedas(245, 135);
                moneda2 = new Monedas(370, 149);
                moneda3 = new Monedas(720, 39);
                moneda4 = new Monedas(355, 738);
                stage.addActor(moneda1);
                stage.addActor(moneda2);
                stage.addActor(moneda3);
                stage.addActor(moneda4);
                //A??adir manager jugador-moneda
                manMoneda1 = new ManagerMoneda(jugador, moneda1);
                manMoneda2 = new ManagerMoneda(jugador, moneda2);
                manMoneda3 = new ManagerMoneda(jugador, moneda3);
                manMoneda4 = new ManagerMoneda(jugador, moneda4);
                stage.addActor(manMoneda1);
                stage.addActor(manMoneda2);
                stage.addActor(manMoneda3);
                stage.addActor(manMoneda4);
                //A??adir meta
                meta = new Meta(725, 720);
                stage.addActor(meta);
                //A??adir manager jugador-meta
                manMeta = new ManagerMeta(jugador, meta);
                stage.addActor(manMeta);
                enemigoD2.toFront();
                enemigoD3.toFront();
                enemigoD5.toFront();
                enemigoD10.toFront();
                jugador.toFront();
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

        camera.position.x = camera.viewportWidth / 2 + offsetX;
        camera.position.y = mapHeightInPixels - camera.viewportHeight / 2 + offsetY;
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();
        stage.draw();


        batch.begin();
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        font.draw(batch, "Intentos: " + jugador.intentosPuntuacion, mapWidthInPixels*0.64f, mapHeightInPixels*0.58f);
        font.draw(batch, "Monedas: " + jugador.contadorMonedas + " / " + jugador.numMonedas, mapWidthInPixels*0.64f, mapHeightInPixels*0.56f);
        batch.end();
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
