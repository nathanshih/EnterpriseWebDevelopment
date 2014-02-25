/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rbevans.bookingrate;

//import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author evansrb1
 */
public class RatesTest {

    private BookingDay seasonStartDay = new BookingDay(2008, 6, 1);
    private BookingDay startDay = new BookingDay(2008, 7, 1);
    private BookingDay endDay = new BookingDay(2008, 7, 8);
    private BookingDay startDay2 = new BookingDay(2008, 7, 2);
    private BookingDay endDay2 = new BookingDay(2008, 7, 9);
    private BookingDay pastEndDay = new BookingDay(2008, 11, 1);

    public RatesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCost method, of class Rates.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        Rates instance = new Rates();
        instance.setBeginDate(startDay);
        instance.setEndDate(endDay);
        double expResult = 540.0;
        double result = instance.getCost();
        assertEquals(expResult, result, 0.001);

        // Test to see if you can re-use the Rates object
        instance.setBeginDate(startDay2);
        instance.setEndDate(endDay2);
        expResult = 540.0;
        result = instance.getCost();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of setBaseRate method, of class Rates.
     */
    @Test
    public void testSetBaseRate() {
        System.out.println("setBaseRate");
        double rate = 100.0;
        Rates instance = new Rates();
        instance.setBaseRate(rate);
        assertEquals(instance.getBaseRate(), rate, 0.001);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeasonStart method, of class Rates.
     */
    @Test
    public void testSetSeasonStart() {
        System.out.println("setSeasonStart");
        int month = 0;
        int day = 0;
        Rates instance = new Rates();
        instance.setSeasonStart(month, day);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeasonEnd method, of class Rates.
     */
    @Test
    public void testSetSeasonEnd() {
        System.out.println("setSeasonEnd");
        int month = 0;
        int day = 0;
        Rates instance = new Rates();
        instance.setSeasonEnd(month, day);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidDates method, of class Rates.
     */
    @Test
    public void testIsValidDates() {
        System.out.println("isValidDates");


        // test for null start
        Rates instance = new Rates();
        instance.setBeginDate(startDay);
        instance.setEndDate(endDay);
        boolean expResult = true;
        boolean result = instance.isValidDates();
        assertEquals(expResult, result);

        // test for null end
        instance = new Rates();
        instance.setBeginDate(startDay);
        expResult = false;
        result = instance.isValidDates();
        assertEquals(expResult, result);

        // test for out of season
        instance = new Rates();
        instance.setBeginDate(endDay);
        instance.setEndDate(pastEndDay);
        expResult = false;
        result = instance.isValidDates();
        assertEquals(expResult, result);

        // test for end date before start
        instance = new Rates();
        instance.setEndDate(startDay);
        instance.setBeginDate(endDay);
        expResult = false;
        result = instance.isValidDates();
        assertEquals(expResult, result);

        // test for invalid day
        instance = new Rates();
        instance.setBeginDate(startDay);
        instance.setEndDate(startDay);
        expResult = false;
        result = instance.isValidDates();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetails method, of class Rates.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");

        // test for null start
        Rates instance = new Rates();
        instance.setEndDate(endDay);
        String expResult = "One of the dates was not defined";
        instance.isValidDates();
        String result = instance.getDetails();
        assertEquals(expResult, result);

        // test for null end
        instance = new Rates();
        instance.setBeginDate(startDay);
        expResult = "One of the dates was not defined";
        instance.isValidDates();
        result = instance.getDetails();
        assertEquals(expResult, result);

        // test for out of season
        instance = new Rates();
        instance.setBeginDate(endDay);
        instance.setEndDate(pastEndDay);
        expResult = "begin or end date was out of season";
        instance.isValidDates();
        result = instance.getDetails();
        assertEquals(expResult, result);

        // test for end date before start
        instance = new Rates();
        instance.setEndDate(startDay);
        instance.setBeginDate(endDay);
        expResult = "end date was before begin date";
        instance.isValidDates();
        result = instance.getDetails();
        assertEquals(expResult, result);

        // test for same day
        instance = new Rates();
        instance.setBeginDate(startDay);
        instance.setEndDate(startDay);
        expResult = "The begin and end date must not be the same date";
        instance.isValidDates();
        result = instance.getDetails();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalDays method, of class Rates.
     */
    @Test
    public void testGetNormalDays() {
        System.out.println("getNormalDays");
        Rates instance = new Rates();
        int expResult = 0;
        int result = instance.getNormalDays();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getPremiumDays method, of class Rates.
     */
    @Test
    public void testGetPremiumDays() {
        System.out.println("getPremiumDays");
        Rates instance = new Rates();
        int expResult = 0;
        int result = instance.getPremiumDays();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeginDate method, of class Rates.
     */
    @Test
    public void testGetBeginDate() {
        System.out.println("getBeginDate");
        Rates instance = new Rates();
        instance.setBeginDate(startDay);
        GregorianCalendar result = instance.getBeginDate();
        assertEquals(startDay.getDate(), result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The start day was not set properly in the Rates object");
    }

    /**
     * Test of getEndDate method, of class Rates.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Rates instance = new Rates();
        instance.setEndDate(endDay);
        GregorianCalendar result = instance.getEndDate();
        assertEquals(endDay.getDate(), result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The end day was not set properly in the Rates object");
    }

    /**
     * Test of getBaseRate method, of class Rates.
     */
    @Test
    public void testGetBaseRate() {
        System.out.println("getBaseRate");
        Rates instance = new Rates();
        double expResult = 60.0;
        double result = instance.getBaseRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPremiumRate method, of class Rates.
     */
    @Test
    public void testGetPremiumRate() {
        System.out.println("getPremiumRate");
        Rates instance = new Rates();
        double expResult = 90.0;
        double result = instance.getPremiumRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setBeginDate method, of class Rates.
     */
    @Test
    public void testSetBeginDate() {
        System.out.println("setBeginDate");
        BookingDay beginDate = new BookingDay(2010,7,1);
        GregorianCalendar gcal = beginDate.getDate();
        Rates instance = new Rates();
        instance.setBeginDate(beginDate);
        assertEquals(gcal, instance.getBeginDate());
    }

    /**
     * Test of setEndDate method, of class Rates.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        BookingDay beginDate = new BookingDay(2010,7,1);
        GregorianCalendar gcal = beginDate.getDate();
        Rates instance = new Rates();
        instance.setEndDate(beginDate);
        assertEquals(gcal, instance.getEndDate());
    }

    /**
     * Test of main method, of class Rates.
     */
    @Test
    public void testMain() {
        System.out.println("main");
    }
}