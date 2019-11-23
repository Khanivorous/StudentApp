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
  }
}