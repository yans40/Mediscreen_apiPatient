FROM mysql:8.0

# Définir les variables d'environnement pour la configuration de la base de données
ENV MYSQL_DATABASE=patient\
 MYSQL_ROOT_PASSWORD=N4NTERRE2022!

# Copier le script SQL d'initialisation dans le conteneur
ADD init.sql /docker-entrypoint-initdb.d/