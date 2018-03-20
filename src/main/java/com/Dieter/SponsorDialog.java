package com.Dieter;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SponsorDialog extends Dialog{
	@SuppressWarnings("unchecked")
	public SponsorDialog(Stage owner, String title, Game model) {
		final ButtonType sponsorButtonType = new ButtonType("Sponsor", ButtonBar.ButtonData.OK_DONE);
		
		setTitle(title);
		
		getDialogPane().getButtonTypes().addAll(sponsorButtonType, ButtonType.CANCEL);
		
		//TODO display the card
		String start = "file:Images/";
		String extention = ".jpg";
		
		Image getImage = new Image(start + model.getCurrentStory().getName() + extention);
		ImageView cardImage = new ImageView(getImage);
		cardImage.setFitHeight(160);
		cardImage.setFitWidth(110);
		
		getDialogPane().setContent(cardImage);
		
		setResultConverter(new Callback<ButtonType, Game>() {
			
			public Game call(ButtonType b) {
				if(b == sponsorButtonType) {
					model.setSponsor();
				}
			}
		});
	}
}
