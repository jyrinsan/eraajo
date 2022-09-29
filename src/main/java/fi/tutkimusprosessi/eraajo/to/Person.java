package fi.tutkimusprosessi.eraajo.to;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Person {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	
	private String group;
	private Integer count;
	
}
