package gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author: Lucy Linder
 * @date: 23.06.2014
 */
public class GsonSimpleExclusionStrategy implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField( FieldAttributes f ){
        return f.getAnnotation( DoNotSerialize.class ) != null;
    }

    @Override
    public boolean shouldSkipClass( Class<?> clazz ){
        return false;  //TODO
    }

}//end
