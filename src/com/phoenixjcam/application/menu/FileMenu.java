package com.phoenixjcam.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.phoenixjcam.application.Paint;
import com.phoenixjcam.application.drawer.Drawer;

public class FileMenu implements ActionListener
{
	private Paint paint;
	private JMenu mnFile;
	private JMenuItem mntmNew;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenuItem mntmPrint;
	private JMenuItem mntmExit;

	public FileMenu()
	{
	}

	public FileMenu(Paint paint)
	{
		this.paint = paint;
	}

	public JMenu fileItems()
	{
		mnFile = new JMenu("File");

		mnFile.add(fileNew());
		mnFile.add(fileOpen());
		mnFile.add(fileSave());
		mnFile.add(fileSaveAs());
		mnFile.add(filePrint());
		mnFile.add(fileExit());

		return mnFile;
	}

	private JMenuItem fileNew()
	{
		mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		mntmNew.addActionListener(this);
		return mntmNew;
	}

	private JMenuItem fileOpen()
	{
		mntmOpen = new JMenuItem("Open...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		mntmOpen.addActionListener(this);
		return mntmOpen;
	}

	private JMenuItem fileSave()
	{
		mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		mntmSave.addActionListener(this);
		return mntmSave;
	}

	private JMenuItem fileSaveAs()
	{
		mntmSaveAs = new JMenuItem("Save as...");
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
		mntmSaveAs.addActionListener(this);
		return mntmSaveAs;
	}

	private JMenuItem filePrint()
	{
		mntmPrint = new JMenuItem("Print...");
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		mntmPrint.addActionListener(this);
		return mntmPrint;
	}

	private JMenuItem fileExit()
	{
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
		mntmExit.addActionListener(this);
		return mntmExit;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object event = e.getSource();

		if (event == mntmNew)
		{
			actionFileNew(e);
		}
		else if (event == mntmOpen)
		{
			actionFileOpen();
		}
		else if (event == mntmSave)
		{
			actionFileSave(e);
		}
		else if (event == mntmSaveAs)
		{
			actionFileSaveAs(e);
		}
		else if (event == mntmPrint)
		{
			actionPrint();
		}
		else if (event == mntmExit)
		{
			actionExit();
		}
	}

	private void actionFileNew(ActionEvent e)
	{
		if (paint.getDrawer().getBufferImage().equals(null))
		{
			paint.getDrawer().clearBufferImage();
		}
		else
		{
			int dialog = JOptionPane.showConfirmDialog(null, "Do you want to save changes to " + paint.getFileName());

			if (dialog == 0)
			{
				actionFileSave(e);
				paint.getDrawer().clearBufferImage();
			}
			else if (dialog == 1)
			{
				paint.getDrawer().clearBufferImage();
			}
			else if (dialog == 2)
			{
				return;
			}
		}
	}

	private void actionFileOpen()
	{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, gif, png images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal == JFileChooser.CANCEL_OPTION)
		{
			return;
		}
		else if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File imageInput = chooser.getSelectedFile();
			try
			{
				BufferedImage bufferedImage = ImageIO.read(imageInput);
				Drawer drawer = paint.getDrawer();
				drawer.setBufferImage(bufferedImage);
				drawer.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
				drawer.printDrawerSize();
				paint.setFileName(imageInput.getName());
				paint.setFilePath(imageInput.getPath());
				paint.setTitle(paint.getFileName());
				drawer.repaint();

			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * TODO save file but need to check if any changes have been made at current picture
	 * 
	 */
	private void actionFileSave(ActionEvent e)
	{
		actionFileSaveAs(e);
	}

	/**
	 * TODO make file extensions to save dialog 
	 * also to ImageIO.write(bi, "png", outputfile);
	 * @param e
	 */
	private void actionFileSaveAs(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int dialog = fileChooser.showSaveDialog(null);

		if (dialog == JFileChooser.CANCEL_OPTION)
		{
			return;
		}
		else if (dialog == JFileChooser.OPEN_DIALOG)
		{
			File myFile = fileChooser.getSelectedFile();

			if (myFile.exists())
			{
				dialog = JOptionPane.showConfirmDialog(null, "A file with same name already exists!\nAre you sure want to overwrite?");
				paint.setTitle(myFile.getName());
				if (dialog != 0)
				{
					return;
				}
			}
			try
			{
				String filePath = myFile.getPath();
				BufferedImage bi = paint.getDrawer().getBufferImage();
				File outputfile = new File(filePath);
				ImageIO.write(bi, "png", outputfile);
			}
			catch (IOException e1)
			{
				JOptionPane.showMessageDialog(null, "Failed to save the file", "Error", JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	private void actionPrint()
	{
	}

	private void actionExit()
	{
	}
}
