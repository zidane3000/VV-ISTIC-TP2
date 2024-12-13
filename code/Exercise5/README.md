# Projet Cyclomatic Complexity Calculator

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

//TODO
