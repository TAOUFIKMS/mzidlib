package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=ProteinGroup and ProteinLevel=PAG
 */
public class FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPAG implements IFalseDiscoveryRateGlobal {
    @Override
    /**
     * Generate FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPAG
     */
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(mzids[0],decoyRatio,"",cvTerm,true,"ProteinGroup","PAG");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
