package com.project.MyStickyNotes.Repositories;


import com.project.MyStickyNotes.Dto.UsernameAndDates;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface DetailsRepository extends JpaRepository<DetailsEntity, Integer>,DetailsRepositoryCustom {

    @Query(value = "select * from details_tbl d where d.username  = \"firstuser\"",
            nativeQuery = true
    )
    List<DetailsEntity> findAllDetailsByUsername(String username, String fromDate, String toDate);

    @Query(value = "select * from details_tbl where username = ", nativeQuery = true)
    List<DetailsEntity> findDetailsByUsernameAndDates(UsernameAndDates usernameAndDates);

    @Query(value = "select count(*) from details_tbl where username = :username", nativeQuery=true)
    int allDetailsCount(@Param("username") String username);

    @Query(value = "select count(*) from details_tbl where username = :username and status = :status", nativeQuery=true)
    int pendingDetailsCount(@Param("username") String username,@Param("status") String status);

}
