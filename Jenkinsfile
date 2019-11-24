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

    stage('email results') {
      steps {
        emailext(body: '${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\\n More info at: ${env.BUILD_URL}', subject: 'Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}', to: 'taherul_khan@hotmail.co.uk', from: 'Jenkins@jenkins')
      }
    }

  }
}