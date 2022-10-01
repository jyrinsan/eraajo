package fi.tutkimusprosessi.eraajo.batch;

import java.util.ArrayList;
import java.util.List;

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
	
	private static final Logger logger = LoggerFactory.getLogger(PersonWriter.class);

	@Autowired
	private PeopleRepository peopleRepo;
	
	private List<PeopleEntity> entities = new ArrayList<PeopleEntity> ();
	
	private PeopleEntity childrenEntity = PeopleEntity.builder()
		.group("children")
		.count(0)
		.build();
	private PeopleEntity adultsEntity = PeopleEntity.builder()
		.group("adults")
		.count(0)
		.build();
	
	@Override
	public void write(List<? extends Person> persons) throws Exception {
		logger.debug("write, items {}", persons);
		
		for (Person person: persons) {
			if (person.getGroup().equals("children")) {
				int count = childrenEntity.getCount();
				count ++;
				childrenEntity.setCount(count);
			} else if (person.getGroup().equals("adults")) {
				int count = adultsEntity.getCount();
				count ++;
				adultsEntity.setCount(count);
			} else {
				throw new UnknownGroupException("Tuntematon ryhmä");
			}
		}

		logger.debug("tallennetaan entityt {}", entities);
		peopleRepo.saveAll(entities);
	}
	
	private void printPeopleData(Iterable<PeopleEntity> list) {
		
		System.out.println("--- People-taulu ---");
		for (PeopleEntity entity: list) {
			System.out.println(entity.getGroup() + " : " + entity.getCount());
		}
		
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {	
		entities.add(adultsEntity);
		entities.add(childrenEntity);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		printPeopleData(peopleRepo.findAll());
		
	}

	@Override
	public void close() throws ItemStreamException {
	}

}
