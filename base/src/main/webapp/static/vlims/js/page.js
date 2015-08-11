var grid_data = 
[ 
	{id:"1",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
	{id:"2",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
	{id:"3",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
	{id:"4",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
	{id:"5",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
	{id:"6",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
	{id:"7",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
	{id:"8",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
	{id:"9",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
	{id:"10",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
	{id:"11",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
	{id:"12",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
	{id:"13",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
	{id:"14",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
	{id:"15",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
	{id:"16",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
	{id:"17",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
	{id:"18",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
	{id:"19",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
	{id:"20",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
	{id:"21",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
	{id:"22",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
	{id:"23",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"}
];	

jQuery(function($) {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	jQuery(grid_selector).jqGrid({
		//direction: "rtl",
		
		data: grid_data,
		datatype: "local",
		height: 250,
		colNames:['','ID','Last Sales','Name', 'Stock', 'Ship via','Notes'],
		colModel:[
			{name:'Edit',index:'Edit', width:36,resizable:false,search:false,align:'center'},
			{name:'id',index:'id', width:60, sorttype:"int"},
			{name:'sdate',index:'sdate',width:90},
			{name:'name',index:'name', width:150},
			{name:'stock',index:'stock', width:70},
			{name:'ship',index:'ship', width:90},
			{name:'note',index:'note', width:150} 
		], 

		viewrecords : true,
		rowNum:10,
		rowList:[10,20,30],
		pager : pager_selector,
		altRows: true,
		rownumbers:true,
		//toppager: true,
		
		multiselect: true,
		//multikey: "ctrlKey",
		multiboxonly: true,


		caption: "title",


		autowidth: true,
		
		gridComplete: function () {

			var ids = jQuery("#grid-table").jqGrid('getDataIDs');


			for (var i = 0; i < ids.length; i++) {

				var id = ids[i];

				var rowData = $("#grid-table").getRowData(id);
				var telHTML = $('#grid-table').jqGrid('getRowData',id).name,
					btn = '<a href="javascript:;" onclick="viewPhoneBuWei(\''+id+'\')">查看</a>'
					telHTML = '<a href="javascript:;" onclick="viewPhoneBuWei(\''+id+'\')">'+telHTML+'</a>';
				jQuery("#grid-table").jqGrid('setRowData', ids[i], { name: telHTML });
				jQuery("#grid-table").jqGrid('setRowData', ids[i], { Edit: btn });

			}

		}

	});	
	


});