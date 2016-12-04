package uk.ac.liv.mzidlib.mzidobjects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Taoufik Bensellak on 23/11/16.
 * Class for Jsonizing FDR fields (String,List<String>,Double,List<Double>,Integer[])
 */
public class ObjectReflectionJSON implements Serializable {
    /**
     * ObjectReflection object used for generating JSON
     */
    public ObjectReflection objectreflection;

    /**
     * Constructor taking as input an ObjectReflection instance
     * @param objectreflection
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public ObjectReflectionJSON(ObjectReflection objectreflection) throws NoSuchFieldException, IllegalAccessException {
        this.objectreflection = objectreflection;
    }

    /**
     * Write object to JSON
     * @param directoryPath
     * @param mzidfiles
     */
    public void writeFDRJSON(String directoryPath,String mzidfiles){
        assert  !directoryPath.equals("");
        if (directoryPath == null)
        {
            throw new IllegalArgumentException(
                    "Name of file to which to serialize object to cannot be null.");
        }
        JSONObject jsonObject = new JSONObject();
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS Z");
        String datestring = DATE_FORMAT.format(date);
        if(!directoryPath.endsWith("/")){
            directoryPath += "/";
        }
        String filename = directoryPath+"FalseDiscoveryRateGlobal-AT-"+datestring+".json";
        try {
            jsonObject.put("createdon",datestring);
            jsonObject.put("mzidfiles",mzidfiles);
            jsonObject.put("fdrLevel", this.objectreflection.getFieldObject("fdrLevel"));
            jsonObject.put("proteinLevel", this.objectreflection.getFieldObject("proteinLevel"));
            jsonObject.put("decoyRatio", this.objectreflection.getFieldObject("decoyRatio"));
            jsonObject.put("decoy", this.objectreflection.getFieldObject("decoy"));
            jsonObject.put("usingFileDecoyAttribute", this.objectreflection.getFieldObject("usingFileDecoyAttribute"));
            jsonObject.put("allowedEvalues", this.objectreflection.getFieldObject("allowedEvalues"));
            jsonObject.put("cvTerm", this.objectreflection.getFieldObject("cvTerm"));
            jsonObject.put("betterScoresAreLower", this.objectreflection.getFieldObject("betterScoresAreLower"));
            jsonObject.put("searchDatabase_Ref", this.objectreflection.getFieldObject("searchDatabase_Ref"));
            jsonObject.put("spectraData_ref", this.objectreflection.getFieldObject("spectraData_ref"));
            jsonObject.put("spectrumResult",listStringsToJSONArray("spectrumResult"));
            jsonObject.put("spectrumItem",listStringsToJSONArray("spectrumItem"));
            jsonObject.put("peptideNames",listStringsToJSONArray("peptideNames"));
            jsonObject.put("evalues",listDoublesToJSONArray("evalues"));
            jsonObject.put("decoyOrNot",listStringsToJSONArray("decoyOrNot"));
            jsonObject.put("sorted_spectrumResult",listStringsToJSONArray("sorted_spectrumResult"));
            jsonObject.put("sorted_spectrumItem",listStringsToJSONArray("sorted_spectrumItem"));
            jsonObject.put("sorted_peptideNames",listStringsToJSONArray("sorted_peptideNames"));
            jsonObject.put("sorted_evalues",listDoublesToJSONArray("sorted_evalues"));
            jsonObject.put("sorted_decoyOrNot",listStringsToJSONArray("sorted_decoyOrNot"));
            jsonObject.put("estimated_simpleFDR",listDoublesToJSONArray("estimated_simpleFDR"));
            jsonObject.put("estimated_qvalue",listDoublesToJSONArray("estimated_qvalue"));
            jsonObject.put("estimated_fdrscore",listDoublesToJSONArray("estimated_fdrscore"));
            jsonObject.put("tp",listDoublesToJSONArray("tp"));
            jsonObject.put("fp",listDoublesToJSONArray("fp"));
            jsonObject.put("sortOrderForEvalues",listIntegersToJSONArray("sortOrderForEvalues"));
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List of strings to JSONArray
     * @param listName
     * @return JSONArray
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public JSONArray listStringsToJSONArray(String listName) throws NoSuchFieldException, IllegalAccessException {
        assert this.objectreflection.fields.containsKey(listName);
        List<String> liststrings = new ArrayList<>();
        liststrings = (List<String>) this.objectreflection.getFieldObject(listName);
        JSONArray jsonArrayliststrings = new JSONArray();
        //System.out.println(this.objectreflection.getFieldObject("sorted_peptideNames"));
        for(String liststring : liststrings){
            jsonArrayliststrings.add(liststring);
        }
        return  jsonArrayliststrings;
    }

    /**
     * List of doubles to JSONArray
     * @param listName
     * @return JSONArray
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public JSONArray listDoublesToJSONArray(String listName) throws NoSuchFieldException, IllegalAccessException {
        assert this.objectreflection.fields.containsKey(listName);
        List<Double> listdoubles = new ArrayList<>();
        listdoubles = (List<Double>) this.objectreflection.getFieldObject(listName);
        JSONArray jsonArraylistdoubles = new JSONArray();
        //System.out.println(this.objectreflection.getFieldObject("sorted_peptideNames"));
        for(Double listdouble : listdoubles){
            jsonArraylistdoubles.add(listdouble);
        }
        return jsonArraylistdoubles;
    }

    /**
     * List of integers to JSONArray
     * @param listName
     * @return JSONArray
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public JSONArray listIntegersToJSONArray(String listName) throws NoSuchFieldException, IllegalAccessException {
        assert this.objectreflection.fields.containsKey(listName);
        Integer[] listintegers ;
        listintegers = (Integer[]) this.objectreflection.getFieldObject(listName);
        JSONArray jsonArraylistdoubles = new JSONArray();
        //System.out.println(this.objectreflection.getFieldObject("sorted_peptideNames"));
        for(int i=0;i<listintegers.length;i++){
            jsonArraylistdoubles.add(listintegers[i]);
        }
        return jsonArraylistdoubles;
    }

    /**
     * Retrieving Object from JSON file
     * @param fileName
     * @param fieldName
     * @return Object
     */
    public  Object readFDRJSON(String fileName,String fieldName){
        assert this.objectreflection.fields.containsKey(fieldName);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject.get(fieldName);
            /*String datestring = (String) jsonObject.get("createdon");
            String fdrLevel= (String) jsonObject.get("fdrLevel");
            String proteinLevel = (String) jsonObject.get("proteinLevel");
            double decoyRatio = (double) jsonObject.get("decoyRatio");
            String decoy= (String) jsonObject.get("decoy");
            boolean usingFileDecoyAttribute = (boolean) jsonObject.get("usingFileDecoyAttribute");
            String allowedEvalues= (String) jsonObject.get("allowedEvalues");
            String cvTerm= (String) jsonObject.get("cvTerm");;
            boolean betterScoresAreLowers = (boolean) jsonObject.get("betterScoresAreLower");
            System.out.println(betterScoresAreLowers);
            System.out.println(datestring+fdrLevel+proteinLevel+decoyRatio+decoyRatio+decoy+usingFileDecoyAttribute+allowedEvalues+cvTerm+betterScoresAreLowers);
            */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
