package ua.deti.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();
    static final Logger log = getLogger(lookup().lookupClass());

    @DataTableType
    public Book bookEntry(Map<String, String> entry) {
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(entry.get("Published")), 1, 1, 0, 0);
        Date newDate = Date.from(ldt.toInstant(ZoneOffset.UTC));
        return new Book(entry.get("Title"), entry.get("Author"), newDate);
    }

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public Date date(String year, String month, String day) {
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
        return Date.from(ldt.toInstant(ZoneOffset.UTC));
    }

    @Given("that the library has the following books:")
    public void givenTheLibraryHasTheFollowingBooks(List<Book> books) {
        for (Book book : books) {
            library.addBook(book);
        }
    }

    @When("I search for the book having the title {string}")
    public void whenISearchForTheBookByTitle(String title) {
        log.debug("Searching for book with title: " + title);
        result = library.findBooksByTitle(title);
    }

    @Then("the book found should have the title {string}")
    public void thenTheBookFoundShouldHaveTheTitle(String expectedTitle) {
        log.debug("Checking if the found book has the title: " + expectedTitle);
        assertEquals(1, result.size(), "Exactly one book should be found.");
        assertEquals(expectedTitle, result.get(0).getTitle(), "The found book should have the expected title.");
    }

    @When("I search for a book written by {string}")
    public void whenISearchForABookWrittenBy(String author) {
        log.debug("Searching for book written by: " + author);
        result = library.findBooksByAuthor(author);
    }

    @Then("the book found should have the author {string}")
    public void thenTheBookFoundShouldHaveTheAuthor(String expectedAuthor) {
        log.debug("Checking if the found book has the author: " + expectedAuthor);
        assertEquals(1, result.size(), "Exactly one book should be found.");
        assertEquals(expectedAuthor, result.get(0).getAuthor(), "The found book should have the expected author.");
    }

    @When("I search for books published between {date} and {date}")
    public void whenISearchForBooksPublishedBetweenAnd(Date fromDate, Date toDate) {
        log.debug("Searching for books published between " + fromDate + " and " + toDate);
        result = library.findBooks(fromDate, toDate); 
    }

    @Then("I should find {int} books")
    public void thenIShouldFindBooks(int expectedCount) {
        log.debug("Checking if " + expectedCount + " books were found");
        assertEquals(expectedCount, result.size(), "The expected number of books should be found.");
    }
}