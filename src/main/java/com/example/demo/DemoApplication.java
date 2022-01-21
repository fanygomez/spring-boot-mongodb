package com.example.demo;

/**
 * @fangomez
 */
import com.example.demo.model.Address;
import com.example.demo.model.Gender;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate template){
		return  args -> {
			var email = "sebasgomez@hotmail.com";
			Address address = new Address("SV","Pasaquina","03116");
			Student student = new Student(
					null,
					"Jose",
					"Gomez",
					email,
					Gender.FEMALE,
					address,
					List.of("Medicine")
					, BigDecimal.TEN,
					LocalDateTime.now()
			);

			/* Custom Query */
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			List<Student> studentList = template.find(query,Student.class);
			if (studentList.size() > 1){
				throw new IllegalAccessException("Found many students with email "+ email);
			}

			if (studentList.isEmpty()){
				System.out.printf("Inserting student "+ student);
				repository.insert(student);
			}else{
				System.out.printf(student + "already exists");
			}
		};
	}
}
