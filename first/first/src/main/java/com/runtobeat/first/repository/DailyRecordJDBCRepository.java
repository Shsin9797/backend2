package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public class DailyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DailyRecordRepository dailyRecordRepository;

    public DailyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public long toSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }
    public LocalTime toLocalTime(long seconds) {
        return LocalTime.ofSecondOfDay(seconds);
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
                    record.getRunningDistance(),
                    record.getRunningTime(),
                    record.getRecordDate(),
                    record.getRecordPace(),
                    record.getRunningStep());
            dailyRecordRepository.save(newRecord);
        } else {
            existingRecord.setDailyTotalDistance(existingRecord.getDailyTotalDistance() + record.getRunningDistance());
            long totalExistingSeconds = toSeconds(existingRecord.getDailyTotalTime());
            long totalNewSeconds = toSeconds(record.getRunningTime());
            long updateTotalSeconds = totalExistingSeconds + totalNewSeconds;
            existingRecord.setDailyTotalTime(toLocalTime(updateTotalSeconds));
            double newTotalDistance = existingRecord.getDailyTotalDistance();
            double newTotalTimeInHours = (double) updateTotalSeconds / 3600.0;
            existingRecord.setDailyRecordPace(updateTotalSeconds/newTotalDistance);
            existingRecord.setDailyRunningStep(existingRecord.getDailyRunningStep() + record.getRunningStep());

            dailyRecordRepository.save(existingRecord);
        }
    }
}
