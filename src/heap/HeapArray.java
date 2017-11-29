package heap;

import java.util.Iterator;
import java.util.Comparator; //lambda fonction defined dans le test
import java.util.NoSuchElementException;

                                            //CONSTRUCTEURS, ITERATOR ET GETTERS
public class HeapArray<E> implements Heap<E> {
    private int size;
    private int capacity;
    private E elements[];
    private Comparator<E> comparator;

    /*Constructeur de la classe HeapArray
    on construit le HeapArray vide
    @param initialCapacity est la taille du HeapArray
    @param comp est le comparateur utilisé dans la structure, il dépend du type et est défini lorsqu'on instancie HeapArray à l'aide d'une lambda fonction
     */
    public HeapArray (int initialCapacity, Comparator comp){
        this.size = 0;
        this.capacity = initialCapacity;
        this.elements = (E[]) elements[initialCapacity];
        this.comparator = comp;
    }

    //@return l'iterator pour HeapArray
    public HeapArrayIterator<E> iterator() {
        return new HeapArrayIterator<E>();
    }

    //Implémente l'interface iterator pour la structure HeapArray
    public class HeapArrayIterator<E> implements Iterator<E> {
        private int index;

        //Constructeur de la classe, on commence a -1 "pas encore rentré dans le Heap"
        public HeapArrayIterator(){
            this.index = -1;
        }

        @Override //@return Renvoie si on a atteint le dernier élément ou pas
        public boolean hasNext() {
            return (this.index < size - 1);
        }

        @Override //@return Renvoie l'élément suivant ou lève une exception
        public E next() throws NoSuchElementException{
            if hasNext(){
                index++;
                return elements[index];
            }else{
                throw new NoSuchElementException("On est au dernier élément");
            }
        }
    }

    //Getters
    public Comparator<E> getComparator() {
        return this.comparator;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty(){
        return(size==0);
    }

    public boolean isFull() { return capacity == size; }

    @Override
    public int size() {
        return size;
    }

                                            //FONCTIONS AUXILIAIRES

    //@return Index du plus petit élément du heap
    private int getMin() {
        int minIndex = capacity / 2; //se trouve forcément sur la derniere couche
        E min = elements[minIndex];
        for (int i = capacity / 2 ; i < capacity ; i++) {
            if (comparator.compare(elements[i], min) < 0) {
                minIndex = i;
                min = elements[minIndex];
            }
        }
        return minIndex;
    }

    private int getFatherIndex(int childIndex){
        return (childIndex -1)/2;
    }

    private void swap(int i, int j){
        E saved = elements[i];
        elements[i] = elements[j];
        elements[j] = saved;
    }

    /*Swap fils avec père jusqu'à ce que la condition du heap soit respectée
     */

    private void swapWithFather(int childIndex) {
        if (childIndex > 0) {
            while (comparator.compare(elements[childIndex], elements[getFatherIndex(childIndex)]) > 0) {
                swap(childIndex, getFatherIndex(childIndex));
                childIndex = getFatherIndex(childIndex);
            }
        }
    }
                                        //INSERTION ET ACCES AU HEAP

    @Override
    public boolean insertElement(E e) {
        if (this.isFull() && comparator.compare(e, (E)elements[getMin()]) <= 0) {
            return false;

        } else if (!this.isFull()) {
            elements[size] = e;
            int index = size;
            swapWithFather(index);
            size++;

            return true;
        } else {
            int minIndex = getMin();
            elements[minIndex] = e;
            swapWithFather(minIndex);
            return true;
        }

    }


    @Override
    public E element() {
        try{
            return elements[0];
        }catch(NoSuchElementException e){
            System.out.println("The heap is empty");
        }
    }
}


