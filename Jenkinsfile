pipeline {
  agent any
  stages {
    stage('unit test') {
      steps {
        script {
          sh './gradlew test jacocoTestReport'
        }

      }
    }

    stage('cucumber test') {
      steps {
        script {
          sh './gradlew cucumber'
        }

      }
    }

    stage('cucumber reports') {
      steps {
        cucumber(buildStatus: 'PASSED', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target')
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

    stage('email results') {
      steps {
        emailext(body: 'Job ${PROJECT_NAME} build ${BUILD_NUMBER} \n\nMore info at: ${BUILD_URL}', subject: 'Jenkins Build $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'taherul_khan@hotmail.co.uk')
      }
    }

  }
}