package com.abstractclasscodingexercise;

public class SearchTree implements NodeList {

    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem item) {
        if(this.root==null) {
            this.root = item;
            return true;
        } else {
            ListItem currItem = root;
            while (currItem!=null) {
                int difference = currItem.compareTo(item);
                if (difference == 0) {
                    System.out.println(item.getValue() + " is already present");
                    return false;
                } else if (difference < 0) {
                    if(currItem.next()==null) {
                        currItem.setNext(item);
                        return true;
                    } else {
                        currItem = currItem.next();
                    }
                } else {
                    if(currItem.previous()==null) {
                        currItem.setPrevious(item);
                        return true;
                    }
                    currItem = currItem.previous();
                }
            }
        }
        return false;
    }

    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currItem = this.root;
        ListItem parentItem = currItem;

        while(currItem!=null) {
            int difference = currItem.compareTo(item);
            if(difference==0) {
                performRemoval(currItem,parentItem);
                return true;
            } else if (difference<0) {
                parentItem = currItem;
                currItem = currItem.next();
            } else {
                parentItem = currItem;
                currItem = currItem.previous();
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.next() == null) {
            if (parent.next() == item) {
                parent.setNext(item.previous());
            } else if (parent.previous() == item) {
                parent.setPrevious(item.previous());
            } else {
                this.root = item.previous();
            }
        } else if (item.previous() == null) {
            if (parent.next() == item) {
                parent.setNext(item.next());
            } else if (parent.previous() == item) {
                parent.setPrevious(item.next());
            } else {
                this.root = item.next();
            }
        } else {
            ListItem current = item.next();
            ListItem leftmostParent = item;
            while (current.previous() != null) {
                leftmostParent = current;
                current = current.previous();
            }
            item.setValue(current.getValue());
            if (leftmostParent == item) {
                item.setNext(current.next());
            } else {
                leftmostParent.setPrevious(current.next());
            }
        }
    }

    public void traverse(ListItem root) {
        if (root != null) {
            traverse(root.previous()); // to the left of root
            System.out.println(root.getValue());
            traverse(root.next()); // to the right of root
        }
    }
}
