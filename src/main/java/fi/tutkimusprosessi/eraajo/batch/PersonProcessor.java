package fi.tutkimusprosessi.eraajo.batch;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import fi.tutkimusprosessi.eraajo.to.Person;

public class PersonProcessor implements ItemProcessor<Person, Person> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonProcessor.class);
	
	@Override
	public Person process(Person person) throws Exception {
		LOGGER.debug("PersonProcessor - process, {}", person);
	
		int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears();
		LOGGER.debug("laskettiin ikä {}", age);
		
		String group;
		if (age < 0) {
			LOGGER.error("prosessorissa tapahtui virhe");
			person.setBirthDate(LocalDate.now());
			throw new Exception("Henkilön syntymäaika oli tulevaisuudessa");	
		}
		else if (age < 18)
			group = "children";
		else if (age < 120)
			group = "adults";
		else
			group = "old";
		
		Person person2 = Person.builder()
				.firstName(person.getFirstName())
				.lastName(person.getLastName())
				.birthDate(person.getBirthDate())
				.group(group)
				.count(1)
				.build();
		
		LOGGER.debug("Processor palauttaa {}", person2);
		return person2;
	}

}
