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
            sh "docker build -t ppak4dev/udemy-dmeo-client:${env.BUILD_NUMBER} ./client "
          }
         }
      }
  }
}