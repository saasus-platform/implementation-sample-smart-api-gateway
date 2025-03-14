# implementation-sample-smart-api-gateway

This is a Smart API Gateway implementation sample using the SaaSus SDK

See the documentation [Smart API Gateway Feature](https://docs.saasus.io/docs/api-gateway-function/overview)

## Requirements

- docker
- maven (latest)

## Setup SDK

```sh
# Run in any directory
git clone git@github.com:saasus-platform/saasus-sdk-java.git
cd saasus-sdk-java
mvn clean package
mvn install:install-file -Dfile=target/saasus-sdk-java-1.0.0.jar -DgroupId=io.saasus -DartifactId=saasus-java -Dversion=0.0.1 -Dpackaging=jar
```

## Run Java API

```sh
# Run in any directory
git clone git@github.com:saasus-platform/implementation-sample-smart-api-gateway.git
cd implementation-sample-smart-api-gateway
mvn clean package
```

```
cp .env.example .env
vi .env

# Set Env for SaaSus Platform API
# Get it in the SaaSus Admin Console
SAASUS_SAAS_ID="xxxxxxxxxx"
SAASUS_API_KEY="xxxxxxxxxx"
SAASUS_SECRET_KEY="xxxxxxxxxx"

# Save and exit
```

```
docker compose up -d
```
