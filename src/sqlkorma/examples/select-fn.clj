(def base (-> (select* users)
            (fields :first :last)
            (order :created)))

(-> base
  (fields :email)
  (where (> :visits 20))
  (exec))
