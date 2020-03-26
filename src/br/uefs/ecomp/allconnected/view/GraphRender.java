package br.uefs.ecomp.allconnected.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class GraphRender {
	
	public static void renderGraph(Pane center) throws FileNotFoundException {
		FileInputStream inputstream = new FileInputStream("/home/dsrs/Documents/projetos/allConnected/assets/circle-256.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image); 
        imageView.setPreserveRatio(true); 
        ImageView imageView2 = new ImageView(image); 
        imageView2.setPreserveRatio(true); 

        center.getChildren().add(imageView);
        
  //      double centralizedPosition_Height = center.getHeight() / 2;
  //      double centralizedPosition_Width = center.getWidth() / 2;
        
        imageView.setFitHeight(15); 
        imageView.setFitWidth(15);
        
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	imageView.setFitHeight(30); 
                imageView.setFitWidth(30);
            }
        });
        
        imageView2.setFitHeight(15); 
        imageView2.setFitWidth(15);
        imageView.setLayoutX((center.getWidth() / 2) - imageView.getFitHeight() / 2); 
        imageView.setLayoutY((center.getHeight() / 2) - imageView.getFitHeight() / 2);
        
        
        
        Line line = new Line();
        line.setStartX(imageView.getLayoutX() + imageView.getFitHeight() / 2);
        line.setStartY(imageView.getLayoutY() + imageView.getFitHeight() / 2);
        line.setEndX(imageView.getLayoutX() + 50);
        line.setEndY(imageView.getLayoutY() - 50);
       
        line.setStyle("-fx-stroke: white;");
        
        imageView2.setLayoutX(line.getEndX() - imageView2.getFitHeight() / 2); 
        imageView2.setLayoutY(line.getEndY() - imageView2.getFitHeight() / 2);
        
        
        
        center.getChildren().add(imageView2);
        center.getChildren().add(line);
        
	}

}
