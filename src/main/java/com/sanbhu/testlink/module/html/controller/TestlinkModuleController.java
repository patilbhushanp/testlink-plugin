package com.sanbhu.testlink.module.html.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanbhu.testlink.module.controller.BaseHtmlController;
import com.sanbhu.testlink.module.database.views.TableView;

@Controller
public class TestlinkModuleController extends BaseHtmlController {

	@Autowired
	private TableView tableView;

	@RequestMapping("/runQuery")
	public String showDashboard(Map<String, Object> model) {
		return "query/runQuery";
	}

	@RequestMapping(value = "/getColumnInformation", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.POST)
	public @ResponseBody String getColumnInformation(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder stringBuilder = new StringBuilder();
		String sqlQuery = request.getParameter("sql");
		if (StringUtils.isNotBlank(sqlQuery)) {
			if (sqlQuery.indexOf(';') > 0) {
				sqlQuery = sqlQuery.substring(0, sqlQuery.indexOf(';'));
			}
			request.getSession().setAttribute("sqlQuery", sqlQuery);
			stringBuilder.append(
					"<table id=\"resultTable\" class=\"table table-striped table-bordered\" cellspacing=\"0\" width=\"100%\">");
			stringBuilder.append("<thead>");
			stringBuilder.append("<tr>");
			for (String columnName : tableView.getColumnList(sqlQuery)) {
				stringBuilder.append("<th>");
				stringBuilder.append(columnName);
				stringBuilder.append("</th>");
			}
			stringBuilder.append("</tr>");
			stringBuilder.append("</thead>");
			stringBuilder.append("</table>");
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		return stringBuilder.toString();
	}
}