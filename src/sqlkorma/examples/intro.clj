(defdb prod (postgres {:db "korma"
                       :username "db"
                       :password "dbpass"}))

(defentity address)
(defentity user
  (has-many address))

(select user
  (with address)
  (fields :firstName :lastName :address.state)
  (where {:email "korma@sqlkorma.com"}))
