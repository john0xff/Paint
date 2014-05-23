package com.phoenixjcam.application.menu;

import javax.swing.JMenuBar;

import com.phoenixjcam.application.Paint;

public class BarMenu
{
	private JMenuBar mnBar;

	private FileMenu mnFile;
	private EditMenu mnEdit;
	private ViewMenu mnView;
	private ImageMenu mnImage;
	private WindowMenu mnWindow;
	private StyleMenu mnStyle;
	private HelpMenu mnHelp;

	public BarMenu(Paint paint)
	{
		mnFile = new FileMenu(paint);
		mnEdit = new EditMenu(paint);
		mnView = new ViewMenu(paint);
		mnImage = new ImageMenu(paint);
		mnWindow = new WindowMenu(paint);
		mnStyle = new StyleMenu(paint);
		mnHelp = new HelpMenu(paint);

		mnBar = new JMenuBar();
		mnBar.add(mnFile.fileItems());
		mnBar.add(mnEdit.editItems());
		mnBar.add(mnView.viewItems());
		mnBar.add(mnImage.imageItems());
		mnBar.add(mnWindow.windowItems());
		mnBar.add(mnStyle.styleItems());
		mnBar.add(mnHelp.helpItems());
	}

	public JMenuBar getBarMenu()
	{
		return mnBar;
	}
}
