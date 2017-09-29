package com.manoj.datastructure;

import java.util.Iterator;

// import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

  private class Node {
    public Item item;
    public Node next;
    public Node back;

    public Node(Item item) {
      this.item = item;
      this.next = null;
      this.back = null;
    }
  }
  
  private Node first;
  private Node last;
  private int size;
  
  /**
   * Construct an empty Deque. 
   */
  public Deque() {
    this.first = null;
    this.last = this.first;
    this.size = 0;
  }
  
  /**
   * Is the Deque empty.
   * @return true if empty, else false
   */
  public boolean isEmpty() {
    if (this.first == null && this.last == null) {
      return true;
    }
    return false;
  }

  /**
   * Return the number of items on the deque.
   * @return - number of items on the deque.
   */
  public int size() {
    return this.size;
  }

  /**
   * Add the item to the front.
   * @param item - Item
   */
  public void addFirst(Item item) {
    if (item == null) {
      throw new java.lang.IllegalArgumentException("Invalid Argument: "
                 + "cannot add a null item to Dequeue.");
    }

    if (this.isEmpty()) {
      this.first = new Node(item);
      this.last = this.first;
      this.last.next = null;
      this.first.back = null;
      return;
    }

    Node oldFirst = this.first;
    this.first = new Node(item);
    this.first.next = oldFirst;
    oldFirst.back = this.first;
    
    this.size++;
  }

  /**
  * Add the item to the end.
  * @param item - ItemT
  */
  public void addLast(Item item) {
    if (item == null) {
      throw new java.lang.IllegalArgumentException("Invalid Argument: "
             + "cannot add a null item to Dequeue.");
    }

    if (this.isEmpty()) {
      addFirst(item);
      return;
    }
    
    Node oldLast = this.last;
    this.last = new Node(item);
    oldLast.next = this.last;
    this.last.back = oldLast;
    
    this.size++;
   
  }

  /**
  * remove and return the item from the front.
  * @return - Item
  */
  public Item removeFirst() {
    if (this.isEmpty()) {
      throw new java.util.NoSuchElementException("Deque is empty.");
    }
    
    final Item firstItem = this.first.item;
    
    this.first = this.first.next;
    this.first.back = null;
    this.size--;
    
    return firstItem;
  }

  /**
   * remove and return the item from the end.
   * @return - Item
   */
  public Item removeLast() {
    if (this.isEmpty()) {
      throw new java.util.NoSuchElementException("Deque is empty.");
    }
    
    final Item lastItem = this.last.item;
    
    this.last = this.last.back;
    this.last.next = null;
    this.size--;
    
    return lastItem;
  }                
  
  /**
   * Main method to run test cases for the object - Deque.
   * @param args - Pass program arguments
   */
  public static void main(String[] args) {
    Deque<String> intQueue = new Deque<String>();

    for (int i = 0; i < 10; i++) {
      intQueue.addFirst("First Value " + i);
      intQueue.addLast("Last Value " + i);
    }
    
    for (String item : intQueue) {
      System.out.println(item);
    }
    
    for (int i = 0; i < 5; i++) {
      intQueue.removeFirst();
      intQueue.removeLast();
    }
    
    for (String item : intQueue) {
      System.out.println(item);
    }
  }
  
  /**
   * Iterator over the items T.
   */
  public Iterator<Item> iterator() {
    Iterator<Item> it = new Iterator<Item>() {

      private Node current = first;
      
      public boolean hasNext() {
        if (current != null) {
          return true;
        }
        return false;
      }

      public Item next() {
        if (current.item == null) {
          throw new java.util.NoSuchElementException("Deque is empty.");
        }
        Item returnItem = current.item;
        current = current.next;
        return returnItem;
      }
      
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };

    return it;
  }

}
