def project = 'Sprboot'
def branchApi = new URL("https://api.github.com/repos/projectabhi/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
	def branchName = it.name
	def jobName = "${project}-${branchName}".replaceAll('/','-')
	def fullPath = "MyFolder/${branchName}"
	
	folder("${fullPath}") {
		displayName("${branchName}")
		description("${branchName}")
	}
	
	listView("${fullPath}") {
    		description("${branchName}")
		jobs {
			name("${fullPath}/${jobName}")
		}
		jobFilters {
			status {
			    status(Status.UNSTABLE)
			}
		}
	}
	
	pipelineJob("MyFolder/${branchName}/"+jobName) {
		def repo = "https://github.com/projectabhi/${project}.git"
		definition {
			cpsScm {
				scm {
					git {
						remote { 
							url(repo) 
						}
					branch("${branchName}")
					scriptPath('CamelSample/Jenkinsfile')
					extensions { }  // required as otherwise it may try to tag the repo, which you may not want
					}
				}
			}
		}
	}
}
