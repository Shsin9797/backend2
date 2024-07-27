package com.runtobeat.first.repository;

import com.runtobeat.first.entity.MonthlyRecord;
import com.runtobeat.first.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Repository
public class MonthlyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    MonthlyRecordRepository monthlyRecordRepository;

    public MonthlyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long toSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }

    public LocalTime toLocalTime(long seconds) {
        return LocalTime.ofSecondOfDay(seconds);
    }

    public String getMonthYear(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(formatter);
    }

    public void save(Record record) {
        String monthYear = getMonthYear(record.getRecordDate());
        MonthlyRecord existingRecord = monthlyRecordRepository.getByMonthYear(monthYear);

        if (existingRecord == null) {
            MonthlyRecord newRecord = new MonthlyRecord(
                    record.getRunningDistance(),
                    record.getRunningTime(),
                    record.getRecordDate(),
                    record.getRecordPace(),
                    record.getRunningStep(),
                    monthYear
            );
            monthlyRecordRepository.save(newRecord);
        } else {
            existingRecord.setMonthlyTotalDistance(existingRecord.getMonthlyTotalDistance() + record.getRunningDistance());
            long totalExistingSeconds = toSeconds(existingRecord.getMonthlyTotalTime());
            long totalNewSeconds = toSeconds(record.getRunningTime());
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            existingRecord.setMonthlyTotalTime(toLocalTime(updateTotalSeconds));

            double newTotalDistance = existingRecord.getMonthlyTotalDistance();
            double newTotalTimeInHours = (double) updateTotalSeconds / 3600.0;
            existingRecord.setMonthlyRecordPace(updateTotalSeconds/newTotalDistance);
            existingRecord.setMonthlyRunningStep(existingRecord.getMonthlyRunningStep() + record.getRunningStep());

            monthlyRecordRepository.save(existingRecord);
        }
    }
}
