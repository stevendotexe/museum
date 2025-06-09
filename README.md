# Museum Management Software

Java based user viewing client and administrator manager for inventory and item management. 

## Features

### Admin Features
- **Dashboard**
  - View statistics for suggested items (pending, approved, rejected)
  - Monitor inventory status (total items, currently exhibited)
  - Quick access to management tools

- **Inventory Management**
  - Add, edit, and remove items
  - Update item status and exhibition status
  - Manage item categories and descriptions

- **Suggestion Management**
  - Review and process item suggestions
  - Approve or reject suggested items
  - Track suggestion status

### User Features
- **Homepage**
  - View highlighted museum items
  - Quick access to collections and suggestions
  - Search and filter functionality

- **Museum Collection**
  - Browse museum items
  - View detailed item information
  - Filter items by category
  - Search functionality

- **Item Suggestions**
  - Submit new item suggestions
  - Track suggestion status
  - View suggestion history

- **Displayed Items**
  - View currently exhibited items
  - Access detailed item information
  - Bookmark favorite items

## Technical Details

### Requirements
- Java JDK 8 or higher
- MySQL Database (Aiven)
- Apache Ant for building

### Database Structure
The application uses a MySQL database with the following main tables:
- `users` - User accounts and authentication
- `item` - Museum collection items
- `suggested_item` - Item suggestions from users
- `inventory` - Item inventory management

### Project Structure
```
src/
├── com/
│   └── app/
│       └── Main.java           # Application entry point
├── Components/
│   ├── Cards/                 # UI card components
│   └── Utilities/             # Utility classes
└── Pages/
    ├── Admin/                 # Admin interface pages
    ├── User/                  # User interface pages
    └── Login/                 # Authentication pages
```

## Getting Started

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   ```

2. **Set up the database**
   - Import the database schema from `/dataBackup`
   - Configure database connection in `DatabaseConnection.java`

3. **Build the project**
   ```bash
   ant build
   ```

4. **Run the application**
   ```bash
   ant run
   ```

## Default Accounts

### Admin Account
- Email: asd
- Password: asd

### User Account
- Email: asda
- Password: asd

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Java Swing for the GUI framework
- MySQL for database management
- Aiven for database hosting 