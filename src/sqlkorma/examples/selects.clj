(select users
  (with address) ;; include other entities based on
                 ;; their relationship
  (fields [:firstname :first] :last :address.state)
      ;; you can alias a field using a vector of [field alias]
  (aggregate (count :*) :cnt :status) 
      ;; You specify alias and optionally a field to group by
      ;; available aggregates:
      ;; sum, first, last, min, max, avg, count
  (where {:first "john"
          :last [like "doe"]
          :date_joined [<= (sqlfn now)]})
      ;; You can use an abritrary sql function by calling
      ;; ( sqlfn fn-name & params)
  (order :id :ASC)
  (group :status)
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

