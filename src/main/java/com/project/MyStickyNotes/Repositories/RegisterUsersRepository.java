package com.project.MyStickyNotes.Repositories;


import com.project.MyStickyNotes.Entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface RegisterUsersRepository extends JpaRepository<RegisterEntity,String> {


    List<RegisterEntity> findAllByUsername(String username);
    RegisterEntity findByUsername(String username);

    @Query(value = "select * from register_tbl where username = :username", nativeQuery=true)
    RegisterEntity findDetailsByUsername(@Param("username") String username);

    @Query(value = "select count(*) from register_tbl where username = :username", nativeQuery=true)
    int countByUsername(String username);



//    @Query(value = "select * from register_tbl where username = :username", nativeQuery=true)
//    RegisterEntity findDetailsByUsernameAndDates(@Param("username") String username);
}
