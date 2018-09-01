package com.dy.dao;

import com.dy.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("homeDao")
public class HomeDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public int queryUserCount() {
        String sql = "select count(*) from t_user";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
    //<editor-fold desc="Answer">
    public int queryCount(String sql, Object[] args) {
        int count = this.jdbcTemplate.queryForObject(sql,args,Integer.class);
        return count;
    }
    public int update(String sql, Object[] args) {
        int count = this.jdbcTemplate.update(sql,args);
        return count;
    }
    public List<Answer> queryAnswer() {
        String sql="select * from t_answer where t_id>?";
        List<Answer> list = this.jdbcTemplate.query(sql,new Object[]{0}, new AnswerRowMapper());
        return list;
    }
    //</editor-fold>

    //region 用户
    public int queryUserCount3() {
        String sql = "select count(*) from t_user";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }
    //endregion

    class AnswerRowMapper implements RowMapper<Answer> {
        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answer user = new Answer(
                    rs.getInt("t_id"),
                    rs.getInt("t_question_id"),
                    rs.getBoolean("t_by_user"),
                    rs.getLong("t_time"),
                    rs.getString("t_content")
            );
            return user;
        }
    }
}
