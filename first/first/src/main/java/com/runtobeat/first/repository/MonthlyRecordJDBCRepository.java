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
                    record.getMember(),
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
            existingRecord.setMonthlyRecordPace(updateTotalSeconds/newTotalDistance);
            existingRecord.setMonthlyRunningStep(existingRecord.getMonthlyRunningStep() + record.getRunningStep());

            monthlyRecordRepository.save(existingRecord);
        }
    }

    public Double getThisMonthAvgDistance() {

        // Get today's date
        LocalDate today = LocalDate.now();
        // Calculate the month-year string for today
        String monthYear = getMonthYear(today);
        // SQL query to calculate the average distance for the current month
        String sql = "SELECT AVG(monthlyTotalDistance) FROM MonthlyRecord WHERE monthYear = ?";
        // Execute the query and return the average distance
        return jdbcTemplate.queryForObject(sql, new Object[]{monthYear}, Double.class);
    }
}
