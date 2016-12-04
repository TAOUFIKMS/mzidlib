package uk.ac.liv.mzidlib.mzidobjects;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Taoufik Bensellak on 22/11/16.
 * Class for serializing and deserializing objects
 * Storing
 */
public class ObjectIOStreamSedeserialize {
    /**
     * Serialize object to path and adding prefix to the file
     * @param objectToSerialize
     * @param directoryPath
     * @param prefix
     * @param <T>
     */
    public static <T> void serialize(T objectToSerialize, String directoryPath,String prefix)
    {
        assert!directoryPath.equals("");
        if (directoryPath == null)
        {
            throw new IllegalArgumentException(
                    "Name of file to which to serialize object to cannot be null.");
        }
        if (objectToSerialize == null)
        {
            throw new IllegalArgumentException("Object to be serialized cannot be null.");
        }
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS Z");
        String datestring = DATE_FORMAT.format(date);
        if(!directoryPath.endsWith("/")){
            directoryPath += "/";
        }
        directoryPath += prefix+"-"+objectToSerialize.getClass().getSimpleName()+"-AT-"+datestring+".ser";
        try (FileOutputStream fos = new FileOutputStream(directoryPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(objectToSerialize);
            System.out.println("Serialization of Object " + objectToSerialize.getClass().getSimpleName() + " completed.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    /**
     * Deserialize from file by class type
     * @param fileToDeserialize
     * @param classBeingDeserialized
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String fileToDeserialize,Class<T> classBeingDeserialized)
    {
        assert !fileToDeserialize.equals("");
        if (fileToDeserialize == null)
        {
            throw new IllegalArgumentException("Cannot deserialize from a null filename.");
        }
        if (classBeingDeserialized == null)
        {
            throw new IllegalArgumentException("Type of class to be deserialized cannot be null.");
        }
        T objectOut = null;
        try (FileInputStream fis = new FileInputStream(fileToDeserialize);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            objectOut = (T) ois.readObject();
            System.out.println("Deserialization of Object " + objectOut.getClass().getSimpleName() + " is completed.");
        }
        catch (IOException | ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return objectOut;
    }
}
