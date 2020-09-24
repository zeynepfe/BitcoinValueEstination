package tr.edu.gazi.bm.bitirme2018.flink.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by flink on 2/23/18.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataMatch {
    int index() default -1;

    boolean dataClass() default false;
}