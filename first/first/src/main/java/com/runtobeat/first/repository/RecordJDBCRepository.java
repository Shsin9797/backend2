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

    public Integer getTodayMyRanking(Long memberId, Long recordId) {
        String sql = "SELECT r.rank FROM (" +
                "    SELECT memberId, recordId, recordPace, " +
                "           ROW_NUMBER() OVER (ORDER BY recordPace) AS rank " +
                "    FROM Record " +
                "    WHERE recordDate = CURDATE() " +
                ") AS r " +
                "WHERE r.memberId = ? AND r.recordId = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{memberId, recordId}, Integer.class);
    }

    public Integer getTodayMyThisRanking2(String memberId, String recordId) {
        String sql = "SELECT ranking " +
                "FROM ( " +
                "    SELECT member_id, record_id, " +
                "           ROW_NUMBER() OVER (PARTITION BY record_id ORDER BY score DESC) AS ranking " +
                "    FROM daily_records " +
                "    WHERE DATE(created_at) = CURRENT_DATE " +
                "      AND record_id = ? " +
                ") AS ranked " +
                "WHERE member_id = ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, recordId, memberId);
    }

    public Double getTodayTotalUserRecordAvgPace() {
        String sql = "SELECT AVG(recordPace) AS avg_pace " +
                "FROM Record " +
                "WHERE recordDate = CURDATE()";

        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Integer getTodayTotalRecordCount() {
        String sql = "SELECT COUNT(*) FROM Record WHERE recordDate = CURDATE()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
