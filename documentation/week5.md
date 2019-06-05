
# Viikkoraportti 5 (7.6.2019)

## Mitä olen tehnyt tällä viikolla?

Olen:
* Toteuttanut oman ArrayList -luokan ja sille testejä
* Toteuttanut oman Complex -luokan ja sille testejä
* Viilaillut testejä muutenkin
* Edistänyt päätoiminnallisuutta

Ajankäyttö: 16h

## Miten ohjelma on edistynyt? 

Paremmin. Ohjelma toimii ja isolla ikkunakoolla (32px) valitsee oikeita pikseleitä.

## Mitä opin tällä viikolla / tänään?

Että fft:n ja 2D fft:n testikeissit voi laskea kivasti R:llä. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Olen epävarma siitä, miten maksimi L^2 normi pitää laskea. Kaksi lähestymistapaa:

[1]

```
summa = 0
Jokaiselle Fourier-muunnosmatriisin alkiolle:
   summa += Abs(alkio)^2              # Tässä Abs koska alkio on kompleksiluku
Palauta Sqrt(summa)
```
[2]

```
maksimi = 0
Jokaiselle Fourier-muunnosmatriisin alkiolle:
   normi = L2Normi(alkio)             # Tässä L2Normi laksetaan Sqrt(alkio.re^2 + alkio.im^2)
   Jos normi > maksimi:
       maksimi = normi
Palauta maksimi
```

## Mitä teen seuraavaksi?

Viilaan päätoiminnallisuutta, kirjoitan puuttuvia testejä. Dokumentointia. Valmistaudun ensi viikon demoon.

## Kysymyksiä

Voiko käyttää exception-luokkia: IOException, IndexOutOfBoundsException, InvalidArgumentException? 

Voiko käyttää Math.PI -vakiota ja Math.sin ja Math.cos -funktioita?

Voiko käyttää System.arraycopy -metodia?


