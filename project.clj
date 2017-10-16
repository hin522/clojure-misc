(defproject clojure-misc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-time "0.8.0"]
                 [org.clojure/data.codec "0.1.0"]
                 [com.amazonaws/aws-java-sdk-kms "1.11.20"]
                 [com.mixpanel/mixpanel-java "1.4.4"]
                 [clj-librato "0.0.5"]
                 [clj-http "3.7.0"]]
  :main ^:skip-aot clojure-misc.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
