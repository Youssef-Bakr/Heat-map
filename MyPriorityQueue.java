
public class MyPriorityQueue {
    class QueueNode{
        private QueueNode Next;
        private double plantAmount;
        private String cityName;

        protected QueueNode(double plantAmount, String cityName){
            this.plantAmount = plantAmount;
            this.cityName = cityName;
            Next = null;
        }

        public double getPlantAmount() {
            return plantAmount;
        }

        public QueueNode getNext() {
            return Next;
        }

        public String getCityName() {
            return cityName;
        }

        protected void setNext(QueueNode next) {
            Next = next;
        }

        protected void setCityName(String cityName) {
            this.cityName = cityName;
        }

        protected void setPlantAmount(double plantAmount) {
            this.plantAmount = plantAmount;
        }
    }
    QueueNode front;
    QueueNode rear;

    public void Enqueue(String Data){
        String[] Dataset = Data.split("-");
        QueueNode newNode = new QueueNode(Double.parseDouble(Dataset[1]), Dataset[0]);
        if(front == null){
            front = newNode;
            rear = newNode;
            front.setNext(rear);
            rear.setNext(null);
            return;
        }
//        if(front == rear){
//            if(front.getPlantAmount() > Double.parseDouble(Dataset[1])){
//                rear.setNext(newNode);
//                rear = newNode;
//                rear.setNext(null);
//            }else{
//                newNode.setNext(front);
//                front = newNode;
//            }
//        } else{
//            QueueNode pointer = front, pointerPrev = front;
//            while(pointer.getPlantAmount() > Double.parseDouble(Dataset[1])){
//                pointerPrev = pointer;
//                pointer = pointer.getNext();
//            }
//            newNode.setNext(pointerPrev.getNext());
//            pointerPrev.setNext(newNode);
//        }
        else {
            if ( front.getPlantAmount() < Double.parseDouble(Dataset[1])){
                newNode.setNext(front);
                front = newNode;
            }
            else {
                QueueNode current = front ;
                while(Double.parseDouble(Dataset[1]) > current.getPlantAmount()){
                    current = current.getNext();
                }
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
        }
    }
    public QueueNode Dequeue(){
        if(isEmpty()){
            System.out.println("Queue is Empty!");
            return null;
        }
        QueueNode temp = front;
        front = front.getNext();
        return temp;
    }
    public boolean isEmpty(){
        return front == null;
    }
    @Override
    public String toString(){
        String temp = "";
        QueueNode pointer = front;
        while(pointer != null) {
            temp += pointer.getCityName() + "  -  " + pointer.plantAmount + "  ||  ";
            pointer = pointer.getNext();
        }
        return temp;

    }
}
