;;Let's look at where clauses with a little more depth:
(where {:first "john"
        :last "doe"})

;;Is the same as:
(where (and (= :first "john")
            (= :last "doe")))

;;Multiple where's are joined with AND, so this
;;is also the same:
(where {:first "john"})
(where {:last "doe"})

;;You can use other predicates for map values too
(where (or {:age [> 5]}
           {:last [like "doe"]}))

;;Is the same as:
(where (or (> :age 5)
           (like :last "doe")))

;;Available predicates:
[=, like, and, or, >, <, >=, <=, in, not, not=]
