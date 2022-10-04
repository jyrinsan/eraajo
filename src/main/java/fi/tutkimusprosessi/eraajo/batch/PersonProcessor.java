package fi.tutkimusprosessi.eraajo.batch;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import fi.tutkimusprosessi.eraajo.to.Person;

public class PersonProcessor implements ItemProcessor<Person, Person> {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonProcessor.class);
	
	@Override
	public Person process(Person person) throws Exception {
		logger.info("processing person {}", person);
	
		int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears();
		logger.debug("age on {}", age);
		
		String group;
		if (age < 0) {
			logger.error("prosessorissa tapahtui virhe");
			person.setBirthDate(LocalDate.now());
			throw new PersonDataIncompleteException("Henkilön syntymäaika oli tulevaisuudessa");	
		}
		else if (age < 18)
			group = "children";
		else if (age < 120)
			group = "adults";
		else
			group = "other";
		
		Person person2 = Person.builder()
				.group(group)
				.count(1)
				.build();
		
		logger.info("palautetaan {}", person2);
		return person2;
	}

}
