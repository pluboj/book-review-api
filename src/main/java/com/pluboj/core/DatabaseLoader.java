package com.pluboj.core;

import com.pluboj.book.Book;
import com.pluboj.book.BookRepository;
import com.pluboj.review.Review;
import com.pluboj.user.User;
import com.pluboj.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final BookRepository books;
    private final UserRepository users;

    @Autowired
    public DatabaseLoader(BookRepository books, UserRepository users) {
        this.books = books;
        this.users = users;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book = new Book("Animals", "Joe Wild", "November 2016");
        book.addReview(new Review(5, "This book goes wild!"));
        books.save(book);

        String[] templates = {
                "English - %s Dictionary",
                "English - %s Dictionary",
                "English - %s Dictionary",
                "English - %s Dictionary",
                "English - %s Dictionary"
        };
        String[] buzzwords = {
             "Mandarin",
             "Italian",
             "German",
             "French",
             "Bongo-Bongo",
             "Martian"
        };

        List<User> customers = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
        );
        users.save(customers);

        List<Book> stackOfBooks = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String temp = templates[i % templates.length];
                    String buzz = buzzwords[i % buzzwords.length];
                    String title = String.format(temp, buzz);
                    Book b = new Book(title, "William Word", "Last year");
                    Review review = new Review((i % 5) + 1, String.format("More %s slang words please!", buzz));
                    review.setReviewer(customers.get(i % customers.size()));
                    b.addReview(review);
                    stackOfBooks.add(b);
                });
        books.save(stackOfBooks);
    }
}
