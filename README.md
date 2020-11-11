# Friends Suggester

### SET UP
```
1 git clone https://github.com/knayamlohani/friend-suggester.git
2 cd friend-suggester
3 mvn clean install
4 java -jar target/friend-suggester-1.0.0.jar
```



### APIs

1 -  add user
```
curl --location --request POST 'http://localhost:9191/friend-suggestor/api/v1/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "b",
    "firstName": "",
    "lastName": "",
    "email": ""
}'
```
only username is mandatory


2 - send friend request from user with user-name a to user with user-name b
```
curl --location --request POST 'http://localhost:9191/friend-suggestor/api/v1/user/add/a/b'
```

3 - get friends of user with user-name a 
```
curl --location --request GET 'http://localhost:9191/friend-suggestor/api/v1/user/friends/a'
```
4 - get pending friend requests of user with user-name a 
```
curl --location --request GET 'http://localhost:9191/friend-suggestor/api/v1/user/friendRequests/a'
```

5 -  get  friend suggestions for user with user-name a 
```
curl --location --request GET 'http://localhost:9191/friend-suggestor/api/v1/user/suggestions/a'
```
### Controllers

* UserController


### Services

* UserService
* UserValidatorService
* UserHelperService
* InMemoryDataService
* ExceptionHelperService


### Aspects

* ExceptionHandler


### all the data is kept in memory and is lost on application restart
