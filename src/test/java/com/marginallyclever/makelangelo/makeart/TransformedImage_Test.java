package com.marginallyclever.makelangelo.makeart;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransformedImage_Test {
    //On initie les paramètres de test :
    int w = 100;
    int h = 100;
    double d = 0.01;
    //flemme de faire le reste

    @Test
    public void testConstructorInitialization() {
        //On crée une img test et on initie la classe avec
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);

        //On veut que les valeurs d'initialisation passent
        assertNotNull(transformedImage.getSourceImage());
        assertEquals(testImage, transformedImage.getSourceImage());

        // On vérifie les translations et mises à l'échelle
        assertEquals(-50.0, transformedImage.getTranslateX(), 0.01);
        assertEquals(-50.0, transformedImage.getTranslateY(), 0.01);
        assertEquals(1.0, transformedImage.getScaleX(), 0.01);
        assertEquals(-1.0, transformedImage.getScaleY(), 0.01);
    }
    @Test
    public void testGetters() {
        BufferedImage testImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);

        assertEquals(testImage, transformedImage.getSourceImage());
        assertEquals(-25.0, transformedImage.getTranslateX(), 0.01);
        assertEquals(-25.0, transformedImage.getTranslateY(), 0.01);
        assertEquals(1.0, transformedImage.getScaleX(), 0.01);
        assertEquals(-1.0, transformedImage.getScaleY(), 0.01);
    }

    @Test
    public void testSample() {
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        double result = transformedImage.sample(10, 10, 5);
        assertNotNull(result, "Sample doit pas être nul");

        //On essaie de couvrir tous les cas :
        // Échantillonnage au centre de l'image
        double result1 = transformedImage.sample(50, 50, 10, 10);
        assertNotNull(result1, "La valeur de l'échantillonnage ne doit pas être nulle au centre.");

        // Échantillonnage à l'extérieur de l'image
        double result2 = transformedImage.sample(150, 150, 10, 10);
        // Adaptez cette assertion en fonction de la logique de `sample`
        assertEquals(0.0, result2, "La valeur de l'échantillonnage devrait être nulle en déhors.");

        // Échantillonnage avec des valeurs négatives
        double result3 = transformedImage.sample(-10, -10, 5, 5);
        assertEquals(0.0, result3, "La valeur de l'échantillonnage devrait être nulle en déhors.");
    }

    @Test
    public void testCanSampleAt() {
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        assertTrue(transformedImage.canSampleAt(10, 10), "On devrait pouvoir sample dedans");
        assertFalse(transformedImage.canSampleAt(200, 200), "On devrait pas pouvoir sample déhors");
    }

    @Test
    public void testSampleArea() {
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        double result = transformedImage.sampleArea(5, 5, 10, 10);
        assertNotNull(result, "SampleArea devrait pas être nullos");
    }

    @Test
    public void testSetScale() {
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        transformedImage.setScale(2.0f, 3.0f);
        assertEquals(2.0f, transformedImage.getScaleX(), 0.01);
        assertEquals(3.0f, transformedImage.getScaleY(), 0.01);
    }

    @Test
    public void testGetTransformedXandY() {
        BufferedImage testImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        //On décide du scale
        transformedImage.setScale(1.0f, 1.0f);

        //Calculs pour vérifier si les calculs calculent comme il faut.
        //Je fais pu ca pour les autres.
        int x = 10;
        int y = 10;
        double transformedX = transformedImage.getTransformedX(x);
        double transformedY = transformedImage.getTransformedY(y);
        double tX = transformedImage.getTranslateX();
        double sX = transformedImage.getScaleX();
        double resAttenduX = (x / sX) - tX;
        double tY = transformedImage.getTranslateY();
        double sY = transformedImage.getScaleY();
        double resAttenduY = (x / sY) - tY;
        // waaaaaaaaaaaa
        assertEquals(resAttenduX, transformedX, 0.01);
        assertEquals(resAttenduY, transformedY, 0.01);
    }

    @Test
    public void testCopySettingsFrom() {
        BufferedImage image1 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

        TransformedImage transformedImage1 = new TransformedImage(image1);
        TransformedImage transformedImage2 = new TransformedImage(image2);

        transformedImage1.setScale(1.5f, -1.5f);
        transformedImage2.copySettingsFrom(transformedImage1);

        assertEquals(1.5f, transformedImage2.getScaleX(), 0.01);
        assertEquals(-1.5f, transformedImage2.getScaleY(), 0.01);
    }



}
