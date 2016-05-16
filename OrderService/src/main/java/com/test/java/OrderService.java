package com.test.java;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Order Servicepwd

 *
 */
public class OrderService
{
    public String processOrder()
    {
        ServiceA serviceA = new ServiceA();
        ServiceAA serviceAA = new ServiceAA();
        ServiceB serviceB = new ServiceB();
        ServiceC serviceC = new ServiceC();

        List<Callable<String>> callableList = new ArrayList();

      callableList.add(new Callable<String>(){
        public String call() throws Exception {
          ServiceA serviceA = new ServiceA();
          ServiceAA serviceAA = new ServiceAA();
          String outA = serviceA.service();
          String outAA = serviceAA.service(outA);
          return (outAA);
        }
      });


        callableList.add(new Callable<String>(){
                public String call() throws Exception {
                  ServiceB serviceB = new ServiceB();
                  String outB = serviceB.service();
                	return (outB);
                }
            });

        ExecutorService executor = Executors.newFixedThreadPool(callableList.size());

      String outAA = null;
      String outB = null;
      try {
          List<Future<String>> futures = executor.invokeAll(callableList);
          Future<String> future = null;

          //the ExecutorService retains the order of the callables in the Future List hence
          //first object here is the call to service a and second to service b, irrespective of the
          //order in which the services got invoked
          future = futures.get(0);
          outAA = future.get();
          future = futures.get(1);
          outB = future.get();

        } catch (InterruptedException|ExecutionException e) {
            //output of A and B defaulted to null
        }
        executor.shutdown();

        return(serviceC.service(outAA, outB));
    }
}
