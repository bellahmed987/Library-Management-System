package org.example;

import java.util.ArrayList;
import java.util.List;

public class library {
    public List<book> books;
    public List<member> members;
    public library(){
        books =new ArrayList<>();
        members=new ArrayList<>();
    }
    public void addBook(book book) {
        books.add(book);
    }
    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }
    public void registerMember(member member) {
        members.add(member);
    }
    public book searchbook(String query)
    {
        for(book book :books){
            if(book.getTitle().equalsIgnoreCase(query)||book.getAuthor().equalsIgnoreCase(query)||book.getIsbn().equalsIgnoreCase(query)){
                return  book ;
            }

        }
        return null;
    }
    public boolean borrowbook(member member,String isbn){
book book=searchbook(isbn);
        if(book!=null && book.isAvailable() ){
            book.setAvailable(false);
            member.borrowbook(book);
            return true;
        }
        return false;
    }
    public boolean returnBook(member member, String isbn) {
        book book = searchbook(isbn);
        if (book != null && !book.isAvailable() && member.getBorrowedbooks().contains(book)) {
            book.setAvailable(true);
            member.returnbook(book);
            return true;
        }
        return false; // Book not borrowed or not available
    }
    public void displayBooks() {
        for (book book : books) {
            System.out.println(book);
        }
    }

    // Display all members
    public void displayMembers() {
        for (member member : members) {
            System.out.println(member);
        }
    }
}
