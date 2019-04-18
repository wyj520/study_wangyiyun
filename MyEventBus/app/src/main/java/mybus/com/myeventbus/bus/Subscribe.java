package mybus.com.myeventbus.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: wyj
 * Date:2019/4/18
 * Description
 */
@Target(ElementType.METHOD) //注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) //jvm在运行时阶段通过反射获取注解值
public @interface Subscribe {
    /**
     *  @Subscribe(threadMode=ThreadMode.MAIN);
     */
    ThreadMode threadMode() default ThreadMode.POSTING; //如果没有传线程，默认是POSTING;如  @Subscribe();
}
