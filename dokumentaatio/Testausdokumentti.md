# Testausdokumentti

## Yksikkötestaus

Kaikki reitinhakualgoritmeille ja omille tietorakenteille on toteutettu kattavat rivikattavuuden yksikkötestit. Omien tietorakenteiden testikattavuus on 100%. Reitinhakualgoritmeista JPS on ainoa millä ei ole täyttä 100%.

Yksikkötestit voi ajaa `gradle test` komennolla.

## Suorituskykytestaus

Benchmark data on osoitteesta https://www.movingai.com/benchmarks/grids.html

Tulokset kartoittain:

<img src=https://www.movingai.com/benchmarks/dao/brc000d.png>

**BFS**:
* Average time: 3.70727 ms
* P25: 1.253 ms
* Median: 3.0133 ms
* P75: 4.8248 ms

**A**:
* Average time: 1.516227 ms
* P25: 0.085 ms
* Median: 0.394 ms
* P75: 1.9776 ms

**JPS**:
* Average time: 0.685276 ms
* P25: 0.1981 ms
* Median: 0.4178 ms
* P75: 0.7096 ms

<img src=https://www.movingai.com/benchmarks/room/16room_007.png>

**BFS**:
* Average time: 66.617826 ms
* P25: 15.0159 ms
* Median: 55.566 ms
* P75: 98.2313 ms

**A***:
* Average time: 18.834426 ms
* P25: 1.6974 ms
* Median: 7.2788 ms
* P75: 20.934 ms

**JPS**:
* Average time: 5.457218 ms
* P25: 0.5462 ms
* Median: 1.7676 ms
* P75: 5.934 ms

<img src=https://www.movingai.com/benchmarks/random/random512-10-0.png>

**BFS**:
* Average time: 62.228822 ms
* P25: 15.081901 ms
* Median: 54.298201 ms
* P75: 91.832801 ms

**A***:
* Average time: 2.913384 ms
* P25: 0.218201 ms
* Median: 1.008901 ms
* P75: 3.322901 ms

**JPS**:
* Average time: 5.229794 ms
* P25: 0.598701 ms
* Median: 1.7822 ms
* P75: 3.5039 ms

<img src=https://www.movingai.com/benchmarks/maze/maze512-1-0.png>

**BFS**:
* Average time: 23.097508 ms
* P25: 4.371 ms
* Median: 16.291 ms
* P75: 34.7954 ms

**A**:
* Average time: 32.494727 ms
* P25: 4.6182 ms
* Median: 22.1585 ms
* P75: 55.7388 ms

**JPS**:
* Average time: 27.118333 ms
* P25: 4.432399 ms
* Median: 17.5369 ms
* P75: 42.4282 ms
