package client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Notify {
    String title() default "通知";
  //  String contentTemplate() default ""; // 使用模板而不是直接内容
    String type() default "举报";
}
