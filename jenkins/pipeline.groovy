pipeline {
  agent {
    label 'docker-worker-pod'  
  }
  stages {
    stage('Get project') {
      steps {
        echo 'Getting project >> >> >>'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/PavloPak/zalando-ts'
      }
    }
    stage('Run tests') {
       steps {
          echo '======  Starting test  ========'
          container('maven') {
            sh "mvn clean test "
          }
         }
      }
  }
}