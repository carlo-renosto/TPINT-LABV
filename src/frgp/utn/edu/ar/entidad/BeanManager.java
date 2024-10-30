package frgp.utn.edu.ar.entidad;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanManager {
	private ApplicationContext appContext;

    public BeanManager() {
        this.appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
    }

    public <T> T getBean(String beanName, Class<T> requiredType) {
        return appContext.getBean(beanName, requiredType);
    }
    
    public void close() {
    	((ConfigurableApplicationContext)(appContext)).close();
    }
}
