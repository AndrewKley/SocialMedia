# SocialMedia

### Content

*   [Entities of DTOs](#Entities-of-DTOs)  
*   [Model layer entities](#model-layer-entities)
*   [UserService](#UserService)


### Entities of DTOs

|Name       | Description   |
|:---------:|:-------------:|
|RegistrationUserDTO -> String login, String password, String confirmPassword, List\<Role\> roles|Use when registering a user (methods of saving, updating)|
|RequestUserDTO -> String login, String password|Use to delete and search for a user|
|ResponseUserDTO -> String login, List\<Role\>|Use to return a response|
|JwtResponse -> String token|Return token for authenticated user|



### Model layer entities

|Name       |Description    |
|:---------:|:-------------:|
|User -> String login (primary key), String password, List\<Post\> post, List\<Role\> roles|Contains user data|
|Role -> String role|Contains a list of roles for a user|



### UserService
|Method name    |Description    |
|:-------------:|:-------------:|
|```Java public Iterable\<User\> findAllUser()```|Return all users
|`public Optional<User> findUserByLogin(String login)`|If the user exists, return itself, otherwise return null|
|`public User saveUser(RegistrationUserDTO user) throws RegistrationUserException`|If the password does not match, throw a RegistrationUserException with the message "Password mismatch". If such a user exists, throw a RegistrationUserException with the message "User exists". Otherwise, encode the password and save the user. The method returns the user|
|Something else|...|
