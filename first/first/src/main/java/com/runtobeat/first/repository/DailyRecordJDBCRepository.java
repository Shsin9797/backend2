package com.runtobeat.first.repository;

import com.runtobeat.first.entity.DailyRecord;
import com.runtobeat.first.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DailyRecordJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DailyRecordRepository dailyRecordRepository;

    public DailyRecordJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Record record) {
        String sql = "";

        //원래있는값 중에서 현재 날짜로 가져 오기
        DailyRecord a  = dailyRecordRepository.getByYearMonthDate(record.getRecordDate());
        // 테이브에 오늘 의 레코드가 없다면 (  Void, empty 라면 ..isEmpty()) #####
        //그냥 바로 저장
        // dailyTotalDistance <-runningDistance
        //dailyTotalTime  <- runningTime
        // yearMonthDate <- recordDate
        // dailRecordPace <- recordPace
        // dailyRunningStep <- runningStep
        dailyRecordRepository.save(
                new DailyRecord(
                        record.getRunningDistance(),
                        record.getRunningTime(),
                        record.getRecordDate(),
                        record.getRecordPace(),
                        record.getRunningStep()));
        // 오늘의 레코드가 있다면  else
        //원래있던값

        // 추가적 으로 jpa 업데이트방법.. 찾아보기 .. .######

        a.setDailyTotalDistance(a.getDailyTotalDistance()+record.getRunningDistance();
        a.setDailyTotalTime(a.getDailyTotalTime()+ record.getRunningTime()); // 시간을 datetime을 초로 변경해서 저장해둬 해야할것같은데?-> 나중에 쓸때는 60으로 나눠서 시,분,초 구분하는걸로  #####
        a.setDailyRecordPace(((a.getDailyTotalDistance()+record.getRunningDistance())/ (a.getDailyTotalTime()+ record.getRunningTime())));
        a.setDailyRunningStep(a.getDailyRunningStep() + record.getRunningStep());

        //
        """ ㅡ쓰레기 코드 dailyRecordRepository.update(
                new DailyRecord(a.getDailyRecordId(),//그대로
                        a.getDailyTotalDistance()+record.getRunningDistance(), //합산
                        a.getDailyTotalTime()+ record.getRunningTime(), //합산
                        record.getRecordDate(), // 그대로
                        ((a.getDailyTotalDistance()+record.getRunningDistance())/ (a.getDailyTotalTime()+ record.getRunningTime())), // 평균
                        record.getRunningStep()) // 합산 );"""
    }
}
