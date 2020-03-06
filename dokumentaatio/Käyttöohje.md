# Käyttöohje

Ohjelma ottaa argumenttinaan polun kansioon jossa on tarvittava data benchmarkille, ja suorittaa suorituskykytestauksen. Kansiossa tulee olla seuraava rakenne:

```
- maps
  - *.map tiedosto [seuraavassa formaatissa](https://www.movingai.com/benchmarks/formats.html)
- scenarios
  - [scenario group name]
    - *.scen tiedosto [seuraavassa formaatissa](https://www.movingai.com/benchmarks/formats.html)
 ```
 
BenchmarkRunner siis suorittaa kaikki skenaario tiedostot, ja ryhmittää tulokset sen perusteella missä kansiossa skenaario tiedosto oli. Tuloksissa on keskimääräinen aika reitin löytämiselle, mediaani ja 25. ja 75. persentiilit.

Ohjelman voi ajaa komentoriviltä komennolla `java -jar ./benchMarkdata`, toki oikealla polulla dataan.

Javadocin saa generoitua komennolla `gradle javadoc` minkä jälkeen dokumentointi löytyy polusta `./build/docs/javadoc/index.html`.
