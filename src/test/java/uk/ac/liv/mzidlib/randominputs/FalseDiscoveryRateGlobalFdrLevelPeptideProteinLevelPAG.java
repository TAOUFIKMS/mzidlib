package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=Peptide and ProteinLevel=PAG
 */
public class FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPAG implements IFalseDiscoveryRateGlobal{
    @Override
    /**
     * Generate FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPAG
     */
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(mzids[0],decoyRatio,"",cvTerm,true,"Peptide","PAG");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}