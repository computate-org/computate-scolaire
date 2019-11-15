
# Prerequisites

## What is the first step to creating my own website?
### Choose a domain name. 
https://www.computate.org/enUS/course/001/001-choose-domain-name

## What is the best operating system to develop my website?
### Try my favorite, CentOS 7. 
https://www.computate.org/enUS/course/001/002-choose-your-operating-system

## How do I try CentOS 7 Linux without replacing my operating system?
### Try CentOS 7 on a VirtualBox virtual machine. 
https://www.computate.org/enUS/course/001/003-try-linux-on-a-virtual-machine-on-virtual-box

## How do I install the operating system to start developing my website?
### Every step to install the CentOS 7 operating system. 
https://www.computate.org/enUS/course/001/004-how-to-install-centos7

## How do I install the latest version of maven.
### Install maven from the source code. 
https://www.computate.org/enUS/course/001/004-how-to-install-the-latest-version-of-maven

## How do I install development tools to develop my website?
### How to install Eclipse IDE the open source way. 
https://www.computate.org/enUS/course/001/006-how-to-install-eclipse

## How do I install the latest version of PostgreSQL.
### Install PostgreSQL from the source code.
https://www.computate.org/enUS/course/001/007-how-to-install-the-latest-version-of-postgresql

## What can I do once I have purchased a domain name?
### Obtain a valid TLS certificate for free, for security and credibility. 
https://www.computate.org/enUS/course/001/008-how-to-obtain-free-tls-certificates

## How do I try out clustered configuration, requests, events and shared data sources in development?
### Install Zookeeper from the source code. 
https://www.computate.org/enUS/course/001/009-how-to-install-the-latest-version-of-zookeeper

## How do I try out a search engine in my project?
### Install Solr from the source code. 
https://www.computate.org/enUS/course/001/010-how-to-install-a-recent-version-of-solr-search-engine

## How do I try out some user management software for single sign on applications? 
### Install Keycloak from the source code. 
Currently a work in progress. 

# Installation

```bash
curl https://raw.githubusercontent.com/computate/computate/master/bin/frFR/installer-computate.sh -o ~/Downloads/installer-computate.sh
bash ~/Downloads/installer-computate.sh
```

# Deployment
Sample OpenShift deployment yml file to be placed in /usr/local/src/computate-scolaire/src/main/fabric8/deployment.yml: 

```yaml
spec:
  strategy:
    activeDeadlineSeconds: 21600
    recreateParams:
      timeoutSeconds: 3600
    resources: {}
    type: Recreate
  template:
    spec:
      containers:
        - name: vertx
          env:
            - name: KUBERNETES_NAMESPACE
              valueFrom:
                fieldRef:
                  apiVersion: v1
                  fieldPath: metadata.namespace
            - name: JAVA_OPTIONS
              value: '-Dvertx.cacheDirBase=/tmp -Dvertx.jgroups.config=default'
            - name: langueNom
              value: frFR
              
            - name: zookeeperHostName
              value: ...
              
            - name: zookeeperPort
              value: 8080
              
            - name: clusterPort
              value: 8081
              
            - name: appliChemin_enUS
              value: /usr/local/src/computate-scolaire
              
            - name: domainName
              value: computate.org
              
            - name: companyName
              value: computate.org
              
            - name: jdbcUrl
              value: "jdbc:postgresql://...:5432/scolaire_enus"
              
            - name: jdbcUsername
              value: computate
              
            - name: jdbcPassword
              value: "..."
              
            - name: siteHostName
              value: school.computate.org
              
            - name: siteBaseUrl
              value: https://school.computate.org
              
            - name: authRealm
              value: COMPUTATE.ORG
              
            - name: authResource
              value: computate.org
              
            - name: authSecret
              value: ...
              
            - name: authUrl
              value: https://sso.computate.org/auth
              
            - name: authSslRequired
              value: all
              
            - name: sslJksPath
              value: /srv/heytate.com/server.jks
              
            - name: sslJksPassword
              value: "..."
              
            - name: sitePort
              value: 8080
              
            - name: solrUrl
              value: http://...:8080/solr/scolaire_enUS
              
            - name: apiContactMail
              value: ...
              
            - name: staticBaseUrl
              value: https://computate.neocities.org/scolaire
              
            - name: numberExecutors
              value: 5
              
            - name: siteZone
              value: America/New_York

          readinessProbe:
            httpGet:
              path: /health
              port: 8080
              scheme: HTTP
            initialDelaySeconds: 15
            timeoutSeconds: 5
          livenessProbe:
            httpGet:
              path: /health
              port: 8080
              scheme: HTTP
            initialDelaySeconds: 15
            timeoutSeconds: 5
          ports:
          - containerPort: 8080
            name: http
            protocol: TCP
          - containerPort: 8081
            name: cluster
            protocol: TCP
          - containerPort: 9779
            name: prometheus
            protocol: TCP
          - containerPort: 8778
            name: jolokia
            protocol: TCP

```

# Deploy static files to CDN. 

```bash
rsync -r /usr/local/src/computate-scolaire-static/ /usr/local/src/computate.org-static/scolaire/
```

# Certs

```bash
sudo yum install -y letsencrypt
curl https://letsencrypt.org/certs/isrgrootx1.pem.txt -o /usr/local/src/computate-scolaire/config/root.crt
curl https://letsencrypt.org/certs/lets-encrypt-x3-cross-signed.pem.txt -o /usr/local/src/computate-scolaire/config/ca1.crt
curl https://letsencrypt.org/certs/letsencryptauthorityx3.pem.txt -o /usr/local/src/computate-scolaire/config/ca2.crt
sudo certbot -d demo.heytate.com --manual --preferred-challenges dns certonly --server https://acme-v02.api.letsencrypt.org/directory
sudo cp /etc/letsencrypt/live/demo.heytate.com/privkey.pem /usr/local/src/computate-scolaire/config/server.key
sudo cp /etc/letsencrypt/live/demo.heytate.com/fullchain.pem /usr/local/src/computate-scolaire/config/server.crt
sudo chown $USER: /usr/local/src/computate-scolaire/config/server.* /usr/local/src/computate-scolaire/config/root.crt
cat /usr/local/src/computate-scolaire/config/root.crt /usr/local/src/computate-scolaire/config/server.crt > /usr/local/src/computate-scolaire/config/merged.crt
openssl pkcs12 -export -in /usr/local/src/computate-scolaire/config/merged.crt -inkey /usr/local/src/computate-scolaire/config/server.key -out /usr/local/src/computate-scolaire/config/server.p12 -name demo.heytate.com
keytool -importkeystore -srckeystore /usr/local/src/computate-scolaire/config/server.p12 -destkeystore /usr/local/src/computate-scolaire/config/server.jks -srcstoretype pkcs12 -deststoretype pkcs12 -alias demo.heytate.com -destalias demo.heytate.com
rm /usr/local/src/computate-scolaire/config/server.jceks
keytool -genseckey -alias demo.heytate.com -storetype JCEKS -keystore /usr/local/src/computate-scolaire/config/server.jceks
# Reconfigure the Red Hat SSO secret:
base64 /usr/local/src/computate-scolaire/config/server.jks | perl -pe'chomp'
base64 /usr/local/src/computate-scolaire/config/server.jceks | perl -pe'chomp'
# Configure the www.computate.org route:
cat /usr/local/src/computate-scolaire/config/server.crt
cat /usr/local/src/computate-scolaire/config/server.key
cat /usr/local/src/computate-scolaire/config/ca1.crt
```

