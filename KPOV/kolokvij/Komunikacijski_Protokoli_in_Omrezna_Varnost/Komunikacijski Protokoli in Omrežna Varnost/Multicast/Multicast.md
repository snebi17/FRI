Za nek protokol, ki bi realiziral multicast, moramo pretehtati sledeče izzive:
- **Postopek razpošiljanja**:
	- Odkrivanje, kje so prejemniki paketa
	- Razpošiljanje zahteva dodatno delo, to je usmerjevalni protokoli morajo vedeti, kdo je prejemnik, tako da lahko paket razpošljejo povsod.
	- Multicast naslovi ne poznajo podomrežji (LAN segmentacije itd.) tako da potrebujejo dodatni posebni vnos.
- **Varnost**:
	- Ali paket lahko pošlje skupini kdorkoli ali samo član ekipe?
	- Prisluškovalec se lahko pretvarja, da je v skupini in posluša pakete legitimno.

### Naslavljanje IPv4
Za te skupine katerim razpošiljamo dejansko določimo različne IPv4, ki so iz posebnega razreda/razpona D:
![[Pasted image 20221111154415.png]]
Naprave poznajo ta posebni razred IP-jev. Definira jih IANA.

### Naslavljanje IPv6
![[Pasted image 20221111161926.png]]
![[Pasted image 20221111162912.png]]
