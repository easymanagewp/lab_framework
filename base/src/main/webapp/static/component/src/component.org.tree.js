define('org.tree',['config','tree'],function(config,tree){
	return function($element){
		$element.data('datasource',config.dataSource.org);
		tree($element);
	}
})