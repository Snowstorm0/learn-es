package com.spring.es.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 代码的路
 * @date 2023/2/23
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findUserByJobOrName(String job, String name);
}