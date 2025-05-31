package main;

import interfaz.Base;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {	 {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Base frame = new Base();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	}

}
