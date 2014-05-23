package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StyleMenu implements ActionListener
{
	private JFrame frame;
	private JMenu mnStyleUI;
	private ButtonGroup btngStyle;
	private JCheckBoxMenuItem winStyle;
	private JCheckBoxMenuItem metalStyle;
	private JCheckBoxMenuItem nimbusStyle;

	public StyleMenu()
	{
		btngStyle = new ButtonGroup();
		actionWindowsOption();
	}

	public StyleMenu(JFrame frame)
	{
		this.frame = frame;
		btngStyle = new ButtonGroup();
		actionWindowsOption();
	}

	public JMenu styleItems()
	{
		mnStyleUI = new JMenu("Style");
		mnStyleUI.add(windowsOption());
		mnStyleUI.add(metalOption());
		mnStyleUI.add(nimbusOption());

		return mnStyleUI;
	}

	public JCheckBoxMenuItem windowsOption()
	{
		winStyle = new JCheckBoxMenuItem("Windows Look");
		winStyle.addActionListener(this);
		btngStyle.add(winStyle);
		winStyle.setSelected(true);
		return winStyle;
	}

	public JCheckBoxMenuItem metalOption()
	{
		metalStyle = new JCheckBoxMenuItem("Metal Look");
		metalStyle.addActionListener(this);
		btngStyle.add(metalStyle);
		return metalStyle;
	}

	public JCheckBoxMenuItem nimbusOption()
	{
		nimbusStyle = new JCheckBoxMenuItem("Nimbus Look");
		nimbusStyle.addActionListener(this);
		btngStyle.add(nimbusStyle);
		return nimbusStyle;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object event = e.getSource();

		if (event == winStyle)
		{
			actionWindowsOption();
		}
		else if (event == metalStyle)
		{
			actionMetalOption();
		}
		else if (event == nimbusStyle)
		{
			actionNimbusOption();
		}
	}

	public void actionWindowsOption()
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}

	public void actionMetalOption()
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}

	public void actionNimbusOption()
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}
}
