public class LetterUtil {

    public static String convert(String value) {
        String rtName = "";

        for (int i = 0; i < value.length(); i++) {
            rtName = rtName + getPrimary(value.charAt(i));
        }

        return rtName;
    }


    private static String getPrimary(char b) {
        String[] letters = new String[]{
                "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        };

        int index = (b - 44032) / (21 * 28);
        return letters[index];
    }
}
