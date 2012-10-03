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
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import net.sf.linuxorg.pcal.messages.Messages;
import net.sf.linuxorg.pcal.PCalPeriodInfo;

/**
 * @author Maryan Rachynskyy
 *
 */
public class PCalStatisticsDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -2958115499763868813L;

	private JLabel periodsCountLabel;
	private JLabel avLengthLabel;
	private JLabel minLengthLabel;
	private JLabel maxLengthLabel;
	private JLabel calendarAccuracyLabel;

	int periodsCount = 0;
	int minLength = 0;
	int avgLength = 0;
	int maxLength = 0;
	int maxCount = 0;
	HashMap<Integer,Integer> lengthDistr = new HashMap<Integer,Integer>();

	/**
	 * Create the dialog with initial layout
	 */
	public PCalStatisticsDialog () {
		super(PCalendar.mainWindow.getFrame(), Messages.getString("PCalStatisticsDialog.0"), true); //$NON-NLS-1$

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				actionPerformed(null);
			}		
		});

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel l = new JLabel(Messages.getString("PCalStatisticsDialog.1")); //$NON-NLS-1$
		c.anchor = GridBagConstraints.WEST;
		c.insets.left = 10;
		add(l,c);
		l = new JLabel(Messages.getString("PCalStatisticsDialog.2")); //$NON-NLS-1$
		c.gridy = 1;
		add(l,c);
		l = new JLabel(Messages.getString("PCalStatisticsDialog.3")); //$NON-NLS-1$
		c.gridy = 2;
		add(l,c);
		l = new JLabel(Messages.getString("PCalStatisticsDialog.4")); //$NON-NLS-1$
		c.gridy = 3;
		add(l,c);
		l = new JLabel(Messages.getString("PCalStatisticsDialog.5")); //$NON-NLS-1$
		c.gridy = 4;
		add(l,c);

		c.gridx = 1;
		c.gridy = 0;
		calendarAccuracyLabel = new JLabel(Messages.getString("PCalStatisticsDialog.6")); //$NON-NLS-1$
		add(calendarAccuracyLabel,c);
		periodsCountLabel = new JLabel(Messages.getString("PCalStatisticsDialog.7")); //$NON-NLS-1$
		c.gridy = 1;
		add(periodsCountLabel,c);
		minLengthLabel = new JLabel(Messages.getString("PCalStatisticsDialog.8")); //$NON-NLS-1$
		c.gridy = 2;
		add(minLengthLabel,c);
		avLengthLabel = new JLabel(Messages.getString("PCalStatisticsDialog.9")); //$NON-NLS-1$
		c.gridy = 3;
		add(avLengthLabel,c);
		maxLengthLabel = new JLabel(Messages.getString("PCalStatisticsDialog.10")); //$NON-NLS-1$
		c.gridy = 4;
		add(maxLengthLabel,c);

		JButton b = new JButton(Messages.getString("PCalStatisticsDialog.11"), MainWindow.createResoruceIcon("16x16/ok.png")); //$NON-NLS-1$ //$NON-NLS-2$
		b.addActionListener(this);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.insets.left = 0;
		add(b, c);
		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(b);
		for(ActionListener al : b.getActionListeners()) {
			rootPane.registerKeyboardAction(al, 
					KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
					JComponent.WHEN_IN_FOCUSED_WINDOW);
		}

		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(Messages.getString("PCalStatisticsDialog.12")), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(5,5,5,5)));
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets.top = 10;
		add(p,c);

		// this is a graph building core
		l = new JLabel() {
			private static final long serialVersionUID = 0L;
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g.create();

				int width = getWidth();
				int height = getHeight();

				// calculate the axes label sizes
				FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
				// get the height of a line of text in this font and render context
				int xdev = metrics.stringWidth("888"); //$NON-NLS-1$
				int ydev = metrics.getHeight();				
				int halfWidth = metrics.stringWidth("8"); //$NON-NLS-1$
				int halfHeight = ydev/2;

				// paint background
				g2d.setColor(Color.WHITE);
				g2d.fillRect(0, 0, getWidth(), getHeight());

				if(periodsCount>1) {

					// calculate the current scale
					int nPoints = maxLength - minLength + 3;
					int deltax = (width - xdev) / (nPoints - 3);
					int deltay = (height - ydev) / maxCount;

					//draw the histogram
					int[] xPoints = new int[nPoints];
					int[] yPoints = new int[nPoints];

					// set the start line coordinates
					xPoints[0] = xdev;
					yPoints[0] = height - ydev;
					//set the end line coordinates
					xPoints[nPoints-1] = width;
					yPoints[nPoints-1] = height - ydev;
			
					for(int i = 1; i< nPoints - 1; i++) {
						xPoints[i] = xdev+(i-1)*deltax;
						Integer currLengthCount = lengthDistr.get(i+minLength-1);
						if(currLengthCount == null) currLengthCount = 0;
						
						yPoints[i] = height - ydev -  currLengthCount*deltay;
					}
					g2d.setColor(Color.YELLOW);
					g2d.fillPolygon(xPoints, yPoints, nPoints);

					// draw the grid
					g2d.setColor(Color.BLACK);
					//Vertical
					for(int i=0; i< (maxLength - minLength+1); i++) {
						int xpos = xdev+i*deltax;
						g2d.drawLine(xpos, 0, xpos, height - ydev);
						int xTextPos = xpos - halfWidth;
						if(i == (maxLength - minLength)) {
							xTextPos -= halfWidth;
						}
						g2d.drawString(""+(i+minLength), xTextPos, height - 2); //$NON-NLS-1$
					}
					//HORIZONTAL
					for(int i = 0; i<=maxCount; i++) {
						int ypos = height - ydev - i*deltay;
						g2d.drawLine(xdev, ypos, width, ypos);
						g2d.drawString(""+i, 2, ypos+halfHeight); //$NON-NLS-1$
					}
				}

				g2d.dispose(); //clean up
			}			
		};

		p.add(l);

		pack();

		//Restore the frame position and size
		Preferences windowPrefsNode = PCalendar.settings.node("StatsWindow"); //$NON-NLS-1$
		Rectangle bounds = new Rectangle(); 
		bounds.width = windowPrefsNode.getInt("width", 380); //$NON-NLS-1$
		bounds.height = windowPrefsNode.getInt("height", 450); //$NON-NLS-1$
		bounds.x = windowPrefsNode.getInt("posX", 200); //$NON-NLS-1$
		bounds.y = windowPrefsNode.getInt("posY", 200); //$NON-NLS-1$
		setBounds(bounds);
	}

	/**
	 * Show the dialog
	 * @param stats - the list of the periods with some statistics information
	 */
	public void showDialog() {
		periodsCount = PCalendar.engine.getPeriodsCount();
		minLength = PCalendar.engine.getMinLength();
		avgLength = PCalendar.engine.getAvgLength();
		maxLength = PCalendar.engine.getMaxLength();
		Vector<PCalPeriodInfo> periodStats = PCalendar.engine.getPeriodsStats();
		lengthDistr.clear();
		maxCount = 0;

		//prepare the data for the histogram
		for(PCalPeriodInfo pstat : periodStats) {
			Integer currLevel = lengthDistr.get(pstat.length);
			if(currLevel == null) currLevel = 0;
			if(maxCount < ++currLevel) maxCount = currLevel; 
			lengthDistr.put(pstat.length, currLevel);			
		}

		periodsCountLabel.setText("" + periodsCount); //$NON-NLS-1$
		avLengthLabel.setText("" + avgLength); //$NON-NLS-1$
		minLengthLabel.setText("" + minLength); //$NON-NLS-1$
		maxLengthLabel.setText("" + maxLength); //$NON-NLS-1$

		String accurLabel = Messages.getString("PCalStatisticsDialog.13"); //$NON-NLS-1$
		Color accurColor = new Color(0,0,0);

		switch(PCalendar.engine.getCalMethodAccuracy()) {
		case 0: {
			accurLabel = Messages.getString("PCalStatisticsDialog.14");  //$NON-NLS-1$
			accurColor = new Color(0x4,0x67,0x0);
			break;
		}
		case 1: {
			accurLabel = Messages.getString("PCalStatisticsDialog.15"); //$NON-NLS-1$
			accurColor = new Color(0x83,0xA1,0x10);
			break;
		}
		case 2: {
			accurLabel = Messages.getString("PCalStatisticsDialog.16"); //$NON-NLS-1$
			accurColor = new Color(0xC1,0x0A,0x1D);
			break;
		}
		}

		calendarAccuracyLabel.setText(accurLabel);
		calendarAccuracyLabel.setForeground(accurColor);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// save the dialog size and position
		Preferences windowPrefsNode = PCalendar.settings.node("StatsWindow"); //$NON-NLS-1$
		Rectangle bounds = getBounds();
		windowPrefsNode.putInt("width", bounds.width); //$NON-NLS-1$
		windowPrefsNode.putInt("height", bounds.height); //$NON-NLS-1$
		windowPrefsNode.putInt("posX", bounds.x); //$NON-NLS-1$
		windowPrefsNode.putInt("posY", bounds.y); //$NON-NLS-1$

		setVisible(false);
	}
}
