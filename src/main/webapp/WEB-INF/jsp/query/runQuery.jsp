	<jsp:include page="../header/header.jsp" />
	<jsp:include page="../navigator/navigator.jsp" />
		<div class="wrapper wrapper-content">
			<div id="loadingDiv" class="loader"></div>
			<div class="row">
				<div class="col-lg-12 ibox-content" id="ibox-content">	
					<div class="col-lg-12 pull-left">
						<h4 class="title">Query Executor</h4>
					</div>
					<div class="col-lg-8 pull-left">
						<textarea class="form-control" rows="7" id="sqlQuery" name="sqlQuery" ></textarea><br/><br/>
						<div id="resultTableDiv" style="width:100%;" class="" >
							&nbsp;
						</div>
					</div>
					<div class="col-lg-4 pull-left">
						<button type="button" class="btn btn-success" id="executeButton" naem="executeButton" >Execute</button>
						<div id="schemaTablesDiv" style="width:100%;" class="" >
							<table id="schemaTables" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Table List</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>				
		</div>
<jsp:include page="../include_js/include_js.jsp" />
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="/js/query/runQuery.js"></script>
<jsp:include page="../footer/footer.jsp" />