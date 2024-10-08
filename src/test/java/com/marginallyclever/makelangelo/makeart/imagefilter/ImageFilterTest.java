package com.marginallyclever.makelangelo.makeart.imagefilter;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class ImageFilterTest {

    //Tests if decode32bit returns the right grayscale with given rgba value
    //Test with an arbitrary color and white/black/transparent as edge cases
    @Test
    public void testDecode32bit(){
        //Arrange
        int randomColor = 0xf0a1b2c3;
        int white = 0xffffffff;
        int black = 0xff000000;
        int randomTransparentColor = 0x00a1b2c3;

        //Act
        int randomGrayScale = ImageFilter.decode32bit(randomColor);
        int whiteGrayScale = ImageFilter.decode32bit(white);
        int blackGrayScale = ImageFilter.decode32bit(black);
        int randomTransparentGrayScale = ImageFilter.decode32bit(randomTransparentColor);

        //Assert
        assert(randomGrayScale == 167);
        assert(whiteGrayScale == 255);
        assert(blackGrayScale == 0);
        assert(randomTransparentGrayScale == 0);
    }

    //Tests if decodeColor returns the right grayscale with given Color object
    //Test with an arbitrary color and white/black/transparent as edge cases
    @Test
    public void testDecodeColor(){
        //Arrange
        Color randomColor = new Color(0xa1, 0xb2, 0xc3, 0xf0);
        Color white = new Color(0xff, 0xff, 0xff, 0xff);
        Color black = new Color(0x00, 0x00, 0x00, 0xff);
        Color randomTransparentColor = new Color(0xa1, 0xb2, 0xc3, 0x00);

        //Act
        int randomGrayScale = ImageFilter.decodeColor(randomColor);
        int whiteGrayScale = ImageFilter.decodeColor(white);
        int blackGrayScale = ImageFilter.decodeColor(black);
        int randomTransparentGrayScale = ImageFilter.decodeColor(randomTransparentColor);

        //Assert
        assert(randomGrayScale == 167);
        assert(whiteGrayScale == 255);
        assert(blackGrayScale == 0);
        assert(randomTransparentGrayScale == 0);
    }

    //Tests if encode32bit returns the right grayscale with given red, green, blue and alpha values
    @Test
    public void testEncode32bitRGBA(){
        //Arrange
        int red = 0xa1;
        int green = 0xb2;
        int blue = 0xc3;
        int alpha = 0xff;

        //Act
        int color = ImageFilter.encode32bit(red, green, blue, alpha);

        //Assert
        assert(color == 0xffa1b2c3);
    }

    //Tests if encode32bit returns the right sade of gray with given grayScale
    //Test for an arbitrary gray scale and opaque/transparent gray edge cases
    @Test
    public void testEncode32bitGrayScale(){
        //Arrange
        int grayScale = 0xaa;
        int transparentGrayScale = 0x00;
        int opaqueGrayScale = 0xff;

        //Act
        int gray = ImageFilter.encode32bit(grayScale);
        int black = ImageFilter.encode32bit(transparentGrayScale);
        int white = ImageFilter.encode32bit(opaqueGrayScale);

        //Assert
        assert (gray == 0xffaaaaaa);
        assert(black == 0xff000000);
        assert(white == 0xffffffff);
    }

}
