(defentity users
  (pk :my_pk) ;; By default "id"
  (table :k_users) ;; By default the name of the symbol
  (entity-fields :first :last) ;; Default fields for selects
  (prepare ..) ;; apply a function before storing in the db
  (transform ..) ;; apply a function to all select results
  ;;Relationships
  (has-one email)
  (has-many address)
  (belongs-to account))
