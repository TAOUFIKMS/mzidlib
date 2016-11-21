package testunits.fdr;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by msproteo on 10/11/16.
 */

public class TestsRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestComputeFDR.class,TestData.class,TestFDRAttributes.class,TestFDRConstructor.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
            System.out.println(failure.getDescription());
            System.out.println(failure.getTestHeader());
            System.out.println(failure.getTrace());
        }

        System.out.println(result.wasSuccessful());
    }
}