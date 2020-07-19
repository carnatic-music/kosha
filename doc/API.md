## API Endpoints
#### GET /search?query=\<query\>&type=\<type\>
Example:
* Query: `GET /search?query=gaula&type=ragam`

* Response:
```json
Status Code: 200
Body: [
{"ragam-link":null,"name":"lalitA","data-source":"Sangeethapriya","type":"ragam","melakartha":null,"similarity":8.259259339534875,"id":614,"arohanam":null,"avarohanam":null,"mela-ragam-id":null},
{"ragam-link":null,"name":"lalitaa","data-source":"Karnatik","type":"ragam","melakartha":null,"similarity":7.696969696969697,"id":34,"arohanam":null,"avarohanam":null,"mela-ragam-id":null},
{"ragam-link":"","name":"Lalit?","data-source":"Wikipedia","type":"ragam","melakartha":false,"similarity":4.835016915292451,"id":1542,"arohanam":"S R1 G3 M1 D1 N3 S","avarohanam":"S N3 D1 M1 G3 R1 S","mela-ragam-id":922},
{"id":2574,"name":"lalitaambe","composer":"H.N. Muttayyah Bhaagavatar","taala":"aadi","language":null,"similarity":3.5291375499783144,"type":"kriti"},
{"id":3575,"name":"shrI_lalitE","composer":"muthaiah_bAgavatar","taala":null,"language":null,"similarity":3.3434342832276314,"type":"kriti"},
{"id":2138,"name":"shree lalite","composer":"H.N. Muttayyah Bhaagavatar","taala":"aadi","language":null,"similarity":3.207459290822347,"type":"kriti"},
{"id":1072,"name":"lalite shree","composer":"Tyaagaraaja","taala":"aadi","language":"telugu","similarity":3.207459290822347,"type":"kriti"},
{"ragam-link":"","name":"Kalika","data-source":"Wikipedia","type":"ragam","melakartha":false,"similarity":2.990675999359651,"id":1298,"arohanam":"S R2 G2 P D2 N2 S","avarohanam":"S N2 D2 P G2 R2 S","mela-ragam-id":929}
]
```

#### GET /ragam/<ragam-id>
Example:
* Query: `GET /ragam/34`

* Response:
```json
Status Code: 200
Body: {
"ragam": {"ragam-id":34,"name":"lalitaa","arohanam":null,"avarohanam":null,"wiki-page":null,"melakartha":null,"mela-ragam-id":null,"data-source":"Karnatik"},
"kritis":[
	{"kriti-id":299,"name":"chandiranoliyil","composer":"Subramanya Bhaaratiyaar","taala":"caapu","language":"tamil","ragam-id":34,"url":"http://www.karnatik.com/c0006.shtml","data-source":"Karnatik"},
	{"kriti-id":323,"name":"cedipoke o","composer":"Walajapet Venkatramana Bhaagavatar","taala":"aadi","language":"telugu","ragam-id":34,"url":"http://www.karnatik.com/c3163.shtml","data-source":"Karnatik"}],
"parent-ragam":{"ragam-id":1812,"name":"M?yam?lava Gowla","arohanam":"S R1 G3 M1 P D1 N3 S","avarohanam":"S N3 D1 P M1 G3 R1 S","wiki-page":"/wiki/Mayamalavagowla","melakartha":true,"mela-ragam-id":null,"data-source":"Wikipedia"},"kritis":[],"parent-ragam":null}
}
```

#### GET /kriti/<kriti-id>
Example:
* Query: `GET /kriti/3575`

* Response:
```json
Status Code: 200
Body: {
"kriti": {"kriti-id":3575,"name":"shrI-lalitE","composer":"muthaiah-bAgavatar","taala":null,"url":null,"lyrics":null,"meaning":null,"ragam-id":740,"data-source":"Sangeethapriya"},
"renditions":[
{"rendition-id":15523,"concert-id":"01-C1541",
"concert-url":"http://www.sangeethamshare.org/tvg/UPLOADS-1601---1800/1677-MB-Chamundamba-Ashtothra-Krithis-Part-7/",
"track-number":6,
"url":"http://sangeethapriya.ravisnet.com:8080/cgi-bin/download.cgi?c2FuZ2VldGhhbXNoYXJlLm9yZy9wdWJsaWNfaHRtbC90dmcvVVBMT0FEUy0xNjAxLS0tMTgwMC8xNjc3LU1CLUNoYW11bmRhbWJhX0FzaHRvdGhyYV9Lcml0aGlzLVBhcnQtNy8wNi1TaHJpX2xhbGl0ZV9qYWdhbm1hdGUtbmFkYW5hbWFrcml5YS1tdXR0aWFoX2JoYWdhdmF0YXIubXAz",
"main-artist":"Chinmaya Sisters - Uma,Radhika",
"kriti-name":"shrI-lalitE",
"kriti-id":3575}]
}
```
