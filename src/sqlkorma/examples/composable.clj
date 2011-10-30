(def base (-> (select* "user")
            (fields :id :username)
            (where {:email [like "*@gmail.com"]})))

(def ordered-and-active (-> base
                          (where {:active true})
                          (order :created)))

(def constrained (-> ordered-and-active
                   (limit 20)))

(exec constrained)
