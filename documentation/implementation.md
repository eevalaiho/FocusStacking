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

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/domain/FFT.html

### SlidingWindow

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/domain/SlidingWindow.html

## IO

### MyImage

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/io/MyImage.html

### MyImageIO

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/io/MyImageIO.html


### RGB

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

MyArrayList -luokka on oma toteutus ArrayList -tietorakenteesta. Luokka sisältää ainoastaan keskeiset ja projektissa tarvitut ArrayList -tietorakenteen metodit ja esimerkiksi Java:n ArrayList -luokan clear() -metodia ei ole toteutettu. 

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/util/MyArrayList.html

### Util

Util-luokkaan on toteutettu staattisia apumetodeja liukulukumatriisin normalisointiin välille \[0, 1] (```normalize```) ja minimi- ja maksimiarvon laskentaan (```normalize```) sekä kompleksilukumatriisin L2-normin laskentaan (```l2Norm```). 

#### Javadoc

https://eevalaiho.github.io/FocusStacking/javadoc/util/Util.html
