/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week5;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;

import com.rbevans.bookingrate.BookingDay;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * @author Nathan Shih
 */
public class Week5Assignment {
	
	private static JTextField txtChooseAHike;
	private static JTextField txtForHowLong;
	private static JComboBox<String> comboHikes;
	private static JComboBox<String> comboDuration;
	
	private static final String GARDINER_LAKE = "Gardiner Lake";
	private static final String HELLROARING_PLATEAU = "Hellroaring Plateau";
	private static final String BEATEN_PATH = "Beaten Path";
	
	private static SpringLayout springLayout;
	
	public enum Hike {
		GARDINER_LAKE, HELLROARING_PLATEAU, BEATEN_PATH
	}
	 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
	private static void createAndShowGUI() {
		
        //Create and set up the window.
        JFrame frame = new JFrame("Tour Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JTextPane txtpnCurrentHikingTours = new JTextPane();
        txtpnCurrentHikingTours.setEditable(false);
        txtpnCurrentHikingTours.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtpnCurrentHikingTours.setBounds(10, 5, 429, 20);
        txtpnCurrentHikingTours.setText("Current hiking tours offered by the Beartooth Hiking Company (BHC):");
        frame.getContentPane().add(txtpnCurrentHikingTours);
           
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setBounds(10, 281, 429, 161);
        
        final UtilDateModel model = new UtilDateModel();
        Calendar currentDate = Calendar.getInstance();      
        model.setDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
        datePicker.setBounds(200, 128, 202, 23);
                
        JButton btnStartDate = new JButton("Choose start date");       
        btnStartDate.setBounds(46, 128, 144, 23);
        btnStartDate.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
           		BookingDay bookingDay = new BookingDay(model.getYear(), model.getMonth(), model.getDay());
           		if (bookingDay.isValidDate()) {
	        		output.setText(null);
	        		output.append("Month=" + bookingDay.getMonth() + " Day=" + bookingDay.getDayOfMonth());
           		}
        	}
        });
        
        springLayout = (SpringLayout) datePicker.getLayout();
        springLayout.putConstraint(SpringLayout.NORTH, btnStartDate, 0, SpringLayout.SOUTH, datePicker.getJFormattedTextField());
        springLayout.putConstraint(SpringLayout.WEST, btnStartDate, 0, SpringLayout.WEST, datePicker.getJFormattedTextField());
        
        //Display the window.
        frame.getContentPane().add(datePicker);
        frame.getContentPane().add(btnStartDate);
        frame.getContentPane().add(output);
        
        comboHikes = new JComboBox<String>();
        comboHikes.setModel(new DefaultComboBoxModel<String>(new String[] {GARDINER_LAKE, HELLROARING_PLATEAU, BEATEN_PATH}));
        comboHikes.setBounds(143, 68, 115, 20);
        frame.getContentPane().add(comboHikes);
              
        comboDuration = new JComboBox<String>();
        comboDuration.setBounds(143, 97, 47, 20);
        
        txtForHowLong = new JTextField();
        txtForHowLong.setEditable(false);
        txtForHowLong.setHorizontalAlignment(SwingConstants.CENTER);
        txtForHowLong.setText("For how long?");
        txtForHowLong.setBounds(44, 97, 89, 20);
        txtForHowLong.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        frame.getContentPane().add(txtForHowLong);
        txtForHowLong.setColumns(10);
        
        txtChooseAHike = new JTextField();
        txtChooseAHike.setHorizontalAlignment(SwingConstants.CENTER);
        txtChooseAHike.setEditable(false);
        txtChooseAHike.setText("Choose a hike:");
        txtChooseAHike.setBounds(46, 68, 89, 20);
        txtChooseAHike.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        frame.getContentPane().add(txtChooseAHike);
        txtChooseAHike.setColumns(10);
        comboHikes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateHikeDuration();
			}      	
        });

        frame.getContentPane().add(comboDuration);
        frame.setSize(466, 488);
        frame.setVisible(true);
        populateHikeDuration();
    }
	
	private static void populateHikeDuration() {
		comboDuration.removeAllItems();
		String selectedHike = (String) comboHikes.getSelectedItem();
		switch (selectedHike) {
			case GARDINER_LAKE:
				comboDuration.addItem("3");
				comboDuration.addItem("5");
				break;
			case HELLROARING_PLATEAU:
				comboDuration.addItem("2");
				comboDuration.addItem("3");
				comboDuration.addItem("4");
				break;
			case BEATEN_PATH:
				comboDuration.addItem("5");
				comboDuration.addItem("7");
				break;						
		}
	}
}
