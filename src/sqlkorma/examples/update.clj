(update users
  (set-fields {:status "active"
               :beta false})
  (where {:last_login [> yesterday]}))

;;You can also compose updates over time:
(def base (-> (update* users)
            (set-fields {:status "active"})))

(-> base
  (set-fields {:beta false})
  (where {:last-login [> yesterday]})
  (update))
