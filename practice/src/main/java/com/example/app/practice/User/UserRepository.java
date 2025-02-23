
package com.example.app.practice.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);  // Ensure this method is correctly defined
    User findByEmail(String email);  // Ensure this method is correctly defined
}
