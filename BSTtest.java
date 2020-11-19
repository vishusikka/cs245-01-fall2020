/*
* Main testing class for BST
* @author: USFCACS 245
*/
public class BSTtest
{
    public static void main(String[] args)
    {
        //TODO CHANGE CLASS AS NEEDED TO TEST CODE
        BST<String> tree = new BST<String>();


        int L = Integer.parseInt(args[0]);
        int R = Integer.parseInt(args[1]);
        for(int i=2; i < args.length; i++)
        {
            tree.insert(args[i]);

        }

        tree.delete(args[3]);
        tree.delete("60");
        System.out.println(tree.find(args[4]));
        tree.print();
        System.out.println(tree.find("60"));

        System.out.println(tree.rangeSum(L, R));



        BST<Integer> tree2 = new BST<Integer>();
        L = 10;
        R = 30;
        tree2.insert(20);
        tree2.insert(30);
        tree2.insert(10);
        tree2.insert(1);

        tree2.delete(10);
        tree2.delete(5);
        System.out.println(tree2.find(1));
        tree2.print();
        System.out.println(tree2.find(5));

        System.out.println(tree2.rangeSum(L, R));
    }
}
