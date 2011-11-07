(select users
 (with emails ;;has-many
   (fields :email)
   (where {:emails [like "%_test%"]}))
 (fields :name))

(select users
  (with addresses ;;has-many
    (with state (fields :state))
    (fields :city :zip :address1 :address2))
  (fields :name)
  (where {:date_joined [>= today]}))




(def constraints [[:userid 1 '=] [:carid 34 '<]]) 
(def constraint-map (reduce (fn [result [field value op]]
                              (assoc result field [op value]))
                            {}
                            constraints))
(select mytable
  (where constraint-map))
