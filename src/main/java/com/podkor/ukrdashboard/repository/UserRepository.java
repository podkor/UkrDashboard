package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    boolean existsByUsername(String username);

    User findByUsernameAndActiveIsTrue(String username);

    List<User> findAllByUsernameIn(Set<String> usernames);

    @Query("select email from User where username in (:username)")
    String findEmailByUsername(String username);

    List<User> findAllByActiveIsTrue();
}