import com.sun.java.swing.*;
import com.sun.java.swing.text.*;
import com.sun.java.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.io.*;

public class GridBagLab extends JFrame {
	static String lastFile = null;
	static GridBagLab gblframe;
	static ConstraintsPanel cp;
	static TextArea helpTextArea = 
					new TextArea(contentsOfFile("intro.txt"));

	final GridBagLayout gbl = new GridBagLayout();

	ButtonGroup group = new ButtonGroup();

	JToggleButton selected = null;

	JToggleButton oneButton = new JToggleButton(), 
			twoButton = new JToggleButton(),
			threeButton = new JToggleButton(),  
			fourButton = new JToggleButton(),
			fiveButton = new JToggleButton(),
			sixButton = new JToggleButton(),
			sevenButton = new JToggleButton(),
			eightButton = new JToggleButton(),
			nineButton = new JToggleButton(),
			tenButton = new JToggleButton();

	public GridBagLab() {
		super("GridBag Lab");

		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem aboutItem = new JMenuItem("about ...");
		JMenuItem quitItem = new JMenuItem("quit");
		final AboutDialog aboutDialog = new AboutDialog(this);

		fileMenu.add(aboutItem);
		fileMenu.add(quitItem);

		mb.add(fileMenu);
		setJMenuBar(mb);

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point loc = getLocation();

				aboutDialog.pack();
				aboutDialog.setLocation(loc.x + 100, loc.y + 100);
				aboutDialog.setVisible(true);
			}
		});
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		GridBagConstraints gbc = new GridBagConstraints();

		ButtonListener listener = new ButtonListener();

		gblframe = this;

		oneButton.setIcon(new ImageIcon("gifs/one.gif"));
		oneButton.addActionListener(listener);

		twoButton.setIcon(new ImageIcon("gifs/two.gif"));
		twoButton.addActionListener(listener);

		threeButton.setIcon(new ImageIcon("gifs/three.gif"));
		threeButton.addActionListener(listener);

		fourButton.setIcon(new ImageIcon("gifs/four.gif"));
		fourButton.addActionListener(listener);

		fiveButton.setIcon(new ImageIcon("gifs/five.gif"));
		fiveButton.addActionListener(listener);

		sixButton.setIcon(new ImageIcon("gifs/six.gif"));
		sixButton.addActionListener(listener);

		sevenButton.setIcon(new ImageIcon("gifs/seven.gif"));
		sevenButton.addActionListener(listener);

		eightButton.setIcon(new ImageIcon("gifs/eight.gif"));
		eightButton.addActionListener(listener);

		nineButton.setIcon(new ImageIcon("gifs/nine.gif"));
		nineButton.addActionListener(listener);

		tenButton.setIcon(new ImageIcon("gifs/ten.gif"));
		tenButton.addActionListener(listener);

		oneButton.setFocusPainted(false);
		twoButton.setFocusPainted(false);
		threeButton.setFocusPainted(false);
		fourButton.setFocusPainted(false);
		fiveButton.setFocusPainted(false);
		sixButton.setFocusPainted(false);
		sevenButton.setFocusPainted(false);
		eightButton.setFocusPainted(false);
		nineButton.setFocusPainted(false);
		tenButton.setFocusPainted(false);
		
		group.add(oneButton);
		group.add(twoButton);
		group.add(threeButton);
		group.add(fourButton);
		group.add(fiveButton);
		group.add(sixButton);
		group.add(sevenButton);
		group.add(eightButton);
		group.add(nineButton);
		group.add(tenButton);

		Container contentPane = getContentPane();

		JPanel buttonsPanel = new JPanel();

		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonsPanel, "Center");

		buttonsPanel.setLayout(gbl);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;

		buttonsPanel.add(oneButton, gbc);

		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = 0;
		buttonsPanel.add(twoButton, gbc);
		buttonsPanel.add(threeButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		buttonsPanel.add(fourButton, gbc);

		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = 1;
		buttonsPanel.add(fiveButton, gbc);
		buttonsPanel.add(sixButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		buttonsPanel.add(sevenButton, gbc);

		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = 2;
		buttonsPanel.add(eightButton, gbc);
		buttonsPanel.add(nineButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		buttonsPanel.add(tenButton, gbc);
	}
	public static void main(String args[]) {
		final GridBagLab   buttonsFrame = new GridBagLab();
		final JFrame constraintsFrame = new JFrame("Constraints");
		final JFrame helpFrame = 
					new JFrame("GridBag Constraints Explained");

		Container contentPane = constraintsFrame.getContentPane();
		contentPane.add(cp = new ConstraintsPanel(buttonsFrame));
		constraintsFrame.pack();

		Dimension constraintsSize = constraintsFrame.getSize();

		buttonsFrame.setBounds(300,100,500,constraintsSize.height);
		buttonsFrame.setVisible(true);

		Point buttonsLocation = buttonsFrame.getLocation();
		Dimension buttonsSize = buttonsFrame.getSize();

		constraintsFrame.setLocation(
			buttonsLocation.x + buttonsSize.width,
			buttonsLocation.y);

		constraintsFrame.setVisible(true);

		Point loc = buttonsFrame.getLocation();
		Dimension size = buttonsFrame.getSize();
		helpFrame.setBounds(loc.x, loc.y + size.height, 
							size.width + 
							constraintsFrame.getSize().width, 300);

		helpTextArea.setEditable(false);
		helpTextArea.setFont(
			new Font("Times-Roman", Font.PLAIN, 12));
		helpFrame.getContentPane().add(helpTextArea, "Center");
		helpFrame.setVisible(true);

		buttonsFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				buttonsFrame.dispose();
				constraintsFrame.dispose();
				helpFrame.dispose();
			}
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public void setHelpViewer(String filename) {
		if(lastFile == null || !lastFile.equals(filename)) {
			helpTextArea.setText(contentsOfFile(filename));
			lastFile = filename;
		}
	}
	public void setConstraints(GridBagConstraints gbc) {
		if(selected != null) {
			gbl.setConstraints(selected, gbc);
			selected.invalidate();
			validate();
		}
	}
	public GridBagConstraints getConstraints() {
		GridBagConstraints gbc = null;

		if(selected != null) 
			gbc = gbl.getConstraints(selected);

		return gbc;
	}
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selected = (JToggleButton)e.getSource();

			cp.setConstraints(gbl.getConstraints(selected));
		}
	}
	static String contentsOfFile(String filename) {
		String s = new String();
		File f;
		char[] buff = new char[50000];
		InputStream is;
		InputStreamReader reader;
		URL url;

		try {
			f = new File(filename);
			reader = new FileReader(f);

			int nch;

			while ((
				nch = reader.read(buff, 0, buff.length)) != -1) {
				s = s + new String(buff, 0, nch);
			}
		} 
		catch (java.io.IOException ex) {
			s = "Could not load file: " + filename;
		}
		return s;
	}
}
class ConstraintsDialog extends JFrame {
	public ConstraintsDialog(GridBagLab test) {
		super("GridBagConstraints");
		getContentPane().add(new ConstraintsPanel(test));
	}
}
class ConstraintsPanel extends JPanel {
	GridBagLab					buttonsFrame = null;
	AnchorFillWeightPanel 	afpanel = new AnchorFillWeightPanel();
	DisplayAreaPanel 		dpanel = new DisplayAreaPanel();
	PaddingPanel 			ppanel = new PaddingPanel();
	InsetsPanel				ipanel = new InsetsPanel();
	String 					dpaneltip = "display area attributes",
							afpaneltip = "component attributes",
							ppaneltip = "padding";

	public ConstraintsPanel(GridBagLab frame) {
		this.buttonsFrame = frame;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(dpanel);
		add(Box.createVerticalStrut(15));
		add(afpanel);
		add(Box.createVerticalStrut(15));
		add(ppanel);
		add(Box.createVerticalStrut(15));
		add(ipanel);
		add(Box.createVerticalStrut(15));
	}
	public void setConstraints(GridBagConstraints gbc) {
		afpanel.setAnchor(gbc.anchor);
		afpanel.setFill(gbc.fill);
		afpanel.setWeightx(new Double(gbc.weightx));
		afpanel.setWeighty(new Double(gbc.weighty));

		dpanel.setGridx(gbc.gridx);
		dpanel.setGridy(gbc.gridy);
		dpanel.setGridwidth(gbc.gridwidth);
		dpanel.setGridheight(gbc.gridheight);

		ppanel.setPadx(gbc.ipadx);
		ppanel.setPady(gbc.ipady);

		ipanel.setInsetsConstraints(gbc.insets);
	}
	public GridBagConstraints getConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = afpanel.getAnchor();
		gbc.fill = afpanel.getFill();

		gbc.gridx = dpanel.getGridx();
		gbc.gridy = dpanel.getGridy();
		gbc.gridwidth = dpanel.getGridwidth();
		gbc.gridheight = dpanel.getGridheight();

		gbc.weightx = (afpanel.getWeightx()).doubleValue();
		gbc.weighty = (afpanel.getWeighty()).doubleValue();

		gbc.ipadx = ppanel.getPadx();
		gbc.ipady = ppanel.getPady();

		gbc.insets = ipanel.getInsetsConstraint();

		return gbc;
	}
}
class PaddingPanel extends JPanel {
	JLabel 	ipadxLabel = new JLabel("ipadx:"),
			ipadyLabel = new JLabel("ipady:");

	JTextField 	ipadxField = new JTextField(3),
				ipadyField = new JTextField(3);

	int padX, padY;

	public PaddingPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(gbl);
		gbc.anchor = GridBagConstraints.NORTHWEST;

		add(ipadxLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		add(ipadxField, gbc);
		add(Box.createHorizontalStrut(20), gbc);
		add(ipadyLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		add(ipadyField, gbc);

		setBorder(new CompoundBorder(
			BorderFactory.createTitledBorder("Internal Padding"),
			BorderFactory.createEmptyBorder(10,10,10,10)));

		ipadxField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GridBagConstraints tgbc = 
					GridBagLab.gblframe.getConstraints();

				tgbc.ipadx = getPadx();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});
		ipadyField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GridBagConstraints tgbc = 
					GridBagLab.gblframe.getConstraints();

				tgbc.ipady = getPady();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});

		class IpadListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("ipad.txt");
			}
		}
		IpadListener ipadListener = new IpadListener();

		ipadxLabel.addMouseListener(ipadListener);
		ipadxField.addMouseListener(ipadListener);
		ipadyLabel.addMouseListener(ipadListener);
		ipadyField.addMouseListener(ipadListener);
	}
	public void setPadx(int padX) {
		ipadxField.setText(Integer.toString(padX));
		repaint();
	}
	public void setPady(int padY) {
		ipadyField.setText(Integer.toString(padY));
		repaint();
	}
	public int getPadx() {
		return Integer.parseInt(ipadxField.getText());
	}
	public int getPady() {
		return Integer.parseInt(ipadyField.getText());
	}
}
class InsetsPanel extends JPanel {
	JTextField 	topField 	= new JTextField(3),
				leftField 	= new JTextField(3),
				bottomField = new JTextField(3),
				rightField 	= new JTextField(3);

	public InsetsPanel() {
		setLayout(new BorderLayout());
		add(topField, "North");
		add(leftField, "West");
		add(bottomField, "South");
		add(rightField, "East");

		topField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateInsets();
			}
		});
		leftField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateInsets();
			}
		});
		bottomField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateInsets();
			}
		});
		rightField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateInsets();
			}
		});
		setBorder(new CompoundBorder(
			BorderFactory.createTitledBorder("Insets"),
			BorderFactory.createEmptyBorder(10,10,10,10)));

		addMouseListener(new MouseAdapter() {	
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("insets.txt");
			}
		});
	}
	public void setInsetsConstraints(Insets insets) {
		topField.setText(Integer.toString(insets.top));
		leftField.setText(Integer.toString(insets.left));
		bottomField.setText(Integer.toString(insets.bottom));
		rightField.setText(Integer.toString(insets.right));
	}
	public Insets getInsetsConstraint() {
		return new Insets(
			Integer.parseInt(topField.getText()),
			Integer.parseInt(leftField.getText()),
			Integer.parseInt(bottomField.getText()),
			Integer.parseInt(rightField.getText()));
	}
	void updateInsets() {
		GridBagConstraints tgbc =
					GridBagLab.gblframe.getConstraints();

		tgbc.insets = getInsetsConstraint();
		GridBagLab.gblframe.setConstraints(tgbc);
	}
}

class DisplayAreaPanel extends JPanel {
	JLabel	gridxLabel = new JLabel("gridx:"),
			gridyLabel = new JLabel("gridy:"),
			gridwidthLabel = new JLabel("gridwidth:"),
			gridheightLabel = new JLabel("gridheight:");

	JComboBox 	gridxCombo 		= new JComboBox(),
				gridyCombo 		= new JComboBox(),
				gridwidthCombo 	= new JComboBox(),
				gridheightCombo = new JComboBox();

	private void addToolTips() {
		gridxLabel.setToolTipText("grid x"); 
		gridyLabel.setToolTipText("grid y");
		gridwidthLabel.setToolTipText("width in grid cells");
		gridheightLabel.setToolTipText("height in grid cells");

		gridxCombo.setToolTipText("integer value");
		gridyCombo.setToolTipText("integer value");
		gridwidthCombo.setToolTipText("integer value");
		gridheightCombo.setToolTipText("integer value");
	}
	public DisplayAreaPanel() {
		GridBagLayout 		gbl = new GridBagLayout();
		GridBagConstraints 	gbc = new GridBagConstraints();

		setLayout(gbl);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		add(gridxLabel, gbc);
		add(Box.createHorizontalStrut(7), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(gridxCombo, gbc);

		gbc.gridwidth = 1;

		add(gridyLabel, gbc);
		add(Box.createHorizontalStrut(7), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(gridyCombo, gbc);
		add(Box.createVerticalStrut(10), gbc);

		gbc.gridwidth = 1;

		add(gridwidthLabel, gbc);
		add(Box.createHorizontalStrut(7), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(gridwidthCombo, gbc);

		gbc.gridwidth = 1;

		add(gridheightLabel, gbc);
		add(Box.createHorizontalStrut(7), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(gridheightCombo, gbc);

		addToolTips();

		setBorder(new CompoundBorder(
			BorderFactory.createTitledBorder("Display Area"),
			BorderFactory.createEmptyBorder(10,10,10,10)));

		gridxCombo.addItem("RELATIVE");
		gridxCombo.addItem("0");
		gridxCombo.addItem("1");
		gridxCombo.addItem("2");
		gridxCombo.addItem("3");
		gridxCombo.addItem("4");
		gridxCombo.addItem("5");
		gridxCombo.addItem("6");
		gridxCombo.addItem("7");
		gridxCombo.addItem("8");
		gridxCombo.addItem("9");
		gridxCombo.addItem("10");

		gridyCombo.addItem("RELATIVE");
		gridyCombo.addItem("0");
		gridyCombo.addItem("1");
		gridyCombo.addItem("2");
		gridyCombo.addItem("3");
		gridyCombo.addItem("4");
		gridyCombo.addItem("5");
		gridyCombo.addItem("6");
		gridyCombo.addItem("7");
		gridyCombo.addItem("8");
		gridyCombo.addItem("9");
		gridyCombo.addItem("10");

		gridwidthCombo.addItem("RELATIVE");
		gridwidthCombo.addItem("REMAINDER");
		gridwidthCombo.addItem("1");
		gridwidthCombo.addItem("2");
		gridwidthCombo.addItem("3");
		gridwidthCombo.addItem("4");
		gridwidthCombo.addItem("5");
		gridwidthCombo.addItem("6");
		gridwidthCombo.addItem("7");
		gridwidthCombo.addItem("8");
		gridwidthCombo.addItem("9");
		gridwidthCombo.addItem("10");

		gridheightCombo.addItem("RELATIVE");
		gridheightCombo.addItem("REMAINDER");
		gridheightCombo.addItem("1");
		gridheightCombo.addItem("2");
		gridheightCombo.addItem("3");
		gridheightCombo.addItem("4");
		gridheightCombo.addItem("5");
		gridheightCombo.addItem("6");
		gridheightCombo.addItem("7");
		gridheightCombo.addItem("8");
		gridheightCombo.addItem("9");
		gridheightCombo.addItem("10");

		gridxCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

				tgbc.gridx = getGridx();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});
		gridyCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

				tgbc.gridy = getGridy();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});
		gridwidthCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

				tgbc.gridwidth = getGridwidth();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});
		gridheightCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

				tgbc.gridheight = getGridheight();
				GridBagLab.gblframe.setConstraints(tgbc);
			}
		});

		class GridxyListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("gridxy.txt");
			}
		}
		class GridwhListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("gridwh.txt");
			}
		}

		GridxyListener gridxyListener = new GridxyListener();
		GridwhListener gridwhListener = new GridwhListener();

		gridxLabel.addMouseListener(gridxyListener);
		gridxCombo.addMouseListener(gridxyListener);

		gridyLabel.addMouseListener(gridxyListener);
		gridyCombo.addMouseListener(gridxyListener);

		gridwidthLabel.addMouseListener(gridwhListener);
		gridwidthCombo.addMouseListener(gridwhListener);

		gridheightLabel.addMouseListener(gridwhListener);
		gridheightCombo.addMouseListener(gridwhListener);
	}
	public void setGridx(int gridx) {
		if(gridx == GridBagConstraints.RELATIVE)
			gridxCombo.setSelectedItem("RELATIVE");
		else
			gridxCombo.setSelectedIndex(gridx+1);
		repaint();
	}
	public void setGridy(int gridy) {
		if(gridy == GridBagConstraints.RELATIVE)
			gridyCombo.setSelectedItem("RELATIVE");
		else
			gridyCombo.setSelectedIndex(gridy+1);
		repaint();
	}
	public void setGridheight(int gridheight) {
		if(gridheight == GridBagConstraints.RELATIVE)
			gridheightCombo.setSelectedItem("RELATIVE");
		else if(gridheight == GridBagConstraints.REMAINDER)
			gridheightCombo.setSelectedItem("REMAINDER");
		else	
			gridheightCombo.setSelectedIndex(gridheight+1);
	}
	public void setGridwidth(int gridwidth) {
		if(gridwidth == GridBagConstraints.RELATIVE)
			gridwidthCombo.setSelectedItem("RELATIVE");
		else if(gridwidth == GridBagConstraints.REMAINDER)
			gridwidthCombo.setSelectedItem("REMAINDER");
		else	
			gridwidthCombo.setSelectedIndex(gridwidth+1);
	}
	public int getGridx() {
		String x = (String)gridxCombo.getSelectedItem();
		int rv;

		if(x.equals("RELATIVE"))
			rv = GridBagConstraints.RELATIVE;
		else {
			rv = Integer.parseInt(
					(String)gridxCombo.getSelectedItem());
		}
		return rv;
	}
	public int getGridy() {
		String y = (String)gridyCombo.getSelectedItem();
		int rv;

		if(y.equals("RELATIVE"))
			rv = GridBagConstraints.RELATIVE;
		else {
			rv = Integer.parseInt(
					(String)gridyCombo.getSelectedItem());
		}
		return rv;
	}
	public int getGridwidth() {
		String width = (String)gridwidthCombo.getSelectedItem();
		int rv;

		if(width.equals("RELATIVE"))
			rv = GridBagConstraints.RELATIVE;
		else if(width.equals("REMAINDER"))
			rv = GridBagConstraints.REMAINDER;
		else {
			rv = Integer.parseInt(
					(String)gridwidthCombo.getSelectedItem());
		}
		return rv;
	}
	public int getGridheight() {
		String height = (String)gridheightCombo.getSelectedItem();
		int rv;

		if(height.equals("RELATIVE"))
			rv = GridBagConstraints.RELATIVE;
		else if(height.equals("REMAINDER"))
			rv = GridBagConstraints.REMAINDER;
		else {
			rv = Integer.parseInt(
					(String)gridheightCombo.getSelectedItem());
		}
		return rv;
	}
}
class AnchorFillWeightPanel extends JPanel {
	JLabel		anchorLabel = new JLabel("Anchor:"),
				fillLabel 	= new JLabel("Fill:"),
				weightxLabel = new JLabel("weightx:"),
				weightyLabel = new JLabel("weighty:");

	JComboBox  	anchorCombo = new JComboBox(),
				fillCombo 	= new JComboBox();

	JComboBox	weightxCombo = new JComboBox(),
				weightyCombo = new JComboBox();

	public AnchorFillWeightPanel() {
		anchorCombo.addItem("NORTH");
		anchorCombo.addItem("NORTHEAST");
		anchorCombo.addItem("EAST");
		anchorCombo.addItem("SOUTHEAST");
		anchorCombo.addItem("SOUTH");
		anchorCombo.addItem("SOUTHWEST");
		anchorCombo.addItem("WEST");
		anchorCombo.addItem("NORTHWEST");
		anchorCombo.addItem("CENTER");

		weightxCombo.addItem("0.0");
		weightxCombo.addItem("0.1");
		weightxCombo.addItem("0.2");
		weightxCombo.addItem("0.25");
		weightxCombo.addItem("0.3");
		weightxCombo.addItem("0.4");
		weightxCombo.addItem("0.5");
		weightxCombo.addItem("0.6");
		weightxCombo.addItem("0.7");
		weightxCombo.addItem("0.75");
		weightxCombo.addItem("0.8");
		weightxCombo.addItem("0.9");
		weightxCombo.addItem("1.0");

		weightyCombo.addItem("0.0");
		weightyCombo.addItem("0.1");
		weightyCombo.addItem("0.2");
		weightyCombo.addItem("0.25");
		weightyCombo.addItem("0.3");
		weightyCombo.addItem("0.4");
		weightyCombo.addItem("0.5");
		weightyCombo.addItem("0.6");
		weightyCombo.addItem("0.7");
		weightyCombo.addItem("0.75");
		weightyCombo.addItem("0.8");
		weightyCombo.addItem("0.9");
		weightyCombo.addItem("1.0");

		fillCombo.addItem("NONE");
		fillCombo.addItem("HORIZONTAL");
		fillCombo.addItem("VERTICAL");
		fillCombo.addItem("BOTH");

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(gbl);
		gbc.anchor = GridBagConstraints.NORTHWEST;

		add(anchorLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		add(anchorCombo, gbc);
		gbc.weightx = 0;
		add(Box.createVerticalStrut(3), gbc);

		gbc.gridwidth = 1;
		add(fillLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		add(fillCombo, gbc);
		gbc.weightx = 0;
		add(Box.createVerticalStrut(13), gbc);

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(weightxLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		add(weightxCombo, gbc);
		gbc.weightx = 0;
		add(Box.createVerticalStrut(3), gbc);

		gbc.gridwidth = 1;
		add(weightyLabel, gbc);
		add(Box.createHorizontalStrut(10), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		add(weightyCombo, gbc);
		gbc.weightx = 0;
		gbc.gridwidth = 1;

		setBorder(new CompoundBorder(
				BorderFactory.createTitledBorder(
					"Anchor, Fill and Weight"),
				BorderFactory.createEmptyBorder(10,10,10,10)));

		fillCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();			

				if(state == ItemEvent.SELECTED) {
					GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

					tgbc.fill = getFill();
					GridBagLab.gblframe.setConstraints(tgbc);
				}
			}
		});
		anchorCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();			

				if(state == ItemEvent.SELECTED) {
					GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

					tgbc.anchor = getAnchor();
					GridBagLab.gblframe.setConstraints(tgbc);
				}
			}
		});
		weightxCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();			

				if(state == ItemEvent.SELECTED) {
					GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

					tgbc.weightx = (getWeightx()).doubleValue();
					GridBagLab.gblframe.setConstraints(tgbc);
				}
			}
		});
		weightyCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();			

				if(state == ItemEvent.SELECTED) {
					GridBagConstraints tgbc =
							GridBagLab.gblframe.getConstraints();

					tgbc.weighty = (getWeighty()).doubleValue();
					GridBagLab.gblframe.setConstraints(tgbc);
				}
			}
		});
		class FillListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("fill.txt");
			}
		}
		class AnchorListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("anchor.txt");
			}
		}
		class WeightListener extends MouseAdapter {
			public void mouseEntered(MouseEvent event) {
				GridBagLab.gblframe.setHelpViewer("weight.txt");
			}
		}
		AnchorListener anchorListener = new AnchorListener();
		WeightListener weightListener = new WeightListener();
		FillListener fillListener = new FillListener();

		anchorLabel.addMouseListener(anchorListener);
		anchorCombo.addMouseListener(anchorListener);

		weightxLabel.addMouseListener(weightListener);
		weightyLabel.addMouseListener(weightListener);

		weightxCombo.addMouseListener(weightListener);
		weightyCombo.addMouseListener(weightListener);

		fillLabel.addMouseListener(fillListener);
		fillCombo.addMouseListener(fillListener);
	}
	public Double getWeightx() {
		return Double.valueOf(
					(String)weightxCombo.getSelectedItem());
	}
	public Double getWeighty() {
		return Double.valueOf(
					(String)weightyCombo.getSelectedItem());
	}
	public void setWeightx(Double d) {
		weightxCombo.setSelectedItem(d.toString());
	}
	public void setWeighty(Double d) {
		weightyCombo.setSelectedItem(d.toString());
	}
	public int getAnchor() {
		String index = (String)anchorCombo.getSelectedItem();
		int anchor = GridBagConstraints.NORTH;

		if("NORTH".equals(index))
			anchor = GridBagConstraints.NORTH;
		else if("NORTHEAST".equals(index))
			anchor = GridBagConstraints.NORTHEAST;
		else if("NORTHWEST".equals(index))
			anchor = GridBagConstraints.NORTHWEST;
		else if("EAST".equals(index))
			anchor = GridBagConstraints.EAST;
		else if("SOUTHEAST".equals(index))
			anchor = GridBagConstraints.SOUTHEAST;
		else if("SOUTH".equals(index))
			anchor = GridBagConstraints.SOUTH;
		else if("SOUTHWEST".equals(index))
			anchor = GridBagConstraints.SOUTHWEST;
		else if("WEST".equals(index))
			anchor = GridBagConstraints.WEST;
		else if("CENTER".equals(index))
			anchor = GridBagConstraints.CENTER;

		return anchor;
	}
	public int getFill() {
		String index = (String)fillCombo.getSelectedItem();
		int fill = GridBagConstraints.NONE;

		if("NONE".equals(index))
			fill = GridBagConstraints.NONE;
		else if("HORIZONTAL".equals(index))
			fill = GridBagConstraints.HORIZONTAL;
		else if("VERTICAL".equals(index))
			fill = GridBagConstraints.VERTICAL;
		else if("BOTH".equals(index))
			fill = GridBagConstraints.BOTH;

		return fill;
	}
	public void setAnchor(int anchor) {
		if(anchor == GridBagConstraints.NORTH)
			anchorCombo.setSelectedItem("NORTH");
		else if(anchor == GridBagConstraints.NORTHEAST)
			anchorCombo.setSelectedItem("NORTHEAST");
		else if(anchor == GridBagConstraints.NORTHWEST)
			anchorCombo.setSelectedItem("NORTHWEST");
		else if(anchor == GridBagConstraints.SOUTH)
			anchorCombo.setSelectedItem("SOUTH");
		else if(anchor == GridBagConstraints.SOUTHEAST)
			anchorCombo.setSelectedItem("SOUTHEAST");
		else if(anchor == GridBagConstraints.SOUTHWEST)
			anchorCombo.setSelectedItem("SOUTHWEST");
		else if(anchor == GridBagConstraints.EAST)
			anchorCombo.setSelectedItem("EAST");
		else if(anchor == GridBagConstraints.WEST)
			anchorCombo.setSelectedItem("WEST");
		else if(anchor == GridBagConstraints.CENTER)
			anchorCombo.setSelectedItem("CENTER");
	}
	public void setFill(int fill) {
		if(fill == GridBagConstraints.NONE)
			fillCombo.setSelectedItem("NONE");
		else if(fill == GridBagConstraints.HORIZONTAL)
			fillCombo.setSelectedItem("HORIZONTAL");
		else if(fill == GridBagConstraints.VERTICAL)
			fillCombo.setSelectedItem("VERTICAL");
		else if(fill == GridBagConstraints.BOTH)
			fillCombo.setSelectedItem("BOTH");
	}
}
class AboutDialog extends Dialog {
	public AboutDialog(JFrame frame) {
		super(frame, "GridBagLab");

		JButton button = new JButton("Ok");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(new JLabel(
			"A Swing application for exploring GridBagLayout"));

		add(Box.createVerticalStrut(20));

		add(new JLabel(
			"Copyright 1997, Sabreware Inc.",
			new ImageIcon("sabreware.gif"),
			SwingConstants.LEFT));

		add(Box.createVerticalStrut(50));
		add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
