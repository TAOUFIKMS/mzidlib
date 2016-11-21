package testunits.fdr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRate;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by msproteo on 20/11/16.
 */
@RunWith(Parameterized.class)
public class TestComputeFDR {
    private String mzid  ;
    private String outmzid  ;
    private String outcsv  ;
    private String outtsv  ;
    private String outsorted  ;
    private String decoyRatio  ;
    private String searchEngine  ;
    private String decoy  ;
    private FalseDiscoveryRateGlobal FDRI;
    private FalseDiscoveryRate FD;
    private String cvTerm  ;
    private  String allowedEvalues  ;
    private Boolean betterScoresAreLower  ;
    private String fdrLevel  ;
    private String proteinLevel  ;
    private int nfp;
    private int ntp;
    private int nsefdr;
    private int nsdon;
    private int nssfdr;
    private int nse;
    private int nsq;
    private double maxfp;
    private double minfp;
    private double maxtp;
    private double mintp;
    private double maxsefdr;
    private double minsefdr;
    private double maxssfdr;
    private double minssfdr;
    private double maxse;
    private double minse;
    private double maxsq;
    private double minsq;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1000-PE:1000-P:1000-cv:3-AC:1-APC:1-In:0-SIL:900.mzid",3265,3265,3265,3265,3265,3265,3265,49.0,0.0,3167.0,1.0,0,0,0.023333333333333334,0.0,0.0,0.0,0.015236318407960199,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1100-PE:1100-P:1100-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",3630,3630,3630,3630,3630,3630,3630,62.0,0.0,3506.0,1.0,0,0,0.023391812865497075,0.0,0.0,0.0,0.017376681614349777,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1200-PE:1200-P:1200-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",3630,3630,3630,3630,3630,3630,3630,66.0,0.0,3498.0,1.0,0,0,0.023391812865497075,0.0,0.0,0.0,0.018518518518518517,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:2000-PE:2000-P:2000-cv:3-AC:1-APC:1-In:0-SIL:2000.mzid",7180,7180,7180,7180,7180,7180,7180,209.0,0.0,6762.0,1.0,0,0,0.03267411865864144,0.0,0.0,0.0,0.02998135131258069,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:4000-PE:4000-P:4000-cv:3-AC:1-APC:1-In:0-SIL:4000.mzid",14035,14035,14035,14035,14035,14035,14035,769.0,0.0,12497.0,1.0,0,0,0.06295020357031006,0.0,0.0,0.0,0.057967737072214684,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:400-PE:400-P:400-cv:3-AC:1-APC:1-In:0-SIL:200.mzid",724,724,724,724,724,724,724,5.0,0.0,714.0,1.0,0,0,0.007072135785007072,0.0,0.0,0.0,0.006954102920723227,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:300.mzid",1107,1107,1107,1107,1107,1107,1107,6.0,0.0,1095.0,1.0,0,0,0.0054894784995425435,0.0,0.0,0.0,0.005449591280653951,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:400.mzid",1466,1466,1466,1466,1466,1466,1466,11.0,0.0,1444.0,1.0,0,0,0.013452914798206279,0.0,0.0,0.0,0.007560137457044674,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:600-PE:600-P:600-cv:3-AC:1-APC:1-In:0-SIL:500.mzid",1822,1822,1822,1822,1822,1822,1822,16.0,0.0,1790.0,1.0,0,0,0.01001001001001001,0.0,0.0,0.0,0.008859357696566999,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:700-PE:700-P:700-cv:3-AC:1-APC:1-In:0-SIL:600.mzid",2193,2193,2193,2193,2193,2193,2193,27.0,0.0,2139.0,1.0,0,0,0.013561320754716982,0.0,0.0,0.0,0.012465373961218837,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:800-PE:800-P:800-cv:3-AC:1-APC:1-In:0-SIL:700.mzid",2545,2545,2545,2545,2545,2545,2545,35.0,0.0,2475.0,1.0,0,0,0.015282730514518594,0.0,0.0,0.0,0.013944223107569721,0.0},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:900-PE:900-P:900-cv:3-AC:1-APC:1-In:0-SIL:800.mzid",2909,2909,2909,2909,2909,2909,2909,42.0,0.0,2825.0,1.0,0,0,0.016666666666666666,0.0,0.0,0.0,0.014649459365190094,0.0}
        });
    }
    public TestComputeFDR(String mzid, int nfp, int ntp, int nsefdr, int nsdon, int nssfdr, int nse, int nsq, double maxfp, double minfp, double maxtp, double mintp, double maxsefdr, double minsefdr, double maxssfdr, double minssfdr, double maxse, double minse, double maxsq, double minsq) {
        this.mzid = mzid;
        this.nfp = nfp;
        this.ntp = ntp;
        this.nsefdr = nsefdr;
        this.nsdon = nsdon;
        this.nssfdr = nssfdr;
        this.nse = nse;
        this.nsq = nsq;
        this.maxfp = maxfp;
        this.minfp = minfp;
        this.maxtp = maxtp;
        this.mintp = mintp;
        this.maxsefdr = maxsefdr;
        this.minsefdr = minsefdr;
        this.maxssfdr = maxssfdr;
        this.minssfdr = minssfdr;
        this.maxse = maxse;
        this.minse = minse;
        this.maxsq = maxsq;
        this.minsq = minsq;
    }
    public static void assertAttributeInfoEqual(Object object,double reality, double asexpected){
        System.out.println("Test Attribute Information  = "+ object.getClass().getSimpleName());
        try{
            assertEquals("Information doesn't match - failed",reality,asexpected,0);
        }catch(AssertionError e){
            throw e;
        }
    }
    public static void assertAttributeInfoEqual(Object object,int reality, int asexpected){
        System.out.println("Test Attribute Information  = "+ object.getClass().getName());
        try{
            assertEquals("Information doesn't match - failed",(double)reality,(double)asexpected,0);
        }catch(AssertionError e){
            throw e;
        }
    }
    @Before
    public void initialise(){
        //mzid = "src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/Adult_Heart_combined.mzid";
        outmzid = "src/test/data/Test-Scenario/FDR/mzid/outmzid/MASCOT/Mascot_MSMS-_example.mzid";
        outcsv = "src/test/data/Test-Scenario/FDR/mzid/outmzid/MASCOT/Mascot_MSMS_tocsv-example.csv";
        outtsv = "src/test/data/Test-Scenario/FDR/mzid/outmzid/MASCOT/Mascot_MSMS_totsv-example.csv";
        outsorted = "src/test/data/Test-Scenario/FDR/mzid/outmzid/MASCOT/Mascot_MSMS_SortedData-example.csv";
        decoyRatio = "1";
        searchEngine = "Mascot|Omssa|tandem";
        decoy = "";
        cvTerm = "PSI-MS";
        allowedEvalues = "MS:1001330" + ";" + "MS:1001172" + ";" + "MS:1001159" + ";" + "MS:1001328" + ";" + "MS:1002045" + ";" + "MS:1002053";
        betterScoresAreLower = true;
        fdrLevel = "Peptide";
        proteinLevel = "PDH";
    }
    @Test
    public void TestFDRConstructorAttributesSizesandExtremValues(){
        File file = new File(mzid);
        if (file.exists()) {
            System.out.println("FDR Attributes size, max, min on "+mzid);
            FDRI= new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);
            FDRI.computeFDRusingJonesMethod();
            assertAttributeInfoEqual(FDRI.getFP(),FDRI.getFP().size(),nfp);
            assertAttributeInfoEqual(FDRI.getTP(),FDRI.getTP().size(),ntp);
            assertAttributeInfoEqual(FDRI.getSorted_estimatedFDR(),FDRI.getSorted_estimatedFDR().size(),nsefdr);
            assertAttributeInfoEqual(FDRI.getSorted_decoyOrNot(),FDRI.getSorted_decoyOrNot().size(),nsdon);
            assertAttributeInfoEqual(FDRI.getSorted_simpleFDR(),FDRI.getSorted_simpleFDR().size(),nssfdr);
            assertAttributeInfoEqual(FDRI.getSorted_qValues(),FDRI.getSorted_simpleFDR().size(),nssfdr);
            assertAttributeInfoEqual(FDRI.getSorted_simpleFDR(),Collections.min(FDRI.getSorted_qValues()),minsq);
            assertAttributeInfoEqual(FDRI.getSorted_simpleFDR(),Collections.max(FDRI.getSorted_qValues()),maxsq);
        }
    }
}
