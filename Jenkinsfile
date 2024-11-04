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

        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

    }
}
