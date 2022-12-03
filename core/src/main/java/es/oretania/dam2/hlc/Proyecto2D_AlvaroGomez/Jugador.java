package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Jugador extends Actor {

    Texture imagen;
    public static OrthographicCamera camera;
    public static float offsetX, offsetY;
    enum VerticalMovement {UP, NONE, DOWN};
    enum HorizontalMovement {LEFT, NONE, RIGHT};
    static int mapWidthInPixels;
    static int mapHeightInPixels;;
    HorizontalMovement horizontalMovement;
    VerticalMovement verticalMovement;
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
        camera.position.set(
                getX() + inicio.getProperties().get("x", Float.class),
                getY() + inicio.getProperties().get("y", Float.class),
                0);
        offsetX = inicio.getProperties().get("x", Float.class) - 308;
        offsetY = inicio.getProperties().get("y", Float.class) - 224;
        addListener(new JugadorInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imagen, getX(), getY());
    }

    @Override
    public void act(float delta) {
        TiledMapTileLayer.Cell cell1 = obstaculos.getCell(Math.round(getX()) / 32, Math.round(getY()) / 32);
        if (verticalMovement == VerticalMovement.UP) {
            this.moveBy(0, 100 * delta);
            if (cell1 != null) {
                this.moveBy(0, -100 * delta);
            }
        }
        if (verticalMovement == VerticalMovement.DOWN) {
            this.moveBy(0, -100 * delta);
            if (cell1 != null) {
                this.moveBy(0, 100 * delta);
            }
        }
        if (horizontalMovement == HorizontalMovement.LEFT) {
            this.moveBy(-100 * delta, 0);
            offsetX -= 100 * Gdx.graphics.getDeltaTime();
            if (cell1 != null) {
                this.moveBy(100 * delta, 0);
                offsetX += 100 * Gdx.graphics.getDeltaTime();
            }
        }
        if (horizontalMovement == HorizontalMovement.RIGHT) {
            this.moveBy(100 * delta, 0);
            if (cell1 != null) {
                this.moveBy(-100 * delta, 0);
            }
        }
        if (getX() < 0) setX(0);
        if (getY() < 0) setY(0);
        if (getX() >= 799 - getWidth()) setX(799 - getWidth());
        if (getY() >= 479 - getHeight()) setY(479 - getHeight());
    }

    class JugadorInputListener extends InputListener {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    verticalMovement = VerticalMovement.DOWN;
                    break;
                case Input.Keys.UP:
                    verticalMovement = VerticalMovement.UP;
                    break;
                case Input.Keys.LEFT:
                    horizontalMovement = HorizontalMovement.LEFT;
                    break;
                case Input.Keys.RIGHT:
                    horizontalMovement = HorizontalMovement.RIGHT;
                    break;
            }
            return true;
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    if (verticalMovement == VerticalMovement.DOWN) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.UP:
                    if (verticalMovement == VerticalMovement.UP) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.LEFT:
                    if (horizontalMovement == HorizontalMovement.LEFT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
                case Input.Keys.RIGHT:
                    if (horizontalMovement == HorizontalMovement.RIGHT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
            }
            return true;
        }
    }
}
