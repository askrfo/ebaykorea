package examples.boot.myboard.test;

// B라는 사람.
public class ExceptionTest {
    public static void main(String[] args){
        Cal cal = new Cal();
        try {
            int value = cal.divide(4, 0);
            System.out.println(value);
        }catch(MyException ae){
            ae.printStackTrace();
            System.out.println("수학적 오류 :" + ae.toString());
        }
    }
}

// A라는 사람.
class Cal{
    public int divide(int i, int j)
            throws MyException{
        int value = 0;
        try {
            value = i / j;
        }catch(Exception ex){
            throw new MyException(ex);
        }
        return value;
    }
}

// 사용자 정의 Exception
class MyException extends RuntimeException{
    public MyException(String str){
        super(str);
    }

    public MyException(Exception ex){
        super(ex);
    }
}