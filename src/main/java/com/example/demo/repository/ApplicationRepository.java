package com.example.demo.repository;

import com.example.demo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    @Modifying
    @Query("UPDATE Application a SET a.personal_identification_number_staff = :staffPin WHERE a.id_application = :applicationId")
    void updateStaffPin(@Param("applicationId") Integer applicationId, @Param("staffPin") String staffPin);
}
