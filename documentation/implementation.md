# Toteutus

## Oletuspaketti

Oletuspakettiin sisältyy kaksi luokkaa: pääohjelmaluokka <a href="#Main">```Main```</a> ja ohjelman varsinaisen toiminnallisuuden toteuttava <a href="#FocusStacking">```FocusStacking```</a> -luokka. 

<a name="Main"></a>
### Main.class

Main-luokkaan on toteutettu ohjemakutsuun vastaava oletusmetodi ```main``` ja sen lisäksi metodeja parametrien käsittelyyn (```parseArguments``` ja ```argumentsToString```) sekä ohjelman varsinaisen toiminnallisuuden käynnistävä ```makeImageStack``` -metodi. 

Pääohjelman ```main``` kulku on kuvattu seuraavassa kaaviossa:

<a name="paaohjelma"></a>
<img src="./diagrams/main.png" alt="Main program" width="250px"/>

#### Ulkopuoliset kirjastot

Luokkaan on tuotu ulkopuolisia kirjastoja ```java.io.IOException``` ohjelman virheiden käsittelyä ja hallintaa varten  sekä ```java.time.LocalDateTime``` uloskirjoitettavan tiedoston uniikkia nimeämistä varten.

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/Main.html

<a name="FocusStacking"></a>
### FocusStacking.class

FocusStacking-luokkaan on toteutettu metodeja kuvatiedostojen lataamiseen ja tallentamiseen (```loadImages```, ```saveImages```), kuvien tarkimpien pikselien laskemiseen (```computeSharpestPixels```) ja tarkimpien pikselien matriisin tulostamiseen ohjelman toiminnallisuuden testaamista ja todentamista varten (```printSharpestPixelIndexes```).

Metodi ```computeSharpestPixels``` sisältää harjoitustyön ydintoiminnallisuuden eli kuvien tarkimpien pikselien laskemisen ja valinnan. Metodin kulku on kuvattu seuraavassa kaaviossa:
<a name="tarkimman"></a>
<img src="./diagrams/sharpest.png" alt="Compute sharpest pixels" width="500px"/>

#### Ulkopuoliset kirjastot

Luokkaan on tuotu projektin ulkopuolisia kirjastoja ```java.io.IOException``` ohjelman virheiden käsittelyä ja hallintaa varten.

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/FocusStacking.html

## Domain

### FFT

FFT-luokkaan on toteutettu metodit yksi- ```fft``` ja kaksiulotteisen ```fft2``` Fourier-muunnoksen laskemiseen. Yksiulotteinen Fourier-muunnos lasketaan Cooley-Tukey algoritmilla ja toteutuksen referenssinä on käytetty Robert Sedgewick ja Kevin Wayne:n toteutusta: https://introcs.cs.princeton.edu/java/97data/FFT.java.html. Kaksiulotteinen Fourier-muunnos lasketaan siten, että ensin lasketaan yksiulotteinen Fourier-muunnos riveittäin ja sen jälkeen tuloksena saadulle kompleksilukumatriisille yksiulotteinen Fourier-muunnos sarakkeittain.

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/domain/FFT.html

### SlidingWindow

SlidindWindow-luokkaan on toteutettu FocusStacking -algoritmin käyttämä ikkunatoiminnallisuus. Luokan instanssi instantioidaan antamalla ikkunan koko ja kuvapistematriisin leveys ja korkeus. Ikkunaa voi siirtää eteenpäin ```hasNext``` ja ```moveNext``` -metodien avulla (ainoastaan eteenpäin siirtyminen on tarpeellista). Fourier-muunnoksessa tarvittava kahteen suuntaan peilattu ikkuna saadaan metodilla ```getDoublyMirroredWindow```. 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/domain/SlidingWindow.html

## IO

### MyImage

MyImage -luokka on yksittäisen kuvan representaatio. Luokkaa käytetään IO-kirjaston sisäisesti MyImageIO -luokasta.

#### Ulkopuoliset kirjastot

Luokkaan on tuotu projektin ulkopuolisia kirjastoja ```javax.imageio.ImageIO```, ```java.awt.image.BufferedImage``` ja ```java.io.File``` kuvien lataamista, käsittelyä ja tallentamista varten sekä ```java.io.IOException``` ohjelman virheiden käsittelyä ja hallintaa varten.

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/io/MyImage.html

### MyImageIO

MyImageIO -luokan tehtävänä on tarjota FocusStacking-luokan tarvitsemat metodit kuvien lataamiseen, esikäsittelyyn, niiden tietojen kyselyyn ja tarkennetun kuvan tallentamiseen. 

#### Ulkopuoliset kirjastot

Luokkaan on tuotu projektin ulkopuolisia kirjastoja ```javax.imageio.ImageIO```, ```java.awt.image.BufferedImage``` ja ```java.io.File``` kuvien lataamista, käsittelyä ja tallentamista varten sekä ```java.io.IOException``` ohjelman virheiden käsittelyä ja hallintaa varten.

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/io/MyImageIO.html

### RGB

RGB-enumeraatio on tietorakenne RGB-kuvan värikanavien käsittelyä varten. Enumeraatioon on toteutettu staattiset metodit värikanavan arvon irrottamiseen RGB:n integer-esityksestä (```getChannelValue```) ja värin merkkijonoesityksen parserointiin (```parse```). 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/io/RGB.html


## Util

### Complex

Complex -luokka on oma toteutus Complex -tietorakenteesta. Luokka sisältää ainoastaan keskeiset ja projektissa tarvitut Complex -tietorakenteen metodit. Toteutuksessa on käytetty referenssinä Robert Sedgewick:n ja Kevin Wayne:n toteutusta https://introcs.cs.princeton.edu/java/32class/Complex.java.html. 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/util/Complex.html

### Math

Math-luokkaan on toteutettu apumetodeja matemaattisten funktioiden laskemiseen: neliöjuuri (```sqrt```), itseisarvo  (```abs```) ja hypotenuusa (```hypot```). 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/util/Math.html

### MyArrayList

MyArrayList-luokka on oma toteutus ArrayList -tietorakenteesta. Luokka sisältää ainoastaan keskeiset ja projektissa tarvitut ArrayList -tietorakenteen metodit ja esimerkiksi Java:n ArrayList -luokan clear() -metodia ei ole toteutettu. 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/util/MyArrayList.html

### Util

Util-luokkaan on toteutettu staattisia apumetodeja liukulukumatriisin normalisointiin välille \[0, 1] (```normalize```) ja minimi- ja maksimiarvon laskentaan (```normalize```) sekä kompleksilukumatriisin L2-normin laskentaan (```l2Norm```). 

#### Javadoc
https://eevalaiho.github.io/FocusStacking/javadoc/util/Util.html
