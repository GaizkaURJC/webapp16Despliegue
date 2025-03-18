package com.daw.daw.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import com.daw.daw.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByName(String username);
    Optional <User> findByEmail(String email);
    Optional <User> findByTelefono(String telefono);
    Optional <User> findByEncodedPassword(String encodedPassword);
    Collection<User> findByRolesContaining(String role);
    Optional <User> getUserById(Long id);
} 
    
    

