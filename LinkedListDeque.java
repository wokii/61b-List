public class LinkedListDeque<T> implements Deque<T> {

    private class StuffNode{
        public T item;
        public StuffNode next, prev;

        public StuffNode(T i, StuffNode p, StuffNode n){
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }

    private void addToEmptySSL(T i){
        // only used when size == 0
        StuffNode temp = new StuffNode(i, null, null);
        sentinel = new StuffNode(null, temp, temp);
        temp.next = sentinel;
        temp.prev = sentinel;
    }

    public LinkedListDeque(T x){
        StuffNode temp = new StuffNode(x, null, null);
        sentinel = new StuffNode(null, temp, temp);
        temp.next = sentinel;
        temp.prev = sentinel;
        size = 1;

    }
    //to add first, the we create a new node whose prev points to sentinel, and next points to the current first node.
    //then set sentinel.next to point to this new node
    //and current first.prev also point to this new node and size++
    @Override
    public void addFirst(T item){
        if(size == 0){
            addToEmptySSL(item);
        }
        else{
            StuffNode currentFirst = sentinel.next;
            sentinel.next = new StuffNode(item, sentinel, sentinel.next);
            currentFirst.prev = sentinel.next;
        }

        size++;
    }
    //to add last, the we create a new node whose next points to sentinel, and prev points to the current last node.
    //then set sentinel.prev to point to this new node
    //and current last.next also point to this new node and size++
    @Override
    public void addLast(T item){
        if(size == 0){
            addToEmptySSL(item);
        }else {
            StuffNode currentLast = sentinel.prev;
            sentinel.prev = new StuffNode(item, sentinel.prev, sentinel);
            currentLast.next = sentinel.prev;
        }
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
    public void printDeque() {
        StuffNode iterator = sentinel.next; //which is the first node then iterate thru the end of the list.
        for (int i = 0; i < size; i++) {
            System.out.print(iterator.item);
            System.out.print(' ');
            iterator = iterator.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        StuffNode currentFirst = sentinel.next;
        sentinel.next = currentFirst.next;
        currentFirst.next.prev = sentinel;
        currentFirst = new StuffNode(currentFirst.item, null, null);
        size--;
        return currentFirst.item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        StuffNode currentLast = sentinel.prev;
        sentinel.prev = currentLast.prev;
        currentLast.prev.next = sentinel;
        currentLast = new StuffNode(currentLast.item, null, null);
        size--;
        return currentLast.item;
    }
    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        StuffNode finder = sentinel.next;
        int i = 0;
        while(i < index){
            finder = finder.next;
            i++;
        }
        return finder.item;
    }

    private StuffNode getRecHelper(StuffNode iterator, int index){
        if (index == 0){
            return iterator;
        }
        return getRecHelper(iterator.next, index - 1);
    }

    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return getRecHelper(sentinel.next, index).item;
    }
}
