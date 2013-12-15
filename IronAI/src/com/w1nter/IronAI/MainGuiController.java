package com.w1nter.IronAI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class MainGuiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private static Pane pictest;

    @FXML
    void initialize() {

    }
    
    public static void fadePane(){
        FadeTransition fadeTransition 
        = new FadeTransition(Duration.millis(500), pictest);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }
    
    public static void moveoutTest() {
    	fadeInPaneAnimation(pictest);;
    	fadeOutPaneAnimation(pictest);
    }
    
    private static void fadeInPaneAnimation(Pane pane){
        pane.setVisible(true);
    	pane.setRotate(180);
        FadeTransition fadeTransition 
        = new FadeTransition(Duration.millis(2000), pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
    	Path path = new Path();
    	path.getElements().add(new MoveTo(0,528));
    	path.getElements().add(new HLineTo(1040));
    	PathTransition pathTransition = new PathTransition();
    	pathTransition.setDuration(Duration.millis(2000));
    	pathTransition.setPath(path);
    	pathTransition.setNode(pane);
    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    	ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeTransition,
                pathTransition
        );
        parallelTransition.play();
    }
    
    private static void fadeOutPaneAnimation(Pane pane){
        pane.setVisible(true);
    	pane.setRotate(180);
        FadeTransition fadeTransition 
        = new FadeTransition(Duration.millis(2000), pane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
    	Path path = new Path();
    	path.getElements().add(new MoveTo(1040,528));
    	path.getElements().add(new HLineTo(2000));
    	PathTransition pathTransition = new PathTransition();
    	pathTransition.setDuration(Duration.millis(2000));
    	pathTransition.setPath(path);
    	pathTransition.setNode(pane);
    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    	ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeTransition,
                pathTransition
        );
        parallelTransition.play();
    }
}



