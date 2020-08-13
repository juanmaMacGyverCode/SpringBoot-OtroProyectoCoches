package es.mariasoria.cardatabase;

import es.mariasoria.cardatabase.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardatabaseApplication {

	@Autowired
	private CarRepository repository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	// CommandLineRunner interface allows us to execute additional code before the application has fully started
	// Therefore, it is a good point to add demo data to your database
	@Bean
	CommandLineRunner runner(){
		return args -> {
			// Add owner objects and save these to db
			Owner owner1 = new Owner("Maria" , "Soria");
			Owner owner2 = new Owner("Adam" , "Flint");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);
			// Save example data to database
			repository.save(Car.builder()
					.brand("Seat")
					.color("red")
					.model("Ibiza")
					.price(15000)
					.year(2005)
					.owner(owner1).build());
			repository.save(Car.builder()
					.brand("Chevrolet")
					.color("grey")
					.model("Epica")
					.price(30000)
					.year(2010)
					.owner(owner1).build());
			repository.save(Car.builder()
					.brand("BMW")
					.color("navy blue")
					.model("X6")
					.price(45000)
					.year(2019)
					.owner(owner2).build());
			// username: user password: user
			userRepository.save(new User("user",
					"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi",
					"USER"));
			// username: admin password: admin
			userRepository.save(new User("admin",
					"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG",
					"ADMIN"));
		};
	}

}
