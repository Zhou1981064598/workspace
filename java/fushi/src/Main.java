import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            nums[i] = scanner.nextInt();
        }
        int [] dp = new int[20];
        int max=0;
        for (int i = 0; i < 5; i++) {
                dp[nums[i]]++;
            max = Math.max(max, dp[nums[i]]);
        }
        System.out.println(max);
        scanner.close();

}


    public  static  String longestPalindrome(String s) {
        int n=s.length();
        if(n<=1){return s;}
        int maxLen=0;
        int start=0;
        boolean[][] dp=new boolean[n][n];
        for(int i=0;i<n;i++){
              dp[i][i]=true;
        }
        for(int i=0;i<n-1;i++){
            if (s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=true;
                maxLen=2;
                start=i;
            }
    }
        for(int L=3;L<=n;L++){
            for(int i=0;i<n-L+1;i++){
                int j=i+L-1;
            if(s.charAt(i)==s.charAt(j)&&dp[i+1][j-1]){
            dp[i][j]=true;
            maxLen=L;
            start=i;
            }
            }
        }
        return s.substring(start,start+maxLen);
    }//动态规划（）最长回文子串
    public static int romanToInt(String s) {
        Map<Character,Integer> map=new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int ans=0;
        for(int i=0;i<s.length();i++){
            if(i<s.length()-1&&map.get(s.charAt(i))<map.get(s.charAt(i+1))){
                ans-=map.get(s.charAt(i));
            }else {
                ans+=map.get(s.charAt(i));
            }
        }
        return ans;
    }//罗马数字
    public static  int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0;int j=0;;int k=0;
        int [] nums=new int[m+n];
        while (i<m&&j<n){
            if(nums1[i]<nums2[j]){
                nums[k++]=nums1[i++];
            }else {
                nums[k++]=nums2[j++];
            }
        }
        while (i<m){nums[k++]=nums1[i++];}
        while (j<n){nums[k++]=nums2[j++];}

        return nums;
    }//归并排序
    public static boolean isPalindrome(int x) {
        if(x<0||x%10==0){return false;}
        int revertnum=0;
        while (revertnum<x){
            revertnum=revertnum*10+x%10;
            x=x/10;
        }
        return x==revertnum||x==revertnum/10;
    }//是否为回文数
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix =longestCommonPrefix(prefix, strs[i]);
        }
        return prefix;
    }//最长公共前缀
    public static String longestCommonPrefix(String str1,String str2) {
        int minlen=Math.min(str1.length(),str2.length());
        for (int i = 0; i < minlen; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minlen);
    }//最长公共前缀{
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(pairs.containsKey(s.charAt(ch))){
                if(stack.isEmpty()||stack.peek()==pairs.get(s.charAt(stack.peek()))){
                    return false;
                }
                stack.pop();
            }
                else {stack.push(ch);}
        }
        return stack.isEmpty();
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list=new ListNode(0);
        ListNode temp=list;
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                temp.next=list1;
                list1=list1.next;
            }else {
                temp.next=list2;
                list2=list2.next;
            }
        }
        if(list1 != null){
            list.next=list1;
        }else {
            list.next=list2;
        }
        return list.next;
    }
}
