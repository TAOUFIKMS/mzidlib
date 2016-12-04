package uk.ac.liv.mzidlib.randominputs;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
/**
 * FalseDiscoveryRateGlobal Factory (Factory Pattern)
 */
public class FalseDiscoveryRateGlobalFactory {

    /**
     * Generate FalseDiscoveryRateGlobal by levels
     * @param FDRGTYPE
     * @return IFalseDiscoveryRateGlobal
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
        public IFalseDiscoveryRateGlobal getFDRG(String FDRGTYPE) throws FalseDiscoveryRateGlobalArgumentException {
            if(FDRGTYPE == null){
                return null;
            }
            switch (FDRGTYPE.toLowerCase()){
                case "fdrleveleptideproteinlevelpag" :
                    return new FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPAG();
                case "fdrlevelpeptideproteinlevelpdh" :
                    return new FalseDiscoveryRateGlobalFdrLevelPeptideProteinLevelPDH();
                case "fdrlevelproteingroupproteinlevelpag" :
                    return new FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPAG();
                case "fdrlevelproteingroupproteinlevelpdh" :
                    return new FalseDiscoveryRateGlobalFdrLevelProteinGroupProteinLevelPDH();
                case "fdrlevelpsmproteinlevelpag" :
                    return new FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPAG();
                case "fdrlevelpsmproteinlevelpdh" :
                    return new FalseDiscoveryRateGlobalFdrLevelPSMProteinLevelPDH();

            }/*
            if(FDRGTYPE.equalsIgnoreCase("FdrLevelPeptideProteinLevelPAG")){

            } else if(FDRGTYPE.equalsIgnoreCase("FdrLevelPeptideProteinLevelPDH")){


            } else if(FDRGTYPE.equalsIgnoreCase("FdrLevelProteinGroupProteinLevelPAG")){

            } else if(FDRGTYPE.equalsIgnoreCase("FdrLevelProteinGroupProteinLevelPDH")){

            } else if(FDRGTYPE.equalsIgnoreCase("FdrLevelPSMProteinLevelPAG")){

            } else if(FDRGTYPE.equalsIgnoreCase("FdrLevelPSMProteinLevelPDH")){

            }*/


            return null;
        }
}
