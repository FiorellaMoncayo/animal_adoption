package com.example.animal_adoptation.infrastructure.repositories;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.animal_adoptation.infrastructure.entities.ShelterBBD;

@Repository
public interface ShelterRepository extends JpaRepository<ShelterBBD, Integer> {

	Optional<ShelterBBD> findBysheltername(String sheltername);

	/*@Modifying
	@Transactional
	@Query("UPDATE ShelterBBD s SET s.password = :password WHERE s.sheltername = :sheltername")
	int updatePassword(@Param("sheltername") String sheltername,
					   @Param("password") String password);*/

	@Modifying
	@Transactional
	@Query("DELETE FROM ShelterBBD s WHERE s.sheltername = :sheltername")
	int deleteBysheltername(@Param("sheltername") String sheltername);

	/*@Modifying
	@Transactional
	@Query("UPDATE ShelterBBD s SET s.sheltername = :newsheltername, s.password = :password WHERE s.id = :id")
	int updateShelter(@Param("id") int id,
					  @Param("newsheltername") String newsheltername,
					  @Param("password") String password);*/
}
