(use 'korma.db)

(defdb prod (postgres {:db "korma"
                       :host "myhost" ;; optional
                       :port "4567" ;; optional
                       :user "korma"
                       :password "kormapass"}))
