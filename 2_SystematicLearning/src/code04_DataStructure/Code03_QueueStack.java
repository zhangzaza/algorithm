package code04_DataStructure;


//数组结构实现队列
//「循环数组实现队列」
public class Code03_QueueStack {

    //注意点：需要使用 「Node<你传入的类型>」 这种形式进行包装
    //队列玩的就是两个指针，一个头指针一个尾指针，双向队列实现
    //栈的话也是一样的，只需要找一个指针，双向队列实现

    //数组实现栈的话，直接使用index 和一个 数组即可


    //指针循环，实现队列
    public static class CircularQueue {
        private int[] queue;
        private int front;
        private int rear;
        private int size;
        private int capacity;

        // 构造函数，初始化队列
        public CircularQueue(int capacity) {
            this.capacity = capacity;
            this.queue = new int[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        // 入队操作
        public boolean enqueue(int value) {
            if (isFull()) {
                return false;
            }
            //就是值了循环
            rear = (rear + 1) % capacity;

            queue[rear] = value;
            size++;
            return true;
        }

        // 出队操作
        public boolean dequeue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            size--;
            return true;
        }

        // 获取队头元素
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[front];
        }

        // 获取队尾元素
        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return queue[rear];
        }

        // 判空操作
        public boolean isEmpty() {
            return size == 0;
        }

        // 判满操作
        public boolean isFull() {
            return size == capacity;
        }

    }




}
