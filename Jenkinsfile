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
          sh './gradlew test cucumber'
        }

      }
    }

    stage('cucumber reports') {
      steps {
        cucumber(buildStatus: 'SUCCESS', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target')
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
            jacoco(exclusionPattern: 'src/test*', classPattern: 'build/classes', execPattern: 'target/jacoco/*.exec', sourcePattern: 'src/main/java/com/khanivrous/app/*')
          }
        }

        stage('publish test results') {
          steps {
            junit 'build/test-results/**/*.xml'
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