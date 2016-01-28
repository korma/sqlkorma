(select users
  (with address) ;; include other entities based on
                 ;; their relationship
  (with posts) ;; include a many-to-many relationship
               ;; you can use (with) for any relationship
               ;; defined in the entity
  (fields [:first :firstname] :last :address.zip)
      ;; you can alias a field using a vector of [field alias]
      ;; these fields will be selected in addition to those defined
      ;; using (entity-fields) in (defentity)
  (modifier "DISTINCT") ;; you can add a modifier
  (aggregate (count :*) :cnt :status)
      ;; You specify alias and optionally a field to group by
      ;; available aggregates:
      ;; sum, first, last, min, max, avg, stdev, count
  (where {:first "john"
          :last [like "doe"]
          :date_joined [<= (sqlfn now)]})
      ;; You can use an abritrary sql function by calling
      ;; (sqlfn fn-name & params)
  (join email (= :email.users_id :id))
      ;; You can do joins manually
  (where {:email.id [in (subselect email
                          (fields :id)
                          (where {:sent true}))]})
      ;; When necessary, you can use subselects in your
      ;; queries just like you would a normal select
  (order :id :ASC)
  (group :status)
  (having {:cnt [> 3]})
      ;; You can use having clauses with the same syntax used
      ;; by where clauses.
  (limit 3)
  (offset 3))

;;You can also compose select queries over time:
(def base (-> (select* users)
              (fields :first :last)
              (order :created)))

(-> base
    (with :email)
    (where (> :visits 20))
    (select))

