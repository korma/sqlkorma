(select users
 (fields :name)
 (with emails
   (fields :email) ;; the context is now email.*
   (where {:email [like "%_test%"]})))

(select users
  (fields :name)
  (where {:date_joined [>= today]})
  (with addresses
    (with state (fields :state)) ;; you can nest withs
    (fields :city :zip :address1 :address2)))
