/**
 *  Copyright (C) 2007 by Maryan Rachynskyy
 *  mrach@users.sourceforge.net
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.linuxorg.pcal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.linuxorg.pcal.engine.Engine;
import net.sf.linuxorg.pcal.messages.Messages;

/**
 * Show, edit and process application preferences
 * @author Maryan Rachynskyy
 *
 */
public class PCalPrefDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -2641057054897738671L;

	//The preferences components
	private JTextField loadOnStartupField;
	private JSpinner bufferDaysSpinner;
	private JSpinner badFeelDaysBeforeSpinner;
	private JSpinner badFeelDaysAfterSpinner;
	private JSpinner regularMinDaysSpinner;
	private JSpinner regularMaxDaysSpinner;
	private final JButton legendLabels[] = {new JButton(Messages.getString("PCalPrefDialog.0")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.1")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.2")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.3")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.4")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.5")), //$NON-NLS-1$
			new JButton(Messages.getString("PCalPrefDialog.6"))}; //$NON-NLS-1$
	private final PCalColorSelector colorSelector = new PCalColorSelector();

	/**
	 * Create the dialog with initial layout
	 */
	public PCalPrefDialog () {
		super(PCalendar.mainWindow.getFrame(), Messages.getString("PCalPrefDialog.7"), true); //$NON-NLS-1$

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				actionPerformed(null);
			}		
		});

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setLayout(new BorderLayout());

		//build the main tabbed pane		
		JTabbedPane tabbedPane = new JTabbedPane();

		//General		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets.right = 5;
		JLabel l = new JLabel(Messages.getString("PCalPrefDialog.8")); //$NON-NLS-1$
		p.add(l,c);
		loadOnStartupField = new JTextField(25);
		c.gridx = 1;
		p.add(loadOnStartupField, c);

		JButton b = new JButton(Messages.getString("PCalPrefDialog.9")); //$NON-NLS-1$
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				// the last folder visited for open is stored in the preferences				
				Preferences dialogsNode = PCalendar.settings.node("Dialogs"); //$NON-NLS-1$
				String lastOpenFolder = dialogsNode.get("last file open folder", ""); //$NON-NLS-1$ //$NON-NLS-2$

				final JFileChooser fc = new JFileChooser(lastOpenFolder);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						Messages.getString("MainWindow.90"), "pcal"); //$NON-NLS-1$ //$NON-NLS-2$
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(PCalendar.mainWindow.getFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					dialogsNode.put("last file open folder", fc.getSelectedFile().getPath()); //$NON-NLS-1$
					try {
						loadOnStartupField.setText(fc.getSelectedFile().getCanonicalPath());
					} catch (IOException e) {
						//ignore the exception here
					}
				}				
			}
		});
		c.gridx = 2;
		p.add(b,c);
		tabbedPane.addTab(Messages.getString("PCalPrefDialog.10"), p); //$NON-NLS-1$
		tabbedPane.setMnemonicAt(0, Messages.getMnemonic("PCalPrefDialog.10.Mnemonic"));  //$NON-NLS-1$

		//Calendar
		p = new JPanel(new GridBagLayout());

		ActionListener colorChooserAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Color")) { //$NON-NLS-1$
					colorSelector.showForLabel((JButton)e.getSource());
				}
			}			
		};

		for(int i=0; i<legendLabels.length; i++) {
			legendLabels[i].setContentAreaFilled(false);
			legendLabels[i].setOpaque(true);
			legendLabels[i].setHorizontalAlignment(JLabel.LEFT);
			legendLabels[i].setActionCommand("Color"); //$NON-NLS-1$
			legendLabels[i].addActionListener(colorChooserAction);
			c = new GridBagConstraints();
			switch(i) {
			case 0:
			case 1: c.gridy = 1; break;
			case 2:
			case 3: c.gridy = 2; break;
			case 4: c.gridy = 3; break;
			case 5:
			case 6: c.gridy = 4;	
			}
			c.gridx = ((i==1)||(i==3)||(i==6))?5:1; 
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.NORTHWEST;
			c.insets.right = 5;
			c.insets.bottom = 5;
			JPanel parent = new JPanel(new BorderLayout());
			parent.add(legendLabels[i]);
			p.add(parent, c);
		}

//		tabbedPane.addTab(Messages.getString("PCalPrefDialog.13"), p); //$NON-NLS-1$
//		tabbedPane.setMnemonicAt(1, Messages.getMnemonic("PCalPrefDialog.13.Mnemonic")); //$NON-NLS-1$

		//Calculations
		p = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.insets.right = 5;
		c.insets.bottom = 5;
		l = new JLabel(Messages.getString("PCalPrefDialog.14")); //$NON-NLS-1$
		p.add(l, c);
		bufferDaysSpinner = new JSpinner(
				new SpinnerNumberModel(0, //initial value
						-3, //min
						3, //max
						1)); //step
		JComponent editor = bufferDaysSpinner.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			((JSpinner.DefaultEditor)editor).getTextField().setEditable(false);
		}
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		p.add(bufferDaysSpinner,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.15")); //$NON-NLS-1$
		c.gridx = 2;
		c.gridwidth = 3;
		p.add(l,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.16")); //$NON-NLS-1$
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		p.add(l,c);		
		badFeelDaysBeforeSpinner = new JSpinner(
				new SpinnerNumberModel(0, //initial value
						0, //min
						5, //max
						1)); //step);
		editor = badFeelDaysBeforeSpinner.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			((JSpinner.DefaultEditor)editor).getTextField().setEditable(false);
		}
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		p.add(badFeelDaysBeforeSpinner,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.17")); //$NON-NLS-1$
		c.gridx = 2;
		p.add(l,c);
		badFeelDaysAfterSpinner = new JSpinner(
				new SpinnerNumberModel(0, //initial value
						0, //min
						5, //max
						1)); //step
		editor = badFeelDaysAfterSpinner.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			((JSpinner.DefaultEditor)editor).getTextField().setEditable(false);
		}
		c.gridx = 3;
		p.add(badFeelDaysAfterSpinner,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.18")); //$NON-NLS-1$
		c.gridx = 4;
		p.add(l,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.19")); //$NON-NLS-1$
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		p.add(l,c);
		regularMinDaysSpinner = new JSpinner(
				new SpinnerNumberModel(24, //initial value
						15, //min
						30, //max
						1)); //step
		editor = regularMinDaysSpinner.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			((JSpinner.DefaultEditor)editor).getTextField().setEditable(false);
		}
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		p.add(regularMinDaysSpinner,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.20")); //$NON-NLS-1$
		c.gridx = 2;
		p.add(l,c);
		regularMaxDaysSpinner = new JSpinner(
				new SpinnerNumberModel(35, //initial value
						20, //min
						50, //max
						1)); //step
		editor = regularMaxDaysSpinner.getEditor();
		if(editor instanceof JSpinner.DefaultEditor) {
			((JSpinner.DefaultEditor)editor).getTextField().setEditable(false);
		}
		c.gridx = 3;
		p.add(regularMaxDaysSpinner,c);
		l = new JLabel(Messages.getString("PCalPrefDialog.21")); //$NON-NLS-1$
		c.gridx = 4;
		p.add(l,c);

//		tabbedPane.addTab(Messages.getString("PCalPrefDialog.22"), p); //$NON-NLS-1$
//		tabbedPane.setMnemonicAt(2, Messages.getMnemonic("PCalPrefDialog.22.Mnemonic")); //$NON-NLS-1$

		add(tabbedPane, BorderLayout.CENTER);

		//build the bottom buttons pane
		JRootPane rootPane = getRootPane();
		p = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		b = new JButton(Messages.getString("PCalPrefDialog.23"), MainWindow.createResoruceIcon("16x16/refresh.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.setActionCommand("Defaults"); //$NON-NLS-1$
		b.addActionListener(this);
		p.add(b);

		b = new JButton(Messages.getString("PCalPrefDialog.26"), MainWindow.createResoruceIcon("16x16/ok.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.setActionCommand("OK"); //$NON-NLS-1$
		p.add(b);
		b.addActionListener(this);
		rootPane.setDefaultButton(b);

		b = new JButton(Messages.getString("PCalPrefDialog.29"), MainWindow.createResoruceIcon("16x16/cancel.png")); //$NON-NLS-1$ //$NON-NLS-2$
		p.add(b);
		b.addActionListener(this);
		for(ActionListener al : b.getActionListeners()) {
			rootPane.registerKeyboardAction(al, 
					KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
					JComponent.WHEN_IN_FOCUSED_WINDOW);
		}

		add(p, BorderLayout.PAGE_END);

		pack();

		//Restore the frame position and size
		Preferences windowPrefsNode = PCalendar.settings.node("PrefsWindow"); //$NON-NLS-1$
		Rectangle bounds = new Rectangle(); 
		bounds.width = windowPrefsNode.getInt("width", 620); //$NON-NLS-1$
		bounds.height = windowPrefsNode.getInt("height", 300); //$NON-NLS-1$
		bounds.x = windowPrefsNode.getInt("posX", 100); //$NON-NLS-1$
		bounds.y = windowPrefsNode.getInt("posY", 100); //$NON-NLS-1$
		setBounds(bounds);
	}

	public void actionPerformed(ActionEvent event) {
		// save the dialog size and position
		Preferences windowPrefsNode = PCalendar.settings.node("PrefsWindow"); //$NON-NLS-1$
		Rectangle bounds = getBounds();
		windowPrefsNode.putInt("width", bounds.width); //$NON-NLS-1$
		windowPrefsNode.putInt("height", bounds.height); //$NON-NLS-1$
		windowPrefsNode.putInt("posX", bounds.x); //$NON-NLS-1$
		windowPrefsNode.putInt("posY", bounds.y); //$NON-NLS-1$
		if(event!=null) {
			String command = ""+event.getActionCommand(); //$NON-NLS-1$
			if(command.equals("Defaults")) { //$NON-NLS-1$
				loadOnStartupField.setText(""); //$NON-NLS-1$ 
				for(int i = 0; i<legendLabels.length; i++) {
					legendLabels[i].setForeground(PCalendar.mainWindow.defaultFGColors[i]);
					legendLabels[i].setBackground(PCalendar.mainWindow.defaultBGColors[i]);
				}
				
				// Load the engine preferences
				showEnginePreferences(PCalendar.engine.getDefaultPreferences()); 
				return;
			} else
				if(command.equals("OK")) { //$NON-NLS-1$
					// Apply the main window preferences
					windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
					windowPrefsNode.put("AutoLoadFile", loadOnStartupField.getText().trim()); //$NON-NLS-1$	

					for(int i = 0; i<legendLabels.length; i++) {
						PCalendar.mainWindow.legendFGColors[i] = legendLabels[i].getForeground();
						PCalendar.mainWindow.legendBGColors[i] = legendLabels[i].getBackground();
						windowPrefsNode.putInt("FGColor"+i, PCalendar.mainWindow.legendFGColors[i].getRGB()); //$NON-NLS-1$
						windowPrefsNode.putInt("BGColor"+i, PCalendar.mainWindow.legendBGColors[i].getRGB()); //$NON-NLS-1$
					}
					
					// Apply Engine preferences
					Engine.EnginePreferences enginePreferences = PCalendar.engine.getEnginePreferences();
					enginePreferences.bufferDays = ((SpinnerNumberModel) bufferDaysSpinner.getModel()).getNumber().intValue(); 
					enginePreferences.badFeelDaysBefore = ((SpinnerNumberModel) badFeelDaysBeforeSpinner.getModel()).getNumber().intValue(); 
					enginePreferences.badFeelDaysAfter = ((SpinnerNumberModel) badFeelDaysAfterSpinner.getModel()).getNumber().intValue(); 
					enginePreferences.regularMinLength = ((SpinnerNumberModel) regularMinDaysSpinner.getModel()).getNumber().intValue(); 
					enginePreferences.regularMaxLength = ((SpinnerNumberModel) regularMaxDaysSpinner.getModel()).getNumber().intValue(); 
					PCalendar.engine.setEnginePreferences(enginePreferences);
					
					PCalendar.mainWindow.refreshLegend();
					PCalendar.mainWindow.refreshAll(false);
				}
		}
		
		setVisible(false);

	}

	/**
	 * Show the engine preferences specified in the preferences dialog
	 * @param enginePreferences
	 */
	private void showEnginePreferences(Engine.EnginePreferences enginePreferences) {
		bufferDaysSpinner.setValue(enginePreferences.bufferDays);
		badFeelDaysBeforeSpinner.setValue(enginePreferences.badFeelDaysBefore);
		badFeelDaysAfterSpinner.setValue(enginePreferences.badFeelDaysAfter);
		regularMinDaysSpinner.setValue(enginePreferences.regularMinLength);
		regularMaxDaysSpinner.setValue(enginePreferences.regularMaxLength);
	}

	public void showDialog() {
		Preferences windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
		loadOnStartupField.setText(windowPrefsNode.get("AutoLoadFile", ""));  //$NON-NLS-1$ //$NON-NLS-2$
		
		// Load the main window preferences
		for(int i = 0; i<legendLabels.length; i++) {
			legendLabels[i].setForeground(PCalendar.mainWindow.legendFGColors[i]);
			legendLabels[i].setBackground(PCalendar.mainWindow.legendBGColors[i]);
		}
		
		// Load the engine preferences
		showEnginePreferences(PCalendar.engine.getEnginePreferences()); 

		setVisible(true);
	}

}
