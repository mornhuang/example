// Serial SMS Client for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda

// Class representing SMS settings panel

import javax.comm.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Enumeration;

class SerialPanel extends Frame implements ItemListener {
	final int HEIGHT = 260;

	final int WIDTH = 300;

	private Frame parent;

	private Label portNameLabel;

	private Choice portChoice;

	private Label baudLabel;

	private Choice baudChoice;

	private Label flowControlInLabel;

	private Choice flowChoiceIn;

	private Label flowControlOutLabel;

	private Choice flowChoiceOut;

	private Label databitsLabel;

	private Choice databitsChoice;

	private Label stopbitsLabel;

	private Choice stopbitsChoice;

	private Label parityLabel;

	private Choice parityChoice;

	public SerialParameters parameters;

	/**
	 Creates and initilizes the configuration panel. The initial settings
	 are from the parameters object.
	 */
	public SerialPanel(Frame parent) {
		super("Serial Settings");
		addWindowListener(new CloseHandler(this));
		this.parent = parent;
		parameters = new SerialParameters();
		setLayout(new GridLayout(7, 2));

		portNameLabel = new Label("Port Name:", Label.LEFT);
		add(portNameLabel);

		portChoice = new Choice();
		portChoice.addItemListener(this);
		add(portChoice);
		listPortChoices();
		portChoice.select(parameters.getPortName());

		baudLabel = new Label("Baud Rate:", Label.LEFT);
		add(baudLabel);

		baudChoice = new Choice();
		baudChoice.addItem("300");
		baudChoice.addItem("2400");
		baudChoice.addItem("9600");
		baudChoice.addItem("14400");
		baudChoice.addItem("28800");
		baudChoice.addItem("38400");
		baudChoice.addItem("57600");
		baudChoice.addItem("152000");
		baudChoice.select(Integer.toString(parameters.getBaudRate()));
		baudChoice.addItemListener(this);
		add(baudChoice);

		flowControlInLabel = new Label("Flow Control In:", Label.LEFT);
		add(flowControlInLabel);

		flowChoiceIn = new Choice();
		flowChoiceIn.addItem("None");
		flowChoiceIn.addItem("Xon/Xoff In");
		flowChoiceIn.addItem("RTS/CTS In");
		flowChoiceIn.select(parameters.getFlowControlInString());
		flowChoiceIn.addItemListener(this);
		add(flowChoiceIn);

		flowControlOutLabel = new Label("Flow Control Out:", Label.LEFT);
		add(flowControlOutLabel);

		flowChoiceOut = new Choice();
		flowChoiceOut.addItem("None");
		flowChoiceOut.addItem("Xon/Xoff Out");
		flowChoiceOut.addItem("RTS/CTS Out");
		flowChoiceOut.select(parameters.getFlowControlOutString());
		flowChoiceOut.addItemListener(this);
		add(flowChoiceOut);

		databitsLabel = new Label("Data Bits:", Label.LEFT);
		add(databitsLabel);

		databitsChoice = new Choice();
		databitsChoice.addItem("5");
		databitsChoice.addItem("6");
		databitsChoice.addItem("7");
		databitsChoice.addItem("8");
		databitsChoice.select(parameters.getDatabitsString());
		databitsChoice.addItemListener(this);
		add(databitsChoice);

		stopbitsLabel = new Label("Stop Bits:", Label.LEFT);
		add(stopbitsLabel);

		stopbitsChoice = new Choice();
		stopbitsChoice.addItem("1");
		stopbitsChoice.addItem("1.5");
		stopbitsChoice.addItem("2");
		stopbitsChoice.select(parameters.getStopbitsString());
		stopbitsChoice.addItemListener(this);
		add(stopbitsChoice);

		parityLabel = new Label("Parity:", Label.LEFT);
		add(parityLabel);

		parityChoice = new Choice();
		parityChoice.addItem("None");
		parityChoice.addItem("Even");
		parityChoice.addItem("Odd");
		parityChoice.select("None");
		parityChoice.select(parameters.getParityString());
		parityChoice.addItemListener(this);
		add(parityChoice);
		pack();
		setSize(WIDTH, HEIGHT);
		setResizable(false);
	}

	/**
	 Sets the configuration panel to the settings in the parameters object.
	 */
	public void setSerialPanel() {
		portChoice.select(parameters.getPortName());
		baudChoice.select(parameters.getBaudRateString());
		flowChoiceIn.select(parameters.getFlowControlInString());
		flowChoiceOut.select(parameters.getFlowControlOutString());
		databitsChoice.select(parameters.getDatabitsString());
		stopbitsChoice.select(parameters.getStopbitsString());
		parityChoice.select(parameters.getParityString());
	}

	/**
	 Sets the parameters object to the settings in the configuration panel.
	 */
	public void setParameters() {
		parameters.setPortName(portChoice.getSelectedItem());
		parameters.setBaudRate(baudChoice.getSelectedItem());
		parameters.setFlowControlIn(flowChoiceIn.getSelectedItem());
		parameters.setFlowControlOut(flowChoiceOut.getSelectedItem());
		parameters.setDatabits(databitsChoice.getSelectedItem());
		parameters.setStopbits(stopbitsChoice.getSelectedItem());
		parameters.setParity(parityChoice.getSelectedItem());
	}

	/**
	 Sets the elements for the portChoice from the ports available on the
	 system. Uses an emuneration of comm ports returned by
	 CommPortIdentifier.getPortIdentifiers(), then sets the current
	 choice to a mathing element in the parameters object.
	 */
	void listPortChoices() {
		CommPortIdentifier portId;

		Enumeration en = CommPortIdentifier.getPortIdentifiers();

		// iterate through the ports.
		while (en.hasMoreElements()) {
			portId = (CommPortIdentifier) en.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				portChoice.addItem(portId.getName());
			}
		}
		portChoice.select(parameters.getPortName());
	}

	/**
	 Event handler for changes in the current selection of the Choices.
	 If a port is open the port can not be changed.
	 If the choice is unsupported on the platform then the user will
	 be notified and the settings will revert to their pre-selection
	 state.
	 */
	public void itemStateChanged(ItemEvent e) {

		setParameters();
	}
}

/**
 Handles closing down system. Allows application to be closed with window
 close box.
 */
class CloseHandler extends WindowAdapter {

	SerialPanel sp;

	public CloseHandler(SerialPanel sp) {
		this.sp = sp;
	}

	public void windowClosing(WindowEvent e) {
		sp.setVisible(false);
	}
}
