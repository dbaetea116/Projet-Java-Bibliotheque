# Projet Java – Bibliothèque
## Pré-requis

- `JDK` récent (Java 21+ recommandé; le `pom.xml` cible Java 25)
- `Maven` installé et accessible dans le terminal
- `MySQL` en local avec un utilisateur ayant accès (par défaut `root/root`)
- JavaFX (les dépendances sont gérées par Maven)

## Structure du projet

- `src/main/java/module-info.java` : module `com.example.javaproject` (JavaFX, JDBC, BCrypt, MySQL)
- `untitled/src/` : code de l’app (MVC)
  - `view/` : FXML (`login.fxml`, `dashboard.fxml`, `livres.fxml`, `membres.fxml`, `emprunts.fxml`)
  - `controller/` : contrôleurs JavaFX
  - `service/` : logique métier
  - `dao/` : accès aux données (JDBC)
  - `model/` : entités (POJOs)
  - `utils/Connexion.java` : connexion MySQL (URL, user, mot de passe)
- `bibliotheque (1).sql` : schéma et données de la base
- `pom.xml` : dépendances (JavaFX 21, MySQL Connector 8.0.33, JUnit 5, BCrypt) et plugin JavaFX

## Base de données

Par défaut, la connexion JDBC est configurée sur `jdbc:mysql://localhost:8889/bibliotheque` avec `root/root`.
Modifiez `untitled/src/utils/Connexion.java` si votre MySQL utilise un autre port (ex: 3306) ou d’autres identifiants.


## Lancement de l’application

Le plugin JavaFX est configuré avec `mainClass` sur `com.example.javaproject/com.example.javaproject.HelloApplication`.
Si cette classe n’existe pas dans votre workspace, créez une classe `Application` qui charge votre FXML (ex: `login.fxml`) ou mettez à jour `pom.xml` pour pointer vers votre classe principale réelle.
