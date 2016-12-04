package uk.ac.liv.mzidlib.fdr;


import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.*;
import org.junit.rules.ExpectedException;
import uk.ac.ebi.jmzidml.model.mzidml.*;
import uk.ac.liv.mzidlib.exceptions.FalseDiscoveryRateGlobalArgumentException;
import uk.ac.liv.mzidlib.mzidobjects.ObjectIOStreamSedeserialize;
import uk.ac.liv.mzidlib.mzidobjects.ObjectReflection;
import uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalFactory;
import uk.ac.liv.mzidlib.randominputs.IFalseDiscoveryRateGlobal;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static uk.ac.liv.mzidlib.randominputs.FalseDiscoveryRateGlobalRandomInputs.*;
/**
 * Class containing test units allowing to check
 * FalseDiscoveryRateGlobal constructor behaviour and some metadata values.
 * @author Bensellak Taoufik
 */
public class TestFalseDiscoveryRateGlobalConstructor {
    /**
     * Rule for handling exceptions
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /**
     *FDRG : FalseDiscoveryRateGlobal instance
     */
    static FalseDiscoveryRateGlobal FDRG;
    /**
     *ORFDRG : ObjectReflection on FDRG
     */
    static ObjectReflection ORFDRG;

    /**
     * Getter for FDRG
     * @return FalseDiscoveryRateGlobal
     */
    public static FalseDiscoveryRateGlobal getFDRG() {
        return FDRG;
    }

    /**
     * Getter for ORFDRG
     * @return ObjectReflection
     */
    public static ObjectReflection getORFDRG() {
        return ORFDRG;
    }

    /**
     * Fixture setup (SUT)
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Setting up constuctor SUT fixture");
        System.out.println("Generating random inputs for FalseDiscoveryRateGlobal : "+FalseDiscoveryGlobalInputsGenerate());
        System.out.println("Create FalseDiscoveryRateGlobal object and its reflection objects");
        FDRG = new FalseDiscoveryRateGlobal(mzids[1],decoyRatio,"",cvTerm,true,fdrLevel,proteinLevel);
        ORFDRG = new ObjectReflection(FDRG);

    }

    /**
     * Cleaning up after all tests are done (TearDown)
     * @throws Exception
     */
    @AfterClass
    public static void setUpAfterClass() throws Exception {
        System.out.println("Cleaning up SUT fixture");
        FDRG.clearAllData();
        FDRG = null;
        ORFDRG.invokeMethod("clearAllData");
        ORFDRG = null;
    }
    /**
     * Wrong mzid file
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setWrongMZIDThrowsException() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on wrong mzid");
        thrown.expect(IllegalArgumentException.class);
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzids[3],decoyRatio,"",cvTerm,true,fdrLevel,proteinLevel);
    }
    /**
     * cvTerm is empty
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void cvTermSetEmptyThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on empty cvTerm");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("Check cvTerm input (Null or empty) allowed evalue");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,"",betterScoresAreLower,"PSM",proteinLevel);

    }

    /**
     * cvTerm is null
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void cvTermSetNullThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing NullPointerException on null cvTerm");
        thrown.expect(NullPointerException.class);
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,null,betterScoresAreLower,fdrLevel,proteinLevel);
    }

    /**
     * cvTerm value not allowed
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void cvTermSetNotEmptyButNotAllowedThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on allowed evalues cvTerm");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("Check cvTerm input allowed evalues");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,"cvTerm",betterScoresAreLower,fdrLevel,proteinLevel);

    }

    /**
     * fdrLevel value not allowed
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void fdrLevelSetNotEmptyButNotAllowedThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on allowed fdrLevel");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("FDR level should be PSM, Peptide or ProteinGroup");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,cvTerm,betterScoresAreLower,"fdrLevel",proteinLevel);

    }

    /**
     * proteinLevel value not allowed
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void proteinLevelSetNotEmptyButNotAllowedThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on allowed proteinLevel");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("Protein level should be PAG or PDH");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,decoyRatio,decoy,cvTerm,betterScoresAreLower,fdrLevel,"proteinLevel");

    }

    /**
     * decoy value not allowed
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void decoySetNotEmptyButWrongThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on wrong decoy");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("Error - no decoy value set, need to use alternative constructor");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzids[1],decoyRatio,"@",cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);

    }

    /**
     * decoyRation should be a double
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void decoyRatioSetNotEmptyButNotDoubleThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing NumberFormatException on decoyRatio");
        thrown.expect(NumberFormatException.class);
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,"decoyRatio",decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);

    }

    /**
     * decoy value should be positive
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void decoyRatioSetNotEmptyButNegativeThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on negative decoyRatio");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("decoyRatio should be positive");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzid,"-1",decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);

    }

    /**
     * MZID file is missing
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    @Test
    public void mzidFileMissingShoudThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException {
        System.out.println("Throwing IllegalStateException on missing mzid file");
        thrown.expect(IllegalStateException.class);
        //thrown.expectMessage("decoyRatio should be positive");
        FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal("missing.mzid",decoyRatio,decoy,cvTerm,betterScoresAreLower,fdrLevel,proteinLevel);

    }

    /**
     * Check if CvList is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectCvMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking Cv metadata");
        CvList cvList = (CvList) ORFDRG.getFieldObject("cvList");
        MatcherAssert.assertThat("cvList size din't match : 3",cvList.getCv().size(),is(equalTo(3)));
        MatcherAssert.assertThat("Full name din't match : Proteomics Standards Initiative Mass Spectrometry Vocabularies ",cvList.getCv().get(0).getFullName(),is(equalTo("Proteomics Standards Initiative Mass Spectrometry Vocabularies")));
        MatcherAssert.assertThat("Id din't match : PSI-MS ",cvList.getCv().get(0).getId(),is(equalTo("PSI-MS")));
        MatcherAssert.assertThat("Uri didn't match : http://psidev.cvs.sourceforge.net/viewvc/*checkout*/psidev/psi/psi-ms/mzML/controlledVocabulary/psi-ms.obo",cvList.getCv().get(0).getUri(),is(equalTo("http://psidev.cvs.sourceforge.net/viewvc/*checkout*/psidev/psi/psi-ms/mzML/controlledVocabulary/psi-ms.obo")));
        MatcherAssert.assertThat("Version didn't match : 2.25.0",cvList.getCv().get(0).getVersion(),is(equalTo("2.25.0")));

    }

    /**
     * Check if AnalysisSoftwareList is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectAnalysisSoftwareListMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking AnalysisSoftwareList metadata");
        AnalysisSoftwareList analysisSoftwareList = (AnalysisSoftwareList) ORFDRG.getFieldObject("analysisSoftwareList");
        MatcherAssert.assertThat("AnalysisSoftwareList size didn't match : 2",analysisSoftwareList.getAnalysisSoftware().size(),is(equalTo(2)));
        MatcherAssert.assertThat("AnalysisSoftwareList version didn't match : 2.2.03",analysisSoftwareList.getAnalysisSoftware().get(0).getVersion(),is(equalTo("2.2.03")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->customisations didn't match : No customisations ",analysisSoftwareList.getAnalysisSoftware().get(0).getCustomizations().replaceAll("\n",""),is(equalTo("                No customisations            ")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->Uri din't match : http://www.matrixscience.com/search_form_select.html",analysisSoftwareList.getAnalysisSoftware().get(0).getUri(),is(equalTo("http://www.matrixscience.com/search_form_select.html")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->Id din't match : AS_mascot_server",analysisSoftwareList.getAnalysisSoftware().get(0).getId(),is(equalTo("AS_mascot_server")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->SoftwareName->CvParam->Accession : MS:1001207",analysisSoftwareList.getAnalysisSoftware().get(0).getSoftwareName().getCvParam().getAccession(),is(equalTo("MS:1001207")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->SoftwareName->CvParam->CvRef din't match : PSI-MS",analysisSoftwareList.getAnalysisSoftware().get(0).getSoftwareName().getCvParam().getCvRef(),is(equalTo("PSI-MS")));
        MatcherAssert.assertThat("AnalysisSoftware->AnalysisSoftware->SoftwareName->CvParam->Name din't match : Mascot",analysisSoftwareList.getAnalysisSoftware().get(0).getSoftwareName().getCvParam().getName(),is(equalTo("Mascot")));
        MatcherAssert.assertThat("AnalysisSoftware->ContactRole->Role->CvParam->Accession : MS:1001267",analysisSoftwareList.getAnalysisSoftware().get(0).getContactRole().getRole().getCvParam().getAccession(),is(equalTo("MS:1001267")));
        MatcherAssert.assertThat("AnalysisSoftware->ContactRole->Role->CvParam->CvRef : PSI-MS",analysisSoftwareList.getAnalysisSoftware().get(0).getContactRole().getRole().getCvParam().getCvRef(),is(equalTo("PSI-MS")));
        MatcherAssert.assertThat("AnalysisSoftware->ContactRole->Role->CvParam->Name din't match : software vendor",analysisSoftwareList.getAnalysisSoftware().get(0).getContactRole().getRole().getCvParam().getName(),is(equalTo("software vendor")));

    }

    /**
     * Check if AuditCollection is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectAuditCollectionMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking AuditCollection metadata");
        AuditCollection auditCollection = (AuditCollection) ORFDRG.getFieldObject("auditCollection");
        MatcherAssert.assertThat("AuditCollection persons size didn't match : 2",auditCollection.getPerson().size(),is(equalTo(2)));
        MatcherAssert.assertThat("AuditCollection organizations size didn't match : 2",auditCollection.getOrganization().size(),is(equalTo(2)));
        MatcherAssert.assertThat("AuditCollection persons and organization size didn't match : 4",auditCollection.getPersonOrOrganization().size(),is(equalTo(4)));
        MatcherAssert.assertThat("AuditCollection person name didn't match : null",auditCollection.getPerson().get(1).getName(),is(equalTo(null)));
        MatcherAssert.assertThat("AuditCollection person first name didn't match : \"\"",auditCollection.getPerson().get(1).getFirstName(),is(equalTo("")));
        MatcherAssert.assertThat("AuditCollection person last name didn't match : Some Person",auditCollection.getPerson().get(1).getLastName(),is(equalTo("Some Person")));
        MatcherAssert.assertThat("AuditCollection person id didn't match : PERSON_DOC_OWNER",auditCollection.getPerson().get(1).getId(),is(equalTo("PERSON_DOC_OWNER")));
        MatcherAssert.assertThat("AuditCollection person->cvParam size didn't match : 1",auditCollection.getPerson().get(1).getCvParam().size(),is(equalTo(1)));
        MatcherAssert.assertThat("AuditCollection person->UserParam size didn't match : 0",auditCollection.getPerson().get(1).getUserParam().size(),is(equalTo(0)));
        MatcherAssert.assertThat("AuditCollection person cvParam accession didn't match : MS:1000589",auditCollection.getPerson().get(1).getCvParam().get(0).getAccession(),is(equalTo("MS:1000589")));
        MatcherAssert.assertThat("AuditCollection person cvParam name didn't match : contact email",auditCollection.getPerson().get(1).getCvParam().get(0).getName(),is(equalTo("contact email")));
        MatcherAssert.assertThat("AuditCollection person cvParam cvRef didn't match : PSI-MS",auditCollection.getPerson().get(1).getCvParam().get(0).getCvRef(),is(equalTo("PSI-MS")));
        MatcherAssert.assertThat("AuditCollection person cvParam value didn't match : someone@someuniversity.edu",auditCollection.getPerson().get(1).getCvParam().get(0).getValue(),is(equalTo("someone@someuniversity.edu")));
        MatcherAssert.assertThat("AuditCollection person cvParam->Affiliation->OrganizationRef didn't match : ORG_MSL",auditCollection.getPerson().get(1).getAffiliation().get(0).getOrganizationRef(),is(equalTo("ORG_MSL")));
        MatcherAssert.assertThat("AuditCollection organization->Id didn't match : ORG_DOC_OWNER",auditCollection.getOrganization().get(1).getId(),is(equalTo("ORG_DOC_OWNER")));
        MatcherAssert.assertThat("AuditCollection organization->Parent->OrganizationRef didn't match : ORG_MSL",auditCollection.getOrganization().get(1).getParent().getOrganizationRef(),is(equalTo("ORG_MSL")));
    }

    /**
     * Check if Provider is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectProviderMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking Provider metadata");
        Provider provider = (Provider) ORFDRG.getFieldObject("provider");
        MatcherAssert.assertThat("Provider id didn't match : PROVIDER",provider.getId(),is(equalTo("PROVIDER")));
        MatcherAssert.assertThat("Provider->ContactRole->Role->CvParam->Accession didn't match : MS:1001271",provider.getContactRole().getRole().getCvParam().getAccession(),is(equalTo("MS:1001271")));
        MatcherAssert.assertThat("Provider->ContactRole->Role->CvParam->Name didn't match : researcher",provider.getContactRole().getRole().getCvParam().getName(),is(equalTo("researcher")));
        MatcherAssert.assertThat("Provider->ContactRole->Role->CvParam->CvRef didn't match : PSI-MS",provider.getContactRole().getRole().getCvParam().getCvRef(),is(equalTo("PSI-MS")));
    }

    /**
     * Check if AnalysisProtocolCollection is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectAnalysisProtocolCollectionMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking AnalysisProtocolCollection metadata");
        AnalysisProtocolCollection analysisProtocolCollection = (AnalysisProtocolCollection) ORFDRG.getFieldObject("analysisProtocolCollection");
        MatcherAssert.assertThat("AnalysisProtocolCollection SpectrumIdentificationProtocol size didn't match : 1",analysisProtocolCollection.getSpectrumIdentificationProtocol().size(),is(equalTo(1)));
        MatcherAssert.assertThat("AnalysisProtocolCollection ProteinDetectionProtocol didn't match : PDP_MascotParser_1",analysisProtocolCollection.getProteinDetectionProtocol().getId(),is(equalTo("PDP_MascotParser_1")));
        MatcherAssert.assertThat("AnalysisProtocolCollection->ProteinDetectionProtocol->AnalysisSoftwareRef didn't match : AS_mascot_parser",analysisProtocolCollection.getProteinDetectionProtocol().getAnalysisSoftwareRef(),is(equalTo("AS_mascot_parser")));
        MatcherAssert.assertThat("AnalysisProtocolCollection->SpectrumIdentificationProtocol->Enzymes->Name didn't match : null",analysisProtocolCollection.getSpectrumIdentificationProtocol().get(0).getEnzymes().getEnzyme().get(0).getName(),is(equalTo(null)));
        MatcherAssert.assertThat("AnalysisProtocolCollection->SpectrumIdentificationProtocol->ModificationParams->SearchModification->CvParam->Name didn't match : Oxidation",analysisProtocolCollection.getSpectrumIdentificationProtocol().get(0).getModificationParams().getSearchModification().get(0).getCvParam().get(0).getName(),is(equalTo("Oxidation")));
        MatcherAssert.assertThat("AnalysisProtocolCollection->ProteinDetectionProtocol->Threshold->CvParam->Name : no threshold",analysisProtocolCollection.getProteinDetectionProtocol().getThreshold().getCvParam().get(0).getName(),is(equalTo("no threshold")));
        MatcherAssert.assertThat("AnalysisProtocolCollection->SpectrumIdentificationProtocol->Enzymes->Enzyme->Name->UserParam->Value didn't match : CNBr+Trypsin",analysisProtocolCollection.getSpectrumIdentificationProtocol().get(0).getEnzymes().getEnzyme().get(0).getEnzymeName().getUserParam().get(0).getValue(),is(equalTo("CNBr+Trypsin")));

    }

    /**
     * Check if AnalysisCollection is correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectAnalysisCollectionMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking AnalysisCollection metadata");
        AnalysisCollection analysisCollection = (AnalysisCollection) ORFDRG.getFieldObject("analysisCollection");
        MatcherAssert.assertThat("AnalysisCollection SpectrumIdentification size didn't : 1",analysisCollection.getSpectrumIdentification().size(),is(equalTo(1)));
        MatcherAssert.assertThat("AnalysisCollection->SpectrumIdentification->SearchDatabaseRef->SearchDatabaseRef didn't match : SDB_SwissProt",analysisCollection.getSpectrumIdentification().get(0).getSearchDatabaseRef().get(0).getSearchDatabaseRef(),is(equalTo("SDB_SwissProt")));
        MatcherAssert.assertThat("AnalysisCollection->SpectrumIdentification->SpectrumIdentificationListRef didn't match : SIL_1",analysisCollection.getSpectrumIdentification().get(0).getSpectrumIdentificationListRef(),is(equalTo("SIL_1")));
        MatcherAssert.assertThat("AnalysisCollection->ProteinDetection->ProteinDetectionListRef didn't match : PDL_1",analysisCollection.getProteinDetection().getProteinDetectionListRef(),is(equalTo("PDL_1")));
        MatcherAssert.assertThat("AnalysisCollection->ProteinDetection->SpectrumIdentification->InputSpectrumIdentifications->SpectrumIdentificationListRef didn't match : SIL_1",analysisCollection.getProteinDetection().getInputSpectrumIdentifications().get(0).getSpectrumIdentificationListRef(),is(equalTo("SIL_1")));

    }

    /**
     * Check if Inputs are correct (slightly)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectInputsMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking Inputs metadata");
        Inputs inputs = (Inputs) ORFDRG.getFieldObject("inputs");
        MatcherAssert.assertThat("Inputs SpectraData size didn't match : 1",inputs.getSpectraData().size(),is(equalTo(1)));
        MatcherAssert.assertThat("Inputs SearchDatabase size didn't match : 1",inputs.getSearchDatabase().size(),is(equalTo(1)));
        MatcherAssert.assertThat("Inputs SourceFile size didn't match : 1",inputs.getSourceFile().size(),is(equalTo(1)));
        MatcherAssert.assertThat("Inputs->SourceFile->Location didn't match : file:///../data/F001350.dat",inputs.getSourceFile().get(0).getLocation(),is(equalTo("file:///../data/F001350.dat")));
        MatcherAssert.assertThat("Inputs->SpectraData->SpectrumIDFormat->CvParam->Name didn't match: Mascot query number",inputs.getSpectraData().get(0).getSpectrumIDFormat().getCvParam().getName(),is(equalTo("Mascot query number")));

    }

    /**
     * Check is SearchDatabase_Ref is correct
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectSearchDatabase_RefMetadata() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking SearchDatabase_Ref metadata");
        String searchDatabase_Ref  = (String) ORFDRG.getFieldObject("searchDatabase_Ref");
        MatcherAssert.assertThat("SearchDatabase_Ref didn't match : SDB_SwissProt",searchDatabase_Ref,is(equalTo("SDB_SwissProt")));
    }

    /**
     * Check is SequenceCollection is correct (Slightly)(By Ids)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectSequenceCollection() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking SequenceCollection");
        List<DBSequence> dBSequenceList  = (List<DBSequence>) ORFDRG.getFieldObject("dBSequenceList");
        MatcherAssert.assertThat("BSequenceList size didn't match : 46",dBSequenceList.size(),is(equalTo(46)));
        MatcherAssert.assertThat("BSequenceList->SearchDatabaseRe didn't match : SDB_SwissProt",dBSequenceList.get(0).getSearchDatabaseRef(),is(equalTo("SDB_SwissProt")));
        MatcherAssert.assertThat("BSequenceList id didn't match : DBSeq_HSP7D_MANSE",dBSequenceList.get(0).getId(),is(equalTo("DBSeq_HSP7D_MANSE")));
        MatcherAssert.assertThat("BSequenceList->CvParam->Value didn't match : Manduca sexta",dBSequenceList.get(0).getCvParam().get(1).getValue(),is(equalTo("Manduca sexta")));
        MatcherAssert.assertThat("BSequenceList last element id didn't match : DBSeq_HSP7D_DROME",dBSequenceList.get(dBSequenceList.size()-1).getId(),is(equalTo("DBSeq_HSP7D_DROME")));
        MatcherAssert.assertThat("BSequenceList last element cvparam's value didn't match : Drosophila melanogaster",dBSequenceList.get(dBSequenceList.size()-1).getCvParam().get(1).getValue(),is(equalTo("Drosophila melanogaster")));
        MatcherAssert.assertThat("BSequenceList->Seq size didn't match : 652",dBSequenceList.get(0).getSeq().length(),is(equalTo(652)));

    }

    /**
     * Check if BSequenceList is in BSequenceMap (By Ids)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void dBSequenceListIndBSequenceMap() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking dBSequenceList and dBSequenceMap");
        List<DBSequence> dBSequenceList  = (List<DBSequence>) ORFDRG.getFieldObject("dBSequenceList");
        List<DBSequence> dBSequenceList1 = new ArrayList<>(dBSequenceList);
        Map<String, DBSequence> BSequenceMap = (Map<String, DBSequence>) ORFDRG.getFieldObject("dBSequenceMap");
        Comparator<DBSequence> dbSequenceComparator = new Comparator<DBSequence>() {
            public int compare(DBSequence dbs1, DBSequence dbs2) {
                List<String> idstosort = new ArrayList<String>();
                if(dbs1.getId().equals(dbs2.getId())){
                    return 0;
                }
                idstosort.add(dbs1.getId());
                idstosort.add(dbs2.getId());
                Collections.sort(idstosort);
                if(idstosort.get(0).equals(dbs1.getId())){
                    return -1;
                }else{
                    return 1;
                }
            }
        };
        List<DBSequence> dbSequenceListfrommap = new ArrayList<DBSequence>(BSequenceMap.values());
        Collections.sort(dBSequenceList1,dbSequenceComparator);
        Collections.sort(dbSequenceListfrommap,dbSequenceComparator);
        MatcherAssert.assertThat("dBSequenceMap values doesn't contain all dBSequenceList ",BSequenceMap.values().containsAll(dBSequenceList1),is(equalTo(true)));
        MatcherAssert.assertThat("dBSequenceList doesn't contain all dBSequences from dBSequenceMap",dBSequenceList1.containsAll(dbSequenceListfrommap),is(equalTo(true)));
        MatcherAssert.assertThat("dBSequenceMap values aren't the same as dBSequenceList",dbSequenceListfrommap,is(equalTo(dBSequenceList1)));
    }

    /**
     * Check if PeptideCollection is correct (Maps vs Lists by ids)
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void setCorrectMZIDGetCorrectPeptideCollection() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Checking PeptideCollection");
        Map<String, PeptideEvidence> peptideEvidenceMap  = (Map<String, PeptideEvidence>) ORFDRG.getFieldObject("peptideEvidenceMap");
        List<Peptide> peptideList= (List<Peptide>) ORFDRG.getFieldObject("peptideList");
        Map<String, String> peptideIdAndSequence  = (Map<String, String>) ORFDRG.getFieldObject("peptideIdAndSequence");
        Map<String, Peptide> peptideMap= (Map<String, Peptide>) ORFDRG.getFieldObject("peptideMap");
        Comparator<Peptide> peptideComparator = new Comparator<Peptide>() {
            public int compare(Peptide pep1, Peptide pep2) {
                List<String> idstosort = new ArrayList<String>();
                if(pep1.getId().equals(pep2.getId())){
                    return 0;
                }
                idstosort.add(pep1.getId());
                idstosort.add(pep2.getId());
                Collections.sort(idstosort);
                if(idstosort.get(0).equals(pep1.getId())){
                    return -1;
                }else{
                    return 1;
                }
            }
        };
        for(String id :peptideMap.keySet()){
            assertTrue(peptideMap.get(id).getPeptideSequence().equals(peptideIdAndSequence.get(id)));
        }

        List<Peptide> peptideListfrommap = new ArrayList<Peptide>(peptideMap.values());
        Collections.sort(peptideList,peptideComparator);
        Collections.sort(peptideListfrommap,peptideComparator);
        MatcherAssert.assertThat("Peptides in peptideList aren't the same as in the Map",peptideList,is(equalTo(peptideListfrommap)));
        MatcherAssert.assertThat("PeptideIdAndSequence keyset is different from peptideMap keyset",peptideIdAndSequence.keySet(),is(equalTo(peptideMap.keySet())));
    }

    /**
     * Insufficient inputs to Main
     * @throws FalseDiscoveryRateGlobalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void insufficientInputsToMainThrowsExcpetion() throws FalseDiscoveryRateGlobalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("Throwing FalseDiscoveryRateGlobalArgumentException on insufficient inputs passed to main ");
        thrown.expect(FalseDiscoveryRateGlobalArgumentException.class);
        thrown.expectMessage("Please see documentation for global false discovery rate  inputs");
        String[] inputs = {decoyRatio,"",cvTerm,fdrLevel,proteinLevel};
        FalseDiscoveryRateGlobal.main(inputs);
    }

    /**
     * Serialize FalseDiscoveryRateGlobal objects for different options
     * @throws FalseDiscoveryRateGlobalArgumentException
     */
    //@Test
    @Ignore("I take too long to finish but remember me!!")
    public void setCorrectMZIDThenSerializeFalseDiscoveryRateGlobal1() throws FalseDiscoveryRateGlobalArgumentException {
        for(int i=1;i<(mzids.length-1);i++){
            for(int j=1;j<decoys.length;j++){
                    for(int k= 0;k<cvTerms.length;k++){
                        for(int l= 0;l<fdrLevels.length;l++){
                            for(int m= 0;m<proteinLevels.length;m++){
                                FalseDiscoveryRateGlobal FDRG = new FalseDiscoveryRateGlobal(mzids[i],decoyRatio(),decoys[j],cvTerms[k],true,fdrLevels[l],proteinLevels[m]);
                                FDRG.computeFDRusingJonesMethod();
                                //ObjectReflection ORFDRG = new ObjectReflection(FDRG);
                                ObjectIOStreamSedeserialize.serialize(FDRG,"src/test/data/testscenario/fdr/expectedoutputs","ConstructorAndComputeFDRG");
                                //ObjectIOStreamSedeserialize.serialize(FDRG,"src/test/data/testscenario/fdr/expectedoutputs","ConstructorAndComputeFDRG");
                            }
                        }
                    }
            }

        }
        assertTrue(true);
    }
    //@Test
    @Ignore("I take too long don't forget me")
    public void setCorrectMZIDThenSerializeFalseDiscoveryRateGlobal() throws FalseDiscoveryRateGlobalArgumentException {
        FalseDiscoveryRateGlobalFactory FDRGFactory = new FalseDiscoveryRateGlobalFactory();
        for(int i=0;i<fdrglevels.length;i++){
            IFalseDiscoveryRateGlobal IFDRG = FDRGFactory.getFDRG(fdrglevels[i]);
            FalseDiscoveryRateGlobal FDRG = IFDRG.genererate();
            FDRG.computeFDRusingJonesMethod();
            ObjectIOStreamSedeserialize.serialize(FDRG,"src/test/data/testscenario/fdr/expectedoutputs","ConstructorAndComputedFDRG");
        }
        //ObjectReflection ORFDRG = new ObjectReflection(FDRG);
        //ObjectIOStreamSedeserialize.serialize(FDRG,"src/test/data/testscenario/fdr/expectedoutputs","ConstructorAndComputeFDRG");
    }
    /**
     * Compare peptides lists
     * @param prevList
     * @param modelList
     * @return
     */
    public boolean compareListPeptide(List<Peptide> prevList, List<Peptide> modelList) {
        if (prevList.size() == modelList.size()) {
            for(int i =0; i<prevList.size();i++){
                if (!prevList.get(i).getId().equals(modelList.get(i).getId())) {
                    System.out.println("Peptides ids didn't match :"+prevList.get(i).getId()+"--"+modelList.get(i).getId());
                    return  false;
                }
            }
        }
        else{
            System.out.println("Peptides size didn't match : "+prevList.size()+" != "+modelList.size());
            return false;
        }
        return true;
    }

    /**
     * Compare DBsequences lists
     * @param prevList
     * @param modelList
     * @return
     */
    public boolean compareListDBSequence(List<DBSequence> prevList, List<DBSequence> modelList) {
        if (prevList.size() == modelList.size()) {
            for(int i =0; i<prevList.size();i++){
                if (!prevList.get(i).getId().equals(modelList.get(i).getId())) {
                    System.out.println("DBSequences ids didn't match"+prevList.get(i).getId()+"--"+modelList.get(i).getId());
                    return  false;
                }
            }
        }
        else{
            System.out.println("DBSequences size didn't match : "+prevList.size()+" != "+modelList.size());
            return false;
        }
        return true;
    }

    /**
     * Compare two Maps
     * @param prevList
     * @param modelList
     * @param type
     * @return
     */
    public boolean compareMaps(Object prevList, Object modelList,String type) {
        switch (type){
            case "DBSequence" : Map<String, DBSequence> prevdbs = (Map<String, DBSequence>) prevList;
                                Map<String, DBSequence> modedbs = (Map<String, DBSequence>) modelList;
                                if(prevdbs.size()==modedbs.size() && prevdbs.keySet().containsAll(modedbs.keySet()) && modedbs.keySet().containsAll(prevdbs.keySet())){
                                      for(String key :prevdbs.keySet()){
                                          if(!prevdbs.get(key).getId().equals(modedbs.get(key).getId())){
                                            System.out.println("DBSequence with key = "+key+" did have two different ids = "+prevdbs.get(key).getId()+","+modedbs.get(key).getId());
                                            return false;
                                          }
                                      }
                                }else {
                                    System.out.println("Map "+type+" sizes didn't match");
                                    return false;
                                }
                                break;
            case "PeptideEvidence" :
                    Map<String, PeptideEvidence> prevpepevs = (Map<String, PeptideEvidence>) prevList;
                    Map<String, PeptideEvidence> modelpepevs = (Map<String, PeptideEvidence>) modelList;
                    if(prevpepevs.size()==modelpepevs.size() && prevpepevs.keySet().containsAll(modelpepevs.keySet()) && modelpepevs.keySet().containsAll(prevpepevs.keySet())){
                        for(String key :prevpepevs.keySet()){
                        if(!prevpepevs.get(key).getId().equals(modelpepevs.get(key).getId())){
                            System.out.println("PeptideEvidence with key = "+key+" did have two different ids = "+prevpepevs.get(key).getId()+","+modelpepevs.get(key).getId());
                            return false;
                        }
                    }
                    }else {
                        System.out.println("Map "+type+" sizes didn't match");
                        return false;
                    }
                    break;
            case "Peptide": Map<String, Peptide> prevpeps = (Map<String, Peptide>) prevList;
                            Map<String, Peptide> modelpeps = (Map<String, Peptide>) modelList;
                if(prevpeps.size()==modelpeps.size() && prevpeps.keySet().containsAll(modelpeps.keySet()) && modelpeps.keySet().containsAll(prevpeps.keySet())){
                    for(String key :prevpeps.keySet()){
                        if(!prevpeps.get(key).getId().equals(modelpeps.get(key).getId())){
                            System.out.println("Peptide with key = "+key+" did have two different ids = "+prevpeps.get(key).getId()+","+modelpeps.get(key).getId());
                            return false;
                        }
                    }
                }else {
                    System.out.println("Map "+type+" sizes didn't match");
                    return false;
                }
                break;
            case "String": Map<String, String> prevpepss = (Map<String, String>) prevList;
                Map<String, String> modelpepss = (Map<String, String>) modelList;
                if(prevpepss.size()==modelpepss.size() && prevpepss.keySet().containsAll(modelpepss.keySet()) && modelpepss.keySet().containsAll(prevpepss.keySet())){
                    for(String key :prevpepss.keySet()){
                        if(!prevpepss.get(key).equals(modelpepss.get(key))){
                            System.out.println("String with key = "+key+" did have two different ids = "+prevpepss.get(key)+","+modelpepss.get(key));
                            return false;
                        }
                    }
                }else {
                    System.out.println("Map "+type+" sizes didn't match");
                    return false;
                }
                break;
        }
        return true;
    }
}