(delete users
  (where {:visits [< 3]})) 

;;You can also compose deletes:
(def base (-> (delete* users)
            (where {:visits [< 3]})))

(-> base
  (where {:status [not= "active"]})
  (delete))