# SSLServerSocket
Voor de mensen die de security feature willen implementeren, vandaag is tijdens het practicum besloten om een SSLServerSocket te gaan gebruiken. Voor een uitgebreide tut over hoe dit te implementeren zie: http://www.javaworld.com/article/2075291/learn-java/build-secure-network-applications-with-ssl-and-the-jsse-api.html. De keystore die nodig is voor het opzetten van een SSL socket is te vinden in deze directory (keystore.jks). Voeg deze toe aan je key/trustStore (wachtwoord: SSR0CKS), om SSL met andere clients en servers te kunnen gebruiken. We snappen dat dit niet de way to go is, de keystore en wachtwoord zo op github zetten, maar voor nu is het even makkelijk.

Hint: bij mij werkte het niet om the trust/keyStore via de commandline te zetten, om het programmatisch toe doen voeg deze code toe voor het starten van de server/client:

        `// SERVER SIDE
        // Initialize and set certificate credentials for SSL connection
        System.setProperty("javax.net.ssl.keyStore", System.getProperty("user.dir").replace("src", "") + "/certs/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "SSR0CKS");

        // CLIENT SIDE
        // Set needed trustStore properties in order to connect to SSLSocket
        System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir").replace("src", "") + "/certs/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "SSR0CKS");`