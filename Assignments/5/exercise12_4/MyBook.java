/**
 * MyBook is a program that provides a representation for various types of books
 * 
 * @author Nafisa Tabassum <a href=
 *         "mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */

// Book representation
abstract class Book {
    private String isbn;
    private int publicationYear;
    private int pages;

    public Book(String isbn, int pages) {
        this.isbn = isbn;
        this.pages = pages;
    }

    public Book() {

    }

    // Retrieves ISBN of book
    public String getIsbn() {
        return this.isbn;
    }

    // Retrieves publication year of book
    public int getPublicationYear() {
        return this.publicationYear;
    }

    // Retrieves the number of pages in book
    public int getPages() {
        return this.pages;
    }

    // Sets the ISBN of the book
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Sets the publication year of the book
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Sets the number of pages the book contains
    public void setPages(int pages) {
        this.pages = pages;
    }

}

// Information regarding paperback books
abstract class Paperback extends Book {
    public Paperback(String isbn, int pages) {
        super(isbn, pages);
    }

    public Paperback() {
        super();
    }

    // Represents the cover art for the paperback book
    public String coverArt() {
        return "Method coverArt called from Paperback";
    }
}

// Information regarding hardcover books
abstract class Hardcover extends Book {
    public Hardcover(String isbn, int pages) {
        super(isbn, pages);
    }

    public Hardcover() {
        super();
    }

    // Represents hardcover binding
    public abstract void binding();
}

// Information regarding hardcover classic books
class Classic extends Hardcover {
    private int origPubYear = 1860;
    private Author theAuthor;
    private Publisher[] bookPublisher = new Publisher[10];

    public Classic(String isbn, int pages) {
        super(isbn, pages);
    }

    public Classic() {
        super();
    }

    // Plot breakdown and notes for classic literature
    public String createNotes() {
        return "Method createNotes called from Classic";
    }

    // Hardcover binding
    public void binding() {

    }

    // Retrieves the original publishing year
    public int getOrigPubYear() {
        return this.origPubYear;
    }

    // Retrieves the author's name
    public Author getTheAuthor() {
        return this.theAuthor;
    }

    // Retrieves the publisher name
    public Publisher[] getBookPublisher() {
        return this.bookPublisher;
    }

    // Sets the original publishing year
    public void setOrigPubYear(int origPubYear) {
        this.origPubYear = origPubYear;
    }

    // Sets the author's name
    public void setTheAuthor(Author theAuthor) {
        this.theAuthor = theAuthor;
    }

    // Sets the book publisher
    public void setBookPublisher(Publisher[] bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}

// Defines the category of the book
class Category {
    private Category subCategory;
    private Category superCategory;
    private String category;

    // Sorts the books according to category
    public String sort() {
        return "Method sort called from Category";
    }

    // Retrieves the subcategory of the book, if any
    public Category getSubCategory() {
        return this.subCategory;
    }

    // Retrieves the super category of the book, if any
    public Category getSuperCategory() {
        return this.superCategory;
    }

    // Retrieves the main book category
    public String getCategory() {
        return this.category;
    }

    // Sets the subcategory of the book
    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    // Sets the super category of the book
    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    // Sets the main category of the book
    public void setCategory(String category) {
        this.category = category;
    }
}

// Information regarding nonfiction paperback books
class Nonfiction extends Paperback {
    private Category deweyClassification;

    public Nonfiction(String isbn, int pages) {
        super(isbn, pages);
    }

    public Nonfiction() {
        super();
    }

    // Book topic
    public String topic() {
        return "Method topic called from Nonfiction";
    }

    // Retrieves dewey classification
    public Category getDeweyClassification() {
        return this.deweyClassification;
    }

    // Sets dewey classification
    public void setDeweyClassification(Category deweyClassification) {
        this.deweyClassification = deweyClassification;
    }
}

// Information regarding fiction paperback books
abstract class Fiction extends Paperback {
    // Book coverart
    public abstract String coverArt();

    public Fiction(String isbn, int pages) {
        super(isbn, pages);
    }

    public Fiction() {
        super();
    }

    // Book genre
    public String genre() {
        return "Method genre called from Fiction";
    }
}

// Information about the story of the book
class Story {
    private Author theAuthor;

    // Explains the book plot
    public String plot() {
        return "Method plot called from Story";
    }

    // Retrieve the author of the story
    public Author getTheAuthor() {
        return this.theAuthor;
    }

    // Set the author of the story
    public void setTheAuthor(Author theAuthor) {
        this.theAuthor = theAuthor;
    }
}

// Information about fictional anthologies
class Anthology extends Fiction {
    private Story[] story = new Story[10];

    public Anthology(String isbn, int pages) {
        super(isbn, pages);
    }

    public Anthology() {
        super();
    }

    // Order of the anthology
    public String storyOrder() {
        return "Method storyOrder called from Anthology";
    }

    // Coverart for anthology
    public String coverArt() {
        return "Method coverArt called from Anthology";
    }

    // Retrieve the story
    public Story[] getStory() {
        return this.story;
    }

    // Sets the story
    public void setStory(Story[] story) {
        this.story = story;
    }
}

// Information about fiction novels
class Novel extends Fiction {
    private Author theAuthor;
    private Series mySeries = new Series();

    public Novel(String isbn, int pages) {
        super(isbn, pages);
    }

    public Novel() {
        super();
    }

    // Theme of the novel
    public String theme() {
        return "Method theme called from Novel";
    }

    // Novel coverart
    public String coverArt() {
        return "Method coverArt called from Novel";
    }

    // Retrieves the author of the novel
    public Author getTheAuthor() {
        return this.theAuthor;
    }

    // Retrieves the novel series
    public Series getMySeries() {
        return this.mySeries;
    }

    // Sets the author of the novel
    public void setTheAuthor(Author theAuthor) {
        this.theAuthor = theAuthor;
    }

    // Sets the novel series
    public void setMySeries(Series mySeries) {
        this.mySeries = mySeries;
    }
}

// Publisher information
class Publisher {
    private String name;
    private String address;
    private Classic[] classicsCatalog = new Classic[10];

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Publisher letterhead
    public String printLetterhead() {
        return "Method printLetterhead called from Publisher";
    }

    // Retrieves name of publisher
    public String getName() {
        return this.name;
    }

    // Retrieves address of publisher
    public String getAddress() {
        return this.address;
    }

    // Retrieves catalog of classics published
    public Classic[] getClassicsCatalog() {
        return this.classicsCatalog;
    }

    // Sets name of publisher
    public void setName(String name) {
        this.name = name;
    }

    // Sets address of publisher
    public void setAddress(String address) {
        this.address = address;
    }

    // Sets classics publisher has published
    public void setClassicsCatalog(Classic[] classicsCatalog) {
        this.classicsCatalog = classicsCatalog;
    }
}

// Author information
class Author {
    private String name = "Unknown";
    private String address;
    private int age;

    public Author(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Author() {

    }

    // Works author has written
    public String write() {
        return "Method write called from Author";
    }

    // Retrieves name of author
    public String getName() {
        return this.name;
    }

    // Retrieves address of author
    public String getAddress() {
        return this.address;
    }

    // Retrieves age of author
    public int getAge() {
        return this.age;
    }

    // Sets author's name
    public void setName(String name) {
        this.name = name;
    }

    // Sets address of author
    public void setAddress(String address) {
        this.address = address;
    }

    // Sets age of author
    public void setAge(int age) {
        this.age = age;
    }
}

// Details about book series
class Series {
    private String seriesName;

    // Theme of series
    public String theme() {
        return "Method theme called from Series";
    }

    // Retrieves series name
    public String getSeriesName() {
        return this.seriesName;
    }

    // Sets series name
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}

// Book contract details
class Contract {
    private String date;
    private Publisher publisherInfo;
    private Author authorInfo;

    public Contract(String date, Publisher publisherInfo, Author authorInfo) {
        this.date = date;
        this.publisherInfo = publisherInfo;
        this.authorInfo = authorInfo;
    }

    // Prints contract
    public String printContract() {
        return "Method printContract called from Contract";
    }

    // Retrieves date of contract
    public String getDate() {
        return this.date;
    }

    // Retrieves publisher information
    public Publisher getPublisherInfo() {
        return this.publisherInfo;
    }

    // Retrieves author information
    public Author getAuthorInfo() {
        return this.authorInfo;
    }

    // Sets date of contract
    public void setDate(String date) {
        this.date = date;
    }

    // Sets publisher information
    public void setPublisherInfo(Publisher publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    // Sets author information
    public void setAuthorInfo(Author authorInfo) {
        this.authorInfo = authorInfo;
    }

}

public class MyBook {
    public static void main(String[] args) {
        var history = new Nonfiction();
        var deweyOne = new Category();
        deweyOne.setCategory("History");
        var deweyTwo = new Category();
        deweyTwo.setCategory("French");
        deweyTwo.setSuperCategory(deweyOne);
        deweyOne.setSubCategory(deweyTwo);
        var deweyThree = new Category();
        deweyThree.setCategory("Revolution");
        deweyThree.setSuperCategory(deweyTwo);
        deweyTwo.setSubCategory(deweyThree);
        System.out.println(deweyThree.sort());
        history.setDeweyClassification(deweyThree);
        System.out.println(history.topic());
        System.out.println(history.coverArt());

        var lestat = new Series();
        lestat.setSeriesName("Lestat the Vampire");
        System.out.println(lestat.theme());
        var interviewWithVampire = new Novel();
        interviewWithVampire.setMySeries(lestat);
        var anne = new Author();
        interviewWithVampire.setTheAuthor(anne);
        System.out.println(interviewWithVampire.coverArt());
        System.out.println(interviewWithVampire.genre());

        var vampireInParis = new Story();
        vampireInParis.setTheAuthor(anne);
        System.out.println(vampireInParis.plot());
        var vampiresEverywhere = new Anthology();
        System.out.println(vampiresEverywhere.storyOrder());
    }
}