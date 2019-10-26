package ui;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;


public class InitUI extends Application {
    private static Stage mainStage;
    private static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/ui/main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("City, Country, River");
        Scene scene = new Scene(root);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
//        primaryStage.setWidth(Constants.SCREEN_WIDTH);
//        primaryStage.setHeight(Constants.SCREEN_HEIGHT);
//        setListeners(scene);
        mainStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void injectMaincontroller(MainController main) {
        InitUI.mainController = main;
    }

//    private static void setListeners(Scene scene) {
//        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                InitUi.mainController.handleEvent(mouseEvent);
//            }
//        });
//        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                InitUi.mainController.handleEvent(mouseEvent);
//            }
//        });
//        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                InitUi.mainController.handleEvent(mouseEvent);
//            }
//        });
//        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                InitUi.mainController.handleEvent(mouseEvent);
//            }
//        });
//        scene.addEventFilter(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                InitUi.mainController.handleEvent(mouseEvent);
//            }
//        });
//        scene.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
//            @Override
//            public void handle(ScrollEvent scrollEvent) {
//                InitUi.mainController.handleScrollEvent(scrollEvent);
//            }
//        });
//    }

    protected static Stage getMainStage() {
        return mainStage;
    }

    protected static void closeProgram() {
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION, "");
        closeAlert.setTitle("Exit");
        Button exitBtn = (Button) closeAlert.getDialogPane().lookupButton(ButtonType.OK);
        exitBtn.setText("Exit");
        Button cancelBtn = (Button) closeAlert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelBtn.setText("Cancel");
        closeAlert.setHeaderText("Exit City, Country, River?");
        closeAlert.initModality(Modality.APPLICATION_MODAL);
        closeAlert.initOwner(InitUI.getMainStage());
        Optional<ButtonType> closeResponse = closeAlert.showAndWait();
        if (ButtonType.OK.equals(closeResponse.get())) {
            InitUI.getMainStage().close();
            System.exit(0);
        }
    }

}