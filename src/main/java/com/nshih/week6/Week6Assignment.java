/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week6;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.nshih.utils.ClassServer;
import com.nshih.utils.Hikes;
import com.rbevans.bookingrate.BookingDay;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
/**
 * @author Nathan Shih
 */
public class Week6Assignment {
	
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
	
	private static String selectedHike;
	private static Integer selectedDuration;
	//private static CalculateRate rateFromLocal;
    private static CalculateRate rateFromServer;
	
    public static void main(String[] args) {
        // schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//rateFromLocal = new CalculateRateFromLocal();
            	rateFromServer = new CalculateRateFromServer(ClassServer.SERVER_URL, ClassServer.SERVER_PORT);
        		
                handleEvents();
            }
        });
    }
    
    /**
     * This creates and sets properties on all UI elements.
     */
    private static void createAndShowGUI() {
    	tourSelector = new JFrame("Tour Selector");
    	tourSelector.setResizable(false);
        tourSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tourSelector.getContentPane().setLayout(null);   
        tourSelector.setSize(456, 362);
        tourSelector.setLocationRelativeTo(null);
        tourSelector.setVisible(true);
        
    	txtpnCurrentHikingTours = new JTextField();
    	txtpnCurrentHikingTours.setHorizontalAlignment(SwingConstants.CENTER);
    	txtpnCurrentHikingTours.setEditable(false);
        txtpnCurrentHikingTours.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtpnCurrentHikingTours.setBounds(10, 5, 429, 20);
        txtpnCurrentHikingTours.setText("Current hiking tours offered by the Beartooth Hiking Company (BHC):");
        txtpnCurrentHikingTours.setBorder(new EmptyBorder(0, 0, 0, 0));
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
        comboHikes.setToolTipText("Current hiking tours offered and displayed here. Check back for updates!");
        comboHikes.setModel(new DefaultComboBoxModel<String>(new String[] {Hikes.GARDINER_LAKE, Hikes.HELLROARING_PLATEAU, Hikes.BEATEN_PATH}));
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
        comboDuration.setToolTipText("Choose a duration for the selected hike.");
        comboDuration.setBounds(143, 97, 47, 20);
        tourSelector.getContentPane().add(comboDuration);
        populateHikeDuration(); // initially set hike durations
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
        output.setBackground(Color.WHITE);
        output.setEditable(false);
        output.setBounds(10, 162, 429, 161);
        output.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        tourSelector.getContentPane().add(output);
        
        try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(tourSelector);
		} catch (Exception e) {
			
		}
    }
    
    /**
     * This handles UI events.
     */
	private static void handleEvents() {		
        // create and set up the window
		createAndShowGUI();
		
		// capture selected date from JDatePicker
        btnStartDate.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {    		
        		BookingDay bookingStart = new BookingDay(model.getYear(), model.getMonth()+1, model.getDay());      		
        		
        		if (bookingStart.isValidDate()) {
        			double cost = 0;
        			// cost = rateFromLocal.getRate(bookingStart, selectedDuration, selectedHike);
        			cost = rateFromServer.getRate(bookingStart, selectedDuration, selectedHike);
        			
        			output.setText(null);

        			if (cost < 1) {
        				JOptionPane.showMessageDialog(null, rateFromServer.getDetails(), "Oops!", JOptionPane.INFORMATION_MESSAGE);
        			} else {
        				output.append("Total cost for the " + selectedHike + " hike starting on " + bookingStart + 
        						" for " + selectedDuration.toString() + " days will cost $" + cost);
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
	
	/**
	 * This populates the hike durations based upon the selected hike.
	 */
	private static void populateHikeDuration() {
		comboDuration.removeAllItems();
		selectedHike = (String) comboHikes.getSelectedItem();
		switch (selectedHike) {
			case Hikes.GARDINER_LAKE:
				for (int duration : Hikes.GARDINER_LAKE_DURATIONS) {
					comboDuration.addItem(duration);
				}
				break;
			case Hikes.HELLROARING_PLATEAU:
				for (int duration : Hikes.HELLROARING_PLATEAU_DURATIONS) {
					comboDuration.addItem(duration);
				}
				break;
			case Hikes.BEATEN_PATH:
				for (int duration : Hikes.BEATEN_PATH_DURATIONS) {
					comboDuration.addItem(duration);
				}
				break;						
		}
	}
}
