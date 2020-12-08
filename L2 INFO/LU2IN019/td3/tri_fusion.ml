let rec division liste=
    match liste with
    |[]->[],[]
    |a::[]->liste,[]
    |a::b::c->
        let (l1,l2)=division c in
        a::l1,b::l2;;
        
let rec fusion liste1 liste2=
    match liste1,liste2 with
    |[],_->liste2;
    |_,[]->liste1;
    |t1::q1,t2::q2->
        if t1<t2 then
            t1::(fusion q1 liste2)
        else
            t2::(fusion liste1 q2);;


let rec tri_fusion liste=
    match liste with
    |[]->[];
    |a::[]->liste;
    |_ ->
        begin
            let (liste1,liste2)=division liste in
            fusion (tri_fusion(liste1)) (tri_fusion(liste2));
        end;;
