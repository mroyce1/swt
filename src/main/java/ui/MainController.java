package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private static MainController instance;
    @FXML
    private AnchorPane pane;

    public static MainController getInstance() {
        return instance;
    }


    public void initialize(URL location, ResourceBundle resources) {

//            this.repaint();
    }
}

