# Zaky-Laurent Rapport

## Informations
Nous avons choisi le projet Makelangelo. \
Malgré la structure complexe, voici les répertoires importants :
- [src/main/java/com/marginallyclever](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever) : \
    Là où se trouve le code principal du logiciel
- [src/test/java/com/marginallyclever](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever) : \
    Là où se trouvent les tests 
- target/site/jacoco : \
    Là où on retrouve le rapport Jacoco principal
- [Makelangelo.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2FMakelangelo.java) : \
    main classe

Nous utilisons Jacoco pour évaluer la couverture des tests. \
Nous pouvons former un rapport et voir quelles classes ne sont pas couvertes.
*> mvn clean install* = fonction pour rebuild le projet

## Étapes pour 1er test : classe 
1. On veut trouver les classes non couvertes par des tests. \
   On remarque que jacoco est déjà mentionné dans le maven. \
   On va utiliser ça pour couvrir les parties du code qui ne sont pas couverts par des tests.
   1. Installation mvn sur machine pour pouvoir exécuter jacoco
   2. Exécution de jacoco : *> mvn clean test jacoco:report -e -X*
   3. Rapport **index.html** créé dans "target/site/jacoco/index.html"
   4. Ouverture du rapport sur browser
   5. Analyse rapport :
      - Missed Instructions : Indique le nombre d’instructions (lignes de code) non couvertes par les tests.

      - Coverage (%) : Montre le pourcentage de couverture des instructions. \
              Plus le pourcentage est élevé, mieux c'est.

      - Missed Branches : Représente les branches de conditions non couvertes par les tests (if, else, etc.).

      - Missed Lines, Methods, and Classes : Montre combien de lignes, méthodes, ou classes spécifiques ne sont pas couvertes par les tests.
    
      - Couleurs : Dans le rapport HTML, les lignes de code couvertes apparaissent généralement en vert, tandis que celles non couvertes apparaissent en rouge.
   
   6. Résultats exemple com.marginallyclever.communications:
      On peut apercevoir 3 classes :
      - **NetworkSession** : Cette classe a une couverture très faible (0%), ce qui signifie que les tests actuels n'exécutent aucune des instructions de cette classe.
      Elle pourrait nécessiter des tests supplémentaires pour vérifier son comportement.
      - **Configuration** : Cette classe est également à 0% de couverture, indiquant que ses méthodes ne sont pas testées.
      - **NetworkSessionEvent** : Cette classe est bien couverte avec 100% de couverture, ce qui signifie que les tests couvrent toutes ses instructions.

2. Faire des classes de tests \
***RAPPEL : en essayant de faire le test pour NetworkSession, j'ai implémenté Mockito dans pom.xml à la ligne 548***
   1. Par exemple, test pour **NetworkSession**: créer classe **NetworkSession_Test** dans *[src/test/java/com/marginallyclever/communications](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fcommunications)*
   2. Création de la classe **ImplTestNetSesh** implémentant les fonctions de **NetworkSession** pour que nous puissions les tester.
   3. Exécution de **NetworkSession_Test** uniquement : *> mvn -Dtest=NetworkSession_Test test*
   4. On remarque que rien n'est testé pour **NetworkSession** dans le rapport initial :

| Element                  | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|--------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|--------|---------|
| **NetworkSession**       | 64 of 64            | 0%   | 2 of 2          | 0%   | 9      | 9    | 18     | 18    | 8      | 8       | 1      | 1       |
| **Configuration**        | 16 of 33            | 51%  | n/a             | n/a  | 4      | 6    | 5      | 11    | 4      | 6       | 0      | 1       |
| **NetworkSessionEvent**  | 0 of 10             | 100% | n/a             | n/a  | 0      | 1    | 0      | 4     | 0      | 1       | 0      | 1       |
| **Total**                | 80 of 107           | 25%  | 2 of 2          | 0%   | 13     | 16   | 23     | 33    | 12     | 15      | 1      | 3       |

Il y a 64 instructions et aucune n'est testée. \
Après l'implémentation des fichiers **NetworkSession_Test.java** et **ImplTestNetSesh.java** pour tester **NetworkSession.java**, \
nous obtenons le tableau suivant :

| Element                  | Missed Instructions | Cov. | Missed Branches | Cov.  | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|--------------------------|---------------------|------|-----------------|-------|--------|------|--------|-------|--------|---------|--------|---------|
| **NetworkSession**       | 9 of 64             | 85%  | 0 of 2          | 100%  | 1      | 9    | 2      | 18    | 1      | 8       | 0      | 1       |
| **Configuration**        | 16 of 33            | 51%  | n/a             | n/a   | 4      | 6    | 5      | 11    | 4      | 6       | 0      | 1       |
| **NetworkSessionEvent**  | 0 of 10             | 100% | n/a             | n/a   | 0      | 1    | 0      | 4     | 0      | 1       | 0      | 1       |
| **Total**                | 25 of 107           | 76%  | 0 of 2          | 100%  | 5      | 16   | 7      | 33    | 5      | 15      | 0      | 3       |

On remarque que nous sommes passé de 80 instructions non couvertes à 25.
Il nous en manque toujours 9 dans NetworkSession, reste à voir ce qu'on a manqué.


# Vrais tests
## [imageconverter](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter)
### *FONCTIONNE PAS ...*  
Dans le rapport **target/site/jacoco/index.html**, on va s'attaquer à **com.marginallyclever/makelangelo/makeart/imageconverter** \
C'est la section du code la moins couverte avec 0% de coverage sur 12 054 instructions. \
On va utiliser _Java Facker_, alors on l'ajoute à **pom.xml** ligne 554.
1. On sélectionne la classe étant le moins testé.
   Dans ce cas, c'est la classe **Converter_Flowfield** avec 1 083 instructions non couvertes.
2. Dans le répertoire [imageconverter](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter) \
   On crée la classe **Converter_FlowField_Test**. \
   **Converter_FlowField** extend la classe abstraite **ImageConverter** \
   On doit ajouter la methode *"public Paper getPaper() { return myPaper; }"* à **ImageConverter** pour accéder à **myPaper** \
   On fait la même chose pour *"getImage()..."* 
3. On régle problèmes de Mockito : 
      - création du répertoire [Mockito-extensions](..%2Fsrc%2Ftest%2Fresources%2FMockito-extensions)
      - création du fichier [org.mockito.plugins.MockMacker](..%2Fsrc%2Ftest%2Fresources%2FMockito-extensions%2Forg.mockito.plugins.MockMacker)
      - On devrait mtn pouvoir mocker certaines classes finales comme statique
      - Erreur dans **[PaperSettingsPanelTest.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fpaper%2FPaperSettingsPanelTest.java)** alors on change qlq petits trucs \
   Fonctionne tjrs pas, 2 choix : 
        1. PowerMock
           - ajout dependency : **pom.xml** ligne 559
        2. Implémentation réelle
           - modification de **Converter_FlowField_Test**  

Fonctionne tjrs pas : **ABANDON** \
DELETE **Converter_FlowField_Test**

## [TransformedImage.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2FTransformedImage.java)
- Créations fichier **[TransformedImage_Test.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2FTransformedImage_Test.java)**
- Ajout method **getTranslateX()** et **getTranslateY()** dans **TransformedImage** ligne 229
- problème : *PaperSettingsPanelTest.testLandscapeToPortrait:64*
- On change les 2 1er asserts qu'on avait changé auparavant
- On rebuild et on fait le rapport : *"> mvn clean install"* et *"> mvn clean test jacoco:report -e -X"*
- On check les changements dans le rapport Jacoco : [index.html](..%2Ftarget%2Fsite%2Fjacoco%2Findex.html)
- On remarque qu'on a juste couvert 17% de **TransformedImage**.
- On rechange les 2 1er asserts de *PaperSettingsPanelTest.testLandscapeToPortrait*
- Build success !!!
- Jacoco nous dit qu'on a toujours pas assez couvert, alors on continue.
- **A FAIRE : INITIER TOUS LES PARAMETRES ET CALCULER NOUS MEME LES RES POUR LES COMPARER AUX FONCTIONS DANS LES ASSERTS**
- comme dans : testGetTransformedXandY()
- Pour le moment, on couvre 70% de la fonction. Faut passer à autre chose.

## Tentative de sim du projet global
- Création de la classe [SimTest.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2FSimTest.java) pour pouvoir faire des tests plsu flexibles.
- ...... **EPIC FAIL LOL, jme suis totalement mélangé**

## Je reprend depuis le début
Je m'intéresse au répertoire [imageconverter](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter) pcq c'est cool en vrai, jsp
1. Test de conversion de base
   - On va donc tester [ImageConverter.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter%2FImageConverter.java)
   - On crée la classe **ImageConverter_Test**
   - Puis, on crée une classe **ImplTestImgConv**, car **ImageConverter** est abstraite.
   - On ajoute jogl dans **pom.xml** ligne .. turns out que yé djà là.