package com.runtobeat.first.repository;

import com.runtobeat.first.entity.WeeklyRecord;
import com.runtobeat.first.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.IsoFields;

@Repository
public class WeeklyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    WeeklyRecordRepository weeklyRecordRepository;

    public WeeklyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Calculate the ISO week number and year from a LocalDate
    public String getWeekYear(LocalDate date) {
        int weekNumber = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return year + "-" + weekNumber;
    }

    public long toSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }

    public LocalTime toLocalTime(long seconds) {
        return LocalTime.ofSecondOfDay(seconds);
    }

    public void save(Record record) {
        // Calculate week year from record date
        String weekYear = getWeekYear(record.getRecordDate());

        // Retrieve existing weekly record by weekYear
        WeeklyRecord existingRecord = weeklyRecordRepository.getByWeekYear(weekYear);

        if (existingRecord == null) {
            // If no existing record, create a new WeeklyRecord
            WeeklyRecord newRecord = new WeeklyRecord(
                    record.getRunningDistance(),
                    record.getRunningTime(),
                    record.getRecordDate(),
                    record.getRecordPace(),
                    record.getRunningStep(),
                    weekYear
            );
            weeklyRecordRepository.save(newRecord);
        } else {
            // Update existing record
            existingRecord.setWeeklyTotalDistance(existingRecord.getWeeklyTotalDistance() + record.getRunningDistance());
            long totalExistingSeconds = toSeconds(existingRecord.getWeeklyTotalTime());
            long totalNewSeconds = toSeconds(record.getRunningTime());
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            existingRecord.setWeeklyTotalTime(toLocalTime(updateTotalSeconds));

            double newTotalDistance = existingRecord.getWeeklyTotalDistance();
            double newTotalTimeInHours = (double) updateTotalSeconds / 3600.0;
            existingRecord.setWeeklyRecordPace(newTotalDistance / newTotalTimeInHours);
            existingRecord.setWeeklyRunningStep(existingRecord.getWeeklyRunningStep() + record.getRunningStep());

            weeklyRecordRepository.save(existingRecord);
        }
    }
}
