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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
    MapLayer posicion;
    MapObject inicio;
    Stage stage;

    public Jugador(TiledMap mapa) {

        stage = new Stage();
        imagen = new Texture(Gdx.files.internal("Jugador.png"));
        this.mapa = mapa;
        obstaculos = (TiledMapTileLayer) mapa.getLayers().get("Paredes");

        posicion = mapa.getLayers().get("Posicion");
        inicio = posicion.getObjects().get("Inicio");
        setX(inicio.getProperties().get("x", Float.class));
        setY(inicio.getProperties().get("y", Float.class));
        offsetX = 0;
        offsetY = 0;
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        addListener(new JugadorInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imagen, getX(), getY());
    }

    @Override
    public void act(float delta) {
        TiledMapTileLayer.Cell celda;
        if (verticalMovement == VerticalMovement.UP) {
            celda = obstaculos.getCell(MathUtils.round(getX()) / 32 , MathUtils.round(getY() + 1) / 32);
            if (celda == null) {
                this.moveBy(0, 150 * delta);
            }
        }

        if (verticalMovement == VerticalMovement.DOWN) {
            celda = obstaculos.getCell(MathUtils.round(getX()) / 32 , MathUtils.round(getY() - 1) / 32);
            if (celda == null) {
                this.moveBy(0, -150 * delta);
            }

        }

        if (horizontalMovement == HorizontalMovement.LEFT) {
            celda = obstaculos.getCell(MathUtils.round(getX() - 1) / 32 , MathUtils.round(getY()) / 32);
            if (celda == null) {
                this.moveBy(-150 * delta, 0);
            }
        }


        if (horizontalMovement == HorizontalMovement.RIGHT) {
            celda = obstaculos.getCell(MathUtils.round(getX() + 1) / 32 , MathUtils.round(getY()) / 32);
            if(celda == null) {
                this.moveBy(150 * delta, 0);
            }
        }
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
