package com.licenta.repositories;

import com.licenta.models.User;
import com.licenta.models.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    List<Weight> findByUserOrderByDateDesc(User user);

    @SuppressWarnings("SqlResolve")
    @Query(
            value = "SELECT weight.* FROM weight, fitnessjiffy_user "
                    + "WHERE weight.user_id = fitnessjiffy_user.id "
                    + "AND fitnessjiffy_user.id = ?1 "
                    + "AND weight.date <= ?2 "
                    + "ORDER BY weight.date DESC LIMIT 1",
            nativeQuery = true
    )
    Weight findByUserMostRecentOnDate(User user, Date date);


    /**
     * "findByUserMostRecentOnDate" is used for purposes of display, and for report generation, to account for days
     * on which weight entry might have been skipped.  "findByUserAndDate", however, looks only on the specified
     * date with no adjustment... for purposes of updating a particular weight entry correctly.
     */

    Weight findByUserAndDate(
            User user,
            Date date
    );
}
