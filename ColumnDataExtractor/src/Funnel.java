import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Funnel {

    public static void main(String[] args) {
        File originIdData = new File("C:\\Users\\connect\\Downloads\\원본_식별자.txt");
        File finalIdData = new File("C:\\Users\\connect\\Downloads\\최종_식별자.txt");
        File originData = new File("C:\\Users\\connect\\Downloads\\원본_경력.txt");

        ArrayList<String> originIds = getArray(originIdData); // 원본 식별자
        ArrayList<String> finalIds = getArray(finalIdData); // 최종 식별자
        ArrayList<String> career = getArray(originData); // 원본 경력

        int originCount = 0;
        int finalCount = 0;
        while (finalCount != finalIds.size()) {
            // 원본 식별자과 일치할 경우
            if (originIds.get(originCount).equals(finalIds.get(finalCount))) {
                System.out.println(career.get(originCount));
                finalCount++;
            }

            originCount++;
        }
    }

    private static ArrayList<String> getArray(File file) {
        ArrayList result = new ArrayList<String>();

        if (file.exists()) {
            try (BufferedReader inFile = new BufferedReader(new FileReader(file))) {
                String sLine;
                while ((sLine = inFile.readLine()) != null) {
                    result.add(sLine);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}