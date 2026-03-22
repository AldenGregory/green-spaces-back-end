# greenspaces-backend

## Database Information:

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
| `latitude` | `decimal(11,8)` | Latitude where green space is located|
| `longitude` | `decimal(11,8)` | Longitude where green space is located|
| `description` | `text` | Description of green space |