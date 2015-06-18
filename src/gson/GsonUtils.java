package gson;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;


/**
 * Utilities to serialize/deserialize objects using Gson library.
 *
 * @author: Lucy Linder
 * @date: 23.05.2013
 */
public class GsonUtils{

    /** see {@link GsonUtils#getJsonFromFile(java.io.File, Object)} */
    public static Object getJsonFromFile( String path, Object container ){
        return getJsonFromFile( new File( path ), container );
    }// end getJsonFromFile


    /** see {@link GsonUtils#getJsonFromFile(java.io.File, Object)} */
    public static Object getJsonFromFile( File file, Object container ){

        FileInputStream fin = null;
        try{
            fin = new FileInputStream( file );
            return getJsonFromFile( fin, container );
        }catch( Exception e ){
            e.printStackTrace();
            System.out.println( "exception while getting json from file " + file.getPath() + " " +
                    "catched." );
        }
        return null;
    }// end getJsonFromFile


    /**
     * read a json stream and store its content into the fields of
     * the container object. <br />
     * The json entries must match the container fields. If a field of the container does not
     * appear in the json file (or its value is left empty), the field is set to null.
     * non-existent json entries ,
     *
     * @param stream the json stream to read from
     */
    public static Object getJsonFromFile( InputStream stream, Object container ){

        try{
            return new GsonBuilder().create() //
                    .fromJson( new InputStreamReader( stream ), container.getClass() );

        }catch( Exception e ){
            System.out.println( e.getMessage() );
            e.printStackTrace();
        }
        return null;

    }// end getJsonFromFile


    /**
     * Serialize the given object into json
     *
     * @param file      the destination file
     * @param container the object
     * @return true if the operation could be performed, false otherwise
     */
    public static boolean writeJsonFile( File file, Object container ){
        try{
            FileOutputStream fos;
            String str = new GsonBuilder().setPrettyPrinting()  //
                    .setExclusionStrategies( new GsonSimpleExclusionStrategy() ) //
                    .create()  //
                    .toJson( container, container.getClass() );

            fos = new FileOutputStream( file );
            fos.write( str.getBytes() );
            fos.close();
            return true;
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            e.printStackTrace();
        }

        return false;
    }//end writeJsonFile


    /** see {@link GsonUtils#writeJsonFile(String, Object)} */
    public static boolean writeJsonFile( String filepath, Object container ){
        return writeJsonFile( new File( filepath ), container );
    }//end writeJsonFile


    /**
     * Serialize the given object into a json string.
     *
     * @param o the object
     * @return the json string
     */
    public static String dump( Object o ){
        return new GsonBuilder().setPrettyPrinting()  //
                //.setExclusionStrategies( new GsonSimpleExclusionStrategy() ) //
                .create()  //
                .toJson( o, o.getClass() );
    }//end toJsonString


    /**
     * Serialize the object into a json string, excluding fields annotated with {@link DoNotSerialize}.
     *
     * @param o the object
     * @return the json string
     */
    public static String toJson( Object o ){
        return new GsonBuilder().setPrettyPrinting()  //
                .setExclusionStrategies( new GsonSimpleExclusionStrategy() ) //
                .create()  //
                .toJson( o, o.getClass() );
    }//end toJsonString


    /**
     * Deserialise a json string to an object.
     * Example:
     *
     * <pre>
     * {@code
     *
     * Map<String,String> map = new HashMap<>();
     * map.put(...);
     * String json = GsonUtils.toJson(map);
     * Map<String, String> deserialisedMap = (Map<String,String>)
     *      GsonUtils.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
     * }
     * </pre>
     *
     * @param json the json string
     * @param type the object's type
     * @return the object
     */
    public static Object fromJson( String json, Type type ){
        return new GsonBuilder().create().fromJson( json, type );
    }

}// end class
