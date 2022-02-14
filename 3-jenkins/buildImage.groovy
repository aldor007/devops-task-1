pipeline {

    agent {
        kubernetes {
            inheritFrom 'artifacts-builder'
            defaultContainer 'builder'
        }
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    container('kaniko')  {
                        sh "/kaniko/executor -f `pwd`/1-Docker-ayes/Dockerfile -c `pwd`/1-Docker-ayes  --cache=true --destination=somerepo/litecoin:0.18.1 --cleanup"
                    }
                }
            }
        }

    }
    post {
        success {
            cleanWs()
        }
    }
}