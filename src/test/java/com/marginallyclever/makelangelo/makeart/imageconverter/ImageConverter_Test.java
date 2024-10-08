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
    private ImplTestImgConv imageConverter;
    private File inputImage;
    private File outputImage;
    private Paper paper;
    private TransformedImage img;
    private GL2 gl2;


    @Before
    public void setUp() {
        imageConverter = new ImplTestImgConv();
        //JSP quelle image prendre
        inputImage = new File("src/test/resources/test.png");
        outputImage = new File("src/test/resources/output.gcode");
        paper = new Paper(); // Assure-toi que le constructeur est correct, ou ajuste avec des paramètres si nécessaire
        //img = new TransformedImage(inputImage);
        // Charger le fichier image en tant que BufferedImage sinon ca marche pas
        try {
            BufferedImage bufferedImage = ImageIO.read(inputImage);
            img = new TransformedImage(bufferedImage);
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
            // On débute le processus de conversion
            imageConverter.start(paper, img);

            // On stop le processus de conversion
            imageConverter.stop();

            // On test si on a output après l'arrêt
            assertTrue("La conversion doit être active après le démarrage", outputImage.exists());

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @Test
    public void testRender() {
        try {
            // Appel la méthode render
            imageConverter.render(gl2);

            // On vérifie si sortie est générée
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
            double stepSize = 1.0;
            double channelCutoff = 0.5;
            TransformedImage image = img;
            // Appelle convertAlongLine et passe les paramètres nécessaires
            imageConverter.convertAlongLine(x1, y1, x2, y2, stepSize, channelCutoff, image); // Exemple avec des valeurs

            // On vérifie application de la transformation
            assertTrue("Le fichier de sortie doit être modifié par convertAlongLine", outputImage.exists());

        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @Test
    public void testConversionEventHandling() {
        try {
            // On teste l'ajout et le retrait d'un listener d'événement
            ImageConverterListener listener = new ImageConverterListener() {
                @Override
                public void onRestart(ImageConverter panel) {
                    //Ajouter des asserts

                }

                @Override
                public void onConvertFinished(Turtle turtle) {
                    //Ajouter des asserts

                }
            };

            // Ajoute un listener
            imageConverter.addImageConverterListener(listener);
            //Vérifier avec un assert

            // Retire le listener
            imageConverter.removeImageConverterListener(listener);
            //Vérifier avec un assert


        } catch (Exception e) {
            fail("Une exception ne doit pas être lancée : " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // On doit cleanup après les tests
        if (outputImage.exists()) {
            outputImage.delete();
        }
    }
}
