pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        script {
          sh './gradlew test jacocoTestReport'
        }

      }
    }

    stage('cucumber') {
      steps {
        script {
          sh './gradlew cucumber'
        }

      }
    }

    stage('cucumber reports') {
      steps {
        cucumber(buildStatus: 'UNSTABLE', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target')
      }
    }

    stage('artifacts') {
      parallel {
        stage('artifacts') {
          steps {
            archiveArtifacts(onlyIfSuccessful: true, allowEmptyArchive: true, artifacts: 'build/libs/*.jar')
          }
        }

        stage('publish jacoco reports') {
          steps {
            jacoco(exclusionPattern: 'src/test*', classPattern: 'target/classes', execPattern: 'target/*.exec', sourcePattern: 'src/main/java')
          }
        }

      }
    }

  }
}