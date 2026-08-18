pipeline {
	agent any {}
	stages {
		stage ('1.Build') {
			steps {
				sh 'mvn clean package'
				}
		}
		stage ('2.Sonar-Scanner') {
			steps {
				//Ejecutar análisis de sonar
				}
		}
		stage ('3.Docker Build') {
			steps {
				sh 'docker build -t ${IMAGE_NAME}:${params.IMAGE_VERSION} .'
			}
		}
        stage ('4.Docker Push') {
            steps {

                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-yappy',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''
						docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
                        docker push ${IMAGE_NAME}:${params.IMAGE_VERSION}
                    '''
                }
            }
        }
        stage ('5.Kubernets Deploy') {
			steps {
				sh "kubectl version --client"
				sh "kubectl set image deployment/yappy yappy=${IMAGE_NAME}:${params.IMAGE_VERSION}"
			}
		}
    }
    parameters {
		string (
			name: 'IMAGE_VERSION',
			defaultValue: '1.0.1',
			description: 'Version de la imagen de Docker'
		)
	}
	environment {
		IMAGE_NAME = 'eduarperez/yappy'
	}
}