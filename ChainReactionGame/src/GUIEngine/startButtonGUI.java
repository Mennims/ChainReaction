package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class startButtonGUI implements EventHandler<ActionEvent> {

    private int numPlayers = 2;
    private int choiceOfGrid = 0;


    @Override
    public void handle(ActionEvent e)
    {

        try {

            Files.deleteIfExists(Paths.get("game.ser"));
            Files.deleteIfExists(Paths.get("undo.ser"));
            //System.out.println("INSIDE ---- 4");

            GUIMain.setEnd_shown(false);

            GUIMain.get_gameEngine().set_choice(0);     // means that you are starting a new game

            if (GUIMain.getNumPlayersCB().getValue() != null) {
                numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
            }

            if (GUIMain.getGridChoiceCB().getValue() != null) {
                String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
                choiceOfGrid = recordedValue.equals("9x6") ? 0 : 1;
            }

            GUIMain.get_gameEngine().set_numPlayers(numPlayers);
            GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);

            //Populating the HashMap with the default values

            GUIMain.getPlayercolor().put(1, ColorUtil.colorToHex(GUIMain.getPlayer_1().getValue()));
            GUIMain.getPlayercolor().put(2, ColorUtil.colorToHex(GUIMain.getPlayer_2().getValue()));
            GUIMain.getPlayercolor().put(3, ColorUtil.colorToHex(GUIMain.getPlayer_3().getValue()));
            GUIMain.getPlayercolor().put(4, ColorUtil.colorToHex(GUIMain.getPlayer_4().getValue()));
            GUIMain.getPlayercolor().put(5, ColorUtil.colorToHex(GUIMain.getPlayer_5().getValue()));
            GUIMain.getPlayercolor().put(6, ColorUtil.colorToHex(GUIMain.getPlayer_6().getValue()));
            GUIMain.getPlayercolor().put(7, ColorUtil.colorToHex(GUIMain.getPlayer_7().getValue()));
            GUIMain.getPlayercolor().put(8, ColorUtil.colorToHex(GUIMain.getPlayer_8().getValue()));

            GUIMain.get_gameEngine().setplayer_colors(GUIMain.getPlayercolor());

            try {
                GUIMain.get_gameEngine().startGame();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Button startButton = (Button) e.getSource();
            StackPane gp = (StackPane) startButton.getParent();
            Scene sc = gp.getScene();
            Stage stage = (Stage) sc.getWindow();

            stage.setScene(GUIMain.createGamePage());


        }
        catch (Exception g){
            g.printStackTrace();
        }


    }

}
