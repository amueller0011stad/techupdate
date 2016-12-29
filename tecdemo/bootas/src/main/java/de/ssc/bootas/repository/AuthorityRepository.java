package de.ssc.bootas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.ssc.bootas.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
