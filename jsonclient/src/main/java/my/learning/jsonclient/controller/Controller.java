package my.learning.jsonclient.controller;

import my.learning.jsonclient.gui.CreateBookPanel;
import my.learning.jsonclient.gui.MainFrame;
import my.learning.jsonclient.gui.ViewBooksPanel;

public class Controller {
	
	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private ViewBooksPanel viewPanel;
	
	public Controller() {
		createPanel = new CreateBookPanel();
		viewPanel = new ViewBooksPanel();
		
		mainFrame = new MainFrame(createPanel, viewPanel);
	}

}
