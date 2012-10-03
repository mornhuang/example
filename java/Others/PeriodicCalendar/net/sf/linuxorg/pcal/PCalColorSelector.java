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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.linuxorg.pcal.messages.Messages;

/**
 * @author Maryan Rachynskyy
 *
 */
public class PCalColorSelector extends javax.swing.JDialog implements ActionListener, ChangeListener{
	private static final long serialVersionUID = -8792547711478381316L;

	//this is a reference to the target label which color is going to be changed
	private JComponent targetComponent = null;
	final private JLabel sampleLabel = new JLabel(Messages.getString("PCalColorSelector.0")); //$NON-NLS-1$
	final private JColorChooser fgColorChooser;
	final private JColorChooser bgColorChooser;

	/**
	 * Create the dialog with initial layout
	 */
	public PCalColorSelector () {
		super(PCalendar.mainWindow.getFrame(), Messages.getString("PCalColorSelector.1"), true); //$NON-NLS-1$

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				actionPerformed(null);
			}		
		});

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		//Build the main panel
		JPanel p = new JPanel(new GridLayout(0,1));
		JPanel p1 = new JPanel();
		p1.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(Messages.getString("PCalColorSelector.2")), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(2,2,2,2)));
		fgColorChooser = new JColorChooser();
		AbstractColorChooserPanel chooserPanel[] = {fgColorChooser.getChooserPanels()[0]};
		fgColorChooser.setChooserPanels(chooserPanel);
		fgColorChooser.setPreviewPanel(new JPanel());
		fgColorChooser.getSelectionModel().addChangeListener(this);
		p1.add(fgColorChooser);
		p.add(p1);
		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(Messages.getString("PCalColorSelector.3")), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(2,2,2,2)));
		bgColorChooser = new JColorChooser();
		AbstractColorChooserPanel chooserPanel2[] = {bgColorChooser.getChooserPanels()[0]};
		bgColorChooser.setChooserPanels(chooserPanel2);
		bgColorChooser.setPreviewPanel(new JPanel());
		bgColorChooser.getSelectionModel().addChangeListener(this);
		p2.add(bgColorChooser);
		p.add(p2);
		add(p, BorderLayout.CENTER);

		//build the bottom buttons pane
		JRootPane rootPane = getRootPane();
		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createRigidArea(new Dimension(5,0)));
		sampleLabel.setOpaque(true);
		p.add(sampleLabel);
		p.add(Box.createHorizontalGlue());
		JButton b = new JButton(Messages.getString("PCalColorSelector.4"), MainWindow.createResoruceIcon("16x16/refresh.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.setActionCommand("Refresh"); //$NON-NLS-1$
		b.addActionListener(this);
		p.add(b);
		p.add(Box.createRigidArea(new Dimension(5,0)));

		b = new JButton(Messages.getString("PCalColorSelector.5"), MainWindow.createResoruceIcon("16x16/ok.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.setActionCommand("OK"); //$NON-NLS-1$
		p.add(b);
		p.add(Box.createRigidArea(new Dimension(5,0)));
		b.addActionListener(this);
		rootPane.setDefaultButton(b);

		b = new JButton(Messages.getString("PCalColorSelector.6"), MainWindow.createResoruceIcon("16x16/cancel.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.setActionCommand("Cancel"); //$NON-NLS-1$
		p.add(b);
		p.add(Box.createRigidArea(new Dimension(5,0)));
		b.addActionListener(this);
		for(ActionListener al : b.getActionListeners()) {
			rootPane.registerKeyboardAction(al, 
					KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
					JComponent.WHEN_IN_FOCUSED_WINDOW);
		}

		add(p, BorderLayout.PAGE_END);

		pack();

		//Restore the frame position and size
		Preferences windowPrefsNode = PCalendar.settings.node("ColorChooserWindow"); //$NON-NLS-1$
		Rectangle bounds = new Rectangle(); 
		bounds.width = windowPrefsNode.getInt("width", 480); //$NON-NLS-1$
		bounds.height = windowPrefsNode.getInt("height", 360); //$NON-NLS-1$
		bounds.x = windowPrefsNode.getInt("posX", 200); //$NON-NLS-1$
		bounds.y = windowPrefsNode.getInt("posY", 200); //$NON-NLS-1$
		setBounds(bounds);
	}

	public void showForLabel(final JComponent label) {
		targetComponent = label;
		//read the initial color values
		fgColorChooser.setColor(label.getForeground());
		bgColorChooser.setColor(label.getBackground());
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		if(event!=null) {
			String command = ""+event.getActionCommand(); //$NON-NLS-1$
			if(command.equals("Refresh")) { //$NON-NLS-1$
				fgColorChooser.setColor(targetComponent.getForeground());
				bgColorChooser.setColor(targetComponent.getBackground());
				return;
			} else
				if(command.equals("OK")) { //$NON-NLS-1$
					targetComponent.setForeground(fgColorChooser.getColor());
					targetComponent.setBackground(bgColorChooser.getColor());			
				}
		}

		// save the dialog size and position
		Preferences windowPrefsNode = PCalendar.settings.node("ColorChooserWindow"); //$NON-NLS-1$
		Rectangle bounds = getBounds();
		windowPrefsNode.putInt("width", bounds.width); //$NON-NLS-1$
		windowPrefsNode.putInt("height", bounds.height); //$NON-NLS-1$
		windowPrefsNode.putInt("posX", bounds.x); //$NON-NLS-1$
		windowPrefsNode.putInt("posY", bounds.y); //$NON-NLS-1$

		setVisible(false);

		targetComponent = null;
	}

	public void stateChanged(ChangeEvent e) {
		// ColorSelectionChange
		sampleLabel.setForeground(fgColorChooser.getColor());
		sampleLabel.setBackground(bgColorChooser.getColor());
	}

}
