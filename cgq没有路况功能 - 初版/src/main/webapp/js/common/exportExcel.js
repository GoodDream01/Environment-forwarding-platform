function ExportExcel(grid,filename) {
	var columns = grid.getColumns();
    var columnsBottom = grid.getBottomColumns();
    var rows = grid.findRows(function(){return true;});
    
    function checkColumn(column){
    	if(column.type && column.type=="indexcolumn"){
    		return true;
    	}
    	if(column.field){
    		return true;
    	}
    	if(column.header && typeof column.header !="string"){
    		return true;
    	}
    	return false;
    }
    
    function getHeaderCellId(column){
    	var gridUID = column._gridUID;
    	var id = column._id;
    	var num = 1;
    	if(column["viewIndex1"]){
    		num = 1;
    	}else if(column["viewIndex2"]){
    		num = 2;
    	}else{
    		num = 2;
    	}
    	var cellId = gridUID+"$headerCell"+num+"$"+id;
    	return cellId;
    }
    
    function getRowCellId(row,column){
		var rowIndex = row._uid;
		var columnIndex = column.columnIndex;
		var cellId = rowIndex+"$cell$"+columnIndex;
		return cellId;
    }
    
    function getColumnSpan(column){
    	var obj = {};
    	var gridUID = column._gridUID;
    	var id = column._id;
    	var cellId = getHeaderCellId(column);
    	var cell = jQuery(document.getElementById(cellId));
    	var colspan = cell.attr("colspan");
    	if(!colspan){
    		colspan = "1";
    	}
    	var rowspan = cell.attr("rowspan");
    	if(!rowspan){
    		rowspan = "1";
    	}
    	var style = cell.attr("style");
    	if(!style){
    		style = "";
    	}
    	obj.colspan = colspan;
    	obj.rowspan = rowspan;
    	obj.style = style;
    	
    	return obj;
    }
    
    function getRowSpan(row,column){
    	var obj = {};
    	var cellId = getRowCellId(row,column);
    	var cell = jQuery(document.getElementById(cellId));
    	var colspan = cell.attr("colspan");
    	if(!colspan){
    		colspan = "1";
    	}
    	var rowspan = cell.attr("rowspan");
    	if(!rowspan){
    		rowspan = "1";
    	}
    	var style = cell.attr("style");
    	if(!style){
    		style = "";
    	}
    	obj.colspan = colspan;
    	obj.rowspan = rowspan;
    	obj.style = style;
    	
    	return obj;
    }
    
    function getColumns(columns) {
        var cols = [];
        for (var i = 0; i < columns.length; i++) {
            var column = columns[i];
            
            var cellId = getHeaderCellId(column);
        	var spanObj = getColumnSpan(column);

            var col = { header: column.header, field: column.field, type: column.type, cellId: cellId, colspan: spanObj.colspan, rowspan: spanObj.rowspan };
        	if(column.dyWidth){
        		col.width = column.dyWidth;
        	}
            if (column.columns) {
                col.columns = getColumns(column.columns);
            }
            cols.push(col);
            
        }
        return cols;
    }
    function getColumnsBottom(columns) {
        columns = columns.clone();
        for (var i = columns.length - 1; i >= 0; i--) {
            var column = columns[i];
            
            if (!checkColumn(column)) {
                columns.removeAt(i);
            } else {
            	var cellId = getHeaderCellId(column);
                var c = { header: column.header, field: column.field, columnIndex: column._id,cellId: cellId };
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
                //var field = column.field;
                var cellId = column.cellId;
                var spanObj = getRowSpan(row,column);
                
                _row[cellId]=getCellValue(row,column);
                //_row["colspan"]=spanObj.colspan;
                //_row["rowspan"]=spanObj.rowspan;
                _row[cellId+"_spanObj"]=spanObj;
            }
            _rows.push(_row);      
        }
		return _rows;
	}
	function getCellValue(row,column){
		var rowIndex = row._uid;
		var columnIndex = column.columnIndex;
		var cellId = getRowCellId(row,column);
		var cell = jQuery(document.getElementById(cellId));
		var text = cell.text();

		return text;
	}
	
	var columns = getColumns(columns);
    var columnsBottom = getColumnsBottom(columnsBottom);
    var rows = getRows(columnsBottom,rows);
    
    DownLoad(HOST_URL+"/common/exportExcel.do",  {type: "excel",filename: filename, columns: columns, columnsBottom:columnsBottom, rows: rows });
    
}
function DownLoad(url, fields) {
	var iframeName = "_$E1$_";
	var formId = "_$F1$_";
 
    //创建Form
    var submitfrm = document.createElement("form");
    submitfrm.id = formId;
    submitfrm.action = url;
    submitfrm.method = "post";
    submitfrm.target = "_blank";
    //submitfrm.target = iframeName;
    document.body.appendChild(submitfrm);
    mini.append(submitfrm,"<iframe id="+iframeName+" name="+iframeName+" style='display:none;'></iframe>");
    
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
var _arr = new Array();
function SetMyWidth(){
	$("[dyWidth]").each(function(i,o){
		var obj = $(this);
		var tempObj = {};
		if(!obj.attr("name")){
			obj.attr("name",obj.attr("field"));
		}
		tempObj.name = obj.attr("name");
		tempObj.dyWidth = obj.attr("dyWidth");
		_arr.push(tempObj);
	});
}
function SetMyWidthToColumn(grid) {
    if(_arr !=null && _arr.length>0){
    	for(var i=0;i<_arr.length;i++){
    		var o = _arr[i];
    		if(o.name){
        		var column = grid.getColumn(o.name);
        		if(column){
	        		column.dyWidth=o.dyWidth;
	        		grid.updateColumn(o.name,column);
	        		//grid.updateColumn(o.name,{dyWidth:o.dyWidth});
        		}
    		}
    	}
    }
}
