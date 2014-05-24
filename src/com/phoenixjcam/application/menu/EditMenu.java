package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.phoenixjcam.application.Paint;

public class EditMenu implements ActionListener {
    private Paint paint;
    private JMenu mnEdit;
    private JMenuItem mntmUndo;
    private JMenuItem mntmRedo;
    private JMenuItem mntmCopy;
    private JMenuItem mntmCut;
    private JMenuItem mntmPaste;

    public EditMenu() {
	// TODO Auto-generated constructor stub
    }

    public EditMenu(Paint paint) {
	this.paint = paint;
    }

    public JMenu editItems() {
	mnEdit = new JMenu("Edit");

	mnEdit.add(editUndo());
	mnEdit.add(editRedo());
	mnEdit.add(editCopy());
	mnEdit.add(editCut());
	mnEdit.add(editPaste());

	return mnEdit;
    }

    public JMenuItem editUndo() {
	mntmUndo = new JMenuItem("Undo");
	mntmUndo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
	mntmUndo.addActionListener(this);
	return mntmUndo;
    }

    public JMenuItem editRedo() {
	mntmRedo = new JMenuItem("Redo");
	mntmRedo.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
	mntmRedo.addActionListener(this);
	return mntmRedo;
    }

    public JMenuItem editCopy() {
	mntmCopy = new JMenuItem("Copy");
	mntmCopy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
	mntmCopy.addActionListener(this);
	return mntmCopy;
    }

    public JMenuItem editCut() {
	mntmCut = new JMenuItem("Cut");
	mntmCut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
	mntmCut.addActionListener(this);
	return mntmCut;
    }

    public JMenuItem editPaste() {
	mntmPaste = new JMenuItem("Paste");
	mntmPaste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
	mntmPaste.addActionListener(this);
	return mntmPaste;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Object event = e.getSource();

	if (event == mntmUndo) {
	    actionEditUndo();
	} else if (event == mntmRedo) {
	    actionEditRedo();
	} else if (event == mntmCopy) {
	    actionEditCopy();
	} else if (event == mntmCut) {
	    actionEditCut();
	} else if (event == mntmPaste) {
	    actionEditPaste();
	}
    }

    private void actionEditUndo() {
	// TODO Auto-generated method stub

    }

    private void actionEditRedo() {
	// TODO Auto-generated method stub

    }

    private void actionEditCopy() {
	// TODO Auto-generated method stub

    }

    private void actionEditCut() {
	// TODO Auto-generated method stub

    }

    private void actionEditPaste() {
	// TODO Auto-generated method stub

    }
}
