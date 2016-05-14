package com.test.java;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Hello world!
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
                	return (new ServiceA().service());
                }
            });

        callableList.add(new Callable<String>(){
                public String call() throws Exception {
                	return (new ServiceB().service());
                }
            });

        ExecutorService executor = Executors.newFixedThreadPool(callableList.size());

      String outA = null;
      String outB = null;
      try {
          List<Future<String>> futures = executor.invokeAll(callableList);
          Future<String> future = null;

          future = futures.get(0);
          outA = future.get();
          future = futures.get(1);
          outB = future.get();

        } catch (InterruptedException|ExecutionException e) {
            //output of A and B defaulted to null
        }


        return(serviceC.service(serviceAA.service(outA), outB));
    }
}