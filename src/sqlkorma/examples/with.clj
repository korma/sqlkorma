(select users
 (fields :first)
 (with email
   (fields :email) ;; the context is now email.*
   (where {:email [like "%_test%"]})))

(select users
  (fields :first)
  (where {:last [like "doe"]})
  (with address
    (with state (fields :state)) ;; you can nest withs
    (fields :city :zip :address1 :address2)))