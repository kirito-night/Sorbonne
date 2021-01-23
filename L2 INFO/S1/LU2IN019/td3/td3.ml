let len_comp_3 (xs : 'a list) :int =
	match xs with 
	|_::_::_::[] ->0
	|_::_::_::_ -> 1
	|_ -> -1
	
