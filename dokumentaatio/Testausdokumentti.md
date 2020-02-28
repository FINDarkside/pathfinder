# Testausdokumentti

## Yksikkötestaus

Kaikki reitinhakualgoritmeille ja omille tietorakenteille on toteutettu kattavat rivikattavuuden yksikkötestit. Omien tietorakenteiden testikattavuus on 100%. Reitinhakualgoritmeista JPS on ainoa millä ei ole täyttä 100%.

Yksikkötestit voi ajaa `gradle test` komennolla.

## Suorituskykytestaus

`BenchmarkRunner.runBencmark` ottaa argumenttinaan polun kansioon jossa on tarvittava data benchmarkille, ja suorittaa suorituskykytestauksen. Kansiossa tulee olla seuraava rakenne:
```
- maps
  - *.map tiedosto [seuraavassa formaatissa](https://www.movingai.com/benchmarks/formats.html)
- scenarios
  - [scenario group name]
    - *.scen tiedosto [seuraavassa formaatissa](https://www.movingai.com/benchmarks/formats.html)
 ```
 
BenchmarkRunner siis suorittaa kaikki skenaario tiedostot, ja ryhmittää tulokset sen perusteella missä kansiossa skenaario tiedosto oli. Tuloksissa on keskimääräinen aika reitin löytämiselle ja 25. ja 75. persentiilit.

Esimerkkitulos:
```
Baldurs Gate:

BFS: 
Average time: 1.066537 ms
P25: 0.1063 ms
P75: 1.2164 ms

A*: 
Average time: 0.901288 ms
P25: 0.0233 ms
P75: 1.0545 ms

JPS: 
Average time: 0.311419 ms
P25: 0.0355 ms
P75: 0.3626 ms
```
