.data 
s : .space 20
ch_ok : .asciiz "bien parenthése\n"
ch_nok : .asciiz "mal parenthése\n"

.text



bon_parenthesage : 
	#nv = 2 , na = 0 , nr = 0 
	#prologue
	addiu $29 , $29 , -12
	sw $31 , 8($29)
	
	
	xor $8, $8 , $8 # d = 0
	xor $9, $9 , $9 # i = 0
	
	while: 
	add $3, $4, $9 # $3 = ch + i 
	lw $10 , 0($3) # $10  = ch[i]
	
	beq $10 , $0, fin_while
	