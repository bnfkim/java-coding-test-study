import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class BOJ_14719_빗물 {
    static int h, w, str, end, answer;
    static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        blocks = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        getAnswer();
    }

    private static void getAnswer() {
        for (int i = h; i > 0; i--) {
            for (int j = 0, size = blocks.length; j < size; j++) {
                if (blocks[j] >= i) {
                    if (str == 0) {
                        str = j + 1;
                    } else if (str > 0) {
                        end = j + 1;
                        answer += end - str - 1;
                        str = j + 1;
                    }
                }
            }
            str = 0;
            end = 0;
        }
        System.out.println(answer);
    }
}
