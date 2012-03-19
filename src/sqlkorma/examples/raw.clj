(select users
  (fields :id :name (raw "SOMECRAZYSYNTAX[]->()"))
  (where {:name [like "%_test5"]})

;;Or when all else fails, you can simply use exec raw
(exec-raw ["SELECT * FROM users WHERE age > ?" [5]] :results)
