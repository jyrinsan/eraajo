package fi.tutkimusprosessi.eraajo.model;

import java.io.Serializable;

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

@Entity(name = "people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PeopleEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(
			name = "people_id",
			nullable = false)
	private Long id;
	
	@Column(
			name = "adult_count",
			nullable = false)
	private Integer adultCount;
	
	@Column(
			name = "child_count",
			nullable = false)
	private Integer childCount;

}
