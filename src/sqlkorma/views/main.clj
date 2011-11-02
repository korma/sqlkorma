(ns sqlkorma.views.main
  (:require [sqlkorma.views.common :as common]
            [sqlkorma.models.examples :as example])
  (:use [noir.core :only [defpage defpartial]]
        hiccup.page-helpers))

(def rows [{:title "Composable from the ground up."
            :body "All the parts of a query in Korma can be composed at will.
                  This allows you to construct queries over time and in virtually
                  any way you can dream of."
            :code (example/get :composable)}
           {:title "Take the pain out of relations."
            :body "Korma entities handles relationships for you. Just specify
                  what kind of relationship it is and all the joining will be
                  done for you."
            :code (example/get :rel)}
           {:title "Built for the real world."
            :body "Real projects are never simple. As such, Korma was designed with
                  flexibility in mind. Couple that with fast query generation
                  and you have a library meant to play in the big leagues."
            :code (example/get :realworld)}])

(defpartial overview-row [{:keys [title body code]}]
  [:li
    [:div
      [:h3 title]
      [:p body]]
      [:code [:pre code]]])

(defpage "/" []
  (example/init)
  (common/layout
    [:div#intro
    [:code [:pre (example/get :intro)]]
    [:div
      [:h3 "Clojure is beautiful."]
      [:h3 "It can make SQL beautiful too."]
      [:p "Korma is a domain specific language for Clojure that takes the pain
          out of working with your favorite RDBMS. Built for speed and designed
          for flexibility, Korma provides a simple and intuitive interface to your
          data that won't leave a bad taste in your mouth."]]]
    [:div#overview
      [:h2 (image "/img/overview-header.png" "A quick overview")]
      [:ul
        (map overview-row rows)]]))
