# PrimeTime

The applictaion provides an API to generate Prime Numbers.

· Application support two media types JSON and XMl and it can be switched by Request parameter **output** by setting value **json** or **xml**. If there is any invalid output then it falls back to JSON format. 
· Application has in-built caching to cache generated Prime Numbers; Work needs to be done in order to setup eviction policy and time to live. 
· Application supports two Prime number algorithms Sieve and Miller-Rabin. The algorith can be switched by setting Request parameter **algo** bt setting values **sieve** and **millerRabin**. In case of invalid or missing algo it falls back to Sieve.

## Build

Application uses gradle build tool and below command can be run  

```sh
gradlew clean build
```
## Run 

Below command will run the applicationon port 8090.
```sh
gradlew bootRun
```
## Test 
Use Postman, curl or any other Rest client

-- Http Request 
```sh
GET /primes/1000?algo=&amp;output= HTTP/1.1
Host: localhost:8090
Cache-Control: no-cache
```

--  Curl Request
```sh
curl -X GET \
  'http://localhost:8090/primes/10000?algo=&output=' \
  -H 'cache-control: no-cache'
```