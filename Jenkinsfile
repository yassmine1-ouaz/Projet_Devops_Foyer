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
                        sh 'mvn sonar:sonar -Dsonar.login=squ_d5e70ad53e9a2cb4224b30e7545d06c7bb6faf98'
                    }
                }

        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

    }
}
