(defdb prod (postgres {:db "korma"
                       :user "db"
                       :password "dbpass"}))

(defentity address)
(defentity user
  (has-one address))

(select user
  (with address)
  (fields :firstName :lastName :address.state)
  (where {:email "korma@sqlkorma.com"}))
