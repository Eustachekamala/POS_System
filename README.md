POS System
==========

A REST API backend for a Point of Sale (POS) application. The system is built with Spring Boot and PostgreSQL, and is designed to support product catalog management, inventory tracking, sales processing, and payment handling for retail operations.

This project follows a layered architecture with clear separation between controllers, services, repositories, and domain entities. Interactive API documentation is provided through OpenAPI (Swagger UI).


Overview
--------

The POS System provides HTTP endpoints for managing business data required at the point of sale. The domain model covers categories, products, suppliers, users, stock levels, sales transactions, and payments.

Category management is fully implemented with CRUD operations and name-based search. Additional domain entities and repositories are in place for products, suppliers, users, sales, and related resources; their service and controller layers are planned for subsequent development.


Tech Stack
----------

| Technology        | Version / Detail              |
|-------------------|-------------------------------|
| Java              | 17                            |
| Spring Boot       | 4.1.0                         |
| Spring Data JPA   | Hibernate with PostgreSQL     |
| PostgreSQL        | Runtime database              |
| Lombok            | Boilerplate reduction         |
| Spring Validation | Request payload validation    |
| SpringDoc OpenAPI | 3.0.3 (Swagger UI)            |
| Maven             | Build and dependency management |


Features
--------

- [x] **Category** - Product groupings with name and description
- [ ] **Product** - Catalog items linked to a category and supplier, with pricing, barcode, quantity, and expiry date
- [ ] **Supplier** - Vendor contact and address information
- [ ] **User** - System users with role-based access (Admin, Manager, Cashier, Customer)
- [ ] **Stock** - Inventory levels per product with minimum quantity thresholds
- [ ] **Sale** - Sales transactions with discount, payment method, and status
- [ ] **SaleItem** - Line items within a sale
- [ ] **Payment** - Payment records associated with sales


Architecture
------------

```
Client
  |
  v
Controllers   (REST endpoints, request/response handling)
  |
  v
Services      (Business logic)
  |
  v
Repositories  (Spring Data JPA)
  |
  v
PostgreSQL
```

Supporting packages:

- **DTO** - Request and response data transfer objects with validation constraints
- **Mappers** - Conversion between entities and DTOs
- **Exceptions** - `BusinessException` and `GlobalException` for consistent error responses
- **Helpers** - Enumerations for roles, payment methods, and payment status

Roadmap
-------

- Product and supplier management APIs
- User authentication and role-based authorization
- Sales and payment processing endpoints
- Stock management and low-inventory alerts
- Expanded test coverage and API documentation examples


License
-------

This project is provided as-is for educational and development purposes. Add a license file if you intend to distribute or open-source the codebase.
