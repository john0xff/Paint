package com.phoenixjcam.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.phoenixjcam.application.drawer.Drawer;
import com.phoenixjcam.application.drawer.Shape;
import com.phoenixjcam.application.drawer.State;
import com.phoenixjcam.application.menu.BarMenu;
import com.phoenixjcam.application.status.StatusBar;
import com.phoenixjcam.application.tools.ToolBar;

public final class Paint extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Drawer drawer;
	private StatusBar statusBar;

	private JPanel backgroundPanel;

	private String filePath;
	private String fileName;

	public Paint()
	{
		setFilePath("");
		setFileName("Untitled");
		setTitle(getFileName() + " - Paint");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int width = 900;
		int height = 600;
		setSize(width, height);

		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		setLocation((centerPoint.x) - (width / 2), (centerPoint.y) - (height / 2));

		initComponents();
		add(initPanel());

		setVisible(true);
	}

	private void initComponents()
	{
		JMenuBar mnBar = new BarMenu(this).getBarMenu();
		setJMenuBar(mnBar);

		JToolBar toolBar = new ToolBar(this).getToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		statusBar = new StatusBar();
		add(statusBar, BorderLayout.PAGE_END);
	}

	private JPanel initPanel()
	{
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(null);
		drawer = new Drawer(new Dimension(300, 200), Shape.BRUSH, State.PAINTING, 2, backgroundPanel, this);
		backgroundPanel.add(drawer);
		backgroundPanel.setBackground(Color.GRAY);

		setDrawer(drawer);

		return backgroundPanel;
	}

	public Drawer getDrawer()
	{
		return drawer;
	}

	public void setDrawer(Drawer drawer)
	{
		this.drawer = drawer;
	}

	public StatusBar getStatusBar()
	{
		return statusBar;
	}

	public void setStatusBar(StatusBar statusBar)
	{
		this.statusBar = statusBar;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Paint();
			}
		});
	}
}
