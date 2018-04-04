package com.sanbhu.testlink.module.database.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class TableView {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> getColumnList(final String sqlQuery) {
		final List<String> columnList = new ArrayList<String>();
		try (Connection connection = jdbcTemplate.getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery + " LIMIT 1");
			ResultSet resultSet = pstmt.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
				columnList.add(resultSetMetaData.getColumnName(i + 1));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return columnList;
	}
	
	public List<List<String>> getTableData(String sqlQuery) {
		List<List<String>> result = jdbcTemplate.query(sqlQuery, new TableDataMapper());
		return result;

	}
	
	private class TableDataMapper implements RowMapper<List<String>> {
		public List<String> mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			List<String> rowData = new ArrayList<String>();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
				String columnValue = resultSet.getString(i + 1);
				rowData.add(columnValue);
			}
			return rowData;
		}
	}
}