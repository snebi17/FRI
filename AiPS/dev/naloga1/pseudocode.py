def poisci_najkrajso_pot():
    # Poiskati moramo najkrajšo pot, ki vsebuje vsa mesta iz seznama
    # Mesto je definirano z imenom, koordinatama ter sosednjimi mesti, s katerimi je povezan
    # Razdaljo med mesti izračunamo s pomočjo evklidske razdalje (√(Δx**2 + Δy**2) => funkcija razdalja()) 
    # in jo lahko uporabimo kot zračno razdaljo med dvema mestoma
    mesta = []
    obiskana_mesta = []
    min_razdalja = MAX_RAZDALJA
    za mesto iz mesta:
        obiskana_mesta.push(mesto)
        za sosednje_mesto iz mesto.sosedi:
            če je razdalja(mesto, sosednje_mesto) < min_razdalja in sosednje_mesto ni v obiskana_mesta:
                min_razdalja = razdalja(mesto, sosednje_mesto)
                najblizje_mesto = sosednje_mesto
        obiskana_mesta.push(najblizje_mesto)
        min_razdalja = MAX_RAZDALJA
        če je len(mesta) enaka len(obiskana_mesta):
            končaj z iskanjem, poiskali smo najkrajšo možno pot
    return obiskana_mesta

