type 'a btree = 
    Empty
    |Node of ('a * 'a btree * 'a btree )


Node(1,
    Node(2,Empty,Empty)
    Node(3,Empty,
        Node(4,Empty,Empty)))



(is_empty Empty) = true
(is_empty (Node(bt1,x,bt2))) = false
    let is_empty (bt: 'a btree): bool = 
        match bt with 
        |Empty -> true
        |_ -> false


let get_root (bt: 'a btree): 'a = 
    match bt with 
        Empty -> failwith "Empty"
        |Node (bt1 , x, bt2) -> x


let swap (bt : 'a btree ) : 'a btree = 
    match bt with 
    |Empty -> Empty
    |Node(bt1,x,bt2)-> Node(bt2,x,bt1)


let rec mem_btree (y:'a) (bt: 'a btree): = 
    match bt with 
        Empty ->false
        |Node(bt,x,bt2) ->
            (x == y) || (mem_btree y bt1) || (mem_btree y bt2)

(*recurrence terminale *)
let size (bt:'a btree) : int = 
    let rec loop( bt: 'a btree) (r: int ) : int =
        match bt with 
            |Empty -> r 
            |Node(bt1, _, bt2) -> 
                let r' = (loop bt2 r') in
                    (loop bt 0)
    in (loop bt 0)



let rec size (bt: 'a btree) : int = 
    match bt with 
        |Empty-> 0
        |Node (bt1, _, bt2) -> 1 + (size bt1)+ (size bt2)