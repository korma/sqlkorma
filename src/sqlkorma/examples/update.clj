(update users
  (set-fields {:status "active"
               :beta false})
  (where {:visits [> 10]}))

;; You can also compose updates over time:
(def base (-> (update* users)
              (set-fields {:status "active"})))

(-> base
    (set-fields {:beta false})
    (where {:visits [> 10]})
    (update))
;; Does the same thing 