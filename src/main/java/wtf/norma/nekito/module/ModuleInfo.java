package wtf.norma.nekito.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {

    String name();

    String description() default "";

    int key() default 0x00; //Keyboard.KEY_NONE

    ModuleCategory moduleCategory();

}
