package com.zimaku.zimaku;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.clients.repository.ClientRepository;
import com.zimaku.zimaku.domain.sales.orders.model.Order;
import com.zimaku.zimaku.domain.sales.orders.repository.OrderRepository;
import com.zimaku.zimaku.domain.sales.price.model.Price;
import com.zimaku.zimaku.domain.sales.price.repository.PriceRepository;
import com.zimaku.zimaku.domain.user.entity.Privilege;
import com.zimaku.zimaku.domain.user.entity.Role;
import com.zimaku.zimaku.domain.user.entity.User;
import com.zimaku.zimaku.domain.user.repository.PrivilegeRepository;
import com.zimaku.zimaku.domain.user.repository.RoleRepository;
import com.zimaku.zimaku.domain.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository,
									RoleRepository roleRepository,
									PrivilegeRepository privilegeRepository,
									PasswordEncoder passwordEncoder,
									OrderRepository orderRepository,
									ClientRepository clientRepository,
									PriceRepository priceRepository){
		return args -> {

			Privilege readPrivilege = Privilege.builder()
					.name("READ_PRIVILEGE")
					.build();

			Privilege writePrivilege = Privilege.builder()
					.name("WRITE_PRIVILEGE")
					.build();

			List<Privilege> privilegeList = Arrays.asList(
					readPrivilege, writePrivilege
			);

			Role adminRole = Role.builder()
					.title("ROLE_ADMIN")
					//.privileges(privilegeList)
					.build();

			Role userRole = Role.builder()
					.title("ROLE_USER")
					//.privileges(Arrays.asList(readPrivilege))
					.build();

			privilegeRepository.save(readPrivilege);
			privilegeRepository.save(writePrivilege);

			roleRepository.save(adminRole);
			roleRepository.save(userRole);

			User admin = User.builder()
					.email("admin@email.com")
					.firstName("user")
					.lastName("surname")
					.department("Production")
					.phoneNumber("0000 000 000")
					.isActive(true)
					.password(passwordEncoder.encode("password"))
					.roles(Arrays.asList(adminRole))
					.build();

			User user = User.builder()
					.email("user@email.com")
					.firstName("user")
					.lastName("surname")
					.department("Sales")
					.phoneNumber("0000 000 000")
					.isActive(true)
					.password(passwordEncoder.encode("password"))
					.roles(Arrays.asList(userRole))
					.build();

			userRepository.save(admin);
			userRepository.save(user);

			var client = Client.builder()
					.firstName("name")
					.lastName("lastName")
					.address("client address")
					.phoneNumber("00009098908")
					.clientType("FARMER")
					.build();

			var price = Price.builder()
							.unitPrice(0.67)
							.currency("USD")
							.build();

			clientRepository.save(client);
			priceRepository.save(price);

			orderRepository.save(
					Order.builder()
							.collectionDate("23-01-2025")
							.quantity(34)
							.isPaid(true)
							.comments("comments")
							.client(client)
							.price(price)
							.build()
			);

/*			eggsRepository.save(
					Eggs.builder()
							.quantity(20)
							.hatchable(20)
							.rejects(20)
							.batchNumber("AD324")
							.isDispatched(false)
							.build());

			dispatchRepository.save(
					Dispatch.builder()
							.quantity(7)
							.batchNumber("TY33")
							.totalStockReceived(7)
							.dateStockReceived("23 Dec 2024")
							.build());*/

		};
	}

}
