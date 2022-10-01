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
		
		if (person.getBirthDate() == null)
			throw new PersonDataIncompleteException("Henkilön tiedot tietokannassa ovat puutteelliset");
	
		int age = Period.between(person.getBirthDate(), LocalDate.now()).getYears(); 
		
		String group;
		if (age < 18)
			group = "children";
		else
			group = "adults";
		
		Person person2 = Person.builder()
				.group(group)
				.count(1)
				.build();
		
		return person2;
	}

}
