pipeline {
    agent any

    
    stages {
        stage('GIT') {
            steps {
                
               echo "Getting project from GIT"
                 git 'https://github.com/yassmine1-ouaz/Projet_Devops_Foyer.git'
               //git branch: 'master', 'https://github.com/yassmine1-ouaz/Projet_Devops_Foyer.git' 
               
            }
        }
        
        stage('MVN CLEAN') {
            steps {
               echo "Cleaning project_foyer"
                sh 'mvn clean'
               
            }
        }
        
        stage('MVN COMPILE') {
            steps {
                
               echo "Compiling project_foyer"
                sh 'mvn compile'
            }
        }
        
        
        stage('MVN SONARQUBE') {
            steps {
                
               echo "Running SonarQube analysis"
               sh 'mvn sonar:sonar -Dsonar.login=squ_362f202801570b397801935e3930b0b15e91eeba'
               // sh 'mvn sonar:sonar'
            }
        }
        
<<<<<<< HEAD
         stage('NEXUS') {
=======
         stage('MVN DEPLOY') {
>>>>>>> a0e7c2b (Deploy NEXUS Jenkinsfile)
            steps {
               echo "Deploying to Nexus repository"
               // Déploie en sautant les tests avec -DskipTests
               //sh 'mvn deploy -DskipTests'
                    withCredentials([usernamePassword(credentialsId: 'nexus-credentials-id', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh 'mvn deploy -DskipTests -Dusername=$NEXUS_USERNAME -Dpassword=$NEXUS_PASSWORD'
                }
            }
        } 
    }
}
