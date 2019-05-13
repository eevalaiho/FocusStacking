# Sekalaista

## Java-kirjastoja vertailutoteutukseen

### Fourier -muunnos

DTFDescriptor: The "DFT" operation computes the discrete Fourier transform of an image
https://docs.oracle.com/cd/E17802_01/products/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/DFTDescriptor.html

JTransforms is the first, open source, multithreaded FFT library written in pure Java. 
https://sites.google.com/site/piotrwendykier/software/jtransforms


## Focal stack compositing for depth of field control

Many cameras provide insufficient control over depth of field. Some have a fixed aperture; others have a variable aperture that is either too small or too large to produce the desired amount of blur. To overcome this limitation, one can capture a focal stack, which is a collection of images each focused at a different depth, then combine these slices to form a single composite that exhibits the desired depth of field. 
https://graphics.stanford.edu/papers/focalstack/


## Image stacking (ei sama kuin focus stacking):

Image Stacker student project assignment: http://nifty.stanford.edu/2014/nicholson-image-stacker/

## Fourier transform

## Princeton FFT.java

[FFT.java.html](https://introcs.cs.princeton.edu/java/97data/FFT.java.html): Compute the FFT and inverse FFT of a length n complex sequence using the radix 2 Cooley-Tukey algorithm.

### OpenCV Java tutorials

[Fourier tranform tutorial](https://opencv-java-tutorials.readthedocs.io/en/latest/05-fourier-transform.html): In this tutorial we are going to create a JavaFX application where we can load a picture from our file system and apply to it the DFT and the inverse DFT. 

## Focus stacking ja java

### JAI (Java Advanced Imaging API)

JAI (Java Advanced Imaging API) has classes DFTDescriptor and IDFTDescriptor which seem to match.

* API docs for DFTDescriptor describe it as follows:

    The "DFT" operation computes the discrete Fourier transform of an image.

* for inverse transform, there is IDFTDescriptor:

    The "IDFT" operation computes the inverse discrete Fourier transform of an image.

[Lähde: https://softwareengineering.stackexchange.com/...](https://softwareengineering.stackexchange.com/questions/126085/forward-and-backward-fourier-transform-image-in-java)


## Focus stacking

[Focus stacking algorithm written in Java](http://dkfu9dpc.c4-suncomet.com/petrihirvonen/Projects/Stacker/stacker.php)


