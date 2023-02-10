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
      stage('Increase version') {
          steps {
              script {
                  sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.nextMinorVersion}.\\\${parsedVersion.incrementalVersion} versions:commit'
                  def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                  def newVersion = matcher[1][1]
                  echo newVersion
                  env.NEW_VERSION_DOCKER = "$newVersion-$BUILD_NUMBER"
              }
          }
      }
    stage('Push version to GIT') {
        steps {
            script {
                withCredentials([usernamePassword(credentialsId: 'github_token', usernameVariable: 'USERNAME'), passwordVariable: 'PASSWORD')]) {
                   def author = sh(returnStdout: true, script: "git log -1 --pretty=format:'%an'").trim()
                   echo author

                   sh 'git status'

                   sh "git remote set-url origin https://${USERNAME}@github.com/valentinvachev/organizer-api.git"
                   sh 'git config --global user.name "JENKINS_TECHNICAL_USER"'
                   sh 'git config --global user.email "jenkins@technical.com"'

                   sh 'git add .'
                   sh 'git commit -m "Change version"'
                   sh 'git push origin HEAD:main'
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