package my.learning.jsonclient;

import javax.swing.SwingUtilities;

import my.learning.jsonclient.controller.Controller;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Controller::new);
	}
}
