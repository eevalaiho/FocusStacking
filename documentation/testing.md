# Testaus

## Yksikkötestaus

Testausraportti: 

Testikattavuus: [https://eevalaiho.github.io/FocusStacking/coverage/index.html](https://eevalaiho.github.io/FocusStacking/coverage/index.html)


## Järjestelmätestaus

Ohjelman järjestelmätestausta varten on [Apysinia fulva -merikorallia esittävästä kuvasta](https://free-images.com/display/aplysina_fulva_png.html) tehty 150x100 pikselin kokoisia testikuvia (alla). Kuvat on tehty kuvankäsittelyohjelmalla peilaamalla 75x50 pikselin kokoinen pala kahteen suuntaan. Tarkoituksena on ollut saada joka neljännekseltään yhtä tarkka kuva. 

![Tarkka](../application/src/main/resources/150x100-koralli-mirrored-sharp.png "Tarkka")

Näin saadusta kuvasta on muokattu kolme testikuvaa, joissa kuvankäsittelyohjelmalla on sumennettu joko vasen-, oikea- tai yläpuoli. 

![Vasemmalta blurri](../application/src/main/resources/150x100-koralli-mirrored-left-blur.png "Vasemmalta blurri")
![Oikealta blurri](../application/src/main/resources/150x100-koralli-mirrored-right-blur.png "Oikealta blurri")
![Ylhäältä blurri](../application/src/main/resources/150x100-koralli-mirrored-top-blur.png "Ylhäältä blurri")

Eri värikanavaa käytettäessä ohjelma tuottaa seuraavat kuvat (punainen, vihreä, sininen):

![Punainen](./images/150x100_koralli_output_RED_32.png "Punainen")
![Vihreä](./images/150x100_koralli_output_GREEN_32.png "Vihreä")
![Sininen](./images/150x100_koralli_output_BLUE_32.png "Sininen")

Kuvista voi silmämääräisesti huomata, että eri kuviin on valikoituu pikseleitä eri kuvista. Värikanavan valinta vaikuttaa siis siihen, mitä pikseleitä algoritmi valitsee. Tällä kuvalla sininen värikanava tuottaa silmämääräisesti parhaan tuloksen. Kun sinistä värikanavaa käytetän eri ikkunakoilla (16, 32, 64) ohjelma tuottaa seuraavat kuvat:

![Output 16](./images/150x100_koralli_output_BLUE_16.png "Output 16")
![Output 32](./images/150x100_koralli_output_BLUE_32.png "Output 32")
![Output 64](./images/150x100_koralli_output_BLUE_64.png "Output 64")


--

Kuvista voidaan silmämäärisesti havaita, että algoritmi valitsee kuviin eniten tarkkoja pikseleitä, mutta myös paljon epätarkkoja. Huomataan myös, että ikkunan koon kasvattaminen näyttäisi parantavan algoritmin tarkkuutta.

