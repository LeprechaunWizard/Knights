package com.Dieter;

import javafx.geometry.Insets;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class QuestAppView extends Pane{
	// game window sizes
	private static final int windowWidth = 1375;
	private static final int windowHeight = 800;
	private static final int areaWidth = windowWidth - 5;
	private static final int areaHeight = 200;
	private static final int sectionHeight = 200;
	private static final int cardWidth = 110;
	private static final int cardHeight = 160;
	
	// Interface components
	private HBox playerArea;
	private HBox playerHand;
	private HBox stagingArea;
	private HBox statusArea;
	
	
	private Game model;
	ImageView cardImage;
	HBox boxToRemoveFrom;
	
	public QuestAppView(Game m) {
		model = m;
		
		// setting up the root pane
		Pane root = new Pane();
		root.setPrefSize(windowWidth, windowHeight);
		
		// setting backgrounds color
		Region background = new Region();
		background.setPrefSize(windowWidth+10, windowHeight+10);
		background.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
		
		
		// root layout
		VBox rootLayout = new VBox();
		rootLayout.setPadding(new Insets(5, 5, 5, 5));
		
		
		// set player character area
		playerArea = new HBox(5);
		playerArea.setStyle("-fx-background-color: #9b1111");
		playerArea.setPrefSize(areaWidth, areaHeight);
		
		//set Player hand
		playerHand = new HBox(5);
		playerHand.setStyle("-fx-background-color: #edea93");
		playerHand.setPrefSize(areaWidth, areaHeight);
		
		//set Staging area
		stagingArea = new HBox(5);
		stagingArea.setStyle("-fx-background-color: #71d676");
		stagingArea.setPrefSize(areaWidth, areaHeight);
		
		// set status area
		statusArea = new HBox(5);
		statusArea.setStyle("-fx-background-color: #6bdbd9");
		statusArea.setPrefSize(areaWidth, areaHeight);
		
		//setup Drag and Drop
		setupGestureTarget(playerHand);
		setupGestureTarget(stagingArea);
		
		/*
		Image boarHunt = new Image("file:Images/Boar Hunt.jpg");
		ImageView boarQuest = new ImageView(boarHunt);
		boarQuest.setFitHeight(cardHeight);
		boarQuest.setFitWidth(cardWidth);
		
		Image testing = new Image("file:Images/Amour.jpg");
		ImageView test = new ImageView(testing);
		test.setFitHeight(cardHeight);
		test.setFitWidth(cardWidth);
		test.setUserData("Amour");
		
		playerHand.getChildren().add(boarQuest);
		playerArea.getChildren().add(test);
		*/
		for(Node iv : playerArea.getChildren()) {
			if(iv instanceof ImageView) {
				System.out.println(iv.getUserData());
			}
		}
		
		
		
		// add children
		rootLayout.getChildren().addAll(statusArea, stagingArea, playerHand, playerArea);
		root.getChildren().addAll(background, rootLayout);
		getChildren().add(root);
		
		update();
	}
	
	/*************************************************************
	 * 					UPDATE
	 * ***************************************************************/
	
	public void update() {
		// TODO update the view 
		clearView();
		loadPlayerHand();
		loadPlayerArea();
		loadStatusArea();
		loadStagingArea();
	}
	
	private void loadPlayerHand() {
		// support method for Update
		List<String> cardsInHand = model.getPlayerHand();
		
		for(int i = 0; i < 12; i++) {
			String cardName = cardsInHand.get(i);
			insertImage(cardName, playerHand);
		}
		
	}
	
	private void clearView() {
		// clears the view boxes
		playerHand.getChildren().clear();
		stagingArea.getChildren().clear();
		playerArea.getChildren().clear();
		statusArea.getChildren().clear();
	}
	
	private void loadPlayerArea() {
		
		int rank = model.getCurrentPlayerRank();
		String rankName;
		int shields = model.getCurrentPlayerShields();
		
		switch (rank) {
		case 1:
			rankName = "R Squire"; 
			break;
		case 2:
			rankName = "R Knight";
			break;
		case 3:
			rankName = "R Champion Knight";
			break;
		default:
			rankName = "error";
			System.out.println("no rank error");
			break;
		}
		
		simpleInsertImage(rankName, playerArea);
		
		for(int i = 0; i < shields; i++) {
			simpleInsertImage("Shield", playerArea);
		}
	}
	
	private void loadStatusArea() {
		String story = model.getCurrentStory().getName();
		
		simpleInsertImage(story, statusArea);
	}
	
	private void loadStagingArea() {
		
	}
	
	private void simpleInsertImage(String cardName, HBox hb1) {
		
		String start = "file:Images/";
		String extention = ".jpg";
		
		Image getImage = new Image(start + cardName + extention);
		cardImage = new ImageView(getImage);
		cardImage.setUserData(cardName);
		cardImage.setFitHeight(cardHeight);
		cardImage.setFitWidth(cardWidth);
		
		hb1.getChildren().add(cardImage);
	}
	
	/*************************************************************
	 * 					DRAG AND DROP
	 * ***************************************************************/
	
	void insertImage(String cardName, HBox hb1) {
		String start = "file:Images/";
		String extention = ".jpg";
		
		Image getImage = new Image(start + cardName + extention);
		cardImage = new ImageView(getImage);
		cardImage.setUserData(cardName);
		cardImage.setFitHeight(cardHeight);
		cardImage.setFitWidth(cardWidth);
		
		setupGestureSource(cardImage);
		
		hb1.getChildren().add(cardImage);
		
	}
	
	void removeImage(ImageView image) {
		boxToRemoveFrom.getChildren().remove(image);
	}
	
	void setupGestureTarget(final HBox targetBox) {
		
		targetBox.setOnDragExited(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				boxToRemoveFrom = targetBox;
			}
		});
		targetBox.setOnDragOver(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				
				if(db.hasImage()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				
				event.consume();
			}
		});
		
		targetBox.setOnDragDropped(new EventHandler <DragEvent>() {
			public void handle(DragEvent event) {
				
				Dragboard db = event.getDragboard();
				event.acceptTransferModes(TransferMode.MOVE);
				
				removeImage(cardImage); // testing
				
				if(db.hasImage()) {
					insertImage((String)cardImage.getUserData(), targetBox);
					event.setDropCompleted(true);
				} else {
					event.setDropCompleted(false);
				}
				event.consume();
			}
			
		});
	}
	
	void setupGestureSource(final ImageView source) {
		
		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("drag detected");
				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				
				Image sourceImage = source.getImage();
				content.putImage(sourceImage);
				db.setContent(content);
				
				cardImage = source;
				
				event.consume();
			}
		});
	}
	
	
}
