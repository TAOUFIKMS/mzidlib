package uk.ac.liv.mzidlib.mzidobjects;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Class using reflection to access all field
 */
public class ObjectReflection implements Serializable {
    /**
     * Map of methods and methods names
     */
    public Map<String,Method> methods = new HashMap<>();
    /**
     * Map of Fields and Fields names
     */
    public Map<String,Field> fields = new HashMap<>();
    /**
     * Object to reflect
     */
    public Object object;

    /**
     * Create object reflection and setting all accessible
     * @param object
     */
    public ObjectReflection(Object object){
        assert !object.equals(null);
        this.object = object;
        List<Method> templistmethods = new ArrayList<Method> (Arrays.asList(object.getClass().getDeclaredMethods()));
        List<Field> templistfields = new ArrayList<Field> (Arrays.asList(object.getClass().getDeclaredFields()));
        for (Method method :templistmethods){
            methods.put(method.getName(),method);
        }
        for (Field field :templistfields){
            fields.put(field.getName(),field);
        }
        assert !fields.isEmpty();
        assert !methods.isEmpty();
        assert templistfields.size() == fields.size();
        assert templistmethods.size() == methods.size();
        assert fields.values().containsAll(Arrays.asList(object.getClass().getDeclaredFields()));
        assert methods.values().containsAll(Arrays.asList(object.getClass().getDeclaredMethods()));
        for (String keym :methods.keySet()){
            methods.get(keym).setAccessible(true);
            assert methods.get(keym).isAccessible();
        }
        for (String keyf :fields.keySet()){
            fields.get(keyf).setAccessible(true);
            assert fields.get(keyf).isAccessible();
        }
    }

    /**
     * Getter for Field by name
     * @param fieldname
     * @return Field
     * @throws NoSuchFieldException
     */
    public Field getField(String fieldname) throws NoSuchFieldException {
        assert fields.containsKey(fieldname);
        Field tempfield = fields.get(fieldname);
        assert fields.containsValue(tempfield);
        assert tempfield.getName() == fieldname;
        return tempfield;
    }

    /**
     * Getter of the object of a field by name
     * @param fieldname
     * @return Object
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public Object getFieldObject(String fieldname) throws NoSuchFieldException, IllegalAccessException {
        assert (fieldname != "") && (fieldname != null);
        if (!fields.containsKey(fieldname)) throw new AssertionError(fieldname+" Field not in");
        Object tempobject;
        Field tempfield = fields.get(fieldname);
        assert fields.containsValue(tempfield);
        assert tempfield.getName()==fieldname;
        tempobject = tempfield.get(object);
        return tempobject;
    }

    /**
     * Retuns the object applying reflection to
     * @return Object
     */
    public Object getObject() {
        return object;
    }

    /**
     * Get method by name and params
     * @param methodname
     * @param paramCount
     * @param params
     * @return Method
     */
    public  Method getMethod(String methodname,int paramCount, Object... params) {
        assert methodname != "" && methodname != null;
        assert methods.containsKey(methodname);
        assert paramCount > 0;
        assert params.length > 0;
        Method tempmethod = null;
        Object[] parameters = new Object[paramCount];
        Class<?>[] classArray = new Class<?>[paramCount];
        for (int i = 0; i < paramCount; i++) {
            parameters[i] = params[i];
            classArray[i] = params[i].getClass();
        }
        assert classArray.length>0;
        assert classArray.length == params.length;
        assert classArray.length == parameters.length;
        try {
            tempmethod = object.getClass().getDeclaredMethod(methodname, classArray);
            assert tempmethod.isVarArgs();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return tempmethod;
    }

    /**
     * Getter for method without params
     * @param methodname
     * @return Method
     * @throws NoSuchMethodException
     */
    public Method getMethod(String methodname) throws NoSuchMethodException {
        assert (methodname != "") && (methodname != null);
        assert methods.containsKey(methodname);
        Method tempmethod = methods.get(methodname);
        assert methods.containsValue(tempmethod);
        assert tempmethod.getName()==methodname;
        assert !tempmethod.isVarArgs();
        return tempmethod;
    }

    /**
     * Setting all fields accessible
     */
    public void setAllAccessible(){
        for (String keym :methods.keySet()){
            methods.get(keym).setAccessible(true);
            assert methods.get(keym).isAccessible();
        }
        for (String keyf :fields.keySet()){
            fields.get(keyf).setAccessible(true);
            assert fields.get(keyf).isAccessible();
        }
    }

    /**
     * Invoking method by name and params
     * @param methodname
     * @param paramCount
     * @param params
     * @return Object
     */
    public  Object invokeMethodVarArgs(String methodname,int paramCount, Object... params) {
        assert (methodname != "") && (methodname != null);
        assert methods.containsKey(methodname);
        Method method;
        Object requiredObj = null;
        Object[] parameters = new Object[paramCount];
        Class<?>[] classArray = new Class<?>[paramCount];
        for (int i = 0; i < paramCount; i++) {
            parameters[i] = params[i];
            classArray[i] = params[i].getClass();
        }
        try {
            method = object.getClass().getDeclaredMethod(methodname, classArray);
            method.setAccessible(true);
            assert method.isVarArgs();
            requiredObj = method.invoke(object, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return requiredObj;
    }

    /**
     * Invoke method without params
     * @param methodname
     * @return Object
     */
    public  Object invokeMethod(String methodname) {
        assert (methodname != "") && (methodname != null);
        assert methods.containsKey(methodname);
        Method method;
        Object requiredObj = null;
        try {
            method = object.getClass().getDeclaredMethod(methodname);
            method.setAccessible(true);
            assert !method.isVarArgs();
            requiredObj = method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return requiredObj;
    }

    /**
     * To string
     * @return String
     */
    @Override
    public String toString(){
        assert !object.equals(null);
        String tostring = object.getClass().getCanonicalName()+"--"+object.getClass().getSimpleName()+"--";
        tostring+= "\nFields names :";
        assert !fields.isEmpty();
        assert !methods.isEmpty();
        for (String keyf :fields.keySet()){
            tostring+="\n"+fields.get(keyf).getName();
        }
        tostring+= "\nMethods names :";
        for (String keym :methods.keySet()){
            tostring+="\n"+methods.get(keym).getName();
        }
        assert !tostring.equals("");
        return tostring;
    }

}
