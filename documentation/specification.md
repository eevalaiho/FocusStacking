# Määrittely

Focus stacking -algoritmi on digitaalinen kuvankäsittelymenetelmä, minkä avulla useasta samasta kohteesta otetusta valokuvasta voidaan luoda yksi mahdollisimman tarkka kuva. Alkuperäisistä valokuvista erotetaan mahdollisimman tarkat kohdat ja kootaan ne uudeksi kuvaksi.

Algoritmin toiminta on pääpiirteissään seuraava ([Sini Lehtosen gradun mukaan](https://helda.helsinki.fi/bitstream/handle/10138/154047/GraduSini.pdf?sequence=3)):

1. <a href="#esikasittely">Esikäsittely</a>: kuvien lataaminen 

2. <a href="#tarkimpien-kuvapisteiden-valinta">Tarkimpien kuvapisteiden valinta</a>: liu'utetaan jokaisen kuvan tietyn värikanavan (esim vihreä) yli 16x16 (tai 32x32) kuvapisteen ikkunaa ja lasketaan jokaiselle ikkunalle:

    1. <a href="fourier-muunnos">Fourier-muunnos</a>,

    2. <a href="ylipaastosuodatus">ylipäästösuodatus</a>,

    3. <a href="l2-normi">L^2-normi</a>.

3. <a href="uuden-kuvan-luonti">Luodaan uusi kuva</a> kopioimalla siihen kuvapisteet:
    
    1. ensimmäisestä kuvasta, jos minkään kuvan L^2-normi ei ylitä tiettyä raja-arvoa: näin ei-fokusoituneesta taustasta tulee tarkempi
    
    2. muutoin siitä alkuperäisestä kuvista, jonka L^2-normi on suurin eli syväterävyys paras


<a name="esikasittely"></a>
## Esikäsittely

Kuvien lataaminen toteutetaan erilliseen IO-luokkaan, jotta kuvatiedoston tallentamiseen voidan käyttää XX valmiskirjastoa. Kuvien vihreät värikanavat tallennetaan k * w * h -taulukkoon, missä k = kuvien määrä, w = kuvan leveys, h = kuvan korkeus.

### Aikavaativuus

O(1)

### Tilavaativuus

O(n), missä n = w * h = kuvapisteiden määrä


<a name="tarkimpien-kuvapisteiden-valinta"></a>
## Tarkimpien kuvapisteiden valinta

Saa parametrikseen esikäsittelyvaiheessa luodun k * w * h -taulukon, missä k = kuvien määrä, w = kuvan leveys, h = kuvan korkeus. Taulukkoon on tallennettu alkuperäisten kuvien vihreät värikanavat.

Kuvapisteiden valinta toteutetaan kahdella sisäkkäisellä silmukalla, missä käydään läpi jokainen kuvapiste jokaisesta kuvasta. Silmukassa lasketaan ikkunan Fourier-muunnos, ylipäästösuodatus ja L^2 -normi.

Algoritmi ylläpitää w * h -kokoista apumatriisia (2D -taulukko tai harva matriisi), mihin on tallennettu kunkin kuvapisteen tarkin kuva.

### Aikavaativuus

O(n^2) = O(k * n * (Fourier-muunnoksen, ylipäästösuodatuksen ja L^2-normin aikavaativuus)) = O(k * n * 3n)

, missä n = kuvapisteiden määrä, k = alkuperäisten kuvien määrä ja k << n.

### Tilavaativuus

O(n), missä n = kuvapisteiden määrä

### Tietorakenteet

Apumatriisi: 2D -taulukko, mihin tallennetaan tarkimman alkuperäisen kuvan tunniste

Konvoluutioikkuna: (ks Fourier-muunnos)

Kompleksiluku: jos tarpeen, mallia esim [https://algs4.cs.princeton.edu/.../Complex.java.html](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Complex.java.html)

<a name="fourier-muunnos"></a>
## Fourier-muunnos

Saa parametrikseen alkuperäisen 2D kuvapistetaulukon, rivi- ja sarakeindeksin ja ikkunan koon. Laskee ikkunalle Fourier-muunnoksen ja palauttaa ikkunan. 

### Aikavaativuus

O(n), missä n konvoluutioikkunan pikselien määrä

### Tilavaativuus

O(n), missä n on konvoluutioikkunan pikselien määrä

### Tietorakenteet

Konvoluutioikkuna: 4 * m^2 taulukko, missä m on konvoluutioikkunan sivun pituus, esimerkiksi 16 tai 32 (ikkuna peilataan kahteen suuntaan)

<a name="ylipaastosuodatus"></a>
## Ylipäästösuodatus

Saa parametrikseen Fourier-muunnos-metodissa lasketun konvoluutioikkunan ja suodattimen arvon. Palauttaa suodatetun konvoluutioikkunan. 

### Aikavaativuus

O(n), missä n konvoluutioikkunan pikselien määrä

### Tilavaativuus

O(1)

### Tietorakenteet

Konvoluutioikkuna: (ks Fourier-muunnos)

<a name="l2-normi"></a>
## L^2-normi

Saa parametrikseen Fourier-muunnos-metodissa lasketun konvoluutioikkunan palauttaa ikkunan L^2 normin (reaaliluku).

### Aikavaativuus

O(n), missä n konvoluutioikkunan pikselien määrä

### Tilavaativuus

O(1)

### Tietorakenteet

Konvoluutioikkuna: (ks Fourier-muunnos)

<a name="uuden-kuvan-luonti"></a>
## Uuden kuvan luonti

Uusi kuva luodaan instantioimalla kuvaobjekti, kopioimalla kuvapisteet oikeasta kuvasta ja tallentamalla kuvatiedosto levylle. Metodi saa parametrikseen tarkimpien kuvapisteiden valinnassa luodun apumatriisin, mikä sisältää tarkimman kuvan tunnisteen jokaiselle kuvapisteelle. 

Perustapauksessa apumatriisi on alkuperäisten kuvien kokoinen kokonaislukutaulukko. 

Ajan niin salliessa voidaan toteuttaa harvan matriisin implementaatio, missä apumatriisiin tallennetaan vain ne kuvapisteet mitkä eivät ole oletuskuvasta. Tällöin tietorakenteena on esimerkiksi kaksiulotteinen taulukko, missä rivinumero-, sarakenumero- ja arvorivit. 

Uuden kuvan luominen ja tallentaminen toteutetaan erilliseen IO-luokkaan, jotta kuvatiedoston tallentamiseen voidan käyttää XX valmiskirjastoa. 

### Aikavaativuus (perustapaus)

O(n) = O(1) + O(n) + O(1) = kuvaobjektin instantiointi + pikselien kopiointi + kuvatiedoston tallennus, 

missä n = kuvapisteiden määrä, apumatriisi käydään läpi pikseli kerrallaan.

### Aikavaativuus (harva matriisi) 

$\lambda$ * perustapaus, missä 0 <= $\lambda$ <= 1

kaikkia kuvapisteitä ei tarvitse käydä läpi, koska apumatriisi on harva.

### Tilavaativuus: 

O(n)








