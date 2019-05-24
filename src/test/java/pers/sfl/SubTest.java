package pers.sfl;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 22:56
 */
public class SubTest {
    public static void main(String[] args) {
        String str="access_token=6d5abcd22a927ecfd53cc04ebfcd2b3acfc69353&scope=user&token_type=bearer";
        int start =str.indexOf("=")+1;
        System.out.println(str.indexOf("=")+1);
        int end = str.indexOf('&');
        System.out.println(str.indexOf('&'));
        System.out.println(str.substring(start,end));
        System.out.println();

    }
}
