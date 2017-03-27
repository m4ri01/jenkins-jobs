String app = "BelajarJenkins"

folder("${app}") {
  description "ini adalah description"
}

job("${app}/BuildGradle") {
    description "coba belajar jenkins"
    logRotator {
        daysToKeep(7)
        numToKeep(10)
    }
scm {
	git {
		remote {
		    url('https://github.com/m4ri01/jenkins-jobs.git')
	}
	branch ('master')

    }
}


    triggers {
        scm('H/2 * * * *')
    } 

    steps {
        shell('''npm install
	npm run build
	tar -cvf dist.tar.gz dist
	''')
    }
	
	publishers {
	    archiveArtifacts {
		pattern("dist.tar.gz")
		onlyIfSuccessful()
		}

	}
}
