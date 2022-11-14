    .text
tabela: .byte 127,100,255,24,88,31,56,192,155,224,48,0,128,99,147,177
rez: .space 1
    .align
.global __start
__start:

    adr r0, tabela
    mov r1, #1 @ stevec
    mov r2, #0 @ stevilo primernih
    mov r3, #8 @ 0000 1000

@ a)
@     ldrb r4, [r0]
@     tst r4, r3
@     addne r2, r2, #1

@ zanka:  ldrb r4, [r0, r1]
@         tst r4, r3
@         addne r2, r2, #1
@         cmp r1, #15
@         addlo r1, r1, #1
@         blo zanka

@ b)
@     ldrb r4, [r0]
@     and r5, r4, #0xc1
@     cmp r5, #0x01
@     addeq r2, r2, #1
	
@ zanka:  ldrb r4, [r0, r1]
@ 		  and r5, r4, #0xc1
@ 		  cmp r5, #0x01
@ 		  addeq r2, r2, #1
@ 		  cmp r1, #15
@ 		  addlo r1, r1, #1
@ 		  blo zanka

@ c)
@     ldrb r4, [r0]
@     and r5, r4, #8
@     cmp r5, #0
@     addne r2, r2, #1

@ zanka:  ldrb r4, [r0, r1]
@         and r5, r4, #8
@         cmp r5, #0
@         addne r2, r2, #1
@         cmp r1, #15
@         addlo r1, r1, #1
@         blo zanka

@ d)
@     ldrb r4, [r0]
@     and r5, r4, #15
@     cmp r5, #8
@     addeq r2, r2, #1

@ zanka:  ldrb r4, [r0, r1]
@         and r5, r4, #15
@         cmp r5, #8
@         addeq r2, r2, #1
@         cmp r1, #15
@         addlo r1, r1, #1
@         blo zanka
    
str r2, rez

__end: b __end