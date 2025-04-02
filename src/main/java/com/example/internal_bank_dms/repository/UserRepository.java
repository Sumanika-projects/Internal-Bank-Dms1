package com.example.internal_bank_dms.repository;

import com.example.internal_bank_dms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     boolean findByEmail(String email);

}
