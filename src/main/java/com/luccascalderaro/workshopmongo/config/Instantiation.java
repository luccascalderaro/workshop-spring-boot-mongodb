package com.luccascalderaro.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luccascalderaro.workshopmongo.domain.Post;
import com.luccascalderaro.workshopmongo.domain.User;
import com.luccascalderaro.workshopmongo.dto.AuthorDTO;
import com.luccascalderaro.workshopmongo.dto.CommentDTO;
import com.luccascalderaro.workshopmongo.repository.PostRepository;
import com.luccascalderaro.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "maria@gmail.com", "maria");
		User alex = new User(null, "alex@gmail.com", "alex");
		User bob = new User(null, "bob@gmail.com", "bob");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar", new AuthorDTO( maria));
		Post post2 = new Post(null, sdf.parse("25/03/2018"), "Partiuuuu Viagem", "Vou viaaaaajar", new AuthorDTO( maria));
		
		
		CommentDTO c1 = new CommentDTO("Boa viagem",sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita",sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Otimo dia",sdf.parse("21/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		
		userRepository.save(maria);

	}

}
