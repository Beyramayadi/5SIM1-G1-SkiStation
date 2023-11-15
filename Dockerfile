FROM openjdk:17
# Exposez le port sur lequel votre application Java écoute
EXPOSE 8089
# Définissez le répertoire de travail dans le conteneur
ADD target/*.jar ski-station.jar
# Commande d'entrée pour exécuter l'application Java
CMD ["java", "-jar", "/ski-stat.jar"]