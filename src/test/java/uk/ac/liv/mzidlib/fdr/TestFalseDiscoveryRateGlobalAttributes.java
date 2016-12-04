package uk.ac.liv.mzidlib.fdr;

import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.liv.mzidlib.mzidobjects.CompareObjects;
import uk.ac.liv.mzidlib.mzidobjects.ObjectIOStreamSedeserialize;
import uk.ac.liv.mzidlib.mzidobjects.ObjectReflection;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.fdrgserialized;
/**
 * Class containing test units allowing to check
 * all FalseDiscoveryRateGlobal attributes.
 * @author Bensellak Taoufik
 */
public class TestFalseDiscoveryRateGlobalAttributes {
    /**
     * Needed SUT fixture
     *FDRSER : String path of serialized FDRG object
     *FDRGKNOWN : FalseDiscoveryRateGlobal deserialized
     *ORFDRGKNOWN : ObjectReflection on FDRGKNOWN
     *FDRG : FalseDiscoveryRateGlobal object (SUT)

     */
    /**
     *FDRSER : String path of serialized FDRG object
     */
    static String FDRSER;
    /**
     *FDRGKNOWN : FalseDiscoveryRateGlobal deserialized
     */
    static FalseDiscoveryRateGlobal FDRGKNOWN;
    /**
     *ORFDRGKNOWN : ObjectReflection on FDRGKNOWN
     */
    static ObjectReflection ORFDRGKNOWN;
    /**
     *FDRG : FalseDiscoveryRateGlobal object (SUT)
     */
    static FalseDiscoveryRateGlobal FDRG;
    /**
     *COMPAREFDGRS : instance of class CompareObjects
     */
    static CompareObjects COMPAREFDGRS;
    /**
     * Getter for static field FDRGKNOWN
     */
    public FalseDiscoveryRateGlobal getFDRGKNOWN(){
        return FDRGKNOWN;
    }
    /**
     * Getter for static field FDRSER
     */
    public String getFDRSER(){
        return FDRSER;
    }
    /**
     * Getter for static field ORFDRGKNOWN
     */
    public static ObjectReflection getORFDRGKNOWN() {
        return ORFDRGKNOWN;
    }
    /**
     * Getter for static field COMPAREFDGRS
     */
    public static CompareObjects getCOMPAREFDGRS() {
        return COMPAREFDGRS;
    }
    /**
     * Getter for static field FDRG
     */
    public static FalseDiscoveryRateGlobal getFDRG() {
        return FDRG;
    }

    /**
     *Preparing SUT and all common needed ressources
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Setting up attributes SUT fixture");
        FDRSER =fdrgserialized();
        System.out.println("Generating random serialized file : "+FDRSER);
        System.out.println("Create FalseDiscoveryRateGlobal object and its reflection object (For both serialized and new constructed)");
        FDRGKNOWN = ObjectIOStreamSedeserialize.deserialize(FDRSER,FalseDiscoveryRateGlobal.class);
        ORFDRGKNOWN = new ObjectReflection(FDRGKNOWN);
        FDRG = new FalseDiscoveryRateGlobal((String)ORFDRGKNOWN.getFieldObject("mzid"),String.valueOf(ORFDRGKNOWN.getFieldObject("decoyRatio")),(String) ORFDRGKNOWN.getFieldObject("decoy"),(String) ORFDRGKNOWN.getFieldObject("cvTerm"),(boolean) ORFDRGKNOWN.getFieldObject("betterScoresAreLower"),(String) ORFDRGKNOWN.getFieldObject("fdrLevel"),(String)ORFDRGKNOWN.getFieldObject("proteinLevel"));
        COMPAREFDGRS = new CompareObjects(FDRGKNOWN,FDRG);
    }
    /**
     * Cleaning up after all tests are done (TearDown)
     */
    @AfterClass
    public static void setUpAfterClass() throws Exception {
        System.out.println("Cleaning up SUT fixture");
        FDRSER ="";
        FDRGKNOWN.clearAllData();
        FDRGKNOWN = null;
        FDRG.clearAllData();
        FDRG =null;
        ORFDRGKNOWN.invokeMethod("clearAllData");
        ORFDRGKNOWN = null;
        COMPAREFDGRS = null;
    }
    /**
     *Testing fields
     */
    @Test
    public void compareFieldsbetweenKnownAndUnknownFDRG(){
        System.out.println("Comparing fields");
        MatcherAssert.assertThat("Fields aren't the same",COMPAREFDGRS.compareFields(),is(equalTo(true)));
    }
    /**
     * Testing primitive attributes
     */
    @Test
    public void comparePrimitiveAttributesbetweenKnownAndUnknownFDRG() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Comparing primitive attributes");
        MatcherAssert.assertThat("Primitive attributes didn't match",COMPAREFDGRS.comparePrimitiveFields(),is(equalTo(true)));
    }
    /**
     * Testing nonprimitive attributes
     */
    @Test
    public void compareNonPrimitiveAttributesbetweenKnownAndUnknownFDRG() throws NoSuchFieldException, IllegalAccessException, JAXBException, IOException, ClassNotFoundException {
        System.out.println("Comparing nonprimitive attributes");
        MatcherAssert.assertThat("Nonprimitive attributes didn't match",COMPAREFDGRS.compareNonPrimitiveFields(),is(equalTo(true)));
    }
    /**
     *Testing type Array attributes
     */
    @Test
    public void compareArrayAttributesbetweenKnownAndUnknownFDRG() throws NoSuchFieldException, IllegalAccessException, JAXBException, IOException, ClassNotFoundException {
        System.out.println("Comparing arrays attributes");
        MatcherAssert.assertThat("Array attributes didn't match",COMPAREFDGRS.compareArraysFields(),is(equalTo(true)));
    }
    /**
     * Testing type Map attributes
     */
    //@Test
    @Ignore("It takes ages")
    public void compareMapAttributesbetweenKnownAndUnknownFDRG() throws NoSuchFieldException, IllegalAccessException, JAXBException, IOException, ClassNotFoundException {
        System.out.println("Comparing maps attributes");
        MatcherAssert.assertThat("Map attributes didn't match",COMPAREFDGRS.compareMapsFields(),is(equalTo(true)));
    }
}
