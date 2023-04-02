Paketi so lahko naslovljeni na več načinov, te ločimo *glede na število naslovikov*:
- **Unicast**: 1 naslovnik
- **Broadcast**: Vsi ki jih naslavljamo v točno določenem omrežju (LAN)
- **Multicast**: Naslovniki so samo določena skupina naprav 
- (**Anycast** ipv6: kdorkoli iz skupine)

Posebenost je pri tem da unicast je zmeraj unikaten naslov ali v Internetu ali pa v lokalnem omrežju, broadcast je nek poseben naslov, ki se ne propagira oz. pošilja izven L2 meja oz. ga omejuje L3 meja.

Multicast pa je potrebno rešiti na nek drug način.

#### Multicast
Multicast lahko realiziramo takole:
- Unicast vsakemu, ki je v neki skupini -> Obremenitev omrežij je manjša, vendar pa obremenjujemo pošiljatelja (**Uporabimo ko je gostota prejemnikov manjša**)
- Broadcast vsem, in samo tisti, ki so v skupini sprejmejo paket/podatke-> Pošiljatelj je manj obremenjen vendar pa je temu več obremenjeno omrežje (**Uporabimo ko je gostota prejemnikov večja**)

![[Pasted image 20221111150635.png]]
To, da se zadeva izvaja na IP nivoju, pomeni da morajo v storitveni shemi delovati tudi L3 naprave -> *Routerji*
## Povezave
- [[Multicast]]
- [[Internet Group Management Protocol]]
- [[Multicast Listener Discovery]]
- [[Razpošiljevalno Drevo in Usmerjanje]]