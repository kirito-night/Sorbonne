let less_divider (i : int ) (n : int) : int =
	let rec loop (i : int ) (n : int ) = 
		if ( n mod i == 0) then i 
		else (loop (i+1) (n))
	in if (i < 2 || n < 2) then raise(Invalid_argument " i et  n ne doit pas etre plus petit diviseur")
	else (loop i n)
	

