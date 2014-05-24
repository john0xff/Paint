package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import com.phoenixjcam.application.Paint;

public class HelpMenu implements ActionListener {
    private Paint paint;
    private JMenu mnHelp;

    public HelpMenu() {
	// TODO Auto-generated constructor stub
    }

    public HelpMenu(Paint paint) {
	this.paint = paint;
    }

    public JMenu helpItems() {
	mnHelp = new JMenu("Help");
	return mnHelp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }

}
