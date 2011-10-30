(delete users
  (where {:last_login [< last-year]}))

;;You can also compose deletes:
(def base (-> (delete* users)
            (where {:last_login [< last-year]})))

(-> base
  (where {:status [not= "active"]})
  (exec))
