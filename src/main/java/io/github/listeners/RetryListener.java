package io.github.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: Jackson.Wen
 */
public class RetryListener implements IAnnotationTransformer {
    int counter=2;
    @Override
    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstrutor, Method testMethod)
    {
        if (testMethod.getName().equals("ChangeInvocationCountOfMethod")) {
            System.out.println("Changing invocation for the following method: " + testMethod.getName());
            testAnnotation.setInvocationCount(counter);

        }

    }
}