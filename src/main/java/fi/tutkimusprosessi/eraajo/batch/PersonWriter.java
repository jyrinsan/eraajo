package fi.tutkimusprosessi.eraajo.batch;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;

import fi.tutkimusprosessi.eraajo.model.PeopleEntity;
import fi.tutkimusprosessi.eraajo.model.PeopleRepository;
import fi.tutkimusprosessi.eraajo.to.Person;

public class PersonWriter implements ItemStreamWriter<Person> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonWriter.class);

	@Autowired
	private PeopleRepository peopleRepo;
	
	private PeopleEntity entity = PeopleEntity.builder()
			.adultCount(0)
			.childCount(0)
			.build();
	
	private Random random = new Random(); 
	
	@Override
	public void write(List<? extends Person> persons) throws Exception {
		LOGGER.debug("PersonWriter - write, persons {}", persons);
		
		for (Person person: persons) {
			if (person.getGroup().equals("children")) {
				entity.setChildCount(entity.getChildCount() + 1);
			} else if (person.getGroup().equals("adults")) {
				entity.setAdultCount(entity.getAdultCount() + 1);
			} else {
				Exception ex = new Exception("Tuntematon ryhmä");
				LOGGER.error("Exception {}", ex.getMessage());
				throw ex;
			}
		}

		LOGGER.debug("tallennetaan entity {}", entity);
		peopleRepo.save(entity);
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
	}

	@Override
	public void close() throws ItemStreamException {
	}

}
