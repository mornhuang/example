/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.client;

import com.sun.tutorial.javaee.dukesbank.exception.AccountNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.CustomerNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.IllegalAccountTypeException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.request.AccountController;
import com.sun.tutorial.javaee.dukesbank.request.CustomerController;
import com.sun.tutorial.javaee.dukesbank.util.AccountDetails;
import com.sun.tutorial.javaee.dukesbank.util.CustomerDetails;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.DefaultListModel;


/**
 *
 * @author  ian
 */
public class BankAdmin extends javax.swing.JFrame {
    @EJB
    private static CustomerController customerController;
    @EJB
    private static AccountController accountController;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountActionsPanel;
    private javax.swing.JButton accountCancelButton;
    private javax.swing.JButton accountCreateButton;
    private javax.swing.JLabel accountDescriptionLabel;
    private javax.swing.JTextField accountDescriptionTextField;
    private javax.swing.JLabel accountIdLabel;
    private javax.swing.JTextField accountIdTextField;
    private javax.swing.JPanel accountInfoPanel;
    private javax.swing.JButton accountNewButton;
    private javax.swing.JButton accountOpenButton;
    private javax.swing.JButton accountRemoveButton;
    private javax.swing.JComboBox accountTypeComboBox;
    private javax.swing.JLabel accountTypeLabel;
    private javax.swing.JButton addCustomerToAccountButton;
    private javax.swing.JLabel addCustomerToAccountLabel;
    private javax.swing.JTextField addCustomerToAccountTextField;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JLabel beginningBalanceLabel;
    private javax.swing.JTextField beginningBalanceTextField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JLabel creditLabel;
    private javax.swing.JTextField creditTextField;
    private javax.swing.JButton customerCancelButton;
    private javax.swing.JLabel customerIdLabel;
    private javax.swing.JTextField customerIdTextField;
    private javax.swing.JPanel customerInfoPanel;
    private javax.swing.JButton customerNewButton;
    private javax.swing.JButton customerOpenButton;
    private javax.swing.JButton customerRemoveButton;
    private javax.swing.JButton customerSearchButton;
    private javax.swing.JLabel customerSearchLabel;
    private javax.swing.JList customerSearchList;
    private javax.swing.JPanel customerSearchPanel;
    private javax.swing.JScrollPane customerSearchScrollPane;
    private javax.swing.JTextField customerSearchTextField;
    private javax.swing.JButton customerUpdateButton;
    private javax.swing.JLabel customersLabel;
    private javax.swing.JTextField customersTextField;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JLabel messageTextLabel;
    private javax.swing.JLabel middleInitialLabel;
    private javax.swing.JTextField middleInitialTextField;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton removeCustomerFromAccountButton;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JTextField streetTextField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timeTextField;
    private javax.swing.JLabel zipLabel;
    private javax.swing.JTextField zipTextField;
    private boolean isNewAccount;
    private boolean isNewCustomer;

    /** Creates new form BankAdmin */
    public BankAdmin() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        customerInfoPanel = new javax.swing.JPanel();
        customerIdLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        middleInitialLabel = new javax.swing.JLabel();
        streetLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        zipLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        customerIdTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        middleInitialTextField = new javax.swing.JTextField();
        streetTextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        stateTextField = new javax.swing.JTextField();
        zipTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        customerOpenButton = new javax.swing.JButton();
        customerNewButton = new javax.swing.JButton();
        customerUpdateButton = new javax.swing.JButton();
        customerRemoveButton = new javax.swing.JButton();
        customerCancelButton = new javax.swing.JButton();
        customerSearchPanel = new javax.swing.JPanel();
        customerSearchLabel = new javax.swing.JLabel();
        customerSearchTextField = new javax.swing.JTextField();
        customerSearchButton = new javax.swing.JButton();
        customerSearchScrollPane = new javax.swing.JScrollPane();
        customerSearchList = new javax.swing.JList();
        accountInfoPanel = new javax.swing.JPanel();
        accountIdLabel = new javax.swing.JLabel();
        accountDescriptionLabel = new javax.swing.JLabel();
        accountTypeLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        creditLabel = new javax.swing.JLabel();
        beginningBalanceLabel = new javax.swing.JLabel();
        customersLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        accountOpenButton = new javax.swing.JButton();
        accountNewButton = new javax.swing.JButton();
        accountCreateButton = new javax.swing.JButton();
        accountRemoveButton = new javax.swing.JButton();
        accountCancelButton = new javax.swing.JButton();
        accountIdTextField = new javax.swing.JTextField();
        accountDescriptionTextField = new javax.swing.JTextField();
        accountTypeComboBox = new javax.swing.JComboBox();
        balanceTextField = new javax.swing.JTextField();
        creditTextField = new javax.swing.JTextField();
        beginningBalanceTextField = new javax.swing.JTextField();
        customersTextField = new javax.swing.JTextField();
        timeTextField = new javax.swing.JTextField();
        accountActionsPanel = new javax.swing.JPanel();
        addCustomerToAccountButton = new javax.swing.JButton();
        addCustomerToAccountLabel = new javax.swing.JLabel();
        addCustomerToAccountTextField = new javax.swing.JTextField();
        removeCustomerFromAccountButton = new javax.swing.JButton();
        messagePanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        messageTextLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        customerInfoPanel.setFocusCycleRoot(true);
        customerIdLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.RIGHT);
        customerIdLabel.setText("Customer Id:");

        firstNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameLabel.setText("First Name:");

        lastNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel.setText("Last Name:");

        middleInitialLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.RIGHT);
        middleInitialLabel.setText("Middle Initial:");

        streetLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        streetLabel.setText("Street:");

        cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cityLabel.setText("City:");

        stateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stateLabel.setText("State:");

        zipLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        zipLabel.setText("Zip:");

        phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phoneLabel.setText("Phone:");

        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailLabel.setText("Email:");

        firstNameTextField.setEditable(false);

        lastNameTextField.setEditable(false);

        middleInitialTextField.setEditable(false);

        streetTextField.setEditable(false);

        cityTextField.setEditable(false);

        stateTextField.setEditable(false);

        zipTextField.setEditable(false);

        phoneTextField.setEditable(false);

        emailTextField.setEditable(false);

        customerOpenButton.setText("Open");
        customerOpenButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerOpenButtonMouseReleased(evt);
                    }
                });

        customerNewButton.setText("New Customer");
        customerNewButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerNewButtonMouseReleased(evt);
                    }
                });

        customerUpdateButton.setText("Update");
        customerUpdateButton.setEnabled(false);
        customerUpdateButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerUpdateButtonMouseReleased(evt);
                    }
                });

        customerRemoveButton.setText("Remove");
        customerRemoveButton.setEnabled(false);
        customerRemoveButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerRemoveButtonMouseReleased(evt);
                    }
                });

        customerCancelButton.setText("Cancel");
        customerCancelButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerCancelButtonMouseReleased(evt);
                    }
                });

        customerSearchPanel.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        null,
                        "Customer Actions",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Tahoma", 0, 11),
                        new java.awt.Color(0, 0, 0)));
        customerSearchLabel.setText("Search By Last Name:");

        customerSearchButton.setText("Search");
        customerSearchButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        customerSearchButtonMouseReleased(evt);
                    }
                });

        customerSearchList.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        customerSearchList.setEnabled(false);
        customerSearchScrollPane.setViewportView(customerSearchList);

        org.jdesktop.layout.GroupLayout customerSearchPanelLayout = new org.jdesktop.layout.GroupLayout(
                    customerSearchPanel);
        customerSearchPanel.setLayout(customerSearchPanelLayout);
        customerSearchPanelLayout.setHorizontalGroup(
                customerSearchPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        org.jdesktop.layout.GroupLayout.TRAILING,
                        customerSearchPanelLayout.createSequentialGroup().addContainerGap().add(
                                customerSearchPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.TRAILING).add(
                                        org.jdesktop.layout.GroupLayout.LEADING,
                                        customerSearchScrollPane,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        409,
                                        Short.MAX_VALUE).add(
                                        customerSearchPanelLayout.createSequentialGroup().add(
                                                customerSearchLabel).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                customerSearchTextField,
                                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                300,
                                                Short.MAX_VALUE))).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerSearchButton).addContainerGap()));
        customerSearchPanelLayout.setVerticalGroup(
                customerSearchPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        customerSearchPanelLayout.createSequentialGroup().add(
                                11,
                                11,
                                11).add(
                                customerSearchPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        customerSearchLabel).add(
                                        customerSearchTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        customerSearchButton)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerSearchScrollPane,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                87,
                                Short.MAX_VALUE).addContainerGap()));

        org.jdesktop.layout.GroupLayout customerInfoPanelLayout = new org.jdesktop.layout.GroupLayout(
                    customerInfoPanel);
        customerInfoPanel.setLayout(customerInfoPanelLayout);
        customerInfoPanelLayout.setHorizontalGroup(
                customerInfoPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        org.jdesktop.layout.GroupLayout.TRAILING,
                        customerInfoPanelLayout.createSequentialGroup().add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.TRAILING).add(
                                        org.jdesktop.layout.GroupLayout.LEADING,
                                        customerInfoPanelLayout.createSequentialGroup().addContainerGap().add(
                                                customerSearchPanel,
                                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)).add(
                                        customerInfoPanelLayout.createSequentialGroup().add(
                                                10,
                                                10,
                                                10).add(
                                                customerInfoPanelLayout.createParallelGroup(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        false).add(
                                                        middleInitialLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.LEADING,
                                                        lastNameLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        streetLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        cityLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        stateLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        zipLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        phoneLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        emailLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        customerIdLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.LEADING,
                                                        firstNameLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        97,
                                                        Short.MAX_VALUE)).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                customerInfoPanelLayout.createParallelGroup(
                                                        org.jdesktop.layout.GroupLayout.LEADING).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        firstNameTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        customerInfoPanelLayout.createSequentialGroup().addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED,
                                                                91,
                                                                Short.MAX_VALUE).add(
                                                                customerNewButton).addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                                customerUpdateButton).addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                                customerRemoveButton).addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                                customerCancelButton)).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        lastNameTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        streetTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        stateTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        phoneTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        emailTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        zipTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        cityTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        middleInitialTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        415,
                                                        Short.MAX_VALUE).add(
                                                        customerInfoPanelLayout.createSequentialGroup().add(
                                                                customerIdTextField,
                                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                                                85,
                                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                                customerOpenButton))))).addContainerGap()));
        customerInfoPanelLayout.setVerticalGroup(
                customerInfoPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        customerInfoPanelLayout.createSequentialGroup().addContainerGap().add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        customerIdTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        customerOpenButton).add(
                                        customerIdLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        firstNameLabel).add(
                                        firstNameTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        lastNameTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        lastNameLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        middleInitialTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        middleInitialLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        streetTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        streetLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        cityTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        cityLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        stateTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        stateLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        zipTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        zipLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        phoneTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        phoneLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        emailTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        emailLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        customerCancelButton).add(
                                        customerUpdateButton).add(
                                        customerNewButton).add(
                                        customerRemoveButton)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                customerSearchPanel,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE).addContainerGap()));
        jTabbedPane1.addTab("Customer Info", customerInfoPanel);

        accountIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        accountIdLabel.setText("Account Id:");

        accountDescriptionLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.RIGHT);
        accountDescriptionLabel.setText("Account Description:");

        accountTypeLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.RIGHT);
        accountTypeLabel.setText("Account Type:");

        balanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        balanceLabel.setText("Balance:");

        creditLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        creditLabel.setText("Credit:");

        beginningBalanceLabel.setHorizontalAlignment(
                javax.swing.SwingConstants.RIGHT);
        beginningBalanceLabel.setText("Beginning Balance:");

        customersLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        customersLabel.setText("Customers:");

        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLabel.setText("Account Created:");

        accountOpenButton.setText("Open");
        accountOpenButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        accountOpenButtonMouseReleased(evt);
                    }
                });

        accountNewButton.setText("New Account");
        accountNewButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        accountNewButtonMouseReleased(evt);
                    }
                });

        accountCreateButton.setText("Create Account");
        accountCreateButton.setEnabled(false);
        accountCreateButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        accountCreateButtonMouseReleased(evt);
                    }
                });

        accountRemoveButton.setText("Remove");
        accountRemoveButton.setEnabled(false);
        accountRemoveButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        accountRemoveButtonMouseReleased(evt);
                    }
                });

        accountCancelButton.setText("Cancel");
        accountCancelButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        accountCancelButtonMouseReleased(evt);
                    }
                });

        accountDescriptionTextField.setEditable(false);

        accountTypeComboBox.setModel(
                new javax.swing.DefaultComboBoxModel(
                        new String[] {
                            "Savings", "Checking", "Money Market", "Credit"
                        }));
        accountTypeComboBox.setEnabled(false);

        balanceTextField.setEditable(false);

        creditTextField.setEditable(false);

        beginningBalanceTextField.setEditable(false);

        customersTextField.setEditable(false);

        timeTextField.setEditable(false);

        accountActionsPanel.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        null,
                        "Account Actions",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Tahoma", 0, 11),
                        new java.awt.Color(0, 0, 0)));
        addCustomerToAccountButton.setText("Add Customer to Account");
        addCustomerToAccountButton.setEnabled(false);
        addCustomerToAccountButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        addCustomerToAccountButtonMouseReleased(evt);
                    }
                });

        addCustomerToAccountLabel.setText("Customer Id:");

        addCustomerToAccountTextField.setEditable(false);

        removeCustomerFromAccountButton.setText("Remove Customer From Account");
        removeCustomerFromAccountButton.setEnabled(false);
        removeCustomerFromAccountButton.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        removeCustomerFromAccountButtonMouseReleased(evt);
                    }
                });

        org.jdesktop.layout.GroupLayout accountActionsPanelLayout = new org.jdesktop.layout.GroupLayout(
                    accountActionsPanel);
        accountActionsPanel.setLayout(accountActionsPanelLayout);
        accountActionsPanelLayout.setHorizontalGroup(
                accountActionsPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        accountActionsPanelLayout.createSequentialGroup().addContainerGap().add(
                                accountActionsPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.LEADING).add(
                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                        accountActionsPanelLayout.createSequentialGroup().add(
                                                addCustomerToAccountLabel).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                addCustomerToAccountTextField,
                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                                55,
                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED,
                                                203,
                                                Short.MAX_VALUE).add(
                                                addCustomerToAccountButton)).add(
                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                        removeCustomerFromAccountButton)).addContainerGap()));
        accountActionsPanelLayout.setVerticalGroup(
                accountActionsPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        accountActionsPanelLayout.createSequentialGroup().add(
                                accountActionsPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        addCustomerToAccountButton).add(
                                        addCustomerToAccountLabel).add(
                                        addCustomerToAccountTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                removeCustomerFromAccountButton).addContainerGap(
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)));

        org.jdesktop.layout.GroupLayout accountInfoPanelLayout = new org.jdesktop.layout.GroupLayout(
                    accountInfoPanel);
        accountInfoPanel.setLayout(accountInfoPanelLayout);
        accountInfoPanelLayout.setHorizontalGroup(
                accountInfoPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        org.jdesktop.layout.GroupLayout.TRAILING,
                        accountInfoPanelLayout.createSequentialGroup().addContainerGap().add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.TRAILING).add(
                                        org.jdesktop.layout.GroupLayout.LEADING,
                                        accountActionsPanel,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE).add(
                                        org.jdesktop.layout.GroupLayout.LEADING,
                                        accountInfoPanelLayout.createSequentialGroup().add(
                                                accountInfoPanelLayout.createParallelGroup(
                                                        org.jdesktop.layout.GroupLayout.TRAILING,
                                                        false).add(
                                                        accountDescriptionLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        timeLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        customersLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        beginningBalanceLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        creditLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        balanceLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE).add(
                                                        accountTypeLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        99,
                                                        Short.MAX_VALUE).add(
                                                        accountIdLabel,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                accountInfoPanelLayout.createParallelGroup(
                                                        org.jdesktop.layout.GroupLayout.LEADING).add(
                                                        accountTypeComboBox,
                                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                                        accountDescriptionTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        balanceTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        creditTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        beginningBalanceTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        customersTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        timeTextField,
                                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                        413,
                                                        Short.MAX_VALUE).add(
                                                        accountInfoPanelLayout.createSequentialGroup().add(
                                                                accountIdTextField,
                                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                                                82,
                                                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                                accountOpenButton)))).add(
                                        accountInfoPanelLayout.createSequentialGroup().add(
                                                accountNewButton).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                accountCreateButton).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                accountRemoveButton).addPreferredGap(
                                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                                accountCancelButton))).addContainerGap()));
        accountInfoPanelLayout.setVerticalGroup(
                accountInfoPanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        accountInfoPanelLayout.createSequentialGroup().addContainerGap().add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        accountIdTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        accountOpenButton).add(accountIdLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        accountDescriptionLabel).add(
                                        accountDescriptionTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        accountTypeComboBox,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        accountTypeLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        balanceTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        balanceLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        creditTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        creditLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        beginningBalanceTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        beginningBalanceLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        customersTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        customersLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        timeTextField,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                        org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
                                        timeLabel)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountInfoPanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.BASELINE).add(
                                        accountCancelButton).add(
                                        accountCreateButton).add(
                                        accountNewButton).add(
                                        accountRemoveButton)).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED).add(
                                accountActionsPanel,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addContainerGap(
                                133,
                                Short.MAX_VALUE)));
        jTabbedPane1.addTab("AccountInfo", accountInfoPanel);

        messagePanel.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createEtchedBorder(),
                        "Messages",
                        javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Tahoma", 0, 11),
                        new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout messagePanelLayout = new org.jdesktop.layout.GroupLayout(
                    messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
                messagePanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        messagePanelLayout.createSequentialGroup().add(
                                messagePanelLayout.createParallelGroup(
                                        org.jdesktop.layout.GroupLayout.LEADING).add(
                                        messageLabel).add(
                                        messagePanelLayout.createSequentialGroup().add(
                                                10,
                                                10,
                                                10).add(
                                                messageTextLabel,
                                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                                509,
                                                Short.MAX_VALUE))).addContainerGap()));
        messagePanelLayout.setVerticalGroup(
                messagePanelLayout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        messagePanelLayout.createSequentialGroup().add(
                                messageLabel).add(14, 14, 14)).add(
                        messagePanelLayout.createSequentialGroup().add(
                                messageTextLabel,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                23,
                                Short.MAX_VALUE).addContainerGap()));

        fileMenu.setText("File");
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        exitMenuItemActionPerformed(evt);
                    }
                });

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
                    getContentPane());
        getContentPane()
            .setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        jTabbedPane1,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        541,
                        Short.MAX_VALUE).add(
                        messagePanel,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(
                        org.jdesktop.layout.GroupLayout.LEADING).add(
                        layout.createSequentialGroup().add(
                                jTabbedPane1,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                org.jdesktop.layout.LayoutStyle.RELATED,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE).add(
                                messagePanel,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
                                org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
                                org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));
        pack();
    } // </editor-fold>//GEN-END:initComponents

    private void removeCustomerFromAccountButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_removeCustomerFromAccountButtonMouseReleased

        if (this.accountIdTextField.getText() == null) {
            this.messageTextLabel.setText("Enter an account ID.");
        } else {
            try {
                accountController.removeCustomerFromAccount(
                        new Long(this.addCustomerToAccountTextField.getText()),
                        new Long(this.accountIdTextField.getText()));

                List<Long> customerIds = accountController.getCustomerIds(
                            new Long(this.accountIdTextField.getText()));
                this.customersTextField.setText(customerIds.toString());
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            } catch (CustomerNotFoundException ex) {
                this.messageTextLabel.setText("Customer not found.");
            } catch (AccountNotFoundException ex) {
                this.messageTextLabel.setText("Account not found.");
            }
        }
    } //GEN-LAST:event_removeCustomerFromAccountButtonMouseReleased

    private void addCustomerToAccountButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_addCustomerToAccountButtonMouseReleased

        if (this.accountIdTextField.getText() == null) {
            this.messageTextLabel.setText("Enter an account ID.");
        } else {
            try {
                accountController.addCustomerToAccount(
                        new Long(this.addCustomerToAccountTextField.getText()),
                        new Long(this.accountIdTextField.getText()));

                List<Long> customerIds = accountController.getCustomerIds(
                            new Long(this.accountIdTextField.getText()));
                this.customersTextField.setText(customerIds.toString());
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            } catch (CustomerNotFoundException ex) {
                this.messageTextLabel.setText("Customer not found.");
            } catch (AccountNotFoundException ex) {
                this.messageTextLabel.setText("Account not found.");
            }
        }
    } //GEN-LAST:event_addCustomerToAccountButtonMouseReleased

    private void accountCancelButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_accountCancelButtonMouseReleased
        this.clearAccountTextFields();
        this.setAccountTextFields(false);
        this.addCustomerToAccountTextField.setText(null);
        this.addCustomerToAccountButton.setEnabled(false);
        this.removeCustomerFromAccountButton.setEnabled(false);
        this.accountRemoveButton.setEnabled(false);
        this.accountNewButton.setEnabled(true);
        this.accountCreateButton.setEnabled(false);
        this.accountIdTextField.setEditable(true);
        this.accountOpenButton.setEnabled(true);
    } //GEN-LAST:event_accountCancelButtonMouseReleased

    private void accountRemoveButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_accountRemoveButtonMouseReleased

        if (this.accountIdTextField.getText() == null) {
            this.messageTextLabel.setText("Enter an account ID.");
        } else {
            try {
                accountController.removeAccount(
                        new Long(this.accountIdTextField.getText()));
                this.clearAccountTextFields();
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            } catch (AccountNotFoundException ex) {
                this.messageTextLabel.setText("Account not found.");
            }
        }
    } //GEN-LAST:event_accountRemoveButtonMouseReleased

    private void accountCreateButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_accountCreateButtonMouseReleased

        BigDecimal credit = new BigDecimal(0);
        BigDecimal begBalance = new BigDecimal(0);
        List<Long> customerIds = new ArrayList<Long>();

        if (((String) this.accountTypeComboBox.getSelectedItem() == "Credit")
                && (this.creditTextField.getText() != null)) {
            credit = new BigDecimal(this.creditTextField.getText());
        }

        if (((String) this.accountTypeComboBox.getSelectedItem() != "Credit")
                && (this.creditTextField.getText() != null)) {
            begBalance = new BigDecimal(
                        this.beginningBalanceTextField.getText());
        }

        if (this.customersTextField.getText() == null) {
            this.messageTextLabel.setText("Enter a customer ID.");
        } else {
            AccountDetails details = new AccountDetails(
                        (String) this.accountTypeComboBox.getSelectedItem(),
                        this.accountDescriptionTextField.getText(),
                        begBalance,
                        credit,
                        begBalance,
                        new Date());
            customerIds.add(new Long(this.customersTextField.getText()));
            details.setCustomerIds(customerIds);
            this.createAccount(details);
            this.setAccountTextFields(false);
            this.accountCreateButton.setEnabled(false);
            this.accountRemoveButton.setEnabled(true);
            this.accountNewButton.setEnabled(true);
            this.addCustomerToAccountTextField.setEditable(true);
            this.removeCustomerFromAccountButton.setEnabled(true);
            this.addCustomerToAccountButton.setEnabled(true);
            this.messageTextLabel.setText("Created customer");
        }
    } //GEN-LAST:event_accountCreateButtonMouseReleased

    private void accountNewButtonMouseReleased(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_accountNewButtonMouseReleased
        this.clearAccountTextFields();
        this.accountIdTextField.setEditable(false);
        this.accountOpenButton.setEnabled(false);
        this.setAccountTextFields(true);
        this.accountNewButton.setEnabled(false);
        this.accountCreateButton.setEnabled(true);
        this.accountRemoveButton.setEnabled(false);
    } //GEN-LAST:event_accountNewButtonMouseReleased

    private void accountOpenButtonMouseReleased(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_accountOpenButtonMouseReleased

        if (this.accountIdTextField.getText() == null) {
            this.messageTextLabel.setText("Enter an account ID.");
        } else {
            try {
                AccountDetails details = accountController.getDetails(
                            new Long(this.accountIdTextField.getText()));

                if (details != null) {
                    this.fillAccountTextFields(details);
                    this.addCustomerToAccountTextField.setEditable(true);
                    this.addCustomerToAccountButton.setEnabled(true);
                    this.removeCustomerFromAccountButton.setEnabled(true);
                    this.accountRemoveButton.setEnabled(true);
                } else {
                    this.messageTextLabel.setText("Account not found.");
                }
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            } catch (AccountNotFoundException ex) {
                this.messageTextLabel.setText("Account not found.");
            }
        }
    } //GEN-LAST:event_accountOpenButtonMouseReleased

    private void customerNewButtonMouseReleased(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerNewButtonMouseReleased
        this.clearCustomerTextFields();
        this.customerIdTextField.setEditable(false);
        this.customerOpenButton.setEnabled(false);
        this.setCustomerTextFields(true);
        this.customerUpdateButton.setText("Create");
        this.customerUpdateButton.setEnabled(true);
    } //GEN-LAST:event_customerNewButtonMouseReleased

    private void customerSearchButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerSearchButtonMouseReleased
        this.customerSearchList.setModel(new DefaultListModel());

        if (this.customerSearchTextField.getText() == null) {
            this.messageTextLabel.setText("Enter a name.");
        } else {
            try {
                List<CustomerDetails> details = customerController
                    .getCustomersOfLastName(
                            this.customerSearchTextField.getText());

                if (details != null) {
                    DefaultListModel listModel = new DefaultListModel();
                    Iterator<CustomerDetails> i = details.iterator();

                    while (i.hasNext()) {
                        CustomerDetails d = i.next();
                        listModel.addElement(
                                d.getLastName() + ", " + d.getFirstName()
                                + " (" + d.getCustomerId().toString() + ")");
                    }

                    this.customerSearchList.setModel(listModel);
                } else {
                    System.err.println("details is null.");
                    this.messageTextLabel.setText("No customers found.");
                }
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            }
        }
    } //GEN-LAST:event_customerSearchButtonMouseReleased

    private void customerUpdateButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerUpdateButtonMouseReleased

        CustomerDetails details = new CustomerDetails(
                    this.lastNameTextField.getText(),
                    this.firstNameTextField.getText(),
                    this.middleInitialTextField.getText(),
                    this.streetTextField.getText(),
                    this.cityTextField.getText(),
                    this.stateTextField.getText(),
                    this.zipTextField.getText(),
                    this.phoneTextField.getText(),
                    this.emailTextField.getText());

        if (this.customerUpdateButton.getText() == "Create") {
            Long customerId = this.createCustomer(details);
            details.setCustomerId(customerId);
            this.fillCustomerTextFields(details);
            this.customerUpdateButton.setText("Update");
            this.customerRemoveButton.setEnabled(true);
        } else {
            System.err.println(
                    "Update customer:" + this.customerIdTextField.getText());
            details.setCustomerId(new Long(this.customerIdTextField.getText()));
            this.updateCustomer(details);
            this.fillCustomerTextFields(details);
        }
    } //GEN-LAST:event_customerUpdateButtonMouseReleased

    private void customerRemoveButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerRemoveButtonMouseReleased

        if (this.customerIdTextField.getText() == null) {
            this.messageTextLabel.setText("No customer selected.");
        } else {
            try {
                customerController.removeCustomer(
                        new Long(this.customerIdTextField.getText()));
                this.clearCustomerTextFields();
                this.setCustomerTextFields(false);
                this.customerUpdateButton.setEnabled(false);
                this.customerIdTextField.setEditable(true);
                this.customerOpenButton.setEnabled(true);
                this.customerRemoveButton.setEnabled(false);
                this.messageTextLabel.setText("Removed customer.");
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            } catch (CustomerNotFoundException ex) {
                this.messageTextLabel.setText("Customer not found");
            }
        }
    } //GEN-LAST:event_customerRemoveButtonMouseReleased

    private void customerCancelButtonMouseReleased(
        java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerCancelButtonMouseReleased
        this.clearCustomerTextFields();
        this.setCustomerTextFields(false);
        this.customerIdTextField.setEditable(true);
        this.customerOpenButton.setEnabled(true);
        this.customerNewButton.setEnabled(true);
        this.customerUpdateButton.setEnabled(false);
        this.customerRemoveButton.setEnabled(false);
    } //GEN-LAST:event_customerCancelButtonMouseReleased

    private void customerOpenButtonMouseReleased(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_customerOpenButtonMouseReleased

        if (this.customerIdTextField.getText() == null) {
            this.messageTextLabel.setText("No customer ID entered.");
        } else {
            try {
                CustomerDetails details = customerController.getDetails(
                            new Long(this.customerIdTextField.getText()));

                if (details != null) {
                    this.fillCustomerTextFields(details);
                    this.setCustomerTextFields(true);
                    this.customerIdTextField.setEditable(false);
                    this.customerNewButton.setEnabled(false);
                    this.customerUpdateButton.setEnabled(true);
                    this.customerRemoveButton.setEnabled(true);
                } else {
                    this.messageTextLabel.setText("Customer not found");
                }
            } catch (CustomerNotFoundException ex) {
                this.messageTextLabel.setText("Customer not found");
            } catch (InvalidParameterException ex) {
                this.messageTextLabel.setText("InvalidParameterException");
            }
        }
    } //GEN-LAST:event_customerOpenButtonMouseReleased

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    } //GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        new BankAdmin().setVisible(true);
                    }
                });
    }

    // End of variables declaration//GEN-END:variables
    private void setCustomerTextFields(boolean value) {
        this.firstNameTextField.setEditable(value);
        this.lastNameTextField.setEditable(value);
        this.middleInitialTextField.setEditable(value);
        this.streetTextField.setEditable(value);
        this.cityTextField.setEditable(value);
        this.stateTextField.setEditable(value);
        this.zipTextField.setEditable(value);
        this.phoneTextField.setEditable(value);
        this.emailTextField.setEditable(value);
    }

    private void setAccountTextFields(boolean value) {
        this.accountDescriptionTextField.setEditable(value);
        this.accountTypeComboBox.setEnabled(value);
        this.creditTextField.setEditable(value);
        this.beginningBalanceTextField.setEditable(value);
        this.customersTextField.setEditable(value);
        this.addCustomerToAccountTextField.setEditable(value);
        this.addCustomerToAccountButton.setEnabled(value);
    }

    private Long createCustomer(CustomerDetails customerDetails) {
        Long customerId = null;

        try {
            customerId = customerController.createCustomer(customerDetails);
        } catch (InvalidParameterException ex) {
            this.messageTextLabel.setText("InvalidParameterException");
        }

        return customerId;
    }

    private void updateCustomer(CustomerDetails customerDetails) {
        try {
            customerController.setName(
                    customerDetails.getLastName(),
                    customerDetails.getFirstName(),
                    customerDetails.getMiddleInitial(),
                    customerDetails.getCustomerId());
            customerController.setAddress(
                    customerDetails.getStreet(),
                    customerDetails.getCity(),
                    customerDetails.getState(),
                    customerDetails.getZip(),
                    customerDetails.getPhone(),
                    customerDetails.getEmail(),
                    customerDetails.getCustomerId());
        } catch (CustomerNotFoundException ex) {
            this.messageTextLabel.setText("Customer not found");
        } catch (InvalidParameterException ex) {
            this.messageTextLabel.setText("InvalidParameterException");
        }
    }

    private void createAccount(AccountDetails details) {
        System.err.println("Customer ID: " + details.getCustomerIds().get(0));

        try {
            Long accountId = accountController.createAccount(
                        details,
                        new Long(details.getCustomerIds().get(0)));
            details.setAccountId(accountId);
            this.fillAccountTextFields(details);
        } catch (IllegalAccountTypeException ex) {
            ex.printStackTrace();
        } catch (CustomerNotFoundException ex) {
            ex.printStackTrace();
        } catch (InvalidParameterException ex) {
            ex.printStackTrace();
        }
    }

    private void fillCustomerTextFields(CustomerDetails details) {
        this.customerIdTextField.setText(details.getCustomerId().toString());
        this.firstNameTextField.setText(details.getFirstName());
        this.lastNameTextField.setText(details.getLastName());
        this.middleInitialTextField.setText(details.getMiddleInitial());
        this.streetTextField.setText(details.getStreet());
        this.cityTextField.setText(details.getCity());
        this.stateTextField.setText(details.getState());
        this.zipTextField.setText(details.getZip());
        this.phoneTextField.setText(details.getPhone());
        this.emailTextField.setText(details.getEmail());
    }

    private void fillAccountTextFields(AccountDetails details) {
        this.accountIdTextField.setText(details.getAccountId().toString());
        this.accountDescriptionTextField.setText(details.getDescription());
        this.accountTypeComboBox.setSelectedItem(details.getType());
        this.balanceTextField.setText(details.getBalance().toString());
        this.creditTextField.setText(details.getCreditLine().toString());
        this.beginningBalanceTextField.setText(
                details.getBeginBalance().toString());
        this.customersTextField.setText(details.getCustomerIds().toString());
        this.timeTextField.setText(
                DateFormat.getDateInstance().format(
                        details.getBeginBalanceTimeStamp()));
    }

    private void clearCustomerTextFields() {
        this.customerIdTextField.setText(null);
        this.firstNameTextField.setText(null);
        this.lastNameTextField.setText(null);
        this.middleInitialTextField.setText(null);
        this.streetTextField.setText(null);
        this.cityTextField.setText(null);
        this.stateTextField.setText(null);
        this.zipTextField.setText(null);
        this.phoneTextField.setText(null);
        this.emailTextField.setText(null);
        this.messageTextLabel.setText(null);
    }

    private void clearAccountTextFields() {
        this.accountIdTextField.setText(null);
        this.accountDescriptionTextField.setText(null);
        this.accountTypeComboBox.setSelectedItem(null);
        this.balanceTextField.setText(null);
        this.creditTextField.setText(null);
        this.beginningBalanceTextField.setText(null);
        this.customersTextField.setText(null);
        this.timeTextField.setText(null);
        this.messageTextLabel.setText(null);
    }

    private void resetAll() {
        this.setCustomerTextFields(false);
        this.setAccountTextFields(false);
    }
}
