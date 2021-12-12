.data

.text 

ori $8 , $0 , 0x9821
lui $9, 0xffff
ori $9 , $9 , 0xba43


#0xba984321

andi $10, $8 , 0x00ff #0x21 dans $10 
andi $11 , $9, 0x00ff #0xffff0043 dans $11
sll $11, $11 , 8 # 0xff004300
or $10, $10, $11 #0xff004321

sll $10, $10 , 2 # 0x00432100
srl  $10, $10 , 2 #0x00004321

srl $8, $8, 2 #0x00000098
andi $9,$9,0xff00 #0xffffba00
or $9 , $9, $8 #0xffffba98

sll $9, $9 , 2 # 0x00432100
or $10 , $9, $10


xor $3, $3, $3
addiU $3 , $3, -1

ori $2 , $0 , 10
syscall



 

