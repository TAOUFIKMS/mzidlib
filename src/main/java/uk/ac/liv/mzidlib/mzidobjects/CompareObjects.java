package uk.ac.liv.mzidlib.mzidobjects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import uk.ac.ebi.jmzidml.model.mzidml.*;

/**
 * @author Bensellak Taoufik
 * Class containing methods for comparing mzid objects
 */
public class CompareObjects {
    /**
     * ObjectReflection
     */
    public ObjectReflection orone;
    /**
     * ObjectReflection
     */
    public ObjectReflection ortwo;

    /**
     * Create ObjectReflection on Object
     * @param oone
     * @param otwo
     */
    public CompareObjects(Object oone, Object otwo) {
        System.out.println("Constructor of CompareObjects Creating ObjectReflection on the two objects");
        this.orone = new ObjectReflection(oone);
        this.ortwo = new ObjectReflection(otwo);
    }

    /**
     * Compare objects fields
     * @return Boolean
     */
    public boolean compareFields(){
        System.out.println("Comparing Fields ");
        if(orone.fields.keySet().size()==ortwo.fields.keySet().size()){
            if(orone.fields.keySet().containsAll(ortwo.fields.keySet()) && ortwo.fields.keySet().containsAll(ortwo.fields.keySet())){
                for(String key :orone.fields.keySet()){
                    //System.out.println(key+orone.fields.get(key).toGenericString());
                    if(!orone.fields.get(key).equals(ortwo.fields.get(key))){
                        System.out.println("Field "+key+" didn't match");
                        return false;
                    }
                }
            }else{
                System.out.println("Fields aren't the same");
                return false;
            }

        }else {
            System.out.println("Fields sizes didn't match");
            return false;
        }
        return true;
    }

    /**
     * Compare primitive fields values
     * @return Boolean
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public boolean comparePrimitiveFields() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Comparing Primitive ");
        if(orone.fields.keySet().size()==ortwo.fields.keySet().size()){
            if(orone.fields.keySet().containsAll(ortwo.fields.keySet()) && ortwo.fields.keySet().containsAll(ortwo.fields.keySet())){
                for(String key :orone.fields.keySet()){
                    if(isPrimitiveOrPrimitiveWrapperOrString(orone.fields.get(key).getType())){
                        //System.out.println(key+""+orone.getFieldObject(key));
                        if(orone.getFieldObject(key)!=null && ortwo.getFieldObject(key)!=null) {
                            if (!orone.getFieldObject(key).equals(ortwo.getFieldObject(key))) {
                                System.out.println("Primitive field "+key+"  value didn't match");
                                return false;
                            }
                        }
                    }
                    if(!orone.fields.get(key).equals(ortwo.fields.get(key))){
                        return false;
                    }
                }
            }else{
                System.out.println("Field aren't the same");
                return false;
            }

        }else {
            System.out.println("Fields sizes didn't match");
            return false;
        }
        return true;
    }

    /**
     * Compare fields of type Array
     * @return Boolean
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws JAXBException
     * @throws IOException
     */
    public   boolean  compareArraysFields() throws NoSuchFieldException, IllegalAccessException, JAXBException, IOException {
        System.out.println("Comparing Arrays ");
        if(orone.fields.keySet().size()==ortwo.fields.keySet().size()){
            if(orone.fields.keySet().containsAll(ortwo.fields.keySet()) && ortwo.fields.keySet().containsAll(ortwo.fields.keySet())){
                for(String key :orone.fields.keySet()){
                    //System.out.println(key+"@@"+orone.getField(key).getGenericType().getTypeName());
                    if(orone.getFieldObject(key)!=null){
                        //System.out.println(key+"@@"+orone.fields.get(key).getType().getSimpleName());
                    if(orone.fields.get(key).getType().getSimpleName().equals("List")){
                        String listtype =orone.getField(key).getGenericType().getTypeName().replaceAll(">","").split("<")[1];
                        //System.out.println();
                        //System.out.println(compareArrayObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key,listtype));
                        if(!compareArrayObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key,listtype)){
                            System.out.println("List Field "+key+" didn't match");
                            return false;
                        }
                    }else{
                        if(orone.fields.get(key).getType().getSimpleName().equals("Integer[]")){
                            String listtype =orone.getField(key).getGenericType().getTypeName().split(".")[2];
                            //System.out.println();
                            //System.out.println(compareArrayObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key,listtype));
                            if(!compareArrayObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key,listtype)){
                                System.out.println("Integer[] Field "+key+" didn't match");
                                return false;
                            }
                        }

                    }
                    }
                }
            }else{
                System.out.println("Fields aren't the same");
                return false;
            }

        }else {
            System.out.println("Fields sizes didn't match");
            return false;
        }
        return true;
    }

    /**
     * Compare fields of type Map
     * @return Boolean
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws JAXBException
     * @throws IOException
     */
    public   boolean  compareMapsFields() throws NoSuchFieldException, IllegalAccessException, JAXBException, IOException {
        System.out.println("Comparing Maps ");
        if(orone.fields.keySet().size()==ortwo.fields.keySet().size()){
            if(orone.fields.keySet().containsAll(ortwo.fields.keySet()) && ortwo.fields.keySet().containsAll(ortwo.fields.keySet())){
                for(String key :orone.fields.keySet()){
                    if(orone.fields.get(key).getType().getSimpleName().contains("Map")){
                        if(orone.getFieldObject(key)!=null){
                            if(!compareMapObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key)){
                                System.out.println("Map field "+key+" didn't match");
                            }
                            //System.out.println(key+"@@"+orone.fields.get(key).getType().getSimpleName());
                        }
                        //String listtype =orone.getField(key).getGenericType().getTypeName().replaceAll(">","").split("<")[1];
                        //System.out.println(key+"@"+orone.getField(key).getGenericType().getTypeName());
                        //System.out.println(compareArrayObjects(orone.getFieldObject(key),ortwo.getFieldObject(key),key,listtype));
                    }

                }
            }else{
                System.out.println("Fields aren't the same");
                return false;
            }

        }else {
            System.out.println("Fields sizes didn't match");
            return false;
        }
        return true;
    }

    /**
     * Compare nonprimitive fields
     * @return Booelan
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws JAXBException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public boolean compareNonPrimitiveFields() throws NoSuchFieldException, IllegalAccessException, JAXBException, ClassNotFoundException, IOException {
        System.out.println("Comparing NonPrimitive ");
        if(orone.fields.keySet().size()==ortwo.fields.keySet().size()){
            if(orone.fields.keySet().containsAll(ortwo.fields.keySet()) && ortwo.fields.keySet().containsAll(ortwo.fields.keySet())){
                for(String key :orone.fields.keySet()){
                    if(!isPrimitiveOrPrimitiveWrapperOrString(orone.fields.get(key).getType())){

                        if(orone.getFieldObject(key)!=null && ortwo.getFieldObject(key)!=null) {
                            if(orone.getFieldObject(key).getClass().toString().contains("uk.ac.ebi.jmzidml.model.mzidml")){
                                //System.out.println(key+"--"+orone.getFieldObject(key).getClass().getSimpleName());
                                if(!compareXMLObjects(orone.getFieldObject(key),ortwo.getFieldObject(key))){
                                    System.out.println("NonPrimitive Field "+key+" didn't match");
                                    return false;
                                }

                            }
                        }
                    }
                }
            }else{
                System.out.println("Fields aren't the same");
                return false;
            }

        }else {
            System.out.println("Fields sizes didn't match");
            return false;
        }
        return true;
    }

    /**
     * Test if type is primitive
     * @param type
     * @return Boolean
     */
    public static boolean isPrimitiveOrPrimitiveWrapperOrString(Class<?> type) {
        return (type.isPrimitive() && type != void.class) ||
                type == Double.class || type == Float.class || type == Long.class ||
                type == Integer.class || type == Short.class || type == Character.class ||
                type == Byte.class || type == Boolean.class || type == String.class;
    }

    /**
     * Compare XML objects using JAXB
     * @param oone
     * @param otwo
     * @return Boolean
     * @throws JAXBException
     * @throws IOException
     */
    public  boolean compareXMLObjects(Object oone,Object otwo) throws JAXBException, IOException {
        String type = oone.getClass().getSimpleName();
        //System.out.println("Comparing XML : "+type);
        //System.out.println(type);
        StringWriter stringWriterone;
        StringWriter stringWritertwo;
        String oonexml = "";
        String otwoxml = "";
        JAXBContext context = JAXBContext.newInstance(oone.getClass());
        Marshaller m = context.createMarshaller();
        QName qName;
        switch (type){
            case "Inputs" :
                stringWriterone = new StringWriter();
                qName = new QName("", "Inputs");
                m.marshal(new JAXBElement<Inputs>(qName, Inputs.class, (Inputs)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<Inputs>(qName, Inputs.class, (Inputs)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("Inputs didn't match");
                    return false;
                }
                break;
            case "Provider" :
                stringWriterone = new StringWriter();
                qName = new QName("", "Provider");
                m.marshal(new JAXBElement<Provider>(qName, Provider.class, (Provider)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<Provider>(qName, Provider.class, (Provider)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("Provider didn't match");
                    return false;
                }
                break;

            case "AnalysisSoftwareList" :
                stringWriterone = new StringWriter();
                qName = new QName("", "AnalysisSoftwareList");
                m.marshal(new JAXBElement<AnalysisSoftwareList>(qName, AnalysisSoftwareList.class, (AnalysisSoftwareList)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<AnalysisSoftwareList>(qName, AnalysisSoftwareList.class, (AnalysisSoftwareList)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("AnalysisSoftwareList didn't match");
                    return false;
                }
                break;
            case "CvList" :
                stringWriterone = new StringWriter();
                qName = new QName("", "CvList");
                m.marshal(new JAXBElement<CvList>(qName, CvList.class, (CvList)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<CvList>(qName, CvList.class, (CvList)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("CvList didn't match");
                    return false;
                }
                break;
            case "AnalysisProtocolCollection" :
                stringWriterone = new StringWriter();
                qName = new QName("", "AnalysisProtocolCollection");
                m.marshal(new JAXBElement<AnalysisProtocolCollection>(qName, AnalysisProtocolCollection.class, (AnalysisProtocolCollection)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<AnalysisProtocolCollection>(qName, AnalysisProtocolCollection.class, (AnalysisProtocolCollection)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("AnalysisProtocolCollection didn't match");
                    return false;
                }
                break;
            case "AuditCollection" :
                stringWriterone = new StringWriter();
                qName = new QName("", "AuditCollection");
                m.marshal(new JAXBElement<AuditCollection>(qName, AuditCollection.class, (AuditCollection)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<AuditCollection>(qName, AuditCollection.class, (AuditCollection)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("AuditCollection didn't match");
                    return false;
                }
                break;
            case "AnalysisCollection" :
                stringWriterone = new StringWriter();
                qName = new QName("", "AnalysisCollection");
                m.marshal(new JAXBElement<AnalysisCollection>(qName, AnalysisCollection.class, (AnalysisCollection)oone), stringWriterone);
                oonexml=stringWriterone.toString();
                stringWriterone.close();
                stringWritertwo = new StringWriter();
                m.marshal(new JAXBElement<AnalysisCollection>(qName, AnalysisCollection.class, (AnalysisCollection)otwo), stringWritertwo);
                otwoxml=stringWritertwo.toString();
                stringWritertwo.close();
                //System.out.println(oonexml);
                //System.out.println(otwoxml);
                if(!oonexml.equals(otwoxml)){
                    System.out.println("AnalysisCollection didn't match");
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * Compare Array objects by type
     * @param oone
     * @param otwo
     * @param key
     * @param listtype
     * @return Boolean
     * @throws JAXBException
     * @throws IOException
     */
    public   boolean compareArrayObjects(Object oone,Object otwo,String key,String listtype) throws JAXBException, IOException {
        System.out.println("Comparing Array : "+key);
        if(key.contains("sorted")){
            switch (key){
                case "sorted_evalues" :
                    List<Double> listone = (List<Double>) oone;
                    List<Double> listtwo = (List<Double>) otwo;
                    if(listone.size()!=listtwo.size()){
                        return false;
                    }
                    List<Double> listonesorted = (List<Double>) oone;
                    List<Double> listtwosorted = (List<Double>) otwo;
                    Collections.sort(listonesorted);
                    Collections.sort(listtwosorted);
                    if(!listonesorted.equals(listone) || !listtwosorted.equals(listtwo)){
                        System.out.println("sorted_evalues not sorted");
                        return false;
                    }else{
                        if(!listone.equals(listtwo)){
                            System.out.println("sorted_evalues didn't match");
                            return false;
                        }
                    }
                    break;
                default:
                    System.out.println(key+"here");
                    List<String> liststingsone = (List<String>) oone;
                    List<String> liststringstwo = (List<String>) otwo;
                    System.out.println(liststingsone.size()+"---"+liststringstwo.size());
                    if(liststingsone.size()!=liststringstwo.size()){
                        System.out.println(key+" sizes didn't match");
                        return false;
                    }
                    List<String> liststringsonesorted = (List<String>) oone;
                    List<String> liststringstwosorted = (List<String>) otwo;
                    Collections.sort(liststringsonesorted);
                    Collections.sort(liststringstwosorted);
                    if(!liststringsonesorted.equals(liststingsone) || !liststringstwosorted.equals(liststringstwo)){
                        System.out.println(key+" not sorted");
                        return false;
                    }else{
                        if(!liststingsone.equals(liststringstwo)){
                            System.out.println(key+" didn't match");
                            return false;
                        }
                    }

            }
        }else{
            if(listtype.contains("String")){
                List<String> liststingsone = (List<String>) oone;
                List<String> liststringstwo = (List<String>) otwo;
                if(liststingsone.size()!=liststringstwo.size()){
                    System.out.println(key+" size didn't match");
                    return false;
                }
                if(!liststingsone.equals(liststringstwo)){
                    System.out.println(key+" didn't match");
                    return false;
                }

            }else{
                if(listtype.contains("Double")){
                    List<Double> listdoublesone = (List<Double>) oone;
                    List<Double> listdoublestwo = (List<Double>) otwo;
                    if(listdoublesone.size()!=listdoublestwo.size()){
                        System.out.println(key+" size didn't match");
                        return false;
                    }
                    if(!listdoublesone.equals(listdoublestwo)){
                        System.out.println(key+" didn't match");
                        return false;
                    }

                }else{
                    if(listtype.contains("DBSequence")){
                        List<DBSequence> listdbssone = (List<DBSequence>) oone;
                        List<DBSequence> listdbsstwo = (List<DBSequence>) otwo;
                        if(listdbssone.size()!=listdbsstwo.size()){
                            System.out.println(key+" size didn't match");
                            return false;
                        }
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
                        Collections.sort(listdbssone,dbSequenceComparator);
                        Collections.sort(listdbsstwo,dbSequenceComparator);
                        for(int i=0;i<listdbssone.size();i++){
                            if(!compareXMLObjects(listdbssone.get(i),listdbsstwo.get(i))){
                                System.out.println(key+" index "+i+"didn't match");
                                return false;
                            }

                        }

                    }else{
                        if(listtype.contains("Peptide")) {
                            List<Peptide> listpepsone = (List<Peptide>) oone;
                            List<Peptide> listpepstwo = (List<Peptide>) otwo;
                            if (listpepsone.size() != listpepstwo.size()) {
                                System.out.println(key+" size didn't match");
                                return false;
                            }
                            Comparator<Peptide> peptideComparator = new Comparator<Peptide>() {
                                public int compare(Peptide pep1, Peptide pep2) {
                                    List<String> idstosort = new ArrayList<String>();
                                    if (pep1.getId().equals(pep2.getId())) {
                                        return 0;
                                    }
                                    idstosort.add(pep1.getId());
                                    idstosort.add(pep2.getId());
                                    Collections.sort(idstosort);
                                    if (idstosort.get(0).equals(pep1.getId())) {
                                        return -1;
                                    } else {
                                        return 1;
                                    }
                                }
                            };
                            Collections.sort(listpepsone, peptideComparator);
                            Collections.sort(listpepstwo, peptideComparator);
                            for (int i = 0; i < listpepsone.size(); i++) {
                                if (!compareXMLObjects(listpepsone.get(i), listpepstwo.get(i))) {
                                    System.out.println(key+" index "+i+"didn't match");
                                    return false;
                                }
                            }
                        }else{
                            if(listtype.contains("Integer[]")) {
                                Integer[] listintsone = (Integer[]) oone;
                                Integer[] listintsstwo = (Integer[]) otwo;
                                if (listintsone.length != listintsstwo.length) {
                                    System.out.println(key+" length didn't match");
                                    return false;
                                }
                                if(!Arrays.equals(listintsone,listintsstwo)){
                                    System.out.println(key+" didn't match");
                                    return  false;
                                }
                            }
                        }
                    }

                }

            }


        }
        return true;
    }
    public   boolean compareMapObjects(Object oone,Object otwo,String key) throws JAXBException, IOException {
        System.out.println("Comparing Map : "+key);
        if(key.equals("bestScorePSM") || key.equals("peptideIdAndSequence") ){
            Map<String, String> map1 = (Map<String, String>) oone;
            Map<String, String> map2 = (Map<String, String>) otwo;
            if(map1.keySet().size()!=map2.keySet().size()){
                System.out.println(key+" keyset size didn't match");
                return false;
            }
            if(!map1.keySet().equals(map2.keySet())){
                System.out.println(key+" keysets aren't the same");
                return false;
            }
            for(String k : map1.keySet()){
                if(!map1.get(k).equals(map2.get(k))){
                    System.out.println(key+"key = value "+map1.get(k)+" didn't match");
                    return false;
                }
            }

        }else{
            if(key.equals("peptidePSMCount")){
                Map<String, Integer> map1 = (Map<String, Integer>) oone;
                Map<String, Integer> map2 = (Map<String, Integer>) otwo;
                if(map1.keySet().size()!=map2.keySet().size()){
                    System.out.println(key+" keyset size didn't match");
                    return false;
                }
                if(!map1.keySet().equals(map2.keySet())){
                    System.out.println(key+" keysets aren't the same");
                    return false;
                }
                for(String k : map1.keySet()){
                    if(!map1.get(k).equals(map2.get(k))){
                        System.out.println(key+"key = value "+map1.get(k)+" didn't match");
                        return false;
                    }
                }

            }else{
                if(key.equals("peptidePSMMap")) {
                    Map<String, List<String>> map1 = (Map<String, List<String>>) oone;
                    Map<String, List<String>> map2 = (Map<String, List<String>>) otwo;
                    if (map1.keySet().size() != map2.keySet().size()) {
                        System.out.println(key+" keyset size didn't match");
                        return false;
                    }
                    if (!map1.keySet().equals(map2.keySet())) {
                        System.out.println(key+" keysets aren't the same");
                        return false;
                    }
                    for (String k : map1.keySet()) {
                        Collections.sort(map1.get(k));
                        Collections.sort(map2.get(k));
                        if (!map1.get(k).equals(map2.get(k))) {
                            System.out.println(key+"key = value "+map1.get(k)+" didn't match");
                            return false;
                        }
                    }
                }else{
                    switch (key){
                        case "dBSequenceMap":
                            Map<String, DBSequence> mapdbs1 = (Map<String, DBSequence>) oone;
                            Map<String, DBSequence> mapdbs2 = (Map<String, DBSequence>) otwo;
                            if(mapdbs1.keySet().size()!=mapdbs2.keySet().size()){
                                System.out.println(key+" keyset size didn't match");
                                return false;
                            }
                            if(!mapdbs1.keySet().equals(mapdbs2.keySet())){
                                System.out.println(key+" keysets aren't the same");
                                return false;
                            }
                            for(String k : mapdbs1.keySet()){
                                if(!compareXMLObjects(mapdbs1.get(k),mapdbs2.get(k))){
                                    System.out.println("value for key "+ key+" at "+k+" value didn't match");
                                    return false;
                                }
                            }
                            break;
                        case  "peptideEvidenceMap":
                            Map<String, PeptideEvidence> mappepev1 = (Map<String, PeptideEvidence>) oone;
                            Map<String, PeptideEvidence> mappepev2 = (Map<String, PeptideEvidence>) otwo;
                            if(mappepev1.keySet().size()!=mappepev2.keySet().size()){
                                System.out.println(key+" keyset size didn't match");
                                return false;
                            }
                            if(!mappepev1.keySet().equals(mappepev2.keySet())){
                                System.out.println(key+" keysets aren't the same");
                                return false;
                            }
                            for(String k : mappepev1.keySet()){
                                if(!compareXMLObjects(mappepev1.get(k),mappepev2.get(k))){
                                    System.out.println("value for key "+ key+" at "+k+" value didn't match");
                                    return false;
                                }
                            }
                            break;
                        case  "peptideMap":
                            Map<String, Peptide> mappep1 = (Map<String, Peptide>) oone;
                            Map<String, Peptide> mappep2 = (Map<String, Peptide>) otwo;
                            if(mappep1.keySet().size()!=mappep2.keySet().size()){
                                System.out.println(key+" keyset size didn't match");
                                return false;
                            }
                            if(!mappep1.keySet().equals(mappep2.keySet())){
                                System.out.println(key+" keysets aren't the same");
                                return false;
                            }
                            for(String k : mappep1.keySet()){
                                if(!compareXMLObjects(mappep1.get(k),mappep2.get(k))){
                                    System.out.println("value for key "+ key+" at "+k+" value didn't match");
                                    return false;
                                }
                            }
                            break;
                    }
                }

            }
        }
        return true;
    }

}
