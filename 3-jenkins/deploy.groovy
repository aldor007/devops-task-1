
pipeline {

    agent {
        kubernetes {
            inheritFrom 'deploy'
            defaultContainer 'deploy'
        }
    }

    stages {
        stage('Login to cluster') {
            withCredentials([azureServicePrincipal('dev')]) {
                sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
                sh "az aks get-credentials --admin --resource-group rg-name --name dev-0"
          }
        }

        stage('Deploy') {
            steps {
                script {
                    sh "kubectl apply -f 2-k8s-FTW/"
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