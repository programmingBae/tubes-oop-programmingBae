package lab_app.controllers;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

public class LoadingController implements Initializable {
    @FXML
    private StackPane stackPane;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread{
        public void run(){
            try {
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            FXMLLoader fxmlLoader =new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../views/Main.fxml"));
                            fxmlLoader.setResources(ResourceBundle.getBundle("resourceBundle"));
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root.setOnMousePressed(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                xOffset = event.getSceneX();
                                yOffset = event.getSceneY();
                            }
                        });

                        Stage primaryStage = new Stage();
                        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                primaryStage.setX(event.getScreenX() - xOffset);
                                primaryStage.setY(event.getScreenY() - yOffset);
                            }
                        });
                        primaryStage.setTitle("Lab App");
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        primaryStage.setScene(new Scene(root));
                        primaryStage.show();
                        stackPane.getScene().getWindow().hide();
                    }
                });


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
