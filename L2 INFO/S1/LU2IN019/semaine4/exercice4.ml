type 'a btree =
   Empty
 | Node of ('a * 'a btree * 'a btree)  

let rec mem (bt : 'a btree) (x : 'a) : bool = 
  match bt with
  |Empty -> false
  |Node(a, g, d) ->
    if x = a then true
    else if x > a then mem d x
    else mem g x



let rec insert (x : 'a ) (bt : 'a btree) : 'a btree=
    match bt with 
    | Empty -> Node(x, Empty, Empty)
    |Node(a, g, d) -> if x>a  then Node(a , g , (insert x d) )
    else Node(a , (insert x g), d)


(*let rec from_list_nt(xs : 'a list) : 'a btree = 
    let bt = Empty  in
    match xs with 
    |[] -> Empty
    |x:: xs -> 
*)
let  from_list(xs : 'a list) : 'a btree = 
    let rec loop (xs : 'a list ) (bt : 'a btree) : 'a btree =
        match xs with 
        |[] -> bt
        |x :: xs -> loop xs (insert x bt)
    in loop xs Empty

let from_list_2 ( xs : 'a list) : 'a btree = 
    List.fold_left (fun bt x-> (insert x bt) ) Empty xs

let rec to_list (bt : 'a btree) : 'a list =
    match bt with 
    |Empty -> []
    |Node(x, g , d) -> (to_list g) @ [x] @ (to_list d)


let tri (xs: 'a list) : 'a list = 
    to_list (from_list_2 xs) 


       

