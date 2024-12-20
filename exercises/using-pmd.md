# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset (see the [pmd install instruction](./pmd-help.md)). Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false positive). Explain why you would not solve this issue.

## Answer

Nous avons exécuter l'analyse PMD sur le projet [Apache Commons Collections](https://github.com/apache/commons-collections) en utilisant la commande suivante :

```bash
pmd.bat check -d commons-collections\src\main\java -f text -R rulesets/java/quickstart.xml -r results_pmd_commons-collections.txt
```
Le fichier de résultats [results_pmd_commons-collections.txt](../code/Exercise2/results_pmd_commons-collections.md) contient une série d'avertissements identifiés par PMD pendant l'analyse du code source. 

### Exemple 1 : Faux Positif
Avertissement : CompareObjectsWithEquals
Fichier : commons-collections\src\main\java\org\apache\commons\collections4\ListUtils.java:322
Message : Use equals() to compare object references.

***Code d'origine :***

```java
public static boolean isEqualList(final Collection<?> list1, final Collection<?> list2) {
    if (list1 == list2) {
        return true;
    }
    if (list1 == null || list2 == null || list1.size() != list2.size()) {
        return false;
    }

    final Iterator<?> it1 = list1.iterator();
    final Iterator<?> it2 = list2.iterator();

    while (it1.hasNext() && it2.hasNext()) {
        final Object obj1 = it1.next();
        final Object obj2 = it2.next();

        if (!Objects.equals(obj1, obj2)) {
            return false;
        }
    }

    return !(it1.hasNext() || it2.hasNext());
}
```

Le message d'avertissement suggère d'utiliser `equals()` au lieu de `==` pour comparer des objets, mais dans ce cas, l'utilisation de == est correcte. Le test if (list1 == list2) vérifie si les deux collections pointent vers le même objet en mémoire, ce qui est une vérification valide. Pour comparer les éléments à l'intérieur des collections, l'utilisation de Objects.equals() est effectivement correcte, car elle gère les valeurs nulles et les comparaisons d'objets via la méthode equals(). Ce faux positif signale une modification qui n'est pas nécessaire ici.

### Exemple 2 : Vrai Positif

Avertissement : UnnecessaryFullyQualifiedName
Fichier : commons-collections\src\main\java\org\apache\commons\collections4\IterableUtils.java:627
Message : Unnecessary qualifier 'IterableUtils': 'isEmpty' is already in scope

***Code d'origine :***

```java
public static <E> Iterable<E> loopingIterable(final Iterable<E> iterable) {
    Objects.requireNonNull(iterable, "iterable");
    return new FluentIterable<E>() {
        @Override
        public Iterator<E> iterator() {
            return new LazyIteratorChain<E>() {
                @Override
                protected Iterator<? extends E> nextIterator(final int count) {
                    if (IterableUtils.isEmpty(iterable)) {
                        return null;
                    }
                    return iterable.iterator();
                }
            };
        }
    };
}
```

Le message d'avertissement **UnnecessaryFullyQualifiedName** indique que l'utilisation du nom complètement qualifié IterableUtils.isEmpty() est superflue. En effet, si la méthode isEmpty est déjà dans la portée (par exemple, importée statiquement), il est inutile de précéder son appel de IterableUtils.

***Correction :***

Remplacer :

```java
IterableUtils.isEmpty(iterable)
```
Par :
```java
isEmpty(iterable)
```
Cela rend le code plus lisible et plus concis sans changer son comportement.

### Conclusion
L'utilisation de PMD permet d'identifier des problèmes potentiels dans le code, mais il est important de différencier les vrais positifs des faux positifs. Les vrais positifs nécessitent une correction du code, tandis que les faux positifs signalent souvent des pratiques qui ne sont pas nécessairement erronées. L'analyse approfondie de chaque avertissement permet d'identifier ce qui doit réellement être modifié et ce qui peut être ignoré.

