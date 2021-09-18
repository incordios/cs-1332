import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Nicolas Rios
 * @version 1.0
 * @userid nrios8 (i.e. gburdell3)
 * @GTID 903665797 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        DoublyLinkedListNode<T> curr;
        DoublyLinkedListNode<T> side = null;
        int i;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index > size");
        } else if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else if (index < size - index) {
            curr = head;
            i = 0;
            while (i < index) {
                if (i == index - 1) {
                    side = curr;
                }
                curr = curr.getNext();
                i++;
            }
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, side, curr);
            curr.setPrevious(newNode);
            if (side != null) {
                side.setNext(newNode);
            }
            size++;
        } else {
            curr = tail;
            i = size - 1;
            while (i > index) {
                if (i == index + 1) {
                    side = curr;
                }
                curr = curr.getPrevious();
                i--;
            }
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, curr, side);
            curr.setNext(newNode);
            if (side != null) {
                side.setPrevious(newNode);
            }
            size++;
        }

    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null");
        } else if (size == 0) {
            head = new DoublyLinkedListNode(data, null, null);
            tail = head;
            size++;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode(data, null, head);
            head.setPrevious(newNode);
            head = newNode;
            size++;
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null");
        } else if (size == 0) {
            head = new DoublyLinkedListNode(data, null, null);
            tail = head;
            size++;
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode(data, tail, null);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        int i;
        DoublyLinkedListNode<T> curr;
        DoublyLinkedListNode<T> side;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index > size ");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else if (index < size - index) {
            curr = head;
            side = head;
            i = 0;
            while (i < index) {
                if (i == index - 1) {
                    side = curr;
                }
                curr = curr.getNext();
                i++;
            }
            side.setNext(curr.getNext());
            curr.getNext().setPrevious(side);
            size--;
            return curr.getData();
        } else {
            curr = tail;
            side = tail;
            i = size - 1;
            while (i > index) {
                if (i == index + 1) {
                    side = curr;
                }
                curr = curr.getPrevious();
                i--;
            }
            side.setPrevious(curr.getPrevious());
            curr.getPrevious().setNext(side);
            size--;
            return curr.getData();
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        DoublyLinkedListNode<T> temp;
        if (size == 0) {
            throw new NoSuchElementException( "ERROR: the list is empty");
        } else if (size == 1) {
            temp = head;
            tail = null;
            head = null;
            size--;
        } else {
            temp = head;
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }
        return temp.getData();
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        DoublyLinkedListNode<T> removedNode;
        if (size == 0) {
            throw new NoSuchElementException( "ERROR: the list is empty");
        } else if (size == 1) {
            removedNode = tail;
            head = null;
            tail = null;
            size--;
        } else {
            removedNode = tail;
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }
        return removedNode.getData();
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        DoublyLinkedListNode<T> curr;
        int i;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index > size");
        } else if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else if (index < size - index) {
            curr = head;
            i = 0;
            while (i < index) {
                curr = curr.getNext();
                i++;
            }
        } else {
            curr = tail;
            i = size - 1;
            while (i > index) {
                curr = curr.getPrevious();
                i--;
            }
        }
        return curr.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null");
        }  else if (tail != null && tail.getData().equals(data)) {
            return removeFromBack();
        } else {
            int index = size - 1;
            DoublyLinkedListNode<T> curr = tail;
            while (index >= 0) {
                if (curr.getData().equals(data)) {
                    return removeAtIndex(index);
                } else if (index != 0) {
                    curr = curr.getPrevious();
                }
                index--;
            }
            throw new NoSuchElementException( " ERROR: data is not found ");
        }
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        DoublyLinkedListNode<T> curr = head;
        int index = 0;
        while (index < size) {
            arr[index] = curr.getData();
            if (curr.getNext() == null) {
                break;
            } else {
                curr = curr.getNext();
            }
            index++;
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
