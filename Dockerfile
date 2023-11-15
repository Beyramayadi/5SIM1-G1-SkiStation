FROM openjdk:17
# Exposez le port sur lequel votre application Java écoute
EXPOSE 8089
# Définissez le répertoire de travail dans le conteneur
ADD target/gestion-statiom-ski-1.0.jar gestion-statiom-ski-1.0.jar
# Commande d'entrée pour exécuter l'application Java
CMD ["java", "-jar", "/gestion-statiom-ski-1.0.jar"]