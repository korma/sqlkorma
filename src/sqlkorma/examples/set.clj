;; Using set operations
(union 
  (queries (subselect users
             (where {:id 7}))
           (subselect users
             (where {:id 1}))
           (subselect users
             (where {:id 1})))
  (order :first))
;; union operations remove duplicates

;; You can compose set operations:
(def base (-> (union*)
              (queries (subselect users
                         (where {:id 7})))))
(-> base
    (queries (subselect users
               (where {:id 1})))
    (queries (subselect users
               (where {:id 1})))
    (exec))
;; Same result as above

(union-all
  (queries (subselect users
             (where {:id 7}))
           (subselect users
             (where {:id 1}))
           (subselect users
             (where {:id 1})))
  (order :first))
;; union-all do not remove duplicates.
;; You can compose union-all with (union-all*)

(intersect
  (queries (subselect users
             (where {:id [in [1 7]]}))
           (subselect users
             (where {:id 1}))
           (subselect users
             (where {:id 1})))
  (order :first))
;; intersect only keeps what is in all queries.
;; You can compose intersect with (intersect*)