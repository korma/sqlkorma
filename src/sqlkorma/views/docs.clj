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
  [:p "blah blah"]
  (code :db-intro :full)
  (code :db-types :full))

(section entities "entities"
  (code :entities :full)
  (code :rel :full)
  [:p "woot"])

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
  [:p "woot"])

;;************************************************
;; pages 
;;************************************************

(defpage "/docs" []
  (common/layout
    [:ul#docLinks
     (section-links)
     [:li#api (link-to "/api/0.2.0/index.html" "API")]]
    (sections)))


