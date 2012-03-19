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

(select users
  (fields [(sqlfn avg (sqlfn sum 3 4)) :cool]))

(select users
  (where (and (> 3 :id)
              (> 2 :soidf))))
