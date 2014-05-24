package com.phoenixjcam.application.drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.phoenixjcam.application.Paint;

public class Drawer extends JPanel implements MouseListener,
	MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private Paint paint;
    private JPanel backgroundPanel;
    private State state;
    private Shape currentShape;
    private int strokeWidth;
    private Color color;

    private Point startPoint = null;
    private Point endPoint = null;
    private BufferedImage bufferImage;
    private Dimension drawerSize;

    public Drawer(Dimension drawerSize, Shape shape, State state,
	    int strokeWidth, JPanel backgroundPanel, Paint paint) {
	setLayout(null);
	setDoubleBuffered(true);
	setLocation(10, 10);
	setBackground(Color.WHITE);
	setStrokeColor(Color.BLACK);

	this.drawerSize = drawerSize;
	setSize(drawerSize);

	this.paint = paint;
	this.backgroundPanel = backgroundPanel;
	clearBufferImage();
	bufferImage = (BufferedImage) createImage(getWidth(), getHeight());

	this.currentShape = shape;
	this.state = state;
	this.strokeWidth = strokeWidth;
	this.backgroundPanel = backgroundPanel;

	setFocusable(true);
	requestFocus();
	addMouseListener(this);
	addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
	Graphics2D g2D = (Graphics2D) g;

	if (bufferImage == null) {
	    bufferImage = (BufferedImage) createImage(getWidth(), getHeight());
	    Graphics2D gc = bufferImage.createGraphics();
	    gc.setColor(Color.WHITE);
	    gc.fillRect(0, 0, getWidth(), getHeight());
	}

	g2D.drawImage(bufferImage, null, 0, 0);

	if (startPoint != null && endPoint != null) {
	    renderShape(g2D);
	}
    }

    public void clearBufferImage() {
	bufferImage = null;
	setStartPoint(null);
	setEndPoint(null);
	printDrawerSize();
	paint.setFilePath("");
	paint.setFileName("Untitled - Paint");
	paint.setTitle(paint.getFileName());
	repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
	printCoords(e);
	startPoint = e.getPoint();
	endPoint = startPoint;

	repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	printCoords(e);
	if (state == State.PAINTING) {
	    startPoint = endPoint;
	    endPoint = e.getPoint();
	    renderShape(bufferImage.createGraphics());
	} else if (state == State.DRAGGING) {
	    endPoint = e.getPoint();
	}
	repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	endPoint = e.getPoint();

	renderShape(bufferImage.createGraphics());
	repaint();

	endPoint = null;
	startPoint = null;
    }

    // print mouse coordinate at status tool bar
    public void printCoords(MouseEvent e) {
	String posX = String.valueOf((int) e.getPoint().getX());
	String posY = String.valueOf((int) e.getPoint().getY());

	paint.getStatusBar().getLblCoords()
		.setText(posX + ",  " + posY + " px");
    }

    // print drawer panel size at status tool bar
    public void printDrawerSize() {
	String width = String.valueOf((int) this.getWidth());
	String height = String.valueOf((int) this.getHeight());

	paint.getStatusBar().getLblDrawerSize()
		.setText(width + ",  " + height + " px");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	printCoords(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void renderShape(Graphics2D g2D) {
	if ((startPoint == null) || (endPoint == null))
	    return;

	g2D.setColor(color);
	g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND,
		BasicStroke.JOIN_ROUND));

	switch (currentShape) {
	case PENCIL:
	    g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
		    BasicStroke.JOIN_ROUND));
	    g2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	    break;
	case BRUSH:
	    g2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	    break;
	case LINE:
	    g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
		    BasicStroke.JOIN_ROUND));
	    g2D.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	    break;
	case RECTANGLE:
	    g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
		    BasicStroke.JOIN_MITER));
	    drawRectangle(g2D);
	    break;
	case ROUNDEDRECT:
	    g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND,
		    BasicStroke.JOIN_ROUND));
	    drawRectangle(g2D);
	    break;
	case OVAL:
	    drawOvwal(g2D);
	    break;
	case ERASER:
	    g2D.setColor(Color.WHITE);
	    g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,
		    BasicStroke.JOIN_ROUND));
	    g2D.fillRect(startPoint.x - (strokeWidth / 2), startPoint.y
		    - (strokeWidth / 2), strokeWidth, strokeWidth);
	    break;
	case PAINTBUCKET:
	    // MainApp.paint.canvas.setBackground(color);
	    break;
	default:
	    break;
	}
    }

    public void drawOvwal(Graphics2D g2D) {
	if (((startPoint.x - endPoint.x) < 0)
		&& ((startPoint.y - endPoint.y < 0))) {
	    g2D.drawOval(startPoint.x, startPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) > 0)
		&& ((startPoint.y - endPoint.y < 0))) {
	    g2D.drawOval(endPoint.x, startPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) > 0)
		&& ((startPoint.y - endPoint.y > 0))) {
	    g2D.drawOval(endPoint.x, endPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) < 0)
		&& ((startPoint.y - endPoint.y > 0))) {
	    g2D.drawOval(startPoint.x, endPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}
    }

    public void drawRectangle(Graphics2D g2D) {
	if (((startPoint.x - endPoint.x) < 0)
		&& ((startPoint.y - endPoint.y < 0))) {
	    g2D.drawRect(startPoint.x, startPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) > 0)
		&& ((startPoint.y - endPoint.y < 0))) {
	    g2D.drawRect(endPoint.x, startPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) > 0)
		&& ((startPoint.y - endPoint.y > 0))) {
	    g2D.drawRect(endPoint.x, endPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}

	if (((startPoint.x - endPoint.x) < 0)
		&& ((startPoint.y - endPoint.y > 0))) {
	    g2D.drawRect(startPoint.x, endPoint.y,
		    Math.abs(startPoint.x - endPoint.x),
		    Math.abs(startPoint.y - endPoint.y));
	}
    }

    public void setShape(Shape shape, State state) {
	this.currentShape = shape;
	this.state = state;
    }

    public void setStrokeWidth(int strokeWidth) {
	this.strokeWidth = strokeWidth;
    }

    public void setStrokeColor(Color color) {
	this.color = color;
    }

    public void setStartPoint(Point startPoint) {
	this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
	this.endPoint = endPoint;
    }

    public void setBufferImage(BufferedImage bufferImage) {
	this.bufferImage = bufferImage;
    }

    public BufferedImage getBufferImage() {
	return bufferImage;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }
}
