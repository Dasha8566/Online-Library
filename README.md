# Digital Library Management Web Application

The local library wants to transition to digital bookkeeping for better management of books and readers. You are tasked with creating a web application to facilitate this transition. The library staff should be able to register readers, lend books to them, and track returned books effectively through this system.

## Required Functionality

The web application should provide the following functionality:

1. Pages for adding, editing, and deleting reader profiles.
2. Pages for adding, editing, and deleting book records.
3. A page displaying a list of all readers (with clickable links to individual reader pages).
4. A page displaying a list of all books (with clickable links to individual book pages).
5. An individual reader page displaying their details and the books they have borrowed. If a reader has not borrowed any books, display the message "This reader has not borrowed any books yet."
6. An individual book page displaying its details and the name of the reader who has borrowed it. If the book is available, display the message "This book is available."
7. On the book's page, if the book is currently borrowed by a reader, there should be a button labeled "Release Book." This button can be clicked by a librarian to mark the book as returned. After clicking, the book should become available again and should no longer be listed under the borrower's books.
8. On the book's page, if the book is available, there should be a dropdown list containing all readers' names and a button labeled "Assign Book." This button can be clicked by a librarian when a reader wants to borrow the book. After clicking, the book should be assigned to the selected reader and should appear in their list of borrowed books.
9. All input fields should be validated using `@Valid` and Spring Validator when necessary.

### Tasks

1. Rewrite then this project using Hibernate and Spring Data JPA. There should be no direct SQL queries in your project. Implement the entities (`@Entity`), repositories, and services for the Book and Person entities. The `PersonDAO` and `BookDAO` should remain empty and should not be used; all database interactions should be handled through services.

### New Functionality

1. Pagination: Modify the `index()` method in the `BooksController` to accept two query parameters: `page` and `books_per_page`. The `page` parameter indicates which page of books to retrieve, and the `books_per_page` parameter determines how many books should be displayed per page. Page numbering starts from 0. If these query parameters are not provided, the endpoint should return all books.

2. Sorting: Extend the `index()` method in the `BooksController` to accept a query parameter `sort_by_year`. If this parameter is set to `true`, the books should be returned in ascending order of publication year. If this parameter is not provided, books should be returned in their default order.

3. Book Search Page: Create a search page at `/books/search` where users can enter the initial letters of a book's title. The page should display the full book title and the author's name. If the book is currently borrowed, display the borrower's name. If no book matches the search criteria, display the message "No books found." Implement the necessary controller methods to support this functionality.

4. Overdue Book Check: On the reader's page (`/people/id`), implement a feature that highlights books in red if they have been borrowed for more than 10 days without being returned. When a book is marked as returned, it should no longer be highlighted as overdue.

Pagination and Sorting can work simultaneously if all three parameters are provided in the query.



