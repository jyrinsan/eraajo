package fi.tutkimusprosessi.eraajo;

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
	
}
