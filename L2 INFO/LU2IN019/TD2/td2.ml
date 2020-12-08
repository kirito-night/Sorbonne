let sum_f (f: int->int) (n: int) : int =
	let rec loop (f: int->int) (n : int) : int = 
		if(n>0) then (f n) + (loop f (n-1)) else (f 0)
	in  if(n<0) then raise(Invalid_argument "sum_f")
		else (loop f n)

let  u (n : int) : int =
	let rec loop (n: int) = 
		if n > 0 then 3 * (loop (n-1)) + 4
		else 42
	in if (n<0 ) then raise(Invalid_argument "n doit etre positive")
	else loop n
	


let u_n (n: int) : int = 
	sum_f u n
	
	
let sum_u_test (n:int) = 
	let rec loop (n : int ) = 
		if (n>0) then (u (n)) + (loop (n-1))
		else (u 0)
	in if (n < 0) then raise(Invalid_argument "non negative")
	else (loop  (n))
	

let rec sum_u (n:int) : int =
  if (n = 0) then (u 0)
  else (u (n))+(sum_u (n-1));;
  
let sum_u_1 (n:int) : int =
  (* Hypothese : n >= 0 *)
  let rec loop (n:int) (t:int) : int =
    if (n = 0) then t
    else t + (loop (n-1) (3 * t + 4))
   in loop n 42
   
   
let sum_u_2 (n:int) : int =
  (* Hypothese : n >= 0 *)
  let rec loop (n:int) (t:int) (s:int) : int =
    if (n = 0) then s + t
    else loop (n-1) (3 * t + 4) (s + t)
   in loop n 42 0



let rec iter (n:int) (f: 'a -> 'a) (a:'a) : 'a =
  if (n > 0) then (iter (n-1) f (f a))
  else a



