BIOS ali **B**asic **I**/**O** **S**ystem je firmware oz. nizkonivojska programska oprema, ki dopušča CPE-ju komuniciranja z periferijo, bolj podrobno z pomnilniško periferijo, saj so operacijski sistemi naloženi na trde diske in druge periferne pomnilniške naprave.

BIOS sestoji iz dveh sklopov:
- Koda, ki se prične ob zagonu računalnika
- Gonilniki za I/O enote
Koda izkoristi te driverje in naloži poseben program -> operacijski sistem, s tem je operacijska oprema "*obuta*" -> "*booting*".

Ta operacijski sistem se ponavadi nahaja v prvih blokih vhodno-izhodne enote, ki je particija, ki se imenuje **master boot record - MBR**

>Dejansko imamo tudi opcijo, da naložimo drug program, iz katerega lahko potem nalagamo več različnih operacijskih sistemov, ta program se imenuje **Bootstrap loader / bootloader**.

![[Pasted image 20221019152642.png]]
