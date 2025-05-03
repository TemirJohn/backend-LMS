package org.temirjohn.temirjhon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
}
