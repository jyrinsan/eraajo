package fi.tutkimusprosessi.eraajo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class PersonWriter implements ItemWriter<Person> {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonWriter.class);

	@Override
	public void write(List<? extends Person> items) throws Exception {
		logger.debug("write, items {}", items);
		
	}





}
