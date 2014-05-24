package com.phoenixjcam.application.status;

import javax.swing.JLabel;
import javax.swing.JToolBar;

public class StatusBar extends JToolBar {
    private static final long serialVersionUID = 1L;
    private JLabel lblGraph;
    private JLabel lblDrawerCoords;
    private JLabel lblSize;
    private JLabel lblDrawerSize;
    private Separator separator;

    public StatusBar() {
	setFloatable(false);
	setRollover(true);

	add(new JLabel(new javax.swing.ImageIcon(getClass().getResource(
		"/icon/coordsPosition.png"))));
	add(drawerCoords());
	add(createSeparator());

	add(new JLabel(new javax.swing.ImageIcon(getClass().getResource(
		"/icon/panelSize.png"))));
	add(drawerSize());
    }

    private JLabel drawerCoords() {
	lblDrawerCoords = new JLabel();
	lblDrawerCoords.setText("  0 x 0  ");
	return lblDrawerCoords;
    }

    private JLabel drawerSize() {
	lblDrawerSize = new JLabel();
	lblDrawerSize.setText("  0 x 0  ");
	return lblDrawerSize;
    }

    private Separator createSeparator() {
	separator = new Separator();
	return separator;
    }

    public JLabel getLblCoords() {
	return lblDrawerCoords;
    }

    public JLabel getLblDrawerSize() {
	return lblDrawerSize;
    }

    public JToolBar getStatusBar() {
	return this;
    }
}
