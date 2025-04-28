package org.example;

import java.util.ArrayList;
import java.util.List;

public class member {
    private String name ;
    private String memberid;
    private List<book> borrowedbooks;
    public  member(String name, String memberId){
        this.name = name;
        this.memberid = memberId;
        this.borrowedbooks = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberid;
    }
    public List<book> getBorrowedbooks(){
        return  borrowedbooks;
    }
    public void borrowbook(book book){
        borrowedbooks.add(book);
    }
    public void returnbook(book book){
        borrowedbooks.remove(book);
    }


    @Override
    public String toString() {
        return "Name: " + name + ", Member ID: " + memberid;
    }
}
