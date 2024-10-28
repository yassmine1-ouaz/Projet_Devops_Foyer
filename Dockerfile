
# Utilisez une image de base Java
FROM openjdk:17-jdk-slim

# Exposer le port sur lequel l'application écoutera
EXPOSE 8089

# Ajouter le fichier JAR construit à partir de votre projet
ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar

# Spécifier le point d'entrée de l'application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
