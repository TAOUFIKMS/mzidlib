package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=PSM and ProteinLevel=PDH
 */
public class FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPDH implements IFalseDiscoveryRateGlobal {
    @Override
    /**
     * Generate FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPDH
     */
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(mzids[0],decoyRatio,"",cvTerm,true,"PSM","PDH");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}