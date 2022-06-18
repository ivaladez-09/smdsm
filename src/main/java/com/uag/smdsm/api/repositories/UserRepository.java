package com.uag.smdsm.api.repositories;

import com.uag.smdsm.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    Integer countByGenderAndBirthdayBetween(String gender, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT COUNT(*) FROM users u\n" +
            "        INNER JOIN user_disease ud ON ud.id_user=u.id\n" +
            "        INNER JOIN diseases d ON ud.id_disease=d.id\n" +
            "        WHERE u.gender = :gender AND ud.active = 1 AND d.name = :disease", nativeQuery = true)
    Integer countByGenderAndDisease(@Param("gender") String gender,
                                    @Param("disease") String disease);

    @Query(value = "SELECT COUNT(*) FROM users u\n" +
            "        INNER JOIN user_risk_factor ur ON ur.id_user=u.id\n" +
            "        INNER JOIN risk_factors r ON ur.id_risk_factor=r.id\n" +
            "        WHERE u.gender = :gender AND ur.active = 1 AND r.name = :riskFactor", nativeQuery = true)
    Integer countByGenderAndRiskFactor(@Param("gender") String gender,
                                       @Param("riskFactor") String riskFactor);
}
