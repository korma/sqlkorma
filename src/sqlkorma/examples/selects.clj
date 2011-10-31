(select users
  (with address)
  (fields :first :last :address.state)
  (where {:first "john"
          :last [like "doe"]})
  (order :id :ASC)
  (limit 3)
  (offset 3))
