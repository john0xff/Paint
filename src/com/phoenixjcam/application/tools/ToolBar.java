package com.phoenixjcam.application.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.phoenixjcam.application.Paint;
import com.phoenixjcam.application.drawer.Shape;
import com.phoenixjcam.application.drawer.State;

public class ToolBar implements ActionListener {
    private JToolBar toolBar;
    private Paint paint;

    private JButton btnNew;
    private JButton btnOpen;
    private JButton btnSave;
    private JButton btnPrint;
    private JButton btnCut;
    private JButton btnCopy;
    private JButton btnPaste;
    private JButton btnUndo;
    private JButton btnRedo;
    private JButton btnZoomOut;
    private JButton btnZoomIn;
    private JButton btnRuler;
    private JButton btnStrokeDecrease;
    private JButton btnStrokeIncrease;
    private JButton btnColor;
    private Separator separator;
    private JComboBox<String> cbZoom;
    private JComboBox<String> cbTools;
    private JComboBox<String> cbStrokeWidth;
    private JDialog colorDialog;
    private JColorChooser colorChooser;

    public ToolBar(Paint paint) {
	this.paint = paint;
	colorDialog = new JDialog(paint, "Color Chooser", false);
	colorChooser = new JColorChooser(Color.black);
	colorDialog.add(colorChooser);

	toolBar = new JToolBar();
	toolBar.setFloatable(false);
	toolBar.setRollover(true);

	toolBar.add(createSeparator());
	toolBar.add(newImage());
	toolBar.add(openImage());
	toolBar.add(saveImage());
	toolBar.add(printImage());
	toolBar.add(createSeparator());
	toolBar.add(cutImage());
	toolBar.add(copyImage());
	toolBar.add(pasteImage());
	toolBar.add(createSeparator());
	toolBar.add(undoImage());
	toolBar.add(redoImage());
	toolBar.add(createSeparator());
	toolBar.add(colors());
	toolBar.add(createSeparator());
	toolBar.add(cbTools());
	toolBar.add(createSeparator());
	toolBar.add(strokeMinus());
	toolBar.add(cbStrokeWidth());
	toolBar.add(strokeAdd());
	toolBar.add(createSeparator());
	toolBar.add(zoomOut());
	toolBar.add(cbZoom());
	toolBar.add(zoomIn());
	toolBar.add(createSeparator());
	toolBar.add(rulers());
	toolBar.add(createSeparator());
    }

    public JToolBar getToolBar() {
	return toolBar;
    }

    private JComboBox<String> cbZoom() {
	cbZoom = new JComboBox<String>();
	cbZoom.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "20 %", "50 %", "100 %", "200 %" }));
	cbZoom.setMaximumSize(new java.awt.Dimension(80, 25));
	cbZoom.setPreferredSize(new java.awt.Dimension(40, 25));
	cbZoom.setSelectedIndex(2);
	cbZoom.addActionListener(this);
	return cbZoom;
    }

    private JComboBox<String> cbTools() {
	cbTools = new JComboBox<String>();

	cbTools.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "Brush", "Pencil", "Line", "Rectangle",
			"Rounded Rectangle", "Oval", "Eraser", "Paint Bucket" }));
	cbTools.setMaximumSize(new java.awt.Dimension(140, 25));
	cbTools.addActionListener(this);
	return cbTools;
    }

    private JComboBox<String> cbStrokeWidth() {
	cbStrokeWidth = new JComboBox<String>();
	cbStrokeWidth.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "1", "2", "5", "8", "10", "12", "15", "20",
			"25", "30", "150", "300" }));
	cbStrokeWidth.setMaximumSize(new java.awt.Dimension(80, 25));
	cbStrokeWidth.setSelectedIndex(1);
	cbStrokeWidth.addActionListener(this);
	return cbStrokeWidth;
    }

    private JButton newImage() {
	btnNew = new JButton();
	btnNew.setIcon(new ImageIcon(getClass()
		.getResource("/icon/newFile.png")));
	btnNew.addActionListener(this);
	return btnNew;
    }

    private JButton openImage() {
	btnOpen = new JButton();
	btnOpen.setIcon(new ImageIcon(getClass().getResource(
		"/icon/openFile.png")));
	btnOpen.addActionListener(this);
	return btnOpen;
    }

    private JButton saveImage() {
	btnSave = new JButton();
	btnSave.setIcon(new ImageIcon(getClass().getResource("/icon/save.png")));
	btnSave.addActionListener(this);
	return btnSave;
    }

    private JButton printImage() {
	btnPrint = new JButton();
	btnPrint.setIcon(new ImageIcon(getClass().getResource(
		"/icon/printer.png")));
	btnPrint.addActionListener(this);
	return btnPrint;
    }

    private JButton cutImage() {
	btnCut = new JButton();
	btnCut.setIcon(new ImageIcon(getClass().getResource(
		"/icon/scissors.png")));
	btnCut.addActionListener(this);
	return btnCut;
    }

    private JButton copyImage() {
	btnCopy = new JButton();
	btnCopy.setIcon(new ImageIcon(getClass().getResource("/icon/copy.png")));
	btnCopy.addActionListener(this);
	return btnCopy;
    }

    private JButton pasteImage() {
	btnPaste = new JButton();
	btnPaste.setIcon(new ImageIcon(getClass()
		.getResource("/icon/paste.png")));
	btnPaste.addActionListener(this);
	return btnPaste;
    }

    private JButton undoImage() {
	btnUndo = new JButton();
	btnUndo.setIcon(new ImageIcon(getClass().getResource("/icon/undo.png")));
	btnUndo.addActionListener(this);
	return btnUndo;
    }

    private JButton redoImage() {
	btnRedo = new JButton();
	btnRedo.setIcon(new ImageIcon(getClass().getResource("/icon/redo.png")));
	btnRedo.addActionListener(this);
	return btnRedo;
    }

    private JButton zoomOut() {
	btnZoomOut = new JButton();
	btnZoomOut.setIcon(new ImageIcon(getClass().getResource(
		"/icon/zoomOut.png")));
	btnZoomOut.addActionListener(this);
	return btnZoomOut;
    }

    private JButton zoomIn() {
	btnZoomIn = new JButton();
	btnZoomIn.setIcon(new ImageIcon(getClass().getResource(
		"/icon/zoomIn.png")));
	btnZoomIn.addActionListener(this);
	return btnZoomIn;
    }

    private JButton rulers() {
	btnRuler = new JButton();
	btnRuler.setIcon(new ImageIcon(getClass().getResource(
		"/icon/rulers.png")));
	btnRuler.addActionListener(this);
	return btnRuler;
    }

    private JButton strokeMinus() {
	btnStrokeDecrease = new JButton();
	btnStrokeDecrease.setIcon(new ImageIcon(getClass().getResource(
		"/icon/minus.png")));
	btnStrokeDecrease.addActionListener(this);
	return btnStrokeDecrease;
    }

    private JButton strokeAdd() {
	btnStrokeIncrease = new JButton();
	btnStrokeIncrease.setIcon(new ImageIcon(getClass().getResource(
		"/icon/add.png")));
	btnStrokeIncrease.addActionListener(this);
	return btnStrokeIncrease;
    }

    private JButton colors() {
	btnColor = new JButton();
	btnColor.setIcon(new ImageIcon(getClass().getResource(
		"/color/palete.png")));
	btnColor.addActionListener(this);
	return btnColor;
    }

    private Separator createSeparator() {
	separator = new Separator();
	return separator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Object event = e.getSource();

	if (event == btnNew) {
	    actionNew();
	} else if (event == btnOpen) {
	    actionOpen();
	} else if (event == btnSave) {
	    actionSave();
	} else if (event == btnPrint) {
	    actionPrint();
	} else if (event == btnCut) {
	    actionCut();
	} else if (event == btnCut) {
	    actionCut();
	} else if (event == btnCopy) {
	    actionCopy();
	} else if (event == btnPaste) {
	    actionPaste();
	} else if (event == btnUndo) {
	    actionUndo();
	} else if (event == btnRedo) {
	    actionRedo();
	} else if (event == btnColor) {
	    actionColorPicker();
	} else if (event == cbTools) {
	    actionPickTools(e);
	} else if (event == btnStrokeDecrease) {
	    actionStrokeDecrease();
	} else if (event == cbStrokeWidth) {
	    actionStrokeWidth(e);
	} else if (event == btnStrokeIncrease) {
	    actionStrokeIncrease();
	} else if (event == btnZoomOut) {
	    actionZoomOut();
	} else if (event == cbZoom) {
	    actionZoom(e);
	} else if (event == btnZoomIn) {
	    actionZoomIn();
	} else if (event == btnRuler) {
	    actionRuler();
	}
    }

    private void actionNew() {
	paint.getDrawer().setSize(400, 100);
	paint.getDrawer().clearBufferImage();
    }

    private void actionOpen() {
	// TODO Auto-generated method stub

    }

    private void actionSave() {
	// TODO Auto-generated method stub

    }

    private void actionPrint() {
	// TODO Auto-generated method stub

    }

    private void actionCut() {
	// TODO Auto-generated method stub

    }

    private void actionCopy() {
	// TODO Auto-generated method stub

    }

    private void actionPaste() {
	// TODO Auto-generated method stub

    }

    private void actionUndo() {
	// TODO Auto-generated method stub

    }

    private void actionRedo() {
	// TODO Auto-generated method stub

    }

    private void actionColorPicker() {
	colorDialog.setVisible(true);
	colorDialog.setSize(620, 350);
	colorChooser.getSelectionModel().addChangeListener(
		new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
			Color newColor = colorChooser.getColor();
			if (newColor != null) {
			    paint.getDrawer().setColor(newColor);
			}
		    }
		});

    }

    private void actionPickTools(ActionEvent e) {
	JComboBox<?> cb = (JComboBox<?>) e.getSource();
	String string = (String) cb.getSelectedItem();
	switch (string) {
	case "Pencil":
	    paint.getDrawer().setShape(Shape.PENCIL, State.PAINTING);
	    break;
	case "Brush":
	    paint.getDrawer().setShape(Shape.BRUSH, State.PAINTING);
	    break;
	case "Line":
	    paint.getDrawer().setShape(Shape.LINE, State.DRAGGING);
	    break;
	case "Rectangle":
	    paint.getDrawer().setShape(Shape.RECTANGLE, State.DRAGGING);
	    break;
	case "Rounded Rectangle":
	    paint.getDrawer().setShape(Shape.ROUNDEDRECT, State.DRAGGING);
	    break;
	case "Oval":
	    paint.getDrawer().setShape(Shape.OVAL, State.DRAGGING);
	    break;
	case "Eraser":
	    paint.getDrawer().setShape(Shape.ERASER, State.PAINTING);
	    break;
	case "Paint Bucket":
	    // System.out.println("Paint Bucket");
	    // canvas.setShape(Shape.PAINTBUCKET, State.PAINTING);
	    break;
	}
    }

    private void actionStrokeDecrease() {
	int selectedItem = cbStrokeWidth.getSelectedIndex();

	if (selectedItem > 0) {
	    selectedItem--;
	    cbStrokeWidth.setSelectedIndex(selectedItem);
	}
    }

    private void actionStrokeWidth(ActionEvent e) {
	String stringStrokeWidth = (String) ((JComboBox<?>) cbStrokeWidth)
		.getSelectedItem();
	int integerStrokeWidth = Integer.parseInt(stringStrokeWidth);
	paint.getDrawer().setStrokeWidth(integerStrokeWidth);
    }

    private void actionStrokeIncrease() {
	int selectedItem = cbStrokeWidth.getSelectedIndex();
	int maxBrushSize = cbStrokeWidth.getModel().getSize();

	if (selectedItem < maxBrushSize - 1) {
	    selectedItem++;
	    cbStrokeWidth.setSelectedIndex(selectedItem);
	}
    }

    private void actionZoomOut() {
	// TODO Auto-generated method stub

    }

    private void actionZoom(ActionEvent e) {

    }

    private void actionZoomIn() {
	// TODO Auto-generated method stub

    }

    private void actionRuler() {
	// TODO Auto-generated method stub

    }
}
