(defentity address
  (table :__addresses :address))

(defentity users
  (table :somecrazy_table_name :users)
  (pk :userID)
  (has-many address {:fk :userID}))

(select users
  (with address)
  (where {:last_login [< a-week-ago]}))

(select users
  (aggregate (count :*) :cnt)
  (where (or (> :visits 20)
             (< :last_login a-year-ago))))
