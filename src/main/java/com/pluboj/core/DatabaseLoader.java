package com.pluboj.core;

import com.pluboj.book.Book;
import com.pluboj.book.BookRepository;
import com.pluboj.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final BookRepository books;

    @Autowired
    public DatabaseLoader(BookRepository books) {
        this.books = books;
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
             "Bongo-Bongo"
        };

        List<Book> stackOfBooks = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String temp = templates[i % templates.length];
                    String buzz = buzzwords[i % buzzwords.length];
                    String title = String.format(temp, buzz);
                    Book b = new Book(title, "William Word", "Last year");
                    b.addReview(new Review((i % 5) + 1, String.format("More %s slang words please!", buzz)));
                    stackOfBooks.add(b);
                });
        books.save(stackOfBooks);
    }
}
