(defentity email)
(defentity address)
(defentity account)

(defentity users
  (has-one email)
  (has-many address)
  (belongs-to account))

(select users
  (fields :email.email :address.state :account.name)
  (with email)
  (with address)
  (with account))
