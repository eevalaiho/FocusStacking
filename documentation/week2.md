
# Viikkoraportti 2 (17.5.2019)

## Mitä olen tehnyt tällä viikolla?

Olen:
* Etsinyt lisää matskua aiheesta
* Toteuttanut ensimmäisen version algoritmista
     * Hyödyntää valmista DoubleFFT_2D kirjastoa ja isoa määrää luuppeja
* Testannut algoritmia lukuisilla eri parametriyhdistelmillä (löytämättä sopivaa)
     * konvoluution koko 4x4, 8x8, 16x16, 32x32, 64x64
     * ylipäästösuodattinen kynnysarvo
     * L2-normin kynnysarvo

Ajankäyttö: 16h

## Miten ohjelma on edistynyt?

Olen toteuttanut ensimmäisen version ohjelmasta. Ohjelmassa kaksi luokkaa: tiedoston luku- ja kirjoitusluokka (MyRGBImage.class) ja itse pääohjelma (Main.class).

## Mitä opin tällä viikolla / tänään?

Java-kielen "hauskuudet" alkavat palautua mieleen. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Algoritmi ei valitse tarkimpia kohtia kuvista. Osa (ei-oletuskuvasta) valikoituvista pikseleistä on tarkkoja, osa epätarkkoja. Tällä hetkellä Fourier-muunnoksen jälkeen ei tehdä fftshift:iä. Fftshift siirtää nollataajuuden komponentit kuvan keskelle. Ajattelen, että menetelmän ymmärryksessä taitaa minulla olla jotain pielessä. Tähän pitää käyttää vähän aikaa. 

## Mitä teen seuraavaksi?

Seuraavaksi yritän hahmottaa algoritmin toiminta paremmin ja korjata toteutusta. 

## Kysymyksiä

* Voinko käyttää projektissa Complex-luokkaa nimiavaruudesta org.apache.commons.math3.complex vai toteutanko luokan itse? [Myös täällä: https://algs4.cs.princeton.edu/...](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Complex.java.html) on valmis Complex.java -toteutus. Tuntuisi jotensakin pöhköltä toteuttaa luokka itse.
* Miten voisin tehokkaammin kuvapistematriisin konvoluutiot Java:lla? Välittämällä koko matriisin, indeksit i, j ja ikkunan koko omalle FFT-metodille?


