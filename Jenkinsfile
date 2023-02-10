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
    stage('Push version to GIT') {
        steps {
            script {
                withCredentials([usernamePassword(credentialsId: 'vachev_github', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    def author = sh(returnStdout: true, script: "git log -1 --pretty=format:'%an'").trim()
                    echo author

                    if (author == 'JENKINS_TECHNICAL_USER') {
                        sh 'git config --global user.name "JENKINS_TECHNICAL_USER"'
                        sh 'git status'
                        sh 'git remote -v'

                        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/valentinvachev/organizer-api.git"
                        sh 'git remote -v'
                    }
                }
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