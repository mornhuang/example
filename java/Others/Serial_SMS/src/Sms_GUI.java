// Serial SMS client for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda

// Main class implementing GUI

import java.io.*;
import java.util.*;
import javax.comm.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Sms_GUI extends Frame implements ActionListener {

	final int HEIGHT = 450;

	final int WIDTH = 410;

	private MenuBar mb;

	private Menu fileMenu;

	private Menu settingsMenu;

	private Menu aboutMenu;

	private MenuItem openItem;

	private MenuItem saveItem;

	private MenuItem exitItem;

	private MenuItem serialItem;

	private MenuItem aboutItem;

	public Button openButton;

	public Button closeButton;

	private Panel buttonPanel;

	private Panel messagePanel;

	private String at_command = "";

	public TextArea OutTextArea;

	public TextArea InTextArea;

	private KeyHandler keyHandler;

	private SerialPanel SerialSettings;

	private InfoDialog info;

	private SmsTerminal term;

	private Properties props = null;

	/**
	 Main method. Checks to see if the command line agrument is requesting
	 usage informaition (-h, -help), if it is, display a usage message and
	 exit, otherwise create a new <code>SerialDemo</code> and set it visible.
	 */
	public static void main(String[] args) {
		if ((args.length > 0)
				&& (args[0].equals("-h") || args[0].equals("-help"))) {
			System.out
					.println("usage: java AccessSystemGUI [configuration File]");
			System.exit(1);
		}

		Sms_GUI GUI = new Sms_GUI(args);
		GUI.setVisible(true);
		GUI.repaint();
	}

	public Sms_GUI(String[] args) {
		super("Serial SMS Client");

		// Set up the GUI for the program
		addWindowListener(new CloseHandler(this));

		mb = new MenuBar();

		fileMenu = new Menu("File");

		openItem = new MenuItem("Load Settings");
		openItem.addActionListener(this);
		fileMenu.add(openItem);

		saveItem = new MenuItem("Save Settings");
		saveItem.addActionListener(this);
		fileMenu.add(saveItem);

		exitItem = new MenuItem("Exit");
		exitItem.addActionListener(this);
		fileMenu.add(exitItem);

		mb.add(fileMenu);

		settingsMenu = new Menu("Settings");

		serialItem = new MenuItem("Serial Port Settings");
		serialItem.addActionListener(this);
		settingsMenu.add(serialItem);

		mb.add(settingsMenu);

		aboutMenu = new Menu("Info");
		aboutItem = new MenuItem("About");
		aboutItem.addActionListener(this);
		aboutMenu.add(aboutItem);

		mb.add(aboutMenu);

		setMenuBar(mb);

		messagePanel = new Panel();
		messagePanel.setLayout(new GridLayout(2, 1));

		OutTextArea = new TextArea();
		OutTextArea.setEditable(false);
		OutTextArea.setFont(new Font("Monospaced", 0, 12));

		messagePanel.add(OutTextArea);

		InTextArea = new TextArea();
		InTextArea.setEditable(false);
		InTextArea.setFont(new Font("Monospaced", 0, 12));

		messagePanel.add(InTextArea);

		add(messagePanel, "Center");

		// Create a new KeyHandler to respond to key strokes in the
		// messageAreaOut. Add the KeyHandler as a keyListener to the
		// messageAreaOut.
		keyHandler = new KeyHandler();
		OutTextArea.addKeyListener(keyHandler);

		buttonPanel = new Panel();

		openButton = new Button("Open Port");
		openButton.addActionListener(this);
		buttonPanel.add(openButton);

		closeButton = new Button("Stop");
		closeButton.addActionListener(this);
		closeButton.setEnabled(false);
		buttonPanel.add(closeButton);

		Panel southPanel = new Panel();

		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();

		cons.gridwidth = GridBagConstraints.REMAINDER;
		gridBag.setConstraints(buttonPanel, cons);
		cons.weightx = 1.0;
		gridBag.setConstraints(buttonPanel, cons);
		southPanel.add(buttonPanel);

		add(southPanel, "South");

		parseArgs(args);

		SerialSettings = new SerialPanel(this);

		SerialSettings.setParameters();

		info = new InfoDialog();

		term = new SmsTerminal(this, SerialSettings.parameters, OutTextArea,
				InTextArea);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2
				- HEIGHT / 2);

		setSize(WIDTH, HEIGHT);
		setResizable(false);

	}

	/**
	 Responds to the menu items and buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// Loads a configuration file.
		if (cmd.equals("Load Settings")) {

			FileDialog fd = new FileDialog(this,
					"Load Serial Port Configuration", FileDialog.LOAD);
			fd.setVisible(true);
			String file = fd.getFile();
			if (file != null) {
				String dir = fd.getDirectory();
				File f = new File(dir + file);
				try {
					FileInputStream fis = new FileInputStream(f);
					props = new Properties();
					props.load(fis);
					fis.close();
				} catch (FileNotFoundException e1) {
					System.err.println(e1);
				} catch (IOException e2) {
					System.err.println(e2);
				}
				loadParams();
			}

		}

		// Saves a configuration file.
		if (cmd.equals("Save Settings")) {
			SerialSettings.setParameters();
			FileDialog fd = new FileDialog(this,
					"Save Serial Port Configuration", FileDialog.SAVE);
			fd.setFile("SerialPort.settings");
			fd.setVisible(true);
			String fileName = fd.getFile();
			String directory = fd.getDirectory();
			if ((fileName != null) && (directory != null)) {
				writeFile(directory + fileName);
			}
		}

		// Exits the program.
		if (cmd.equals("Exit")) {
			this.setVisible(false);
			try {
				term.close();
			} catch (NullPointerException ex) {
			}
		}

		// Calls serial settings window
		if (cmd.equals("Serial Port Settings")) {
			SerialSettings.setVisible(true);
			SerialSettings.repaint();
		}

		// Calls serial settings window
		if (cmd.equals("About")) {
			info.setVisible(true);
			info.repaint();

		}

		// Opens a port.
		if (cmd.equals("Open Port")) {
			System.out.println("Open serial connection");
			openButton.setEnabled(false);
			SerialSettings.setParameters();
			try {
				term.openConnection();
				closeButton.setEnabled(true);
				OutTextArea.setEditable(true);

			} catch (IOException e2) {
				AlertDialog ad = new AlertDialog(this, "Error Opening Port!",
						"Error opening port,", e2.getMessage() + ".",
						"Select new settings, try again.");
				openButton.setEnabled(true);
				return;
			}
		}

		// Closes a port.
		if (cmd.equals("Stop")) {
			try {
				term.close();
			} catch (NullPointerException ex) {
			}
			openButton.setEnabled(true);
			closeButton.setEnabled(false);
			OutTextArea.setEditable(false);
		}
	}

	/**
	 Writes the current parameters to a configuration file of the
	 java.properties style.
	 */
	private void writeFile(String path) {

		Properties newProps;
		FileOutputStream fileOut = null;

		newProps = new Properties();

		newProps.put("portName", (SerialSettings.parameters).getPortName());
		newProps.put("baudRate", (SerialSettings.parameters)
				.getBaudRateString());
		newProps.put("flowControlIn", (SerialSettings.parameters)
				.getFlowControlInString());
		newProps.put("flowControlOut", (SerialSettings.parameters)
				.getFlowControlOutString());
		newProps.put("parity", (SerialSettings.parameters).getParityString());
		newProps.put("databits", (SerialSettings.parameters)
				.getDatabitsString());
		newProps.put("stopbits", (SerialSettings.parameters)
				.getStopbitsString());

		try {
			fileOut = new FileOutputStream(path);
		} catch (IOException e) {
			System.out.println("Could not open file for writiing");
		}

		try {
			newProps.store(fileOut, "Serial Port settings");
		} catch (IOException e) {
			System.out.println("Could not write file");
		}

		try {
			fileOut.close();
		} catch (IOException e) {
			System.out.println("Could not close file for writiing");
		}
	}

	/**
	 Finds configuration file in arguments and creates a properties object from
	 that file.
	 */
	private void parseArgs(String[] args) {
		if (args.length < 1) {
			return;
		}

		File f = new File(args[0]);

		if (!f.exists()) {
			f = new File(System.getProperty("user.dir")
					+ System.getProperty("path.separator") + args[0]);
		}

		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				props = new Properties();
				props.load(fis);
				fis.close();
				loadParams();
			} catch (IOException e) {
			}
		}
	}

	/**
	 Set the parameters object to the settings in the properties object.
	 */
	private void loadParams() {
		(SerialSettings.parameters).setPortName(props.getProperty("portName"));
		(SerialSettings.parameters).setBaudRate(props.getProperty("baudRate"));
		(SerialSettings.parameters).setFlowControlIn(props
				.getProperty("flowControlIn"));
		(SerialSettings.parameters).setFlowControlOut(props
				.getProperty("flowControlOut"));
		(SerialSettings.parameters).setParity(props.getProperty("parity"));
		(SerialSettings.parameters).setDatabits(props.getProperty("databits"));
		(SerialSettings.parameters).setStopbits(props.getProperty("stopbits"));
		SerialSettings.setSerialPanel();
	}

	// Handles closing down system. Allows application to be closed with window close box.

	class CloseHandler extends WindowAdapter {

		Frame sp;

		public CloseHandler(Frame sp) {
			this.sp = sp;
		}

		public void windowClosing(WindowEvent e) {
			sp.setVisible(false);
			try {
				term.close();
			} catch (NullPointerException ex) {
			}
			System.out.println("Program End");
			System.exit(0);
		}
	}

	private int detectComma(String text) {
		int i;
		for (i = 0; i < text.length() - 1; i++) {
			if (text.charAt(i) == ',')
				break;
		}
		return i;
	}

	// parsing SMS commands
	public void parse_command(String at_command) {
		String temp;
		int comma;

		if ((at_command.toUpperCase()).startsWith("READSMS ")) {
			int index = Integer.parseInt(at_command.substring(8, 9), 10);
			term.ReadSMS(index);
		}

		if ((at_command.toUpperCase()).startsWith("DELETESMS ")) {
			int index = Integer.parseInt(at_command.substring(10, 11), 10);
			term.DeleteSMS(index);
		}

		else if ((at_command.toUpperCase()).startsWith("SENDSMS ")) {
			temp = at_command.substring(8, at_command.length());
			comma = detectComma(temp);
			if (comma != 0) {
				if (temp.charAt(comma + 1) == ' ')
					temp = temp.substring(0, comma + 1)
							+ temp.substring(comma + 2, temp.length());
				term.SendMessage(temp.substring(0, comma), "", temp.substring(
						comma + 1, temp.length()));
			}
		}

		else if ((at_command.toUpperCase()).startsWith("WRITERXSMS ")) {
			temp = at_command.substring(11, at_command.length());
			comma = detectComma(temp);
			if (comma != 0) {
				if (temp.charAt(comma + 1) == ' ')
					temp = temp.substring(0, comma + 1)
							+ temp.substring(comma + 2, temp.length());
				term.WriteRxMessage(temp.substring(0, comma), "", temp
						.substring(comma + 1, temp.length()));
			}
		}

		else if ((at_command.toUpperCase()).startsWith("WRITETXSMS ")) {
			temp = at_command.substring(11, at_command.length());
			comma = detectComma(temp);
			if (comma != 0) {
				if (temp.charAt(comma + 1) == ' ')
					temp = temp.substring(0, comma + 1)
							+ temp.substring(comma + 2, temp.length());
				term.WriteTxMessage(temp.substring(0, comma), "", temp
						.substring(comma + 1, temp.length()));
			}
		}

		else // Ordinary AT-Commands
		{
			term.atCmd(at_command, 0, 500);
		}

	}

	// For typing AT Command in Input window
	class KeyHandler extends KeyAdapter {

		public KeyHandler() {
		}

		public void keyTyped(KeyEvent evt) {

			if ((int) evt.getKeyChar() == 10) {
				parse_command(at_command);
				at_command = "";
			} else {
				at_command = at_command + evt.getKeyChar();
			}
		}
	}

}
