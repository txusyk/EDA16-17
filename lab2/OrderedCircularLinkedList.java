package lab2;

public class OrderedCircularLinkedList<T extends Comparable<T>> extends CircularLinkedList<T> implements OrderedListADT<T> {

    public void add(T elem) {
        Node<T> current;    //pointer
        Node<T> auxNode = new Node<T>(elem); //auxiliar pointer
        boolean finded = false; //flag for while

        if (count == 0 ? true : false) {//if it's empty
            last = auxNode;
            auxNode.next = auxNode;
        } else {
            current = last.next;//first element
            if (current.data.compareTo(elem) > 0) {//if the first is > elem
                auxNode.next = last.next;
                last.next = auxNode;

            } else if (last.data.compareTo(elem) < 0) {//if the last < auxNode
                auxNode.next = last.next;
                last.next = auxNode;
                last = auxNode;
            } else { //between two elements
                while (!finded) {
                    if (current.data.compareTo(elem) < 0 && current.next.data.compareTo(elem) > 0) { //if whe've finded where to insert
                        auxNode.next = current.next;
                        current.next = auxNode;
                        finded = true;
                    }
                    current = current.next;
                }
            }
        }
        count++;
    }
}
