(select users
  (fields :id :first (raw "users.last"))
  (where {:first [like "%_test5"]}))

;;Or when all else fails, you can simply use exec raw
(exec-raw ["SELECT * FROM users WHERE age > ?" [5]] :results) 