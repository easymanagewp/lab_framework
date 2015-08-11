define('config',{
		"clsNames" : {
			"code" : ".v-com-code",
			"account" : ".v-com-account",
			"account-view" : ".v-com-account-view",
			"org" : ".v-com-org",
			'org-view' : '.v-com-org-view',
			'org-tree':'.v-com-org-tree',
			'tree' : '.v-com-tree',
			'duty-view' : '.v-com-duty-view'
		},
		"dataSource" : {
			'code' : basePath + '/sys/code/{{busInfo}}/{{code}}.do',
			'account-user' : basePath + '/sys/user.do?simple=true',
			'account-account':basePath + '/sys/account.do?simple=true',
			'account-view' : basePath + '/sys/account/{{id}}.do?simple=true',
			'org' : basePath + '/sys/org.do?simple=true',
			'org-view' : basePath + '/sys/org/{{id}}.do?simple=true',
			'duty-view' : basePath + '/sys/duty/{{id}}.do'
		}
	}
);