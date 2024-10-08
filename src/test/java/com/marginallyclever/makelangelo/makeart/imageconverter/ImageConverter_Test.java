package com.marginallyclever.makelangelo.makeart.imageconverter;

import com.jogamp.opengl.GL2;
import com.marginallyclever.makelangelo.makeart.TransformedImage;
import com.marginallyclever.makelangelo.paper.Paper;
import com.marginallyclever.makelangelo.turtle.Turtle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter_Test {
    private ImplTestImgConv imageConverter; // Remplace par la sous-classe concrète
    private File inputImage;
    private File outputImage;
    private Paper paper;
    private TransformedImage img;
    private GL2 gl2;


    @Before
    public void setUp() {
        imageConverter = new ImplTestImgConv();
        //JSP quelle image prendre
        //inputImage = new File("src/test/resources/test.png");
        outputImage = new File("src/test/resources/output.gcode");
        paper = new Paper(); // Assure-toi que le constructeur est correct, ou ajuste avec des paramètres si nécessaire
        //img = new TransformedImage(inputImage);
        // Charger le fichier image en tant que BufferedImage
        try {
            BufferedImage bufferedImage = ImageIO.read(inputImage);
            img = new TransformedImage(bufferedImage);  // Utilise BufferedImage pour créer TransformedImage
        } catch (IOException e) {
            e.printStackTrace();
            fail("Impossible de lire l'image d'entrée : " + e.getMessage());
        }

        // Mock GL2 pour le test
        gl2 = mock(GL2.class);
    }


    @Test
    public void testStartAndStopConversion() {
        try {
            // Démarre le processus de conversion
            imageConverter.start(paper, img);

            // Arrête le processus de conversion
            imageConverter.stop();

            // Teste si on a output après l'arrêt
            assertTrue("La conversion doit être active après le démarrage", outputImage.exists());

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @Test
    public void testRender() {
        try {
            // Appelle la méthode render pour vérifier le traitement de l'image
            imageConverter.render(gl2);

            // Selon l'implémentation de render(), tu peux ajouter des assertions.
            // Par exemple, vérifie si le fichier de sortie est généré après le rendu.
            assertTrue("Le fichier de sortie doit exister après le rendu", outputImage.exists());

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @Test
    public void testConvertAlongLine() {
        try {
            // On choisit des valeurs :
            double x1 = 0, y1 = 0, x2 = 100, y2 = 100;
            double stepSize = 1.0;        // Taille de pas pour la conversion
            double channelCutoff = 0.5;   // Seuil pour les canaux
            TransformedImage image = img; // Utiliser l'image déjà initialisée
            // Appelle convertAlongLine et passe les paramètres nécessaires (ex : coordonnées)
            imageConverter.convertAlongLine(x1, y1, x2, y2, stepSize, channelCutoff, image); // Exemple avec des valeurs

            // Ici, vérifie l'état ou le contenu si convertAlongLine modifie le fichier de sortie
            assertTrue("Le fichier de sortie doit être modifié par convertAlongLine", outputImage.exists());

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @Test
    public void testConversionEventHandling() {
        try {
            // Teste l'ajout et le retrait d'un listener d'événement
            ImageConverterListener listener = new ImageConverterListener() {
                @Override
                public void onRestart(ImageConverter panel) {

                }

                @Override
                public void onConvertFinished(Turtle turtle) {

                }
            };

            // Ajoute un listener
            imageConverter.addImageConverterListener(listener);

            // Vérifie si l'ajout a fonctionné
            // Selon l'implémentation, tu pourrais vérifier si listener est dans la liste des listeners

            // Retire le listener
            imageConverter.removeImageConverterListener(listener);

            // Selon l'implémentation, tu pourrais vérifier si le listener a bien été retiré

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Nettoyage après chaque test
        if (outputImage.exists()) {
            outputImage.delete();
        }
    }
}
