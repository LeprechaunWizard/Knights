package com.Dieter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class QuestApp extends Application implements Runnable{
	// model and view fields
	private Game model;
	private QuestAppView view;
	private static final int windowWidth = 1385;
	private static final int windowHeight = 810;
	
	// networking fields
	private final String ip = "localhost";
	private final int port = 3000;
	//private final Thread thread;
	
	private Socket socket;
	private ServerSocket serverSocket;
	private DataOutputStream outputTurn;
	private DataInputStream inputTurn;
	private ObjectOutputStream outputBoard;
	private ObjectInputStream inputBoard;
	
	private boolean isServer = false;
	private boolean clientConnected = false;
	private int numPlayersConnected = 0;
	
	
	private boolean gameOver = false;
	private boolean gameStateUpdate = false;

	@Override
	public void start(Stage gameWindow) throws Exception {
		// TODO Auto-generated method stub
		model = Game.scenario1();
		drawStoryCard(); // TODO remove this is testing
		view = new QuestAppView(model);
		
		
		
		gameWindow.setTitle("Quest");
		gameWindow.setScene(new Scene(view, windowWidth, windowHeight));
		gameWindow.show();
		view.update();
	}
	
	public static void main(String[]args) {launch(args);}

	public void run() {
		// TODO Auto-generated method stub
		
	};
	
	/********************************************************************************************
	 * 										EVENT HANDLING
	 * ******************************************************************************************/
	
	
	
	
	/********************************************************************************************
	 * 										HANDLE METHODS
	 * ******************************************************************************************/
	
	public void handleLoadPlayerTurn() {
		
	}
	
	public void handleEndPlayerTurn() {
		
	}
	
	public void drawStoryCard() {
		model.drawStoryCard();
	}
	
	
	/*******************************************************************
	 *                     	NETWORKING METHODS
	 * *****************************************************************/
	
	private boolean connectToServer() {
		System.out.println("Trying to connect to server");
		
		try {
			socket = new Socket(ip, port);
			outputTurn = new DataOutputStream(socket.getOutputStream());
			inputTurn = new DataInputStream(socket.getInputStream());
			
			outputBoard = new ObjectOutputStream(socket.getOutputStream());
			inputBoard = new ObjectInputStream(socket.getInputStream());
			
			clientConnected = true;
			isServer = false;
			
			System.out.println("Connected to Server");
		} catch(IOException e) {
			System.out.println("> Unable to connect to server");
			return false;
		}
		return true;
	}
	
	private void initServer() {
		//TODO figure out how to que players;
		System.out.println("initializing server");
		
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
			
			isServer = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listenForServerRequest() {
		Socket socket = null;
		
		try {
			socket = serverSocket.accept(); // will block server until recieve request
			
			outputTurn = new DataOutputStream(socket.getOutputStream());
			inputTurn = new DataInputStream(socket.getInputStream());
			
			outputBoard = new ObjectOutputStream(socket.getOutputStream());
			inputBoard = new ObjectInputStream(socket.getInputStream());
			
		} catch(IOException e) {
			System.out.println("error while listening");
		}
	}
	
	private void fetchBoardState() {
		
	}
}
