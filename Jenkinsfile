
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            when {
                expression {
                    env.BRANCH_NAME == 'main' ||  env.BRANCH_NAME == 'master'
                }
            }
            steps {
                echo 'This is branch ${env.BRANCH_NAME}'
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
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