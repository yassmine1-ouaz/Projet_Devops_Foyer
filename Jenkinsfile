pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }

    stages {
        stage('Récupération du code') {
            steps {
                // Obtenir du code à partir d'un dépôt GitHub
                git branch: 'BelhassenRezgui-groupe2-Foyer ', url: 'https://github.com/yassmine1-ouaz/Projet_Devops_Foyer.git'
            }
        }
         stage('Lancement de Maven') {
            steps {
                // Exécuter Maven sur un agent Unix.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // Pour exécuter Maven sur un agent Windows, utilisez
                 //bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
    }
