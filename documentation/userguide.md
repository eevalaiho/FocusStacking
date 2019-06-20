# Käyttöohje

## Ohjelman suorittaminen

Ohjelma suoritetaan projektin alihakemistossa ```application```.
```
$ cd [download-location]/FocusStacking/application
```
Ohjelma käynnistetään komennolla:
```
java -jar FocusStacking.jar --fileNames 30x20-kaunokki-left-blur.png 30x20-kaunokki-right-blur.png 30x20-kaunokki-top-blur.png
```
Ohjelma olettaa, että ```fileNames``` -parametrillä välitetyt tiedostot löytyvät alihakemistosta ```application/src/main/resources```. 

### Debug-tila

Debug-tila on hyödyllinen ohjelman toiminnan todentamisessa. Se asetetaan päälle ```--debug``` tai ```-d``` -parametreilla:
```
java -jar FocusStacking.jar --debug
```
Debug-tilassa ohjelma tulostaa konsoliin enemmän tietoa: ohjelman suorituksessa käytetyt parametrit ja tarkimpien pikselien valinnassa käytetyn matriisin.

### Ikkunan koko

Fourier-muunnoksessa käytetyn ikkunan koko voidaan asettaa parametrilla ```--windowSizes``` tai ```-w```. Lukuarvon tulee olla kahden potenssi. 
```
java -jar FocusStacking.jar --windowSizes 16
```
Ikkunakokoja voi määritellä useita, jolloin tarkan kuvan laskeminen suoritetaan jokaisella annetulla kuvakoolla erikseen.
```
java -jar FocusStacking.jar --windowSizes 16 32
```

Ikkunan koko vaikuttaa ohjelman suoritusaikaan polynomisesti, joten ohjelman suoritus suuremmilla ikkunakooilla (64, 128, ...) on hidasta.  

Jos ikkunan kokoa ei ole annettu, käytetään oletusarvoa 16.

### Värikanava

Fourier-muunnoksessa käytettävä värikanava voidaan asettaa parametrilla ```--channels``` tai ```-c```. Arvon tulee olla merkkijono "RED", "GREEN" tai "BLUE". 
```
java -jar FocusStacking.jar --channels RED
```
Värikanavia voi määritellä useita, jolloin tarkan kuvan laskeminen suoritetaan jokaisella annetulla värikanavalla erikseen.
```
java -jar FocusStacking.jar --channels RED BLUE
```
Jos värikanavaa kokoa ei ole annettu, käytetään oletusarvoa BLUE.    


## JavaDoc

Ohjelman JavaDoc -dokumentaatio [https://eevalaiho.github.io/FocusStacking/javadoc/](https://eevalaiho.github.io/FocusStacking/javadoc/)



