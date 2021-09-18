import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Nicolas Rios
 * @version 1.0
 * @userid nrios8
 * @GTID 903665797
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Special case: resizing and shuffling in one loop
     *
     * @param array the list to be resized and shuffled
     * @param index the original list to populate from
     * @param data the data to add at the specified index
     */
    private void resizeShuffleAdd(T[] array, int index, T data) {
        int doubledSize = array.length * 2;
        backingArray = (T[]) new Object[doubledSize];
        // case where index should be size
        T temp = (index < array.length) ? array[index] : null;
        // shift everything forward from that point in one loop
        for (int i = 0; i <= array.length + 1; i++) {
            if (i >= index) {
                backingArray[i] = (i == index) ? data : temp;
                temp = (i < array.length) ? array[i] : null;
            } else {
                backingArray[i] = array[i];
            }
        }
        size++;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > backingArray.length) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index > size ");
        }
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null ");
        }
        // make sure array has space for one more entry, if not, resize
        if (size == backingArray.length) {
            resizeShuffleAdd(backingArray, index, data);
        } else if (index == size) {
            addToBack(data);
        } else {
            // add at index and shift
            T replace;
            T held = data;
            for (int i = index; i <= size; i++) {
                replace = held;
                held = backingArray[i];
                backingArray[i] = replace;
            }
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null ");
        } else if (size == backingArray.length) {
            resizeShuffleAdd(backingArray, 0, data);
        } else {
            addAtIndex(0, data);
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException(" ERROR: data is null ");
        } else if (size == backingArray.length) {
            resizeShuffleAdd(backingArray, backingArray.length, data);
        } else {
            backingArray[size] = data;
            size++;
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T data = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index >= size ");
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            data = backingArray[index];
            T replace;
            T held = backingArray[size - 1];
            for (int i = size - 1; i >= index; i--) {
                replace = held;
                held = backingArray[i];
                backingArray[i] = replace;
            }
            backingArray[size - 1] = null;
            size--;
        }
        return data;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException(" ERROR: list is empty ");
        }
        final T data = backingArray[0];
        removeAtIndex(0);
        return data;
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
        if (isEmpty()) {
            throw new NoSuchElementException(" ERROR: list is empty ");
        }
        final T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return data;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" ERROR: index < 0 or index >= size ");
        }
        return backingArray[index];
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
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
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
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
