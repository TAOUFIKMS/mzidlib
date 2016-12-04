package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=ProteinGroup and ProteinLevel=PDH
 */
public class FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPDH implements IFalseDiscoveryRateGlobal {
    @Override
    /**
     * Generate FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPDH
     */
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(mzids[0],decoyRatio,"",cvTerm,true,"ProteinGroup","PDH");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
