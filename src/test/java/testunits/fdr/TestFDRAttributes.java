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

import static org.junit.Assert.assertEquals;

/**
 * Created by msproteo on 20/11/16.
 */
@RunWith(Parameterized.class)
public class TestFDRAttributes {
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
    private int as;
    private int dbs;
    private int pepev;
    private int pep;
    private int cv;
    private int ais;
    private int apc;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1000-PE:1000-P:1000-cv:3-AC:1-APC:1-In:0-SIL:900.mzid",2,1000,1000,1000,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1100-PE:1100-P:1100-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",2,1100,1100,1100,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:1200-PE:1200-P:1200-cv:3-AC:1-APC:1-In:0-SIL:1000.mzid",2,1200,1200,1200,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:2000-PE:2000-P:2000-cv:3-AC:1-APC:1-In:0-SIL:2000.mzid",2,2000,2000,2000,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:200-PE:200-P:200-cv:3-AC:1-APC:1-In:0-SIL:100.mzid",2,200,200,200,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:4000-PE:4000-P:4000-cv:3-AC:1-APC:1-In:0-SIL:4000.mzid",2,4000,4000,4000,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:400-PE:400-P:400-cv:3-AC:1-APC:1-In:0-SIL:200.mzid",2,400,400,400,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:300.mzid",2,500,500,500,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:500-PE:500-P:500-cv:3-AC:1-APC:1-In:0-SIL:400.mzid",2,500,500,500,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:600-PE:600-P:600-cv:3-AC:1-APC:1-In:0-SIL:500.mzid",2,600,600,600,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:700-PE:700-P:700-cv:3-AC:1-APC:1-In:0-SIL:600.mzid",2,700,700,700,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:800-PE:800-P:800-cv:3-AC:1-APC:1-In:0-SIL:700.mzid",2,800,800,800,3,1,1},
                {"src/test/data/Test-Scenario/FDR/mzid/inmzid/COMBINED/AS:2-DBS:900-PE:900-P:900-cv:3-AC:1-APC:1-In:0-SIL:800.mzid",2,900,900,900,3,1,1}
        });
    }
    public TestFDRAttributes(String mzid,int as,int dbs,int pepev,int pep,int cv,int ais,int apc) {
        this.mzid = mzid;
        this.as = as;
        this.dbs = dbs;
        this.pepev = pepev;
        this.pep = pep;
        this.cv = cv;
        this.ais = ais;
        this.apc = apc;

    }
    public static void assertAttributesize(int reality, int asexpected){
        try{
            assertEquals("Size doesn't match - failed",(double)reality,(double)asexpected,0);
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
        searchEngine = "combined";
        decoy = "";
        cvTerm = "PSI-MS";
        allowedEvalues = "MS:1001330" + ";" + "MS:1001172" + ";" + "MS:1001159" + ";" + "MS:1001328" + ";" + "MS:1002045" + ";" + "MS:1002053";
        betterScoresAreLower = true;
        fdrLevel = "Peptide";
        proteinLevel = "PDH";
    }
    @Test
    public void TestFDRConstructorAttributesSizes(){
        File file = new File(mzid);
        if (file.exists()) {
            System.out.println("FDR Attributes size on "+mzid);
            FDRI= new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);
            System.out.println(FDRI.getAnalysisSoftwareList().getAnalysisSoftware().size()+","+FDRI.getdBSequenceHashMap().keySet().size()+","+FDRI.getPeptideEvidenceMap().keySet().size()+","+FDRI.getPeptideHashMap().keySet().size()+","+FDRI.getCvList().getCv().size()+","+FDRI.getAnalysisCollection().getSpectrumIdentification().size()+","+FDRI.getAnalysisProtocolCollection().getSpectrumIdentificationProtocol().size());
            assertAttributesize(FDRI.getAnalysisSoftwareList().getAnalysisSoftware().size(),as);
            assertAttributesize(FDRI.getdBSequenceHashMap().keySet().size(), dbs);
            assertAttributesize(FDRI.getPeptideEvidenceMap().keySet().size(), pepev);
            assertAttributesize(FDRI.getPeptideHashMap().keySet().size(), pep);
            assertAttributesize(FDRI.getCvList().getCv().size(),cv);
            assertAttributesize(FDRI.getAnalysisCollection().getSpectrumIdentification().size(),ais);
            assertAttributesize(FDRI.getAnalysisProtocolCollection().getSpectrumIdentificationProtocol().size(),apc);

        }
    }
}
