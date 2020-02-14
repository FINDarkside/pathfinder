# Testausdokumentti

## Yksikkötestaus

Kaikki reitinhakualgoritmeille ja omille tietorakenteille on toteutettu kattavat rivikattavuuden yksikkötestit. Omien tietorakenteiden testikattavuus on 100%. Reitinhakualgoritmeista JPS on ainoa millä ei ole täyttä 100%.

## Suorituskykytestaus

Tällä hetkellä on toteutettuna yksinkertainen suorituskykytestaus. Ohjelma lataa [.scen](https://www.movingai.com/benchmarks/formats.html) tiedoston ja suorittaa siinä määritellyt skenaariot. Lopuksi ohjelma tulostaa vastaavan muotoisen tuloksen:
```
Total time: 
BFS: 312 ms
A*: 247 ms
JPS: 140 ms
```
