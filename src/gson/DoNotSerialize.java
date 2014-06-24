package gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Lucy Linder
 * @date: 23.06.2014
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.FIELD } )
public @interface DoNotSerialize{
    // Field tag only annotation
}//end interface