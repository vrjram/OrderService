package com.test.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class OrderServiceTest
    extends TestCase
{
    /**
     * Rigourous Test :-)
     */
    public void testOrderService()
    {
        OrderService orderService = new OrderService();
        assertEquals("serviceAserviceAAserviceBserviceC",orderService.processOrder());
    }
}
