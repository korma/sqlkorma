(has-one email) ;; assumes users.id = email.users_id

(has-many address) ;; assumes users.id = address.users_id
                   ;; but gets the results in a second query
                   ;; for each element
          
(belongs-to account) ;; assumes users.account_id = account.id
