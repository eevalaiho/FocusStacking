# Käyttöohje

## Ohjelman suorittaminen

Ohjelma suoritetaan projektin alihakemistossa ```application```.
```
$ cd [download-location]/FocusStacking/application
```
Ohjelma käynnistetään komennolla:
```
java -jar build/libs/FocusStacking.jar --fileNames 30x20-kaunokki-left-blur.png 30x20-kaunokki-right-blur.png 30x20-kaunokki-top-blur.png
```
Ohjelma olettaa, että ```fileNames``` -parametrillä välitetyt tiedostot löytyvät alihakemistosta ```application/src/main/resources```. 

### Debug-tila

Debug-tila on hyödyllinen ohjelman toiminnan todentamisessa. Se asetetaan päälle ```--debug``` tai ```-d``` -parametreilla:
```
java -jar build/libs/FocusStacking.jar --debug
```


Ikkunan koko 

*             <li>-d, --debug                    to set debug mode on<br /> In debug mode the id's of the sharpest images in each pixel position will be printed</li>
     *             <li>-w, --windowSize               to set window size for fourier transform<br />Window size must be a power of two. Multiple sizes can be provided. A separate output image will be created for each window size. Example -w 8 16 32</li>
     *             <li>-c, --channels                 to st color channel(s) to use for figuring out the sharpest pixels<br />Possible values RED, GREEN, BLUE. A separate output image will be created for each window size. Example -c BLUE</li>
     *             <li>-f, --fileNames                to set names of the files to use<br />The files should reside in application/src/main/resources folder. Example -f pic1.png pic2.png pic3.png</li>
     *             <li>-o, --outputFileNameFormat     to set the formatting string of the output file<br />If none is provided a default formatting string is used. Should contain format instruction %s for channel name and %d for window size. Example output_%s_%d.png</li>
     


## JavaDoc

Ohjelman JavaDoc -dokumentaatio [https://eevalaiho.github.io/FocusStacking/javadoc/](https://eevalaiho.github.io/FocusStacking/javadoc/)



