package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=PSM and ProteinLevel=PAG
 */
public class FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPAG  implements IFalseDiscoveryRateGlobal {
    /**
     *  Generate FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPA
     */
    @Override
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(FalseDiscoveryRateGlobalRandomInputs.mzids[0],FalseDiscoveryRateGlobalRandomInputs.decoyRatio,"",FalseDiscoveryRateGlobalRandomInputs.cvTerm,true,"PSM","PAG");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}