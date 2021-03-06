package GUIEngine;

import GameEngine.GameController;
import GameEngine.GameEngine;
import GameEngine.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * An {@link EventHandler} class associated with the Undo button in the game page
 *
 * @author adsrc
 * @author brihijoshi
 * @version 1.0
 *
 */

public class undoButtonGUI implements EventHandler<ActionEvent> {

    /**
     * A function that restores the state of the last move in the game by deserialising
     * the {@link GameState} object.
     *
     * Sets the <code>_gridSize</code> of the {@link GameEngine} to <code>0</code> if the grid
     * is 9x6 and to <code>1</code> if the grid if 15x10. Sets the <code>_numPlayers</code> to number
     * of players extracted from the saved state.
     *
     * @param e the {@link ActionEvent} of the Undo button clicked
     */



    @Override
    public void handle(ActionEvent e) {

        try {

            if (GameEngine.checkUndo()) {


                GUIMain.get_gameEngine().set_choice(1);

                GameController gc = new GameController();

                GUIMain.get_gameEngine().set_gc(gc);

                try {

                    GUIMain.get_gameEngine().get_gc().loadUndoState();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

                GUIMain.get_gameEngine().get_gc().saveGameState();


                GUIMain.get_gameEngine().set_numPlayers(gc.get_players().size());

                if (GUIMain.get_gameEngine().get_gc().get_grid().get_grid().size() == 9) {
                    GUIMain.get_gameEngine().set_gridSize(0);
                } else {
                    GUIMain.get_gameEngine().set_gridSize(1);
                }

                try {
                    GUIMain.get_gameEngine().startGame();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                Button undoButton = (Button) e.getSource();
                BorderPane gp = (BorderPane) undoButton.getParent();
                Scene sc = gp.getScene();
                Stage stage = (Stage) sc.getWindow();
                stage.setScene(GUIMain.createGamePage());



            }
        } catch (Exception w) {
            w.printStackTrace();
        }


    }
}
