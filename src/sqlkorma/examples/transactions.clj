;;transaction, rollback, and is-rollback? are in korma.db
(transaction
  (insert users
    (values {:name "cool"}))
  (insert address
    (values {:username "cool"})))

(transaction
  (if-not (valid?)
    (rollback)
    (do-complicated-query))
  (when-not (is-rollback?)
    (println "success!")))

