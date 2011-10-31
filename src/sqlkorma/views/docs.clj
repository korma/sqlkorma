(ns sqlkorma.views.docs
  (:require [sqlkorma.views.common :as common]
            [sqlkorma.models.examples :as example])
  (:use [noir.core :only [defpage defpartial]]
        [hiccup.core :only [html]]
        [sqlkorma.views.common :only [code]]
        hiccup.page-helpers))

;;************************************************
;; section util
;;************************************************

(defpartial doc-bubble [{:keys [name id rows content]}]
  [:div#docBubble
    [:h2 {:id id} name]
    content])

(def _sections (atom []))

(defn add-section [id v]
  (swap! _sections conj [id v]))

(defn sections []
  (for [[_ s] @_sections]
    (s)))

(defn section-links []
  (for [[id] @_sections]
    [:li (link-to (str "#" id) id)]))

(defmacro section [id title & content]
  `(do
     (defn ~id []
       (doc-bubble {:id ~(name id)
                    :name ~title
                    :content (html ~@content)}))
     (add-section ~(name id) ~id)))

;;************************************************
;; sections
;;************************************************

(section db "db"
  (code :db-intro :full)
  [:p "To define a connection to a database you use the (" [:em "defdb"] ") macro, which takes a map
      of database parameters. Those familiar with clojure.java.jdbc will notice this is the
      same connection map you would use there. To speed things up a bit, Korma provides a set of 
      convenience functions for common database specs."]
  [:p "(" [:em "defdb"] ") creates a connection pool for your db spec (using the
      wonderful c3p0 library) to make sure resources are used efficiently. It also sets the last
      created pool as the default for all queries. As such, connection management is essentially 
      taken care of for you."])

(section entities "entities"
  (code :entities :full)
  (code :rel :full)
  [:p "Entities map one to one with tables and are the initial building block for all your
      queries."])

(section select "select queries"
  (code :selects :full)
  (code :where :full)
  (code :with :full)
  (code :select-fn :full)
  [:p "woot"])

(section update "update queries"
         (code :update :full)
  [:p "woot"])

(section insert "insert queries"
         (code :insert :full)
  [:p "woot"])

(section delete "delete queries"
         (code :delete :full)
  [:p "woot"])

(section misc "misc"
         (code :execmodes :full)
  [:p "Korma has the notion of execution modes. By default, when exec is called, it simply
      generates the SQL string and params necessary for your query and sends those to your
      database. Sometimes, however, what you really want is to just generate the string,
      or even do a dry run where you see the SQL printed to the console.
      Exec modes allow you to do exactly that."])

;;************************************************
;; pages 
;;************************************************

(defpage "/docs" []
         (example/init)
  (common/layout
    [:ul#docLinks
     (section-links)
     [:li#api (link-to "/api/0.2.0/index.html" "API")]]
    (sections)))


