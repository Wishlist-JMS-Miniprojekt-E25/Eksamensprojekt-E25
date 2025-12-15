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

###  Fork/klon repo
1. Fork repoet til din egen Github.
2. klon dit fork:  
    * git clone https://github.com/Wishlist-JMS-Miniprojekt-E25/Eksamensprojekt-E25.git

### Teknologier
Projektet blev udviklet med de følgende teknologier:
- jdbcTemplate
- MySQL(8.2.0)
- Thymeleaf (3.1.3)
- HTML
- CSS
- IntelliJ IDEA (2025.2.5)
- H2 2.2.224 database til test
- MockMVC til test af controllere
- Java (21)
- JDK (21)
- Maven (3.9.9)
- Springboot (4.0.0)

Det er vigtigt for programmets funktion, at du har downloadet de nødvendige programmer.

Der forventes en generel forståelse af de brugte teknologier.

### Ønskede features
Projektet har potentiale for videreudvikling, og vi er åbne overfor idéer.

Vi er særligt interesseret i muligheden for at tilføje kompetencer til employees, så man kan tilføje de mest kompetente ansatte til de rette opgaver.

Små forbedringer og ændringer til programmets fordel tages glædeligt imod.

Hold den gode tone og sørg for at al feedback er konstruktiv og løsningsorienteret.

Tak fordi du ønsker at bidrage til vores projekt!

