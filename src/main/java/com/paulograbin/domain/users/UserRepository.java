package com.paulograbin.domain.users;

import com.paulograbin.domain.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by I848568 on 16/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
