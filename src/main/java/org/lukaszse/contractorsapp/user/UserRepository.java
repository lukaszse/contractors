package org.lukaszse.contractorsapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<User, String> {

    Optional<User> findUserByUserName(final String userName);

    @Modifying
    @Query("update User u set u.password = :password where u.userName = :user_name")
    void changePassword(@Param("user_name") final String userName, @Param("password") final String password);
}
