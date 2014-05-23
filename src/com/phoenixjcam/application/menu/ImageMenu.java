package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import com.phoenixjcam.application.Paint;

public class ImageMenu implements ActionListener
{
	private Paint paint;
	private JMenu mnImage;

	public ImageMenu()
	{
		// TODO Auto-generated constructor stub
	}

	public ImageMenu(Paint paint)
	{
		this.paint = paint;
	}

	public JMenu imageItems()
	{
		mnImage = new JMenu("Image");
		return mnImage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
