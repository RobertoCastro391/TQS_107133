Feature: Book Search

    Background:
        Given that the library has the following books:
            | Title           | Author            | Published     |
            | The Great Gatsby | F. Scott Fitzgerald | 1925         |
            | The Da Vinci Code | Dan Brown          | 2003         |
            | The Catcher in the Rye | J.D. Salinger | 1951      |
            | The Hobbit      | J.R.R. Tolkien     | 1937         |

    
    Scenario: Searching for a book by title
        When I search for the book having the title "The Great Gatsby"
        Then the book found should have the title "The Great Gatsby"

    Scenario: Searching for a book by author
        When I search for a book written by "F. Scott Fitzgerald"
        Then the book found should have the author "F. Scott Fitzgerald"

    Scenario: Searching for a book by publication date
        When I search for books published between 1930-09-12 and 1960-11-10
        Then I should find 2 books
