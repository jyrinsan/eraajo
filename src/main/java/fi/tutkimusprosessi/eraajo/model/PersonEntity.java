package fi.tutkimusprosessi.eraajo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(
			name = "person_id",
			nullable = false)
	private Integer id;
	
	@Column(
			name = "first_name",
			nullable = false)
	private String firstName;
	
	@Column(
			name = "last_name",
			nullable = false)
	private String lastName;
	
	@Column(
			name = "birth_date",
			nullable = false)
	private LocalDate birthDate;

}
