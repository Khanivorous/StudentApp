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
        script{
          sh './gradlew cucumber'
        }
      }
    }
    stage('Generate HTML report') {
      cucumber buildStatus: 'UNSTABLE',
               fileIncludePattern: '**/*.json',
               rendsLimit: 10,
               classifications: [
                 [
                   'key': 'Browser',
                   'value': 'Chrome'
                 ]
               ]
      }
  }
}