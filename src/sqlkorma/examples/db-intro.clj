(use 'korma.db)

(defdb prod (postgres {:db "korma"
                       :user "korma"
                       :password "kormapass"
                       ;;OPTIONAL KEYS
                       :host "myhost"
                       :port "4567"
                       :delimiters "" ;; remove delimiters
                       :naming {:keys string/lower-case
                                ;; set map keys to lower
                                :fields string/upper-case}}))
                                ;; but field names are upper

;;Helpers for common databases:
(defdb pg (postgres ..))
(defdb ms (mssql ..))
(defdb orc (oracle ..))
(defdb mys (mysql ..))
(defdb sqll (sqlite3 ..))

;; Or you can simply pass in a jdbc database specification
(defdb mydb {:classname   "org.h2.Driver"
             :subprotocol "h2"
             :subname "tcp://localhost:9092/Sample"
             :user     "korma"
             :password "kormapass"})

