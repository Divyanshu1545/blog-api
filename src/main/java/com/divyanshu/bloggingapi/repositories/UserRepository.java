package com.divyanshu.bloggingapi.repositories;

import com.divyanshu.bloggingapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
}
