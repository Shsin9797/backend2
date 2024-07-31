package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class DailyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DailyRecordRepository dailyRecordRepository;

    public DailyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Record record) {
        //원래있는값 중에서 현재 날짜로 가져 오기
        DailyRecord existingRecord = dailyRecordRepository.getByYearMonthDate(record.getRecordDate());
        // 테이브에 오늘 의 레코드가 없다면 (  Void, empty 라면 ..isEmpty()) #####
        //그냥 바로 저장
        // dailyTotalDistance <-runningDistance
        //dailyTotalTime  <- runningTime
        // yearMonthDate <- recordDate
        // dailRecordPace <- recordPace
        // dailyRunningStep <- runningStep
        if (existingRecord == null) {
            DailyRecord newRecord = new DailyRecord(
                    record.getMember(),
                    record.getRunningDistance(),
                    record.getRunningTime(),
                    record.getRecordDate(),
                    record.getRecordPace(),
                    record.getRunningStep());
            dailyRecordRepository.save(newRecord);
        } else {
            existingRecord.setDailyTotalDistance(existingRecord.getDailyTotalDistance() + record.getRunningDistance());
            long totalExistingSeconds = existingRecord.getDailyTotalTime();
            long totalNewSeconds = record.getRunningTime();
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            existingRecord.setDailyTotalTime(updateTotalSeconds);
            double newTotalDistance = existingRecord.getDailyTotalDistance();
            existingRecord.setDailyRecordPace(updateTotalSeconds/newTotalDistance);
            existingRecord.setDailyRunningStep(existingRecord.getDailyRunningStep() + record.getRunningStep());

            dailyRecordRepository.save(existingRecord);
        }
    }

    public Double getTodayAvgDistance() {
        // Get today's date
        LocalDate today = LocalDate.now();
        // Query to calculate the average distance for today
        String sql = "SELECT AVG(dailyTotalDistance) FROM DailyRecord WHERE DATE(yearMonthDate) = ?";
        // Execute the query and return the average distance
        return jdbcTemplate.queryForObject(sql, new Object[]{today}, Double.class);
    }
}
