package com.abstractclasscodingexercise;

public class MyLinkedList implements NodeList {
    // write code here
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem item) {
        if (root==null) {
            this.root = item;
            return true;
        }

        ListItem currentItem = this.root;
        while(currentItem!=null) {
            int comparison = currentItem.compareTo(item);
            if(comparison<0) {
                if(currentItem.next()!=null) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(item).setPrevious(currentItem);
                    return true;
                }
            } else if (comparison > 0) {
                if(currentItem.previous()!=null) {
                    currentItem.previous().setNext(item).setPrevious(currentItem.previous());
                    item.setNext(currentItem).setPrevious(item);
                } else {
                    item.setNext(this.root).setPrevious(item);
                    this.root = item;
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currItem = this.root;
        while(currItem!=null) {
            int difference = currItem.compareTo(item);
            if (difference == 0) {
                if(currItem==this.root) {
                    this.root = currItem.next();
                } else {
                    currItem.previous().setNext(currItem.next());
                    if(currItem.next()!=null) {
                        currItem.next().setPrevious(currItem.previous());
                    }
                }
                return true;
            } else if ( difference < 0) {
                currItem = currItem.next();
            } else {
                return false;
            }
        }
        return false;
    }

    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
    }
}
