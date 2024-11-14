pipeline {
    agent any

    tools {
        maven "M2_HOME"  // Assurez-vous que "M2_HOME" correspond bien à votre configuration Maven
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
        }

        stage('Create target directory') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Tests Mockito et JUnit') {
            steps {
                sh 'mvn test'
            }
        }

         stage('SonarQube analysis') {
                     steps {
                         sh 'mvn test jacoco:report'
                         sh 'mvn sonar:sonar -Dsonar.login=squ_d5e70ad53e9a2cb4224b30e7545d06c7bb6faf98'
                     }
                 }

         stage('Déploiement Nexus') {
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
   stage('Deploy with Docker Compose') {
            steps {
                dir('firstpipeline') {
                    sh 'docker compose down'
                    sh " docker compose up -d" //IMAGE_TAG=${IMAGE_TAG}

                }
            }
        }

        stage('Démarrage de Grafana et Prometheus') {
            steps {
                sh 'docker start grafana'
                sh 'docker start prometheus'
            }
        }

        stage('Vérification de Prometheus') {
            steps {
                script {
                    sleep(10)  // Attendre que Prometheus soit prêt
                    sh 'curl -f http://localhost:9090/-/ready || echo "Prometheus is not ready"'
                }
            }
        }

        stage('Vérification de Grafana') {
            steps {
                script {
                    sleep(10)  // Attendre que Grafana soit prêt
                    sh 'curl -f http://localhost:3000/api/health || echo "Grafana is not running"'
                }
            }
        }
    }
     post {
             success {
                 script {
                     // Statut de réussite du build
                     def buildStatus = 'SUCCESS'
                     def color = 'good'

                     // Envoi de la notification Slack pour un build réussi
                     slackSend(
                         channel: '#devops-notification',
                         color: color,
                         message: "Build ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER} - <${env.BUILD_URL}|Cliquez ici pour plus de détails>"
                     )
                 }
             }
             failure {
                 script {
                     // Statut d'échec du build
                     def buildStatus = 'FAILURE'
                     def color = 'danger'

                     // Envoi de la notification Slack pour un build échoué
                     slackSend(
                         channel: '#devops-notification',
                         color: color,
                         message: "Build ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER} - <${env.BUILD_URL}|Cliquez ici pour plus de détails>"
                     )
                 }
             }
         }
}
