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



        stage('SonarQube analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=squ_5f1592bfc2827598bc05316c1342afca5eca87dc'
            }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
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

    }
}
