<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='cache-control' content='no-cache' />
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
<title>Table Sorter</title>
<script src='https://code.jquery.com/jquery-3.2.1.slim.js'
	integrity='sha256-tA8y0XqiwnpwmOIl3SGAcFl2RvxHjA8qp0+1uCGmRmg='
	crossorigin='anonymous'></script>
<script> /* JQuery */ var data = [ ['name', 'Java', 'Node', 'Perl'], ['Gu', 80, 70, 30], ['Kim', 90, 60, 80], ['Lee', 70, 70, 70], ['Brad', 50, 90, 90] ]; $( document ).ready(function() { var table = $('<table>').attr('border', 1).attr('width', 200); $('#targetPn').append( table ); $.each(data, function(inx, row ) { var tr = $('<tr>'); table.append(tr); $.each(row, function(inx, col ) { var td = $('<td>'); tr.append(td.html(col)); }); }); }); var sortType = 'asc'; function sortTable(){ sortType = (sortType === 'asc') ? 'desc':'asc'; var chkSort = true; while (chkSort){ chkSort = false; //$('table').find('tr').each(function(inx, row) { $('table > tr').each(function(inx, row) { if (inx===0 || !row.nextSibling) return; var fCell = row.cells[0].innerHTML.toLowerCase(); var sCell = row.nextSibling.cells[0].innerHTML.toLowerCase(); if ( (sortType === 'asc' && fCell > sCell) || (sortType === 'desc' && fCell < sCell) ) { $( row.nextSibling ).insertBefore( $(row) ); chkSort = true; } }); } } </script>
</head>
<body>
	<button type='button' onclick='sortTable()'>Sort</button>
	<div id='targetPn' style='width: 130px'></div>
</body>
</html>

