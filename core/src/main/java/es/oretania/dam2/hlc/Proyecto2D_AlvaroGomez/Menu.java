package es.oretania.dam2.hlc.Proyecto2D_AlvaroGomez;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu extends ScreenAdapter {

    private final ImpossibleGame game;
    private Stage stage;

    public Menu(ImpossibleGame game) {
        this.game = game;
        //stage = new Stage(new ScreenViewport());
        TextButton btnFacil = new TextButton("Impossible Game", game.gameSkin, "default");
        btnFacil.setWidth(Gdx.graphics.getWidth() / 2);
        btnFacil.setPosition(
                Gdx.graphics.getWidth() / 2 - btnFacil.getWidth() / 2,
                Gdx.graphics.getHeight() * 3 / 4 - btnFacil.getHeight() / 2
        );
    }
}
