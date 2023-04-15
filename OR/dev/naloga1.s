    .text

S1: .byte 91
S2: .byte 1      
    .align
    
.global __start
__start:
    adr r0, S1
    ldrb r1, [r0]
    adr r0, S2
    ldrb r2, [r0]

    cmp r1, r2
    blo manjsi
    bhi vecji
   
manjsi: mov r3, #1 @s1 < s2
        b __end
vecji: mov r3, #2 @s1 > s2

    adr r0, S1
    ldrsb r1, [r0]
    adr r0, S2
    ldrsb r2, [r0] 

    cmp r1, r2
    movlt r3, #1 @s1 < s2
    movgt r3, #2 @s1 > s2 
     
__end: b __end