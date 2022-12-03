package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Jugador extends Actor {

    Texture imagen;
    public static OrthographicCamera camera;
    public static float offsetX, offsetY;
    enum VerticalMovement {UP, NONE, DOWN};
    enum HorizontalMovement {LEFT, NONE, RIGHT};
    TiledMap mapa;
    TiledMapTileLayer obstaculos;

    public Jugador(TiledMap mapa) {

        imagen = new Texture(Gdx.files.internal("Jugador.png"));
        this.mapa = mapa;
        obstaculos = (TiledMapTileLayer) mapa.getLayers().get("Paredes");

        MapLayer posicion = mapa.getLayers().get("Posicion");
        MapObject inicio = posicion.getObjects().get("Inicio");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        setPosition((camera.viewportWidth - 24) / 2, (camera.viewportHeight - 32) / 2);
        camera.position.set(getX() + inicio.getProperties().get("x", Float.class), getY() + inicio.getProperties().get("y", Float.class), 0);    }
}
