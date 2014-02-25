/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rbevans.bookingrate;

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
public class BookingDayTest {

    public BookingDayTest() {
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
     * Test of equals method, of class BookingDay.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        BookingDay day1 = new BookingDay(2010, 7, 1);
        Object day2 = new BookingDay(2010, 7, 1);
        boolean result = day1.equals(day2);
        assertTrue(result);

        day2 = new Integer(5);
        if (day1.equals(day2)) {
            fail("Equals thought an Integer was BookingDay");
        }
    }

    /**
     * Test of hashCode method, of class BookingDay.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        BookingDay day1 = new BookingDay(2010,7,1);
        BookingDay day2 = new BookingDay(2010,7,1);
        BookingDay day3 = new BookingDay(2010,7,2);
        int expResult = 0;
        int result1 = day1.hashCode();
        int result2 = day2.hashCode();
        int result3 = day3.hashCode();

        assertEquals(result1, result2);

        // two different days should have two different hash values
        boolean compare = (result2 == result3);
        assertFalse(compare);

        // make sure hash isn't 0 (odds are it Never is)
        compare = (expResult == result1);
        assertFalse(compare);
    }

    /**
     * Test of isValidDate method, of class BookingDay.
     */
    @Test
    public void testIsValidDate() {
        System.out.println("isValidDate");
        BookingDay instance = new BookingDay(2010,7,1);
        boolean result = instance.isValidDate();
        assertTrue(result);

        instance = new BookingDay(2010, 6, 31);
        result = instance.isValidDate();
        assertFalse(result);

        instance = new BookingDay(20101, 6, 31);
        result = instance.isValidDate();
        assertFalse(result);

        instance = new BookingDay(2010, 13, 31);
        result = instance.isValidDate();
        assertFalse(result);

        // January shouldn't be 0, it should be 1
        instance = new BookingDay(2010, 0, 1);
        result = instance.isValidDate();
        assertFalse(result);

        // but Jan as 1 is valid
        instance = new BookingDay(2010, 1, 1);
        result = instance.isValidDate();
        assertTrue(result);


    }

    /**
     * Test of before method, of class BookingDay.
     */
    @Test
    public void testBefore_BookingDay() {
        System.out.println("before");
        BookingDay endDay = new BookingDay(2010,7,2);
        BookingDay instance = new BookingDay(2010,7,1);

        // this should be true
        boolean result = instance.before(endDay);
        assertTrue(result);

        // from now on, everything should be false
        endDay = new BookingDay(2010,7,1);
        result = instance.before(endDay);
        assertFalse(result);

        endDay = new BookingDay(2010,6,1);
        result = instance.before(endDay);
        assertFalse(result);

        endDay = null;
        result = instance.before(endDay);
        assertFalse(result);
    }

    /**
     * Test of after method, of class BookingDay.
     */
    @Test
    public void testAfter_BookingDay() {
        System.out.println("after");
        BookingDay endDay = new BookingDay(2010,6,30);
        BookingDay instance = new BookingDay(2010,7,1);


        // this should be true
        boolean result = instance.after(endDay);
        assertTrue(result);

        // from now on, everything should be false
        endDay = new BookingDay(2010,7,1);
        result = instance.after(endDay);
        assertFalse(result);

        endDay = new BookingDay(2010,8,1);
        result = instance.after(endDay);
        assertFalse(result);

        endDay = null;
        result = instance.after(endDay);
        assertFalse(result);

    }

    /**
     * Test of after method, of class BookingDay.
     */
    @Test
    public void testAfter_int_int() {
        System.out.println("after");
        int month = 6;
        int day = 30;
        BookingDay instance = new BookingDay(2010,7,1);
        boolean result = instance.after(month, day);
        assertTrue(result);

        month = 7;
        day = 1;
        result = instance.after(month, day);
        assertFalse(result);

        month = 7;
        day = 2;
        result = instance.after(month, day);
        assertFalse(result);

    }

    /**
     * Test of before method, of class BookingDay.
     */
    @Test
    public void testBefore_int_int() {
        System.out.println("before");
        int month = 7;
        int day = 2;
        BookingDay instance = new BookingDay(2010,7,1);
        boolean result = instance.before(month, day);
        assertTrue(result);

        month = 7;
        day = 1;
        result = instance.before(month, day);
        assertFalse(result);

        month = 6;
        day = 30;
        result = instance.before(month, day);
        assertFalse(result);
    }

    /**
     * Test of getValidationStatus method, of class BookingDay.
     */
    @Test
    public void testGetValidationStatus() {
        System.out.println("getValidationStatus");
        BookingDay instance = new BookingDay(2010,7,1);
        instance.isValidDate();
        String expResult = BookingDay.VALID;
        String result = instance.getValidationStatus();
        assertEquals(expResult, result);

        instance = new BookingDay(2010,13,27);
        expResult = BookingDay.NOT_VALIDATED;
        result = instance.getValidationStatus();
        assertEquals(expResult, result);

        instance.isValidDate();
        expResult = BookingDay.NOT_VALID;
        result = instance.getValidationStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BookingDay.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BookingDay instance = new BookingDay(2010,7,1);
        String expResult = "7/1/2010";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of badDay method, of class BookingDay.
     */
    @Test
    public void testBadDay() {
        System.out.println("badDay");
        BookingDay instance = new BookingDay(2010,7,1);
        boolean result = instance.badDay();
        assertFalse("Thought that July 1, 2010 was a bad day", result);

        instance = new BookingDay(1980, 2, 29);
        result = instance.badDay();
        assertFalse("Did not recognize Feb 29th in leap year",result);

        instance = new BookingDay(2010, 2, 29);
        result = instance.badDay();
        assertTrue("Thought that Feb 29th in a non-leap year was okay", result);

        instance = new BookingDay(2010, 9, 31);
        result = instance.badDay();
        assertTrue("Thought that Sep 31 is okay", result);

        instance = new BookingDay(2010, 4, 31);
        result = instance.badDay();
        assertTrue("Thought that Apr 31 is okay", result);

        instance = new BookingDay(2010, 6, 31);
        result = instance.badDay();
        assertTrue("Thought that Jun 31 is okay", result);

        instance = new BookingDay(2010, 11, 31);
        result = instance.badDay();
        assertTrue("Thought that Nov 31 is okay", result);
    }

    /**
     * Test of getDate method, of class BookingDay.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        BookingDay instance = new BookingDay(2010,7,1);
        // remember, for GregorianCalendar, July is month 6
        GregorianCalendar expResult = new GregorianCalendar(2010, 6, 1);
        GregorianCalendar result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class BookingDay.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        BookingDay instance = new BookingDay(2010,7,1);
        int expResult = 2010;
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYear method, of class BookingDay.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2011;
        BookingDay instance = new BookingDay(2010,7,1);
        instance.setYear(year);
        GregorianCalendar expResult = new GregorianCalendar(2011, 6, 1);
        GregorianCalendar result = instance.getDate();
        assertEquals(expResult, result);

        assertEquals(year, instance.getYear());

        String expValidResult = BookingDay.NOT_VALIDATED;
        String validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);

        instance.isValidDate();
        expValidResult = BookingDay.VALID;
        validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);

    }

    /**
     * Test of getMonth method, of class BookingDay.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        BookingDay instance = new BookingDay(2010,7,1);
        int expResult = 7;
        int result = instance.getMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMonth method, of class BookingDay.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        int month = 8;
        BookingDay instance = new BookingDay(2010,7,1);
        instance.setMonth(month);
        GregorianCalendar expResult = new GregorianCalendar(2010, 7, 1);
        GregorianCalendar result = instance.getDate();
        assertEquals(expResult, result);

        assertEquals(month, instance.getMonth());

        String expValidResult = BookingDay.NOT_VALIDATED;
        String validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);

        instance.isValidDate();
        expValidResult = BookingDay.VALID;
        validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);

    }

    /**
     * Test of getDayOfMonth method, of class BookingDay.
     */
    @Test
    public void testGetDayOfMonth() {
        System.out.println("getDayOfMonth");
        BookingDay instance = new BookingDay(2010,7,1);
        int expResult = 1;
        int result = instance.getDayOfMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDayOfMonth method, of class BookingDay.
     */
    @Test
    public void testSetDayOfMonth() {
        System.out.println("setDayOfMonth");
        int dayOfMonth = 5;
        BookingDay instance = new BookingDay(2010,7,1);
        instance.setDayOfMonth(dayOfMonth);
        GregorianCalendar expResult = new GregorianCalendar(2010, 6, 5);
        GregorianCalendar result = instance.getDate();
        assertEquals(expResult, result);

       assertEquals(dayOfMonth, instance.getDayOfMonth());

        String expValidResult = BookingDay.NOT_VALIDATED;
        String validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);

        instance.isValidDate();
        expValidResult = BookingDay.VALID;
        validResult= instance.getValidationStatus();
        assertEquals(expValidResult, validResult);
    }


    @Test
    public void testMain() {
        System.out.println("main");
    }

}