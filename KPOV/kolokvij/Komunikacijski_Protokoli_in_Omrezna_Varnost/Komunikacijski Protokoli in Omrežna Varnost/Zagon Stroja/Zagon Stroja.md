CPE ob priklopu stroja na napetost / napajanje, postavi *programski števec na določeno* vrednost, to je hardware-sko določeno in vsakemu procesorju specifično.

Za tem začne izvajati ukaze, ki se nahajajo na tisti lokaciji + programski števec v pomnilniku, tam se pa ponavadi nahaja bootloader (BIOS je ponavadi shranjen v posebnem persistentnem EEPROM-u posebaj na matični plošči, ta se je prekopiral točno na tisto pozicijo v pomnilniku, kjer se je začel izvajat), ki je program, ki zaštarta operacijski sistem, tako da CPU-ju pove, kje se v pomnilniku nahajajo zagonske datoteke operacijskega sistema, kjer se ta začne izvajati.

BIOS je eden izmed standardnih protokolov za komunikacijo, ki ga počasi zamenjuje UEFI.
## Povezave
- [[BIOS]]
- [[UEFI]]
- [[Zagon Stroja Preko Omrežja]]
