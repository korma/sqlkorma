;;You can insert a single value:
(insert users
  (values {:first "john" :last "doe"}))

;;or a collection of several:
(insert users
  (values [{:first "john" :last "doe"}
           {:first "jane" :last "doe"}]))

;;You can also compose inserts:
(def base (-> (insert* users)
            (values {:first "john" :last "doe"})))

(-> base
  (values {:first "jane" :last "doe"})
  (exec))
