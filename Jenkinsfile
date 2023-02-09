#! /usr/bin/env groovy

pipeline {
  agent any
  environment {
    DOCKER_TAG = 'latest'
  }
  parameters {
    booleanParam(name: 'skipTests', defaultValue: false, description: 'This is param for skipping tests')
  }
  tools {
    maven 'my-maven'
  }
  stages {
    stage('Test') {
      when {
        expression {
          params.skipTests == true
        }
      }
      steps {
        echo 'Test phase. Testing ...'
        sh 'mvn test'
      }
    }
    stage('Increase version') {
        steps {
            script {
                def version = readFile('pom.xml') =~ '<version>(.+)</version>'
                echo "Version will be increased from $version"
            }

        }
    }
    stage('Build') {
      steps {
        script {
          if (params.skipTests == true) {
            echo 'Building with tests'
            sh 'mvn clean package'
          } else {
            echo 'Building without tests'
            sh 'mvn clean package -DskipTests'
          }
        }
      }
    }
    stage('Deploy image') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'github_vachev', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
              sh 'docker login -u $USERNAME -p $PASSWORD'
              sh 'docker build -t valentinvachev/private-app .'
              sh 'docker push valentinvachev/private-app:${DOCKER_TAG}'
            }
        }
    }
  }
  post {
    always {
      echo 'The build ends'
    }
    success {
      echo 'The build is successfully done!'
    }
  }
}