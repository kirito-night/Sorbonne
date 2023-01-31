
programme :-
    load_files('LRC_part1.pl'),
    load_files('LRC_part2.pl'),
    load_files('LRC_part3.pl'),
    load_files('LRC_Tbox_Abox.pl'),
    load_files('LRC_nnf.pl'),
              premiere_etape(Tbox,Abi,Abr),
              deuxieme_etape(Abi,Abi1,Tbox),
              troisieme_etape(Abi1,Abr).


             