package com.test.java;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.powermock.api.easymock.PowerMock.createMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({OrderService.class})
public class OrderServiceTest
{

  private ServiceC serviceC = createMock(ServiceC.class);

    @Test
    public void testOrderService() throws Exception
    {
        OrderService orderService = new OrderService();
        expectNew(ServiceC.class).andReturn(serviceC);

        expect(serviceC.service(isA(String.class), isA(String.class))).andReturn("serviceAserviceAAserviceBserviceC");
        replayAll();
        assertEquals("serviceAserviceAAserviceBserviceC",orderService.processOrder());
        verifyAll();
    }
}
