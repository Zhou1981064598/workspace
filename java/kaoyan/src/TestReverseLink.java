

/**
 * @author 徐登宇
 */
public class TestReverseLink {

    // 链表节点的数据结构
    private static class Node{
        private int data; // 当前节点的值
        private Node next; // 当前节点指向的下一个节点

        public Node(int data){
            this.data=data;
        }
    }

    // 构建链表并返回头结点
    private static Node createLink(){
        Node head = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        head.next=n1;
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        return head;
    }

    // 打印链表
    private static void printLink(Node head){
        // 如果链表只有一个节点，直接打印该节点的值
        if (head.next==null) {
            System.out.println(head.data);
        }
        while (head!=null){
            System.out.print(head.data+" -> ");
            head=head.next;
        }
    }

    /**
     * 通过头插法来实现链表反转
     *
     * 需要三个变量：
     *   1. 作为每次反转时被指向的节点，最后被作为完成反转的链表的头结点返回；
     *   2. 作为头插时被插入的头结点
     *   3. 作为保存后续节点的作用，并起到判断是否走到链表最后的作用【可以用原始链表的头结点实现这些个作用：每次循环head=head.next就可以】
     *
     *
     * @param head 原始链表头结点
     * @return 链表反转后的新的头结点
     */
    private static Node reverseWithHeadInsert(Node head){
        // 当链表只有一个节点，或者没有节点时，直接返回
        if (head==null||head.next==null) {
            return head;
        }

        // 头插法实现链表反转，也需要三个变量
        // 1. 作为反转后的新的头结点，每次头插最后指向新插上的头结点
        // 2. 作为临时节点，实现头插的作用，作为临时的头结点
        // 3. 【我们将原始链表的头结点作为这个变量就可以】作为是否走到链表最后的条件，以及实现保存原始链表的节点的作用
        Node newHead=null,temp;
        while (head!=null){
            temp=head;
            head=head.next;
            temp.next=newHead;
            newHead=temp;

        }
        return newHead;

    }

    public static Node recursion(Node listNode){
        if(listNode!=null&&listNode.next!=null){
            Node newHead=recursion(listNode.next);
            System.out.print(newHead.data);
            listNode.next.next=listNode;
            listNode.next=null;
            return newHead;
        }
        return listNode;
    }
    public static void main(String[] args) {
        Node head = createLink();
        System.out.println("反转前：");
        printLink(head);
        System.out.println("\n反转后：");
        head=recursion(head);
       // head=reverseWithHeadInsert(head);
        //printLink(head);
    }

}

