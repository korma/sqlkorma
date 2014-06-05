;; First you'll need to add Korma as a dependency in your lein/cake project:
[korma "0.3.2"]

;; You'll also need the JDBC driver for your database. These are easy to find if 
;; you search for "my-db jdbc driver maven".
;; Example for postgres:
[org.postgresql/postgresql "9.2-1002-jdbc4"]
;; Example for h2:
[com.h2database/h2 "1.3.170"]
;; Example for sqlite:
[org.xerial/sqlite-jdbc "3.7.15-M1"]
