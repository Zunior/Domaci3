package main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import config.MyBeanConfig;
import repository.IRepository;
import service.MessageService;

@Component(value = "main")
public class Main {
	
	public static void main(String[] args) {
		//2. main ne kreiramo preko new operatora
		BeanFactory container = new AnnotationConfigApplicationContext(MyBeanConfig.class);
		Main main = container.getBean("main", Main.class);
//		main.annotationConfig(container);
		main.saveMessage("Gladan sam!!!", "xml");//promeniti string po potrebi xml ili txt
	}

	private void annotationConfig(BeanFactory container) {
		IRepository txtRepository = (IRepository)container.getBean("txtRepository");
		txtRepository.save("My plain message!");
		
		IRepository xmlRepository = container.getBean("xmlRepository", IRepository.class);
		xmlRepository.save("My xml message!");
		
		((AnnotationConfigApplicationContext)container).close();
	}
	
	private void saveMessage(String message, String flag) {
		//1. U zavisnosti od nekog uslova, cuvamo poruku u txt repository ili xml repository
		BeanFactory container = new AnnotationConfigApplicationContext(MyBeanConfig.class);
		MessageService messageService = 
				container.getBean("messageService", MessageService.class);
		messageService.save(message, flag);
	}
}
