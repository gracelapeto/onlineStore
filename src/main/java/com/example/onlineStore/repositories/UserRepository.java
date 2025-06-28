package com.example.onlineStore.repositories;

import com.example.onlineStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
