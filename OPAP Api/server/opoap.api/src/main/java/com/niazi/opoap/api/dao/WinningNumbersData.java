package com.niazi.opoap.api.dao;

import com.niazi.opoap.api.entities.WinningNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinningNumbersData extends JpaRepository<WinningNumber, Integer> {}
