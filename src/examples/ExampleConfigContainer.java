package examples;

import com.google.gson.annotations.SerializedName;
import gson.DoNotSerialize;
import gson.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple example.
 *
 * @author: Lucy Linder
 * @date: 19.06.2014
 */
public class ExampleConfigContainer{

    public String course;

    @SerializedName( "course_url" )
    public String courseUrl;

    @SerializedName( "inodes_to_names_mapping" )
    public Map<String, String> inodesToNamesMapping;

    public List<String> dir;

    @SerializedName( "ctypes" )
    public ArrayList<String> ctypes;

    @DoNotSerialize
    private String shouldNotBeSerialized = "lala";
    //----------------------------------------------------


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append( this.getClass() );
        for( Field field : this.getClass().getFields() ){
            try{
                builder.append( System.lineSeparator() )  //
                        .append( field.getName() ).append( ": " )   //
                        .append( field.get( this ) );
            }catch( Exception e ){
                System.out.println( field.getName() );
                e.printStackTrace();
            }
        }//end for
        return builder.toString();
    }


    //----------------------------------------------------


    public static void main( String[] args ) throws IOException{

        // deserialize object
        InputStream stream = ExampleConfigContainer.class.getResourceAsStream( "/resources/.cybe" );
        ExampleConfigContainer exampleContainer = ( ExampleConfigContainer ) GsonUtils.getJsonFromFile( stream,
                new ExampleConfigContainer() );

        System.out.println("===================================== dump");
        System.out.println(GsonUtils.dump( exampleContainer ).replaceAll( "\\\"|\\{|\\}|\\[|\\]", "" ));

        // modify a field
        exampleContainer.dir.add( "it works !" );
        System.out.println("===================================== toJson");
        System.out.println(GsonUtils.toJson( exampleContainer ));

        // serialize object
        File file = new File( "test.json" );
        GsonUtils.writeJsonFile( file, exampleContainer, true );

    }
}//end class
