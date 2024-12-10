# Exercie 3

## Règle XPath pour détecter les instructions `if` imbriquées

Cette règle XPath est utilisée pour détecter un code complexe avec trop d'instructions `if` imbriquées. La règle déclenche un avertissement lorsqu'il y a trois instructions `if` imbriquées ou plus dans un bloc de code.

### Définition de la règle XPath

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ruleset name="Custom Rules"
         xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0
         http://pmd.sourceforge.net/ruleset_2_0_0.xsd">

    <description>
        Detects complex code with too many nested if statements.
    </description>

    <!-- Rule to detect three or more nested 'if' statements -->
    <rule name="TooManyNestedIfs"
          language="java"
          message="found three or more nested 'if' statements !"
          class="net.sourceforge.pmd.lang.rule.xpath.XPathRule">
        <description>Detects when there are three or more nested if statements.</description>
        <priority>3</priority>

        <properties>
            <property name="xpath">
                <value>
                    <![CDATA[ //IfStatement[count(.//IfStatement) >= 3] ]]>
                </value>
            </property>
        </properties>

    </rule>

</ruleset>
```

 Cette règle signale toute occurrence de trois instructions `if` imbriquées ou plus dans le code source.

## Exécution de la règle XPath pour détecter les instructions `if` imbriquées sur differents projet 

Pour exécuter la règle XPath et détecter les instructions `if` imbriquées, vous pouvez utiliser la commande suivante :

```bash
pmd check -d src/main/java -R rule_nested_if.xml -f text -r ../pmd_report_nested_if_statement.txt

Explication de la commande
pmd check : Cette commande exécute l'analyse PMD sur le code source.
-d src/main/java : Spécifie le répertoire contenant les fichiers Java à analyser.
-R rule_nested_if.xml : Indique le fichier XML contenant la règle personnalisée définissant la détection des instructions if imbriquées.
-f text : Spécifie que le format de sortie sera en texte simple.
-r ../pmd_report_nested_if_statement.txt : Définit le chemin où le rapport des résultats sera enregistré.

###Apache Commons Collections
Résultats dans le fichier [PMD report](code/Exercice3/pmd_report_nested_if_statement_projet1.txt).

###Apache Commons CLI
Résultats dans le fichier [PMD report](code/Exercice3/pmd_report_nested_if_statement_projet1.txt).

###Apache Commons Math
Résultats dans le fichier [PMD report](code/Exercice3/pmd_report_nested_if_statement_projet3.txt)

###Apache Commons Lang
Résultats dans le fichier [PMD report](code/Exercice3/pmd_report_nested_if_statement_projet4.txt)
