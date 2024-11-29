# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

A refresher on TCC and LCC is available in the [course notes](https://oscarlvp.github.io/vandv-classes/#cohesion-graph).

## Answer

### Les différentes circonstances où TCC et LCC produisent la même valeur pour une classe Java :

#### Cas 1 : Toutes les connexions entre méthodes sont directes

Si toutes les méthodes partagent des attributs communs et qu'il n'existe aucune connexion indirecte, alors :
- **TCC = LCC**, car toutes les paires de méthodes sont directement connectées.
- Le graphe de cohésion est **complet** (chaque méthode est directement liée aux autres).

##### Exemple inspiré du graphe dans le cours (*Figure8*) :
```java
class Figure8 {
    int x, y;

    public int sub() {
        return x - y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int dot() {
        return x * y;
    }
}
```

**Analyse :**
- Méthodes : `sub()`, `getX()`, `getY()`, `dot()`.
- Toutes les méthodes accèdent soit à `x`, soit à `y` (ou les deux), donc toutes les paires de méthodes sont directement connectées.

---

#### Cas 2 : Aucune connexion entre méthodes

Si aucune méthode ne partage d'attribut avec une autre méthode, alors :
- Il n'existe aucune connexion (ni directe, ni indirecte).
- **TCC = LCC = 0**.

##### Exemple :
```java
class Exemple2 {
    int x, y;
    
    public int getX() {
        return x;
    }

    public void getY() {
        return y;
    }
}
```

**Analyse :**
- Méthodes : `getX()` et `getY()`.
- Aucune méthode ne partage d'attribut.
- Le graphe de cohésion est totalement **disjoint** (aucune arête entre les nœuds).

---

### LCC peut-il être inférieur à TCC ?

Non, **LCC ne peut jamais être inférieur à TCC**, car **LCC inclut toutes les connexions** (directes et indirectes), alors que **TCC ne considère que les connexions directes**.

Donc : 
\(
TCC =< LCC
\)

- Si des connexions indirectes existent, **LCC > TCC**.
- Si aucune connexion indirecte n'existe, **LCC = TCC**.

---
