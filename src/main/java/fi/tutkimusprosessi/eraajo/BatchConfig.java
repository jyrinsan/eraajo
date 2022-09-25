package fi.tutkimusprosessi.eraajo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	  public PersonWriter writer() {
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