package com.yomounew.user.domain.repository;

import com.yomounew.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * email or userNameからユーザーを取得
     * @param userId String
     * @return user User
     */
    @Query("SELECT u FROM User u WHERE u.email = :userId OR u.userName = :userId")
    User findUser(String userId);
}
