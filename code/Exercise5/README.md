# Cyclomatic Complexity with JavaParser

Ce projet calcule la complexité cyclomatique des méthodes dans un projet Java en utilisant la bibliothèque JavaParser pour analyser le code source. Il génère un rapport au format CSV (et/ou Mackdom) et un histogramme représentant la distribution des valeurs de la complexité cyclomatique (CC).

## Partie 1 : Code
### Structure du Code

Le projet est composé de plusieurs classes qui interagissent pour analyser le code source Java et générer les résultats :

1. **`CyclomaticComplexityCalculator.java`** :
    - Cette classe contient la logique pour calculer la complexité cyclomatique d'une méthode. La complexité cyclomatique est calculée en fonction des structures de contrôle (comme `if`, `for`, `while`, `switch`, etc.) et des opérateurs logiques (`&&`, `||`).

2. **`HistogramGenerator.java`** :
    - Cette classe est responsable de la génération et de la sauvegarde des histogrammes des valeurs de complexité cyclomatique. Elle utilise la bibliothèque **XChart** pour créer un histogramme et le sauvegarder au format PNG.

3. **`PublicElementsPrinter.java`** :
    - Cette classe parcourt le code source et extrait les méthodes publiques de chaque classe. Elle collecte les informations nécessaires pour le calcul de la complexité cyclomatique et génère les rapports au format CSV.

4. **`MethodMetrics.java`** :
    - Cette classe représente une méthode dans le code source. Elle contient les informations suivantes : nom du package, nom de la classe, nom de la méthode, types des paramètres et complexité cyclomatique.

5. **`Main.java`** :
    - C'est le point d'entrée du programme. Il permet de spécifier un répertoire contenant des fichiers Java, d'exécuter l'analyse du code source, et de générer les rapports et histogrammes associés.

## Partie 2 : Comparaison des Histogrammes de Trois Projets Différents

Les histogrammes ci-dessous représentent la distribution des valeurs de complexité cyclomatique pour chaque projet.

### Histogrammes

#### 1. Apache Commons Collections
![Histogramme Apache Commons Collections](code/Exercise5/results/projet1/histogram_Apache Commons Collections.png)

**Analyse :**
- La majorité des méthodes ont une complexité cyclomatique de 1, ce qui indique un code simple et linéaire pour la plupart des cas.
- Quelques méthodes ont des complexités légèrement plus élevées, nécessitant potentiellement une refactorisation pour améliorer leur maintenabilité.

#### 2. Apache Commons CLI
![Histogramme Apache Commons CLI](code/Exercise5/results/projet2/histogram_Apache Commons CLI.png)

**Analyse :**
- Comme pour Apache Commons Collections, une grande partie des méthodes ont une complexité cyclomatique de 1.
- Le pic est légèrement moins élevé, ce qui pourrait indiquer une répartition des responsabilités un peu différente dans ce projet.

#### 3. Apache Commons Lang
![Histogramme Apache Commons Lang](code/Exercise5/results/projet3/histogram_Apache Commons Lang.png)

**Analyse :**
- Ce projet présente une distribution similaire, avec un nombre élevé de méthodes ayant une complexité cyclomatique faible.
- Cependant, certaines méthodes affichent des valeurs de complexité nettement plus élevées, dépassant 50. Ces méthodes sont probablement plus complexes et nécessiteraient une analyse approfondie pour déterminer s'il serait pertinent de les refactorer.

### Comparaison Globale

- **Simplicité du Code :** Tous les projets ont une majorité de méthodes avec une complexité cyclomatique de 1, ce qui est une bonne indication de simplicité et de clarté dans le code.
- **Méthodes Complexes :** Apache Commons Lang présente des méthodes avec des valeurs de complexité plus élevées (jusqu'à 50), ce qui est significativement plus que dans les deux autres projets.
- **Recommandations :** Pour tous les projets, les méthodes avec une complexité cyclomatique supérieure à 10 pourraient être examinées pour réduire leur complexité, améliorer leur lisibilité et faciliter leur maintenance.

### Conclusion

Les histogrammes montrent que les trois projets ont une distribution similaire avec des différences notables dans les queues des distributions, particulièrement pour Apache Commons Lang. Ces résultats permettent d'identifier les zones du code qui pourraient bénéficier de refactorisation.
