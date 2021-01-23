(*exercice 5*)
let less_divider (i: int) (n: int) : int = 
	let rec loop (a: int) (n: int) = 
		if(n mod a = 0) then a
		else loop (a+1) n
	in if ((i<2) || (n<2)) then raise(Invalid_argument "i et n ne peut etre plus petit que 2, c'est a dire egale a 1")
	else loop i n

let prime (n : int ) : bool =
	if( (less_divider 2 n) == n ) then true 
	else false 
	
let rec next_prime (n : int ) : int = 
	if (prime n) then n
	else (next_prime (n+1))

let nth_prime (n : int ) : int =
 let rec loop (n : int ) (x : int ) = 
 	if (n = 0) then x
 	else (loop (n) (next_prime(x+1)))
 in loop n 2


(*exercice 6*)
(*Q1*)
let f a x = 
	(x+.(a/.x))/.2.

(*Q2*)
let sqrt_n (n: int ) ( a: float) (x0: float) : float =
	let rec loop (n: int ) ( a: float) (x0: float) = 
		if ( n = 0 ) then x0
		else (loop (n-1) a (f a x0))
	in loop n a x0
	
let eq_eps (e: float) (x:float) (y:float) : bool=
	if (abs_float (x-.y) < e) then true
	else false  

let sqrt_x (e : float) (a: float) (x0: float) : float =
	let rec loop (e : float) (a: float) (x0: float) = 
		if(eq_eps e a (x0*.x0)) then x0
		else loop e a (f a x0)
	in loop e a x0

	
	
