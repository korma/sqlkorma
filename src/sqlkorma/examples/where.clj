;; Let's look at where clauses with a little more depth:
;; And everything that you can do with (where), you can
;; also do with (having)
;; To use (having) you need a (group) clause of course.
(-> (select* users)
    (where {:first "john"
            :last "doe"})
    (as-sql)) ;; we use (as-sql) to see the string of SQL
              ;; that will be run. To run this you can
              ;; replace (as-sql) by (exec) or (select)

;; Is the same as:
(-> (select* users)
    (where (and (= :first "john")
                (= :last "doe")))
    (as-sql))

;; Multiple where's are joined with AND, so this
;; is also the same:
(-> (select* users)
    (where {:first "john"})
    (where {:last "doe"})
    (as-sql))
;; => "SELECT \"USERS\".* FROM \"USERS\" WHERE
;;     (\"USERS\".\"FIRST\" = ? AND \"USERS\".\"LAST\" = ?)"

;; You can use other predicates for map values too
(-> (select* users)
    (where (or {:age [> 5]}
               {:last [like "doe"]}))
    (as-sql))

;; Is the same as:
(-> (select* users)
    (where (or (> :age 5)
               (like :last "doe")))
    (as-sql))
;; => "SELECT \"USERS\".* FROM \"USERS\" WHERE
;;     (\"USERS\".\"AGE\" > ? OR \"USERS\".\"LAST\" LIKE ?)"

;; Available predicates:
[=, like, and, or, >, <, >=, <=, in, not-in, not, not=, between]