package com.runtobeat.first.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecordJDBCRepository {

    private final JdbcTemplate jdbcTemplate;
    RecordRepository RecordRepository;

    public RecordJDBCRepository(JdbcTemplate jdbcTemplate, RecordRepository RecordRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.RecordRepository = RecordRepository;
    }


    public Integer getTodayMyThisRanking(Long memberId, String recordId) {
        String sql = "SELECT rank FROM (" +
                "    SELECT memberId, recordId, recordPace, " +
                "           RANK() OVER (ORDER BY recordPace) as rank " +
                "    FROM Record " +
                "    WHERE recordDate = CURDATE() " +
                ") ranked_records " +
                "WHERE memberId = ? AND recordId = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{memberId, recordId}, Integer.class);

    }

    //아닌거같아서 미수정
    public Integer getTodayMyThisRanking2(String memberId, String recordId) {
        String sql = "SELECT ranking " +
                "FROM ( " +
                "    SELECT member_id, record_id, " +
                "           ROW_NUMBER() OVER (PARTITION BY record_id ORDER BY score DESC) as ranking " +
                "    FROM daily_records " +
                "    WHERE DATE(created_at) = CURRENT_DATE " +
                "      AND record_id = ? " +
                ") ranked " +
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
