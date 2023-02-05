
pipeline {
    agent any
    environment {     //Custom environments which will be available in all stages
        DOCKER_TAG = latest
        DOCKER_CREDENTIALS = credentials('github_vachev')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "Building ${env.DOCKER_TAG}"
            }
        }
        stage('Test') {
            when {
                expression {
                    env.BRANCH_NAME == 'main' ||  env.BRANCH_NAME == 'master'
                }
            }
            steps {
                echo "This is branch ${env.BRANCH_NAME}"
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo "Using credentials: ${DOCKER_CREDENTIALS}"
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