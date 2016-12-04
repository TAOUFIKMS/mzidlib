package uk.ac.liv.mzidlib.randominputs;
import java.util.Random;
/**
 * Class used random inputs (Correct and allowed) for FalseDiscoveryRateGlobal constructor
 */
public class FalseDiscoveryRateGlobalRandomInputs {
    /**
     * mzid file
     */
    public static String mzid;
    /**
     * cvTerm
     */
    public static String cvTerm;
    /**
     * decoy
     */
    public static String decoy;
    /**
     * decoRatio
     */
    public static String decoyRatio;
    /**
     * betterScoresAreLower
     */
    public static boolean betterScoresAreLower;
    /**
     * fdrLevel
     */
    public static String fdrLevel;
    /**
     * proteinLevel
     */
    public static String proteinLevel;
    /**
     * fdrgserialized
     */
    public static String fdrgserialized;

    /**
     * String of random inputs for FalseDiscoveryGlobal constructor
     * @return String
     */
    public static String FalseDiscoveryGlobalInputs() {
        return 	mzid  + " " +
                decoyRatio + " " +
                decoy + " " +
                cvTerm + " " +
                betterScoresAreLower+" "+
                fdrLevel+" "+proteinLevel;
    }

    /**
     * Generate random inputs for FalseDiscoveryGlobal constructor
     * @return String
     */
    public static String FalseDiscoveryGlobalInputsGenerate() {

        return 	mzid()  + " " +
                decoyRatio() + " " +
                decoy()+ " " +
                cvTerm() + " " +
                betterScoresAreLower()+" "+
                fdrLevel()+" "+proteinLevel();
    }

    /**
     * fdrgserialized, File for serialized FalseDiscoveryGlobal object
     * @return String
     */
    public static String fdrgserialized(){
        FalseDiscoveryRateGlobalRandomInputs.fdrgserialized = randomItemFrom(fdrgsserialized);
        return fdrgserialized;
    }
    /**
     * mzid file
     * @return String
     */
    public static String mzid() {
        FalseDiscoveryRateGlobalRandomInputs.mzid = randomItemFrom(mzids);
        return mzid;
    }
    /**
     * cvTerm
     * @return String
     */
    public static String cvTerm() {
        FalseDiscoveryRateGlobalRandomInputs.cvTerm = randomItemFrom(cvTerms);
        return cvTerm ;
    }

    /**
     * decoyRatio
     * @return String
     */
    public static String decoyRatio() {
        Random r = new Random();
        FalseDiscoveryRateGlobalRandomInputs.decoyRatio = String.valueOf(r.nextDouble());
        return decoyRatio;
    }
    /**
     * decoy
     * @return String
     */
    public static String decoy() {
        FalseDiscoveryRateGlobalRandomInputs.decoy = randomItemFrom(decoys);
        return decoy;
    }
    /**
     * betterScoresAreLower
     * @return Boolean
     */
    public static boolean betterScoresAreLower() {
        int flip = randomNumber(0, 1);
        FalseDiscoveryRateGlobalRandomInputs.betterScoresAreLower = (flip == 1);
        return FalseDiscoveryRateGlobalRandomInputs.betterScoresAreLower ;
    }
    /**
     * fdrLevel
     * @return String
     */
    public static String fdrLevel() {
        FalseDiscoveryRateGlobalRandomInputs.fdrLevel = randomItemFrom(fdrLevels);
        return fdrLevel;
    }
    /**
     * proteinLevel
     * @return String
     */
    public static String proteinLevel() {
        FalseDiscoveryRateGlobalRandomInputs.proteinLevel = randomItemFrom(proteinLevels);
        return proteinLevel;
    }
    /**
     * random value from array
     * @return String
     */
    public static String randomItemFrom(String[] array) {
        return array[(randomNumber(0, (array.length - 1) ))];
    }

    /**
     * Generate random index between max and min
     * @param minNumber
     * @param maxNumber
     * @return int
     */
    public static int randomNumber(int minNumber, int maxNumber) {

        if ( minNumber > maxNumber ) {
            minNumber				= 1;
            maxNumber				= 10;
        }
        Random randomGenerator		= new Random();
        int randomNumber = randomGenerator.nextInt(maxNumber - minNumber + 1) + minNumber;
        return randomNumber;
    }

    /**
     * Supporting array for mzid
     */
    public static String[] mzids = {
            "src/test/data/testscenario/fdr/mzid/inmzid/combined/Adult_Heart_combined_search.mzid",
            "src/test/data/testscenario/fdr/mzid/inmzid/mascot/Mascot_MSMS_example.mzid",
            "src/test/data/testscenario/fdr/mzid/inmzid/mascot/55merge_mascot_full.mzid",
            "src/test/data/testscenario/fdr/mzid/inmzid/mascot/Messy.mzid"
    };
    /**
     * Supporting array for fdrgsserialized
     */
    public static String[] fdrgsserialized = {
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:27:756 +0000.ser",
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:31:625 +0000.ser",
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:35:514 +0000.ser",
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:38:312 +0000.ser",
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:42:885 +0000.ser",
            "src/test/data/testscenario/fdr/expectedoutputs/ConstructorAndComputedFDRG-FalseDiscoveryRateGlobal-AT-03-12-16:13:46:521 +0000.ser"
    };
    /**
     * Supporting array for cvTerm
     */
    public static String[] cvTerms = {
            "MS:1001330","MS:1001172","MS:1001159" ,"MS:1001328", "MS:1002045", "MS:1002053"
    };
    /**
     * Supporting array for decoy
     */
    public static String[] decoys = {
            "_REVERSED",
            ""
    };
    /**
     * Supporting array for fdrLevel
     */
    public static String[] fdrLevels = {
            "PSM","Peptide","ProteinGroup"
    };
    /**
     * Supporting array for proteinLevel
     */
    public static String[] proteinLevels = {
         "PAG","PDH"
    };
    /**
     * Supporting array for FalseDiscoveryRateGlobal factory
     */
    public static String[] fdrglevels = {
            "fdrleveleptideproteinlevelpag",
            "fdrlevelpeptideproteinlevelpdh",
            "fdrlevelproteingroupproteinlevelpag",
            "fdrlevelproteingroupproteinlevelpdh",
            "fdrlevelpsmproteinlevelpag",
            "fdrlevelpsmproteinlevelpdh"
    };
}