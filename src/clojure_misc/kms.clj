(ns kms
  (:require [clojure.data.codec.base64 :as b64])
  (:import (com.amazonaws.services.kms AWSKMSClientBuilder)
           (com.amazonaws.services.kms.model DecryptRequest EncryptRequest)
           (java.util Base64)
           (java.nio ByteBuffer)))

(defn encrypt [text key-id]
  "Encrypt a plain text using AWS KMS key"
  (let [text-byte (.getBytes text)
        buffer (.flip (.put (ByteBuffer/allocate (.length text)) text-byte))
        request (.withPlaintext (.withKeyId (new EncryptRequest) key-id) buffer)
        kms-client  (AWSKMSClientBuilder/defaultClient)]
    (.getCiphertextBlob (.encrypt kms-client request))))

(defn- convert-cipher-text-to-byte-buffer [cipher-text]
  (-> cipher-text
      .getBytes
      b64/decode
      ByteBuffer/wrap))

(defn- create-decrypt-request [byte-buffer]
  (.withCiphertextBlob (new DecryptRequest) byte-buffer))

(defn decrypt-cipher-text [cipher-text]
  "Decrypt a AWS KMS encrypted cipher text to plain text"
  (let [kms-client (AWSKMSClientBuilder/defaultClient)]
    (->> cipher-text
         convert-cipher-text-to-byte-buffer
         create-decrypt-request
         (.decrypt kms-client)
         .getPlaintext
         .array
         (map char)
         (apply str))))