package com.daw.daw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Collection;
import com.daw.daw.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByName(String username);
    Optional <User> findByEmail(String email);
    Optional <User> findByPhone(String phone);
    Optional <User> findByEncodedPassword(String encodedPassword);
    Collection<User> findByRolesContaining(String role);
    Optional <User> getUserById(Long id);
    Page <User> findAll(Pageable pageable);


} 
    
    

