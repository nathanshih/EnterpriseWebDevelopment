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
import com.rbevans.bookingrate.Rates;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * @author Nathan Shih
 */
public class Week5Assignment {
	
	// GUI objects
	private static JFrame tourSelector;
	private static JTextField txtChooseAHike;
	private static JTextField txtForHowLong;
	private static JTextField txtpnCurrentHikingTours;
	private static JComboBox<String> comboHikes;
	private static JComboBox<Integer> comboDuration;
	private static JDatePanelImpl datePanel;
	private static JDatePickerImpl datePicker;
	private static UtilDateModel model;
	private static JButton btnStartDate;
	private static JTextArea output;
	
	// constants
	private static final String GARDINER_LAKE = "Gardiner Lake";
	private static final String HELLROARING_PLATEAU = "Hellroaring Plateau";
	private static final String BEATEN_PATH = "Beaten Path";
	private static final double GARDINER_LAKE_RATE = 40;
	private static final double HELLROARING_PLATEAU_RATE = 35;
	private static final double BEATEN_PATH_RATE = 45;
		
	private static String selectedHike;
	private static Integer selectedDuration;
	
    public static void main(String[] args) {
        // schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                handleEvents();
            }
        });
    }
    
    private static void createAndShowGUI() {
    	tourSelector = new JFrame("Tour Selector");
        tourSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tourSelector.getContentPane().setLayout(null);   
        tourSelector.setSize(466, 369);
        tourSelector.setVisible(true);
        
    	txtpnCurrentHikingTours = new JTextField();
    	txtpnCurrentHikingTours.setHorizontalAlignment(SwingConstants.CENTER);
    	txtpnCurrentHikingTours.setEditable(false);
        txtpnCurrentHikingTours.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtpnCurrentHikingTours.setBounds(10, 5, 429, 20);
        txtpnCurrentHikingTours.setText("Current hiking tours offered by the Beartooth Hiking Company (BHC):");
        txtpnCurrentHikingTours.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tourSelector.getContentPane().add(txtpnCurrentHikingTours);
        
        txtChooseAHike = new JTextField();
        txtChooseAHike.setHorizontalAlignment(SwingConstants.CENTER);
        txtChooseAHike.setEditable(false);
        txtChooseAHike.setText("Choose a hike:");
        txtChooseAHike.setBounds(46, 68, 89, 20);
        txtChooseAHike.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtChooseAHike.setColumns(10);
        tourSelector.getContentPane().add(txtChooseAHike);
              
        comboHikes = new JComboBox<String>();
        comboHikes.setModel(new DefaultComboBoxModel<String>(new String[] {GARDINER_LAKE, HELLROARING_PLATEAU, BEATEN_PATH}));
        comboHikes.setBounds(143, 68, 115, 20);
        tourSelector.getContentPane().add(comboHikes);
        
        txtForHowLong = new JTextField();
        txtForHowLong.setEditable(false);
        txtForHowLong.setHorizontalAlignment(SwingConstants.CENTER);
        txtForHowLong.setText("For how long?");
        txtForHowLong.setBounds(44, 97, 89, 20);
        txtForHowLong.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtForHowLong.setColumns(10);
        tourSelector.getContentPane().add(txtForHowLong);
        
        comboDuration = new JComboBox<Integer>();
        comboDuration.setBounds(143, 97, 47, 20);
        tourSelector.getContentPane().add(comboDuration);
        populateHikeDuration();
        selectedDuration = (Integer) comboDuration.getSelectedItem(); // initially set selectedDuration to avoid NPE error
        
        model = new UtilDateModel();
        Calendar currentDate = Calendar.getInstance();
        model.setDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(200, 128, 202, 23);
        tourSelector.getContentPane().add(datePicker);
        
        btnStartDate = new JButton("Choose start date");       
        btnStartDate.setBounds(46, 128, 144, 23);
        tourSelector.getContentPane().add(btnStartDate);
        
        output = new JTextArea();
        output.setEditable(false);
        output.setBounds(10, 162, 429, 161);
        tourSelector.getContentPane().add(output);     
    }
    
	private static void handleEvents() {		
        // create and set up the window
		createAndShowGUI();
        
		// capture selected date from JDatePicker
        btnStartDate.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {    		
        		BookingDay bookingStart = new BookingDay(model.getYear(), model.getMonth()+1, model.getDay());      		
           		if (bookingStart.isValidDate()) {
           			
           			// find end date based on selected duration
            		Calendar endDate = Calendar.getInstance();
            		endDate.set(model.getYear(), model.getMonth(), model.getDay());
            		endDate.add(Calendar.DATE, selectedDuration);
            		BookingDay bookingEnd = new BookingDay(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH)+1, endDate.get(Calendar.DAY_OF_MONTH));
           			
            		if (bookingEnd.isValidDate()) {               			
            			Rates rate = new Rates();
            			rate.setBeginDate(bookingStart);
            			rate.setEndDate(bookingEnd);

            			// set base rates on chosen hike
            			switch (selectedHike) {
	            			case GARDINER_LAKE:
	            				rate.setBaseRate(GARDINER_LAKE_RATE);
	            				break;
	            			case HELLROARING_PLATEAU:
	            				rate.setBaseRate(HELLROARING_PLATEAU_RATE);
	            				break;
	            			case BEATEN_PATH:
	            				rate.setBaseRate(BEATEN_PATH_RATE);
	            				break;						
            			}
            			
            			double cost = rate.getCost();
            		           			
            			output.setText(null);
            			if (cost < 0) {
            				output.append(rate.getDetails());
            			} else {
            				output.append("Total cost for the " + selectedHike + " hike for " + selectedDuration.toString() + 
            								" days will cost $" + cost);
            			}
            		}
           		}
        	}
        });
              
        comboHikes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedHike = (String) comboHikes.getSelectedItem();
				populateHikeDuration();
			}      	
        });
        
        comboDuration.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
        		selectedDuration = (Integer) comboDuration.getSelectedItem();
        	}
        });
    }
	
	private static void populateHikeDuration() {
		comboDuration.removeAllItems();
		selectedHike = (String) comboHikes.getSelectedItem();
		switch (selectedHike) {
			case GARDINER_LAKE:
				comboDuration.addItem(3);
				comboDuration.addItem(5);
				break;
			case HELLROARING_PLATEAU:
				comboDuration.addItem(2);
				comboDuration.addItem(3);
				comboDuration.addItem(4);
				break;
			case BEATEN_PATH:
				comboDuration.addItem(5);
				comboDuration.addItem(7);
				break;						
		}
	}
}
