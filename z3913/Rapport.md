# Zaky-Laurent Rapport

## Informations
Nous avons choisi le projet Makelangelo. \

### Répertoires
Malgré la structure complexe, voici les répertoires importants  
- [src/main/java/com/marginallyclever](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever) : \
    Là où se trouve le code principal du logiciel
- [src/test/java/com/marginallyclever](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever) : \
    Là où se trouvent les tests 
- [target/site/jacoco](..%2Ftarget%2Fsite%2Fjacoco) : \
    Là où on retrouve le rapport Jacoco principal sous le nom de **index.html**
- [Makelangelo.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2FMakelangelo.java) : \
    main classe
- [pom.xml](..%2Fpom.xml) : \
   Fichier de construction maven. 
   Au cours du projet, on y rajoutera des dépendances, notament à partir de la ligne 549.

### Couverture
Nous utilisons Jacoco pour évaluer la couverture des tests. \
Jacoco était déjà inclus dans le build maven, alors nous en avons profité. \
À chaque fois que nous ajoutons une classe test, nous enregistrons nos modifications et 
nous reconstruisons le projet maven avec la ligne de commande *> mvn clean install*. \
Si nous n'avons pas d'erreurs, nous pouvons alors former un rapport et voir quelles classes ne sont pas couvertes. \
Nous pouvons former un rapport avec la ligne de code suivante : *> mvn clean test jacoco:report -e -X* \
Le rapport global se trouve dans le répertoire **[target/site/jacoco](..%2Ftarget%2Fsite%2Fjacoco)** sous le nom de **index.html**. \
Nous ouvrons le rapport dans un navigateur, pour éviter le plus de problèmes potentiels. \
Voici comment lire le rapport :
   - Missed Instructions : Indique le nombre d’instructions (lignes de code) non couvertes par les tests.

   - Coverage (cov) : Montre le pourcentage des instructions qui sont couvertes.

   - Missed Branches : Représente les conditions (if, else, etc) non couvertes.

   - Missed Lines, Methods, and Classes : Montre combien de lignes, méthodes, et classes spécifiques ne sont pas couvertes par les tests.
    
   - Couleurs : 
     * lignes vertes = nombre de parties couvertes 
     * lignes rouges = nombre de parties non couvertes 
     * Les lignes reflètent normalement le % de coverage


## TESTS
### [NetworkSession.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fcommunications%2FNetworkSession.java)
 Nous voulons tester la classe [NetworkSession.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fcommunications%2FNetworkSession.java), car cette classe s'occuper de la gestion des connexions réseau.
1. On crée la classe test pour **NetworkSession** : **NetworkSession_Test** dans *[src/test/java/com/marginallyclever/communications](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fcommunications)*
2. On crée la classe **ImplTestNetSesh** implémentant les fonctions de **NetworkSession** pour que nous puissions les tester.
3. On exécute **NetworkSession_Test** uniquement : *> mvn -Dtest=NetworkSession_Test test*

#### Comparaison des résultats
Voici les résultats lors de l'exécution des tests avant l'implémentation de **NetworkSession_Test** et de **ImplTestNetSesh**

| Element                  | Missed Instructions | Cov. | Missed Branches | Cov. | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|--------------------------|---------------------|------|-----------------|------|--------|------|--------|-------|--------|---------|--------|---------|
| **NetworkSession**       | 64 of 64            | 0%   | 2 of 2          | 0%   | 9      | 9    | 18     | 18    | 8      | 8       | 1      | 1       |
| **Configuration**        | 16 of 33            | 51%  | n/a             | n/a  | 4      | 6    | 5      | 11    | 4      | 6       | 0      | 1       |
| **NetworkSessionEvent**  | 0 of 10             | 100% | n/a             | n/a  | 0      | 1    | 0      | 4     | 0      | 1       | 0      | 1       |
| **Total**                | 80 of 107           | 25%  | 2 of 2          | 0%   | 13     | 16   | 23     | 33    | 12     | 15      | 1      | 3       |

On remarque qu'il y a 64 instructions et aucune n'est testée. \
Après l'implémentation des fichiers **NetworkSession_Test.java** et **ImplTestNetSesh.java** pour tester **NetworkSession.java**, \
nous obtenons le tableau suivant :

| Element                  | Missed Instructions | Cov. | Missed Branches | Cov.  | Missed | Cxty | Missed | Lines | Missed | Methods | Missed | Classes |
|--------------------------|---------------------|------|-----------------|-------|--------|------|--------|-------|--------|---------|--------|---------|
| **NetworkSession**       | 9 of 64             | 85%  | 0 of 2          | 100%  | 1      | 9    | 2      | 18    | 1      | 8       | 0      | 1       |
| **Configuration**        | 16 of 33            | 51%  | n/a             | n/a   | 4      | 6    | 5      | 11    | 4      | 6       | 0      | 1       |
| **NetworkSessionEvent**  | 0 of 10             | 100% | n/a             | n/a   | 0      | 1    | 0      | 4     | 0      | 1       | 0      | 1       |
| **Total**                | 25 of 107           | 76%  | 0 of 2          | 100%  | 5      | 16   | 7      | 33    | 5      | 15      | 0      | 3       |

On remarque que nous sommes passé de 80 instructions non couvertes à 25. \

### [TransformedImage.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2FTransformedImage.java)
Nous voulons tester les transformations applicable sur une image.
1. On crée la classe test pour **TranformedImage** : **[TransformedImage_Test.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2FTransformedImage_Test.java)**
   dans le répertoire *[src/test/java/com/marginallyclever/makelangelo/makeart](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart)*
2. On ajoute des méthodes à **TranformedImage** (**getTranslateX()** et **getTranslateY()**) ligne 229 pour obtenir des données privées.
3. On change les asserts de ***PaperSettingsPanelTest.testLandscapeToPortrait*** pour éviter des erreurs.
Puisque **TransformedImage.java** n'avais pas de tests auparavant, comme **NetworkSession**, alors on sait que 0% était couvert. \
Maintenant, nous pouvons remarquer qu'on couvre 74 % de la classe.


### [ImageConverter.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter%2FImageConverter.java)
On s'intéresse au répertoire [imageconverter](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter) car c'est le moins couvert.
1. On va donc commencer par tester **ImageConverter**
2. On crée la classe **[ImageConverter_Test.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter%2FImageConverter_Test.java)** 
   dans le répertoire *[src/test/java/com/marginallyclever/makelangelo/makeart/imageconverter](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter)*
3. **ImageConverter_Test** est abstraite, alors on crée une classe **[ImplTestImgConv.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimageconverter%2FImplTestImgConv.java)** 
   pour l'implémenter.

### [ImageFilter.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimagefilter%2FImageFilter.java)
Similairement, on s'intéresse à la classe ImageFilter, qui n'a, initialement, aucune couverture.
1. On crée la class **[ImageFilterTest.java](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimagefilter%2FImageFilterTest.java)**
   dans le répertoire *[src/test/java/com/marginallyclever/makelangelo/makeart/imagefilter](..%2Fsrc%2Ftest%2Fjava%2Fcom%2Fmarginallyclever%2Fmakelangelo%2Fmakeart%2Fimagefilter)*
2. On crée une méthode de test pour chaque methode de la class en suivant le pattern AAA.