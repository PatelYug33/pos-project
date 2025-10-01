package com.ecom.pos.system.repository;

import com.ecom.pos.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByemail(String email);
}
