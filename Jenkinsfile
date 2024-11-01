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
        stage('Lancement de Maven') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Maven clean') {
            steps {
                         sh 'mvn clean'
                    }
        }
        stage('Maven compile') {
            steps {
                         sh 'mvn compile'
                   }
        }

        stage('Tests Mockito') {
                    steps {
                       sh 'mvn test'
        }}

        stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=squ_309d2d05302fe2d880bca65fda52d00c5c309d7c'
            }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Build Image Docker') {
                  steps {
                   sh 'docker build -t belhassenrezgui_tpfoyer .'
                  }
               }
               stage('Deploy image to Docker Hub') {
                 steps {

                    sh 'docker login -u belho -p Belho27666629.'
                    echo "next"
                    sh 'docker tag belhassenrezgui_tpfoyer belho/belhassenrezgui_tpfoyer:latest'
                    sh 'docker push belho/belhassenrezgui_tpfoyer:latest'

                 }
              }

              stage('Docker Compose') {
           steps {
               script {
                  sh 'docker compose down'
                  sh 'docker compose up -d'
               }
           }
       }

        stage('Vérification de Prometheus') {
            steps {
                script {
                    // Attendre un peu pour que Prometheus démarre
                    sleep(10)
                    sh 'curl -f http://localhost:9090/api/v1/status || echo "Prometheus is not running"'
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
