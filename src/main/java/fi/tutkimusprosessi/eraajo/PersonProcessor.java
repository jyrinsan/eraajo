package fi.tutkimusprosessi.eraajo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonProcessor.class);

	@Override
	public Person process(Person item) throws Exception {
		logger.debug("processor, item {}", item);
		Person person2 = Person.builder()
				.firstName(item.getFirstName().toUpperCase())
				.lastName(item.getLastName().toUpperCase())
				.build();
		
		return person2;
	}



}
