function ExportExcel(grid,filename) {
    var columns = grid.getBottomColumns();
    var rows = grid.findRows(function(){return true;});

    function getColumns(columns) {
        columns = columns.clone();
        for (var i = columns.length - 1; i >= 0; i--) {
            var column = columns[i];
            
            if (!column.field || !column.header || typeof column.header !="string") {
                columns.removeAt(i);
            } else {
                var c = { header: column.header, field: column.field, columnIndex: column._id };
                columns[i] = c;
            }
        }
        return columns;
    }
	function getRows(columns,rows){
		columns = columns.clone();
		var _rows = new Array();
		for (var i =  0; i < rows.length; i++) {
            var row = rows[i];

            var _row = {};
            for (var j = 0; j < columns.length; j++) {
                var column = columns[j];
                var field = column.field;
                
                _row[field]=getCellValue(row,column);
            }
            _rows.push(_row);      
        }
		return _rows;
	}
	function getCellValue(row,column){
		var rowIndex = row._uid;
		var columnIndex = column.columnIndex;
		var cellId = rowIndex+"$cell$"+columnIndex;
		var cell = jQuery(document.getElementById(cellId));
		var text = cell.text();

		return text;
	}
    
    var columns = getColumns(columns);
    var rows = getRows(columns,rows);
    
    DownLoad(HOST_URL+"/common/exportExcel.do",  {type: "excel",filename: filename, columns: columns, rows: rows });
    
}
function DownLoad(url, fields) {
    
    //创建Form
    var submitfrm = document.createElement("form");
    submitfrm.action = url;
    submitfrm.method = "post";
    submitfrm.target = "_blank";
    document.body.appendChild(submitfrm);

    if (fields) {
    
        for (var p in fields) {
            var input = mini.append(submitfrm, "<input type='hidden' name='" + p + "'>");
            var v = fields[p];
            if (typeof v != "string") v = mini.encode(v);
            input.value = v;
        }
    }

    submitfrm.submit();
    setTimeout(function () {
        submitfrm.parentNode.removeChild(submitfrm);
    }, 1000);
}