package service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import repository.IRepository;
import repository.impl.TxtRepository;
import repository.impl.XMLRepository;
import service.MessageService;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {
//	private IRepository repository;
		private TxtRepository txtRepository;
		private XMLRepository xmlRepository;
	
	/*
	public MessageServiceImpl(IRepository repository) {
		this.repository = repository;
	}
	*/
	
	public void save(String message, String flag) {
		if(flag.equalsIgnoreCase("txt")) {
			setRepository(txtRepository);
			txtRepository.save(message);
		}else if(flag.equalsIgnoreCase("xml")) {
			setRepository(xmlRepository);
			xmlRepository.save(message);
		}else {
			
		}
		
//		@Qualifier("txtRepository")
	}
	
	/*
	@Autowired
	@Qualifier(value ="xmlRepository")
	*/
	@Resource(name = "txtRepository")
	public void setRepository(TxtRepository repository) {
		this.txtRepository = repository;
	}
	
	
	@Resource(name = "xmlRepository")
	public void setRepository(XMLRepository repository) {
		this.xmlRepository = repository;
	}
	
	
}
