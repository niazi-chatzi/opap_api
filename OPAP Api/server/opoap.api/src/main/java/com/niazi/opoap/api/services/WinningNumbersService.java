package com.niazi.opoap.api.services;

import com.niazi.opoap.api.dao.WinningNumbersData;
import com.niazi.opoap.api.entities.WinningNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WinningNumbersService {

    private final WinningNumbersData winningNumbersData;

    public WinningNumbersService(WinningNumbersData winningNumbersData) {
        this.winningNumbersData = winningNumbersData;
    }

    public List<WinningNumber> getAll() {
        return winningNumbersData.findAll();
    }

    public Optional<WinningNumber> getById(Integer id) {
        return winningNumbersData.findById(id);
    }

    public List<WinningNumber> getAllSorted() {
        return winningNumbersData.findAll(Sort.by("timesShown").descending());
        /*return winningNumbersData.findAll().stream()
                .sorted((w1, w2) -> w1.compareToReverse(w2))
                .collect(Collectors.toList());*/
    }

    public List<WinningNumber> post(List<WinningNumber> winningNumbers) {
        return winningNumbersData.saveAll(winningNumbers);
    }

    public List<WinningNumber> update(List<WinningNumber> reqWinningNumbers) {
        List<WinningNumber> winningNumbers = winningNumbersData.findAll();
        winningNumbers.forEach(w1 -> reqWinningNumbers.forEach(w2 -> {
            if(w2.getNumber() == w1.getNumber()) {
                w1.setTimesShown(w1.getTimesShown() + w2.getTimesShown());
                return;
            }
        }));
        return winningNumbersData.saveAll(reqWinningNumbers);
    }
}
