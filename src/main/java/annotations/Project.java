package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//указываем, что аннотация применяется только для КЛАССОВ!
public @interface Project {
    String name();
}
