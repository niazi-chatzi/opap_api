package com.niazi.opoap.api.dao;

import com.niazi.opoap.api.entities.UpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface UpdateLogData extends JpaRepository<UpdateLog, Integer> {
    @Query(value = "SELECT max(last_updated) FROM update_log", nativeQuery = true)
    Optional<Timestamp> findLatestUpdate();
}
