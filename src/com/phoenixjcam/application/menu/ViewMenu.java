package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import com.phoenixjcam.application.Paint;

public class ViewMenu implements ActionListener
{
	private Paint paint;
	private JMenu mnView;

	public ViewMenu()
	{
		// TODO Auto-generated constructor stub
	}

	public ViewMenu(Paint paint)
	{
		this.paint = paint;
	}

	public JMenu viewItems()
	{
		mnView = new JMenu("View");
		return mnView;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
