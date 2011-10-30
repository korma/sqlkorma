;; you can get a string of the SQL instead of executing
;; by using the sql-only mode
(sql-only
  (select users))

;; => "SELECT * FROM users"

;; You can print a string of the sql, with the params and
;; return fake results by using the dry-run mode
(dry-run
  (select users
    (where {:age [> 5]})))

;; dry run :: SELECT * FROM users WHERE (user.age > ?) :: [5]
;; => [{:id 1}]
