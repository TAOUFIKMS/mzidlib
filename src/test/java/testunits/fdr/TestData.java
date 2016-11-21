package testunits.fdr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by msproteo on 20/11/16.
 */
@RunWith(Parameterized.class)
public class TestData {
    private final String mzid;
    private final boolean expectedResult;
    private final double filesize;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1000-PE:1000-P:1000-cv:3-AC:1-APC:1-In:0-SIL:900.mzid",true,4665939.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1100-PE:1100-P:1100-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",true,5217480.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1200-PE:1200-P:1200-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",true,5292020.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:2000-PE:2000-P:2000-cv:3-AC:1-APC:1-In:0-SIL:2000.mzid",true,1.0249058E7},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:200-PE:200-P:200-cv:3-AC:1-APC:1-In:0-SIL:100.mzid",true,594946.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:4000-PE:4000-P:4000-cv:3-AC:1-APC:1-In:0-SIL:4000.mzid",true,2.0430664E7},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:400-PE:400-P:400-cv:3-AC:1-APC:1-In:0-SIL:200.mzid",true,1199729.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:300.mzid",true,1706290.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:400.mzid",true,2130061.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:600-PE:600-P:600-cv:3-AC:1-APC:1-In:0-SIL:500.mzid",true,2623639.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:700-PE:700-P:700-cv:3-AC:1-APC:1-In:0-SIL:600.mzid",true,3152362.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:800-PE:800-P:800-cv:3-AC:1-APC:1-In:0-SIL:700.mzid",true,3660522.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:900-PE:900-P:900-cv:3-AC:1-APC:1-In:0-SIL:800.mzid",true,4171915.0}
        });
    }
    public TestData(String mzid, boolean expectedResult,double filesize) {
        this.mzid = mzid;
        this.expectedResult = expectedResult;
        this.filesize = filesize;
    }
    public static void assertFilemMissing(boolean reality, boolean asexpected){
        try{
            assertEquals("File existence test isn't as expected"+ " - passed",reality,asexpected);
        }catch(AssertionError e){
            throw e;
        }
    }
    public static void assertFilesize(double reality, double asexpected){
        try{
            assertEquals("File size didn't match as expected"+ " - passed",reality,asexpected,0);
        }catch(AssertionError e){
            throw e;
        }
    }
    @Test
    public void TestMissingFiles() {
        System.out.println("Test missing file  = "+ this.mzid);
        File file = new File(mzid);
        assertFilemMissing(file.exists(),expectedResult);
        //assertEquals(file.exists(),expectedResult);
    }
    @Test
    public void TestFilesSize() {
        System.out.println("Test file size  = "+ this.mzid);
        File file = new File(mzid);
        if (file.exists()) {

            double bytes = file.length();
            assertFilesize(bytes,filesize);
        }
    }
}
