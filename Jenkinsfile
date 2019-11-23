pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        './gradlew test jacocoTestReport'
      }
    }
    stage('cucumber') {
      steps {
        './gradlew cucumber'
      }
    }
  }
}