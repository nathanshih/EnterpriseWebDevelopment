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
	private static JTextPane txtpnCurrentHikingTours;
	private static JComboBox<String> comboHikes;
	private static JComboBox<String> comboDuration;
	private static JDatePanelImpl datePanel;
	private static JDatePickerImpl datePicker;
	private static UtilDateModel model;
	private static JButton btnStartDate;
	private static JTextArea output;
	
	private static final String GARDINER_LAKE = "Gardiner Lake";
	private static final String HELLROARING_PLATEAU = "Hellroaring Plateau";
	private static final String BEATEN_PATH = "Beaten Path";
		 
    public static void main(String[] args) {
        // schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void setupGUI() {
    	tourSelector = new JFrame("Tour Selector");
        tourSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tourSelector.getContentPane().setLayout(null);   
        tourSelector.setSize(466, 488);
        tourSelector.setVisible(true);
        
    	txtpnCurrentHikingTours = new JTextPane();
        txtpnCurrentHikingTours.setEditable(false);
        txtpnCurrentHikingTours.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtpnCurrentHikingTours.setBounds(10, 5, 429, 20);
        txtpnCurrentHikingTours.setText("Current hiking tours offered by the Beartooth Hiking Company (BHC):");
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
        
        comboDuration = new JComboBox<String>();
        comboDuration.setBounds(143, 97, 47, 20);
        tourSelector.getContentPane().add(comboDuration);
        
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
        output.setBounds(10, 281, 429, 161);
        tourSelector.getContentPane().add(output);     
    }
    
	private static void createAndShowGUI() {		
        // create and set up the window
		setupGUI();
		
		populateHikeDuration();
        
		// capture selected date from JDatePicker
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
              
        comboHikes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				populateHikeDuration();
			}      	
        });
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
