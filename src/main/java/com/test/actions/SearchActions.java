package com.test.actions;

import com.test.base.BaseActions;
import com.test.entity.Book;

import java.util.List;

public class SearchActions extends BaseActions {

    public boolean checkBooksResult(List<Book> books) {
        if (books.size() == 0)
            return false;

        boolean isExist = false;
        for (Book book : books)
            if (book.getName().equals("Head First Java, 2nd Edition"))
                isExist = true;


        return isExist;
    }
}
