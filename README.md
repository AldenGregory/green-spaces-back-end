# Green Space Locator Back End

This is the backend for the Green Space Locator application. It's built with
Java and Spring Boot. This API provides green space data and supports
functionality used by the frontend, including generating routes, determining
green spaces reachable from a location in a specified amount of time, and
translating responses to supported languages.

## Tech Stack

- Java
- Spring Boot
- MySQL

## Running Locally:

1. Clone this repository.

2. In the file `src/main/resources/application.properties` ensure the active
profile is set to local with the following property:

```properties
spring.profiles.active=local
```

3. Set up your green spaces database as described in the Database Setup section
below.

4. Create the file `env.properties` in the project root.

5. Add the following properties to `env.properties`:

```properties
app.db_password=<your-database-password>
app.db_url=<your-database-url>
app.geoapify_api_key=<your-geoapify-api-key>
app.translate_service_credentials=<your-google-translate-service-credentials>
```

6. Run the application.

## Database Setup:

1. Create a MySQL database.

2. Create the `green_space_information` table using the schema below.

3. Add green space data to the table.

### Table: green_space_information

| Column Name | Data Type | Description |
| :--- | :--- | :--- |
| `id` | `INT` | Primary key, unique identifier for green spaces |
| `park_name` | `varchar(150)` | Name of green space |
| `street` | `varchar(100)` | Address where green space is located |
| `city` | `varchar(100)` | City where green space is located |
| `state` | `varchar(100)` | State where green space is located |
| `postal_code` | `varchar(20)` | Postal code where green space is located |
| `country` | `varchar(100)` | Country where green space is located |
| `latitude` | `decimal(11,8)` | Latitude where green space is located |
| `longitude` | `decimal(11,8)` | Longitude where green space is located |
| `description` | `text` | Description of green space |

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/green-spaces` | Retrieves all green spaces |
| `GET` | `/translate-description` | Translates a green space description into a supported language |
| `GET` | `/reachable-polygon` | Retrieves a GeoJSON polygon representing the area reachable within a given time using public transportation |
| `POST` | `/simple-route` | Generates a public transportation route to a green space |
| `GET` | `/weather` | Retrieves weather information for a location |