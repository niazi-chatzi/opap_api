package com.niazi.opoap.api.services;

import com.niazi.opoap.api.dao.UpdateLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UpdateLogService {
    @Autowired private UpdateLogData updateLogData;

    public Optional<Timestamp> getLastUpdateTime() {
        return updateLogData.findLatestUpdate();
    }

    public boolean isUpdatable() {
        Optional<Timestamp> latestUpdate = updateLogData.findLatestUpdate();
        if(latestUpdate.isEmpty()) return false;

        LocalDateTime twoDaysLater = latestUpdate.get().toLocalDateTime().plusDays(2);
        return LocalDateTime.now().isAfter(twoDaysLater);
    }

    public String getCountDown() {
        Optional<Timestamp> latestUpdate = updateLogData.findLatestUpdate();
        if(latestUpdate.isEmpty()) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(latestUpdate.get());
        c.add(Calendar.DATE, 2);

        Date twoDaysLater = c.getTime();
        Date now = new Date();

        long totalMils = twoDaysLater.getTime() - now.getTime();
        long hours = TimeUnit.HOURS.convert(totalMils, TimeUnit.MILLISECONDS);

        long remainingMills = totalMils - (hours * 60 * 60 * 1000);
        long minutes = TimeUnit.MINUTES.convert(remainingMills, TimeUnit.MILLISECONDS);

        remainingMills = remainingMills - (minutes * 60 * 1000);
        long seconds = TimeUnit.SECONDS.convert(remainingMills, TimeUnit.MILLISECONDS);

        return "You have to wait " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds.";
    }
}
