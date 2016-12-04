package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.fdr.FalseDiscoveryRateGlobal;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * FalseDiscoveryRateGlobal for FdrLevel=Peptide and ProteinLevel=PDH
 */
public class FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPDH implements IFalseDiscoveryRateGlobal{
    @Override
    /**
     * Generate FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPDH
     */
    public FalseDiscoveryRateGlobal genererate() {
        try {
            return new FalseDiscoveryRateGlobal(mzids[0],decoyRatio,"",cvTerm,true,"Peptide","PDH");
        } catch (FalseDiscoveryRateGlobalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
