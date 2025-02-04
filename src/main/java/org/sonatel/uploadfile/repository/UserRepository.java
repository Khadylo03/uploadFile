package org.sonatel.uploadfile.repository;

import org.sonatel.uploadfile.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

   // Optional<UserEntity> findByUserName(String username);

}
