package com.foursquare.takehome;

import java.util.LinkedList;

public class SortedLinkedList<E> extends LinkedList<E> {
    private Link<E> first;
    private Link<E> last;

    public SortedLinkedList() {
        first = null;
        last = null;

    }

    private class Link<E> {
        public Comparable<E> data;
        public Link next;

    }

    public boolean add(E obj)
    {
        Link newLink = new Link();
        newLink.data = (Comparable<E>)obj;

        // When the list is initially empty
        if (first == null)
        {
            first = newLink;
            last = newLink;
            return true;
        }


        // When the element to be added is less than the first element in the list
        if (newLink.data.compareTo(first.data) < 0)
        {
            //newLink.data = obj;
            newLink.next = first;
            first = newLink;
            return true;
        }

        // When the element to be added is greater than every element in in list
        // and has to be added at end of the list
        if (newLink.data.compareTo(last.data) > 0)
        {
            //newLink.data = obj;
            last.next = newLink;
            last = newLink;
            return true;
        }

        //When the element to be added lies between other elements in the list
        if (newLink.data.compareTo(first.data) >= 0 && newLink.data.compareTo(last.data) <= 0)
        {
            //newLink.data = obj;
            Link current = first.next;
            Link previous = first;
            while (newLink.data.compareTo(current.data) <= 0)
            {
                previous = current;
                current = current.next;
            }
            previous.next = newLink;
            newLink.next = current;

        }

        return true;
    }
}

