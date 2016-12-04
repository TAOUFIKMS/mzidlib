package MzidLib;

/**
 * Created by msproteo on 25/11/16.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import uk.ac.liv.mzidlib.fdr.TestFalseDiscoveryRateGlobalAttributes;
import uk.ac.liv.mzidlib.fdr.TestFalseDiscoveryRateGlobalConstructor;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestFalseDiscoveryRateGlobalConstructor.class,
        TestFalseDiscoveryRateGlobalAttributes.class
})

public class AppTest {
}