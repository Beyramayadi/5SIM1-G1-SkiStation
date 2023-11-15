FROM openjdk:17
RUN mkdir -p /app
WORKDIR /app
# Exposez le port sur lequel votre application Java écoute
# Définissez le répertoire de travail dans le conteneur
COPY target/5SIM1-G1-SkiStation.jar /app/5SIM1-G1-SkiStation.jar
# Commande d'entrée pour exécuter l'application Java
CMD ["java", "-jar", "/app/5SIM1-G1-SkiStation.jar"]