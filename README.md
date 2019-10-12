
# Deployment

# Deploy static files to CDN. 
rsync -r /usr/local/src/computate-scolaire-static/ /usr/local/src/computate.org-static/scolaire/

# Certs
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

