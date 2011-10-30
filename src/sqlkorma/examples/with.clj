(defentity address)
(defentity account)
(defentity email)

(defentity users
  (has-one email)
  (has-many address)
  (belongs-to account))

(select users
  (with address)
  (with email))


