Seifeldin Sabry
Relja Mitrovic
Max Etman
Ransford Addai

### Env file setup

1. make sure you have plugin EnvFile installed in your IDE
2. create a file called .env in the root directory of the project
3. edit run configurations and enable env
4. select .env file in the env file dropdown

### Run Instructions

Make sure you have a postgres database called YouthCouncilDev for dev profile and YouthCouncil for prod profile.
The reason for picking postgres in dev was to easily check the database when things are persisted.

- make sure you are in the root directory of the project

dev: `./gradlew bootRun --args='--spring.profiles.active=dev' --POSTGRES_USERNAME=yourusername --POSTGRES_PASSWORD=yourpassword' (replace `
yourpassword` with the password you set for the postgres user and `
yourusername` with the username you set for the postgres user)`

-

prod: `./gradlew bootRun --args='--spring.profiles.active=prod --POSTGRES_USERNAME=yourusername --POSTGRES_PASSWORD=yourpassword'` (
replace `yourpassword` with the password you set for the postgres user and `yourusername` with the username you set for
the postgres user)

Users for the dev profile are:

username: `admin`
password: `pass`
role: `ADMIN`
<br>
username: `yc_admin`
password: `pass`
role: `YC_ADMIN`
<br>
username: `mod`
password: `pass`
role: `MODERATOR`
<br>
username: `user`
password: `pass`
role: `USER`

### Password Administrator Members creation

For now its hardcoded to `password`

How to add an admin

1. You need to fill in the SQL an insert statment for the user you want to add
2. `INSERT INTO app_users (email, username, password, role, provider) VALUES (email, username, password, role, provider) VALUES (<email>, <username>, <password>, 'ADMINISTRATOR', 'LOCAL');`
3. for the password go to website [BCrypt](https://www.browserling.com/tools/bcrypt) and generate a password
4. copy the generated password and paste it in the insert statement

example
admin: `INSERT INTO app_users (email, username, password, role, provider) VALUES ('admin@admin.co', 'admin', '$2a$10$3/cFBOh7pajctdMKJke2DuejPelAV8Ikt90BFcsvx/yUigAkP5zPO', 'ADMINISTRATOR', 'LOCAL');
`

#### Important notes about deployment

1. The first deploy must have ddl-auto set to create in the application.properties file
2. After the first deploy, ddl-auto must be set to update in the application.properties file, and the scripts to insert
   must be commented out
3. Now any update will just update the schema, and not drop the tables and recreate them
4. All municipalities should also be registered and their postcode too as a result
