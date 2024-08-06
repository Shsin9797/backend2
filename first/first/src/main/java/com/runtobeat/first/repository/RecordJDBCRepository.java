package com.runtobeat.first.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecordJDBCRepository {

    private final JdbcTemplate jdbcTemplate;
    RecordRepository recordRepository;

    public RecordJDBCRepository(JdbcTemplate jdbcTemplate, RecordRepository recordRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.recordRepository = recordRepository;
    }

    public Integer getTodayMyRanking(Long recordId) {
        String sql = "SELECT r.ranking FROM (" +
                "    SELECT record_id, record_pace, " +
                "           ROW_NUMBER() OVER (ORDER BY record_pace) AS ranking " +
                "    FROM record " +
                "    WHERE record_date = CURDATE() " +
                ") AS r " +
                "WHERE r.record_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{recordId}, Integer.class);
    }

//    public Integer getTodayMyThisRanking2(String memberId, String recordId) {
//        String sql = "SELECT ranking " +
//                "FROM ( " +
//                "    SELECT member_id, record_id, " +
//                "           ROW_NUMBER() OVER (PARTITION BY record_id ORDER BY score DESC) AS ranking " +
//                "    FROM daily_records " +
//                "    WHERE DATE(created_at) = CURRENT_DATE " +
//                "      AND record_id = ? " +
//                ") AS ranked " +
//                "WHERE member_id = ?";
//
//        return jdbcTemplate.queryForObject(sql, Integer.class, recordId, memberId);
//    }

    public Double getTodayTotalUserRecordAvgPace() {
        String sql = "SELECT AVG(record_pace) AS avg_pace " +
                "FROM record " +
                "WHERE record_date = CURDATE()";

        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Integer getTodayTotalRecordCount() {
        String sql = "SELECT COUNT(*) FROM record WHERE record_date = CURDATE()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
