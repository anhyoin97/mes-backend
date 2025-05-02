package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

//@SpringBootApplication
public class SpringJpaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaExampleApplication.class, args);
	}
	
	// 이 Bean은 프로그램 실행 직후 실행
//	@Bean
//	public CommandLineRunner demo(UserRepository userRepository) {
//		return (args) -> {
//			User user = new User();
//			user.setName("테스터");
//			user.setEmail("ahi1105@kakao.com");
//			
//			userRepository.save(user);
//			System.out.println("사용자 정보 저장 완료.");
//		};
//	}

}
