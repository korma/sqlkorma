(use 'korma.core)
(require '[clojure.string :as str])

(declare users email address state account posts)

(defentity users
  ;; Basic configuration
  (pk :id) ;; by default "id". This line is unnecessary.
           ;; it's used for relationships joins.
  (table :users) ;; by default the name of the symbol.
                 ;; The line above is also unecessary.
  (database db) ;; if none is specified the last defdb
                ;; will be used. Also unnecessary.
  (entity-fields :first :last) ;; default fields for selects

  ;; Mutations
  (prepare (fn [{last :last :as v}]
             (if last
               (assoc v :last (str/upper-case last)) v)))
  ;; apply a function before storing in the db
  ;; in this case the function changes the "last" field
  ;; to upper case.
  (transform (fn [{first :first :as v}]
               (if first
                 (assoc v :first (str/capitalize first)) v)))
  ;; apply a function to all select results
  ;; in this case the function changes the "first" field
  ;; to capitalized.

  ;; Relationships
  (has-one address)
      ;; assumes users.id = address.users_id
  (has-many email)
      ;; assumes users.id = email.users_id
      ;; but gets the results in a second query
      ;; for each element
  (belongs-to account)
      ;; assumes users.account_id = account.id
  (many-to-many posts :users_posts))
      ;; assumes a table users_posts with columns users_id
      ;; and posts_id
      ;; like has-many, also gets the results in a second
      ;; query for each element
      ;; does not allow a where clause in the users_posts
      ;; table, a combination of has-many and belongs-to
      ;; should be used instead if that is necessary

;; Subselects can be used as entities too!
(defentity subselect-example
  (table (subselect users
            (where {:active true}))
         :activeUsers))

(defentity email
  (belongs-to users))

(defentity address
  (pk :my_pk) ;; sets the primary key to "my_pk"
  (belongs-to users)
  (belongs-to state {:fk :id_state}))
      ;; you can optionally specify the foreign key
      ;; assumes state.id = address.id_state

(defentity state
  (table :state_st) ;; sets the table to "state_st"
  (has-many address))

(defentity account
  (has-one users))

(defentity posts
  (many-to-many users :users_posts
    {:lfk :posts_id :rfk :users_id})
      ;; you can optionally specify the foreign keys
      ;; left foreign key is for the main entity and
      ;; right foreign key is for the joined entity
      ;; the used values are the same as the defaults
