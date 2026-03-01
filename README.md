🏦 Banque – Gestion Clients, Prêts et Remboursements
📌 Description

Cette application Java Swing permet la gestion complète des clients, prêts et remboursements dans un contexte bancaire.
Elle inclut :

Création de compte avec confirmation par email (JavaMail)

Connexion sécurisée (Sign In)

Gestion des clients (CRUD complet : Create, Read, Update, Delete)

Gestion des prêts et remboursements avec suivi de l’encours

Statistiques et graphiques mensuels (optionnel avec Swing Charts)

Architecture propre MVC / DAO / Service

L’application est développée avec Java 24, Maven, JDBC et Swing.



Java 24 ou supérieur

Maven

MySQL (ou tout SGBD compatible JDBC)

Compte Gmail pour l’envoi de mails via JavaMail

Table client, pret, remboursement dans la base de données

UML :


-DIAGRAMME DE CLASSE :


DIAGRAMME DE CAS D'UTILISATION :


Configurer la base de données :

Créez une base bankdb et les tables :

CREATE TABLE client(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    categorie VARCHAR(50),
    ville VARCHAR(50)
);

CREATE TABLE pret(
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    montant DOUBLE,
    taux DOUBLE,
    date_debut DATE,
    FOREIGN KEY(client_id) REFERENCES client(id)
);

CREATE TABLE remboursement(
    id INT AUTO_INCREMENT PRIMARY KEY,
    pret_id INT,
    montant DOUBLE,
    date DATE,
    FOREIGN KEY(pret_id) REFERENCES pret(id)
);

Configurer le fichier DBConnection.java avec vos paramètres MySQL.

Configurer l’email pour JavaMail (dans LoginFrame.java) avec votre email Gmail et mot de passe d’application.

Compiler et exécuter :

mvn clean install
mvn exec:java -Dexec.mainClass="ma.bank.ui.LoginFrame"
🖥 Fonctionnalités principales
Sign Up / Sign In

Création de compte avec email et mot de passe

Envoi d’un email de confirmation

Connexion sécurisée (Sign In)

Gestion des clients (CRUD)

Ajouter un client

Modifier un client

Supprimer un client

Lister tous les clients et filtrer par catégorie

Gestion des prêts

Accorder un prêt à un client

Suivre l’encours d’un client

Lister les prêts non remboursés

Gestion des remboursements

Enregistrer un remboursement

Vérifier si le prêt est totalement remboursé

Calcul du total remboursé par prêt

Graphiques : 

-CATEGORIE DES CLIENTS PAR VILLE : 


<img width="843" height="310" alt="chart" src="https://github.com/user-attachments/assets/74b32d47-fec6-48f8-beff-36a0bfd1ed59" />

Visualisation de l’encours total par mois via Swing Charts

📦 Technologies utilisées

Java 24

Maven

JDBC pour la base de données

Swing pour l’interface graphique

JavaMail pour l’envoi de mails

MySQL comme base de données

🛠 Architecture





Model : Entités (Client, Pret, Remboursement)



DAO : Accès aux données (ClientDAO, PretDAO, RemboursementDAO)



Service : Logique métier et validations (ClientService, PretService, RemboursementService)







UI : Fenêtres Swing (LoginFrame, MainMenu, ClientForm, etc.)




<img width="1024" height="1536" alt="ChatGPT Image 1 mars 2026, 08_38_50" src="https://github.com/user-attachments/assets/123e16ae-72fd-40ca-83c2-c214dd7670d8" />

🔐 Notes de sécurité

Mot de passe utilisé pour Sign Up / Sign In doit être sécurisé et stocké hashé (actuellement en clair pour la démonstration)

L’envoi d’emails utilise mot de passe d’application Gmail pour plus de sécurité

Realise Par :

Ilyass OUBABA


Mohamed EDDINARI


ÉtudiantS FST Marrakech – Projet mini-banque Java

GitHub : 

📄 Licence

Ce projet est sous licence SIR. .
