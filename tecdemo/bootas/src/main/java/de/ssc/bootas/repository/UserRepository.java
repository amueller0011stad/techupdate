package de.ssc.bootas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ssc.bootas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findOneByEmail(String email);
}
