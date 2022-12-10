package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Jugador extends Actor {

    ImpossibleGame game;
    BitmapFont fuente;
    public int intentosPuntuacion;
    public int numMonedas, contadorMonedas;
    public static boolean dentroMeta;
    private Texture imageJugador;
    enum VerticalMovement {UP, NONE, DOWN};
    enum HorizontalMovement {LEFT, NONE, RIGHT};
    public float inicioX, inicioY;
    HorizontalMovement horizontalMovement;
    VerticalMovement verticalMovement;
    TiledMap mapa;
    TiledMapTileLayer obstaculos;
    MapLayer posicion;
    MapObject inicio;
    Stage stage;

    TiledMapTileLayer.Cell cell1, cell2, cell3, cell4;
    int baseIzqX, baseIzqY, baseDerX, baseDerY, baseArribaIzqX, baseArribaIzqY, baseArribaDerX, baseArribaDerY;
    int jugadorWidth, jugadorHeight, tileWidth = 32, tileHeight = 32;
    float ultX, ultY;


    public Jugador(TiledMap mapa, int numMonedas, ImpossibleGame game) {
        if(fuente == null){
            fuente = new BitmapFont();
        }
        intentosPuntuacion = 0;
        this.mapa = mapa;
        this.numMonedas = numMonedas;
        this.game = game;
        contadorMonedas = 0;
        dentroMeta = false;

        stage = new Stage();
        imageJugador = new Texture(Gdx.files.internal("Jugador.png"));
        jugadorWidth = imageJugador.getWidth();
        jugadorHeight = imageJugador.getHeight();
        obstaculos = (TiledMapTileLayer) mapa.getLayers().get("Paredes");
        posicion = mapa.getLayers().get("Posicion");
        inicio = posicion.getObjects().get("Inicio");
        inicioX = inicio.getProperties().get("x", Float.class);
        inicioY = inicio.getProperties().get("y", Float.class);
        //Size importante, si no no colisionan
        setSize(jugadorWidth, jugadorHeight);
        setPosition(inicioX, inicioY);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        addListener(new JugadorInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(imageJugador, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        ultX = getX();
        ultY = getY();

        if (verticalMovement == VerticalMovement.UP) {
            this.moveBy(0, 150 * delta);
            if(dentroMeta == true){
                game.setScreen(new PantallaFinal(game));
                PantallaDeJuego.victoria.play();
            }
        }
        if (verticalMovement == VerticalMovement.DOWN) {
            this.moveBy(0, -150 * delta);
            if(dentroMeta == true){
                game.setScreen(new PantallaFinal(game));
                PantallaDeJuego.victoria.play();
            }
        }
        if (horizontalMovement == HorizontalMovement.LEFT) {
            this.moveBy(-150 * delta, 0);
            if(dentroMeta == true){
                game.setScreen(new PantallaFinal(game));
                PantallaDeJuego.victoria.play();
            }
        }
        if (horizontalMovement == HorizontalMovement.RIGHT) {
            this.moveBy(150 * delta, 0);
            if(dentroMeta == true){
                game.setScreen(new PantallaFinal(game));
                PantallaDeJuego.victoria.play();
            }
        }

        //COORDENADA ABAJO IZQUIERDA
        baseIzqX = (int) getX() / tileWidth;
        baseIzqY = (int) getY() / tileHeight;
        //COORDENADA ABAJO DERECHA
        baseDerX = (int) (getX() + jugadorWidth) / tileWidth;
        baseDerY = (int) getY() / tileHeight;
        //COORDENADA ARRIBA IZQUIERDA
        baseArribaIzqX = (int) getX() / tileWidth;
        baseArribaIzqY = (int) (getY() + jugadorHeight) / tileHeight;
        //COORDENADA ARRIBA DERECHA
        baseArribaDerX = (int) (getX() + jugadorWidth) / tileWidth;
        baseArribaDerY = (int) (getY() + jugadorHeight) / tileHeight;

        cell1 = obstaculos.getCell(baseIzqX, baseIzqY);
        cell2 = obstaculos.getCell(baseDerX, baseDerY);
        cell3 = obstaculos.getCell(baseArribaIzqX, baseArribaIzqY);
        cell4 = obstaculos.getCell(baseArribaDerX, baseArribaDerY);

        if (cell1 != null || cell2 != null || cell3!= null || cell4!=null) {
            setPosition(ultX, ultY);
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

    public Rectangle getShape() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
