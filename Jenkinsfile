def projectName = 'my-app-backend'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
  agent any

  tools{
    maven 'Maven 3.6.3'
  }

  stages {
     stage('Build docker image') {
          steps {
            sh "docker build -t ${dockerImageTag} ."
          }
      }

      stage('Run Application Tests'){
        steps{
            sh "mvn -Dmaven.test.skip=false clean package"
        }
      }


    stage('Deploy Container To Openshift') {
      steps {
        sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app ${dockerImageTag} -l version=${version}"
        sh "oc expose svc/${projectName}"
      }
    }
  }
}
