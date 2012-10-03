/**
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
 *
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.linuxorg.pcal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TreeSet;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import net.sf.linuxorg.pcal.engine.Engine;
import net.sf.linuxorg.pcal.engine.Engine.PasswordRequiredException;
import net.sf.linuxorg.pcal.messages.Messages;

/**
 * @author Maryan Rachynskyy
 * 
 */
public class MainWindow {

    private static final String PCAL_EXT = "pcal"; //$NON-NLS-1$

    private static final String PCAL_ENC_EXT = "pcalc"; //$NON-NLS-1$

    public final Color DEFAULT_UNDEFINED_MONTH_BG_COLOR = new Color(0xFFFFFF);

    public final Color DEFAULT_UNDEFINED_MONTH_FG_COLOR = new Color(0x000000);

    public final Color DEFAULT_INACTIVE_MONTH_BG_COLOR = new Color(0xE5E5E5);

    public final Color DEFAULT_INACTIVE_MONTH_FG_COLOR = new Color(0xBFBFBF);

    public final Color DEFAULT_DAY1_BG_COLOR = new Color(0xB85252);

    public final Color DEFAULT_DAY1_FG_COLOR = new Color(0xFFFF00);

    public final Color DEFAULT_PDAY1_BG_COLOR = new Color(0xE01F1F);

    public final Color DEFAULT_PDAY1_FG_COLOR = new Color(0xFFFF00);

    public final Color DEFAULT_FERT_BG_COLOR = new Color(0x52B852);

    public final Color DEFAULT_FERT_FG_COLOR = new Color(0xFFFF00);

    public final Color DEFAULT_PFERT_BG_COLOR = new Color(0x1FE01F);

    public final Color DEFAULT_PFERT_FG_COLOR = new Color(0xFFFF00);

    public final Color DEFAULT_PREG_BG_COLOR = new Color(0xB1C4E9);

    public final Color DEFAULT_PREG_FG_COLOR = new Color(0x000000);

    public final Color DEFAULT_BIRTH_BG_COLOR = new Color(0x97189E);

    public final Color DEFAULT_BIRTH_FG_COLOR = new Color(0xFFFF00);

    public final Color DEFAULT_PBIRTH_BG_COLOR = new Color(0xDD0FEA);

    public final Color DEFAULT_PBIRTH_FG_COLOR = new Color(0xFFFF00);

    private JFrame frame;

    private Container pane;

    // master current date storage
    private Date calCurrentDate = null;

    // the current calendar starting date
    private Date calCurrentStartDate = null;

    // the currently selected note date
    private Date calCurrentNoteDate = null;

    private Action actionNew, actionOpen, actionImport, actionExport,
	    actionPreferences, actionExit, actionNextMonth, actionPrevMonth,
	    actionNextYear, actiomPrevYear, actionToday, actionAddMenstruation,
	    actionRecordPregnancy, actionRecordPregInt, actionRecordBirth,
	    actionDeleteRecord, actionShowStatistics, actionAddNote,
	    actionRemoveNote, actionContent, actionAbout, actionLicense;

    // these two actions extend core functionality
    private ActionSave actionSave;

    private ActionSaveAs actionSaveAs;

    // main panel components

    // the week day names taken from locale
    private String[] dayNames;

    // the month names taken from locale
    private final String[] monthNames = {
	    Messages.getString("MainWindow.202"), Messages.getString("MainWindow.203"), Messages.getString("MainWindow.204"), Messages.getString("MainWindow.205"), Messages.getString("MainWindow.206"), Messages.getString("MainWindow.207"), Messages.getString("MainWindow.208"), Messages.getString("MainWindow.209"), Messages.getString("MainWindow.210"), Messages.getString("MainWindow.211"), Messages.getString("MainWindow.212"), Messages.getString("MainWindow.213") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$

    // This structure represents the simple to process information for the
    // calendar cell renderer
    private class DayRenderInfo {
	public DayRenderInfo(final String label, final Color FGColor,
		final Color BGColor, boolean showIcon) {
	    this.label = label;
	    this.FGColor = FGColor;
	    this.BGColor = BGColor;
	    this.showIcon = showIcon;
	}

	public String label;

	public Color FGColor;

	public Color BGColor;

	public boolean showIcon; // Show notes availability icon

	public String toString() {
	    return label;
	}
    }

    // month day number being displayed in the calendar i.e. (29, 30, 1, 2, ...)
    // this array cover the current month only
    private Vector<DayRenderInfo> calendarDays = new Vector<DayRenderInfo>();

    private JTable calendarTable = null;

    private JPanel bottomPanel = null;
    
    private JPopupMenu calendarPopupMenu = null;

    private boolean jumping = false; // true during selection change in
					// jumpToDate

    private int target_day_pos = 0;

    private JScrollPane calendarScrollPane;

    private final JLabel legendLabels[] = {
	    new JLabel(Messages.getString("MainWindow.0")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.1")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.2")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.3")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.4")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.5")), //$NON-NLS-1$
	    new JLabel(Messages.getString("MainWindow.6")) }; //$NON-NLS-1$

    public final Color defaultFGColors[] = { DEFAULT_DAY1_FG_COLOR,
	    DEFAULT_PDAY1_FG_COLOR, DEFAULT_FERT_FG_COLOR,
	    DEFAULT_PFERT_FG_COLOR, DEFAULT_PREG_FG_COLOR,
	    DEFAULT_BIRTH_FG_COLOR, DEFAULT_PBIRTH_FG_COLOR };

    public final Color defaultBGColors[] = { DEFAULT_DAY1_BG_COLOR,
	    DEFAULT_PDAY1_BG_COLOR, DEFAULT_FERT_BG_COLOR,
	    DEFAULT_PFERT_BG_COLOR, DEFAULT_PREG_BG_COLOR,
	    DEFAULT_BIRTH_BG_COLOR, DEFAULT_PBIRTH_BG_COLOR };

    public Color[] legendFGColors = new Color[7];

    public Color[] legendBGColors = new Color[7];

    // these are constants for looking up the day colors in the corresponding
    // arrays
    private final int IDX_DAY1 = 0;

    private final int IDX_P_DAY1 = 1;

    private final int IDX_FERT = 2;

    private final int IDX_P_FERT = 3;

    private final int IDX_PREG = 4;

    private final int IDX_BIRTH = 5;

    private final int IDX_P_BIRTH = 6;

    // toolbar components
    private JLabel toolbarMonthLabel;

    private JLabel toolbarYearLabel;

    // right panel components
    private JLabel dayInfoLabelDate = new JLabel(""); //$NON-NLS-1$

    private JLabel dayInfoLabels[] = { new JLabel(""), //$NON-NLS-1$
	    new JLabel(""), //$NON-NLS-1$
	    new JLabel(""), //$NON-NLS-1$
	    new JLabel(""), //$NON-NLS-1$
	    new JLabel(""), //$NON-NLS-1$
	    new JLabel("") //$NON-NLS-1$
    };

    private JLabel predictionLabel = new JLabel(""); //$NON-NLS-1$

    // bottom panel components
    private DefaultListModel notesListModel;

    private JList notesDatesList;

    private JTextArea notesTextArea;

    private JSplitPane notesSplitPane;

    // status bar components
    JLabel timerStatusBar = null;

    JLabel statisticsStatusBar = null;

    // the statistics dialog
    PCalStatisticsDialog statisticsDialog = null;

    // the preferences dialog
    PCalPrefDialog prefsDialog = null;

    // Help Window
    PCalHelpWindow helpDialog = null;

    /**
     * Constructor creates the main window and show it immediately
     * 
     */
    public MainWindow() {
	// Schedule a job for the event-dispatching thread:
	// creating and showing this application's GUI.
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		createAndShowGUI(PCalendar.preloadFile);
	    }
	});
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private void createAndShowGUI(String preloadFile) {
	initializeActions();

	// Create and set up the window.
	frame = new JFrame(Messages.getString("MainWindow.15")); //$NON-NLS-1$
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	// Border layout is needed to make toolbar draggable
	// frame.setLayout(new BorderLayout());
	pane = frame.getContentPane();
	pane.setLayout(new BorderLayout());
	frame.setResizable(false);

	// set the frame image
	ImageIcon ii = createResoruceIcon("pcalendar.png"); //$NON-NLS-1$
	if (ii != null) {
	    frame.setIconImage(ii.getImage());
	}

	// Get the locale-specific values
	Locale locale = Locale.getDefault();
	DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
	Calendar calendar = java.util.Calendar.getInstance(locale);
	int firstDayOfWeek = calendar.getFirstDayOfWeek();

	// get the locale day of week names
	String[] originalDayNames = dateFormatSymbols.getShortWeekdays();
	dayNames = new String[originalDayNames.length - 1];
	System.arraycopy(originalDayNames, firstDayOfWeek, dayNames, 0,
		dayNames.length - (firstDayOfWeek - 1));
	if (firstDayOfWeek != 1) {
	    System.arraycopy(originalDayNames, 1, dayNames, dayNames.length
		    - (firstDayOfWeek - 1), firstDayOfWeek - 1);
	}

	createMainPanel();

	buildMenuAndToolbar();

	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		actionExit.actionPerformed(null);
	    }
	});

	// initiate the timer
//	new Timer(5000, new ActionListener() {
//	    public void actionPerformed(ActionEvent evt) {
//		timerStatusBar.setText(DateFormat.getDateTimeInstance(
//			DateFormat.LONG, DateFormat.SHORT).format(new Date()));
//	    }
//	}).start();

	// Refresh (actually initialize everything)
	refreshLegend();
	Preferences windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
	if (preloadFile.isEmpty()) {
	    // check if the default file to load
	    preloadFile = windowPrefsNode.get("AutoLoadFile", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}
	if (!preloadFile.isEmpty()) {
	    loadFileHandler(new File(preloadFile));
	}

	updateWindowTitle();

	jumpToDate(null, true);
	refreshNotes();
//	refreshStatistics();

	// Display the window.
	frame.pack();
	resizeComponents();
	readSettings();
	frame.setVisible(true);

	if (windowPrefsNode.get("ShowDisclaimer", "Yes").equals("Yes")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	    showDisclaimer();
	    windowPrefsNode.put("ShowDisclaimer", "No"); //$NON-NLS-1$ //$NON-NLS-2$
	}
    }

    private void showDisclaimer() {
	JOptionPane.showMessageDialog(frame,
		Messages.getString("MainWindow.7"), //$NON-NLS-1$
		Messages.getString("MainWindow.8"), //$NON-NLS-1$
		JOptionPane.WARNING_MESSAGE);
    }

    private void createMainPanel() {
	// define the sub-panels
	JPanel masterPanel = new JPanel(new GridBagLayout());
	// JPanel leftPanel = new JPanel(new BorderLayout());
	// leftPanel.setBorder(BorderFactory.createCompoundBorder(
	// BorderFactory.createTitledBorder(Messages.getString("MainWindow.16")),
	// //$NON-NLS-1$
	// BorderFactory.createEmptyBorder(2,2,2,2)));
	// JPanel rightPanel = new JPanel(new GridBagLayout());
	// rightPanel.setBorder(BorderFactory.createCompoundBorder(
	// BorderFactory.createTitledBorder(Messages.getString("MainWindow.17")),
	// //$NON-NLS-1$
	// BorderFactory.createEmptyBorder(2,2,2,2)));
	bottomPanel = new JPanel(new BorderLayout());
	bottomPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
		.createTitledBorder(Messages.getString("MainWindow.18")), //$NON-NLS-1$
		BorderFactory.createEmptyBorder(2, 2, 2, 2)));

	// Creating Calendar frame
	calendarTable = new JTable(new AbstractTableModel() {
	    private static final long serialVersionUID = 0L;

	    public String getColumnName(int col) {
		return dayNames[col].toString();
	    }

	    public int getRowCount() {
		return calendarDays.size() == 0 ? 0 : (calendarDays.size() / 7);
	    }

	    public int getColumnCount() {
		return 7;
	    }

	    public Object getValueAt(int row, int col) {
		int offset = row * 7 + col;
		return (calendarDays.size() <= offset) ? "" : calendarDays.get(offset); //$NON-NLS-1$
	    }

	    public boolean isCellEditable(int row, int col) {
		return false;
	    }

	    public void setValueAt(Object value, int row, int col) {
		fireTableCellUpdated(row, col);
	    }

	    public Class<?> getColumnClass(int c) {
		return DayRenderInfo.class;
	    }
	});

	calendarTable.setDefaultRenderer(DayRenderInfo.class,
		new TableCellRenderer() {
		    public Component getTableCellRendererComponent(
			    JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int row, int column) {
			if (!(value instanceof DayRenderInfo))
			    return null;
			DayRenderInfo dinfo = (DayRenderInfo) value;
			JLabel label;
			if (dinfo.showIcon) {
			    label = new JLabel(
				    dinfo.label,
				    createResoruceIcon("12x12/stock_attach.png"), //$NON-NLS-1$
				    JLabel.TRAILING);
			} else {
			    label = new JLabel(dinfo.label, JLabel.TRAILING);
			}
			label.setOpaque(true);
			if (isSelected) {
			    label.setBorder(BorderFactory.createMatteBorder(1,
				    1, 1, 1, dinfo.FGColor));
			}
			label.setBackground(dinfo.BGColor);
			label.setForeground(dinfo.FGColor);
			return label;
		    }
		});

	calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	calendarTable.setRowSelectionAllowed(false);
	calendarTable.setCellSelectionEnabled(true);
	// calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	calendarTable.setRowHeight(45);
	calendarTable.getTableHeader().setReorderingAllowed(false);
	calendarTable.getTableHeader().setResizingAllowed(false);
	calendarTable.getTableHeader().setFont(new Font("Dialog", 0, 17));
//	calendarTable.setShowGrid(false); 
	ListSelectionListener listener = new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {
		// Ignore extra messages.
		if (e.getValueIsAdjusting())
		    return;
		if (jumping)
		    return;
		int srow = calendarTable.getSelectedRow();
		int scol = calendarTable.getSelectedColumn();
		if ((srow < 0) || (scol < 0))
		    return;
		int selection_pos = srow * 7 + scol;

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(calCurrentStartDate);
		calendar.add(Calendar.DATE, selection_pos);
		jumpToDate(calendar.getTime(), false);
	    }
	};
	calendarTable.getSelectionModel().addListSelectionListener(listener);
	calendarTable.getColumnModel().getSelectionModel()
		.addListSelectionListener(listener);

	// set up the calendar popup menu
	calendarPopupMenu = new JPopupMenu();
	calendarPopupMenu.add(actionAddMenstruation);
	// calendarPopupMenu.add(actionRecordPregnancy);
	// calendarPopupMenu.add(actionRecordBirth);
	// calendarPopupMenu.add(actionRecordPregInt);
	calendarPopupMenu.addSeparator();
	calendarPopupMenu.add(actionDeleteRecord);

	// set up the calendar key event
	calendarTable.addKeyListener(new KeyListener() {
	    public void keyPressed(KeyEvent e) {
		int delta = 0;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		    delta = -1;
		    break;
		case KeyEvent.VK_KP_LEFT:
		    delta = -1;
		    break;
		case KeyEvent.VK_RIGHT:
		    delta = 1;
		    break;
		case KeyEvent.VK_KP_RIGHT:
		    delta = 1;
		    break;
		case KeyEvent.VK_UP:
		    delta = -7;
		    break;
		case KeyEvent.VK_KP_UP:
		    delta = -7;
		    break;
		case KeyEvent.VK_DOWN:
		    delta = 7;
		    break;
		case KeyEvent.VK_KP_DOWN:
		    delta = 7;
		    break;
		case KeyEvent.VK_PAGE_UP:
		    delta = -30;
		    break;
		case KeyEvent.VK_PAGE_DOWN:
		    delta = 30;
		    break;
		default:
		    return;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(calCurrentDate);

		if (delta == -30) {
		    calendar.add(Calendar.MONTH, -1);
		} else if (delta == 30) {
		    calendar.add(Calendar.MONTH, 1);
		} else {
		    calendar.add(Calendar.DATE, delta);
		}

		jumpToDate(calendar.getTime(), true);
		e.consume();
	    }

	    public void keyReleased(KeyEvent e) {
		// no need to change anything
	    }

	    public void keyTyped(KeyEvent e) {
		// no need to change anything
	    }
	});

	// set up the calendar mouse event
	calendarTable.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    int row = calendarTable.rowAtPoint(e.getPoint());
		    int col = calendarTable.columnAtPoint(e.getPoint());

		    Calendar calendar = new GregorianCalendar();
		    calendar.setTime(calCurrentStartDate);
		    calendar.add(Calendar.DATE, row * 7 + col);

		    jumpToDate(calendar.getTime(), false);
		    
		    if (actionAddMenstruation.isEnabled() == true) {
			actionAddMenstruation.actionPerformed(new ActionEvent(this, -1, ""));
		    } else if (actionDeleteRecord.isEnabled() == true) {
			actionDeleteRecord.actionPerformed(new ActionEvent(this, -1, ""));
		    }
		}
	    }

	    public void mousePressed(MouseEvent e) {
		maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
		maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    int row = calendarTable.rowAtPoint(e.getPoint());
		    int col = calendarTable.columnAtPoint(e.getPoint());

		    Calendar calendar = new GregorianCalendar();
		    calendar.setTime(calCurrentStartDate);
		    calendar.add(Calendar.DATE, row * 7 + col);

		    jumpToDate(calendar.getTime(), false);
		    calendarPopupMenu
			    .show(e.getComponent(), e.getX(), e.getY());
		}
	    }
	});

	calendarScrollPane = new JScrollPane(calendarTable);
	GridBagConstraints c = new GridBagConstraints();
	// c.fill = c.BOTH;
	// leftPanel.add(calendarScrollPane,BorderLayout.CENTER);
	// leftPanel.add(calendarTable, c);

	Preferences windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
	// setup the legend components
	for (int i = 0; i < legendLabels.length; i++) {
	    // legendLabels[i].setOpaque(true);
	    // legendLabels[i].setHorizontalAlignment(JLabel.CENTER);
	    // c = new GridBagConstraints();
	    // switch(i) {
	    // case 0:
	    // case 1: c.gridy = 1; break;
	    // case 2:
	    // case 3: c.gridy = 2; break;
	    // case 4: c.gridy = 3; break;
	    // case 5:
	    // case 6: c.gridy = 4;
	    // }
	    // c.gridx = ((i==1)||(i==3)||(i==6))?2:1;
	    // if(i==4) {
	    // c.gridwidth = 2;
	    // }
	    // c.fill = GridBagConstraints.HORIZONTAL;
	    // c.anchor = GridBagConstraints.NORTHWEST;
	    // c.insets.right = 5;
	    // c.insets.bottom = 5;
	    // leftPanel.add(legendLabels[i], c); //Modified by Morn.Huang
	    Color fgColor = new Color(windowPrefsNode.getInt(
		    "FGColor" + i, defaultFGColors[i].getRGB())); //$NON-NLS-1$
	    Color bgColor = new Color(windowPrefsNode.getInt(
		    "BGColor" + i, defaultBGColors[i].getRGB())); //$NON-NLS-1$
	    // legendLabels[i].setForeground(fgColor);
	    // legendLabels[i].setBackground(bgColor);
	    legendFGColors[i] = fgColor;
	    legendBGColors[i] = bgColor;
	}
	// enforce top left corner
	// c = new GridBagConstraints();
	// c.gridx = 10;
	// c.gridy = 10;
	// c.weightx = 1.0;
	// c.weighty = 1.0;
	// c.fill = GridBagConstraints.BOTH;
	// leftPanel.add(new JLabel(""),c); //$NON-NLS-1$

	// Create right panel
	// JLabel label = new JLabel(Messages.getString("MainWindow.22"));
	// //$NON-NLS-1$
	// c = new GridBagConstraints();
	// c.anchor = GridBagConstraints.WEST;
	// //force left fill
	// c.fill = GridBagConstraints.HORIZONTAL;
	// c.insets.right = 5;
	// rightPanel.add(label, c);
	// c = new GridBagConstraints();
	// c.gridx = 1;
	// c.anchor = GridBagConstraints.WEST;
	// c.weightx = 1.0;
	// c.insets.right = 5;
	// rightPanel.add(dayInfoLabelDate, c);
	//
	// for(int i=0;i<dayInfoLabels.length; i++) {
	// c = new GridBagConstraints();
	// c.gridx = i%2;
	// c.gridy = i/2+1;
	// c.anchor = GridBagConstraints.WEST;
	// c.insets.right = 5;
	// c.fill = GridBagConstraints.HORIZONTAL;
	// rightPanel.add(dayInfoLabels[i], c);
	// }
	//
	// c = new GridBagConstraints();
	// c.gridy = 4;
	// c.anchor = GridBagConstraints.NORTHWEST;
	// c.weighty = 1.0;
	// c.gridwidth = 2;
	// rightPanel.add(predictionLabel, c);

	// create bottom panel
	JToolBar buttonsPanel = new JToolBar();
	buttonsPanel.setOrientation(JToolBar.VERTICAL);
	buttonsPanel.setFloatable(false);
	buttonsPanel.add(actionAddNote);
	buttonsPanel.addSeparator();
	buttonsPanel.add(actionRemoveNote);

	notesTextArea = new JTextArea();
	notesTextArea.setEnabled(false);
	JScrollPane notesScrollPane = new JScrollPane(notesTextArea);
	notesScrollPane
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	notesScrollPane
		.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	notesListModel = new DefaultListModel() {
	    private static final long serialVersionUID = -3900047130891716805L;

	    public Object getElementAt(int index) {
		DateFormat dateFormatter = DateFormat
			.getDateInstance(DateFormat.SHORT);
		return dateFormatter.format(get(index));
	    }
	};
	notesDatesList = new JList(notesListModel);
	notesDatesList.addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
		    return;
		int selectedIndex = notesDatesList.getSelectedIndex();
		if (selectedIndex > -1) {
		    jumpToDate((Date) notesListModel.get(selectedIndex), false);
		}
	    }
	});
	JScrollPane datesListScrollPane = new JScrollPane(notesDatesList);
	datesListScrollPane
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	notesSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		datesListScrollPane, notesScrollPane);
	notesSplitPane.setOneTouchExpandable(true);
	notesSplitPane.setDividerLocation(150);

	Dimension minimumSize = new Dimension(100, 50);
	notesDatesList.setMinimumSize(minimumSize);
	notesTextArea.setMinimumSize(minimumSize);

	bottomPanel.add(buttonsPanel, BorderLayout.LINE_START);
	bottomPanel.add(notesSplitPane, BorderLayout.CENTER);

	// create status bar
	JPanel statusBar = new JPanel(new BorderLayout());
	statisticsStatusBar = new JLabel();
	statisticsStatusBar.setBorder(BorderFactory.createLoweredBevelBorder());
	statusBar.add(statisticsStatusBar, BorderLayout.CENTER);
	timerStatusBar = new JLabel();
	timerStatusBar.setBorder(BorderFactory.createLoweredBevelBorder());
	timerStatusBar.setText(DateFormat.getDateTimeInstance(DateFormat.LONG,
		DateFormat.SHORT).format(new Date()));
	statusBar.add(timerStatusBar, BorderLayout.LINE_END);

	// lay out panels //Modified by Morn.Huang
	c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
	c.weighty = 0.1;
	masterPanel.add(calendarScrollPane, c);
	// c = new GridBagConstraints();
	// c.gridx = 1;
	// c.weightx = 1.0;
	// c.fill = GridBagConstraints.BOTH;
	// masterPanel.add(rightPanel, c);
	c = new GridBagConstraints();
	c.gridy = 1;
	c.fill = GridBagConstraints.BOTH;
	masterPanel.add(bottomPanel, c);

	// c = new GridBagConstraints();
	// c.fill = GridBagConstraints.BOTH;
	pane.add(masterPanel, BorderLayout.CENTER);
	// frame.add(masterPanel);
	// frame.add(statusBar, BorderLayout.PAGE_END);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createResoruceIcon(String imageName) {
	java.net.URL imageURL = MainWindow.class
		.getResource("images/" + imageName); //$NON-NLS-1$
	if (imageURL == null) {
	    return null;
	} else {
	    return new ImageIcon(imageURL);
	}
    }

    /**
     * Create all global actions.
     */
    private void initializeActions() {
	actionNew = new ActionNew();
	actionOpen = new ActionOpen();
	actionSave = new ActionSave();
	actionSaveAs = new ActionSaveAs();
	actionImport = new ActionImport();
	actionExport = new ActionExport();
	actionPreferences = new ActionPreferences();
	actionExit = new ActionExit();
	actionNextMonth = new ActionNextMonth();
	actionPrevMonth = new ActionPrevMonth();
	actionNextYear = new ActionNextYear();
	actiomPrevYear = new ActiomPrevYear();
	actionToday = new ActionToday();
	actionAddMenstruation = new ActionAddMenstruation();
	actionRecordPregnancy = new ActionRecordPregnancy();
	actionRecordPregInt = new ActionRecordPregInt();
	actionRecordBirth = new ActionRecordBirth();
	actionDeleteRecord = new ActionDeleteRecord();
	actionShowStatistics = new ActionShowStatistics();
	actionAddNote = new ActionAddNote();
	actionRemoveNote = new ActionRemoveNote();
	actionContent = new ActionContent();
	actionAbout = new ActionAbout();
	actionLicense = new ActionLicense();
    }

    private void buildMenuAndToolbar() {
	JMenuBar mainMenuBar = new JMenuBar();
	JMenu menu = new JMenu(Messages.getString("MainWindow.23")); //$NON-NLS-1$		
	menu.setMnemonic(Messages.getMnemonic("MainWindow.23.Mnemonic")); //$NON-NLS-1$
	menu.getAccessibleContext().setAccessibleDescription(
		Messages.getString("MainWindow.24")); //$NON-NLS-1$

	JMenuItem menuItem = new JMenuItem(actionNew);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionOpen);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionSave);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionSaveAs);
	menu.add(menuItem);
	// Modified by Morn.Huang
	// menu.addSeparator();
	// menuItem = new JMenuItem(actionImport);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionExport);
	// menu.add(menuItem);
	menu.addSeparator();
	menuItem = new JMenuItem(actionPreferences);
	menu.add(menuItem);
	menu.addSeparator();
	menuItem = new JMenuItem(actionExit);
	menu.add(menuItem);

	mainMenuBar.add(menu);

	// menu = new JMenu(Messages.getString("MainWindow.25")); //$NON-NLS-1$
	// menu.setMnemonic(Messages.getMnemonic("MainWindow.25.Mnemonic"));
	// //$NON-NLS-1$
	// menu.getAccessibleContext().setAccessibleDescription(Messages.getString("MainWindow.26"));
	// //$NON-NLS-1$
	// menuItem = new JMenuItem(actionAddMenstruation);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionRecordPregnancy);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionRecordBirth);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionRecordPregInt);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionDeleteRecord);
	// menu.add(menuItem);
	// menu.addSeparator();
	// menuItem = new JMenuItem(actionShowStatistics);
	// menu.add(menuItem);
	// mainMenuBar.add(menu);
	//
	// menu = new JMenu(Messages.getString("MainWindow.27")); //$NON-NLS-1$
	// menu.setMnemonic(Messages.getMnemonic("MainWindow.27.Mnemonic"));
	// //$NON-NLS-1$
	// menu.getAccessibleContext().setAccessibleDescription(Messages.getString("MainWindow.28"));
	// //$NON-NLS-1$
	// menuItem = new JMenuItem(actionAddNote);
	// menu.add(menuItem);
	// menuItem = new JMenuItem(actionRemoveNote);
	// menu.add(menuItem);
	// mainMenuBar.add(menu);

	menu = new JMenu(Messages.getString("MainWindow.29")); //$NON-NLS-1$
	menu.setMnemonic(Messages.getMnemonic("MainWindow.29.Mnemonic")); //$NON-NLS-1$
	menu.getAccessibleContext().setAccessibleDescription(
		Messages.getString("MainWindow.30")); //$NON-NLS-1$
	menuItem = new JMenuItem(actionToday);
	menu.add(menuItem);
	menu.addSeparator();
	menuItem = new JMenuItem(actionNextMonth);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionPrevMonth);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionNextYear);
	menu.add(menuItem);
	menuItem = new JMenuItem(actiomPrevYear);
	menu.add(menuItem);
	mainMenuBar.add(menu);

	// Help
	menu = new JMenu(Messages.getString("MainWindow.31")); //$NON-NLS-1$
	menu.setMnemonic(Messages.getMnemonic("MainWindow.31.Mnemonic")); //$NON-NLS-1$
	menu.getAccessibleContext().setAccessibleDescription(
		Messages.getString("MainWindow.32")); //$NON-NLS-1$
	menuItem = new JMenuItem(actionContent);
	menu.add(menuItem);
	menuItem = new JMenuItem(actionLicense);
	menu.add(menuItem);
	menu.addSeparator();
	menuItem = new JMenuItem(actionAbout);
	menu.add(menuItem);

	mainMenuBar.add(menu);

	frame.setJMenuBar(mainMenuBar);

	// ----------------------

	JToolBar toolBar = new JToolBar(Messages.getString("MainWindow.33")); //$NON-NLS-1$
	toolBar.setFloatable(false);
	toolBar.add(actionNew);
	toolBar.add(actionOpen);
	toolBar.add(actionSave);
	toolBar.add(actionExit);
	toolBar.addSeparator();
	toolBar.add(actionPrevMonth);
	toolBar.add(Box.createRigidArea(new Dimension(5, 0)));

	toolbarMonthLabel = new JLabel("", JLabel.CENTER); //$NON-NLS-1$
	toolBar.add(toolbarMonthLabel);

	toolBar.add(Box.createRigidArea(new Dimension(5, 0)));
	toolBar.add(actionNextMonth);
	toolBar.addSeparator();
	toolBar.add(actiomPrevYear);
	toolBar.add(Box.createRigidArea(new Dimension(5, 0)));
	toolbarYearLabel = new JLabel("", JLabel.CENTER); //$NON-NLS-1$
	toolBar.add(toolbarYearLabel);
	toolBar.add(Box.createRigidArea(new Dimension(5, 0)));
	toolBar.add(actionNextYear);
	toolBar.addSeparator();
	toolBar.add(actionToday);

	// frame.add(toolBar, BorderLayout.PAGE_START);
	GridBagConstraints c = new GridBagConstraints();
	pane.add(toolBar, BorderLayout.PAGE_START);
    }

    /**
     * Set the preferred sizes for the components to fit the content well
     */
    private void resizeComponents() {
	// resize the main window components

	// Dimension calendarDimension = new
	// Dimension(calendarTable.getPreferredScrollableViewportSize().width,
	// (calendarTable.getRowHeight())*(calendarTable.getRowCount()));
	// calendarTable.getParent().setMinimumSize(calendarTable.getPreferredScrollableViewportSize());
	// calendarTable.setRowHeight(50);
	// calendarTable.getParent().setMinimumSize(new
	// Dimension(calendarTable.getPreferredScrollableViewportSize().width,
	// calendarTable.getPreferredScrollableViewportSize().height));
	// calendarScrollPane.setMinimumSize(new Dimension (calendarDimension));
	// calendarScrollPane.setMinimumSize(calendarTable.getParent().getMinimumSize());
	// calendarScrollPane.setMinimumSize(calendarTable.getPreferredScrollableViewportSize());

	bottomPanel.setMinimumSize(bottomPanel.getPreferredSize());
	calendarScrollPane.setMinimumSize(calendarTable
		.getPreferredSize());
	
	// resize the toolbar components
	// calculate the longest month name width
	int max_month_width = 0;
	FontMetrics fm = toolbarMonthLabel.getGraphics().getFontMetrics();

	for (String monthName : monthNames) {
	    int iwidth = fm.stringWidth(monthName);
	    if (max_month_width < iwidth) {
		max_month_width = iwidth;
	    }
	}
	Dimension dimensiom = new Dimension(max_month_width + 10,
		toolbarMonthLabel.getHeight());
	toolbarMonthLabel.setMaximumSize(dimensiom);
	toolbarMonthLabel.setPreferredSize(dimensiom);
	dimensiom = new Dimension(
		fm.stringWidth("8888") + 10, toolbarYearLabel.getHeight()); //$NON-NLS-1$
	toolbarYearLabel.setMaximumSize(dimensiom);
	toolbarYearLabel.setPreferredSize(dimensiom);
    }

    /**
     * Refresh all main window areas
     */
    public void refreshAll(boolean jumpToToday) {
	refreshStatistics();
	jumpToDate(jumpToToday ? null : calCurrentDate, true);
	refreshNotes();
    }

    /**
     * This method refresh the main window components according to the curerntly
     * selected date. This include: 1. Regenerate calendar grid as needed. 2.
     * Update the current day summary panel 3. Update toolbar month/year labels
     * 4. Select/unselect date notes as needed.
     * 
     * @param new_date -
     *                if null - today date will be used
     */
    private void jumpToDate(Date new_date, boolean force_refresh) {

	if (new_date == null)
	    new_date = new Date();
	Calendar calendar = new GregorianCalendar();
	// normalize to 00:00
	Calendar temp_calendar = new GregorianCalendar();
	temp_calendar.setTime(new_date);
	calendar.clear();
	calendar.set(temp_calendar.get(Calendar.YEAR), temp_calendar
		.get(Calendar.MONTH), temp_calendar.get(Calendar.DAY_OF_MONTH));
	new_date = calendar.getTime();

	boolean need_to_rebuild = false;
	target_day_pos = 0;

	if (force_refresh) {
	    need_to_rebuild = true;
	} else {
	    if (calCurrentDate == null) {
		need_to_rebuild = true;
	    } else {
		Calendar old_calendar = new GregorianCalendar();
		old_calendar.setTime(calCurrentDate);
		need_to_rebuild = (calendar.get(Calendar.MONTH) != old_calendar
			.get(Calendar.MONTH))
			|| (calendar.get(Calendar.YEAR) != old_calendar
				.get(Calendar.YEAR));
	    }
	}

	// refresh the calendar for the current date
	PCalDayInfo target_day_info = refreshCalendar(new_date, calendar,
		need_to_rebuild);

	// fill the toolbar labels
	toolbarMonthLabel.setText(monthNames[calendar.get(Calendar.MONTH)]);
	toolbarYearLabel.setText(new Integer(calendar.get(Calendar.YEAR))
		.toString());

	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
	dayInfoLabelDate.setText(dateFormatter.format(new_date));

	for (JLabel label : dayInfoLabels) {
	    label.setText(""); //$NON-NLS-1$
	}

	if ((target_day_info == null) || (target_day_info.day_num == -1)) {
	    predictionLabel.setText(Messages.getString("MainWindow.38")); //$NON-NLS-1$
	    actionAddMenstruation.setEnabled(true);
	    actionRecordPregnancy.setEnabled(true);
	    actionRecordPregInt.setEnabled(true);
	    actionRecordBirth.setEnabled(true);
	    actionDeleteRecord.setEnabled(false);
	} else {
	    if (target_day_info.birth) {
		dayInfoLabels[0].setText(Messages.getString("MainWindow.39")); //$NON-NLS-1$
	    } else if (target_day_info.pregnancy_interruption) {
		dayInfoLabels[0].setText(Messages.getString("MainWindow.11")); //$NON-NLS-1$
	    } else if (target_day_info.pregnancy) {
		dayInfoLabels[0].setText(Messages.getString("MainWindow.40")); //$NON-NLS-1$
		dayInfoLabels[1].setText(String
			.valueOf(target_day_info.day_num / 7 + 1));
		dayInfoLabels[2].setText(Messages.getString("MainWindow.41")); //$NON-NLS-1$
		// calculate the estimated birthday
		Date bdate = PCalendar.engine.getPredictedBirthDay(new_date,
			target_day_info.day_num);
		dayInfoLabels[3].setText(dateFormatter.format(bdate));
	    } else {
		// this is a regular day
		dayInfoLabels[0].setText(Messages.getString("MainWindow.42")); //$NON-NLS-1$
		dayInfoLabels[1].setText(String
			.valueOf(target_day_info.day_num + 1));
		dayInfoLabels[2].setText(Messages.getString("MainWindow.43")); //$NON-NLS-1$
		dayInfoLabels[3]
			.setText(target_day_info.fertile ? Messages
				.getString("MainWindow.44") : Messages.getString("MainWindow.45")); //$NON-NLS-1$ //$NON-NLS-2$
		dayInfoLabels[4].setText(Messages.getString("MainWindow.46")); //$NON-NLS-1$
		dayInfoLabels[5]
			.setText(target_day_info.badFeel ? Messages
				.getString("MainWindow.47") : Messages.getString("MainWindow.48")); //$NON-NLS-1$ //$NON-NLS-2$
	    }

	    predictionLabel.setText(target_day_info.estimate ? Messages
		    .getString("MainWindow.49") : ""); //$NON-NLS-1$ //$NON-NLS-2$

	    boolean recordActionsEnabled = ((target_day_info.day_num != 0)
		    && !target_day_info.birth && !target_day_info.pregnancy_interruption)
		    || target_day_info.estimate;
	    actionAddMenstruation.setEnabled(recordActionsEnabled);
	    actionRecordPregnancy.setEnabled(recordActionsEnabled);
	    actionRecordBirth.setEnabled(recordActionsEnabled);
	    actionRecordPregInt.setEnabled(recordActionsEnabled);
	    actionDeleteRecord.setEnabled(!recordActionsEnabled);
	}

	calCurrentDate = new_date;
	((AbstractTableModel) calendarTable.getModel()).fireTableDataChanged();
	jumping = true;

	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		calendarTable.changeSelection(target_day_pos / 7,
			target_day_pos % 7, false, false);
		jumping = false;
		selectDateNote(calCurrentDate);
	    }
	});

	frame.validate();
    }

    /**
     * This is an internal method which refresh the calendar widget
     * 
     * @param new_date -
     *                the target date to be shown
     * @param calendar -
     *                the target date in a form of calendar
     * @param need_to_rebuild -
     *                if true - the calendar needs to be rebuilt
     * @return - the day information record for the target date
     */
    private PCalDayInfo refreshCalendar(Date new_date, Calendar calendar,
	    boolean need_to_rebuild) {
	PCalDayInfo target_day_info = null;
	if (!need_to_rebuild) {
	    // this is the same month - just set a new position
	    target_day_pos = Engine.dateDiff(calCurrentStartDate, new_date);
	    target_day_info = PCalendar.engine.getDayInfo(calendar.getTime());
	} else {
	    calendarDays.clear();

	    int next_month = calendar.get(Calendar.MONTH) + 1;
	    if (next_month == 12)
		next_month = 0;

	    // find the first day of week which contains the <Month> 1 date
	    int firstDayOfWeek = calendar.getFirstDayOfWeek();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    while (calendar.get(Calendar.DAY_OF_WEEK) != firstDayOfWeek) {
		calendar.add(Calendar.DATE, -1);
	    }

	    calCurrentStartDate = calendar.getTime();

	    boolean neighbor_month = true;

	    while (!((calendar.get(Calendar.DAY_OF_WEEK) == firstDayOfWeek) && (calendar
		    .get(Calendar.MONTH) == next_month))) {
		int current_day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
		if (current_day_of_month == 1)
		    neighbor_month = !neighbor_month;

		if (neighbor_month) {
		    // this is preceding or succeeding month dates
		    // paint in grey
		    calendarDays.add(new DayRenderInfo(String
			    .valueOf(current_day_of_month),
			    DEFAULT_INACTIVE_MONTH_FG_COLOR,
			    DEFAULT_INACTIVE_MONTH_BG_COLOR, PCalendar.engine
				    .existsDateNote(calendar.getTime())));
		} else {
		    // this is a curent month date
		    // show in all colors
		    PCalDayInfo current_day_info = PCalendar.engine
			    .getDayInfo(calendar.getTime());
		    if (calendar.getTime().equals(new_date)) {
			target_day_info = current_day_info;
			target_day_pos = calendarDays.size();
		    }

		    Color dateBGColor = DEFAULT_UNDEFINED_MONTH_BG_COLOR;
		    Color dateFGColor = DEFAULT_UNDEFINED_MONTH_FG_COLOR;

		    if (current_day_info != null) {
			if (current_day_info.birth) {
			    if (current_day_info.estimate) {
				dateFGColor = legendFGColors[IDX_P_BIRTH];
				dateBGColor = legendBGColors[IDX_P_BIRTH];
			    } else {
				dateFGColor = legendFGColors[IDX_BIRTH];
				dateBGColor = legendBGColors[IDX_BIRTH];
			    }
			} else if (current_day_info.pregnancy
				|| current_day_info.pregnancy_interruption) {
			    dateFGColor = legendFGColors[IDX_PREG];
			    dateBGColor = legendBGColors[IDX_PREG];
			} else if (current_day_info.day_num == 0) {
			    if (current_day_info.estimate) {
				dateFGColor = legendFGColors[IDX_P_DAY1];
				dateBGColor = legendBGColors[IDX_P_DAY1];
			    } else {
				dateFGColor = legendFGColors[IDX_DAY1];
				dateBGColor = legendBGColors[IDX_DAY1];
			    }
			} else if (current_day_info.fertile) {
			    if (current_day_info.estimate) {
				dateFGColor = legendFGColors[IDX_P_FERT];
				dateBGColor = legendBGColors[IDX_P_FERT];
			    } else {
				dateFGColor = legendFGColors[IDX_FERT];
				dateBGColor = legendBGColors[IDX_FERT];
			    }
			}
		    }

		    calendarDays.add(new DayRenderInfo(String
			    .valueOf(current_day_of_month), dateFGColor,
			    dateBGColor, PCalendar.engine
				    .existsDateNote(calendar.getTime())));
		}
		calendar.add(Calendar.DATE, 1);
	    }

	    calendar.setTime(new_date);
	}
	return target_day_info;
    }

    /**
     * This method reloads the notes from the engine. If the current calendar
     * date matches some note from the list, such note is highlighted
     */
    private void refreshNotes() {
	TreeSet<Date> notes_dates = new TreeSet<Date>(PCalendar.engine
		.getNoteDates());
	notesListModel.clear();

	for (Date date : notes_dates) {
	    notesListModel.addElement(date);
	}
	selectDateNote(calCurrentDate);
    }

    /**
     * Select the note in the list and show in text panel for editing
     * 
     * @param note_date -
     *                the target date
     */
    private void selectDateNote(final Date note_date) {
	if (note_date == null) {
	    calCurrentNoteDate = null;
	    return;
	}
	if (calCurrentNoteDate != null) {
	    if (calCurrentNoteDate.equals(note_date)) {
		return;
	    }
	    flushNotesPage();
	}
	// load the new note
	int notePos = notesListModel.indexOf(note_date);
	if (notePos == -1) {
	    // no note found for the proposed date unselect everything in the
	    // list and show the empty disabled edit pane
	    notesDatesList.setValueIsAdjusting(true);
	    notesDatesList.clearSelection();
	    notesDatesList.setValueIsAdjusting(false);
	    notesTextArea.setText(""); //$NON-NLS-1$
	    notesTextArea.setEnabled(false);
	    calCurrentNoteDate = null;
	    actionAddNote.setEnabled(true);
	    actionRemoveNote.setEnabled(false);
	} else {
	    notesDatesList.setValueIsAdjusting(true);
	    notesDatesList.setSelectedIndex(notePos);
	    notesDatesList.setValueIsAdjusting(false);
	    String noteText = PCalendar.engine.getDateNote(note_date);
	    if (noteText == null) {
		noteText = ""; //$NON-NLS-1$
	    }
	    notesTextArea.setText(noteText);
	    notesTextArea.setEnabled(true);
	    calCurrentNoteDate = note_date;
	    actionAddNote.setEnabled(false);
	    actionRemoveNote.setEnabled(true);
	}
    }

    /**
     * This method refreshes the statistics status bar
     */
    private void refreshStatistics() {
//	int periodsCount = PCalendar.engine.getPeriodsCount();
//	int minLength = PCalendar.engine.getMinLength();
//	int avgLength = PCalendar.engine.getAvgLength();
//	int maxLength = PCalendar.engine.getMaxLength();
//	String accString = "<font color=%s>%s</font>"; //$NON-NLS-1$
//
//	switch (PCalendar.engine.getCalMethodAccuracy()) {
//	case 0: {
//	    accString = String.format(accString,
//		    "#046700", Messages.getString("MainWindow.55")); //$NON-NLS-1$ //$NON-NLS-2$
//	    break;
//	}
//	case 1: {
//	    accString = String.format(accString,
//		    "#83A110", Messages.getString("MainWindow.57")); //$NON-NLS-1$ //$NON-NLS-2$
//	    break;
//	}
//	case 2: {
//	    accString = String.format(accString,
//		    "#C10A1D", Messages.getString("MainWindow.59")); //$NON-NLS-1$ //$NON-NLS-2$
//	    break;
//	}
//	default: {
//	    accString = String.format(accString,
//		    "#000000", Messages.getString("MainWindow.61")); //$NON-NLS-1$ //$NON-NLS-2$
//	}
//	}
//
//	statisticsStatusBar.setText(String.format(Messages
//		.getString("MainWindow.62"), //$NON-NLS-1$
//		periodsCount, minLength, avgLength, maxLength, accString));
    }

    /**
     * This method makes the legend pane to reload colors from the application
     * settings
     */
    public void refreshLegend() {
	for (int i = 0; i < legendLabels.length; i++) {
	    legendLabels[i].setBackground(legendBGColors[i]);
	    legendLabels[i].setForeground(legendFGColors[i]);
	}
    }

    /**
     * Turn the main window cursor busy/idle
     * 
     * @param busy
     */
    public void setBusyCursor(boolean busy) {
	if (busy) {
	    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	} else {
	    frame.setCursor(null);
	}
    }

    /**
     * Load the stored main window settings
     */
    private void readSettings() {
	Preferences windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
	Rectangle bounds = new Rectangle();
	bounds.width = windowPrefsNode.getInt("width", 640); //$NON-NLS-1$
	bounds.height = windowPrefsNode.getInt("height", 380); //$NON-NLS-1$
	bounds.x = windowPrefsNode.getInt("posX", 20); //$NON-NLS-1$
	bounds.y = windowPrefsNode.getInt("posY", 20); //$NON-NLS-1$
	frame.setBounds(bounds);

	notesSplitPane.setDividerLocation(windowPrefsNode.getInt(
		"notesSplit", 150)); //$NON-NLS-1$
    }

    /**
     * Store the main window settings
     */
    private void writeSettings() {
	Preferences windowPrefsNode = PCalendar.settings.node("MainWindow"); //$NON-NLS-1$
	Rectangle bounds = frame.getBounds();
	windowPrefsNode.putInt("width", bounds.width); //$NON-NLS-1$
	windowPrefsNode.putInt("height", bounds.height); //$NON-NLS-1$
	windowPrefsNode.putInt("posX", bounds.x); //$NON-NLS-1$
	windowPrefsNode.putInt("posY", bounds.y); //$NON-NLS-1$
	windowPrefsNode.putInt(
		"notesSplit", notesSplitPane.getDividerLocation()); //$NON-NLS-1$
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
	return frame;
    }

    private boolean canCloseFile() {
	// flush the notes pane if active
	flushNotesPage();
	if (!PCalendar.engine.isModified())
	    return true;
	int q_res = JOptionPane.showConfirmDialog(frame, Messages
		.getString("MainWindow.75"), //$NON-NLS-1$
		Messages.getString("MainWindow.76"), //$NON-NLS-1$
		JOptionPane.YES_NO_CANCEL_OPTION);
	if (q_res == JOptionPane.YES_OPTION) {
	    return actionSave.saveActionCore(null);
	}
	return q_res != JOptionPane.CANCEL_OPTION;
    }

    public boolean saveToFileHandler(final File file, final char[] password) {
	boolean saved = false;

	setBusyCursor(true);
	try {
	    saved = true;
	    PCalendar.engine.saveToFile(file, password);
	    updateWindowTitle();
	} catch (ParserConfigurationException e1) {
	    JOptionPane.showMessageDialog(frame, Messages
		    .getString("MainWindow.77") + e1.getMessage(), //$NON-NLS-1$
		    Messages.getString("MainWindow.78"), //$NON-NLS-1$
		    JOptionPane.ERROR_MESSAGE);
	    saved = false;
	} catch (IOException e1) {
	    JOptionPane.showMessageDialog(frame, Messages
		    .getString("MainWindow.79") + e1.getMessage(), //$NON-NLS-1$
		    Messages.getString("MainWindow.80"), //$NON-NLS-1$
		    JOptionPane.ERROR_MESSAGE);
	    saved = false;
	}
	setBusyCursor(false);
	return saved;
    }

    public class ActionNew extends AbstractAction {
	private static final long serialVersionUID = 70609933524514939L;

	public ActionNew() {
	    super(
		    Messages.getString("MainWindow.81"), createResoruceIcon("16x16/new.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.83")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.81.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,
		    ActionEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    if (canCloseFile()) {
		PCalendar.engine.clear();
		updateWindowTitle();
		calCurrentNoteDate = null;
		refreshAll(true);
	    }
	}
    }

    public class ActionOpen extends AbstractAction {
	private static final long serialVersionUID = 6094692722720837140L;

	public ActionOpen() {
	    super(
		    Messages.getString("MainWindow.84"), createResoruceIcon("16x16/open.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.86")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.84.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,
		    ActionEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    if (canCloseFile()) {
		// the last folder visited for open is stored in the preferences
		Preferences dialogsNode = PCalendar.settings.node("Dialogs"); //$NON-NLS-1$
		String lastOpenFolder = dialogsNode.get(
			"last file open folder", ""); //$NON-NLS-1$ //$NON-NLS-2$

		final JFileChooser fc = new JFileChooser(lastOpenFolder);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			Messages.getString("MainWindow.90"), PCAL_EXT, PCAL_ENC_EXT); //$NON-NLS-1$
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    dialogsNode
			    .put(
				    "last file open folder", fc.getSelectedFile().getParent()); //$NON-NLS-1$
		    loadFileHandler(fc.getSelectedFile());
		}
		refreshAll(true);
	    }
	}
    }

    /**
     * Just a shorter wrapper for the loading file handler
     * 
     * @param selectedFile
     */
    private void loadFileHandler(final File selectedFile) {
	loadFileHandler(selectedFile, null);
    }

    /**
     * This method is responsible for the actual file loading
     * 
     * @param selectedFile
     * @param password -
     *                a password to be used for the file decryption. Can be null
     *                or empty for unencrypted files.
     */
    private void loadFileHandler(final File selectedFile, char[] password) {
	setBusyCursor(true);
	try {
	    PCalendar.engine.loadFromFile(selectedFile, password);
	    updateWindowTitle();
	} catch (SAXException e1) {
	    JOptionPane.showMessageDialog(frame, Messages
		    .getString("MainWindow.93") + e1.getMessage(), //$NON-NLS-1$
		    Messages.getString("MainWindow.94"), //$NON-NLS-1$
		    JOptionPane.ERROR_MESSAGE);
	} catch (IOException e1) {
	    JOptionPane.showMessageDialog(frame, Messages
		    .getString("MainWindow.95") + e1.getMessage(), //$NON-NLS-1$
		    Messages.getString("MainWindow.96"), //$NON-NLS-1$
		    JOptionPane.ERROR_MESSAGE);
	} catch (ParserConfigurationException e1) {
	    JOptionPane.showMessageDialog(frame, Messages
		    .getString("MainWindow.97") + e1.getMessage(), //$NON-NLS-1$
		    Messages.getString("MainWindow.98"), //$NON-NLS-1$
		    JOptionPane.ERROR_MESSAGE);
	} catch (PasswordRequiredException e) {
	    // showPasswordWindow will block the gui unil the password will be
	    // entered
	    char[] pwd = showPasswordWindow();
	    if (pwd != null) {
		if (pwd.length > 0) {
		    loadFileHandler(selectedFile, pwd);
		}
	    }
	}
	setBusyCursor(false);
    }

    /**
     * This method updates the main window title according to the current file
     * name
     */
    private void updateWindowTitle() {
	File f = PCalendar.engine.getWorkFile();
	if (f == null) {
	    // "Periodic Calendar - New File"
	    frame
		    .setTitle(Messages.getString("MainWindow.15") + Messages.getString("MainWindow.216") + Messages.getString("MainWindow.217")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	} else {
	    // "Periodic Calendar - <file name>"
	    frame
		    .setTitle(Messages.getString("MainWindow.15") + Messages.getString("MainWindow.218") + f.getPath()); //$NON-NLS-1$ //$NON-NLS-2$
	}
    }

    public class ActionSave extends AbstractAction {
	private static final long serialVersionUID = 7138103426857065945L;

	public ActionSave() {
	    super(
		    Messages.getString("MainWindow.99"), createResoruceIcon("16x16/filesave.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.101")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.99.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
		    ActionEvent.CTRL_MASK));
	}

	public boolean saveActionCore(ActionEvent e) {
	    flushNotesPage();
	    File currentWorkFile = PCalendar.engine.getWorkFile();
	    if (currentWorkFile != null) {
		// just save a file with current password if any
		return saveToFileHandler(currentWorkFile, null);
	    } else {
		return actionSaveAs.saveAsActionCore(e);
	    }
	}

	public void actionPerformed(ActionEvent e) {
	    saveActionCore(e);
	}
    }

    public class ActionSaveAs extends AbstractAction {
	private static final long serialVersionUID = 4774617007701485665L;

	public ActionSaveAs() {
	    super(
		    Messages.getString("MainWindow.102"), createResoruceIcon("16x16/filesave.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.104")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.102.Mnemonic")); //$NON-NLS-1$
	}

	public boolean saveAsActionCore(ActionEvent e) {
	    // get the current path of the currently open file if none
	    // get the last folder visited for open stored in the preferences

	    boolean saved = false;

	    flushNotesPage();

	    File currentFile = PCalendar.engine.getWorkFile();
	    String lastOpenFolder = ""; //$NON-NLS-1$
	    Preferences dialogsNode = PCalendar.settings.node("Dialogs"); //$NON-NLS-1$

	    if (currentFile != null) {
		lastOpenFolder = currentFile.getParent();
	    } else {
		lastOpenFolder = dialogsNode.get("last file open folder", ""); //$NON-NLS-1$ //$NON-NLS-2$
	    }

	    final JFileChooser fc = new JFileChooser(lastOpenFolder);

	    FileNameExtensionFilter plainFileFilter = new FileNameExtensionFilter(
		    Messages.getString("MainWindow.109") + " (." + PCAL_EXT + ")", PCAL_EXT); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

	    FileNameExtensionFilter encryptedFileFilter = new FileNameExtensionFilter(
		    Messages.getString("MainWindow.110") + " (." + PCAL_ENC_EXT + ")", PCAL_ENC_EXT); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$)

	    fc.addChoosableFileFilter(encryptedFileFilter);
	    fc.addChoosableFileFilter(plainFileFilter);

	    boolean dialogDismissed = false;

	    while (!dialogDismissed) {
		int returnVal = fc.showSaveDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File f = fc.getSelectedFile();
		    char[] password = {}; // remove the password from file by
					    // default

		    dialogsNode.put("last file open folder", f.getParent()); //$NON-NLS-1$

		    if ((fc.getFileFilter() == plainFileFilter)
			    && !f.getName().endsWith("." + PCAL_EXT)) { //$NON-NLS-1$
			f = new File(f.getPath() + "." + PCAL_EXT); //$NON-NLS-1$
		    } else if (fc.getFileFilter() == encryptedFileFilter) {
			password = showPasswordWindow();
			if (password == null) {
			    // user canceled the password entry
			    return saved;
			}
			if (!f.getName().endsWith("." + PCAL_ENC_EXT)) { //$NON-NLS-1$
			    f = new File(f.getPath() + "." + PCAL_ENC_EXT); //$NON-NLS-1$
			}
		    }

		    if (f.exists()) {
			// File %s already exists. Do you want to replace it?
			int q_res = JOptionPane
				.showConfirmDialog(
					frame,
					String
						.format(
							Messages
								.getString("MainWindow.214"), f.getPath()), //$NON-NLS-1$
					Messages.getString("MainWindow.215"), //$NON-NLS-1$
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (q_res == JOptionPane.NO_OPTION) {
			    continue;
			} else if (q_res == JOptionPane.CANCEL_OPTION) {
			    break;
			}
		    }
		    saved = saveToFileHandler(f, password);
		}
		dialogDismissed = true;
	    }
	    return saved;
	}

	public void actionPerformed(ActionEvent e) {
	    saveAsActionCore(e);
	}
    }

    public class ActionImport extends AbstractAction {
	private static final long serialVersionUID = -8320781357955115059L;

	public ActionImport() {
	    super(
		    Messages.getString("MainWindow.186"), createResoruceIcon("16x16/import.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.188")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.186.Mnemonic")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    // the last folder visited for open is stored in the preferences
	    Preferences dialogsNode = PCalendar.settings.node("Dialogs"); //$NON-NLS-1$
	    String lastOpenFolder = dialogsNode
		    .get("last file open folder", ""); //$NON-NLS-1$ //$NON-NLS-2$

	    final JFileChooser fc = new JFileChooser(lastOpenFolder);
	    int returnVal = fc.showOpenDialog(frame);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		dialogsNode
			.put(
				"last file open folder", fc.getSelectedFile().getParent()); //$NON-NLS-1$
		try {
		    PCalendar.engine.importFromFile(fc.getSelectedFile());
		} catch (IOException e1) {
		    JOptionPane
			    .showMessageDialog(
				    frame,
				    Messages.getString("MainWindow.189") + e1.getLocalizedMessage(), //$NON-NLS-1$
				    Messages.getString("MainWindow.190"), //$NON-NLS-1$
				    JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e1) {
		    JOptionPane.showMessageDialog(frame, Messages
			    .getString("MainWindow.191"), //$NON-NLS-1$
			    Messages.getString("MainWindow.192"), //$NON-NLS-1$
			    JOptionPane.ERROR_MESSAGE);
		} finally {
		    refreshAll(true);
		}

	    }
	}
    }

    public class ActionExport extends AbstractAction {
	private static final long serialVersionUID = 8762257051182815236L;

	public ActionExport() {
	    super(
		    Messages.getString("MainWindow.193"), createResoruceIcon("16x16/export.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.195")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.193.Mnemonic")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    // get the last folder visited for open stored in the preferences
	    Preferences dialogsNode = PCalendar.settings.node("Dialogs"); //$NON-NLS-1$
	    String lastOpenFolder = dialogsNode
		    .get("last file open folder", ""); //$NON-NLS-1$ //$NON-NLS-2$

	    final JFileChooser fc = new JFileChooser(lastOpenFolder);
	    int returnVal = fc.showSaveDialog(frame);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		dialogsNode
			.put(
				"last file open folder", fc.getSelectedFile().getParent()); //$NON-NLS-1$
		try {
		    PCalendar.engine.exportToFile(fc.getSelectedFile());
		} catch (IOException e1) {
		    JOptionPane.showMessageDialog(frame, Messages
			    .getString("MainWindow.196") + e1.getMessage(), //$NON-NLS-1$
			    Messages.getString("MainWindow.197"), //$NON-NLS-1$
			    JOptionPane.ERROR_MESSAGE);
		}
	    }
	}
    }

    public class ActionPreferences extends AbstractAction {
	private static final long serialVersionUID = 6062980706660003945L;

	public ActionPreferences() {
	    super(
		    Messages.getString("MainWindow.201"), createResoruceIcon("16x16/preferences.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.200")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.201.Mnemonic")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    if (prefsDialog == null) {
		prefsDialog = new PCalPrefDialog();
	    }
	    prefsDialog.showDialog();
	}
    }

    public class ActionExit extends AbstractAction {
	private static final long serialVersionUID = 4577607875914425290L;

	public ActionExit() {
	    super(
		    Messages.getString("MainWindow.112"), createResoruceIcon("16x16/exit.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.114")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.112.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q,
		    ActionEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    if (canCloseFile()) {
		writeSettings();
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	    }
	}
    }

    public class ActionNextMonth extends AbstractAction {
	private static final long serialVersionUID = -7213636761893581907L;

	public ActionNextMonth() {
	    super(
		    Messages.getString("MainWindow.115"), createResoruceIcon("16x16/forward.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.117")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.115.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M,
		    ActionEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(calCurrentDate);
	    calendar.add(Calendar.MONTH, 1);
	    jumpToDate(calendar.getTime(), true);
	}
    }

    public class ActionPrevMonth extends AbstractAction {
	private static final long serialVersionUID = 2449966282061999064L;

	public ActionPrevMonth() {
	    super(
		    Messages.getString("MainWindow.118"), createResoruceIcon("16x16/backward.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.120")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.118.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(calCurrentDate);
	    calendar.add(Calendar.MONTH, -1);
	    jumpToDate(calendar.getTime(), true);
	}
    }

    public class ActionNextYear extends AbstractAction {
	private static final long serialVersionUID = -1576689620404140834L;

	public ActionNextYear() {
	    super(
		    Messages.getString("MainWindow.121"), createResoruceIcon("16x16/last.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.123")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.121.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		    ActionEvent.CTRL_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(calCurrentDate);
	    calendar.add(Calendar.YEAR, 1);
	    jumpToDate(calendar.getTime(), true);
	}
    }

    public class ActiomPrevYear extends AbstractAction {
	private static final long serialVersionUID = -8882893657100610398L;

	public ActiomPrevYear() {
	    super(
		    Messages.getString("MainWindow.124"), createResoruceIcon("16x16/first.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.126")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.124.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(calCurrentDate);
	    calendar.add(Calendar.YEAR, -1);
	    jumpToDate(calendar.getTime(), true);
	}
    }

    public class ActionToday extends AbstractAction {
	private static final long serialVersionUID = -7542164554291154210L;

	public ActionToday() {
	    super(
		    Messages.getString("MainWindow.127"), createResoruceIcon("16x16/jump-to.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.129")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.127.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    jumpToDate(null, false);
	}
    }

    public class ActionAddMenstruation extends AbstractAction {
	private static final long serialVersionUID = -1428104594702264450L;

	public ActionAddMenstruation() {
	    super(
		    Messages.getString("MainWindow.130"), createResoruceIcon("16x16/add.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.132")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.130.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    PCalendar.engine.addStartDate(calCurrentDate);
	    refreshStatistics();
	    jumpToDate(calCurrentDate, true);
	}
    }

    public class ActionRecordPregnancy extends AbstractAction {
	private static final long serialVersionUID = -1960772136271613819L;

	public ActionRecordPregnancy() {
	    super(
		    Messages.getString("MainWindow.133"), createResoruceIcon("16x16/add.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.135")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.133.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    Date pregnancyStartDate = calCurrentDate;
	    Date lastMensDate = PCalendar.engine.getLastMenstruationDate();
	    if (lastMensDate != null) {

		String lastMensDateString = DateFormat.getDateInstance(
			DateFormat.SHORT).format(lastMensDate);
		Object[] options = {
			lastMensDateString
				+ Messages.getString("MainWindow.136"), //$NON-NLS-1$
			DateFormat.getDateInstance(DateFormat.SHORT).format(
				calCurrentDate)
				+ Messages.getString("MainWindow.137"), //$NON-NLS-1$
			Messages.getString("MainWindow.138") }; //$NON-NLS-1$

		int q_res = JOptionPane
			.showOptionDialog(
				frame,
				Messages.getString("MainWindow.139") + //$NON-NLS-1$
					Messages.getString("MainWindow.140") + lastMensDateString + ".\n" + //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("MainWindow.142"), //$NON-NLS-1$
				Messages.getString("MainWindow.143"), //$NON-NLS-1$
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		switch (q_res) {
		case JOptionPane.YES_OPTION:
		    pregnancyStartDate = lastMensDate;
		    break;
		case JOptionPane.CANCEL_OPTION:
		    return;
		}
	    }

	    PCalendar.engine.addPregnancyDate(pregnancyStartDate);
	    refreshStatistics();
	    jumpToDate(calCurrentDate, true);
	}
    }

    public class ActionRecordBirth extends AbstractAction {
	private static final long serialVersionUID = -7421773784507675941L;

	public ActionRecordBirth() {
	    super(
		    Messages.getString("MainWindow.144"), createResoruceIcon("16x16/add.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.146")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.144.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    PCalendar.engine.addBirthDate(calCurrentDate);
	    refreshStatistics();
	    jumpToDate(calCurrentDate, true);
	}
    }

    public class ActionRecordPregInt extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public ActionRecordPregInt() {
	    super(
		    Messages.getString("MainWindow.141"), createResoruceIcon("16x16/cancel.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.148")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.141.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    PCalendar.engine.addPregnancyInterruptDate(calCurrentDate);
	    refreshStatistics();
	    jumpToDate(calCurrentDate, true);
	}
    }

    public class ActionDeleteRecord extends AbstractAction {
	private static final long serialVersionUID = 6375480347509749765L;

	public ActionDeleteRecord() {
	    super(
		    Messages.getString("MainWindow.147"), createResoruceIcon("16x16/remove.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.149")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.147.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    PCalendar.engine.removeDateRecord(calCurrentDate);
	    refreshStatistics();
	    jumpToDate(calCurrentDate, true);
	}
    }

    public class ActionShowStatistics extends AbstractAction {
	private static final long serialVersionUID = 3403436701466016364L;

	public ActionShowStatistics() {
	    super(
		    Messages.getString("MainWindow.150"), createResoruceIcon("16x16/stats.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.152")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.150.Mnemonic")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    if (statisticsDialog == null) {
		statisticsDialog = new PCalStatisticsDialog();
	    }
	    statisticsDialog.showDialog();
	}
    }

    public class ActionAddNote extends AbstractAction {
	private static final long serialVersionUID = 6111798952805072738L;

	public ActionAddNote() {
	    super(
		    Messages.getString("MainWindow.153"), createResoruceIcon("16x16/add.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.155")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.153.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    if (PCalendar.engine.getDateNote(calCurrentDate) == null) {
		PCalendar.engine.setDateNote(calCurrentDate, ""); //$NON-NLS-1$
	    }
	    jumpToDate(calCurrentDate, true);
	    refreshNotes();
	    notesTextArea.requestFocusInWindow();
	}
    }

    public class ActionRemoveNote extends AbstractAction {
	private static final long serialVersionUID = -4933134107660700206L;

	public ActionRemoveNote() {
	    super(
		    Messages.getString("MainWindow.157"), createResoruceIcon("16x16/remove.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.159")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.157.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R,
		    ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
	}

	public void actionPerformed(ActionEvent e) {
	    if (PCalendar.engine.getDateNote(calCurrentDate) != null) {
		PCalendar.engine.removeDateNote(calCurrentDate);
	    }
	    calCurrentNoteDate = null;
	    jumpToDate(calCurrentDate, true);
	    refreshNotes();
	}
    }

    public class ActionContent extends AbstractAction {
	private static final long serialVersionUID = 8438859177628611109L;

	public ActionContent() {
	    super(
		    Messages.getString("MainWindow.160"), createResoruceIcon("16x16/contents.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.162")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.160.Mnemonic")); //$NON-NLS-1$
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}

	public void actionPerformed(ActionEvent e) {
	    if (helpDialog == null) {
		helpDialog = new PCalHelpWindow();
	    } else {
		helpDialog.setVisible(true);
	    }
	}
    }

    public class ActionAbout extends AbstractAction {
	private static final long serialVersionUID = 4391143612433028422L;

	public ActionAbout() {
	    super(
		    Messages.getString("MainWindow.172"), createResoruceIcon("16x16/pcalendar.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.174")); //$NON-NLS-1$
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.172.Mnemonic")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    final JDialog aboutDialog = new JDialog(frame, Messages
		    .getString("MainWindow.175"), true); //$NON-NLS-1$
	    aboutDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    aboutDialog.setLayout(new GridBagLayout());

	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 1;
	    c.gridheight = 4;
	    c.insets.top = 5;
	    c.insets.left = 5;
	    c.anchor = GridBagConstraints.FIRST_LINE_START;
	    aboutDialog.add(new JLabel(createResoruceIcon("orgbook.png")), c); //$NON-NLS-1$

	    c = new GridBagConstraints();
	    c.gridx = 2;
	    c.gridy = 0;
	    c.weighty = 1.0;
	    c.insets.top = 10;
	    c.insets.bottom = 15;
	    c.insets.right = 10;
	    aboutDialog
		    .add(new JLabel(Messages.getString("MainWindow.176")), c); //$NON-NLS-1$

	    c = new GridBagConstraints();
	    c.gridx = 2;
	    c.gridy = 1;
	    c.insets.top = 5;
	    c.insets.right = 10;
	    aboutDialog
		    .add(
			    new JLabel(
				    Messages.getString("MainWindow.177") + PCalendar.APP_VERSION), c); //$NON-NLS-1$

	    c = new GridBagConstraints();
	    c.gridx = 2;
	    c.gridy = 2;
	    c.insets.top = 5;
	    c.insets.right = 10;
	    aboutDialog
		    .add(new JLabel(Messages.getString("MainWindow.178")), c); //$NON-NLS-1$

	    c = new GridBagConstraints();
	    c.gridx = 2;
	    c.gridy = 3;
	    c.insets.top = 5;
	    c.insets.right = 10;
	    aboutDialog
		    .add(new JLabel(Messages.getString("MainWindow.179")), c); //$NON-NLS-1$

	    JButton b = new JButton(
		    Messages.getString("MainWindow.180"), createResoruceIcon("16x16/ok.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    c = new GridBagConstraints();
	    c.gridx = 1;
	    c.gridy = 4;
	    c.gridwidth = 2;
	    c.insets.top = 15;
	    c.insets.bottom = 10;
	    aboutDialog.add(b, c);
	    JRootPane rootPane = aboutDialog.getRootPane();
	    rootPane.setDefaultButton(b);

	    b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    aboutDialog.setVisible(false);
		    aboutDialog.dispose();
		}
	    });

	    for (ActionListener al : b.getActionListeners()) {
		rootPane.registerKeyboardAction(al, KeyStroke.getKeyStroke(
			KeyEvent.VK_ESCAPE, 0),
			JComponent.WHEN_IN_FOCUSED_WINDOW);
	    }

	    aboutDialog.setResizable(false);
	    aboutDialog.pack();
	    aboutDialog.setLocationRelativeTo(frame);
	    aboutDialog.setVisible(true);
	}
    }

    public class ActionLicense extends AbstractAction {
	private static final long serialVersionUID = -1215691181077118833L;

	public ActionLicense() {
	    super(
		    Messages.getString("MainWindow.181"), createResoruceIcon("16x16/emacs.png")); //$NON-NLS-1$ //$NON-NLS-2$ 
	    putValue(MNEMONIC_KEY, Messages
		    .getMnemonic("MainWindow.181.Mnemonic"));//$NON-NLS-1$
	    putValue(SHORT_DESCRIPTION, Messages.getString("MainWindow.183")); //$NON-NLS-1$
	}

	public void actionPerformed(ActionEvent e) {
	    final JDialog licenseDialog = new JDialog(frame, Messages
		    .getString("MainWindow.184"), true); //$NON-NLS-1$
	    licenseDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    licenseDialog.setLayout(new GridBagLayout());

	    JEditorPane editorPane = new JEditorPane();
	    editorPane.setEditable(false);
	    editorPane.setFocusable(false);
	    java.net.URL helpURL = MainWindow.class
		    .getResource("help/gpl-license.html"); //$NON-NLS-1$
	    if (helpURL != null) {
		try {
		    editorPane.setPage(helpURL);
		} catch (IOException ee) {
		    // this should normally never happen
		    ee.printStackTrace();
		}
	    }

	    // Put the editor pane in a scroll pane.
	    JScrollPane editorScrollPane = new JScrollPane(editorPane);
	    editorScrollPane.setPreferredSize(new Dimension(500, 400));
	    editorScrollPane.setMinimumSize(new Dimension(10, 10));
	    editorScrollPane.setFocusable(true);

	    GridBagConstraints c = new GridBagConstraints();
	    c.insets.top = 5;
	    c.insets.left = 5;
	    c.insets.right = 5;
	    c.insets.bottom = 5;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.CENTER;

	    licenseDialog.add(editorScrollPane, c);

	    JButton b = new JButton(
		    Messages.getString("MainWindow.185"), createResoruceIcon("16x16/ok.png")); //$NON-NLS-1$ //$NON-NLS-2$
	    b.setFocusable(false);
	    c = new GridBagConstraints();
	    c.gridy = 2;
	    c.insets.bottom = 5;
	    c.anchor = GridBagConstraints.CENTER;
	    licenseDialog.add(b, c);
	    licenseDialog.getRootPane().setDefaultButton(b);

	    b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    licenseDialog.setVisible(false);
		    licenseDialog.dispose();
		}
	    });
	    JRootPane rootPane = licenseDialog.getRootPane();
	    for (ActionListener al : b.getActionListeners()) {
		rootPane.registerKeyboardAction(al, KeyStroke.getKeyStroke(
			KeyEvent.VK_ESCAPE, 0),
			JComponent.WHEN_IN_FOCUSED_WINDOW);
	    }
	    licenseDialog.pack();
	    licenseDialog.setLocationRelativeTo(frame);
	    licenseDialog.setVisible(true);
	}
    }

    private void flushNotesPage() {
	if (calCurrentNoteDate != null) {
	    //store the previous note
	    PCalendar.engine.setDateNote(calCurrentNoteDate, notesTextArea
		    .getText());
	}
    }

    /**
     * Show the password entry window.
     * @return password or null if entry was canceled
     */
    private char[] showPasswordWindow() {
	final JPasswordField passwordField = new JPasswordField(10);
	final String questionText = Messages.getString("MainWindow.9"); //$NON-NLS-1$
	final Object[] array = { questionText, passwordField };

	final JOptionPane pane = new JOptionPane(array,
		JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

	final JDialog dialog = pane.createDialog(null, Messages
		.getString("MainWindow.10")); //$NON-NLS-1$

	dialog.addComponentListener(new ComponentAdapter() {
	    public void componentShown(ComponentEvent ce) {
		passwordField.requestFocusInWindow();
	    }
	});

	dialog.pack();
	dialog.setVisible(true);

	Object selectedValue = pane.getValue();
	if (selectedValue != null) {
	    if (selectedValue instanceof Integer) {
		if ((Integer) selectedValue == JOptionPane.OK_OPTION) {
		    return passwordField.getPassword();
		}
	    }
	}
	return null;
    }

}