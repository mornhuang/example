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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import net.sf.linuxorg.pcal.messages.Messages;

/**
 * @author Maryan Rachynskyy
 *
 */
public class PCalHelpWindow extends JFrame {
	private static final long serialVersionUID = -4438820880718852611L;

	private JEditorPane editorPane;

	public PCalHelpWindow() {
		super(Messages.getString("PCalHelpWindow.0"));  //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setIconImage(MainWindow.createResoruceIcon("16x16/contents.png").getImage()); //$NON-NLS-1$

		JToolBar toolBar = new JToolBar(Messages.getString("PCalHelpWindow.1")); //$NON-NLS-1$
		JButton button = new JButton(MainWindow.createResoruceIcon("16x16/contents.png")); //$NON-NLS-1$
		button.setToolTipText(Messages.getString("PCalHelpWindow.2")); //$NON-NLS-1$
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadInitialPage();				
			}		    	
		});
		button.setFocusable(false);
		toolBar.add(button);
		add(toolBar, BorderLayout.PAGE_START);			

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setFocusable(false);

		if(!loadInitialPage()) return;

		editorPane.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					JEditorPane pane = (JEditorPane) e.getSource();
					if (e instanceof HTMLFrameHyperlinkEvent) {
						HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
						HTMLDocument doc = (HTMLDocument)pane.getDocument();
						doc.processHTMLFrameHyperlinkEvent(evt);
					} else {
						try {
							pane.setPage(e.getURL());
						} catch (Throwable t) {
							JOptionPane.showMessageDialog(PCalHelpWindow.this,
									Messages.getString("PCalHelpWindow.3"),  //$NON-NLS-1$
									Messages.getString("PCalHelpWindow.4"),  //$NON-NLS-1$
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		// Put the editor pane in a scroll pane.
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		editorScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		editorScrollPane.setPreferredSize(new Dimension(600, 400));
		editorScrollPane.setMinimumSize(new Dimension(100, 100));
		add(editorScrollPane);

		JButton b = new JButton(Messages.getString("PCalHelpWindow.5"), MainWindow.createResoruceIcon("16x16/ok.png"));   //$NON-NLS-1$//$NON-NLS-2$
		b.setFocusable(false);
		JPanel p = new JPanel();
		p.add(b);
		add(p, BorderLayout.PAGE_END);

		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePosition();
				setVisible(false);
				dispose();
			}
		});

		// Map escape key to OK button 
		for(ActionListener al : b.getActionListeners()) {
			rootPane.registerKeyboardAction(al, 
					KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
					JComponent.WHEN_IN_FOCUSED_WINDOW);
		}

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				savePosition();
			}		
		});

		pack();
		editorScrollPane.grabFocus();


		Preferences windowPrefsNode = PCalendar.settings.node("HelpWindow"); //$NON-NLS-1$
		Rectangle bounds = new Rectangle(); 
		bounds.width = windowPrefsNode.getInt("width", 680); //$NON-NLS-1$
		bounds.height = windowPrefsNode.getInt("height", 660); //$NON-NLS-1$
		bounds.x = windowPrefsNode.getInt("posX", 20); //$NON-NLS-1$
		bounds.y = windowPrefsNode.getInt("posY", 20); //$NON-NLS-1$
		setBounds(bounds);
		setVisible(true);
	}

	private boolean loadInitialPage() {
		java.net.URL helpURL = MainWindow.class.getResource(
				Messages.getString("PCalHelpWindow.6"));  //$NON-NLS-1$
		if (helpURL != null) {
			try {
				editorPane.setPage(helpURL);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this,
						Messages.getString("PCalHelpWindow.7"),  //$NON-NLS-1$
						Messages.getString("PCalHelpWindow.8"),  //$NON-NLS-1$
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this,
					Messages.getString("PCalHelpWindow.9"),  //$NON-NLS-1$
					Messages.getString("PCalHelpWindow.10"),  //$NON-NLS-1$
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void savePosition() {
		Preferences windowPrefsNode = PCalendar.settings.node("HelpWindow"); //$NON-NLS-1$
		Rectangle bounds = getBounds();
		windowPrefsNode.putInt("width", bounds.width); //$NON-NLS-1$
		windowPrefsNode.putInt("height", bounds.height); //$NON-NLS-1$
		windowPrefsNode.putInt("posX", bounds.x); //$NON-NLS-1$
		windowPrefsNode.putInt("posY", bounds.y); //$NON-NLS-1$
	}
	
}
