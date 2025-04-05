package com.example.animal_adoptation.infrastructure.repositories;

import com.example.animal_adoptation.infrastructure.entities.UserBBD;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBBD, Integer> {
    Optional <UserBBD> findByUsername (String username);
    // no está implementado todavia el update de password
    @Modifying
    @Transactional
    @Query("UPDATE UserBBD u SET u.password = :password WHERE u.username = :username")
    int updatePassword(@Param("username") String username,
                       @Param("password") String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserBBD u WHERE u.username = :username")
    int deleteByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserBBD u SET u.username = :newUsername, u.password = :password WHERE u.id = :id")
    int updateUser(@Param("id") Integer id,
                   @Param("newUsername") String newUsername,
                   @Param("password") String password);
}

