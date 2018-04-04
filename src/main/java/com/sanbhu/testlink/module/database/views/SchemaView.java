package com.sanbhu.testlink.module.database.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanbhu.testlink.module.database.dao.Table;

@Transactional
@Repository
public class SchemaView {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Table> getAllTables() {
		final String sqlQuery = "show tables";
		List<Table> result = jdbcTemplate.query(sqlQuery, new TableListRowMapper());
		return result;

	}
	
	private class TableListRowMapper implements RowMapper<Table> {
		public Table mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Table table = new Table();
			table.setTableName(resultSet.getString(1));
			return table;
		}
	}
}
