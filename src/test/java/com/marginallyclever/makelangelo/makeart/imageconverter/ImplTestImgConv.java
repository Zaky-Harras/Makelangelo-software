package com.marginallyclever.makelangelo.makeart.imageconverter;

import com.marginallyclever.makelangelo.makeart.TransformedImage;
import com.marginallyclever.makelangelo.paper.Paper;

import com.jogamp.opengl.GL2;

public class ImplTestImgConv extends ImageConverter {
    //ON SIM TOUTES LES PARTIES DU CODE

    // true = processus roule
    // false = processus arrêté
    private boolean converting = false;

    @Override
    public String getName() {
        return "ImplTestCon";
    }

    @Override
    public void start(Paper paper, TransformedImage img) {
        this.converting = true;
        System.out.println("Conversion started.");
    }

    @Override
    public void stop() {
        // Simule l'arrêt du processus de conversion
        this.converting = false;
        System.out.println("Conversion stopped.");
        //ajout de logique?
    }

    @Override
    public void render(GL2 gl2) {
        System.out.println("Rendering image with OpenGL context.");
        //ajout de logique?
    }

    @Override
    public void convertAlongLine(double x1, double y1, double x2, double y2,
                                 double stepSize, double channelCutoff, TransformedImage img) {
        System.out.println("Converting Along Line from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");

        // Mod pour changer état?
    }

    @Override
    //(double x0,double y0,double x1,double y1,
    // double stepSize,double channelCutoff,
    // double [] error0,double [] error1,TransformedImage img)
    public void convertAlongLineErrorTerms(double x1, double y1, double x2, double y2,
                                           double stepSize, double channelCutoff,
                                           double[] errorTerm1, double[] errorTerm2, TransformedImage img) {
        System.out.println("Converting with error terms along line from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
    }

    @Override
    public void fireRestart() {
        System.out.println("Restart event fired.");
        super.fireRestart();
    }

    @Override
    public void fireConversionFinished() {
        System.out.println("Conversion finished event fired.");
        super.fireConversionFinished();
    }

    // Ajout/Retrait listeners?
}
