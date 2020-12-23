package com.nolouser.demo.config;

import com.nolouser.demo.service.StaticDepTaskInfoService;
import com.nolouser.demo.service.StaticDepUserInfoService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Spring注入static静态成员的高级方式
 */
@Component
public class AutowireStaticSmartInitializingSingleton implements SmartInitializingSingleton {

    private final AutowireCapableBeanFactory beanFactory;

    private final AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor;

    public AutowireStaticSmartInitializingSingleton(AutowireCapableBeanFactory beanFactory, AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor) {
        this.beanFactory = beanFactory;
        this.annotationBeanPostProcessor = annotationBeanPostProcessor;
    }

    /**
     * 当所有的单例Bena初始化完成后，对static静态成员进行赋值
     */
    @Override
    public void afterSingletonsInstantiated() {
        // 因为是给static静态属性赋值，因此这里new一个实例做注入是可行的
        beanFactory.autowireBean(new StaticDepUserInfoService());
        // beanFactory.createBean(StaticDepTaskInfoService.class);

        annotationBeanPostProcessor.processInjection(new StaticDepTaskInfoService());
    }
}
