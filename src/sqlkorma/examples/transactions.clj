;;transaction, rollback, and is-rollback? are in korma.db
(transaction
  (insert users
    (values {:first "cool"}))
  (insert address
    (values {:address1 "cool"})))

(transaction
  (if-not (valid?)
    (rollback)
    (do-complicated-query))
  (when-not (is-rollback?)
    (println "success!")))

