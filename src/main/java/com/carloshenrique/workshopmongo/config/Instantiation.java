package com.carloshenrique.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.carloshenrique.workshopmongo.domain.Post;
import com.carloshenrique.workshopmongo.domain.User;
import com.carloshenrique.workshopmongo.dto.AuthorDTO;
import com.carloshenrique.workshopmongo.repository.PostRepository;
import com.carloshenrique.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

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
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, bob, alex));
		
		Post post1=  new Post(null, sdf.parse("21/03/2018"), "partiu viagem", "Vou viajar para São paulo. Abraços!", new AuthorDTO(maria));
		Post post2=  new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
				
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
