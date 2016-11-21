package testunits.fdr;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRate;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by msproteo on 05/11/16.
 */
@RunWith(Parameterized.class)
public class TestFDRConstructor extends TestCase{
    private boolean bAnalysisSoftware;
    private boolean bcv;
    private boolean bPeptideEvidence;
    private boolean DBSequences;
    private boolean bPeptides;
    private boolean bAnalysisProtocolCollection;
    private boolean bInputs;
    private boolean bSpectrumIdentificationResul;
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
    private boolean expectedResult;
    private double filesize;
    private boolean fdrin;
    private boolean acn;
    private boolean apcn;
    private boolean asn;
    private boolean auditn;
    private boolean cvn;
    private boolean dbsn;
    private boolean inputsn;
    private boolean providern;
    private boolean sdrn;
    private boolean pepnamesn;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1000-PE:1000-P:1000-cv:3-AC:1-APC:1-In:0-SIL:900.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1100-PE:1100-P:1100-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1200-PE:1200-P:1200-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:2000-PE:2000-P:2000-cv:3-AC:1-APC:1-In:0-SIL:2000.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:200-PE:200-P:200-cv:3-AC:1-APC:1-In:0-SIL:100.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:4000-PE:4000-P:4000-cv:3-AC:1-APC:1-In:0-SIL:4000.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:400-PE:400-P:400-cv:3-AC:1-APC:1-In:0-SIL:200.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:300.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:400.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:600-PE:600-P:600-cv:3-AC:1-APC:1-In:0-SIL:500.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:700-PE:700-P:700-cv:3-AC:1-APC:1-In:0-SIL:600.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:800-PE:800-P:800-cv:3-AC:1-APC:1-In:0-SIL:700.mzid",true,true,true,true,false,true,true,true,false,true,true},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:900-PE:900-P:900-cv:3-AC:1-APC:1-In:0-SIL:800.mzid",true,true,true,true,false,true,true,true,false,true,true}
        });
    }
    public TestFDRConstructor(String mzid, boolean fdrin, boolean acn, boolean apcn, boolean asn, boolean auditn, boolean cvn, boolean dbsn, boolean inputsn, boolean providern, boolean sdrn, boolean pepnamesn) {
        this.mzid = mzid;
        this.fdrin  = fdrin;
        this.acn  = acn;
        this.apcn  = apcn;
        this.asn  = asn;
        this.auditn  = auditn;
        this.cvn  = cvn;
        this.dbsn  = dbsn;
        this.inputsn  = inputsn;
        this.providern  = providern;
        this.sdrn  = sdrn;
        this.pepnamesn  = pepnamesn;
    }
    public static void TestFDRAttributeNotNull(Object object,boolean asexpected){
        System.out.println("Test attribute not null  = "+ object.getClass().getSimpleName());
        if(asexpected){
            assertNotNull(object.getClass().getName()+" Is null  : unexepected"+ " - failed",object);
            //System.out.println(object.getClass().getName()+" Isn't null as exepected"+ " - passed");
        }else{
            assertNull(object.getClass().getName()+" Isn't null as exepected"+  " - failed",object);
            //System.out.println(object.getClass().getName()+" Is null as exepected"+  " - failed");
        }

    }
    public static void TestFDRAttributeNull(Object object,boolean asexpected){
        System.out.println("Test attribute is null  = "+ object.getClass().getSimpleName());
        if(!asexpected){
            assertNotNull(object.getClass().getName()+" Is not null  : unexepected"+ " - failed",object);
            //System.out.println(object.getClass().getName()+" Isn't null as exepected"+ " - passed");
        }else{
            assertNull(object.getClass().getName()+" Isn't null as exepected"+  " - failed",object);
            //System.out.println(object.getClass().getName()+" Is null as exepected"+  " - failed");
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
        searchEngine = "combined";
        decoy = "";
        cvTerm = "MS:1001172";
        allowedEvalues = "MS:1001330" + ";" + "MS:1001172" + ";" + "MS:1001159" + ";" + "MS:1001328" + ";" + "MS:1002045" + ";" + "MS:1002053";
        betterScoresAreLower = true;
        fdrLevel = "PSM";
        proteinLevel = "PDH";
    }
    @Test
    public void TestFDRConstructorAttributes(){
        File file = new File(mzid);
        if (file.exists()) {
            System.out.println("FDR constructor on "+mzid);
            FDRI= new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);
            TestFDRAttributeNotNull(FDRI,fdrin);
            TestFDRAttributeNotNull(FDRI.getAnalysisCollection(),acn);
            TestFDRAttributeNotNull(FDRI.getAnalysisProtocolCollection(),apcn);
            TestFDRAttributeNotNull(FDRI.getAnalysisSoftwareList(),asn);
            TestFDRAttributeNotNull(FDRI.getCvList(),cvn);
            TestFDRAttributeNotNull(FDRI.getdBSequenceHashMap(),dbsn);
            TestFDRAttributeNotNull(FDRI.getInputs(),inputsn);
            TestFDRAttributeNotNull(FDRI.getSearchDatabase_Ref(),sdrn);
            TestFDRAttributeNotNull(FDRI.getSorted_peptideNames(),pepnamesn);
            //TestFDRAttributeNull(FDRI.getAuditCollection(),auditn);
            //TestFDRAttributeNull(FDRI.getProvider(),providern);


        }
    }
    public static void main(String[] args) {

    }
}
