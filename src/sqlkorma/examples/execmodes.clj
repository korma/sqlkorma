;; you can get a string of the SQL instead of executing
;; by using the sql-only mode
;;
;; Notice that even if there are subentities, sql-only
;; will only print the SQL for the main entity select.
(sql-only
  (select users))

;; => "SELECT * FROM users"

;; You can print a string of the sql, with the params and
;; return fake results by using the dry-run mode
;;
;; dry-run will also print all subentity selects
(dry-run
  (select users
    (where {:age [> 5]})))

;; dry run :: SELECT * FROM users WHERE (user.age > ?) :: [5]
;; => [{:id 1}]
