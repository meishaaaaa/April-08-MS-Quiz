import exception.InputException;

public class Tools {
    //解析输入字符串的方法
    public static String[] parseString(String input, String split, int limit) {
        String[] result = input.split(split);

        if (result.length == limit) {
            return result;
        } else {
            throw new InputException("格式错误，请重新输入");
        }
    }

}
