let rec mem(x: 'a) (l:'a list) :bool = 
	match l with 
	| [] ->false 
	| y:: ys -> 
		if y = x then true
		else mem x ys 


(*sortie anticipe*)

let rec nth(l: 'a list) (i: int ): 'a = 
	match l with 
	|[]-> raise((Invalid_argument "nth"))
	| x :: xs ->
		if i = 0  then x 
		else (nth xs (i-1))
(* renvoir le n-ime element de la list*)


let rec nth_match(l: 'a list) (i: int ): 'a = 
	match l with 
	|[]-> raise((Invalid_argument "nth"))
	| x :: xs ->
		match i with 
		| 0 -> x
		| _ -> nth_match xs (i-1)
		

let rec map (f: 'a -> 'b)(l : 'a list) : 'b list = 
	match l with 
	|[] -> []
	| x:: xs -> (f x ) :: ( map f xs)
	
let fois_deux (l : int list) : int list  = 
	map (fun x -> 2 * x) l


let fois_deux_without_fun (l : int list) : int list= 
 let ma_fonction_qui_multi_par_2 (x : int ) : int  = 
 	2 * x 
 in map ma_fonction_qui_multi_par_2 l
 
 let rec filter (p: 'a->bool) (l: 'a list) : 'a list =
 	match l with 
 	| [] -> []
 	| x :: xs -> 
 		if (p x) then 
 			x::(filter p xs)
 		else 
 		 (filter p xs)
 
let garde_pair (l : int list) : int list = 
  (filter (fun x-> x mod 2 = 0) l )
 
let rec fold_left f a l=
	match l with 
	|[] -> a
	|x :: xs -> fold_left f (f a x) xs
	
	
(*  _ represente "sinon" tous les autre cas *)
	
