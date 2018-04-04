$(document).ready(function() {
	initEvents();	
	getAllTableList();
	$('.dataTable').on('dblclick', 'tbody td', function() {
		var sqlQuery = 'select * from ' + this.textContent + ';';
		$("#sqlQuery").val(sqlQuery);
		getTableData(sqlQuery);
	})
	$('.dataTable').on('click', 'tbody td', function() {
		var sqlQuery = 'select * from ' + this.textContent + ';';
		$("#sqlQuery").val($("#sqlQuery").val() + '\n' + sqlQuery);
	})
	$('#schemaTablesDiv').show();
	$('#loadingDiv').hide();
});


function initEvents(){
	$("#executeButton").click(function() {
		var query = $("#sqlQuery").val();
		getTableData(query);
	});
}

function getTableData(query) {	
	$('#resultTableDiv').empty();
	$.ajax({
		url: "/getColumnInformation",
		type: "POST",
		data: "sql="+query,
		async:false,
		success: function(html){
			$("#resultTableDiv").append(html);
		}
	});
	$('#resultTable').DataTable({
		"ajax" : '/getTableData',
		searching : false,
		paging : true,
		info : false
	});

}
function getAllTableList(){
	$('#schemaTables').DataTable({
		"ajax" : '/getAllTables',
		"columns" : [ {
			"data" : "tableName",
			"width" : "80%"
		}],
		searching : false,
		paging : false,
		info : false
	});
}