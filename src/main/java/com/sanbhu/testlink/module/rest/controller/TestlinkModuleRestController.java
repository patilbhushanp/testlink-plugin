package com.sanbhu.testlink.module.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhu.testlink.module.controller.BaseRestController;
import com.sanbhu.testlink.module.database.dao.Table;
import com.sanbhu.testlink.module.database.views.SchemaView;
import com.sanbhu.testlink.module.database.views.TableView;

@RestController
public class TestlinkModuleRestController extends BaseRestController {

	@Autowired
	private SchemaView schemaView;

	@Autowired
	private TableView tableView;

	
	@RequestMapping("/getAllTables")
	public Map<String, List<Table>> getAllTables() {
		final Map<String, List<Table>> dataResult = new ConcurrentHashMap<String, List<Table>>();
		dataResult.put("data", schemaView.getAllTables());
		return dataResult;
	}
	
	@RequestMapping("/getTableData")
	public Map<String, List<List<String>>> getTableData(HttpServletRequest request, HttpServletResponse response) {
		final Map<String, List<List<String>>> dataResult = new ConcurrentHashMap<String, List<List<String>>>();
		String sqlQuery = (String)request.getSession().getAttribute("sqlQuery");
		if(StringUtils.isNotBlank(sqlQuery)) {
			final List<List<String>> recordList = tableView.getTableData(sqlQuery);
			dataResult.put("data", recordList);
		}
		return dataResult;
	}
}
