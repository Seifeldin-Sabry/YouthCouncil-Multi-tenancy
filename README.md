Seifeldin Sabry
Relja Mitrovic
Ruben Vergauwen
Max Etman
Ransford Addai

### Run Instructions

Make sure you have a postgres database called YouthCouncilDev for dev profile and YouthCouncil for prod profile.
The reason for picking postgres in dev was to easily check the database when things are persisted.

- make sure you are in the root directory of the project


-

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
