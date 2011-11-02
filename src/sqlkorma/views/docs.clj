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
  [:p "Entities map one to one with tables and are the initial building block for all your
      queries. You have the ability to specify a number of properties associated with them,
      such as the table name, an alias for the table, the primary key and so on. You can
      also specify functions to be run before a record goes to the database as the result of
      an insert/update, or functions to be mapped over the results of a select. This allows you
      to apply common mutations to your data without having to think about them at every step."]
  [:p "Entities also allow you to specify the relationships to be used when you do select queries.
      With these relationships defined, you can then simply use the (" [:em "with"] ") function in
      your select query to join the entities and return the results."])

(section select "select queries"
  [:div
    (code :selects :full)
    [:p "woot"]]
  [:div
    (code :where :full)
    [:p "woot"]])

(section update "update queries"
         (code :update :full)
  [:p "Update queries use the (" [:em "set-fields"] ") function to specifiy the fields to be
      updated. Multiple calls to set-fields will be merged together, allowing you to build
      the update over time. Updates also allow where clauses as you would expect."])

(section insert "insert queries"
         (code :insert :full)
  [:p "Insert queries use the function (" [:em "values"] ") to add records. It takes either
      a single map or a collection of maps and returns the id of the first inserted record."])

(section delete "delete queries"
         (code :delete :full)
  [:p "Delete queries only allow where clauses and not including one will cause all
      records for the given entity to be deleted. The result of a delete is the 
      id of the first record deleted."])

(section misc "misc"
         (code :execmodes :full)
  [:p "By default, when exec is called, it simply generates the SQL string and params 
      necessary for your query and sends those to your database. Sometimes, however, what 
      you really want is to just generate the string, or even do a dry run where you see 
      the SQL printed to the console. As such, korma includes a couple of other execution
      modes that allow you to do exactly that."])

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


