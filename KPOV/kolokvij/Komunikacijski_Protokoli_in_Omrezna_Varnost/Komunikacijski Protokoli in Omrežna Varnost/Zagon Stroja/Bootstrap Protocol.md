Ob zagonu, računalnik načeloma lahko ali pa tudi ne, torej nedefinirano pozna sledeče svoje lastnosti:
- ime
- IP naslov
- ...

Vendar mora poznati protokol za nalaganje OS preko omrežja, torej za uspešno nalaganje, mora znati računalnik:
1. Poiskati strežnik, s katerega bo naložil OS
2. Znati se nastaviti, kot bo zahteval / svetoval strežnik
3. Prenesti OS k sebi, kot datoteko
4. Namestiti sam OS in ga zagnati

>Zadnji korak je isti, kot pri BIOS/UEFI zaganjanju

Je pa načrtovalsko določeno, da se 1. in 2. korak izvedeta s BOOTP protokolom in 3. s TFTP

# BOOTP
BOOTP oz. Bootstrap Protokol je protokol definiran v RFC 951:

Kratek povzetek protokola:
1. Zgodi se ena izmenjava paketov, med hostom in serverjem, tukaj se uporabljajo timeout-i za ponovno pošiljanje paketa, dokler ne dobimo odgovor od host-a. Isti paketni format se uporablja za obe smeri.
2. Obstaja polje v paketu imenovano '*opcode*', ki ima dve vrednosti. Host broadcast-a '*bootrequest*' paket (Ta vsebuje MAC naslov in IP, če ga ima, ni pa nujno), če obstaja kakšen server z BOOTP protokolom, ta odgovori z '*bootreply*' paketom. Torej vrednosti sta bootrequest in bootreply.
3. Request lahko opcijsko vsebuje ime serverja, od katerega želi odgovor. To je v primeru, da lahko client forsira boot proces od specifičnega serverja, v primeru da več bootfile-ov obstaja, ali pa če je server v dalnjem omrežju in domeni. V tej fazi se client ne ukvarja z imenskim / domenskim storitvami, to prepusti BOOTP serverju.
4. Request lahko opcijsko tudi vsebuje 'generičen' filename za boot-anje, na primer 'unix' ali 'ethertip'. Ko server pošlje bootreply, spremeni ta field v z absolutno potjo do pravega boot file-a. V tem koraku lahko server pregleda svojo podatkovno bazo za izbiranje tega tega imena za boot file, ki pa strukturira clientov naslov in filename request v relaciji z pravim boot file-om, posebno narejenim za tisti client. Če tega ni, potem naloži nekakšen default.
5. Če clienti svoj IP ne poznajo ima server tudi database, ki relira MAC naslove z IP naslovi, in v bootreply-u pošlje tudi IP clientu.
6. Nekatere topologije so postavljene tako, da v LAN-u ni nobenega TFTP serverja, v tem primeru BOOTP podpira tudi zagon iz serverjev nekaj hoppov stran oz. nekaj gateway-u

>BOOTP vzpostavi tako imenovani lockstep ali koračni pogovor, t.j. **odjemalec vpraša, strežnik odgovori**. Zmeraj tako

#### Oblika paketa
![[Pasted image 20221019192208.png]]
![[Pasted image 20221019192227.png]]

## Povezave
- [[Razširitve Vend Opcije]]
- 