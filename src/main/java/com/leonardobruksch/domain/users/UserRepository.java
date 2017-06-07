package com.leonardobruksch.domain.users;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by I848568 on 16/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findByPassword(String password);
}
