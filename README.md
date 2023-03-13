Seifeldin Sabry
Relja Mitrovic
Ruben Vergauwen
Max Etman
Ransford Addai

### Run Instructions

- make sure you are in the root directory of the project

```bash
- dev: `./gradlew bootRun --args='--spring.profiles.active=dev'`
- prod: `./gradlew bootRun --args='--spring.profiles.active=prod --POSTGRES_USERNAME=yourusername --POSTGRES_PASSWORD=yourpassword'` (replace `yourpassword` with the password you set for the postgres user and `yourusername` with the username you set for the postgres user)
```