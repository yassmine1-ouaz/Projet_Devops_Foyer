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
              // withCredentials([string(credentialsId: 'squ_50f79dfb86e289418110f32436eb1b1f28547944', variable: 'SONAR_TOKEN')]) {
              //sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
              // }
               sh 'mvn sonar:sonar -Dsonar.login=squ_50f79dfb86e289418110f32436eb1b1f28547944'
            
            
               // sh 'mvn sonar:sonar'
            }
        }
        
      /*  stage('MVN DEPLOY') {
            steps {
               echo "Deploying to Nexus repository"
               // Déploie en sautant les tests avec -DskipTests
               sh 'mvn deploy -DskipTests'
            }
        } */
    }
}