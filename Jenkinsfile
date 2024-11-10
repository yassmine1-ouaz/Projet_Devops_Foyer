pipeline {
    agent any

    tools {
        maven "M2_HOME"  // Assurez-vous que c'est le bon nom
    }

    stages {
        stage('Récupération du code') {
            steps {
                git branch: 'BelhassenRezgui-groupe2-Foyer', url: 'https://github.com/yassmine1-ouaz/Projet_Devops_Foyer.git'
            }
        }

         stage('Clean & Build') {
                    steps {
                       sh 'mvn clean install -DskipTests'

                }
                stage('Create target directory') {
                                    steps {
                                       sh 'mvn clean package'

                                }
        stage('Tests Mockito et junit') {
                    steps {
                       sh 'mvn test'
                    }
                }

         stage('SonarQube analysis') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=squ_d5e70ad53e9a2cb4224b30e7545d06c7bb6faf98'
                    }
                }

        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

        stage('Build image') {
                   steps {
                    sh 'docker build -t belhassen_rezgui_tpfoyer .'
                   }
                }

                stage('Deploy Image to DockerHub') {
                   steps {
                 sh 'docker login -u belho -p Belho27666629.'
                 echo "next"
                 sh 'docker tag belhassen_rezgui_tpfoyer belho/belhassen_rezgui_tpfoyer:latest '
                 sh 'docker push belho/belhassen_rezgui_tpfoyer:latest'
                      }
               }

         stage('Vérification de Prometheus') {
                    steps {
                        script {
                            // Attendre un peu pour que Prometheus démarre
                            sleep(10)
                            sh 'curl -f http://localhost:9090/-/ready || echo "Prometheus is not ready"'
                        }
                    }
                }
                stage('Vérification de Grafana') {
                    steps {
                        script {
                            // Attendre un peu pour que Grafana démarre
                            sleep(10)
                            sh 'curl -f http://localhost:3000/api/health || echo "Grafana is not running"'
                        }
                    }


    }
}
}
