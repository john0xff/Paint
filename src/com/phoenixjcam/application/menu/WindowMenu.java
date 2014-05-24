package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import com.phoenixjcam.application.Paint;

public class WindowMenu implements ActionListener {
    private Paint paint;
    private JMenu mnWindow;

    public WindowMenu() {
	// TODO Auto-generated constructor stub
    }

    public WindowMenu(Paint paint) {
	this.paint = paint;
    }

    public JMenu windowItems() {
	mnWindow = new JMenu("Window");
	return mnWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }
}
