/** Copyright © 2014 
 *  Nathan Shih
 *  All rights reserved.
 */
package com.nshih.week5;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Calendar;

import com.rbevans.bookingrate.BookingDay;

/**
 * @author Nathan Shih
 */
public class Week5Assignment {

	 
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
           
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 281, 429, 161);
        
        final UtilDateModel model = new UtilDateModel();
        Calendar currentDate = Calendar.getInstance();      
        model.setDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);   
        datePicker.setBounds(55, 36, 202, 23);
                
        JButton btnStartDate = new JButton("Choose start date");       
        btnStartDate.setBounds(275, 36, 119, 23);
        btnStartDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
           		BookingDay bookingDay = new BookingDay(model.getYear(), model.getMonth(), model.getDay());
           		if (bookingDay.isValidDate()) {
	        		textArea.setText(null);
	        		textArea.append("Month=" + bookingDay.getMonth() + " Day=" + bookingDay.getDayOfMonth());
           		}
        	}
        });
        
        SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
        springLayout.putConstraint(SpringLayout.NORTH, btnStartDate, 0, SpringLayout.SOUTH, datePicker.getJFormattedTextField());
        springLayout.putConstraint(SpringLayout.WEST, btnStartDate, 0, SpringLayout.WEST, datePicker.getJFormattedTextField());
        
        //Display the window.
        frame.getContentPane().add(datePicker);
        frame.getContentPane().add(btnStartDate);
        frame.getContentPane().add(textArea);
        frame.setSize(466, 488);
        frame.setVisible(true);
    }
}
