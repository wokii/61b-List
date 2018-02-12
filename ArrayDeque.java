import java.util.Arrays;
public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int currentFirst; // Indicate first item's position in array
    private int currentLast; // Indicate last item's position in array
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }
    public ArrayDeque(T i){
        items = (T[]) new Object[8];
        items[2] = i;
        currentFirst = 2;
        currentLast = 2;
        size = 1;
    }

    public void printArray(){
        System.out.println(Arrays.toString(items));

    }

    private void addArraySize(){
        /**resizing when more space is needed <> indicate original array, [ indicates currentFirst and ] current last.
         pos:                       0  1  2  3  4  5  6  7
         prev array        eg.    < g  h][a  b  c  d  e  f >
         new array     /1  1  1  1<[a  b  c  d  e  f  g  h]>1  1  1  1/
         pos:           0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
         Starting copying from the one forth position of the array (1/4 newSize), to the third forth of the array(3/4 newSize - 1 )

         THIS DOESN'T MODIFY SIZE */
        assert size == items.length;//something is wrong if size != array length.
        int newArraySize = size * 2;
        int newStartingPos = newArraySize / 4;
        T[] temp = (T[]) new Object[newArraySize];
        //should be using item.length instead of size to be precise, but we asserted size has to be equal to
        // items.length at the very beginning, so we are fine.
        System.arraycopy(items, currentFirst, temp, newStartingPos, size - currentFirst);

        //copying starting from first element in original array to new array starting position 1/4 newArraySize
        System.arraycopy(items, 0, temp,newStartingPos + size - currentFirst, currentFirst);
        // copying the second part of array, will copy nothing (0) if currentFirst is at position 0.
        items = temp;
        currentFirst = newStartingPos;
        currentLast = newStartingPos + size - 1;

    }

    /**VERY IMPORTANT: Only contract size when size is bigger than 128, and when size * 4 < items.length;
     *
     */
    public void contractArraySize(){
        assert size * 2 < items.length; //if size * 2 is greater than items.length, then the array shouldn't be modified.
        assert items.length > 8; // smallest array length is 8!!
        int newArraySize = items.length / 2;
        int newStartingPos = newArraySize / 4;
        T[] temp = (T[]) new Object[newArraySize];
        if(currentLast < currentFirst) {
            System.arraycopy(items, currentFirst, temp, newStartingPos, items.length - currentFirst);
            System.arraycopy(items, 0, temp, newStartingPos + items.length - currentFirst, currentLast + 1);
        }else{
            System.arraycopy(items, currentFirst, temp, newStartingPos, size);
        }
        /*copying the second part of the items, starting from 0th position in the original array all the way to
        current last position
        */
        items = temp;
        currentFirst = newStartingPos;
        currentLast = newStartingPos + size - 1;
    }

    private void addToEmptyArray(T i){
        items[2] = i;
        currentFirst = 2;
        currentLast = 2;
        size = 1;

    }
    @Override
    public void addFirst(T item){
        if (isEmpty()){
            addToEmptyArray(item);
            return;
        }

        if(size == items.length){
            addArraySize();
        }
        int nextFirst = (items.length + currentFirst - 1) % items.length; //added item.length to avoid result being negative.
        // useful when currentFirst is already at pos 0, where this will return items.length - 1
        assert nextFirst != currentLast; // nextFirst shouldn't replace currentLast pos!!!
        currentFirst = nextFirst;
        items[nextFirst] = item;
        size++;
    }
    @Override
    public void addLast(T item){
        if (isEmpty()){
            addToEmptyArray(item);
            return;
        }
        if(size == items.length){
            addArraySize();
        }
        int nextLast = (currentLast + 1) % items.length;
        // useful when currentLast is already at end of array, where this will return 0
        assert nextLast != currentFirst; // nextLast shouldn't replace currentFirst pos!!!
        currentLast = nextLast;
        items[nextLast] = item;
        size++;
    }
    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        for(int i = 0; i < size; i++){
            int indexToPrint = (currentFirst + i) % items.length;
            System.out.print(items[indexToPrint]);
            System.out.print(" ");
        }
        System.out.println();
    }
    public int getArrayLength(){ //check the size of the array
        return items.length;
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T first = items[currentFirst];
        items[currentFirst] = null;
        currentFirst = (currentFirst + 1) % items.length;
        size--;
        if (items.length > 128 && size * 4 < items.length){
            contractArraySize();
        }
        return first;

    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T last = items[currentLast];
        items[currentLast] = null;
        currentLast = (currentLast - 1 + items.length) % items.length; // added items.length to avoid result being negative
        size--;
        if (items.length > 128 && size * 4 < items.length){
            contractArraySize();
        }
        return last;
    }
    @Override
    public T get(int index){
        int indexToGet = (index + currentFirst) % items.length;
        return items[indexToGet];
    }
}
