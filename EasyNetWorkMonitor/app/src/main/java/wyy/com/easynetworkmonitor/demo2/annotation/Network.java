package wyy.com.easynetworkmonitor.demo2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import wyy.com.easynetworkmonitor.demo2.NetType;

/**
 * author: wyj
 * Date:2019/5/30
 * Description
 */

@Target(ElementType.METHOD) //作用在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时.jvm运行时，通过反射拿到
public @interface Network {
   NetType netType() default NetType.AUTO;
}
