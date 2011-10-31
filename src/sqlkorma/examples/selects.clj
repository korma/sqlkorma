(select users
  (with address) ;; include other entities based on
                 ;; their relationship
  (fields :first :last :address.state)
  (where {:first "john"
          :last [like "doe"]}) 
  (order :id :ASC)
  (limit 3)
  (offset 3))

;;You can also compose select queries over time:
(def base (-> (select* users)
            (fields :first :last)
            (order :created)))

(-> base
  (fields :email)
  (where (> :visits 20))
  (exec))

