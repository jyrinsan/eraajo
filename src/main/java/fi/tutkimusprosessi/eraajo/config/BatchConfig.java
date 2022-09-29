package fi.tutkimusprosessi.eraajo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fi.tutkimusprosessi.eraajo.batch.PersonProcessor;
import fi.tutkimusprosessi.eraajo.batch.PersonReader;
import fi.tutkimusprosessi.eraajo.batch.PersonWriter;
import fi.tutkimusprosessi.eraajo.to.Person;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	  @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  @Bean
	  public PersonProcessor processor() {
	    return new PersonProcessor();
	  }
	  
	  @Bean
	  public PersonReader reader() {
	    return new PersonReader();
	  }
	  
	  @Bean
	  public ItemWriter<? super Person> writer() {
	    return new PersonWriter();
	  }
	  
	  @Bean
	  public Job job(Step step1) {
	    return jobBuilderFactory.get("job")
	      .incrementer(new RunIdIncrementer())
	      //.listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1() {
	    return stepBuilderFactory.get("step1")
	      .<Person, Person> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer())
	      .build();
	  }

}