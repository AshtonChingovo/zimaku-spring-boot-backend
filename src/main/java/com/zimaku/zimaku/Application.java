package com.zimaku.zimaku;

import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
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
									EggsRepository eggsRepository,
									PrivilegeRepository privilegeRepository,
									DispatchRepository dispatchRepository,
									PasswordEncoder passwordEncoder){
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
					.privileges(privilegeList)
					.build();

			Role userRole = Role.builder()
					.title("ROLE_USER")
					.privileges(Arrays.asList(readPrivilege))
					.build();

			privilegeRepository.save(readPrivilege);
			privilegeRepository.save(writePrivilege);

			roleRepository.save(adminRole);
			roleRepository.save(userRole);

			User admin = User.builder()
					.email("admin@email.com")
					.firstname("user")
					.surname("surname")
					.isActive(true)
					.password(passwordEncoder.encode("password"))
					.roles(Arrays.asList(adminRole))
					.build();

			User user = User.builder()
					.email("user@email.com")
					.firstname("user")
					.surname("surname")
					.isActive(true)
					.password(passwordEncoder.encode("password"))
					.roles(Arrays.asList(userRole))
					.build();

			userRepository.save(admin);
			userRepository.save(user);

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
