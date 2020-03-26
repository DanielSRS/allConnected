package br.uefs.ecomp.allconnected.view;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	double xOffset, yOffset;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
   
//    primaryStage.initStyle(StageStyle.UNDECORATED);  // Não usar bordas e barra de titulo do sistema
    	
    	
        
        // Criando botões de fechar, maximizar e minimizar
        Button closeButton = new Button("X");
        Button maximizeButton = new Button("-");
        Button minimizeButton = new Button("_");
        
        // Padronizando tamanho dos botões 
        double buttonsSize = closeButton.getHeight();
        closeButton.setMinWidth(buttonsSize);
 //     maximizeButton.setMaxSize(buttonsSize, buttonsSize);
 //     minimizeButton.setMaxSize(buttonsSize, buttonsSize);
        minimizeButton.setMinSize(buttonsSize, buttonsSize);
        maximizeButton.setMinSize(buttonsSize, buttonsSize);
        
        minimizeButton.setMinWidth(buttonsSize);
        maximizeButton.setMinWidth(buttonsSize);
        
        BorderPane root = new BorderPane(); // painel principal para organizar os elementos
        Pane center = new Pane(); // onde o grafo será renderizado
        HBox titleBar = new HBox(); 

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        maximizeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(primaryStage.isMaximized()) {
            		System.out.println(center.getHeight());
            		System.out.println(center.getLayoutY());
            		System.out.println(center.getWidth());
            		System.out.println(center.getLayoutX());
            		System.out.println("----------------------");
            		primaryStage.setMaximized(false);
            		Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                        	System.out.print("fdsv");
                        	center.getChildren().clear();
                            try {
            					GraphRender.renderGraph(center);
            				} catch (FileNotFoundException e) {
            					// TODO Auto-generated catch block
            			//		e.printStackTrace();
            				}    
                        }
                    });
                    new Thread(sleeper).start();
            	}
            	else {
            		System.out.println(center.getLayoutY());
            		System.out.println(center.getHeight());
            		System.out.println(center.getWidth());
            		System.out.println(center.getLayoutX());
            		System.out.println("______________________");
            	    primaryStage.setMaximized(true);
            	    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                        	System.out.print("fdsv");
                        	center.getChildren().clear();
                            try {
            					GraphRender.renderGraph(center);
            				} catch (FileNotFoundException e) {
            					// TODO Auto-generated catch block
            			//		e.printStackTrace();
            				}    
                        }
                    });
                    new Thread(sleeper).start();
            	} 
            }
        });
        minimizeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	primaryStage.setIconified(true);  
            }
        });
        
        titleBar.getChildren().addAll(minimizeButton, maximizeButton, closeButton);
        titleBar.setAlignment(Pos.CENTER_RIGHT);// alinhar na extrema direitda tela
        titleBar.setSpacing(10); // espaçamento entre os botões
        titleBar.setPadding(new Insets(7, 7, 2, 0)); //espaço entre as bordas da janela
        
        root.setTop(titleBar);
        root.setCenter(center);
        
        // Possibilita reposicionar a janela arrastando com o mousse
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });;
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
                center.getChildren().clear();
                try {
					GraphRender.renderGraph(center);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
				//.printStackTrace();
				}
            }
        });
        
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("Main.css").toExternalForm());
        primaryStage.setScene(scene);
        
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.show();
        
        
        GraphRender.renderGraph(center);
        
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                center.getChildren().clear();
                try {
					GraphRender.renderGraph(center);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
				}
            }
        });
        
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                center.getChildren().clear();
                try {
					GraphRender.renderGraph(center);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
				}
            }
        });
        
        
        
        //ha um bug na exibição dos elementos, estes sendo renderizados no lugar incorreto
        //a solução imediata (abaixo) é limpar tudo e por novamente em tela
        //o bug provavelmente é causado aparentemente por um delay dos valores de altura e largura
        //de center logo apor a exibição do stage.
        center.getChildren().clear();
        GraphRender.renderGraph(center);
        
        primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
		    	ObservableList<Node> cc = center.getChildren();
		    	center.getChildren().clear();
		    	center.getChildren().addAll(cc);
                try {
					GraphRender.renderGraph(center);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
				}
		    }
		});
        
        /*
        // E  vamos de gambiarra
        
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	System.out.print("fdsv");
            	center.getChildren().clear();
                try {
					GraphRender.renderGraph(center);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
				}    
            }
        });
        new Thread(sleeper).start();
        
        */
        
    }
}

/*


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    
 //   public static void main(String[] args) {
 //       launch(Main.class, args);
 //   }
    
    @Override
    public void start(Stage stage) {

        BorderPane border = new BorderPane();
        
        HBox hbox = addHBox();
        border.setTop(hbox);
        
        addStackPane(hbox);
        
        hbox.setAlignment(Pos.CENTER_RIGHT);

        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("Layout Sample");
        stage.show();
    }

    private HBox addHBox() {

        HBox hbox = new HBox();
      //  hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
     //   hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        
   //     hbox.getChildren().addAll(buttonCurrent, buttonProjected);
        
        return hbox;
    }

    private void addStackPane(HBox hb) {

        
        Button closeButton = new Button();
        closeButton.setText("X");
        
        Button maximizeButton = new Button();
        maximizeButton.setText("-");
        
        Button minimizeButton = new Button();
        minimizeButton.setText("_");
        
        HBox hjbox = new HBox();
        //  hbox.setPadding(new Insets(15, 12, 15, 12));
        hjbox.setSpacing(10);
        
        hjbox.getChildren().addAll(closeButton, maximizeButton, minimizeButton);
        
        
        
        
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
             //   System.out.println("Hello World!");
             //   System.out.println("Bye World!");
           //     primaryStage.close();
                
            }
        });
        
        maximizeButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            /*	if(primaryStage.isMaximized()) {
            		primaryStage.setMaximized(false);
            	}
            	else {
            	    primaryStage.setMaximized(true);
            	} 
                
            }
        });
        
        minimizeButton.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
            //	primaryStage.setIconified(true);
                
            }
        });
        
      //  hjbox.setAlignment(Pos.CENTER_RIGHT);
        hb.getChildren().add(hjbox);
     //   HBox.setHgrow(hjbox, Priority.ALWAYS);
                
    }

}


*/