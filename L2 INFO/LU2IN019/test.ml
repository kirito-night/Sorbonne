let pop (xs: 'a list ) : 'a list = 
 match xs with 
 | [] -> []
 |x::[] -> xs
 |x :: y :: l -> (x+y):: l
 let _ = assert(pop [1;2;2;3] = [3;2;3])

let rec iter (n : int) (f : 'a -> 'a ->'a) (a : 'a) = 
    if (n > 0) then (f (iter (n - 1) f a) a) 
    else a

let _ = assert(iter 3 (fun x->(fun y -> (x+y+1))) 3 = 15) 