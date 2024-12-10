# Exercie 3

## Règle XPath pour détecter les instructions `if` imbriquées

Cette règle XPath est utilisée pour détecter un code complexe avec trop d'instructions `if` imbriquées. La règle déclenche un avertissement lorsqu'il y a trois instructions `if` imbriquées ou plus dans un bloc de code.

### Définition de la règle

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
