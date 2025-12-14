# Contributing guide
Tak fordi du overvejer at bidrage til dette projekt! Vi har brug for så mange hænder som overhovedet muligt.  
Ved bidragelse bedes du følge retningslinjerne nedenfor.

### Branch struktur
Vi arbejder på følgende måde med vores branches:
- **main-branchen**
    - Dette er vores release-branch og skal forblive stabil
- **dev-branchen**
    - Dette er vores udviklingsbranch, og skal så vidt som muligt være fejlfri
- **feature-branches**
    - Denne branch skal navngives efter den specifikke feature, og baseres på dev-branchen. Det er denne branch, hvor man kan skrive kode til en specifik feature

Når man ens feature-branch er færdig, skal man merge dev ind i sin feature-branch før man forsøger at merge sin feature-branch ind i dev.
Dette gøres for at undgå fatale merge-conflicts i dev-branchen.

Husk altid at teste en feature *før* du forsøger at merge den ind i dev-branchen.

### Test
Når en feature er færdig, skal der laves tilsvarende unittests for controller-metoder.
Integrationstest er en fordel, men ikke et krav.

###  Hvordan du kommer i gang
1. Fork repoet til din egen Github.
2. klon dit fork:  
    * git clone https://github.com/Wishlist-JMS-Miniprojekt-E25/Eksamensprojekt-E25.git




