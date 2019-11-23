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

    stage('Cucumber Reports') {
      steps {
        cucumber(buildStatus: 'UNSTABLE', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target')
      }
    }

    stage('Artifacts') {
      steps {
        archiveArtifacts(onlyIfSuccessful: true, allowEmptyArchive: true, artifacts: 'build/libs/*.jar')
      }
    }

  }
}