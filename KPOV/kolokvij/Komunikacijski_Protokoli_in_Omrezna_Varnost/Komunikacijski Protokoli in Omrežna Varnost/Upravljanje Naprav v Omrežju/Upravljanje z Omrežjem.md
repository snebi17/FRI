S upravljanjem omrežja želi vzpostaviti storitev, ki nam bo zagotovala:
- Povezavo do omrežja 
- Podpiranje vseh raznih naprav v omrežju
- Avtomatizirana razširljivost 
- Pregled stanja naprav
- Sporočila o napakah
- Oddaljeno upravljanje
- ...


Upravljamo lahko s *napakami*, *konfiguracijami*, *beleženje dostopov* in *varnostjo*. Ta storitev lahko v programski opremi naredimo v CLI ali GUI načinu.

Da lahko nekaj takega realiziramo potrebujemo **PROTOKOL**, potrebne vse **L4 STORITVE** in nato **APLIKACIJSKE STORITVE**.

### Arhitektura upravljanja
![[Pasted image 20221028151758.png]]

### Podatki za upravljanje
Podatke ne samo da moramo dobiti, vendar jih moramo tudi logično nekako strukturirati. Te shranjujemo in upravljamo v **MIB**-u (**M**anagement 
**I**nformation **B**ase), kjer so podatki o *objektih* in njihovih *parametrov*. Upravljalec ima svoj **MDB** (Management Database), kjer pa centralizirano hrani konkretne podatke o vsaki napravi. Za definicijo zapisa Objektov in Parametrov pa imenujemo **SMI** (Structure of Management Information).

![[Pasted image 20221028154703.png]]
![[Pasted image 20221028155237.png]]
![[Pasted image 20221028155300.png]]
![[Pasted image 20221028155443.png]]![[Pasted image 20221028155458.png]]
