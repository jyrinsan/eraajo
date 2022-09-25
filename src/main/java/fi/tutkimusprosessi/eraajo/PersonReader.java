package fi.tutkimusprosessi.eraajo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonReader implements ItemStreamReader<Person> {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonReader.class);

	@Autowired
	private PersonRepository repo;
	
	private List<Person> personList = new ArrayList();
	private int index = 0;
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		logger.debug("open");
		
		List<PersonEntity> personEntities = (List<PersonEntity>) repo.findAll();
		for (PersonEntity entity: personEntities) {
			Person person = Person.builder().firstName(entity.getFirstName())
					.lastName(entity.getLastName()).build();
			personList.add(person);
		}
		
	}
		
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.debug("read");
			
		while (index < personList.size()) {
			logger.debug("index {}", index);
			Person person = personList.get(index);
			logger.debug("person {}", person);
			index++;
			return person;
		}
			
		return null;
	}
	
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}

}
