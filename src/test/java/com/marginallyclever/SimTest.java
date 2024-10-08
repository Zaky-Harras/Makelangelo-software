package com.marginallyclever;

import com.github.javafaker.Faker;
import com.marginallyclever.makelangelo.makeart.TransformedImage;
import com.marginallyclever.makelangelo.makeart.imageconverter.Converter_Boxxy;
import com.marginallyclever.makelangelo.paper.Paper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Converter_Boxxy.class })
public class SimTest {
    private Converter_Boxxy mockClass;
    private Faker faker;

    @Before
    public void setUp() {
        // Utilisation de PowerMockito pour moquer la classe
        mockClass = PowerMockito.mock(Converter_Boxxy.class);
        faker = new Faker();
    }

    @Test
    public void testSimulation() {
        Paper paper = mock(Paper.class);
        BufferedImage testImage = new BufferedImage(faker.number().numberBetween(100, 500), faker.number().numberBetween(100, 500), BufferedImage.TYPE_INT_ARGB);
        TransformedImage transformedImage = new TransformedImage(testImage);
        // Configuration de la simulation avec PowerMockito
        PowerMockito.doNothing().when(mockClass).start(paper, transformedImage);

        // Votre logique de test ici
        //String result = mockClass.start(paper, transformedImage);
        //assertEquals("Simulated Value", result);
    }
}
