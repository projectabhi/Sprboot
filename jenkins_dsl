def project = 'Sprboot'
def branchApi = new URL("https://api.github.com/repos/projectabhi/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
	def branchName = it.name
	def jobName = "${project}-${branchName}".replaceAll('/','-')
	pipelineJob(jobName) {
		def repo = "https://github.com/projectabhi/${project}.git"
		definition {
		    cpsScm {
		      scm {
			git {
			  remote { url(repo) }
			  branch("${branchName}")
			  scriptPath('CamelSample/Jenkinsfile')
			  extensions { }  // required as otherwise it may try to tag the repo, which you may not want
			}
		      }
		    }
		  }
	}
}
